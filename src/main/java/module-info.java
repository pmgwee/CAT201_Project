module com.example.cat201_project {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.cat201_project to javafx.fxml;
    exports com.example.cat201_project;
}