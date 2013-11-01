/*	Copyright 2013 havocx42
	
	This file is part of PRStats.

    PRStats is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    PRStats is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with PRStats.  If not, see <http://www.gnu.org/licenses/>.
*/
package havocx42;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

public class Controller {
	private final static Logger LOGGER = Logger.getLogger(Controller.class.getName());
	
	public void run(File dataFolder,File output){
		LOGGER.info("source: " + dataFolder.getAbsolutePath());
		LOGGER.info("target: " + output.getAbsolutePath());

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
			LOGGER.info("Your excel file has been written!");
		} catch (IOException e) {
			System.err.println("Unable to save stats: "+e.getMessage());
			LOGGER.log(Level.SEVERE,"Unable to save stats" , e);
		}

	}
	
	private void categorizeFiles(File directory, List<File> weaponFiles, List<File> ammoFiles) {
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
