module com.tommy.loggerapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires tools.jackson.databind;
    requires tools.jackson.core;

    opens com.tommy.loggerapp to javafx.fxml;
    exports com.tommy.loggerapp;
}