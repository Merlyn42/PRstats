
public class Deviation {
	double minDev;
	double setFireDevMax;
	double setFireDevAdd;
	double setFireDevCool;
	double setTurnDevMax;
	double setTurnDevLeft;
	double setTurnDevRight;
	double setTurnDevCool;
	double setSpeedDevMax;
	double setSpeedDevMove;
	double setSpeedDevStrafe;
	double setSpeedDevCool;
	double setMiscDevMax;
	double setMiscDevAdd;
	double setMiscDevCool;
	double devModStand;
	double devModCrouch;
	double devModLie;
	double devModZoom;
	
	double singleFireCoolDown(){
		return setFireDevAdd/(30*setFireDevCool);		
	}
	double maxFireCoolDown(){
		return setFireDevMax/(30*setFireDevCool);
	}
	double turnCoolDown(){
		return setTurnDevMax/(30*setTurnDevCool);
		
	}
	double speedCoolDown(){
		return setSpeedDevMax/(30*setSpeedDevCool);
	}
	double miscCoolDown(){
		return setMiscDevMax/(30*setMiscDevCool);
		
	}
	@Override
	public String toString() {
		return "Deviation [minDev=" + minDev + ", setFireDevMax="
				+ setFireDevMax + ", setFireDevAdd=" + setFireDevAdd
				+ ", setFireDevCool=" + setFireDevCool + ", setTurnDevMax="
				+ setTurnDevMax + ", setTurnDevLeft=" + setTurnDevLeft
				+ ", setTurnDevRight=" + setTurnDevRight + ", setTurnDevCool="
				+ setTurnDevCool + ", setSpeedDevMax=" + setSpeedDevMax
				+ ", setSpeedDevMove=" + setSpeedDevMove
				+ ", setSpeedDevStrafe=" + setSpeedDevStrafe
				+ ", setSpeedDevCool=" + setSpeedDevCool + ", setMiscDevMax="
				+ setMiscDevMax + ", setMiscDevAdd=" + setMiscDevAdd
				+ ", setMiscDevCool=" + setMiscDevCool + ", devModStand="
				+ devModStand + ", devModCrouch=" + devModCrouch
				+ ", devModLie=" + devModLie + ", devModZoom=" + devModZoom
				+ ", singleFireCoolDown()=" + singleFireCoolDown()
				+ ", maxFireCoolDown()=" + maxFireCoolDown()
				+ ", turnCoolDown()=" + turnCoolDown() + ", speedCoolDown()="
				+ speedCoolDown() + ", miscCoolDown()=" + miscCoolDown() + "]";
	}
	
	
	
	
	

}
