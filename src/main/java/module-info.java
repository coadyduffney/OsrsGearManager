module com.osrsgearmanager {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.osrsgearmanager to javafx.fxml;
    exports com.osrsgearmanager;
}