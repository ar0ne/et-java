/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.et.gui;

import com.company.et.domain.Professor;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author ieshua
 */
public class NewProfessorController {
    
    @FXML
    private TextField textFieldFIO;
    @FXML
    private TextField textFieldRate;
    
    private Stage dialogStage;
    private boolean okClicked = false;
    private Professor professor;
    
    
    @FXML
    private void initialize() {
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
    
    public boolean isOkClicked() {
        return okClicked;
    }
    
    @FXML
    private void cancelButtonHandler() {
        dialogStage.close();
    }
    
    @FXML
    private void okButtonHandler() {
        if(isInputValid()) {
            professor.setFio(textFieldFIO.getText());
            professor.setRate(Double.valueOf(textFieldRate.getText()));
            
            okClicked = true;
            dialogStage.close();
        }
    }
    
    private boolean isInputValid() {
        String errorMessage = "";

        if (textFieldFIO.getText() == null || textFieldFIO.getText().length() == 0) {
            errorMessage += "Введите инициалы преподавателя!\n"; 
        }
        if (textFieldRate.getText() == null || textFieldRate.getText().length() == 0) {
            errorMessage += "Введите ставку преподавателя!\n"; 
        }else {
            try {
                Double.parseDouble(textFieldRate.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Поле ставки заполнено не верно!\n"; 
            }
        }
       

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Dialogs.create()
                .title("Ошибка")
                .masthead("Исправьте следующие ошибки!")
                .message(errorMessage)
                .showError();
            return false;
        }
    }
    
}
