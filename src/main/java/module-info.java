module com.example.clean {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.clean to javafx.fxml;
    exports com.example.clean;
}