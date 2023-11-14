package lk.ijse.driveHub.controller.popupWindowVehicle;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.driveHub.controller.openWindow.PopupWindows;
import lk.ijse.driveHub.dto.VehicleDto;
import lk.ijse.driveHub.dto.VehicleOwnerDto;
import lk.ijse.driveHub.dto.VehicleTypeDto;
import lk.ijse.driveHub.model.VehicleTypeModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class VehicleFormController implements Initializable {
    @FXML
    private JFXButton backBtn;
    @FXML
    private CheckBox chbVehicleBook;
    @FXML
    private JFXButton closeBtn;
    @FXML
    private ComboBox<String> cmbTransmissionType;
    @FXML
    private ComboBox<String> cmbVehicleType;
    @FXML
    private JFXButton nextBtn;
    @FXML
    private DatePicker dateManufactureYear;
    @FXML
    private TextField txtRegisterNumber;
    @FXML
    private TextField txtVehicleBrand;
    @FXML
    private TextField txtVehicleModel;

    private String chbVehicleBookValue;
    PopupWindows popupWindows = new PopupWindows();
    static Stage vehicleFormController = new Stage();
    static LicenseFormController licenseFormController = new LicenseFormController();
    static VehicleDto vehicleDto = new VehicleDto();
    VehicleOwnerDto vehicleOwnerDto = new VehicleOwnerDto();
    VehicleTypeDto vehicleTypeDto = new VehicleTypeDto();
    VehicleTypeModel vehicleTypeModel = new VehicleTypeModel();
    private int id;

    @FXML
    void backBtnOnAction(ActionEvent event) throws IOException, SQLException {
        setValues();
        VehicleOwnerFormController.vehicleOwnerFormController.show();
        vehicleFormController.close();
    }
    @FXML
    void closeBtnOnAction(ActionEvent event) throws IOException {
        popupWindows.window("/view/popupWindowVehicle/cancel_form.fxml","Cancel Form");

    }

    @FXML
    void nextBtnOnAction(ActionEvent event) throws IOException, SQLException {
        setValues();
        URL resource = this.getClass().getResource("/view/popupWindowVehicle/license_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("License Details");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
        licenseFormController.add(vehicleFormController,stage);
        vehicleFormController.close();
    }

    private void setValues() throws SQLException {
        //vehicleTypeDto.setName(cmbVehicleType.getValue());
        List<VehicleTypeDto> typeDtoList = vehicleTypeModel.getAllVehicleType();
        for (VehicleTypeDto vehicleTypeDtoList:typeDtoList) {
            if (cmbVehicleType.getValue().equals(vehicleTypeDtoList.getName())) {
                vehicleTypeDto.setId(vehicleTypeDtoList.getId());
                vehicleTypeDto.setName(vehicleTypeDtoList.getName());
            }

        }

        vehicleDto.setId(0);
        vehicleDto.setVehicleTypeId(vehicleTypeDto.getId());
        vehicleDto.setBrand(txtVehicleBrand.getText());
        vehicleDto.setModel(txtVehicleModel.getText());
        vehicleDto.setTransmissionType(cmbTransmissionType.getValue());
        vehicleDto.setManufactureYear(dateManufactureYear.getValue());
        vehicleDto.setIsCollectedBookCopy(chbVehicleBookValue);
        vehicleDto.setRegisterNumber(txtRegisterNumber.getText());
        vehicleDto.setOwnerId(id);
    }
    @FXML
    void chbVehicleBookCopyOnAction(ActionEvent event) {
        if (chbVehicleBook.isSelected()) {
            chbVehicleBookValue = "Yes";
        }else {
            chbVehicleBookValue = "No";
        }
    }

    public void add(Stage vehicleOwnerFormController,Stage vehicleFormStage) {
        VehicleOwnerFormController.vehicleOwnerFormController = vehicleOwnerFormController;
        vehicleFormController = vehicleFormStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbVehicleType.setValue(vehicleTypeDto.getName());
        txtVehicleBrand.setText(vehicleDto.getBrand());
        dateManufactureYear.setValue(vehicleDto.getManufactureYear());

        cmbTransmissionType.setValue(vehicleDto.getTransmissionType());
        txtVehicleModel.setText(vehicleDto.getModel());
        txtRegisterNumber.setText(vehicleDto.getRegisterNumber());
        loadAllVehicleType();
        cmbTransmissionType.setItems(FXCollections.observableArrayList("Auto","Manual"));

    }

    private void loadAllVehicleType() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        try {
            List<VehicleTypeDto> vehicleType = vehicleTypeModel.getAllVehicleType();
            for (VehicleTypeDto vehicleTypeDto: vehicleType) {
                observableList.add(vehicleTypeDto.getName());
            }
            cmbVehicleType.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void setNewOwnerId(int ownerId) {
        id = ownerId;
    }

}
