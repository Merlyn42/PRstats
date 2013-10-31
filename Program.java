import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;

public class Program {

	/**
	 * @param args
	 */
	
	private final static Logger LOGGER = Logger.getLogger(Program.class.getName());
	
	private static void initRootLogger() throws SecurityException, IOException {

		FileHandler fileHandler;
		fileHandler = new FileHandler("PRWeaponStats.%u.%g.txt", 1024 * 1024*8, 3, false);
		fileHandler.setLevel(Level.FINEST);
		fileHandler.setFormatter(new SimpleFormatter());
		Logger rootLogger = Logger.getLogger("");
		Handler[] handlers = rootLogger.getHandlers();
		for (Handler handler : handlers) {
			handler.setLevel(Level.INFO);
		}
		rootLogger.setLevel(Level.FINEST);
		rootLogger.addHandler(fileHandler);
	}

	
	
	public static void main(String[] args) {
		try {
			initRootLogger();
		} catch (SecurityException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		// get location
		File dataFolder = new File("E:/prweapons/weapons1.0/");
		File output = new File("weaponstatsn1.0.xls");
		LOGGER.info("source data folder: " + dataFolder.getAbsolutePath());
		LOGGER.info("output file: " + output.getAbsolutePath());
		
		// categorize
		ArrayList<File> ammoFiles = new ArrayList<File>();
		ArrayList<File> weaponFiles = new ArrayList<File>();
		LOGGER.finer("Categorizing files");
		categorizeFiles(dataFolder, weaponFiles, ammoFiles);
		
		
		// gather data
		LOGGER.finer("gathering data files");
		StatsGatherer gatherer = new StatsGatherer();
		gatherer.initAmmunitionData(ammoFiles);
		Map<String, Weapon> weapons = gatherer.parseWeaponFiles(weaponFiles);
		
		//separate complete and incomplete categories
		ArrayList<Weapon> completeWeapons = new ArrayList<Weapon>();
		ArrayList<Weapon> incompleteWeapons = new ArrayList<Weapon>();
		for(Weapon weapon : weapons.values()){
			if(weapon.complete()){
				LOGGER.finer("Weapon: "+weapon.name+" is complete");
				completeWeapons.add(weapon);
			}else{
				LOGGER.finer("Weapon: "+weapon.name+" is not complete");
				incompleteWeapons.add(weapon);
			}
		}
		
		// pass each category to the excel sheet
		ExcelFile excel = new ExcelFile();
		excel.createWeaponSheet("Weapons", completeWeapons);
		excel.createWeaponSheet("Other Items", incompleteWeapons);
		
		// write the excel sheet
		try {
			excel.writeWorkbook(output);
		} catch (IOException e) {
			System.err.println("Unable to save stats: "+e.getMessage());
			LOGGER.log(Level.SEVERE,"Unable to save stats" , e);
		}

	}



	public static void categorizeFiles(File directory, List<File> weaponFiles, List<File> ammoFiles) {
		String[] extensions = { "con", "tweak" };
		Collection<File> files = FileUtils.listFiles(directory, extensions, true);
		for (File file : files) {
			String contents = null;
			try {
				contents = FileUtils.readFileToString(file).toLowerCase();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (contents != null) {
				if (contents.contains("genericprojectile")) {
					LOGGER.finer("Categorizing "+file.getName()+" as ammunition");
					ammoFiles.add(file);
				} else if (contents.contains("genericfirearm")) {
					LOGGER.finer("Categorizing "+file.getName()+" as weapon");
					weaponFiles.add(file);
				} else {
					LOGGER.finer("Unable to categorize file: "+file.getName());
				}
			}
		}
	}
}
