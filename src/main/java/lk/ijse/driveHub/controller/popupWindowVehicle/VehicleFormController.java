package lk.ijse.driveHub.controller.popupWindowVehicle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.driveHub.dto.VehicleDto;
import lk.ijse.driveHub.dto.VehicleOwnerDto;
import lk.ijse.driveHub.dto.VehicleTypeDto;
import lk.ijse.driveHub.model.VehicleTypeModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class VehicleFormController implements Initializable {
    @FXML
    private JFXButton backBtn;
    @FXML
    private JFXCheckBox chbVehicleBook;
    @FXML
    private JFXButton cancelBtn;
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

    @FXML
    private ComboBox<String> cmbVehicleBrand;

    @FXML
    private ComboBox<String> cmbVehicleModel;
    @FXML
    private TextField txtPerAdditionalKmRate;
    @FXML
    private TextField txtPerDateRate;
    @FXML
    private TextField txtPerDayKm;

    private String chbVehicleBookValue;

    static Stage vehicleFormController = new Stage();
    LicenseFormController licenseFormController = new LicenseFormController();
    static VehicleDto vehicleDto = new VehicleDto();
    VehicleOwnerDto vehicleOwnerDto = new VehicleOwnerDto();
    VehicleTypeDto vehicleTypeDto = new VehicleTypeDto();
    VehicleTypeModel vehicleTypeModel = new VehicleTypeModel();


    @FXML
    void backBtnOnAction(ActionEvent event) throws IOException, SQLException {
        setValues();
        VehicleOwnerFormController.vehicleOwnerFormController.show();
        vehicleFormController.close();
    }
    @FXML
    void closeBtnOnAction(ActionEvent event) throws IOException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION,
                "Are you want to cancel vehicle onboarding process ?", yes, no).showAndWait();
        if (type.orElse(no) == yes) {
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
        }
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

    public void setValues() throws SQLException {

        List<VehicleTypeDto> typeDtoList = vehicleTypeModel.getAllVehicleType();
        for (VehicleTypeDto vehicleTypeDtoList:typeDtoList) {
            if (cmbVehicleType.getValue().equals(vehicleTypeDtoList.getName())) {
                vehicleTypeDto.setId(vehicleTypeDtoList.getId());
                vehicleTypeDto.setName(vehicleTypeDtoList.getName());
            }

        }
        vehicleDto.setId(0);
        vehicleDto.setVehicleTypeId(vehicleTypeDto.getId());
        vehicleDto.setBrand(cmbVehicleBrand.getValue());
        vehicleDto.setModel(cmbVehicleModel.getValue());
        vehicleDto.setTransmissionType(cmbTransmissionType.getValue());
        vehicleDto.setManufactureYear(dateManufactureYear.getValue());
        vehicleDto.setIsCollectedBookCopy(chbVehicleBookValue);
        vehicleDto.setRegisterNumber(txtRegisterNumber.getText());
        vehicleDto.setPerDayKm(Double.parseDouble(txtPerDayKm.getText()));
        vehicleDto.setPerDayRate(Double.parseDouble(txtPerDateRate.getText()));
        vehicleDto.setPerAdditionalKmRate(Double.parseDouble(txtPerAdditionalKmRate.getText()));
        vehicleDto.setOwnerId(vehicleOwnerDto.getId());
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
        loadAllVehicleType();
        cmbVehicleType.setValue(vehicleTypeDto.getName());
        cmbVehicleBrand.setValue(vehicleDto.getBrand());
        dateManufactureYear.setValue(vehicleDto.getManufactureYear());
        cmbTransmissionType.setValue(vehicleDto.getTransmissionType());
        cmbVehicleModel.setValue(vehicleDto.getModel());
        txtRegisterNumber.setText(vehicleDto.getRegisterNumber());
        cmbTransmissionType.setItems(FXCollections.observableArrayList("Auto","Manual"));
        cmbVehicleBrand.setItems(FXCollections.observableArrayList("TOYOTA","AUDI","HONDA"));
        txtPerDateRate.setText(String.valueOf(vehicleDto.getPerDayRate()));
        txtPerDayKm.setText(String.valueOf(vehicleDto.getPerDayKm()));
        txtPerAdditionalKmRate.setText(String.valueOf(vehicleDto.getPerAdditionalKmRate()));

        cmbVehicleBrand.setOnAction(e-> {
            if (cmbVehicleBrand.getSelectionModel().getSelectedItem().equals("TOYOTA")) {
                setCmbVehicleModelToyota();
            }
            if (cmbVehicleBrand.getSelectionModel().getSelectedItem().equals("HONDA")) {
                setCmbVehicleModelHonda();
            }
            if (cmbVehicleBrand.getSelectionModel().getSelectedItem().equals("AUDI")) {
                setCmbVehicleModelAudi();
            }
        });

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
    @FXML
    void cmbVehicleBrandOnAction(ActionEvent event) {

        if(cmbVehicleBrand.getSelectionModel().getSelectedItem().equals("TOYOTA")) {
            cmbVehicleModel.setItems(setCmbVehicleModelToyota());
        }
    }

    @FXML
    void cmbVehicleModelOnAction(ActionEvent event) {

    }

    private void setCmbVehicleModelSetValues() {

    }

    public  ObservableList<String> setCmbVehicleModelToyota() {
        cmbVehicleModel.setItems(FXCollections.observableArrayList(
                "Corolla HatchBack",
                "CHR",
                "Vitz",
                "Yaris ATIV",
                "Wigo",
                "AQUA",
                "PRIUS",
                "Corolla Sedan",
                "AXIO",
                "Allion"));
        return null;
    }

    private ObservableList<String> setCmbVehicleModelHonda() {
        cmbVehicleModel.setItems(FXCollections.observableArrayList(
                "Fit GE6",
                "Fit GP1",
                "Fit GP5",
                "Fit GP1",
                "FIT Shuttle",
                "Insight",
                "Grace EX",
                "Grace",
                "Civic FB1",
                "Dio"));
        return null;
    }

    private ObservableList<String> setCmbVehicleModelAudi() {
        cmbVehicleModel.setItems(FXCollections.observableArrayList(
                "A3 Sedan",
                "A4 Sedan",
                "A6 Saloon",
                "A8 L",
                "Q2",
                "Q3",
                "Q5",
                "Q7",
                "TT Coupe",
                "TTS Coupe",
                "A5 Sportback",
                "A7 Sportback"));
        return null;
    }

    public static void clearDto() {
        vehicleDto.setId(0);
        vehicleDto.setVehicleTypeId(0);
        vehicleDto.setOwnerId(0);
        vehicleDto.setModel(null);
        vehicleDto.setBrand(null);
        vehicleDto.setRegisterNumber(null);
        vehicleDto.setIsCollectedBookCopy(null);
        vehicleDto.setPerDayKm(0);
        vehicleDto.setPerDayRate(0);
        vehicleDto.setPerAdditionalKmRate(0);
        vehicleDto.setManufactureYear(null);
        vehicleDto.setTransmissionType(null);
    }
}
