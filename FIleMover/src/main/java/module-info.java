module com.example.filemover {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.filemover to javafx.fxml;
    exports com.example.filemover;
}