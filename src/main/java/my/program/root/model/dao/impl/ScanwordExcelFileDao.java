package my.program.root.model.dao.impl;

import java.io.*;
import java.sql.Connection;
import java.text.*;
import java.util.*;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import my.program.root.model.dao.api.ScanwordDao;
import my.program.root.model.scanword.*;

public class ScanwordExcelFileDao implements ScanwordDao{
	
	private class Dimention {
		private int xDimention;
		private int yDimention;
		private int xStartPosition;
		private int yStartPosition;
		
		public Dimention() {
		}
		public int getXDimention() {
			return xDimention;
		}
		public void setXDimention(int value) {
			this.xDimention = value;
		}
		public int getYDimention() {
			return yDimention;
		}
		
		public void setYDimention(int value) {
			this.yDimention = value;
		}
		
		public int getXStartPosition() {
			return xStartPosition;
		}
		
		public void setXStartPosition(int value) {
			this.xStartPosition = value;
		}
		
		public int getYStartPosition() {
			return yStartPosition;
		}
		
		public void setYSrartPosition(int value) {
			this.yStartPosition = value;
		}
	}
	
	private String path;
	
	@Override
	public void getConnetction(Object conn) {
		if(conn instanceof String) {
			path = (String)conn;
		}
	}

	@Override
	public void create(Scanword scanword) {
		
	}

	private Dimention getDimention(XSSFSheet sheet) {
		int rLength = 0;
		int cLength = 0;
		Dimention dim = new Dimention();
		boolean lable = false;
		
		XSSFRow row = null;
		XSSFCell cell = null;
		for(int rIndex = 0; rIndex < 10; rIndex++) {
			row = sheet.getRow(rIndex);
			for(int cIndex = 0; cIndex < 10; cIndex++) {
				cell = row.getCell(cIndex);
				if(cell != null) {
						dim.setXStartPosition(rIndex);
						dim.setYSrartPosition(cIndex);
						lable = true;
						break;
				}
			}
			if(lable) {
				break;
			}
		}
		row = null;
		cell = null;
		
		row = sheet.getRow(dim.getYStartPosition());
		for(int cIndex = dim.getXStartPosition(); cIndex < 50; cIndex++) {
			cell = row.getCell(cIndex);
			if(cell != null) {
				rLength++;
			} else {
				break;
			}
		}
		row = null;
		cell = null;
		
		for(int rIndex = dim.getYStartPosition(); rIndex < 50; rIndex++) {
			row = sheet.getRow(rIndex);
			cell = row.getCell(dim.getXStartPosition());
			if(cell != null) {
				cLength++;
			} else {
				break;
			}
		}
		dim.setXDimention(rLength);
		dim.setYDimention(cLength);
		return dim;
	}
		
	private boolean equal(byte[] first, byte[] second) {
		boolean flag = true;
		for(int i = 0; i < 3; i++) {
			if(first[i] != second[i]) {
				flag = false;
			}
		}
		return flag;
	}
	
	@Override
	public List<Scanword> readAll() {
		List<Scanword> list = null;
		Scanword scanword = null;
		InputStream inputFile = null;
		Cell[][] mCells = null;
		
		try {
			inputFile = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(inputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int numberOfSheets = workbook.getNumberOfSheets();
		
		list = new ArrayList<Scanword> (numberOfSheets);
		for(int index = 0; index < numberOfSheets; index++) {

			XSSFSheet sheet = workbook.getSheetAt(index);
			String sheetName = sheet.getSheetName();
			
			Dimention dim = getDimention(sheet);
			mCells = new Cell[dim.getXDimention()][dim.getYDimention()];
			if(dim.getXDimention() == 15 && dim.getYDimention() ==30) {
				DateFormat format = new SimpleDateFormat("dd/mm/yyy HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				scanword = new ShortMozaicScanword(sheetName, format.format(cal.getTime()));
			}
			
			XSSFColor whiteColor = null, blackColor = null, greenColor = null;
			byte [] whiteColorByte = null, blackColorByte = null;
			whiteColor = new XSSFColor();
			blackColor = new XSSFColor();
			greenColor = new XSSFColor();
			whiteColor.setIndexed(IndexedColors.WHITE.getIndex());
			blackColor.setIndexed(IndexedColors.BLACK.getIndex());
			greenColor.setIndexed(IndexedColors.RED.getIndex());
			whiteColorByte = whiteColor.getRgb();
			blackColorByte = blackColor.getRgb();
			
			for(int rowIndex = dim.getYStartPosition(); rowIndex < dim.getYDimention(); rowIndex++) {
				XSSFRow row = sheet.getRow(rowIndex);
				for(int cellIndex = dim.getXStartPosition(); cellIndex < dim.getXDimention();cellIndex++) {
					XSSFCell cell = row.getCell(cellIndex);
					if(cell != null) {
						XSSFColor color = cell.getCellStyle().getFillForegroundXSSFColor();
						if(color!=null) {
							byte [] colorByte = color.getRgb();
							if(this.equal(colorByte, whiteColorByte)) {
								mCells[cellIndex][rowIndex].setEditable(true);
								if(cell.getStringCellValue().equals("")) {
									mCells[cellIndex][rowIndex].setText("");
								}
								else {
									mCells[cellIndex][rowIndex].setText(cell.getStringCellValue());
								}
							}
							if(this.equal(colorByte, blackColorByte)) {
								mCells[cellIndex][rowIndex].setComment(true);
								mCells[cellIndex][rowIndex].setEditable(false);
							}
							if(!this.equal(colorByte, whiteColorByte) && !this.equal(colorByte, blackColorByte)) {
								mCells[cellIndex][rowIndex].setComment(false);
								mCells[cellIndex][rowIndex].setEditable(false);
							}
						}
					}
				}
			}
			scanword.setArray(mCells);
			list.add(scanword);
		}
		
		return list;
	}

	@Override
	public Scanword readById(int index) {
		return null;
	}

	@Override
	public void update(Scanword scanword) {
	}

	@Override
	public void delete(Scanword scanword) {
	}
}