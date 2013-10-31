package havocx42;
import java.io.FileInputStream;


public class Ammunition {
	String name;
	double maxDamage;
	double minDamage;
	double minDist;
	double maxDist;
	double gravityModifier;
	
	public Ammunition(String name, double maxDamage, double minDamage, double minDist,
			double maxDist, double gravityModifier) {
		super();
		this.name = name;
		this.maxDamage = maxDamage;
		this.minDamage = minDamage;
		this.minDist = minDist;
		this.maxDist = maxDist;
		this.gravityModifier = gravityModifier;
	}
	public Ammunition(){
		
	}
	@Override
	public String toString() {
		return "Ammunition [name=" + name + ", maxDamage=" + maxDamage
				+ ", minDamage=" + minDamage + ", minDist=" + minDist
				+ ", maxDist=" + maxDist + ", gravityModifier="
				+ gravityModifier + "]";
	}
	

	

	

}
