package com.tommy.loggerapp;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Saver {
    private static Saver instance;
    private final File saveDirectory = new File("saves");
    private final File saveFile = new File(saveDirectory, "save.json");
    private Saver()
    {}

    public static Saver getInstance() {
        if (instance == null) {
            instance = new Saver();
        }
        return instance;
    }
    public void loadLogManagerFromFile() {
        try {
            saveDirectory.mkdir();
            saveFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            LogManager lm = mapper.readValue(saveFile, LogManager.class);
            LogManager.setInstance(lm);
            System.out.println(lm);
        } catch (
                JacksonException e) {
            System.out.println("ERROR LOADING SAVE");
            System.err.println(e);
        }
    }

    public void saveLogManagerToFile() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(saveFile, LogManager.getInstance());
            System.out.println(saveFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
