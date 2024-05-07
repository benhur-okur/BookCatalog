package com.example.bookcatalog;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class SelectingFileController {
    @FXML
    private Button runBT;
    private MainScreenController mainScreenController;
    private Stage currentStage;

    public void setMainScreenControllerAndStage(MainScreenController mainScreenController, Stage stage) {
        this.mainScreenController = mainScreenController;
        currentStage = stage;
    }


    @FXML
    private void selectExistingJsonFile () {
        mainScreenController.hasSelectedJSONFile = false;
        // Dosya seçiciyi oluştur
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select JSON File");

        // JSON dosyası filtresi ekle (opsiyonel)
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);

        // Kullanıcıdan dosyayı seçmesini iste
        MainScreenController.selectedFile = fileChooser.showOpenDialog(null);

        // Kullanıcı dosyayı seçti mi kontrol et
        if (MainScreenController.selectedFile != null) {
            System.out.println("Selected file: " + MainScreenController.selectedFile.getAbsolutePath());

            // Burada seçilen JSON dosyasını işlemek için gerekli adımları gerçekleştirebilirsiniz
            // Örneğin, dosyayı okuyup içeriğini işleyebilirsiniz
            mainScreenController.hasSelectedJSONFile = true;
        } else {
            System.out.println("No file selected.");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("You didn't choose a JSON File \n" +
                    "You can manually try select a FILE From : \n" +
                    "File -> Open (TOP LEFT CORNER)");
            alert.showAndWait();
        }
    }
    @FXML
    private void createNewJSONFile () {
        mainScreenController.hasSelectedJSONFile = false;
        String directoryPath = "src/JSON_files";

        // Kullanıcıdan dosya adını al
        TextInputDialog dialog = new TextInputDialog("new_file");
        dialog.setTitle("New JSON File");
        dialog.setHeaderText("Enter the name for the new JSON file:");
        dialog.setContentText("File Name:");
        dialog.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.trim().isEmpty()) {
                dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(true);
            } else {
                dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(false);
            }
        });
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(fileName -> {
            // JSON dosyasının tam yolu
            String filePath = directoryPath + File.separator + fileName + ".json";

            try {
                // Dosyayı oluştur
                MainScreenController.selectedFile = new File(filePath);
                boolean fileCreated = MainScreenController.selectedFile.createNewFile();

                if (fileCreated) {
                    System.out.println("New JSON file created: " + filePath);
                    mainScreenController.hasSelectedJSONFile = true;
                } else {
                    System.out.println("Failed to create new JSON file.");
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Hata durumunda kullanıcıya bilgi ver
                // ...
            }
        });
    }
    @FXML
    private void run () {
        if (mainScreenController.hasSelectedJSONFile) {
            currentStage.close();
        }
    }

}
