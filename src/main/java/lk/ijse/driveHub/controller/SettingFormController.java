package lk.ijse.driveHub.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.driveHub.dto.UserDto;
import lk.ijse.driveHub.model.UserModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class SettingFormController implements Initializable {

    @FXML
    private ComboBox<String> cmbCurrentUsername;
    @FXML
    private ComboBox<String> cmbCurrentUsername2;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private TextField txtConfirmUserName;

    @FXML
    private TextField txtNewPassword;

    @FXML
    private TextField txtNewUserName;
    @FXML
    private Label lblConfirmPassword;

    @FXML
    private Label lblConfirmUserName;

    @FXML
    private Label lblNewPassword;

    @FXML
    private Label lblNewUserName;

    UserDto userDto = new UserDto();
    UserModel userModel = new UserModel();


    @FXML
    void userNameChangeBtnOnAction(ActionEvent event) {
        userDto.setUserName(cmbCurrentUsername.getValue());
        String newUsername = txtNewUserName.getText();
        boolean isValidateUserName = validateUserNamePassword();
        if (isValidateUserName) {
            if (txtNewUserName.getText().equals(txtConfirmUserName.getText())) {
                try {
                    boolean isUpdate = userModel.updateUserName(userDto,newUsername);
                    if (isUpdate) {
                        new Alert(Alert.AlertType.INFORMATION,"Change Successfully!").show();
                    }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
                }
            }
        }
    }

    @FXML
    void passwordChangeBtnOnAction(ActionEvent event) {
        userDto.setUserName(cmbCurrentUsername2.getValue());
        userDto.setPassword(txtConfirmPassword.getText());
        boolean isValidatePassword = validatePassword();
        if (isValidatePassword)  {
            if (txtNewPassword.getText().equals(txtConfirmPassword.getText())) {
                try {
                    boolean isUpdate = userModel.updateUserPassword(userDto);
                    if (isUpdate) {
                        new Alert(Alert.AlertType.INFORMATION,"Change Successfully!").show();
                    }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
                }
            }
        }
    }

    public void setUserName() {
        //UserDto userDto = new UserDto();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        UserModel userModel = new UserModel();

        try {
            List<UserDto> userDtoList = userModel.loadAllUser();
            for (UserDto userDto: userDtoList) {
                observableList.add(userDto.getUserName());
            }
            cmbCurrentUsername.setItems(observableList);
            cmbCurrentUsername2.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUserName();
    }

    public boolean validateUserNamePassword() {

        boolean isUserNameValidate = Pattern.matches("[A-Za-z]", txtNewUserName.getText());
        if (!isUserNameValidate) {
            lblNewUserName.setText("Invalid Username");
            lblConfirmUserName.setText("Invalid Username");
            return false;
        }
        return true;

    }
    public boolean validatePassword() {
        boolean isPasswordValidate = Pattern.matches("[A-Za-z]",txtNewPassword.getText());
        if (!isPasswordValidate) {
            lblNewPassword.setText("Invalid Password");
            lblConfirmPassword.setText("Invalid Password");
            return false;
        }
        return true;
    }

    @FXML
    void registerNewUserBtnOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/registerUser_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("License Details");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }
}

