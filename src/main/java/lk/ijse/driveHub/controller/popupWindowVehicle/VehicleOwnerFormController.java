package lk.ijse.driveHub.controller.popupWindowVehicle;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.driveHub.controller.openWindow.PopupWindows;

import java.io.IOException;
import java.net.URL;


public class VehicleOwnerFormController {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton nextBtn;
    @FXML
    private JFXButton closeBtn;

    @FXML
    void cancelBtnOnAction(ActionEvent event) throws IOException {
//        CancelFormController cancelFormController = new CancelFormController();
//        cancelFormController.cancel();
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
    void nextBtnOnAction(ActionEvent event) throws IOException {
        PopupWindows popupWindows = new PopupWindows();
        popupWindows.window("/view/popupWindowVehicle/vehicle_form.fxml","Vehicle Owner");

        Stage stage = (Stage) nextBtn.getScene().getWindow();
        stage.close();
    }
}
