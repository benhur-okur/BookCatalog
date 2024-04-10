module com.example.bookcatalog {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.desktop;


    opens com.example.bookcatalog to javafx.fxml;
    exports com.example.bookcatalog;
}