package lk.ijse.driveHub.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.driveHub.dto.UserDto;
import lk.ijse.driveHub.model.UserModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginFormController {
    @FXML
    private JFXButton loginBtn;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;
    @FXML
    private Label lblPasswordInvalied;

    @FXML
    private Label lblUserNameInvalied;

    private UserModel userModel = new UserModel();

    @FXML
    void logginBtnOnAction(ActionEvent event) throws SQLException, IOException {

        String username = txtUserName.getText();
        String password = txtPassword.getText();

         List<UserDto> userDtoList = userModel.loginUser();
        for (UserDto userDto:userDtoList) {
            if (userDto.getUserName().equals(username)) {
                if (userDto.getPassword().equals(password)) {
                    Stage stage = new Stage();
                    stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/navigationBar_form.fxml"))));
                    stage.setTitle("DRIVE HUB");
                    stage.show();
                    stage = (Stage) loginBtn.getScene().getWindow();
                    stage.close();
                }else {
                    lblPasswordInvalied.setText("Invalid Password");
                }
            }else {
                lblUserNameInvalied.setText("Invalid Username");
            }
        }

  }
}
