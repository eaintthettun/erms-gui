package utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import model.Course;
import model.Father;
import model.Marks;
import model.Mother;
import model.Student;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.poi.xwpf.usermodel.XWPFTable.XWPFBorderType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHeight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

import database.ChangeOfSubDao;
import database.CourseDao;
import database.Dao;
import database.FatherDao;
import database.MajorDao;
import database.MotherDao;
import database.StudentDao;

public class OfficeUsage {
	
	public static String[] header = {
		"No.",
		"Entrance ID",
		"Name",
		"Name (MM)",
		"Gender",
		"Ethnic",
		"Religion",
		"NRC No.",
		"Date of Birth",
		"Matriculation Roll No.",
		"Matriculated Year",
		"Matriculation Department",
		"Major",
		"Father's NRC No.",
		"Mother's NRC No.",
		"Birth Place",
		"Permanent Address",
		"Temporary Address",
		"Phone No.",
		"E-mail",
		"Father Name",
		"Father Name (MM)",
		"Father Ethnic",
		"Father Religion",
		"Father Birth Place",
		"Father Job/Address",
		"Mother Name",
		"Mother Name (MM)",
		"Mother Ethnic",
		"Mother Religion",
		"Mother Birth Place",
		"Mother Job/Address",
		"Remark",
	};
	
	public static boolean exportStudentToExcel(Student std,File f){
		
		String excelFileName = f.getAbsolutePath();
		if(!excelFileName.endsWith(".xlsx")){
			excelFileName+=".xlsx";
			f = new File(excelFileName);
		}
		
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sh = wb.createSheet("Student Information");
		
		Row rowHeader = sh.createRow(0);
		int colNo=0;
		for(String h:header){
			Cell c = rowHeader.createCell(colNo++);
			c.setCellValue(h);
		}
		
		Row row = sh.createRow(1);
		colNo = 0;
		Cell c = row.createCell(colNo++);
		c.setCellValue(1);
		
		ArrayList<String> stdInfos = Usage.separateSubCodes(std.toString());
		ArrayList<String> fatherInfos = Usage.separateSubCodes(FatherDao.getFatherByNrc(std.getFather_nrc()).toString());
		ArrayList<String> motherInfos = Usage.separateSubCodes(MotherDao.getMotherByNrc(std.getMother_nrc()).toString());
		
		fatherInfos.remove(5);
		motherInfos.remove(5);
		
		for(String s:stdInfos)
			row.createCell(colNo++).setCellValue(s);
		for(String fath:fatherInfos)
			row.createCell(colNo++).setCellValue(fath);
		for(String moth:motherInfos)
			row.createCell(colNo++).setCellValue(moth);
		
		try{
			//if(!f.exists())f.createNewFile();
			FileOutputStream outputStream = new FileOutputStream(f);
			wb.write(outputStream);
			wb.close();
			return true;
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static boolean exportAllStudentsToExcel(ArrayList<Student> stds,File f){
		
		String excelFileName = f.getAbsolutePath();
		if(!excelFileName.endsWith(".xlsx")){
			excelFileName+=".xlsx";
			f = new File(excelFileName);
		}
		
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sh = wb.createSheet("All Students Information");
		
		int rowNo=0;
		Row rowHeader = sh.createRow(rowNo++);
		int colNo=0;
		for(String h:header){
			Cell c = rowHeader.createCell(colNo++);
			c.setCellValue(h);
		}
		
		for(Student std:stds){
		Row row = sh.createRow(rowNo);
		colNo = 0;
		Cell c = row.createCell(colNo++);
		c.setCellValue(rowNo);
		
		ArrayList<String> stdInfos = Usage.separateSubCodes(std.toString());
		ArrayList<String> fatherInfos = Usage.separateSubCodes(FatherDao.getFatherByNrc(std.getFather_nrc()).toString());
		ArrayList<String> motherInfos = Usage.separateSubCodes(MotherDao.getMotherByNrc(std.getMother_nrc()).toString());
		
		fatherInfos.remove(5);
		motherInfos.remove(5);
		
		for(String s:stdInfos)
			row.createCell(colNo++).setCellValue(s);
		for(String fath:fatherInfos)
			row.createCell(colNo++).setCellValue(fath);
		for(String moth:motherInfos)
			row.createCell(colNo++).setCellValue(moth);
		
		rowNo++;
		}
		try{
			//if(!f.exists())f.createNewFile();
			FileOutputStream outputStream = new FileOutputStream(f);
			wb.write(outputStream);
			wb.close();
			return true;
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static boolean exportMarksToExcel(ArrayList<Marks> mks, File f){
		String excelFileName = f.getAbsolutePath();
		if(!excelFileName.endsWith(".xlsx")){
			excelFileName+=".xlsx";
			f = new File(excelFileName);
		}
		Marks idealMark = mks.get(0);
		XSSFWorkbook book = new XSSFWorkbook();
		XSSFSheet sh = book.createSheet(idealMark.getAcademic_year()+" Academic Year Result");
		
		ArrayList<String> subList = Usage.separateSubCodes(idealMark.getSubjects());
		
		int rowCount = 0;
		int colCount = 0;
		Row headerRow = sh.createRow(rowCount++);
		headerRow.createCell(colCount++).setCellValue("No.");
		headerRow.createCell(colCount++).setCellValue("Academic Year");
		headerRow.createCell(colCount++).setCellValue("Semester");
		headerRow.createCell(colCount++).setCellValue("Entrance ID");
		headerRow.createCell(colCount++).setCellValue("Roll No.");
		headerRow.createCell(colCount++).setCellValue("Student Name");
		for(String sub:subList){
			headerRow.createCell(colCount++).setCellValue(sub+" ("+ChangeOfSubDao.getSubjectName(sub, idealMark.getAcademic_year())+")");
		}
		headerRow.createCell(colCount++).setCellValue("Total Mark");
		headerRow.createCell(colCount++).setCellValue("Remark");
		
		for(Marks mk:mks){
			colCount = 0;
			Row row = sh.createRow(rowCount++);
			ArrayList<String> markList = Usage.separateSubCodes(mk.getMarks());
			row.createCell(colCount++).setCellValue(rowCount-1);
			row.createCell(colCount++).setCellValue(mk.getAcademic_year());
			row.createCell(colCount++).setCellValue(mk.getSemester().equals("1")?"First":"Second");
			row.createCell(colCount++).setCellValue(mk.getStd_id());
			row.createCell(colCount++).setCellValue(mk.getRollno());
			row.createCell(colCount++).setCellValue(StudentDao.getStudentByID(mk.getStd_id()).getStd_name());
			int totalMark = 0;
			for(String mark:markList){
				if(mark.equals("xx"))
					row.createCell(colCount++).setCellValue(" - ");
				else{
					row.createCell(colCount++).setCellValue(mark);
					totalMark+=Integer.parseInt(mark);
				}
			}
			row.createCell(colCount++).setCellValue(totalMark);
			row.createCell(colCount++).setCellValue(Usage.getResultType(subList, markList, mk.getYear()));
		}
		
		try{
			FileOutputStream fos = new FileOutputStream(f);
			book.write(fos);
			fos.close();
			book.close();
			return true;
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static boolean reportMarkToWord(Marks mk, File f){
		try{
			String wordFileName = f.getAbsolutePath();
			if(!wordFileName.endsWith(".docx")){
				wordFileName+=".docx";
				f = new File(wordFileName);
			}
			XWPFDocument doc = new XWPFDocument();
			
			XWPFTable tableTitle = doc.createTable(1,2);
			tableTitle.setInsideHBorder(XWPFBorderType.NONE, 0, 0, "");
			tableTitle.setInsideVBorder(XWPFBorderType.NONE, 0, 0, "");
			CTTblPr tblPrTitle = tableTitle.getCTTbl().getTblPr();
            CTTblBorders bordersTitle = tblPrTitle.addNewTblBorders();
            bordersTitle.addNewTop().setSz(BigInteger.ZERO);
            bordersTitle.addNewRight().setSz(BigInteger.ZERO);
            bordersTitle.addNewBottom().setSz(BigInteger.ZERO);
            bordersTitle.addNewLeft().setSz(BigInteger.ZERO);
			
			XWPFTableRow headerTitle = tableTitle.getRow(0);
			CTTrPr headerPrTitle = headerTitle.getCtRow().addNewTrPr();
			CTHeight headerHtTitle = headerPrTitle.addNewTrHeight();
			headerHtTitle.setVal(BigInteger.valueOf(1000));
			
			XWPFTableCell cellLogo = headerTitle.getCell(0);
			CTTcPr subPrLogo = cellLogo.getCTTc().addNewTcPr();
			subPrLogo.addNewTcW().setW(BigInteger.valueOf(1200));
			CTVerticalJc vjcLogo = subPrLogo.addNewVAlign();
			vjcLogo.setVal(STVerticalJc.CENTER);
			
			XWPFParagraph paraLogo = cellLogo.getParagraphs().get(0);
			paraLogo.setAlignment(ParagraphAlignment.RIGHT);
			paraLogo.setVerticalAlignment(TextAlignment.CENTER);
			
			XWPFRun runLogo = paraLogo.createRun();
			runLogo.addTab();
			runLogo.addPicture(new ByteArrayInputStream(Dao.getPicture("ytuLogo")), XWPFDocument.PICTURE_TYPE_PNG, 
					"",Units.pixelToEMU(86), Units.pixelToEMU(115));
			
			XWPFTableCell cellTitle = headerTitle.getCell(1);
			CTTcPr subPrTitle = cellTitle.getCTTc().addNewTcPr();
			subPrTitle.addNewTcW().setW(BigInteger.valueOf(8900));
			CTVerticalJc vjcTitle = subPrTitle.addNewVAlign();
			vjcTitle.setVal(STVerticalJc.CENTER);
			
			XWPFParagraph paraTitle = cellTitle.getParagraphs().get(0);
			paraTitle.setAlignment(ParagraphAlignment.CENTER);
			paraTitle.setVerticalAlignment(TextAlignment.TOP);
			XWPFRun runTitle = paraTitle.createRun();
			runTitle.setBold(true);
			runTitle.setFontSize(18);
			runTitle.setFontFamily("Arial");
			runTitle.setColor("800080");
			runTitle.setText("Yangon Technological University");
			runTitle.addBreak();
			runTitle.setText("Marks Certificate");
			
			XWPFParagraph paraText = doc.createParagraph();
			paraText.setSpacingBetween(1.7);
			
			XWPFRun txt1 = paraText.createRun();
			txt1.addBreak();
			txt1.addTab();
			writeNormal(txt1, "This is to certify that    ");
			
			XWPFRun varName = paraText.createRun();
			writeVariable(varName, StudentDao.getStudentByID(mk.getStd_id()).getStd_name());
			
			XWPFRun txt2 = paraText.createRun();
			writeNormal(txt2, "    under Roll No    ");
			
			XWPFRun varRollNo = paraText.createRun();
			writeVariable(varRollNo, mk.getRollno());
			
			XWPFRun txt3 = paraText.createRun();
			writeNormal(txt3, "    has obtained the following marks in the    ");
			
			XWPFRun varSem = paraText.createRun();
			writeVariable(varSem, mk.getSemester().equals("1") ? "First":"Second");
			
			XWPFRun txt4 = paraText.createRun();
			writeNormal(txt4, "    Semester Examination of the");
			
			if(mk.getYear().compareTo("6")<=0){
				String year="";
				switch(mk.getYear()){
				case "1":year="First";break;
				case "2":year="Second";break;
				case "3":year="Third";break;
				case "4":year="Fourth";break;
				case "5":year="Fifth";break;
				case "6":year="Sixth";break;
				}
				year="    "+year;
				
				XWPFRun varYear = paraText.createRun();
				writeVariable(varYear, year);
				
				XWPFRun txt5 = paraText.createRun();
				writeNormal(txt5, "    Year of the Bachelor of Engineering    ");
				
				XWPFRun varMajor = paraText.createRun();
				writeVariable(varMajor, MajorDao.getMajorCodeAndName().get(mk.getMajor_code()));
				
				XWPFRun txt6 = paraText.createRun();
				writeNormal(txt6, "    Course held in ");
				
			}else if(mk.getYear().equals("7")){
				XWPFRun txt7 = paraText.createRun();
				writeNormal(txt7, " Postgraduate Diploma Course (    ");
				
				XWPFRun varMajor = paraText.createRun();
				writeVariable(varMajor, MajorDao.getMajorCodeAndName().get(mk.getMajor_code()));
				
				XWPFRun txt6 = paraText.createRun();
				writeNormal(txt6, "    ) held in ");
				
			}else if(mk.getYear().equals("8")||mk.getYear().equals("9")){
				XWPFRun txt8 = paraText.createRun();
				writeNormal(txt8, " Master's Course (    ");
				
				XWPFRun varMajor = paraText.createRun();
				writeVariable(varMajor, MajorDao.getMajorCodeAndName().get(mk.getMajor_code()));
			
				XWPFRun txt6 = paraText.createRun();
				writeNormal(txt6, "    ) held in ");
				
			}else if(mk.getYear().equals("10")){
				XWPFRun txt9 = paraText.createRun();
				writeNormal(txt9, " Doctorate Course (    ");
				
				XWPFRun varMajor = paraText.createRun();
				writeVariable(varMajor, MajorDao.getMajorCodeAndName().get(mk.getMajor_code()));
				
				XWPFRun txt6 = paraText.createRun();
				writeNormal(txt6, "    ) held in ");
				
			}
			
			XWPFRun txt10 = paraText.createRun();
			writeNormal(txt10, ".........................................................");
			
			/*******************************
			 * Start of Subjects and marks *
			 *******************************/
			
			ArrayList<String> subList = Usage.separateSubCodes(mk.getSubjects());
			ArrayList<String> markList = Usage.separateSubCodes(mk.getMarks());
			
			for(int i=0;i<markList.size();i++){
				if(markList.get(i).equals("xx")){
					markList.remove(i);
					subList.remove(i);
				}
			}
			
			int rowNum = subList.size()+4;
			int colNum = 4;
			
			XWPFTable table = doc.createTable(rowNum,colNum);
			table.setInsideHBorder(XWPFBorderType.NONE, 0, 0, "");
			table.setInsideVBorder(XWPFBorderType.NONE, 0, 0, "");
			CTTblPr tblPr = table.getCTTbl().getTblPr();
            CTTblBorders borders = tblPr.addNewTblBorders();
            borders.addNewTop().setSz(BigInteger.ZERO);
            borders.addNewRight().setSz(BigInteger.ZERO);
            borders.addNewBottom().setSz(BigInteger.ZERO);
            borders.addNewLeft().setSz(BigInteger.ZERO);
			
			XWPFTableRow header = table.getRow(0);
			CTTrPr headerPr = header.getCtRow().addNewTrPr();
			CTHeight headerHt = headerPr.addNewTrHeight();
			headerHt.setVal(BigInteger.valueOf(230));
			
			XWPFTableCell cellSub = header.getCell(1);
			CTTcPr subPr = cellSub.getCTTc().addNewTcPr();
			subPr.addNewTcW().setW(BigInteger.valueOf(5500));
			CTVerticalJc vjc = subPr.addNewVAlign();
			vjc.setVal(STVerticalJc.CENTER);
			XWPFParagraph subPara = cellSub.getParagraphs().get(0);
			subPara.setAlignment(ParagraphAlignment.LEFT);
			XWPFRun subRun = subPara.createRun();
			//subRun.setBold(true);
			subRun.setUnderline(UnderlinePatterns.SINGLE);
			subRun.setFontSize(11);
			subRun.setFontFamily("Comic Sans MS");
			subRun.setText("SUBJECTS");
			
			XWPFTableCell cellMks = header.getCell(2);
			CTTcPr mksPr = cellMks.getCTTc().addNewTcPr();
			CTVerticalJc vjcM = mksPr.addNewVAlign();
			vjcM.setVal(STVerticalJc.CENTER);
			XWPFParagraph mksPara = cellMks.getParagraphs().get(0);
			mksPara.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun mksRun = mksPara.createRun();
			//mksRun.setBold(true);
			mksRun.setUnderline(UnderlinePatterns.SINGLE);
			mksRun.setFontSize(11);
			mksRun.setFontFamily("Comic Sans MS");
			mksRun.setText("MARKS OBTAINED");
			
			int rowCt = 1;
			for(String sub:subList){
				XWPFTableRow row = table.getRow(rowCt);
				CTTrPr rowPr = row.getCtRow().addNewTrPr();
				CTHeight rowHt = rowPr.addNewTrHeight();
				rowHt.setVal(BigInteger.valueOf(210));
				
				List<XWPFTableCell> cells = row.getTableCells();
				int colCt = 0;
				for(XWPFTableCell cell:cells){
					
					CTTcPr cellPr = cell.getCTTc().addNewTcPr();
					if(colCt==1)
						cellPr.addNewTcW().setW(BigInteger.valueOf(5500));
					CTVerticalJc vjcC = cellPr.addNewVAlign();
					vjcC.setVal(STVerticalJc.CENTER);
					XWPFParagraph cellPara = cell.getParagraphs().get(0);
					
					if(colCt == 2){
						cellPara.setAlignment(ParagraphAlignment.CENTER);
					}else{
						cellPara.setAlignment(ParagraphAlignment.LEFT);
					}
					
					XWPFRun cellRun = cellPara.createRun();
					cellRun.setFontSize(14);
					cellRun.setFontFamily("Monotype Corsiva");
					
					if(colCt==0) cellRun.setText(rowCt+".");
					if(colCt==1) cellRun.setText(ChangeOfSubDao.getSubjectName(sub, mk.getAcademic_year()));
					
					int m = Integer.parseInt(markList.get(rowCt-1));
					if(colCt==2){
						String mkStr = null;
						if(m<=100){
							mkStr = m+" / "+100;
						}else{
							mkStr = m+" / "+1000;
						}
						cellRun.setText(mkStr);
					}
					if(colCt==3 && Integer.parseInt(mk.getYear())<=6 && Checker.isExcellent(sub, m)) 
						cellRun.setText("Excellent");
					
					colCt++;
				}
				rowCt++;
			}
			
			XWPFTableRow row = table.getRow(rowCt++);
			CTTrPr rowPr = row.getCtRow().addNewTrPr();
			CTHeight rowHt = rowPr.addNewTrHeight();
			rowHt.setVal(BigInteger.valueOf(288));
			
			XWPFTableCell cellTotLbl = row.getCell(1);
			CTTcPr lblPr = cellTotLbl.getCTTc().addNewTcPr();
			lblPr.addNewTcW().setW(BigInteger.valueOf(5500));
			CTVerticalJc vjcL = lblPr.addNewVAlign();
			vjcL.setVal(STVerticalJc.CENTER);
			XWPFParagraph lblPara = cellTotLbl.getParagraphs().get(0);
			lblPara.setAlignment(ParagraphAlignment.RIGHT);
			XWPFRun lblRun = lblPara.createRun();
			lblRun.setFontSize(11);
			lblRun.setFontFamily("Arial");
			lblRun.setText("Total........");
			
			XWPFTableCell cellTotVal = row.getCell(2);
			CTTcPr valPr = cellTotVal.getCTTc().addNewTcPr();
			CTVerticalJc vjcV = valPr.addNewVAlign();
			vjcV.setVal(STVerticalJc.CENTER);
			XWPFParagraph valPara = cellTotVal.getParagraphs().get(0);
			valPara.setAlignment(ParagraphAlignment.CENTER);
			valPara.setVerticalAlignment(TextAlignment.CENTER);
			XWPFRun valRun = valPara.createRun();
			valRun.setBold(true);
			valRun.setFontSize(14);
			//valRun.setColor("D3DFEE");
			valRun.setFontFamily("Monotype Corsiva");
			
			valRun.setText(Usage.getTotalAndMax(subList, markList));
			
			XWPFTableRow rowRs = table.getRow(rowCt++);
			CTTrPr rsPr = rowRs.getCtRow().addNewTrPr();
			CTHeight rsHt = rsPr.addNewTrHeight();
			rsHt.setVal(BigInteger.valueOf(288));
			
			XWPFTableCell cellRs = rowRs.getCell(1);
			CTTcPr resPr = cellRs.getCTTc().addNewTcPr();
			resPr.addNewTcW().setW(BigInteger.valueOf(5500));
			CTVerticalJc vjcR = resPr.addNewVAlign();
			vjcR.setVal(STVerticalJc.BOTTOM);
			XWPFParagraph resPara = cellRs.getParagraphs().get(0);
			resPara.setAlignment(ParagraphAlignment.LEFT);
			XWPFRun lblRsRun = resPara.createRun();
			writeNormal(lblRsRun, "Result :     ");
			
			XWPFRun resRun = resPara.createRun();
			writeVariable(resRun, Usage.getResultType(subList, markList, mk.getYear()));
			
			XWPFTableRow rowOfc = table.getRow(rowCt++);
			CTTrPr ofcPr = rowOfc.getCtRow().addNewTrPr();
			CTHeight ofcHt = ofcPr.addNewTrHeight();
			ofcHt.setVal(BigInteger.valueOf(288*5));
			
			XWPFTableCell cellSeal = rowOfc.getCell(1);
			CTTcPr sealPr = cellSeal.getCTTc().addNewTcPr();
			sealPr.addNewTcW().setW(BigInteger.valueOf(5500));
			CTVerticalJc vjcS = sealPr.addNewVAlign();
			vjcS.setVal(STVerticalJc.CENTER);
			XWPFParagraph sealPara = cellSeal.getParagraphs().get(0);
			sealPara.setAlignment(ParagraphAlignment.LEFT);
			XWPFRun sealRun = sealPara.createRun();
			sealRun.addBreak();sealRun.addBreak();
			sealRun.addBreak();sealRun.addBreak();
			writeNormal(sealRun, "       (SEAL)");
			
			XWPFTableCell cellStamp = rowOfc.getCell(2);
			CTTcPr stampPr = cellStamp.getCTTc().addNewTcPr();
			CTVerticalJc vjcT = stampPr.addNewVAlign();
			vjcT.setVal(STVerticalJc.CENTER);
			XWPFParagraph stampPara = cellStamp.getParagraphs().get(0);
			stampPara.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun stampRun = stampPara.createRun();
			stampRun.addBreak();stampRun.addBreak();
			stampRun.addBreak();stampRun.addBreak();
			stampRun.addBreak();
			stampRun.setFontSize(9);
			stampRun.setFontFamily("Arial");
			stampRun.setColor("525252");
			stampRun.setText("Registrar");
			stampRun.addBreak();
			stampRun.setText("Yangon Technological University");
			stampRun.addBreak();
			stampRun.setText("Yangon");
			
			XWPFParagraph infoPara = doc.createParagraph();
			XWPFRun infoRun = infoPara.createRun();
			infoRun.addBreak();infoRun.addBreak();
			writeNormal(infoRun, "Dated,.........................................");
			infoRun.addBreak();
			writeNormal(infoRun, "Receipt No : ................................"
					+ " / Book No : ............................");
			
			
			/**
			 * Writing data into Microsoft Word File
			 */
			
			FileOutputStream fos = new FileOutputStream(f);
			doc.write(fos);
			doc.close();
			fos.close();
			
			return true;
		}catch(IOException | InvalidFormatException e){
			e.printStackTrace();
			return false;
		}
	}
	
	private static void writeNormal(XWPFRun r,String txt){
		r.setFontSize(11);
		r.setFontFamily("Arial");
		r.setText(txt);
	}
	
	private static void writeVariable(XWPFRun r,String txt){
		r.setFontSize(15);
		r.setFontFamily("Monotype Corsiva");
		r.setBold(true);
		r.setText(txt);
	}
	
	public static HashMap<String,Object> importStdInfoFromOthers(File f){
		MyConstants.ERRORS="";
		int total = 0;
		int success = 0;
		HashMap<String,Object> info = new HashMap<String, Object>();
		try {
			FileInputStream fis = new FileInputStream(f);
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			
			Iterator<Row> iterator = datatypeSheet.iterator();
			
			iterator.next();
			while(iterator.hasNext()){
				total++;
				Row currentRow = iterator.next();
				Student std = new Student();
				Father fath = new Father();
				Mother moth = new Mother();
				Iterator<Cell> cellIterator = currentRow.iterator();
				int colNo=0;
				while(cellIterator.hasNext()){
					Cell currentCell = cellIterator.next();
					String value = "";
					if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
						value = currentCell.getNumericCellValue()+"";
					else
						value = currentCell.getStringCellValue();
					
					value = value.trim();
					
					Date dob = null;
					try{
						dob = currentCell.getDateCellValue();
					}catch(Exception e){
						dob = null;
					}
					if(colNo==0 && Checker.isID(value))
						colNo++;

					switch(colNo){
							//"No.",
					case 1:	std.setStd_id(value);break;//"Entrance ID",
					case 2:	std.setStd_name(value);break;//"Name",
					case 3:	std.setStd_name_mm(value);break;//"Name (MM)",
					case 4:	
						if(value.equals("Male") || value.equals("M"))
							std.setStd_gender("M");
						else
							std.setStd_gender("F");
						break;//"Gender",
					case 5:	std.setStd_ethnic(value);break;//"Ethnic",
					case 6:	std.setStd_religion(value);break;//"Religion",
					case 7:	std.setStd_nrc(value);break;//"NRC No.",
					case 8:	
						if(dob == null){
						SimpleDateFormat fmt;
						if(value.matches("\\d{1,2}/\\d{1,2}/\\d{4,4}"))
							fmt = new SimpleDateFormat("dd/MM/yyyy");
						else if(value.matches("\\d{1,2}-\\d{1,2}-\\d{2,2}"))
							fmt = new SimpleDateFormat("dd-MM-yy");
						else if(value.matches("\\d{1,2}/\\d{1,2}/\\d{2,2}"))
							fmt = new SimpleDateFormat("dd/MM/yy");
						else if(value.matches("\\d{1,2}-\\d{1,2}-\\d{4,4}"))
							fmt = new SimpleDateFormat("dd-MM-yyyy");
						else{
							fmt = new SimpleDateFormat("MMM dd, yyyy");
							value = value.length() > 12 ? value.substring(0,12) : value;
						}
						try {
							std.setStd_dob(fmt.parse(value));
						} catch (ParseException e) {
							std.setStd_dob(new Date());
						}
						}else
							std.setStd_dob(dob);
						break;
					case 9:	std.setStd_mat_id(value);break;//"Matriculation Roll No.",
					case 10: 
						if(dob == null) std.setStd_mat_year(value);
						else{
							std.setStd_mat_year(new SimpleDateFormat("yyyy").format(dob));
						}
						break;	//"Matriculated Year",
					case 11: std.setStd_mat_dept(value);break;	//"Matriculation Department",
					case 12: std.setMajor_code(value);break;	//"Major",
					case 13: 
						std.setFather_nrc(value);
						fath.setFather_nrc(value);
						break;	//"Father's NRC No.",
					case 14:
						std.setMother_nrc(value);
						moth.setMother_nrc(value);
						break;	//"Mother's NRC No.",
					case 15: std.setStd_birth_place(value);break;	//"Birth Place",
					case 16: std.setStd_addr_perm(value);break;	//"Permanent Address",
					case 17: std.setStd_addr_curr(value);break;	//"Temporary Address",
					case 18: std.setStd_phone(value);break;	//"Phone No.",
					case 19: std.setStd_email(value);break;	//"E-mail",
					case 20: fath.setFather_name(value);break;	//"Father Name",
					case 21: fath.setFather_name_mm(value);break;	//"Father Name (MM)",
					case 22: fath.setFather_ethnic(value);break;	//"Father Ethnic",
					case 23: fath.setFather_religion(value);break;	//"Father Religion",
					case 24: fath.setFather_birth_place(value);break;	//"Father Birth Place",
					case 25: fath.setFather_address(value);break;	//"Father Job/Address",
					case 26: moth.setMother_name(value);break;	//"Mother Name",
					case 27: moth.setMother_name_mm(value);break;	//"Mother Name (MM)",
					case 28: moth.setMother_ethnic(value);break;	//"Mother Ethnic",
					case 29: moth.setMother_religion(value);break;	//"Mother Religion",
					case 30: moth.setMother_birth_place(value);break;	//"Mother Birth Place",
					case 31: moth.setMother_address(value);break;	//"Mother Job/Address",
							//"Remark",
					}
					colNo++;
				}
				
				try {
					boolean allSuccess = false;
					allSuccess = StudentDao.saveStudent(std);
					if(allSuccess) allSuccess = FatherDao.saveFather(fath);
					if(allSuccess) allSuccess = MotherDao.saveMother(moth);
					if(allSuccess) success++;
				}catch(Exception e) {
					MyConstants.ERRORS += e.getMessage() == null ? "":(total + ". " + e.getMessage() + "\n");
				}
				
			}
			workbook.close();
			
		} catch (Exception e){
			// e.printStackTrace();
		}
		
		info.put("total", total);
		info.put("success", success);
		info.put("error", MyConstants.ERRORS.length() > 100 ? MyConstants.ERRORS.substring(0, 100) + "...":MyConstants.ERRORS);
		return info;
	}
	
	public static boolean importMarksFromExcel(File f){
		MyConstants.ERRORS="";
		boolean flag = true;
		try {
			FileInputStream fis = new FileInputStream(f);
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			
			Iterator<Row> iterator = datatypeSheet.iterator();
			
			int rowNo=0;
			ArrayList<String> subCodes = new ArrayList<String>();
			while(iterator.hasNext()){
				Row currentRow = iterator.next();
				
				Marks mks = new Marks();
				
				ArrayList<String> mksList = new ArrayList<String>();
				
				Iterator<Cell> cellIterator = currentRow.iterator();
				int colNo=0;
				while(cellIterator.hasNext()){
					Cell currentCell = cellIterator.next();
					String value = "";
					if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
						value = currentCell.getNumericCellValue()+"";
					else
						value = currentCell.getStringCellValue();
					
					if(rowNo==0 && colNo==0 && !(value.equalsIgnoreCase("No.")||
							value.equalsIgnoreCase("No") ||
							value.equalsIgnoreCase("Academic Year"))){
						JOptionPane.showMessageDialog(null, "Incorrect or Incomplete "
								+ "data sheet!", "Warnings", JOptionPane.WARNING_MESSAGE);
						return false;
					}
					if(rowNo==0 && colNo==1 && !(value.equalsIgnoreCase("Academic Year") ||
							value.equalsIgnoreCase("Semester"))){
						JOptionPane.showMessageDialog(null, "Incorrect or Incomplete "
								+ "data sheet!", "Warnings", JOptionPane.WARNING_MESSAGE);
						return false;
					}
					if(rowNo>0){
					if(colNo==0 && (value.matches("dddd-dddd") || value.matches("dddd-dd")))
						colNo++;

					switch(colNo){
							//"No.",
					case 1:	if(value.matches("dddd-dddd")) mks.setAcademic_year(value);
					else if(value.matches("dddd-dd")){
						mks.setAcademic_year(value.substring(0, 5)+value.substring(0, 2)+value.substring(5));
					}
						break;//"Academic Year",
					case 2:	mks.setSemester(value.equalsIgnoreCase("first")?"1":"2");break;//"Semester",
					case 3:	mks.setStd_id(value);break;//"Entrance ID",
					case 4:	mks.setRollno(value);break;//"Roll No.",
					case 5:	break;//"Name",
					default:mksList.add(value);
					}
					HashMap<String, String> marks= new HashMap<String, String>();
					for(int i=0;i<subCodes.size();i++){
						marks.put(subCodes.get(i), mksList.get(i));
					}
					
					String roll = mks.getRollno();
					
					Course c = new Course();
					c.setAcademic_year(mks.getAcademic_year());
					c.setMajor_code(roll.substring(roll.charAt('.')+1, roll.charAt(' ')));
					c.setSemester(mks.getSemester());
					switch(roll.substring(0, roll.charAt('.')+1)){
					case "I.":c.setYear(1+"");break;
					case "II.":c.setYear(2+"");break;
					case "III.":c.setYear(3+"");break;
					case "IV.":c.setYear(4+"");break;
					case "V.":c.setYear(5+"");break;
					case "VI.":c.setYear(6+"");break;
					case "Dip.":c.setYear(7+"");break;
					case "M.S.":c.setYear(8+"");break;
					case "M.E.":c.setYear(9+"");break;
					case "PhD.":c.setYear(10+"");break;
					}
					
					if(!CourseDao.doesCourseExist(c)){
						MyConstants.ERRORS+="Warnings : Course for "+(c.getSemester().equals("1")?"First":"Second")+
					" semester of ACY ("+c.getAcademic_year()+") "
								+ roll.substring(0, roll.charAt(' '))+" does not exist!\n"
										+ "Please Create new course for this result to add to.";
					}else{
						
					}
					
					}else{
						// for header
						if(colNo>=6){
							String subcode = value.substring(0,value.indexOf(' '));
							if(Checker.isSubjectCode(subcode)) subCodes.add(subcode);
						}
					}
					colNo++;
				}
				
				
			}
			workbook.close();
			
		} catch (Exception e){
			e.printStackTrace();
			flag = false;
		}
		
		if(MyConstants.ERRORS!=null || !MyConstants.ERRORS.isEmpty())
			JOptionPane.showMessageDialog(null, MyConstants.ERRORS, "Errors", JOptionPane.ERROR_MESSAGE);
			
		return flag;
	}

}
