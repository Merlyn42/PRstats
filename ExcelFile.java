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
	private HSSFWorkbook	workbook;

	public ExcelFile() {
		workbook = new HSSFWorkbook();
	}

	public void createWeaponSheet(String sheetName, List<Weapon> weapons) {
		HSSFSheet sheet = workbook.createSheet(sheetName);
		setupSheet(sheet);
		populateSheet(sheet, weapons);
	}

	public void writeWorkbook(File outputLocation) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(outputLocation);
		workbook.setForceFormulaRecalculation(true);
		try {
			workbook.write(fileOut);
		} finally {
			try {
				fileOut.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void populateSheet(HSSFSheet sheet, List<Weapon> weapons) {
		int i = 0;
		Comparator<Weapon> comparator = new Comparator<Weapon>() {
			public int compare(Weapon c1, Weapon c2) {
				return c1.name.compareTo(c2.name);
			}
		};
		Collections.sort(weapons, comparator);
		for (Weapon w : weapons) {
			HSSFRow row = sheet.createRow((int) i + 1);
			int cellNumber = 0;

			// System.out.println("Writing data for: " + w.name);
			if (w.name != null){
				row.createCell(cellNumber++).setCellValue(w.name);
			}else{
				cellNumber++;
			}
			if (w.magSize != null){
				row.createCell(cellNumber++).setCellValue(w.magSize);
			}else{
				cellNumber++;
			}
			if (w.reloadTime != null){
				row.createCell(cellNumber++).setCellValue(w.reloadTime);
			}else{
				cellNumber++;
			}
			if (w.recoilForceUp != null){
				row.createCell(cellNumber++).setCellValue(w.recoilForceUp);
			}else{
				cellNumber++;
			}
			if (w.recoilForceLeftRight != null){
				row.createCell(cellNumber++).setCellValue(w.recoilForceLeftRight);
			}else{
				cellNumber++;
			}
			row.createCell(cellNumber++).setCellValue(w.velocity);
			if (w.roundsPerMinute != null){
				row.createCell(cellNumber++).setCellValue(w.roundsPerMinute == null ? "null" : w.roundsPerMinute.toString());
			}else{
				cellNumber++;
			}
			if (w.ammo != null) {
				row.createCell(cellNumber++).setCellValue(w.ammo.name);
				row.createCell(cellNumber++).setCellValue(w.ammo.maxDamage);
				row.createCell(cellNumber++).setCellValue(w.ammo.minDamage);
				row.createCell(cellNumber++).setCellValue(w.ammo.maxDist);
				row.createCell(cellNumber++).setCellValue(w.ammo.minDist);
				row.createCell(cellNumber++).setCellValue(w.ammo.gravityModifier);
			}else{
				cellNumber+=6;
			}
			if (w.dev != null) {
				row.createCell(cellNumber++).setCellValue(w.dev.devModStand);
				row.createCell(cellNumber++).setCellValue(w.dev.devModCrouch);
				row.createCell(cellNumber++).setCellValue(w.dev.devModLie);
				row.createCell(cellNumber++).setCellValue(w.dev.devModZoom);
				row.createCell(cellNumber++).setCellValue(w.dev.minDev);
				row.createCell(cellNumber++).setCellValue(w.dev.setFireDevMax);
				row.createCell(cellNumber++).setCellValue(w.dev.setFireDevAdd);
				row.createCell(cellNumber++).setCellValue(w.dev.setFireDevCool);
				row.createCell(cellNumber++).setCellValue(w.dev.setTurnDevMax);
				row.createCell(cellNumber++).setCellValue(w.dev.setTurnDevLeft);
				row.createCell(cellNumber++).setCellValue(w.dev.setTurnDevRight);
				row.createCell(cellNumber++).setCellValue(w.dev.setTurnDevCool);
				row.createCell(cellNumber++).setCellValue(w.dev.setSpeedDevMax);
				row.createCell(cellNumber++).setCellValue(w.dev.setSpeedDevMove);
				row.createCell(cellNumber++).setCellValue(w.dev.setSpeedDevStrafe);
				row.createCell(cellNumber++).setCellValue(w.dev.setSpeedDevCool);
				row.createCell(cellNumber++).setCellValue(w.dev.setMiscDevMax);
				row.createCell(cellNumber++).setCellValue(w.dev.setMiscDevAdd);
				row.createCell(cellNumber++).setCellValue(w.dev.setMiscDevCool);
				row.createCell(cellNumber++).setCellFormula("(S" + (i + 2) + "/(30*T" + (i + 2) + "))");
				row.createCell(cellNumber++).setCellFormula("(Y" + (i + 2) + "/(30*AB" + (i + 2) + "))");
				row.createCell(cellNumber++).setCellFormula("(AD" + (i + 2) + "/(30*AE" + (i + 2) + "))");
			}else{
				cellNumber+=22;
			}
			i = i + 1;
		}
	}

	private void setupSheet(HSSFSheet sheet) {
		sheet.createFreezePane(1, 1);
		int cellNumber = 0;
		HSSFRow rowhead = sheet.createRow(0);
		rowhead.createCell(cellNumber).setCellValue("Weapon Name");
		sheet.setColumnWidth(cellNumber++, 30 * 280);
		rowhead.createCell(cellNumber).setCellValue("magSize");
		sheet.setColumnWidth(cellNumber++, "magSize".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("reloadTime");
		sheet.setColumnWidth(cellNumber++, "reloadTime".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("recoilForceUp");
		sheet.setColumnWidth(cellNumber++, "recoilForceUp".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("recoilForceLeftRight");
		sheet.setColumnWidth(cellNumber++, "recoilForceLeftRight".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("Velocity");
		sheet.setColumnWidth(cellNumber++, "Velocity".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("Rounds Per Minute");
		sheet.setColumnWidth(cellNumber++, "Rounds Per Minute".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("Ammo Name");
		sheet.setColumnWidth(cellNumber++, "Ammo Name".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("Ammo Max Damage");
		sheet.setColumnWidth(cellNumber++, "Ammo Max Damage".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("Ammo Min Damage");
		sheet.setColumnWidth(cellNumber++, "Ammo Min Damage".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("Ammo Max Dist");
		sheet.setColumnWidth(cellNumber++, "Ammo Max Dist".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("Ammo Min Dist");
		sheet.setColumnWidth(cellNumber++, "Ammo Min Dist".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("Ammo GravityModifier");
		sheet.setColumnWidth(cellNumber++, "Ammo GravityModifier".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("Deviation devModStand");
		sheet.setColumnWidth(cellNumber++, "Deviation devModStand".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("Deviation devModCrouch");
		sheet.setColumnWidth(cellNumber++, "Deviation devModCrouch".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("Deviation devModLie");
		sheet.setColumnWidth(cellNumber++, "Deviation devModLie".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("Deviation devModZoom");
		sheet.setColumnWidth(cellNumber++, "Deviation devModZoom".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("Deviation minDev");
		sheet.setColumnWidth(cellNumber++, "Deviation minDev".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("Deviation setFireDevMax");
		sheet.setColumnWidth(cellNumber++, "Deviation setFireDevMax".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("Deviation setFireDevAdd");
		sheet.setColumnWidth(cellNumber++, "Deviation setFireDevAdd".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("Deviation setFireDevCool");
		sheet.setColumnWidth(cellNumber++, "Deviation setFireDevCool".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("Deviation setTurnDevMax");
		sheet.setColumnWidth(cellNumber++, "Deviation setTurnDevMax".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("Deviation setTurnDevLeft");
		sheet.setColumnWidth(cellNumber++, "Deviation setTurnDevLeft".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("Deviation setTurnDevRight");
		sheet.setColumnWidth(cellNumber++, "Deviation setTurnDevRight".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("Deviation setTurnDevCool");
		sheet.setColumnWidth(cellNumber++, "Deviation setTurnDevCool".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("Deviation setSpeedDevMax");
		sheet.setColumnWidth(cellNumber++, "Deviation setSpeedDevMax".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("Deviation setSpeedDevMove");
		sheet.setColumnWidth(cellNumber++, "Deviation setSpeedDevMove".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("Deviation setSpeedDevStrafe");
		sheet.setColumnWidth(cellNumber++, "Deviation setSpeedDevStrafe".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("Deviation setSpeedDevCool");
		sheet.setColumnWidth(cellNumber++, "Deviation setSpeedDevCool".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("Deviation setMiscDevMax");
		sheet.setColumnWidth(cellNumber++, "Deviation setMiscDevMax".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("Deviation setMiscDevAdd");
		sheet.setColumnWidth(cellNumber++, "Deviation setMiscDevAdd".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("Deviation setMiscDevCool");
		sheet.setColumnWidth(cellNumber++, "Deviation setMiscDevCool".length() * 280);

		rowhead.createCell(cellNumber).setCellValue("Single Shot Settle Time");
		sheet.setColumnWidth(cellNumber++, "Single Shot Settle Time".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("Maximum Movement Settle Time");
		sheet.setColumnWidth(cellNumber++, "Maximum Movement Settle Time".length() * 280);
		rowhead.createCell(cellNumber).setCellValue("Prone Settle Time");
		sheet.setColumnWidth(cellNumber++, "Prone Settle Time".length() * 280);
	}

}
