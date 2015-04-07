/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.et.gui;

import com.company.et.domain.Professor;
import com.company.et.domain.Task;
import com.company.et.service.JsonService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellEditEvent;
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

    public static String UCHEBNO_METOD = "Учебно-методическая";
    public static String NAUCHNAYA = "Научная";
    public static String OBSHESTVENNAYA = "Общественная";
    private Professor currentProfessor = new Professor();

    private final ObservableList<Professor> professorsList = FXCollections.observableArrayList();

    private TreeItem<Task> root;
    private ArrayList<TreeItem<Task>> parts = new ArrayList<>();
    private Task copiedTask = null;
    ObservableList<Task> tasksList = FXCollections.observableArrayList();
    FileChooser fc = new FileChooser();

    @FXML
    private MenuItem menuBarFileOpen;
    @FXML
    private MenuItem menuBarFileSave;
    @FXML
    private MenuItem menuBarFileSaveAs;
    @FXML
    private MenuItem menuBarFileExit;
    @FXML
    private ComboBox<Professor> comboBoxProfessorsList;
    @FXML
    private Button newProfessor;
    @FXML
    private Button removeProfessor;
    @FXML
    private TextField rate;
    @FXML
    private Label labelFileName;
    @FXML
    private TreeTableColumn<Task, String> taskClmn;
    @FXML
    private TreeTableColumn<Task, String> periodClmn;
    @FXML
    private TreeTableColumn<Task, Double> capacityClmn;
    @FXML
    private TreeTableColumn<Task, Double> septemberClmn;
    @FXML
    private TreeTableColumn<Task, Double> octoberClmn;
    @FXML
    private TreeTableColumn<Task, Double> novemberClmn;
    @FXML
    private TreeTableColumn<Task, Double> decemberClmn;
    @FXML
    private TreeTableColumn<Task, Double> januaryClmn;
    @FXML
    private TreeTableColumn<Task, Double> firstSemClmn;
    @FXML
    private TreeTableColumn<Task, Double> februaryClmn;
    @FXML
    private TreeTableColumn<Task, Double> marchClmn;
    @FXML
    private TreeTableColumn<Task, Double> aprilClmn;
    @FXML
    private TreeTableColumn<Task, Double> mayClmn;
    @FXML
    private TreeTableColumn<Task, Double> juneClmn;
    @FXML
    private TreeTableColumn<Task, Double> julyClmn;
    @FXML
    private TreeTableColumn<Task, Double> augustClmn;
    @FXML
    private TreeTableColumn<Task, Double> secondSemClmn;
    @FXML
    private TreeTableColumn<Task, Double> allYearClmn;
    @FXML
    private TreeTableColumn<Task, Boolean> checkClmn;
    @FXML
    private TreeTableView<Task> treeTableView;

    @FXML
    private void fileOpen(ActionEvent event) throws IOException, ParseException {
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON", "*.json")
        );
        fc.setTitle("Выберите файл");
        try {
            File file = fc.showOpenDialog(null);
            String json = JsonService.readFromFile(file);
            Professor[] profs = JsonService.jsonToObjectProfessorArray(json);
            JsonService.setFilename(file.getName());
            labelFileName.setText(JsonService.getFilename());
            professorsList.clear(); // clear list before add profs from json
            professorsList.addAll(Arrays.asList(profs));
            currentProfessor = profs[0];
            comboBoxInitialize();
            initTableData();
            recountWork();
        } catch (IOException | ParseException e) {
            Dialogs.create()
                    .title("Ошибка")
                    .message("Ошибка открытия файла")
                    .showError();
        }
    }

    @FXML
    private void fileSave(ActionEvent event) throws IOException, ParseException {
        // если professorList не был загружен, то вызываем сохранить как
        // @TODO: добавить проверку по лучше
        if ("undefined".equals(labelFileName.getText())) {
            fileSaveAs(event);
        } else {
            JsonService.writeToFile(JsonService.objectToString(professorsList));
        }

    }

    @FXML
    private void fileSaveAs(ActionEvent event) {
        //сохранение в новый файл (диалоговое окно)
        File file = fc.showSaveDialog(null);
        if (file != null) {
            try {
                // add .json to filename
                String filename = file.getName();
                if ( filename.length() <= 5 || !filename.substring(filename.length() - 5).equals(".json") ) {
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
            fileSave(event);
        }

        Platform.exit();
    }

    @FXML
    private void removeProfessorHandler(ActionEvent event) {
        professorsList.remove(currentProfessor);
        currentProfessor = professorsList.get(0);
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
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initData();
            labelFileName.setText("undefined");
        } catch (IOException | ParseException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        comboBoxInitialize();
        recountWork();

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
    public void initData() throws IOException, ParseException {

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
        initTableData();

    }

    public void initTableData() {
        Task all = new Task();
        all.setProfessorsWork("Общее");

        Task task = new Task();
        task.setProfessorsWork(UCHEBNO_METOD);

        Task task2 = new Task();
        task2.setProfessorsWork(NAUCHNAYA);

        Task task3 = new Task();
        task3.setProfessorsWork(OBSHESTVENNAYA);

        root = new TreeItem<>(all);
        parts.clear();
        parts.add(new TreeItem<>(task));
        parts.add(new TreeItem<>(task2));
        parts.add(new TreeItem<>(task3));

        for (TreeItem<Task> part : parts) {
            part.setExpanded(true);
            root.getChildren().add(part);
        }

        for (int i = 0; i < currentProfessor.getTasks().size(); i++) {
            for (int j = 0; j < currentProfessor.getTasks().get(i).size(); j++) {
                parts.get(i).getChildren().add(new TreeItem<>(currentProfessor.getTasks().get(i).get(j)));
            }
        }
    }

    public void recountWork() {
        for (TreeItem<Task> part : parts) {
            double capacity = 0.0;
            double septemberCapacity = 0.0;
            double octoberCapacity = 0.0;
            double novemberCapacity = 0.0;
            double decemberCapacity = 0.0;
            double januaryCapacity = 0.0;
            double firstSemester = 0.0;
            double februaryCapacity = 0.0;
            double marchCapacity = 0.0;
            double aprilCapacity = 0.0;
            double mayCapacity = 0.0;
            double juneCapacity = 0.0;
            double julyCapacity = 0.0;
            double augustCapacity = 0.0;
            double secondSemester = 0.0;
            double allYear = 0.0;
            part.getValue().nullCapacities();

            for (TreeItem<Task> children : part.getChildren()) {
                capacity += children.getValue().getCapacity();
                septemberCapacity += children.getValue().getSeptemberCapacity();
                octoberCapacity += children.getValue().getOctoberCapacity();
                novemberCapacity += children.getValue().getNovemberCapacity();
                decemberCapacity += children.getValue().getDecemberCapacity();
                januaryCapacity += children.getValue().getJanuaryCapacity();
                firstSemester += children.getValue().getFirstSemester();
                februaryCapacity += children.getValue().getFebruaryCapacity();
                marchCapacity += children.getValue().getMarchCapacity();
                aprilCapacity += children.getValue().getAprilCapacity();
                mayCapacity += children.getValue().getMayCapacity();
                juneCapacity += children.getValue().getJuneCapacity();
                julyCapacity += children.getValue().getJulyCapacity();
                augustCapacity += children.getValue().getAugustCapacity();
                secondSemester += children.getValue().getSecondSemester();
                allYear += children.getValue().getAllYear();
            }

            part.getValue().setCapacity(capacity);
            part.getValue().setSeptemberCapacity(septemberCapacity);
            part.getValue().setOctoberCapacity(octoberCapacity);
            part.getValue().setNovemberCapacity(novemberCapacity);
            part.getValue().setDecemberCapacity(decemberCapacity);
            part.getValue().setJanuaryCapacity(januaryCapacity);
            part.getValue().setFirstSemester(firstSemester);
            part.getValue().setFebruaryCapacity(februaryCapacity);
            part.getValue().setMarchCapacity(marchCapacity);
            part.getValue().setAprilCapacity(aprilCapacity);
            part.getValue().setMayCapacity(mayCapacity);
            part.getValue().setJuneCapacity(juneCapacity);
            part.getValue().setJulyCapacity(julyCapacity);
            part.getValue().setAugustCapacity(augustCapacity);
            part.getValue().setSecondSemester(secondSemester);
            part.getValue().setAllYear(allYear);
        }
        
        tableColumnInitialize();
    }

    public void comboBoxInitialize() {
        comboBoxProfessorsList.setItems(professorsList);
        comboBoxProfessorsList.getSelectionModel().selectFirst();
        comboBoxProfessorsList.setButtonCell(new ProfessorsListCell());
        comboBoxProfessorsList.setCellFactory((ListView<Professor> p) -> new ProfessorsListCell());

        // Handle CheckBox event.
        comboBoxProfessorsList.setOnAction((event) -> {
            Professor selectedProfessor = comboBoxProfessorsList.getSelectionModel().getSelectedItem();
            this.currentProfessor = selectedProfessor;
            initTableData();
            recountWork();
        });
    }

    //--------------------------------------------------------------------------------------
    public void tableColumnInitialize() {

        setEditableCells();
        taskClmn.setCellValueFactory(new TreeItemPropertyValueFactory<>("professorsWork"));
        periodClmn.setCellValueFactory(new TreeItemPropertyValueFactory<>("period"));
        capacityClmn.setCellValueFactory(new TreeItemPropertyValueFactory<>("capacity"));
        septemberClmn.setCellValueFactory(new TreeItemPropertyValueFactory<>("septemberCapacity"));
        octoberClmn.setCellValueFactory(new TreeItemPropertyValueFactory<>("octoberCapacity"));
        novemberClmn.setCellValueFactory(new TreeItemPropertyValueFactory<>("novemberCapacity"));
        decemberClmn.setCellValueFactory(new TreeItemPropertyValueFactory<>("decemberCapacity"));
        januaryClmn.setCellValueFactory(new TreeItemPropertyValueFactory<>("januaryCapacity"));
        firstSemClmn.setCellValueFactory(new TreeItemPropertyValueFactory<>("firstSemester"));
        februaryClmn.setCellValueFactory(new TreeItemPropertyValueFactory<>("februaryCapacity"));
        treeTableView.setRoot(root);
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

        Callback<TreeTableColumn<Task, Double>, TreeTableCell<Task, Double>> cellFactory
                = new Callback<TreeTableColumn<Task, Double>, TreeTableCell<Task, Double>>() {

                    @Override
                    public TreeTableCell call(TreeTableColumn p) {
                        return new EditingCell(MainSceneController.this);
                    }
                };

        capacityClmn.setCellFactory(cellFactory);
        capacityClmn.setOnEditCommit((CellEditEvent<Task, Double> t) -> {
            Task task = t.getRowValue().getValue();
            task.setCapacity(t.getNewValue());
        });

        septemberClmn.setCellFactory(cellFactory);
        septemberClmn.setOnEditCommit((CellEditEvent<Task, Double> t) -> {
            Task task = t.getRowValue().getValue();
                task.setSeptemberCapacity(t.getNewValue());
                    
        });

        octoberClmn.setCellFactory(cellFactory);
        octoberClmn.setOnEditCommit((CellEditEvent<Task, Double> t) -> {
            Task task = t.getRowValue().getValue();
            task.setOctoberCapacity(t.getNewValue());
        });

        novemberClmn.setCellFactory(cellFactory);
        novemberClmn.setOnEditCommit((CellEditEvent<Task, Double> t) -> {
            Task task = t.getRowValue().getValue();
            task.setNovemberCapacity(t.getNewValue());
        });

        decemberClmn.setCellFactory(cellFactory);
        decemberClmn.setOnEditCommit((CellEditEvent<Task, Double> t) -> {
            Task task = t.getRowValue().getValue();
            task.setDecemberCapacity(t.getNewValue());
        });

        januaryClmn.setCellFactory(cellFactory);
        januaryClmn.setOnEditCommit((CellEditEvent<Task, Double> t) -> {
            Task task = t.getRowValue().getValue();
            task.setJanuaryCapacity(t.getNewValue());
        });

        firstSemClmn.setCellFactory(cellFactory);
        firstSemClmn.setOnEditCommit((CellEditEvent<Task, Double> t) -> {
            Task task = t.getRowValue().getValue();
            task.setFirstSemester(t.getNewValue());
        });

        februaryClmn.setCellFactory(cellFactory);
        februaryClmn.setOnEditCommit((CellEditEvent<Task, Double> t) -> {
            Task task = t.getRowValue().getValue();
            task.setFebruaryCapacity(t.getNewValue());
        });

        marchClmn.setCellFactory(cellFactory);
        marchClmn.setOnEditCommit((CellEditEvent<Task, Double> t) -> {
            Task task = t.getRowValue().getValue();
            task.setMarchCapacity(t.getNewValue());
        });

        aprilClmn.setCellFactory(cellFactory);
        aprilClmn.setOnEditCommit((CellEditEvent<Task, Double> t) -> {
            Task task = t.getRowValue().getValue();
            task.setAprilCapacity(t.getNewValue());
        });

        mayClmn.setCellFactory(cellFactory);
        juneClmn.setOnEditCommit((CellEditEvent<Task, Double> t) -> {
            Task task = t.getRowValue().getValue();
            task.setMayCapacity(t.getNewValue());
        });

        juneClmn.setCellFactory(cellFactory);
        juneClmn.setOnEditCommit((CellEditEvent<Task, Double> t) -> {
            Task task = t.getRowValue().getValue();
            task.setJuneCapacity(t.getNewValue());
        });

        julyClmn.setCellFactory(cellFactory);
        julyClmn.setOnEditCommit((CellEditEvent<Task, Double> t) -> {
            Task task = t.getRowValue().getValue();
            task.setJulyCapacity(t.getNewValue());
        });

        augustClmn.setCellFactory(cellFactory);
        augustClmn.setOnEditCommit((CellEditEvent<Task, Double> t) -> {
            Task task = t.getRowValue().getValue();
            task.setAugustCapacity(t.getNewValue());
        });

        secondSemClmn.setCellFactory(cellFactory);
        secondSemClmn.setOnEditCommit((CellEditEvent<Task, Double> t) -> {
            Task task = t.getRowValue().getValue();
            task.setSecondSemester(t.getNewValue());
        });

        allYearClmn.setCellFactory(cellFactory);
        allYearClmn.setOnEditCommit((CellEditEvent<Task, Double> t) -> {
            Task task = t.getRowValue().getValue();
            task.setAllYear(t.getNewValue());
        });

        Callback<TreeTableColumn<Task, Boolean>, TreeTableCell<Task, Boolean>> cellBoolFactory
                = new Callback<TreeTableColumn<Task, Boolean>, TreeTableCell<Task, Boolean>>() {

                    @Override
                    public TreeTableCell call(TreeTableColumn p) {
                        return new EditingCell(MainSceneController.this);
                    }
                };
        checkClmn.setCellFactory(cellBoolFactory);
        checkClmn.setOnEditCommit((CellEditEvent<Task, Boolean> t) -> {
            Task task = t.getRowValue().getValue();
            task.setCompleteWork(t.getNewValue());
        });
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

    public boolean showNewProfessorDialog(Professor professor) { //РјРµС‚РѕРґ РґР»СЏ РїСЂРѕСЂРёСЃРѕРІРєРё РґРёР°Р»РѕРіРѕРІРѕРіРѕ РѕРєРЅР° РґРѕР±Р°РІР»РµРЅРёСЏ РїСЂРµРїРѕРґР°
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/NewProfessor.fxml"));
            GridPane page = (GridPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Добавление нового преподавателя");
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
}
