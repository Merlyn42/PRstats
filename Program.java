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

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;

public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// get location
		File dataFolder = new File("E:/prweapons/weapons1.0/");
		File output = new File("weaponstatsn1.0.xls");
		
		// categorize
		ArrayList<File> ammoFiles = new ArrayList<File>();
		ArrayList<File> weaponFiles = new ArrayList<File>();
		categorizeFiles(dataFolder, weaponFiles, ammoFiles);
		
		
		// gather data
		StatsGatherer gatherer = new StatsGatherer();
		gatherer.initAmmunitionData(ammoFiles);
		Map<String, Weapon> weapons = gatherer.parseWeaponFiles(weaponFiles);
		
		//separate complete and incomplete categories
		ArrayList<Weapon> completeWeapons = new ArrayList<Weapon>();
		ArrayList<Weapon> incompleteWeapons = new ArrayList<Weapon>();
		for(Weapon weapon : weapons.values()){
			if(weapon.complete()){
				completeWeapons.add(weapon);
			}else{
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
					ammoFiles.add(file);
				} else if (contents.contains("genericfirearm")) {
					weaponFiles.add(file);
				} else {
					System.err.println("Unable to categorize file:" + file.getName());
				}
			}
		}
	}
}
