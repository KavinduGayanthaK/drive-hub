package lk.ijse.driveHub.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.driveHub.dto.UserDto;
import lk.ijse.driveHub.model.UserModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterUserFormController implements Initializable {
    @FXML
    private ComboBox<String> cmbType;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    private JFXButton registerBtn;

    UserDto userDto = new UserDto();
    UserModel userModel = new UserModel();
    @FXML
    void RegisterBtnOnAction(ActionEvent event)  {
        userDto.setId(0);
        userDto.setUserName(txtUserName.getText());
        userDto.setPassword(txtPassword.getText());
        userDto.setEmail(txtEmail.getText());
        userDto.setType(cmbType.getValue());

        try {

            boolean registerUser = userModel.saveUser(userDto);
            if (registerUser) {
                Stage stage = (Stage) registerBtn.getScene().getWindow();
                stage.close();
                new Alert(Alert.AlertType.INFORMATION,"Register Successfully").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbType.setItems(FXCollections.observableArrayList("Admin","Cashier"));
    }
}
