package com.company.et.service;

import com.company.et.domain.Professor;
import com.company.et.domain.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by denis on 20.2.15.
 */
public class ProfessorService {

    public static void addNewProfessor(List<Professor> currentList, Professor newProfessor) {
        currentList.add(newProfessor);
    }

    public static void removeProfessor(List<Professor> currentList, Integer indexOfRemoveProfessor) {
        if (indexOfRemoveProfessor >= 0 && indexOfRemoveProfessor < currentList.size()) {
            currentList.remove(indexOfRemoveProfessor);
        }
    }

    public static Professor createNewProfessor() {
        return new Professor();
    }

//    public static void addTaskToProfessor(Professor professor, Task task) {
//        professor.getTasks().add(task);
//    }
//
//    public static void removeTaskFromProfessor(Professor professor, Integer id) {
//        if (id >= 0 && id < professor.getTasks().size()) {
//            professor.getTasks().remove(id);
//        }
//    }
}
