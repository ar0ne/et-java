/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.et.gui;

import com.company.et.domain.Professor;
import com.company.et.domain.Task;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellEditEvent;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Пальчук
 */
public class MainSceneController implements Initializable {
    private Professor currentProfessor = new Professor();
    
    private final ObservableList<Professor> professorsList = FXCollections.observableArrayList();
    
    ObservableList<Task> tasksList = FXCollections.observableArrayList();
    
    @FXML
    private MenuItem menuBarFileOpen;
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
    private TreeTableColumn<Task, String>taskClmn;
    @FXML
    private TreeTableColumn<Task, String> periodClmn;
    @FXML
    private TreeTableColumn<Task, Double> volumeClmn;
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
    private TreeTableColumn<Task, Double> checkClmn;
    @FXML
    private TreeTableView<Task> treeTableView;

    { //блок инициализации списка задач
        Task task= new Task();
        task.setCapacity(20.02);
        tasksList.add(task);
        tasksList.add(new Task());
        tasksList.add(new Task());        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initData();
        comboBoxInitialize();
        tableColumnInitialize();
    }    
public void initData() { // для примера
        
        
        currentProfessor = new Professor("Профессор1",tasksList,0.5);           
        professorsList.add(currentProfessor);
        ObservableList<Task> taskList2 = FXCollections.observableArrayList();
        taskList2.add(new Task());
        Professor secondProfessor = new Professor("Профессор2",taskList2,1.0);
        professorsList.add(secondProfessor);
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
        tableColumnInitialize();
       });
    }
    public void tableColumnInitialize() {
        Task task = new Task();
        task.setProfessorsWork("Общие");
        task.setCapacity(20.0);
        Task childTask = new Task();
        childTask.setProfessorsWork("Дочерний");
        childTask.setCapacity(10.1);
        TreeItem<Task> root = new TreeItem<>(task);
        TreeItem<Task> child = new TreeItem<>(childTask);
        root.getChildren().add(child);
        setEditableCells();
        taskClmn.setCellValueFactory(new TreeItemPropertyValueFactory<>("professorsWork"));
        periodClmn.setCellValueFactory(new TreeItemPropertyValueFactory<>("period"));
        volumeClmn.setCellValueFactory(new TreeItemPropertyValueFactory<>("capacity"));
        treeTableView.setRoot(root);
    }
    
    
   
    public void setEditableCells() { //метод по обработке изменения в ячейке
        
        taskClmn.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        taskClmn.setOnEditCommit((CellEditEvent<Task, String> t) -> {
            Task task = t.getRowValue().getValue();
            task.setProfessorsWork(t.getNewValue());
        });
        Callback<TreeTableColumn<Task,Double>, TreeTableCell<Task,Double>> cellFactory =
                new Callback<TreeTableColumn<Task,Double>, TreeTableCell<Task,Double>>() {
                           
                          @Override
                          public TreeTableCell call(TreeTableColumn p) {
                              return new EditingCell();
                          }
                };
        
        volumeClmn.setCellFactory(cellFactory);
        volumeClmn.setOnEditCommit((CellEditEvent<Task, Double> t) -> {
            Task task = t.getRowValue().getValue();
            task.setCapacity(t.getNewValue());
        });
        
      
        //TODO доделать для остальных колонок
    }
    @FXML
    private void fileOpen(ActionEvent event) {
    }

    @FXML
    private void fileExit(ActionEvent event) {
    }

    @FXML
    private void newProfessorHandler(ActionEvent event) {
    }

    @FXML
    private void removeProfessorHandler(ActionEvent event) {
    }
    private class ProfessorsListCell extends ListCell<Professor> {
        @Override
        protected void updateItem(Professor professor, boolean empty) {
            super.updateItem(professor, empty);
            
            if (professor != null)
                setText(professor.getFio());
            else 
                setText(null);
        }
        
    }
    
    private class EditingCell extends TreeTableCell<Task, Double> { // класс для изменения ячейки типа Double
        private TextField textField;                // по дефолту можно только стринговые изменять
         
        public EditingCell() {}
         
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
             
            setText(String.valueOf(getItem()));
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
                }
            }
        }
         
        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()*2);
            textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
              
                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ENTER) {
                        commitEdit(Double.parseDouble(textField.getText()));
                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                }
            });
        }
      
        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
}
