/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.et.gui;

import com.company.et.domain.DoubleCapacities;
import com.company.et.domain.Professor;
import com.company.et.domain.Task;
import com.company.et.service.JsonService;
import com.company.et.service.XlsService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellEditEvent;
import javafx.scene.control.TreeTablePosition;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author
 */
public class MainSceneController implements Initializable {

    public static final int COUNT_OF_DOUBLE_COLUMNS = 16;
    public static final int NUMBER_OF_ALL_YEAR_COLUMN = 15;
    public static final int NUMBER_OF_COLUNM_OF_SECOND_SEM = 14;
    public static final int NUMBER_OF_COLUMN_OF_FIRST_SEM = 6;
    public static final String METOD = "Методическая";
    public static final String UCHEBNAYA = "Учебная";
    public static final String NAUCHNAYA = "Научная";
    public static final String OBSHESTVENNAYA = "Общественная";
    public static final String RESERVE = "Резерв";
    public static final String OBSHEE = "Общее";
    public static final String ADD_NEW_PROFESSOR = "Добавление нового преподавателя";
    public static final String SPISOK_ZADACH = "Список Задач";

    private ArrayList<TreeTableColumn<Task, Double>> listOfColumns;
    private Professor currentProfessor = new Professor();
    private Professor standartProfessor = new Professor();
    private final ObservableList<Professor> professorsList = FXCollections.observableArrayList();
    private TreeItem<Task> dummyRoot;
    private TreeItem<Task> reserve;
    private TreeItem<Task> root;
    private ArrayList<ArrayList<Double>> partsWaiting = new ArrayList<>();
    private Task copiedTask = null;
    private final FileChooser fileChooserForJson = new FileChooser();
    private int hours;
    public static int countOfHours = 1548;
    private boolean flag = false; //переменная, которая не пропускает окошко сохранения при старте проги
    @FXML
    private ComboBox<Professor> comboBoxProfessorsList;
    @FXML
    private TextField textFieldRate;
    @FXML
    private Label labelFileName;
    @FXML
    private TextField textFieldHours;
    @FXML
    private TreeTableColumn<Task, String> taskClmn;
    @FXML
    private TreeTableColumn<Task, String> periodClmn;
    @FXML
    private TreeTableView<Task> treeTableView;

    @FXML
    private void fileOpen(ActionEvent event) throws IOException, ParseException {
        fileChooserForJson.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON", "*.json")
        );
        fileChooserForJson.setTitle("Выберите файл");
        try {
            File file = fileChooserForJson.showOpenDialog(null);
            String json = JsonService.readFromFile(file);
            Professor[] profs = JsonService.jsonToObjectProfessorArray(json);
            JsonService.setFilename(file.getName());
            labelFileName.setText(JsonService.getFilename());
            // clear list and add profs from json
            professorsList.clear();
            professorsList.addAll(Arrays.asList(profs));
            currentProfessor = profs[0];
            initializeGUI();
        } catch (IOException | ParseException e) {
            Dialogs.create()
                    .title("Ошибка")
                    .message("Ошибка открытия файла")
                    .showError();
        }
    }

    @FXML
    private void fileSaveHandler(ActionEvent event) throws IOException, ParseException {
        fileSave();
    }

    private void fileSave() throws IOException, ParseException {
        // если professorList не был загружен, то вызываем сохранить как
        // @TODO: добавить проверку по лучше
        if ("undefined".equals(labelFileName.getText())) {
            fileSaveAs();
        } else {
            JsonService.writeToFile(JsonService.objectToString(professorsList));
        }
    }

    @FXML
    private void fileSaveAsHandler(ActionEvent event) {
        fileSaveAs();
    }

    private void fileSaveAs() {
        //сохранение в новый файл (диалоговое окно)
        File file = fileChooserForJson.showSaveDialog(null);
        if (file != null) {
            try {
                // add .json to filename
                String filename = file.getName();
                if (filename.length() <= 5 || !filename.substring(filename.length() - 5).equals(".json")) {
                    filename += ".json";
                }
                JsonService.setFilename(filename);
                JsonService.writeToFile(JsonService.objectToString(professorsList));
                labelFileName.setText(JsonService.getFilename());
            } catch (IOException | ParseException ex) {
                Dialogs.create()
                        .title("Ошибка")
                        .message("Ошибка сохранения файла")
                        .showError();
            }
        }
    }

    @FXML
    private void fileExit(ActionEvent event) throws IOException, ParseException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Выход");
        alert.setHeaderText("Подтверждение");
        alert.setContentText("Сохранить данные перед выходом?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            fileSave();
        }

        Platform.exit();
    }

    @FXML
    private void removeProfessorHandler(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Удаление");
        alert.setHeaderText("Подтверждение");
        alert.setContentText("Вы уверены, что хотите удалить запись?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            professorsList.remove(currentProfessor);
            currentProfessor = professorsList.get(0);
        }
    }

    @FXML
    private void newProfessorHandler(ActionEvent event) {
        Professor tempProfessor = new Professor();
        for (int i = 0; i < tempProfessor.getTasks().size(); i++) {
            tempProfessor.getTasks().get(i).add(new Task());
        }
        boolean okClicked = showNewProfessorDialog(tempProfessor);
        if (okClicked) {
            professorsList.add(tempProfessor);
        }
        try {
            fileSave();
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void generateAllYearReport(ActionEvent event) throws IOException {
        //Настраиваем форму создания файла генерации
        FileChooser fileChooserForXls = new FileChooser();
        fileChooserForXls.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("EXCEL", "*.xls")
        );
        final String extentionString = ".xls";
        File file = fileChooserForXls.showSaveDialog(null);
        if (file != null) {
            String filename = file.getName();
            if (filename.length() <= extentionString.length()
                    || !filename.substring(filename.length() - extentionString.length()).equals(extentionString)) {
                filename += ".xls";
            }
            XlsService.setFilename(filename);

            /*Создаем список часов всех выполненных работ (roots)  и возможно 
             выполненных работ (allOfParts). Также удаляем список всех работ 
             (standartProfessor)
             */
            ArrayList<TreeItem<Task>> roots = new ArrayList<>();
            ArrayList<ArrayList<ArrayList<Double>>> allOfParts = new ArrayList<>();
            int currentId = professorsList.indexOf(currentProfessor);

            ObservableList<Professor> professors = FXCollections.observableArrayList();

            professorsList.stream().filter((professor) -> !(professor.getFio().equals(SPISOK_ZADACH))).forEach((professor) -> {
                professors.add(professor);
                setCurrentProfessor(professor);
                roots.add(root);
                allOfParts.add(new ArrayList<>(partsWaiting));
            });
            XlsService.generateFile(roots, allOfParts, professors);
            setCurrentProfessor(professorsList.get(currentId));
        }
    }

    @FXML
    public void generateForMonthReport(ActionEvent event) throws FileNotFoundException, IOException {
        //Настраиваем форму создания файла генерации
        FileChooser fileChooserForXls = new FileChooser();
        fileChooserForXls.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("EXCEL", "*.xls")
        );
        final String extentionString = ".xls";
        File file = fileChooserForXls.showSaveDialog(null);
        if (file != null) {
            String filename = file.getName();
            if (filename.length() <= extentionString.length()
                    || !filename.substring(filename.length() - extentionString.length()).equals(extentionString)) {
                filename += ".xls";
            }
            XlsService.setFilename(filename);
        }

        ObservableList<TreeTablePosition<Task, ?>> currentColumn = treeTableView.getSelectionModel().getSelectedCells();
        int column;
        if (currentColumn.isEmpty()) {
            showError("Не выделена ячейка для определения месяца");
        } else {
            column = currentColumn.get(0).getColumn();
            if (column >= 3) {
                column -= 2;
                XlsService.createReportForMonthForOnePerson(column, currentProfessor, partsWaiting, root);
            } else {
                showError("Выделена ячейка не с месяцем");
            }
        }
    }

    @FXML
    public void generateForMonthReportForAll(ActionEvent event) throws FileNotFoundException, IOException {
        //Настраиваем форму создания файла генерации
        FileChooser fileChooserForXls = new FileChooser();
        fileChooserForXls.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("EXCEL", "*.xls")
        );
        final String extentionString = ".xls";
        File file = fileChooserForXls.showSaveDialog(null);
        if (file != null) {
            String filename = file.getName();
            if (filename.length() <= extentionString.length()
                    || !filename.substring(filename.length() - extentionString.length()).equals(extentionString)) {
                filename += ".xls";
            }
            XlsService.setFilename(filename);

            ObservableList<TreeTablePosition<Task, ?>> currentColumn = treeTableView.getSelectionModel().getSelectedCells();
            int column;
            if (currentColumn.isEmpty()) {
                showError("Не выделена ячейка для определения месяца");
            } else {
                column = currentColumn.get(0).getColumn();
                if (column >= 3) {
                    column -= 2;
                    ArrayList<TreeItem<Task>> roots = new ArrayList<>();
                    ArrayList<ArrayList<ArrayList<Double>>> allOfParts = new ArrayList<>();
                    int currentId = professorsList.indexOf(currentProfessor);

                    ObservableList<Professor> professors = FXCollections.observableArrayList();

                    professorsList.stream().filter((professor) -> !(professor.getFio().equals(SPISOK_ZADACH))).forEach((professor) -> {
                        professors.add(professor);
                        setCurrentProfessor(professor);
                        roots.add(root);
                        allOfParts.add(new ArrayList<>(partsWaiting));
                    });
                    XlsService.createReportForMonthForManyPerson(column, professors, allOfParts, roots);
                    setCurrentProfessor(professorsList.get(currentId));
                } else {
                    showError("Выделена ячейка не с месяцем");
                }
            }

        }
    }

    public void showError(String textError) {
        Dialogs.create()
                .title("Ошибка")
                .message(textError)
                .showError();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initData();
            labelFileName.setText("undefined");

        } catch (IOException | ParseException ex) {
            Logger.getLogger(MainSceneController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        initializeGUI();
        flag = true;
    }

    public Professor getCurrentProfessor() {
        return currentProfessor;
    }

    public Task getCopiedTask() {
        return copiedTask;
    }

    public void setCopiedTask(Task copiedTask) {
        this.copiedTask = copiedTask;
    }

    public TreeItem getRoot() {
        return root;
    }

    public ArrayList<ArrayList<Double>> getPartsWaiting() {
        return partsWaiting;
    }

    public void setPartsWaiting(ArrayList<ArrayList<Double>> parts) {
        this.partsWaiting = parts;
    }

    public void initData() throws IOException, ParseException {
        listOfColumns = new ArrayList<>();
        for (int i = 0; i < COUNT_OF_DOUBLE_COLUMNS; i++) {
            listOfColumns.add(new TreeTableColumn<>(DoubleCapacities.getDoubleCapacitiesByIndex(i).toString()));
        }
        //два преподавателя при инициализации
        currentProfessor = new Professor();
        currentProfessor.setFio("Профессор 1");
        currentProfessor.setRate(1.0);
        for (int i = 0; i < currentProfessor.getTasks().size(); i++) {
            currentProfessor.getTasks().get(i).add(new Task());
        }
        professorsList.add(currentProfessor);

        Professor secondProfessor = new Professor();
        for (int i = 0; i < currentProfessor.getTasks().size(); i++) {
            secondProfessor.getTasks().get(i).add(new Task());
        }
        professorsList.add(secondProfessor);

        //Преподаватель - список всех задач
        standartProfessor = new Professor();
        standartProfessor.setFio(SPISOK_ZADACH);
        for (int i = 0; i < currentProfessor.getTasks().size(); i++) {
            standartProfessor.getTasks().get(i).add(new Task());
        }
        professorsList.add(standartProfessor);

        treeTableView.getColumns().addAll(listOfColumns);

    }

    public void initTableData() {
        hours = countOfHours;

        Task all = new Task();
        all.setProfessorsWork(OBSHEE);

        Task task = new Task();
        task.setProfessorsWork(METOD);

        Task task4 = new Task();
        task4.setProfessorsWork(UCHEBNAYA);

        Task task2 = new Task();
        task2.setProfessorsWork(NAUCHNAYA);

        Task task3 = new Task();
        task3.setProfessorsWork(OBSHESTVENNAYA);

        Task reserveTask = new Task();
        reserveTask.setProfessorsWork(RESERVE);

        root = new TreeItem<>(all);
        root.getChildren().add(new TreeItem<>(task));
        root.getChildren().add(new TreeItem<>(task4));
        root.getChildren().add(new TreeItem<>(task2));
        root.getChildren().add(new TreeItem<>(task3));

        root.getChildren().stream().forEach((part) -> {
            part.setExpanded(true);
        });

        for (int i = 0; i < currentProfessor.getTasks().size(); i++) {
            for (int j = 0; j < currentProfessor.getTasks().get(i).size(); j++) {
                root.getChildren().get(i).getChildren().add(new TreeItem<>(currentProfessor.getTasks().get(i).get(j)));
            }
        }

        reserve = new TreeItem<>(reserveTask);
        dummyRoot = new TreeItem<>();
        dummyRoot.getChildren().addAll(root, reserve);
    }

    public void recountWork() {
        //обнуляем список возможновыполненных работ
        partsWaiting.clear();
        partsWaiting.add(new ArrayList<>());
        partsWaiting.add(new ArrayList<>());
        partsWaiting.add(new ArrayList<>());
        partsWaiting.add(new ArrayList<>());

        partsWaiting.stream().forEach((partsWaiting1) -> {
            partsWaiting1.addAll(Collections.nCopies(COUNT_OF_DOUBLE_COLUMNS, 0.0));
        });

        ArrayList<Double> sumCapacitiesActual;
        ArrayList<Double> sumCapacitiesWaiting;

        for (int k = 0; k < root.getChildren().size(); k++) {
            sumCapacitiesActual = new ArrayList<>(Collections.nCopies(COUNT_OF_DOUBLE_COLUMNS, 0.0));
            sumCapacitiesWaiting = new ArrayList<>(Collections.nCopies(COUNT_OF_DOUBLE_COLUMNS, 0.0));

            for (TreeItem<Task> children : root.getChildren().get(k).getChildren()) {

                double allCapacityForSem = 0.0;

                for (int i = 1; i < NUMBER_OF_COLUMN_OF_FIRST_SEM; i++) {
                    allCapacityForSem += children.getValue().getCapacities().get(i);
                }
                children.getValue().getCapacities().set(NUMBER_OF_COLUMN_OF_FIRST_SEM, allCapacityForSem);

                allCapacityForSem = 0.0;
                for (int i = 7; i < NUMBER_OF_COLUNM_OF_SECOND_SEM; i++) {
                    allCapacityForSem += children.getValue().getCapacities().get(i);
                }
                children.getValue().getCapacities().set(NUMBER_OF_COLUNM_OF_SECOND_SEM, allCapacityForSem);

                children.getValue().getCapacities().set(NUMBER_OF_ALL_YEAR_COLUMN, children.getValue().getCapacities().get(NUMBER_OF_COLUMN_OF_FIRST_SEM)
                        + children.getValue().getCapacities().get(NUMBER_OF_COLUNM_OF_SECOND_SEM));

                for (int i = 0; i < children.getValue().getCapacities().size(); i++) {
                    sumCapacitiesWaiting.set(i, sumCapacitiesWaiting.get(i) + children.getValue().getCapacities().get(i));
                    if (children.getValue().getCompleteWork()) {
                        sumCapacitiesActual.set(i, sumCapacitiesActual.get(i) + children.getValue().getCapacities().get(i));
                    }
                }

            }

            for (int i = 0; i < sumCapacitiesActual.size(); i++) {
                partsWaiting.get(k).set(i, sumCapacitiesWaiting.get(i));
                root.getChildren().get(k).getValue().getCapacities().set(i, sumCapacitiesActual.get(i));
            }
        }

        sumCapacitiesActual = new ArrayList<>(Collections.nCopies(COUNT_OF_DOUBLE_COLUMNS, 0.0));
        sumCapacitiesWaiting = new ArrayList<>(Collections.nCopies(COUNT_OF_DOUBLE_COLUMNS, 0.0));

        for (int k = 0; k < root.getChildren().size(); k++) {
            for (int i = 0; i < sumCapacitiesActual.size(); i++) {
                sumCapacitiesWaiting.set(i, sumCapacitiesWaiting.get(i) + partsWaiting.get(k).get(i));
                sumCapacitiesActual.set(i, sumCapacitiesActual.get(i) + root.getChildren().get(k).getValue().getCapacities().get(i));
            }
        }

        for (int i = 0; i < COUNT_OF_DOUBLE_COLUMNS; i++) {
            root.getValue().getCapacities().set(i, sumCapacitiesActual.get(i));
        }

        for (int i = 0; i < COUNT_OF_DOUBLE_COLUMNS; i++) {
            if (i == DoubleCapacities.CAPACITY.ordinal() || i == DoubleCapacities.ALL_YEAR.ordinal()) {
                reserve.getValue().getCapacities().set(i, hours - root.getValue().getCapacities().get(i));
            } else if (i == DoubleCapacities.FIRST_SEMESTER.ordinal() || i == DoubleCapacities.SECOND_SEMESTER.ordinal()) {
                reserve.getValue().getCapacities().set(i, hours / 2 - root.getValue().getCapacities().get(i));
            } else {
                reserve.getValue().getCapacities().set(i, hours * currentProfessor.getRate() / 10 - root.getValue().getCapacities().get(i));
            }
        }
        tableColumnInitialize();
    }

    public void textLabelsInitialize() {
        textFieldHours.setOnAction((event) -> {
            if (textFieldHours.getText() != null && !textFieldHours.getText().isEmpty()) {
                hours = Integer.parseInt(textFieldHours.getText());
            }
        });
        textFieldHours.setText(Integer.toString(hours));

        textFieldRate.setOnAction((event) -> {
            if (textFieldRate.getText() != null && !textFieldRate.getText().isEmpty()) {
                currentProfessor.setRate(Double.parseDouble(textFieldRate.getText()));
            }
        });
        textFieldRate.setText(currentProfessor.getRate().toString());
    }

    public void comboBoxInitialize() {
        comboBoxProfessorsList.setItems(professorsList);
        comboBoxProfessorsList.getSelectionModel().selectFirst();
        comboBoxProfessorsList.setButtonCell(new ProfessorsListCell());
        comboBoxProfessorsList.setCellFactory((ListView<Professor> p) -> new ProfessorsListCell());

        comboBoxProfessorsList.setOnAction((event) -> {
            Professor selectedProfessor = comboBoxProfessorsList.getSelectionModel().getSelectedItem();
            setCurrentProfessor(selectedProfessor);

        });
    }

    //--------------------------------------------------------------------------------------
    public void tableColumnInitialize() {

        setEditableCells();
        taskClmn.setCellValueFactory(new TreeItemPropertyValueFactory<>("professorsWork"));
        periodClmn.setCellValueFactory(new TreeItemPropertyValueFactory<>("period"));
        for (int i = 0; i < COUNT_OF_DOUBLE_COLUMNS; i++) {
            final int columnIndex = i;
            listOfColumns.get(i).setCellValueFactory((TreeTableColumn.CellDataFeatures<Task, Double> param) -> new ReadOnlyObjectWrapper<>(param.getValue().getValue().getCapacities().get(columnIndex)));
        }
        treeTableView.setRoot(dummyRoot);
        treeTableView.setShowRoot(false);

        try {
            if (flag) {
                fileSave();
            } else {
                flag = true;
            }
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setEditableCells() { //РјРµС‚РѕРґ РїРѕ РѕР±СЂР°Р±РѕС‚РєРµ РёР·РјРµРЅРµРЅРёСЏ РІ СЏС‡РµР№РєРµ

        taskClmn.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        taskClmn.setOnEditCommit((CellEditEvent<Task, String> t) -> {
            Task task = t.getRowValue().getValue();
            task.setProfessorsWork(t.getNewValue());
        });
        periodClmn.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        periodClmn.setOnEditCommit((CellEditEvent<Task, String> t) -> {
            Task task = t.getRowValue().getValue();
            task.setPeriod(t.getNewValue());
        });

        Callback<TreeTableColumn<Task, Double>, TreeTableCell<Task, Double>> cellFactory;
        cellFactory = (TreeTableColumn<Task, Double> p) -> {
            return new EditingCell(MainSceneController.this);
        };
        for (int i = 0; i < listOfColumns.size(); i++) {
            final int index = i;
            listOfColumns.get(i).setCellFactory(cellFactory);
            listOfColumns.get(i).setOnEditCommit(new EventHandlerImpl(index));
        }
    }

    private void initializeGUI() {
        initTableData();
        recountWork();
        textLabelsInitialize();
        comboBoxInitialize();
    }

    private void setCurrentProfessor(Professor selectedProfessor) {
        this.currentProfessor = selectedProfessor;
        initTableData();
        recountWork();
        textLabelsInitialize();
    }

    private class ProfessorsListCell extends ListCell<Professor> {

        @Override
        protected void updateItem(Professor professor, boolean empty) {
            super.updateItem(professor, empty);

            if (professor != null) {
                setText(professor.getFio());
            } else {
                setText(null);
            }
        }

    }

    public boolean showNewProfessorDialog(Professor professor) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/NewProfessor.fxml"));
            GridPane page = (GridPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle(ADD_NEW_PROFESSOR);
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            NewProfessorController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProfessor(professor);

            dialogStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException e) {
            return false;
        }
    }

    private static class EventHandlerImpl implements EventHandler<CellEditEvent<Task, Double>> {

        private final int index;

        public EventHandlerImpl(int index) {
            this.index = index;
        }

        @Override
        public void handle(CellEditEvent<Task, Double> t) {
            Task task = t.getRowValue().getValue();
            task.getCapacities().set(index, t.getNewValue());
        }
    }

}
