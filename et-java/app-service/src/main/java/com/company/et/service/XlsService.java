/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.et.service;

import com.company.et.domain.Professor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class XlsService {

    private static String filename = "data.xls";

    public static void setFilename(String name) {
        filename = name;
    }

    public static String getFilename() {
        return filename;
    }

    public static void generateFile(Professor currentProfessor) throws FileNotFoundException, IOException {
        FileOutputStream out = new FileOutputStream(filename);
        Workbook wb = new HSSFWorkbook();
        Sheet s = wb.createSheet(currentProfessor.getFio());
        Row rowFirst = s.createRow(0);
        
        createConstStringCells(wb,s,"Месяц",rowFirst, 0, 0, 1, 0, 0);
        createConstStringCells(wb,s,"Учебная работа",rowFirst,1,0,0,1,3);
        createConstStringCells(wb,s,"Методическая работа",rowFirst,4,0,0,4,6);
        createConstStringCells(wb,s,"Научная работа",rowFirst,7,0,0,7,9);
        createConstStringCells(wb,s,"Общественная работа",rowFirst,10,0,0,10,12);
        createConstStringCells(wb,s,"Вся работа",rowFirst,13,0,0,13,15);
        
        Row rowSecond = s.createRow(1);
        for(int i=0,j=1;i<5;i++,j+=3) {
            createConstStringCells(wb,s,"План",rowSecond, j, 0, 0, 0, 0);
            createConstStringCells(wb,s,"Факт",rowSecond, j+1, 0, 0, 0, 0);
            createConstStringCells(wb,s,"Резерв",rowSecond, j+2, 0, 0, 0, 0);
        }
        
        wb.write(out);

        out.close();
    }

    public static void createConstStringCells(Workbook wb, Sheet s, String text, Row rowFirst, int coordinateColumn, 
            int mergedRegionCoordinatRow1, int mergedRegionCoordinatRow2,
            int mergedRegionCoordinatColumn1, int mergedRegionCoordinatColumn2) {
        
        
        s.addMergedRegion(new CellRangeAddress(mergedRegionCoordinatRow1, mergedRegionCoordinatRow2, 
                mergedRegionCoordinatColumn1, mergedRegionCoordinatColumn2));
        Cell firstCell = rowFirst.createCell(coordinateColumn);
        firstCell.setCellValue(text);
        CellStyle firstCellStyle = wb.createCellStyle();
        firstCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        firstCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        firstCell.setCellStyle(firstCellStyle);
        
    }
    public static void createReportForMonth() {
        
    }        
}
