module com.example.bookcatalog {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.bookcatalog to javafx.fxml;
    exports com.example.bookcatalog;
}