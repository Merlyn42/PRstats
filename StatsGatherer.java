import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class StatsGatherer {
	Map<String, Ammunition> ammo = new HashMap<String, Ammunition>();
	

	public void initAmmunitionData(ArrayList<File> ammoFiles){
		for (File ammoFile : ammoFiles) {
			Ammunition a = AmmunitionFactory.createAmmunition(ammoFile, ammo);
			ammo.put(a.name, a);
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
			weapons.put(w.name,w);
		}
		return weapons;
	}
}
