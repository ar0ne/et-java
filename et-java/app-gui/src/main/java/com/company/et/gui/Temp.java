/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.et.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author ieshua
 */
public class Temp {
    private StringProperty task;
    
    public Temp(String str) {
        this.task = new SimpleStringProperty(str);
    }
    
    public String getTask() {
        return task.get();
    }
    
    public void setTask(String str) {
        task.set(str);
    }
    
    public StringProperty taskProperty() {
        return task;
    }
}


//package com.company.et.gui;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Label;
//
//import com.company.et.domain.Professor;
//import com.company.et.domain.Task;
//import com.company.et.domain.Work;
//import java.util.ArrayList;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.MenuItem;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//
//
//
//public class MainSceneController implements Initializable {
//    
//    Professor professor = new Professor();
//    ObservableList<Task> tasks = FXCollections.observableArrayList();;
//    
//    @FXML 
//    TableView<Task> tableOfTasks;
//    @FXML
//    private MenuItem menuBarFileOpen;
//    @FXML
//    private MenuItem menuBarFileExit;
//    @FXML
//    private ComboBox<?> professorsList;
//    @FXML
//    private Button newProfessor;
//    @FXML
//    private Button removeProfessor;
//    @FXML
//    private TextField rate;
//    @FXML
//    private TableColumn<Task, String> taskClmn;
//    @FXML
//    private TableColumn<Task, String> periodClmn;
//    @FXML
//    private TableColumn<Task, Double> volumeClmn;
//    @FXML
//    private TableColumn<?, ?> septemberClmn;
//    @FXML
//    private TableColumn<?, ?> octoberClmn;
//    @FXML
//    private TableColumn<?, ?> novemberClmn;
//    @FXML
//    private TableColumn<?, ?> decemberClmn;
//    @FXML
//    private TableColumn<?, ?> januaryClmn;
//    @FXML
//    private TableColumn<?, ?> firstSemClmn;
//    @FXML
//    private TableColumn<?, ?> februaryClmn;
//    @FXML
//    private TableColumn<?, ?> marchClmn;
//    @FXML
//    private TableColumn<?, ?> aprilClmn;
//    @FXML
//    private TableColumn<?, ?> mayClmn;
//    @FXML
//    private TableColumn<?, ?> juneClmn;
//    @FXML
//    private TableColumn<?, ?> julyClmn;
//    @FXML
//    private TableColumn<?, ?> secondSemClmn;
//    @FXML
//    private TableColumn<?, ?> allYearClmn;
//    @FXML
//    private TableColumn<?, ?> checkClmn;
//    @FXML
//    private TableColumn<?, ?> markClmn;
//    
//    //MenuBar
//    @FXML
//    private void fileExit(ActionEvent event) {
//        
//    }
//    
//    @FXML private void fileOpen(ActionEvent event) {
//        
//    }
//    
//    //Buttons
//    @FXML
//    private void newProfessorHandler(ActionEvent event) {
//       
//        
//    }
//    @FXML
//    private void removeProfessorHandler(ActionEvent event) {
//        
//    }
//    
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//        initData();
//        taskClmn.setCellValueFactory(new PropertyValueFactory<Task, String>("professorsWork"));
//        periodClmn.setCellValueFactory(new PropertyValueFactory<Task, String>("period"));
//        volumeClmn.setCellValueFactory(new PropertyValueFactory<Task, Double>("capacity"));
//        
//        tableOfTasks.setItems(professor.getTasks());
//        
//    } 
//    
//    public void initData() {
//        professor.setFio("Головко");
//                
//        tasks.add(new Task());
//        tasks.add(new Task());
//        tasks.add(new Task());
//        professor.setTasks(tasks);
//    }