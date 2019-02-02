package com.sda.settings;

import com.sda.files.ConfigDTO;
import com.sda.files.PropertiesManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    private Logger logger = LogManager.getLogger(SettingsController.class);

    @FXML
    Parent settingsPane;
    //welcome
    @FXML
    Slider slider;

    @FXML
    TextField userName;

    @FXML
    TextField localization;

    @FXML
    Label calories;


    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        this.slider.valueProperty().addListener(
                (observable, oldValue, newValue) ->
                        calories.setText(String.valueOf(newValue.intValue())));


    }

    public void loadMain(MouseEvent mouseEvent) {
        updateProperties();
        URL resource = getClass().getClassLoader().getResource("main.fxml");
        if (resource != null) {
            try {
                Pane mainPane = FXMLLoader.load(resource);
                Stage stage = (Stage) settingsPane.getScene().getWindow();
                stage.setScene(new Scene(mainPane, 600, 600));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();

            }

        }
    }

    private void updateProperties() {
        ConfigDTO configDTO = new ConfigDTO(userName.getText(), localization.getText(), slider.getValue());
        PropertiesManager saveConfigDTO = new PropertiesManager();
        saveConfigDTO.updateConfigProperties(configDTO);
    }
}