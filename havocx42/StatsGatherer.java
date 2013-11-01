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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class StatsGatherer {
	Map<String, Ammunition> ammo = new HashMap<String, Ammunition>();
	
	private final static Logger LOGGER = Logger.getLogger(StatsGatherer.class.getName());
	
	public void initAmmunitionData(ArrayList<File> ammoFiles){
		for (File ammoFile : ammoFiles) {
			Ammunition a = AmmunitionFactory.createAmmunition(ammoFile, ammo);
			LOGGER.finer("Adding ammunition: "+a.name);
			ammo.put(a.name.toLowerCase(), a);
		}
	}
	
	public Map<String, Weapon> parseWeaponFiles(ArrayList<File> weaponFiles){
		Map<String, Weapon> weapons = new HashMap<String, Weapon>();
		WeaponFactory weaponFactory = new WeaponFactory(ammo);
		for (File weaponFile : weaponFiles) {
 			Weapon w = weaponFactory.createWeaponFromFile(weaponFile,weapons);
			if (w == null) {
				System.err.println("Null Weapon created");
			}
			weapons.put(w.name.toLowerCase(),w);
		}
		return weapons;
	}
}
