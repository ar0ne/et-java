package com.company.et.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import com.company.et.domain.Professor;
import com.company.et.domain.Task;

import java.io.IOException;
import javafx.event.EventHandler;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

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
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;




public class MainSceneControllerOld implements Initializable {
    
    private Professor currentProfessor = new Professor();
    
    private ObservableList<Professor> professorsList = FXCollections.observableArrayList();
    
    ObservableList<Task> tasksList = FXCollections.observableArrayList();
    
    
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
    private TableColumn<Task, Double>septemberClmn;
    @FXML
    private TableColumn<Task, Double> octoberClmn;
    @FXML
    private TableColumn<Task, Double> novemberClmn;
    @FXML
    private TableColumn<Task, Double> decemberClmn;
    @FXML
    private TableColumn<Task, Double> januaryClmn;
    @FXML
    private TableColumn<Task, Double> firstSemClmn;
    @FXML
    private TableColumn<Task, Double> februaryClmn;
    @FXML
    private TableColumn<Task, Double> marchClmn;
    @FXML
    private TableColumn<Task, Double> aprilClmn;
    @FXML
    private TableColumn<Task, Double> mayClmn;
    @FXML
    private TableColumn<Task, Double> juneClmn;
    @FXML
    private TableColumn<Task, Double> julyClmn;
    @FXML
    private TableColumn<Task, Double> augustClmn;
    @FXML
    private TableColumn<Task, Double> secondSemClmn;
    @FXML
    private TableColumn<Task, Double> allYearClmn;
    @FXML
    private TableColumn<Task, Boolean> checkClmn;
    
    { //блок инициализации списка задач
        Task task= new Task();
        task.setCapacity(20.02);
        tasksList.add(task);
        tasksList.add(new Task());
        tasksList.add(new Task());        
    }
    
     
    //MenuBar
    @FXML
    private void fileExit(ActionEvent event) {
        
    }
    
    @FXML 
    private void fileOpen(ActionEvent event) {
        
    }
    
    //Buttons
    @FXML
    private void newProfessorHandler(ActionEvent event) {
        Professor tempProfessor = new Professor();
        tempProfessor.setTasks(tasksList);
        boolean okClicked = showNewProfessorDialog(tempProfessor);
            if (okClicked) {
                professorsList.add(tempProfessor);
            }       
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
    
    
    public boolean showNewProfessorDialog(Professor professor) { //метод для прорисовки диалогового окна добавления препода
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/NewProfessor.fxml"));
            GridPane page = (GridPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Добавление преподавателя");
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

