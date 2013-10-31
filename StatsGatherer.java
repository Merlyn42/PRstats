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
