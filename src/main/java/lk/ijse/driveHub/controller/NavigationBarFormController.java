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
    private AnchorPane root;
    @FXML
    private JFXButton dashBoardBtn;

    @FXML
    private JFXButton reservationBtn;

    @FXML
    private JFXButton vehicleBtn;
    public void initialize() throws IOException {
        dashBoardBtnOnAction(null);
    }
    void setForms(String forms) throws IOException {
        String[] form= {"/view/dashboard_form.fxml","/view/manageReservation_form.fxml","/view/manageVehicle_form.fxml"};
        JFXButton[] btn = {dashBoardBtn,vehicleBtn,reservationBtn};

        AnchorPane load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(forms)));
        root.getChildren().clear();
        root.getChildren().add(load);

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
}
