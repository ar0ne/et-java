package com.company.et.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import com.company.et.domain.Professor;
import com.company.et.domain.Task;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;



public class MainSceneController implements Initializable {
    
    @FXML TableView<Task> tableOfTasks;
    @FXML
    private MenuItem menuBarFileOpen;
    @FXML
    private MenuItem menuBarFileExit;
    @FXML
    private ComboBox<?> professorsList;
    @FXML
    private Button newProfessor;
    @FXML
    private Button removeProfessor;
    @FXML
    private TextField rate;
    @FXML
    private TableColumn<?, ?> taskClmn;
    @FXML
    private TableColumn<?, ?> periodClmn;
    @FXML
    private TableColumn<?, ?> volumeClmn;
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
