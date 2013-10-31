
public class Weapon {
	String name;
	public Double magSize;
	public Double reloadTime;
	public Double roundsPerMinute;
	public String recoilForceUp;
	double velocity;
	Ammunition ammo;
	Deviation dev;
	
	
	public Weapon() {
		super();
	}
	
	public boolean complete(){
		
		if(ammo!=null&&dev!=null&&reloadTime!=null&&recoilForceUp!=null&&name!=null){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public String toString() {
		try{
		return "Weapon [name=" + name + ", magSize=" + magSize
				+ ", reloadTime=" + reloadTime + ", recoilForceUp="
				+ recoilForceUp + ", ammo=" + ammo.toString() + ", dev=" + dev.toString() + "]";
		}catch( NullPointerException e){
			return "Weapon name="+ name +" does not have all attributes";
		}
	}

	
	

}
