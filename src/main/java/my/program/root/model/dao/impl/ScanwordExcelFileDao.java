package my.program.root.model.dao.impl;

import java.io.*;
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
		else if (conn instanceof File) {
			path = ((File)conn).getAbsolutePath(); 
		}
	}

	@Override
	public void create(Scanword scanword) {
		
	}

	private Dimention getDimention(XSSFSheet sheet) {
		Dimention dim = new Dimention();
		XSSFRow row = null;
		XSSFCell cell = null;
		int[] index = {0,0,0,0};
		for(int rIndex = 0; rIndex < 50; rIndex++) {
			row = sheet.getRow(rIndex);
			if(row != null) {
				if ((row.getLastCellNum() - row.getFirstCellNum()) > 1) {
					for(int cIndex = 0; cIndex < 50; cIndex++) {
						cell = row.getCell(cIndex);
						if(cell != null){
							if(cell.getCellStyle().getBorderLeft() == 2) {
								index[0] = cIndex; //left index
							}
							if (cell.getCellStyle().getBorderTop() == 2) {
								index[2] = rIndex; // top index
							}
							if(cell.getCellStyle().getBorderRight() == 2) {
								index[1] = cIndex; // right index
							}
							if (cell.getCellStyle().getBorderBottom() == 2) {
								index[3] = rIndex; //bottom index
							}
						}
					}
				}
			}
		}
		dim.setXStartPosition(index[0]);
		dim.setYSrartPosition(index[2]);
		dim.setXDimention(index[1] - index[0] + 1);
		dim.setYDimention(index[3] - index[2] + 1);

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
			mCells = new Cell[dim.getYDimention()][dim.getXDimention()];
			Cell mCell = null;
			if(dim.getXDimention() == 15 && dim.getYDimention() ==30) {
				DateFormat format = new SimpleDateFormat("dd/mm/yyy HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				Date date = cal.getTime();
				String time = format.format(date);
				scanword = new ShortMozaicScanword(sheetName, time);
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
			
			for(int rowIndex = dim.getYStartPosition(); rowIndex < dim.getYDimention() + dim.getYStartPosition(); rowIndex++) {
				XSSFRow row = sheet.getRow(rowIndex);
				for(int cellIndex = dim.getXStartPosition(); cellIndex < dim.getXDimention() + dim.getXStartPosition();cellIndex++) {
					mCell = new Cell();
					XSSFCell cell = row.getCell(cellIndex);
					if(cell != null) {
						XSSFColor color = cell.getCellStyle().getFillForegroundXSSFColor();
						if(color!=null) {
							byte [] colorByte = color.getRgb();
							if(this.equal(colorByte, whiteColorByte)) {
								mCell.setEditable(true);
								if(cell.getStringCellValue().equals("")) {
									mCell.setText("");
								}
								else {
									mCell.setText(cell.getStringCellValue());
								}
							}
							if(this.equal(colorByte, blackColorByte)) {
								mCell.setComment(true);
								mCell.setEditable(false);
								mCell.setText(" ");
							}
							if(!this.equal(colorByte, whiteColorByte) && !this.equal(colorByte, blackColorByte)) {
								mCell.setComment(false);
								mCell.setEditable(false);
								mCell.setText(" ");
							}
						}
					}
					mCells[rowIndex-dim.getYStartPosition()][cellIndex-dim.getXStartPosition()] = mCell;
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