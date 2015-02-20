package com.company.et.service;

import com.company.et.domain.Task;
import com.company.et.domain.*;
/**
 * Created by denis on 21.2.15.
 */
public class StandartTasksService {

    public static void addStandartTask(StandartTasks standartTasks, Task standartTask) {
        standartTasks.getTasks().add(standartTask);
    }

    public static void removeStandartTask(StandartTasks standartTasks, Integer id) {
        if (id >= 0 && id < standartTasks.getTasks().size())
            standartTasks.getTasks().remove(id);
    }
}
