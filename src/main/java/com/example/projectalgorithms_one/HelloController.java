package com.example.projectalgorithms_one;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    public ComboBox<String> algo;

    @FXML
    public ComboBox<String> sortBy;

    @FXML
    public Button select;

    @FXML
    public Button save;

    @FXML
    public GridPane disp;

    @FXML
    public Button sortData;

    @FXML
    public TextArea TextArea;

    private Student[] studentsMarge;

    private Student[] studentsQuick;

    private String path;

    public void setPath(String path) {
        this.path = path;
    }

    public void selected() {
        select.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV File", "*.csv"), new FileChooser.ExtensionFilter("All Files", "*.*"));
            File selectedFile = fileChooser.showOpenDialog(new Stage());
            if (selectedFile != null) {
                setPath(selectedFile.getAbsolutePath());
                try {
                    dispData(FileHandling.readData(path));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                TextArea.setText("تمت عملية استيراد البيانات");
            }
        });
    }

    public void dispData(Student[] students) throws IOException {
        disp.getChildren().clear();

        for (int row = 0; row < students.length; row++) {
            Label LabelOfIndexOne = new Label(String.valueOf(students[row].getId()));
            Label LabelOfIndexTwo = new Label(students[row].getName());
            Label LabelOfIndexThree = new Label(String.valueOf(students[row].getAverage()));

            LabelOfIndexOne.setPadding(new Insets(5, 10, 5, 10));
            LabelOfIndexTwo.setPadding(new Insets(5, 10, 5, 10));
            LabelOfIndexThree.setPadding(new Insets(5, 10, 5, 10));

            disp.add(LabelOfIndexOne, 0, row);
            disp.add(LabelOfIndexTwo, 1, row);
            disp.add(LabelOfIndexThree, 2, row);

        }
    }

    public Comparator<Student> compare() throws IOException {
        String sort = sortBy.getValue();

        if (sortBy == null) {
            throw new IOException("تأكد من تحديد آلية الترتيب");
        }

        switch (sort) {
            case "By ID":
                return new Compare_ById();
            case "By Name":
                return new Compare_ByName();
            case "By Average":
                return new Compare_ByAverage();
            default:
                throw new IllegalArgumentException("Invalid sort criteria: " + sortBy);
        }
    }


    public void sort() throws IOException {
        Student[] students = FileHandling.readData(path);
        String algorithm = algo.getValue();

        switch (algorithm) {
            case "Marge Sort":
                studentsMarge = SortUtil.mergeSort(students, compare());
                dispData(studentsMarge);
                break;
            case "Quick Sort":
                studentsQuick = SortUtil.quickSort(students, compare());
                dispData(studentsQuick);
                break;
        }
    }

    public void sortData() {
        sortData.setOnAction(e -> {
            try {
                sort();
                TextArea.setText("تم ترتيب البيانات بنجاح");
            } catch (IOException ex) {
                TextArea.setText(ex.getMessage());
            }
        });
    }

    public void saveData() {
        save.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV File", "*.csv"), new FileChooser.ExtensionFilter("All Files", "*.*"));
            File saveFile = fileChooser.showSaveDialog(new Stage());

            String algorithm = algo.getValue();

            if (saveFile != null) {
                setPath(saveFile.getAbsolutePath());
                try {
                    switch (algorithm) {
                        case "Marge Sort":
                            FileHandling.writeData(studentsMarge, path);
                            break;
                        case "Quick Sort":
                            FileHandling.writeData(studentsQuick, path);
                            break;
                    }
                } catch (IOException ex) {
                    TextArea.setText(ex.getMessage());
                }
            }
        });
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        ObservableList<String> observableList_OfAlgorithmSort = FXCollections.observableArrayList("Marge Sort", "Quick Sort");
        algo.setItems(observableList_OfAlgorithmSort);

        ObservableList<String> observableList_OfSortBy = FXCollections.observableArrayList("By ID", "By Name", "By Average");
        sortBy.setItems(observableList_OfSortBy);

        selected();
        sortData();
        saveData();
    }
}