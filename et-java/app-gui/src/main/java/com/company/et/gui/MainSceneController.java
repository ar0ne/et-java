package com.company.et.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import com.company.et.domain.Professor;
import com.company.et.domain.Task;

import java.util.ArrayList;



import javafx.event.EventHandler;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;




public class MainSceneController implements Initializable {
    
    private Professor currentProfessor = new Professor();
    
    private ObservableList<Professor> professorsList = FXCollections.observableArrayList();
    
    
    @FXML 
    private TableView<Task> tableOfTasks;
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
    private TableColumn<Task, String> taskClmn;
    @FXML
    private TableColumn<Task, String> periodClmn;
    @FXML
    private TableColumn<Task, Double> volumeClmn;
    @FXML
    private TableColumn<?, ?> septemberClmn;
    @FXML
    private TableColumn<?, ?> octoberClmn;
    @FXML
    private TableColumn<?, ?> novemberClmn;
    @FXML
    private TableColumn<?, ?> decemberClmn;
    @FXML
    private TableColumn<?, ?> januaryClmn;
    @FXML
    private TableColumn<?, ?> firstSemClmn;
    @FXML
    private TableColumn<?, ?> februaryClmn;
    @FXML
    private TableColumn<?, ?> marchClmn;
    @FXML
    private TableColumn<?, ?> aprilClmn;
    @FXML
    private TableColumn<?, ?> mayClmn;
    @FXML
    private TableColumn<?, ?> juneClmn;
    @FXML
    private TableColumn<?, ?> julyClmn;
    @FXML
    private TableColumn<?, ?> secondSemClmn;
    @FXML
    private TableColumn<?, ?> allYearClmn;
    @FXML
    private TableColumn<?, ?> checkClmn;
    @FXML
    private TableColumn<?, ?> markClmn;
    
    public MainSceneController() {
    }
    
    //MenuBar
    @FXML
    private void fileExit(ActionEvent event) {
        
    }
    
    @FXML private void fileOpen(ActionEvent event) {
        
    }
    
    //Buttons
    @FXML
    private void newProfessorHandler(ActionEvent event) {
        ObservableList<Task> t = FXCollections.observableArrayList();
        t = currentProfessor.getTasks();
        
        System.out.println(currentProfessor.toString());
//        for(int i=0;i<t.size();i++) 
//        System.out.println(t.get(0).toString());       //нынешний функционал не имеет отношения к кнопке,
                                                       //при нажатии на кнопку "+" можно убедиться, что при изменении 
                                                       //значения в ячейке изменяется и сам объект
                                                       //на примере первой строки(вывод в консоли)
                                                       //потом функционал поменяется
        
    }
    @FXML
    private void removeProfessorHandler(ActionEvent event) {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initData();
        comboBoxInitialize();
        tableColumnInitialize();
        
        
        
    }
    
    public void initData() { // для примера
        ObservableList<Task> tasksList = FXCollections.observableArrayList();
        Task task= new Task();
        task.setCapacity(20.02);
        tasksList.add(task);
        tasksList.add(new Task());
        tasksList.add(new Task());
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
        setEditableCells();
        taskClmn.setCellValueFactory(new PropertyValueFactory<>("professorsWork"));
        periodClmn.setCellValueFactory(new PropertyValueFactory<>("period"));
        volumeClmn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        tableOfTasks.setItems(currentProfessor.getTasks());
    }
    
    
   
    public void setEditableCells() { //метод по обработке изменения в ячейке
        
        taskClmn.setCellFactory(TextFieldTableCell.forTableColumn());
        taskClmn.setOnEditCommit((CellEditEvent<Task, String> t) -> {
            ((Task) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
                    ).setProfessorsWork(t.getNewValue());
        });
        
        periodClmn.setCellFactory(TextFieldTableCell.forTableColumn());
        periodClmn.setOnEditCommit((CellEditEvent<Task, String> t) -> {
            ((Task) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
                    ).setPeriod(t.getNewValue());
        });
        
        Callback<TableColumn<Task,Double>, TableCell<Task,Double>> cellFactory =
                new Callback<TableColumn<Task,Double>, TableCell<Task,Double>>() {
                           
                          @Override
                          public TableCell call(TableColumn p) {
                              return new EditingCell();
                          }
                };
        
        volumeClmn.setCellFactory(cellFactory);
        volumeClmn.setOnEditCommit((TableColumn.CellEditEvent<Task, Double> t) -> {
            ((Task)t.getTableView().getItems().get(
                    t.getTablePosition().getRow())).setCapacity(t.getNewValue());
        });
        
        //TODO доделать для остальных колонок
    }
    
    



    private class EditingCell extends TableCell<Task, Double> { // класс для изменения ячейки типа Double
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
    
}

