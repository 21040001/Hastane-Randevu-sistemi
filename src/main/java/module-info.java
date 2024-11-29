module com.Hastane.RezerveSystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.context;
    requires spring.core;
    requires spring.beans;
    requires java.sql;
    opens com.Hastane.RezerveSystem to javafx.graphics, spring.core;
    opens com.Hastane.RezerveSystem.View to javafx.fxml;
    exports com.Hastane.RezerveSystem;
}
