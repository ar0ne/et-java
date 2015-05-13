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
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
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

    public static void generateFile(List<TreeItem<Task>> root, List<ArrayList<ArrayList<Double>>> waitingParts, ObservableList<Professor> professors) throws FileNotFoundException, IOException {
        FileOutputStream out = new FileOutputStream(filename);
        Workbook wb = new HSSFWorkbook();

        for (int k = 0; k < professors.size(); k++) {

            Sheet s = wb.createSheet(professors.get(k).getFio());

            Row rowFirst = s.createRow(0);

            createConstStringCells(wb, s, "Месяц", rowFirst, 0, 0, 1, 0, 0);
            createConstStringCells(wb, s, "Методическая работа", rowFirst, 1, 0, 0, 1, 3);
            createConstStringCells(wb, s, "Учебная работа", rowFirst, 4, 0, 0, 4, 6);
            createConstStringCells(wb, s, "Научная работа", rowFirst, 7, 0, 0, 7, 9);
            createConstStringCells(wb, s, "Общественная работа", rowFirst, 10, 0, 0, 10, 12);
            createConstStringCells(wb, s, "Вся работа", rowFirst, 13, 0, 0, 13, 15);

            Row rowSecond = s.createRow(1);
            for (int i = 0, j = 1; i < 5; i++, j += 3) {
                createConstStringCells(wb, s, "План", rowSecond, j, 0, 0, 0, 0);
                createConstStringCells(wb, s, "Факт", rowSecond, j + 1, 0, 0, 0, 0);
                createConstStringCells(wb, s, "Резерв", rowSecond, j + 2, 0, 0, 0, 0);
            }

            for (int i = 1; i < 16; i++) {
                Row row = s.createRow(i + 1);
                for (int j = 0; j < root.get(k).getChildren().size(); j++) {
                    createCellOfDouble(wb, s, row, j * 3 + 2, root.get(k).getChildren().get(j).getValue().getCapacities().get(i));
                    createCellOfDouble(wb, s, row, j * 3 + 1, waitingParts.get(k).get(j).get(i));
                    createReserveCell(wb, s, row, (j + 1) * 3);

                }
                createMonthCell(wb, s, row, DoubleCapacities.getDoubleCapacitiesByIndex(i).toString());
                createFullTasksActualCell(wb, s, row, root.get(k).getChildren().size() * 3 + 1);
                createFullTasksCell(wb, s, row, root.get(k).getChildren().size() * 3 + 2);
                createFullTasksReserveCell(wb, s, row, (root.get(k).getChildren().size() + 1) * 3);

            }
        }
        FormulaEvaluator formulaEval = wb.getCreationHelper().createFormulaEvaluator();
        //all year report
        Sheet s = wb.createSheet("Все");
        Row rowFirst = s.createRow(0);
        Row rowSecond = s.createRow(1);
        createConstStringCells(wb, s, "№П/П", rowFirst, 0, 0, 1, 0, 0);
        createConstStringCells(wb, s, "ФИО", rowFirst, 1, 0, 1, 1, 1);
        createConstStringCells(wb, s, "Должность", rowFirst, 2, 0, 1, 2, 2);
        createConstStringCells(wb, s, "Шт. Ед.", rowFirst, 3, 0, 1, 3, 3);
        for (int i = 1; i < 16; i++) {
            createConstStringCells(wb, s, DoubleCapacities.getDoubleCapacitiesByIndex(i).toString(), rowFirst, (i * 3) + 1, 0, 0, (i * 3) + 1, (i * 3) + 3);
            createConstStringCells(wb, s, "План", rowSecond, (i * 3) + 1, 1, 1, (i * 3) + 1, (i * 3) + 1);
            createConstStringCells(wb, s, "Факт", rowSecond, (i * 3) + 2, 1, 1, (i * 3) + 2, (i * 3) + 2);
            createConstStringCells(wb, s, "Резерв", rowSecond, (i * 3) + 3, 1, 1, (i * 3) + 3, (i * 3) + 3);
        }
        for (int i = 0; i < professors.size(); i++) {
            Row row = s.createRow(i + 2);
            createConstStringCells(wb, s, Integer.toString(i + 1), row, 0, i + 2, i + 2, 0, 0);
            createConstStringCells(wb, s, professors.get(i).getFio(), row, 1, i + 2, i + 2, 1, 1);
            createConstStringCells(wb, s, professors.get(i).getRate().toString(), row, 3, i + 2, i + 2, 3, 3);
            for (int j = 1; j < 16; j++) {
                createConstStringCells(wb, s, formulaEval.evaluate(wb.getSheet(professors.get(i).getFio()).getRow(j + 1).getCell(13)).formatAsString(), row, (j * 3) + 1, i + 2, i + 2, (j * 3) + 1, (j * 3) + 1);
                createConstStringCells(wb, s, formulaEval.evaluate(wb.getSheet(professors.get(i).getFio()).getRow(j + 1).getCell(14)).formatAsString(), row, (j * 3) + 2, i + 2, i + 2, (j * 3) + 2, (j * 3) + 2);
                createConstStringCells(wb, s, formulaEval.evaluate(wb.getSheet(professors.get(i).getFio()).getRow(j + 1).getCell(15)).formatAsString(), row, (j * 3) + 3, i + 2, i + 2, (j * 3) + 3, (j * 3) + 3);
            }
        }
        wb.write(out);

        out.close();

    }

    public static void createReportForMonthForOnePerson(int numOfMonth, Professor professor, ArrayList<ArrayList<Double>> waitingParts, TreeItem<Task> root) throws FileNotFoundException, IOException {

        FileOutputStream out = new FileOutputStream(filename);
        Workbook wb = new HSSFWorkbook();
        Sheet s = wb.createSheet(professor.getFio());
        createReportForMonth(wb, s, numOfMonth, professor, waitingParts, root);
        wb.write(out);
        out.close();
    }
    public static void createReportForMonthForManyPerson(int numOfMonth, List<Professor> professors, ArrayList<ArrayList<ArrayList<Double>>> waitingParts, List<TreeItem<Task>> root) throws FileNotFoundException, IOException {

        FileOutputStream out = new FileOutputStream(filename);
        Workbook wb = new HSSFWorkbook();
        for(int i=0;i<professors.size();i++) {
        Sheet s = wb.createSheet(professors.get(i).getFio());
        createReportForMonth(wb, s, numOfMonth, professors.get(i), waitingParts.get(i), root.get(i));
        }
        wb.write(out);
        out.close();
    }
    public static void createReportForMonth(Workbook wb, Sheet s, int numOfMonth, Professor professor, ArrayList<ArrayList<Double>> waitingParts, TreeItem<Task> root) {

        Row row = s.createRow(0);

        createConstStringCells(wb, s, "Работа преподавателя", row, 0, 0, 0, 0, 0);
        createConstStringCells(wb, s, "Срок", row, 1, 0, 0, 1, 1);
        createConstStringCells(wb, s, "Объём", row, 2, 0, 0, 2, 2);
        createConstStringCells(wb, s, DoubleCapacities.getDoubleCapacitiesByIndex(numOfMonth).toString(), row, 3, 0, 0, 3, 3);
        int rowCounter = 1;
        for (int i = 0; i < root.getChildren().size(); i++) {
            row = s.createRow(rowCounter);
            createConstStringCells(wb, s, (i + 1) + ". " + root.getChildren().get(i).getValue().getProfessorsWork(), row, 0, rowCounter, rowCounter, 0, 0);
            createConstStringCells(wb, s, root.getChildren().get(i).getValue().getPeriod(), row, 1, rowCounter, rowCounter, 1, 1);
            createCellOfDouble(wb, s, row, 2, root.getChildren().get(i).getValue().getCapacities().get(0));
            createCellOfDouble(wb, s, row, 3, waitingParts.get(i).get(numOfMonth));
            rowCounter++;
            for (TreeItem<Task> treeItem : root.getChildren().get(i).getChildren()) {
                Task task = treeItem.getValue();
                row = s.createRow(rowCounter);
                createConstStringCells(wb, s, task.getProfessorsWork(), row, 0, rowCounter, rowCounter, 0, 0);
                createConstStringCells(wb, s, task.getPeriod(), row, 1, rowCounter, rowCounter, 1, 1);
                createCellOfDouble(wb, s, row, 2, task.getCapacities().get(0));
                createCellOfDouble(wb, s, row, 3, task.getCapacities().get(numOfMonth));
                rowCounter++;
            }
        }
        row = s.createRow(rowCounter);
        createConstStringCells(wb, s, "Итого(1+2+3+4)", row, 0, rowCounter, rowCounter, 0, 0);
        createConstStringCells(wb, s, "", row, 1, rowCounter, rowCounter, 1, 1);
        createCellOfDouble(wb, s, row, 2, root.getValue().getCapacities().get(0));
        createCellOfDouble(wb, s, row, 3, root.getValue().getCapacities().get(numOfMonth));
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
        cell.setCellFormula("C" + (row.getRowNum() + 1) + "+ F" + (row.getRowNum() + 1) + "+ I" + (row.getRowNum() + 1) + "+ L" + (row.getRowNum() + 1));
        setCellStyle(wb, s, cell);
    }

    private static void createFullTasksActualCell(Workbook wb, Sheet s, Row row, int column) {
        Cell cell = row.createCell(column);
        cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
        cell.setCellFormula("B" + (row.getRowNum() + 1) + "+ E" + (row.getRowNum() + 1) + "+ H" + (row.getRowNum() + 1) + "+ K" + (row.getRowNum() + 1));
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
        cell.setCellFormula("D" + (row.getRowNum() + 1) + "+ G" + (row.getRowNum() + 1) + "+ J" + (row.getRowNum() + 1) + "+ M" + (row.getRowNum() + 1));
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
