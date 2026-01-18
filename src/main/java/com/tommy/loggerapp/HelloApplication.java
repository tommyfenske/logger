package com.tommy.loggerapp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        System.out.println("Starting Application");
        Saver.getInstance().loadLogManagerFromFile();

        this.stage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 360);
        stage.setTitle("Logger");
        stage.setScene(scene);
        stage.show();

        if (fxmlLoader.getController() instanceof HelloController c) {
            c.setStage(stage);
        };
    }

    public void printMessage(String message){
        System.out.println(message);
    }

}
