package com.tommy.loggerapp;

import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class LogManager {
    private static LogManager instance;

    private List<Log> logList;

    public LogManager() {
        logList = new ArrayList<>();
    }
    public static LogManager getInstance() {
        if (instance == null) {
            instance = new LogManager();
        }
        return instance;
    }

    public static void setInstance(LogManager instance) {
        LogManager.instance = instance;
    }

    public boolean addLog() {
        Log l = new Log();

        if (logList.contains(l)) {
            System.out.println("Log already exists");
            return false;
        }
        else {
            logList.addFirst(l);
            System.out.println("Log added: \n" + l.toString());
            return true;
        }
    }

    public List<Log> getLogList() {
        return logList;
    }

    public void setLogList(List<Log> logList) {
        this.logList = logList;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("LogManager: \n");
        for (Log log : logList) {
            output.append(log.toString()).append("\n");
        }
        return output.toString();
    }
}
