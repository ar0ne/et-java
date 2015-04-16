/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.et.service;

import com.company.et.domain.DoubleCapacities;
import com.company.et.domain.Professor;
import com.company.et.domain.Task;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.scene.control.TreeItem;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;

public class XlsService {

    private static String filename = "data.xls";

    public static void setFilename(String name) {
        filename = name;
    }

    public static String getFilename() {
        return filename;
    }

    public static void generateFile(TreeItem<Task> root, Professor currentProfessor) throws FileNotFoundException, IOException {
        FileOutputStream out = new FileOutputStream(filename);
        Workbook wb = new HSSFWorkbook();
        Sheet s = wb.createSheet(currentProfessor.getFio());
        Row rowFirst = s.createRow(0);

        createConstStringCells(wb, s, "Месяц", rowFirst, 0, 0, 1, 0, 0);
        createConstStringCells(wb, s, "Учебно-Методическая работа", rowFirst, 1, 0, 0, 1, 3);
        createConstStringCells(wb, s, "Научная работа", rowFirst, 4, 0, 0, 4, 6);
        createConstStringCells(wb, s, "Общественная работа", rowFirst, 7, 0, 0, 7, 9);
        createConstStringCells(wb, s, "Вся работа", rowFirst, 10, 0, 0, 10, 12);

        Row rowSecond = s.createRow(1);
        for (int i = 0, j = 1; i < 4; i++, j += 3) {
            createConstStringCells(wb, s, "План", rowSecond, j, 0, 0, 0, 0);
            createConstStringCells(wb, s, "Факт", rowSecond, j + 1, 0, 0, 0, 0);
            createConstStringCells(wb, s, "Резерв", rowSecond, j + 2, 0, 0, 0, 0);
        }

        for (int i = 1; i < 16; i++) {
            Row row = s.createRow(i + 2);
            for (int j = 0; j < root.getChildren().size(); j++) {
                createCellOfDouble(wb, s, row, j * 3 + 1, root.getChildren().get(j).getValue().getCapacities().get(i));
                createReserveCell(wb, s, row, (j + 1) * 3);

            }
            createMonthCell(wb, s, row, DoubleCapacities.getDoubleCapacitiesByIndex(i).toString());
            createFullTasksCell(wb, s, row, root.getChildren().size() * 3 + 1);
            createFullTasksReserveCell(wb, s, row, (root.getChildren().size() + 1) * 3);

        }
        wb.write(out);

        out.close();
    }

    public static void createReportForMonth() {

    }

    private static void createConstStringCells(Workbook wb, Sheet s, String text, Row rowFirst, int coordinateColumn,
            int mergedRegionCoordinatRow1, int mergedRegionCoordinatRow2,
            int mergedRegionCoordinatColumn1, int mergedRegionCoordinatColumn2) {

        s.addMergedRegion(new CellRangeAddress(mergedRegionCoordinatRow1, mergedRegionCoordinatRow2,
                mergedRegionCoordinatColumn1, mergedRegionCoordinatColumn2));
        Cell firstCell = rowFirst.createCell(coordinateColumn);
        firstCell.setCellValue(text);
        setCellStyle(wb, s, firstCell);

    }

    private static void createCellOfDouble(Workbook wb, Sheet s, Row row, int column, Double get) {
        Cell cell = row.createCell(column);
        cell.setCellValue(get);
        setCellStyle(wb, s, cell);
    }

    private static void createFullTasksCell(Workbook wb, Sheet s, Row row, int column) {
        Cell cell = row.createCell(column);
        cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
        cell.setCellFormula("B" + (row.getRowNum() + 1) + "+ E" + (row.getRowNum() + 1) + "+ H" + (row.getRowNum() + 1));
        setCellStyle(wb, s, cell);
    }

    private static void createReserveCell(Workbook wb, Sheet s, Row row, int column) {
        Cell cell = row.createCell(column);
        cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
        cell.setCellFormula(CellReference.convertNumToColString(column - 2) + (row.getRowNum() + 1) + "-"
                + CellReference.convertNumToColString(column - 1) + (row.getRowNum() + 1));
        setCellStyle(wb, s, cell);
    }

    private static void createFullTasksReserveCell(Workbook wb, Sheet s, Row row, int column) {
        Cell cell = row.createCell(column);
        cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
        cell.setCellFormula("D" + (row.getRowNum() + 1) + "+ G" + (row.getRowNum() + 1) + "+ J" + (row.getRowNum() + 1));
        setCellStyle(wb, s, cell);
    }

    private static void createMonthCell(Workbook wb, Sheet s, Row row, String text) {
        Cell cell = row.createCell(0);
        cell.setCellValue(text);
        setCellStyle(wb, s, cell);
    }

    private static void setCellStyle(Workbook wb, Sheet s, Cell cell) {
        CellStyle cellStyle = wb.createCellStyle();
//        if (cell.getStringCellValue().equals("Месяц")) {
//            s.autoSizeColumn(0,true);
//        }
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        Font font = wb.createFont();
        font.setBold(true);
        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);
    }

}
