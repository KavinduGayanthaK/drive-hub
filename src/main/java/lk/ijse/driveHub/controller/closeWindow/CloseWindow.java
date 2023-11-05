package lk.ijse.driveHub.controller.closeWindow;

import com.jfoenix.controls.JFXButton;
import javafx.stage.Stage;

public class CloseWindow {
    public void closeWindow(JFXButton button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

}
