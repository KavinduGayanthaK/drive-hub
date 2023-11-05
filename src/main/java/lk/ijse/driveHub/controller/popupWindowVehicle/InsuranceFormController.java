package lk.ijse.driveHub.controller.popupWindowVehicle;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class InsuranceFormController {
    @FXML
    private JFXButton closeBtn;

    @FXML
    private JFXButton finishBtn;


    @FXML
    void closeBtnOnAction(ActionEvent event) {

    }

    @FXML
    void finishBtnOnAction(ActionEvent event) {
        Stage stage = (Stage) finishBtn.getScene().getWindow();
        stage.close();

    }
}
