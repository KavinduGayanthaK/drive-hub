package lk.ijse.driveHub.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class NavigationBarFormController {
    @FXML
    private JFXButton reportBtn;

    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton dashBoardBtn;

    @FXML
    private JFXButton reservationBtn;

    @FXML
    private JFXButton vehicleBtn;
    @FXML
    private JFXButton settingBtn;
    public void initialize() throws IOException {

        dashBoardBtnOnAction(null);
    }
    void setForms(String forms) throws IOException {
        String[] form = {
                "/view/dashboard_form.fxml",
                "/view/manageVehicle_form.fxml",
                "/view/manageReservation_form.fxml",
                "/view/report_form.fxml",
                "/view/setting_form.fxml"
        };
        JFXButton[] btn = {dashBoardBtn,vehicleBtn,reservationBtn,reportBtn,settingBtn};

        AnchorPane load = FXMLLoader.load((getClass().getResource(forms)));
        root.getChildren().clear();
        root.getChildren().add(load);

        for (int i = 0; i < form.length; i++) {
            btn[i].setStyle("-fx-background-color:linear-gradient(to right,#00B7D8, #188AF0); -fx-font-size: 18");
            if (forms.equals(form[i])) {
                btn[i].setStyle("-fx-background-color:linear-gradient(to right,#188AF0,#00B7D8)");
            }
            
        }
    }
    @FXML
    void dashBoardBtnOnAction(ActionEvent event) throws IOException {
        setForms("/view/dashboard_form.fxml");

    }

    @FXML
    void manageReservationBtnOnAction(ActionEvent event) throws IOException {
        setForms("/view/manageReservation_form.fxml");
    }

    @FXML
    void manageVehicleBtnOnAction(ActionEvent event) throws IOException {
        setForms("/view/manageVehicle_form.fxml");
    }
    @FXML
    void settingBtnOnAction(ActionEvent event) throws IOException {
        setForms("/view/setting_form.fxml");
    }
    @FXML
    void reportBtnOnAction(ActionEvent event) throws IOException {
        setForms("/view/report_form.fxml");
    }
}
