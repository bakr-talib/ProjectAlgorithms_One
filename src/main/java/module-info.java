module com.example.projectalgorithms_one {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projectalgorithms_one to javafx.fxml;
    exports com.example.projectalgorithms_one;
}