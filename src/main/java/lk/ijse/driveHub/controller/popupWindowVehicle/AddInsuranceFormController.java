package lk.ijse.driveHub.controller.popupWindowVehicle;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lk.ijse.driveHub.controller.closeWindow.CloseWindow;
import lk.ijse.driveHub.controller.openWindow.PopupWindows;

import java.io.IOException;

public class AddInsuranceFormController {
    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXButton cancelBtn;

    @FXML
    private JFXButton submitBtn;
    static PopupWindows popupWindows = new PopupWindows();
    static CloseWindow closeWindow = new CloseWindow();

    @FXML
    void backBtnOnAction(ActionEvent event) throws IOException {
        popupWindows.window("/view/popupWindowVehicle/addLicense_from.fxml","License Form");
        closeWindow.closeWindow(backBtn);

    }

    @FXML
    void cancelBtnOnAction(ActionEvent event) throws IOException {

        popupWindows.window("/view/popupWindowVehicle/cancel_form.fxml","Cancel Form");
        closeWindow.closeWindow(cancelBtn);
    }

    @FXML
    void submitBtnOnAction(ActionEvent event) {
        closeWindow.closeWindow(submitBtn);
    }

}
