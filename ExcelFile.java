import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class ExcelFile {
	private HSSFWorkbook workbook;
	
	public ExcelFile(){
		workbook = new HSSFWorkbook();
	}
	
	public void createWeaponSheet(String sheetName,List<Weapon> weapons){
		HSSFSheet sheet = workbook.createSheet(sheetName);
		setupSheet(sheet);
		populateSheet(sheet, weapons);
	}
	
	public void writeWorkbook(File outputLocation) throws IOException{
		FileOutputStream fileOut = new FileOutputStream(outputLocation);
		workbook.setForceFormulaRecalculation(true);
		try{
		workbook.write(fileOut);
		}finally{
			try{
				fileOut.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private void populateSheet(HSSFSheet sheet, List<Weapon> weapons) {
		int i=0;
		Comparator<Weapon> comparator = new Comparator<Weapon>() {
		    public int compare(Weapon c1, Weapon c2) {
		        return c1.name.compareTo(c2.name);
		    }
		};
		Collections.sort(weapons,comparator);
		for (Weapon w : weapons) {
			HSSFRow row = sheet.createRow((int) i + 1);
			
//			System.out.println("Writing data for: " + w.name);
			if (w.name != null)
				row.createCell((int) 0).setCellValue(w.name);
			if (w.magSize != null)
				row.createCell((int) 1).setCellValue(w.magSize);
			if (w.reloadTime != null)
				row.createCell((int) 2).setCellValue(w.reloadTime);
			if (w.recoilForceUp != null)
				row.createCell((int) 3).setCellValue(w.recoilForceUp);
			row.createCell((int) 4).setCellValue(w.velocity);
			if (w.roundsPerMinute != null)
				row.createCell((int) 5).setCellValue(w.roundsPerMinute == null ? "null" : w.roundsPerMinute.toString());
			if (w.ammo != null) {
				row.createCell((int) 6).setCellValue(w.ammo.name);
				row.createCell((int) 7).setCellValue(w.ammo.maxDamage);
				row.createCell((int) 8).setCellValue(w.ammo.minDamage);
				row.createCell((int) 9).setCellValue(w.ammo.maxDist);
				row.createCell((int) 10).setCellValue(w.ammo.minDist);
				row.createCell((int) 11).setCellValue(w.ammo.gravityModifier);
			}
			if (w.dev != null) {
				row.createCell((int) 12).setCellValue(w.dev.devModStand);
				row.createCell((int) 13).setCellValue(w.dev.devModCrouch);
				row.createCell((int) 14).setCellValue(w.dev.devModLie);
				row.createCell((int) 15).setCellValue(w.dev.devModZoom);
				row.createCell((int) 16).setCellValue(w.dev.minDev);
				row.createCell((int) 17).setCellValue(w.dev.setFireDevMax);
				row.createCell((int) 18).setCellValue(w.dev.setFireDevAdd);
				row.createCell((int) 19).setCellValue(w.dev.setFireDevCool);
				row.createCell((int) 20).setCellValue(w.dev.setTurnDevMax);
				row.createCell((int) 21).setCellValue(w.dev.setTurnDevLeft);
				row.createCell((int) 22).setCellValue(w.dev.setTurnDevRight);
				row.createCell((int) 23).setCellValue(w.dev.setTurnDevCool);
				row.createCell((int) 24).setCellValue(w.dev.setSpeedDevMax);
				row.createCell((int) 25).setCellValue(w.dev.setSpeedDevMove);
				row.createCell((int) 26).setCellValue(w.dev.setSpeedDevStrafe);
				row.createCell((int) 27).setCellValue(w.dev.setSpeedDevCool);
				row.createCell((int) 28).setCellValue(w.dev.setMiscDevMax);
				row.createCell((int) 29).setCellValue(w.dev.setMiscDevAdd);
				row.createCell((int) 30).setCellValue(w.dev.setMiscDevCool);
				row.createCell((int) 31).setCellFormula("(S" + (i + 2) + "/(30*T" + (i + 2) + "))");
				row.createCell((int) 32).setCellFormula("(Y" + (i + 2) + "/(30*AB" + (i + 2) + "))");
				row.createCell((int) 33).setCellFormula("(AD" + (i + 2) + "/(30*AE" + (i + 2) + "))");
			}
			i=i+1;
		}
	}

	private void setupSheet(HSSFSheet sheet) {
		sheet.createFreezePane(1, 1);

		HSSFRow rowhead = sheet.createRow((int) 0);
		rowhead.createCell((int) 0).setCellValue("Weapon Name");
		sheet.setColumnWidth(0, 30 * 260);
		rowhead.createCell((int) 1).setCellValue("magSize");
		sheet.setColumnWidth(1, "magSize".length() * 260);
		rowhead.createCell((int) 2).setCellValue("reloadTime");
		sheet.setColumnWidth(2, "reloadTime".length() * 260);
		rowhead.createCell((int) 3).setCellValue("recoilForceUp");
		sheet.setColumnWidth(3, "recoilForceUp".length() * 260);
		rowhead.createCell((int) 4).setCellValue("Velocity");
		sheet.setColumnWidth(4, "Velocity".length() * 260);
		rowhead.createCell((int) 5).setCellValue("Rounds Per Minute");
		sheet.setColumnWidth(5, "Rounds Per Minute".length() * 260);
		rowhead.createCell((int) 6).setCellValue("Ammo Name");
		sheet.setColumnWidth(6, "Ammo Name".length() * 260);
		rowhead.createCell((int) 7).setCellValue("Ammo Max Damage");
		sheet.setColumnWidth(7, "Ammo Max Damage".length() * 260);
		rowhead.createCell((int) 8).setCellValue("Ammo Min Damage");
		sheet.setColumnWidth(8, "Ammo Min Damage".length() * 260);
		rowhead.createCell((int) 9).setCellValue("Ammo Max Dist");
		sheet.setColumnWidth(9, "Ammo Max Dist".length() * 260);
		rowhead.createCell((int) 10).setCellValue("Ammo Min Dist");
		sheet.setColumnWidth(10, "Ammo Min Dist".length() * 260);
		rowhead.createCell((int) 11).setCellValue("Ammo GravityModifier");
		sheet.setColumnWidth(11, "Ammo GravityModifier".length() * 260);
		rowhead.createCell((int) 12).setCellValue("Deviation devModStand");
		sheet.setColumnWidth(12, "Deviation devModStand".length() * 260);
		rowhead.createCell((int) 13).setCellValue("Deviation devModCrouch");
		sheet.setColumnWidth(13, "Deviation devModCrouch".length() * 260);
		rowhead.createCell((int) 14).setCellValue("Deviation devModLie");
		sheet.setColumnWidth(14, "Deviation devModLie".length() * 260);
		rowhead.createCell((int) 15).setCellValue("Deviation devModZoom");
		sheet.setColumnWidth(15, "Deviation devModZoom".length() * 260);
		rowhead.createCell((int) 16).setCellValue("Deviation minDev");
		sheet.setColumnWidth(16, "Deviation minDev".length() * 260);
		rowhead.createCell((int) 17).setCellValue("Deviation setFireDevMax");
		sheet.setColumnWidth(17, "Deviation setFireDevMax".length() * 260);
		rowhead.createCell((int) 18).setCellValue("Deviation setFireDevAdd");
		sheet.setColumnWidth(18, "Deviation setFireDevAdd".length() * 260);
		rowhead.createCell((int) 19).setCellValue("Deviation setFireDevCool");
		sheet.setColumnWidth(19, "Deviation setFireDevCool".length() * 260);
		rowhead.createCell((int) 20).setCellValue("Deviation setTurnDevMax");
		sheet.setColumnWidth(20, "Deviation setTurnDevMax".length() * 260);
		rowhead.createCell((int) 21).setCellValue("Deviation setTurnDevLeft");
		sheet.setColumnWidth(21, "Deviation setTurnDevLeft".length() * 260);
		rowhead.createCell((int) 22).setCellValue("Deviation setTurnDevRight");
		sheet.setColumnWidth(22, "Deviation setTurnDevRight".length() * 260);
		rowhead.createCell((int) 23).setCellValue("Deviation setTurnDevCool");
		sheet.setColumnWidth(23, "Deviation setTurnDevCool".length() * 260);
		rowhead.createCell((int) 24).setCellValue("Deviation setSpeedDevMax");
		sheet.setColumnWidth(24, "Deviation setSpeedDevMax".length() * 260);
		rowhead.createCell((int) 25).setCellValue("Deviation setSpeedDevMove");
		sheet.setColumnWidth(25, "Deviation setSpeedDevMove".length() * 260);
		rowhead.createCell((int) 26).setCellValue("Deviation setSpeedDevStrafe");
		sheet.setColumnWidth(26, "Deviation setSpeedDevStrafe".length() * 260);
		rowhead.createCell((int) 27).setCellValue("Deviation setSpeedDevCool");
		sheet.setColumnWidth(27, "Deviation setSpeedDevCool".length() * 260);
		rowhead.createCell((int) 28).setCellValue("Deviation setMiscDevMax");
		sheet.setColumnWidth(28, "Deviation setMiscDevMax".length() * 260);
		rowhead.createCell((int) 29).setCellValue("Deviation setMiscDevAdd");
		sheet.setColumnWidth(29, "Deviation setMiscDevAdd".length() * 260);
		rowhead.createCell((int) 30).setCellValue("Deviation setMiscDevCool");
		sheet.setColumnWidth(30, "Deviation setMiscDevCool".length() * 260);

		rowhead.createCell((int) 31).setCellValue("Single Shot Settle Time");
		sheet.setColumnWidth(31, "Single Shot Settle Time".length() * 260);
		rowhead.createCell((int) 32).setCellValue("Maximum Movement Settle Time");
		sheet.setColumnWidth(32, "Maximum Movement Settle Time".length() * 260);
		rowhead.createCell((int) 33).setCellValue("Prone Settle Time");
		sheet.setColumnWidth(33, "Prone Settle Time".length() * 260);
	}

}
