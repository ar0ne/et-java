/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.et.gui;
import com.company.et.domain.Task;
import static com.company.et.gui.MainSceneController.NAUCHNAYA;
import static com.company.et.gui.MainSceneController.METOD;
import java.text.NumberFormat;
import javafx.event.ActionEvent;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author denis
 */
class EditingCell extends TreeTableCell<Task, Double> {

    private final ContextMenu addMenu = new ContextMenu();
    private final ContextMenu deleteMenu = new ContextMenu();
    private TextField textField;
    private final MenuItem addMenuItem;
    private final MenuItem deleteMenuItem;
    private final MenuItem copyMenuItem;
    private final MenuItem pasteMenuItem;
    private final MenuItem markComplMenuItem;
    private final MenuItem markUnComplItem;
    private MainSceneController mainSceneController;
    private final NumberFormat numberFormat; 
    public EditingCell(MainSceneController mainSC) {
        this.numberFormat = NumberFormat.getNumberInstance();
        this.markUnComplItem = new MenuItem("Отменить отметку о выполнении");
        this.markComplMenuItem = new MenuItem("Отметить как выполнена");
        this.pasteMenuItem = new MenuItem("Вставить работу");
        this.copyMenuItem = new MenuItem("Скопировать работу");
        this.deleteMenuItem = new MenuItem("Удалить работу");
        this.addMenuItem = new MenuItem("Добавить работу");
        numberFormat.setMaximumFractionDigits(3);
        this.mainSceneController = mainSC;
        addMenu.getItems().add(addMenuItem);
        addMenuItem.setOnAction((ActionEvent event) -> {
            TreeItem<Task> newEmployee = new TreeItem<>(new Task());
            getTreeTableRow().getTreeItem().getChildren().add(newEmployee);
            
            if (getTreeTableRow().getTreeItem().getValue().getProfessorsWork().equals(METOD)) {
                mainSceneController.getCurrentProfessor().getTasks().get(0).add(newEmployee.getValue());
            } else if (getTreeTableRow().getTreeItem().getValue().getProfessorsWork().equals(NAUCHNAYA)) {
                mainSceneController.getCurrentProfessor().getTasks().get(1).add(newEmployee.getValue());
            } else {
                mainSceneController.getCurrentProfessor().getTasks().get(2).add(newEmployee.getValue());
            }
        });

        addMenu.getItems().add(pasteMenuItem);
        pasteMenuItem.setOnAction((ActionEvent event) -> {
            TreeItem<Task> newEmployee = new TreeItem<>(mainSceneController.getCopiedTask());
            getTreeTableRow().getTreeItem().getChildren().add(newEmployee);
            
            if (getTreeTableRow().getTreeItem().getValue().getProfessorsWork().equals(METOD)) {
                mainSceneController.getCurrentProfessor().getTasks().get(0).add(newEmployee.getValue());
            } else if (getTreeTableRow().getTreeItem().getValue().getProfessorsWork().equals(NAUCHNAYA)) {
                mainSceneController.getCurrentProfessor().getTasks().get(1).add(newEmployee.getValue());
            } else {
                mainSceneController.getCurrentProfessor().getTasks().get(2).add(newEmployee.getValue());
            }
        });

        deleteMenu.getItems().add(deleteMenuItem);
        deleteMenuItem.setOnAction((ActionEvent event) -> {
            TreeItem<Task> deletedTask = getTreeTableRow().getTreeItem();
            if (getTreeTableRow().getTreeItem().getParent().getChildren().size() > 1) {
                getTreeTableRow().getTreeItem().getParent().getChildren().remove(deletedTask);
            } else {
                Dialogs.create()
                        .title("Ошибка")
                        .masthead("Невозможно удалить работу")
                        .message("В разделе " + getTreeTableRow().getTreeItem().getParent().getValue().getProfessorsWork()
                                + " должно быть хоть одна работа")
                        .showError();
            }
        });

        deleteMenu.getItems().add(copyMenuItem);
        copyMenuItem.setOnAction((ActionEvent event) -> {
            mainSceneController.setCopiedTask(new Task(getTreeTableRow().getTreeItem().getValue()));
        });
        deleteMenu.getItems().add(markComplMenuItem);
        markComplMenuItem.setOnAction((ActionEvent event) -> {
            getTreeTableRow().getItem().setCompleteWork(true);
            mainSC.recountWork();
            getTreeTableRow().setStyle("-fx-background-color:lightgreen");
        });
        deleteMenu.getItems().add(markUnComplItem);
        markUnComplItem.setOnAction((ActionEvent event) -> {
            getTreeTableRow().setStyle("");
            getTreeTableRow().getItem().setCompleteWork(false);
            mainSC.recountWork();
        });
        if ( getTreeTableRow() != null && getTreeTableRow().getItem().getCompleteWork()) {
            getTreeTableRow().setStyle("-fx-background-color:lightgreen");
        }
            
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

            }
            if (item != null && item != 0.0) {
                    
                setText(numberFormat.format(item));
            } else {
                setText("");
            }
            if (getTreeTableRow().getTreeItem() != null) {

                if (getTreeTableRow().getTreeItem().isLeaf()) {
                    setContextMenu(deleteMenu);
                    if (getTreeTableRow().getTreeItem().getValue().getCompleteWork())
                        getTreeTableRow().setStyle("-fx-background-color:lightgreen");
                    else getTreeTableRow().setStyle("");
                } 
                else {
                    setEditable(false);
                    if (!getTreeTableRow().getTreeItem().equals(mainSceneController.getRoot())) {
                        setContextMenu(addMenu);
                        getTreeTableRow().setStyle("");
                    }
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
                    double parseDouble = Double.parseDouble(textField.getText());

                    if (parseDouble == 0.0) {
                        commitEdit(null);
                    } else {
                        commitEdit(Double.parseDouble(textField.getText()));
                    }                  
                    mainSceneController.recountWork();
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
