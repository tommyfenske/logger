package com.tommy.loggerapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    Stage stage;

    public void initialize() {
        System.out.println("Loading Log Buttons");
        for (Log log : LogManager.getInstance().getLogList()) {
            System.out.println(log);
            logListVBox.getChildren().addFirst(createLogListButton(log));
        }

    }

    @FXML
    public Label logPreviewLabel;
    @FXML
    public VBox logFieldBox;

    @FXML
    private Label welcomeText;

    @FXML
    private VBox logListVBox;


    @FXML
    protected void onSaveButtonClick() {
        Saver.getInstance().saveLogManagerToFile();
    }

    @FXML
    protected void onTemplateButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 360);
        stage.setScene(scene);
    }

    @FXML
    protected void onTodayLogButtonClick() {
        LogManager lm = LogManager.getInstance();
        boolean logAdded = lm.addLog();
        if (logAdded) {
            logListVBox.getChildren().addFirst(createLogListButton(lm.getLogList().getFirst()));
        }
        logSelected(lm.getLogList().getFirst());

    }

    protected Button createLogListButton(Log log) {
        Button el = new Button(log.getDate().toString());
        el.getStyleClass().add("fill-green");
        el.setAlignment(Pos.BASELINE_LEFT);
        el.setUserData(log);
        el.setPrefWidth( logListVBox.getMaxWidth() );

        el.setOnAction(e -> {

            Object eventSource = e.getSource();
            // Check if event is from a button holding a Log as data
            if (eventSource instanceof Button b && b.getUserData() instanceof Log l) {
                logSelected(l);
            }


        });

        return el;
    }

    private void logSelected(Log log) {
        logPreviewLabel.setText(log.getDate().toString());

        logFieldBox.getChildren().clear();
        for (Field field : log.getFields()) {
            logFieldBox.getChildren().add(createFieldVBox(field));
        }
    }
    private VBox createFieldVBox(Field field) {
        VBox el = new VBox();

        Label l = new Label(field.getPrompt());
        l.getStyleClass().add("field-label");
        el.getChildren().add(l);

        TextArea ta = new TextArea(field.getResponse());
        ta.getStyleClass().add("field-text-area");
        ta.getStyleClass().add("green-border");
        ta.setUserData(field);
        ta.setWrapText(true);
        ta.setMinHeight(200);

        ta.setOnKeyTyped( e -> {
            System.out.println( "Text changed" );
            Object eventSource = e.getSource();
            // Check if event is from a text area holding a Field as data
            if (eventSource instanceof TextArea a && a.getUserData() instanceof Field f) {
                f.setResponse(ta.getText());
            }
        });
        el.getChildren().add(ta);

        return el;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        System.out.println(stage);
        //stage.setScene();
    }
}
