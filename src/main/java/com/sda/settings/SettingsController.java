package com.sda.settings;

import com.sda.ConfigDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    private Logger logger = LogManager.getLogger(SettingsController.class);

    @FXML
    Parent settingsPane;

    @FXML
    Slider slider;

    @FXML
    TextField localization;
    @FXML
    TextField userName;
    @FXML
    Label calories;


    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        this.calories.setText(String.valueOf(slider.getValue()));
        this.slider.valueProperty().addListener( (observable, oldValue, newValue) -> calories.setText(String.valueOf(newValue.intValue())));
    }

    public void loadMain(MouseEvent mouseEvent) {
       saveName();
       saveLocalization();
       saveSlider();

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

    public void saveName(ConfigDTO configDTO){
        Properties properties = new Properties();
        try{
            FileWriter fw = new FileWriter("com/sda/save/config.properties");
            properties.setProperty("userName", configDTO.getUserName());
            properties.store(fw, "");
            fw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void saveLocalization(ConfigDTO configDTO){
        Properties properties2 = new Properties();
        try{
            FileWriter sl = new FileWriter("com/sda/save/config.properties");
            properties2.setProperty("localization", configDTO.getLocalization());
            properties2.store(sl, "");
        } catch (IOException e){
            e. printStackTrace();
        }
    }

    public void saveSlider (ConfigDTO configDTO){
        Properties properties1 =  new Properties();
        try{
            FileWriter sw = new FileWriter("com/sda/save/config.properties");
            properties1.setProperty("slider", configDTO.getCaloriesLimit());
            properties1.store(sw, "");
        } catch (IOException e){
            e.printStackTrace();
        }
    }



    public void saveSettings(MouseEvent mouseEvent) {
        ConfigDTO configDTO = new ConfigDTO(userName.getText(), localization.getText(), slider.getAccessibleText());
        PropertiesManager pm = new PropertiesManager();
        pm.updateConfigProperties(configDTO);
        UpdateConfigProperties ucp = new UpdateConfigProperties();
        ucp.updateConfigProperties(configDTO);

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
}
