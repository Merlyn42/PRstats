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

public class Weapon {
	String name;
	public Double magSize;
	public Double reloadTime;
	public Double roundsPerMinute;
	public String recoilForceUp;
	public String recoilForceLeftRight;
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
				+ recoilForceUp + ", recoilForceLeftRight=" + recoilForceLeftRight + ", ammo=" + ammo.toString() + ", dev=" + dev.toString() + "]";
		}catch( NullPointerException e){
			return "Weapon name="+ name +" does not have all attributes";
		}
	}

	
	

}
