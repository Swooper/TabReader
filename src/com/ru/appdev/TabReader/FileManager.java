package com.ru.appdev.TabReader;

import java.util.List;

/**
 * Created by haukur on 03/11/2014.
 */
public class FileManager {
    private List<String> openFiles;
    private String currentTab;

    private void OnCreate() {
        // TODO: Retrieve list of open files somehow

    }

    public List<String> getOpenFiles () {
        return openFiles;
    }

    public String getCurrentTab () {
        return currentTab;
    }

}
