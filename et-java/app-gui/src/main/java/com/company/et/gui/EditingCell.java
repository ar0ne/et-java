/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.et.gui;

import com.company.et.domain.Professor;
import com.company.et.domain.Task;
import static com.company.et.gui.MainSceneController.NAUCHNAYA;
import static com.company.et.gui.MainSceneController.UCHEBNO_METOD;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
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

    private ContextMenu addMenu = new ContextMenu();
    private ContextMenu deleteMenu = new ContextMenu();
    private TextField textField;
    private MenuItem addMenuItem = new MenuItem("Добавить работу");
    private MenuItem deleteMenuItem = new MenuItem("Удалить работу");
    private MenuItem copyMenuItem = new MenuItem("Скопировать работу");
    private MenuItem pasteMenuItem = new MenuItem("Вставить работу");
    private MainSceneController mainSceneController;

    public EditingCell(MainSceneController mainSC) {
        this.mainSceneController = mainSC;
        addMenu.getItems().add(addMenuItem);
        addMenuItem.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                TreeItem<Task> newEmployee = new TreeItem<Task>(new Task());
                getTreeTableRow().getTreeItem().getChildren().add(newEmployee);

                if (getTreeTableRow().getTreeItem().getValue().getProfessorsWork().equals(UCHEBNO_METOD)) {
                    mainSceneController.getCurrentProfessor().getTasks().get(0).add(newEmployee.getValue());
                } else if (getTreeTableRow().getTreeItem().getValue().getProfessorsWork().equals(NAUCHNAYA)) {
                    mainSceneController.getCurrentProfessor().getTasks().get(1).add(newEmployee.getValue());
                } else {
                    mainSceneController.getCurrentProfessor().getTasks().get(2).add(newEmployee.getValue());
                }
            }
        });

        addMenu.getItems().add(pasteMenuItem);
        pasteMenuItem.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                TreeItem<Task> newEmployee = new TreeItem<>(mainSceneController.getCopiedTask());
                getTreeTableRow().getTreeItem().getChildren().add(newEmployee);

                if (getTreeTableRow().getTreeItem().getValue().getProfessorsWork().equals(UCHEBNO_METOD)) {
                    mainSceneController.getCurrentProfessor().getTasks().get(0).add(newEmployee.getValue());
                } else if (getTreeTableRow().getTreeItem().getValue().getProfessorsWork().equals(NAUCHNAYA)) {
                    mainSceneController.getCurrentProfessor().getTasks().get(1).add(newEmployee.getValue());
                } else {
                    mainSceneController.getCurrentProfessor().getTasks().get(2).add(newEmployee.getValue());
                }
            }
        });

        deleteMenu.getItems().add(deleteMenuItem);
        deleteMenuItem.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                TreeItem<Task> deletedTask = getTreeTableRow().getTreeItem();
                if (getTreeTableRow().getTreeItem().getParent().getChildren().size() > 1) {
                    getTreeTableRow().getTreeItem().getParent().getChildren().remove(deletedTask);
                    for (ObservableList<Task> task : mainSceneController.getCurrentProfessor().getTasks()) {
                        if (task.remove(deletedTask)) {
                            break;
                        }
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
                mainSceneController.setCopiedTask(new Task(getTreeTableRow().getTreeItem().getValue()));
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
                if (textField != null && item != 0.0) {
                    textField.setText(getString());
                }

                setGraphic(textField);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            } else {
                setText(getString());
                setContentDisplay(ContentDisplay.TEXT_ONLY);

            }
            if (!getTreeTableRow().getTreeItem().isLeaf()
                    && !getTreeTableRow().getTreeItem().equals(mainSceneController.getRoot())) {
                setContextMenu(addMenu);
            } else if (getTreeTableRow().getTreeItem().isLeaf()) {
                setContextMenu(deleteMenu);
            }
        }

    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        textField.setOnKeyPressed((KeyEvent t) -> {
            if (t.getCode() == KeyCode.ENTER) {
                try {
                    if (Double.parseDouble(textField.getText()) != 0.0) {
                        commitEdit(Double.parseDouble(textField.getText()));
                        mainSceneController.recountWork();
                    }
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
