package lk.ijse.driveHub.controller.popupWindowVehicle;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;


public class CancelFormController {
    @FXML
    private JFXButton cancelBtn;

    @FXML
    private JFXButton continueBtn;



    @FXML
    void cancelBtnOnAction(ActionEvent event) throws IOException {
       Stage stage1 = (Stage) cancelBtn .getScene().getWindow();
       stage1.close();



    }

    @FXML
    void continueBtnOnActon(ActionEvent event) {
        Stage stage =(Stage)continueBtn.getScene().getWindow();
        stage.close();
    }
}
