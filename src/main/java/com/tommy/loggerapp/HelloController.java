package com.tommy.loggerapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class HelloController {

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
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        LogManager lm = LogManager.getInstance();
        System.out.println(lm.toString());
    }

    @FXML
    protected void onSaveButtonClick() {
        Saver.getInstance().saveLogManagerToFile();
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

        el.getChildren().add(new Label(field.getPrompt()));

        TextArea ta = new TextArea(field.getResponse());
        ta.setUserData(field);
        ta.setWrapText(true);

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
}
