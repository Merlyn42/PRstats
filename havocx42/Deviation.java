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
