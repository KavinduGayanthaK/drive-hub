package lk.ijse.driveHub.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.driveHub.dto.UserDto;
import lk.ijse.driveHub.model.UserModel;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {
    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;
    private UserModel userModel = new UserModel();

    @FXML
    void loginBtnOnAction(ActionEvent event) throws SQLException, IOException {

        String username = txtUserName.getText();
        String password = txtPassword.getText();
        UserDto userDto = new UserDto(username,password);

        boolean isChecked = userModel.loginUser(userDto);
        if (!isChecked) {
            new Alert(Alert.AlertType.ERROR,"Login successful!");
        }else{
            Stage stage = null;
            stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/navigationBar_form.fxml"))));
            stage.setTitle("DRIVE HUB");
            stage.show();
        }

    }
}
