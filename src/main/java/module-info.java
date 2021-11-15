module com.osrsgearmanager {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.google.gson;

    opens com.osrsgearmanager to javafx.fxml;
    exports com.osrsgearmanager;
}