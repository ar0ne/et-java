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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private Task copiedTask;
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
        } catch (Exception e) {
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
                if (!filename.substring(filename.length() - 5).equals(".json")) {
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
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        comboBoxInitialize();
        recountWork();

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
        for (TreeItem<Task> part : parts) {
            part.getValue().nullCapacities();

            for (TreeItem<Task> children : part.getChildren()) {
                capacity += children.getValue().getCapacity();
                septemberCapacity += children.getValue().getSeptemberCapacity();
                //part1.getValue().setCapacity(part1.getChildren().get(i).getValue().getCapacity() + part1.getValue().getCapacity());
//            part1.getValue().setSeptemberCapacity(part1.getChildren().get(i).getValue().getSeptemberCapacity() + part1.getValue().getSeptemberCapacity());
//            part1.getValue().setOctoberCapacity(part1.getChildren().get(i).getValue().getOctoberCapacity() + part1.getValue().getOctoberCapacity());
//            part1.getValue().setNovemberCapacity(part1.getChildren().get(i).getValue().getNovemberCapacity() + part1.getValue().getNovemberCapacity());
//            part1.getValue().setDecemberCapacity(part1.getChildren().get(i).getValue().getDecemberCapacity() + part1.getValue().getDecemberCapacity());
//            part1.getValue().setJanuaryCapacity(part1.getChildren().get(i).getValue().getJanuaryCapacity() + part1.getValue().getJanuaryCapacity());
//            part1.getValue().setFirstSemester(part1.getChildren().get(i).getValue().getFirstSemester()+ part1.getValue().getFirstSemester());
//            part1.getValue().setFebruaryCapacity(part1.getChildren().get(i).getValue().getFebruaryCapacity()+ part1.getValue().getFebruaryCapacity());
//            part1.getValue().setMarchCapacity(part1.getChildren().get(i).getValue().getMarchCapacity()+ part1.getValue().getMarchCapacity());
//            part1.getValue().setAprilCapacity(part1.getChildren().get(i).getValue().getAprilCapacity()+ part1.getValue().getAprilCapacity());
//            part1.getValue().setMayCapacity(part1.getChildren().get(i).getValue().getMayCapacity()+ part1.getValue().getMayCapacity());
//            part1.getValue().setJuneCapacity(part1.getChildren().get(i).getValue().getJuneCapacity()+ part1.getValue().getJuneCapacity());
//            part1.getValue().setJulyCapacity(part1.getChildren().get(i).getValue().getJulyCapacity()+ part1.getValue().getJulyCapacity());
//            part1.getValue().setAugustCapacity(part1.getChildren().get(i).getValue().getAugustCapacity()+ part1.getValue().getAugustCapacity());
//            part1.getValue().setSecondSemester(part1.getChildren().get(i).getValue().getSecondSemester()+ part1.getValue().getSecondSemester());
//            part1.getValue().setAllYear(part1.getChildren().get(i).getValue().getAllYear()+ part1.getValue().getAllYear());
            }

            part.getValue().setCapacity(capacity);
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
                        return new EditingCell();
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
                        return new EditingCell();
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

    private class EditingCell extends TreeTableCell<Task, Double> {

        private ContextMenu addMenu = new ContextMenu();
        private ContextMenu deleteMenu = new ContextMenu();
        private TextField textField;

        public EditingCell() {
            MenuItem addMenuItem = new MenuItem("Добавить работу");
            MenuItem deleteMenuItem = new MenuItem("Удалить работу");
            MenuItem copyMenuItem = new MenuItem("Скопировать работу");
            MenuItem pasteMenuItem = new MenuItem("Вставить работу");
            addMenu.getItems().add(addMenuItem);
            addMenuItem.setOnAction(new EventHandler() {
                @Override
                public void handle(Event event) {
                    TreeItem<Task> newEmployee = new TreeItem<Task>(new Task());
                    getTreeTableRow().getTreeItem().getChildren().add(newEmployee);

                    if (getTreeTableRow().getTreeItem().getValue().getProfessorsWork().equals(UCHEBNO_METOD)) {
                        currentProfessor.getTasks().get(0).add(newEmployee.getValue());
                    } else if (getTreeTableRow().getTreeItem().getValue().getProfessorsWork().equals(NAUCHNAYA)) {
                        currentProfessor.getTasks().get(1).add(newEmployee.getValue());
                    } else {
                        currentProfessor.getTasks().get(2).add(newEmployee.getValue());
                    }
                }
            });
            addMenu.getItems().add(pasteMenuItem);
            addMenuItem.setOnAction(new EventHandler() {

                @Override
                public void handle(Event event) {

                }
            });

            deleteMenu.getItems().add(deleteMenuItem);
            deleteMenuItem.setOnAction(new EventHandler() {
                @Override
                public void handle(Event event) {
                    TreeItem<Task> deletedTask = getTreeTableRow().getTreeItem();
                    if (getTreeTableRow().getTreeItem().getParent().getChildren().size() > 1) {
                        getTreeTableRow().getTreeItem().getParent().getChildren().remove(deletedTask);
                        if (getTreeTableRow().getTreeItem().getParent().getValue().getProfessorsWork().equals(UCHEBNO_METOD)) {
                            currentProfessor.getTasks().get(0).remove(deletedTask.getValue());
                        } else if (getTreeTableRow().getTreeItem().getParent().getValue().getProfessorsWork().equals(NAUCHNAYA)) {
                            currentProfessor.getTasks().get(1).remove(deletedTask.getValue());
                        } else {
                            currentProfessor.getTasks().get(2).remove(deletedTask.getValue());
                        }
                    } else {
                        Dialogs.create()
                                .title("Ошибка")
                                .masthead("Невозможно удалить работу")
                                .message("В разделе " + getTreeTableRow().getTreeItem().getParent().getValue().getProfessorsWork()
                                        + " должно быть хоть одна работа")
                                .showError();
                    }
                }
            });

            deleteMenu.getItems().add(copyMenuItem);
            copyMenuItem.setOnAction(new EventHandler() {

                @Override
                public void handle(Event event) {
                    copiedTask = new Task(getTreeTableRow().getTreeItem().getParent().getValue());
                }
            });

        }

        @Override
        public void startEdit() {
            super.startEdit();

            if (textField == null) {
                createTextField();
            }

            setGraphic(textField);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            textField.selectAll();
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            if (String.valueOf(getItem()).equals("null") || String.valueOf(getItem()).equals("0.0")) {
                setText(" ");
            } else {
                setText(String.valueOf(getItem()));
            }
            setContentDisplay(ContentDisplay.TEXT_ONLY);

        }

        @Override
        public void updateItem(Double item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }

                    setGraphic(textField);
                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                } else {
                    setText(getString());
                    setContentDisplay(ContentDisplay.TEXT_ONLY);
                    if (!getTreeTableRow().getTreeItem().isLeaf()
                            && !getTreeTableRow().getTreeItem().equals(root)) {
                        setContextMenu(addMenu);
                    } else if (getTreeTableRow().getTreeItem().isLeaf()) {
                        setContextMenu(deleteMenu);
                    }
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            textField.setOnKeyPressed((KeyEvent t) -> {
                if (t.getCode() == KeyCode.ENTER) {
                    try {
                        commitEdit(Double.parseDouble(textField.getText()));
                        recountWork();
                    } catch (NumberFormatException e) {
                        cancelEdit();
                    }

                } else if (t.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
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
            e.printStackTrace();
            return false;
        }
    }
}
