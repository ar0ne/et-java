package com.company.et.service;

import com.company.et.domain.Task;
import com.company.et.domain.*;
/**
 * Created by denis on 21.2.15.
 */
public class StdWorksService {

    public static void addStdWorks(StdWorks stdWorks, Work stdWork) {
        stdWorks.getWorks().add(stdWork);
    }

    public static void removeStandartTask(StdWorks stdWorks, Integer id) {
        if (id >= 0 && id < stdWorks.getWorks().size())
            stdWorks.getWorks().remove(id);
    }
}
