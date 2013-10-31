import java.io.*;
import java.util.Map;
import java.util.logging.Logger;


public class AmmunitionFactory {
	static String damage = "ObjectTemplate.damage ";
	static String minDamage = "ObjectTemplate.minDamage ";
	static String minDist = "ObjectTemplate.DistToStartLoseDamage ";
	static String maxDist = "ObjectTemplate.DistToMinDamage ";
	static String gravityModifier = "ObjectTemplate.gravityModifier ";
	
	private final static Logger LOGGER = Logger.getLogger(AmmunitionFactory.class.getName());
	
	 static Ammunition createAmmunition(File f,Map<String, Ammunition> ammo){
		 String name =f.getName().substring(0, f.getName().indexOf('.'));
		 
		 Ammunition result = ammo.get(name);
		 if(result==null){
			 result= new Ammunition();
			 LOGGER.finer("Creating ammunition: "+name);
		 }else{
			 LOGGER.finer("Updating ammunition: "+name);
		 }
		 BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(f));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
		 while(true){
			 String work;
			try {
				work = reader.readLine();
			} catch (IOException e) {	
				e.printStackTrace();
				return null;
			}
			 if (work==null||work.startsWith("rem")) break;
			 if(work.contains(damage)){
				 result.maxDamage = Double.valueOf(work.substring(damage.length(),work.length()));
			 }
			 if(work.contains(minDamage)){
				 result.minDamage = Double.valueOf(work.substring(minDamage.length(),work.length()));
			 }
			 if(work.contains(minDist)){
				 result.minDist = Double.valueOf(work.substring(minDist.length(),work.length()));
			 }
			 if(work.contains(maxDist)){
				 result.maxDist = Double.valueOf(work.substring(maxDist.length(),work.length()));
			 }
			 if(work.contains(gravityModifier)){
				 result.gravityModifier = Double.valueOf(work.substring(gravityModifier.length(),work.length()));
			 }
		 }
		 result.name =f.getName().substring(0, f.getName().indexOf('.'));
		 
		
		return result;
		
		
	}

}
