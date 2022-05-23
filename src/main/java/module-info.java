module student.utcn.foodmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens prezentation.layer to javafx.fxml;
    exports prezentation.layer;
    exports business.layer;
    opens business.layer to javafx.fxml;
}