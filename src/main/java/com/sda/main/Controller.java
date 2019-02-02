package com.sda.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
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

public class Controller implements Initializable {
    private Logger logger = LogManager.getLogger(Controller.class);

    @FXML
    Parent mainPane;
    @FXML
    ScrollPane mainScrollPane;

    //welcome
    @FXML
    Label usernameLabel;

    //notes
    @FXML
    Label visibleNoteLabel;

    //calories
    @FXML
    PieChart caloriesPieChart;
    @FXML
    Label caloriesAbsorbedLabel;
    @FXML
    TextField newCaloriesTextField;

    //weather
    @FXML
    Label wLocalizationLabel;
    @FXML
    Label wTheTempLabel;
    @FXML
    Label wMinTempLabel;
    @FXML
    Label wMaxTempLabel;
    @FXML
    ImageView wImageView;

    //events
    @FXML
    ListView<String> eventsTodayListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();

        mainScrollPane.prefWidthProperty().bind(((Pane) mainPane).widthProperty());
        mainScrollPane.prefHeightProperty().bind(((Pane) mainPane).heightProperty());

        eventsTodayListView.getItems().add("Mary's birthday.");
        eventsTodayListView.getItems().add("Annual meeting.");
        caloriesPieChart.setStartAngle(90);

    }


    public void loadSettings(MouseEvent mouseEvent) {
        URL resource = getClass().getClassLoader().getResource("settings.fxml");
        if (resource != null) {
            try {
                Pane settingsPane = FXMLLoader.load(resource);
                Stage stage = (Stage) mainPane.getScene().getWindow();
                stage.setScene(new Scene(settingsPane, 600, 600));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadData() {
        Integer absorbed = 0;
        this.caloriesAbsorbedLabel.setText(absorbed.toString());
        ObservableList<PieChart.Data> caloriesData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Absorbed", absorbed),
                        new PieChart.Data("Pending", 1500 - absorbed));
        this.caloriesPieChart.setData(caloriesData);
    }

}
