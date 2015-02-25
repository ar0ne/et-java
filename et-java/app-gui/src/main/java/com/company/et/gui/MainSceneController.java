package com.company.et.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import com.company.et.domain.Professor;
import com.company.et.domain.Task;
import javafx.scene.control.TableView;



public class MainSceneController implements Initializable {
    
    @FXML TableView<Task> tableOfTasks;
    
    //MenuBar
    @FXML
    private void fileExit(ActionEvent event) {
        
    }
    
    @FXML private void fileOpen(ActionEvent event) {
        
    }
    
    //Buttons
    @FXML
    private void newProfessorHandler(ActionEvent event) {
       
        
    }
    @FXML
    private void removeProfessorHandler(ActionEvent event) {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
}
