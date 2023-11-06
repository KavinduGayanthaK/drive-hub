package lk.ijse.driveHub.controller.popupWindowVehicle;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


public class CancelFormController {
    @FXML
    private JFXButton cancelBtn;

    @FXML
    private JFXButton continueBtn;
    private String file;
    public void cancel() throws IOException {
        //this.file = file;

        URL resource = this.getClass().getResource("/view/popupWindowVehicle/cancel_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Cancel");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }
    @FXML
    void cancelBtnOnAction(ActionEvent event) throws IOException {
       Stage stage = (Stage) cancelBtn .getScene().getWindow();
       stage.close();



    }

    @FXML
    void continueBtnOnActon(ActionEvent event) {
        Stage stage =(Stage)continueBtn.getScene().getWindow();
        stage.close();
    }
}
