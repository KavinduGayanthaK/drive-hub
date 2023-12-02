package lk.ijse.driveHub.controller.popupWindowVehicle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.driveHub.controller.closeWindow.CloseWindow;
import lk.ijse.driveHub.controller.openWindow.PopupWindows;
import lk.ijse.driveHub.dto.VehicleDto;
import lk.ijse.driveHub.dto.VehicleOwnerDto;
import lk.ijse.driveHub.dto.VehicleTypeDto;
import lk.ijse.driveHub.model.VehicleOwnerModel;
import lk.ijse.driveHub.model.VehicleTypeModel;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

import static lk.ijse.driveHub.controller.popupWindowVehicle.VehicleOwnerFormController.vehicleOwnerDto;

public class AddVehicleFormController implements Initializable {

    @FXML
    private JFXButton cancelBtn;
    @FXML
    private JFXCheckBox chbVehicleBook;
    @FXML
    private ComboBox<String> cmbTransmissionType;
    @FXML
    private ComboBox<String> cmbVehicleBrand;
    @FXML
    private ComboBox<String> cmbVehicleModel;
    @FXML
    private ComboBox<String> cmbVehicleType;
    @FXML
    private JFXButton nextBtn;
    @FXML
    private JFXCheckBox ownerCheckBox;
    @FXML
    private DatePicker txtManufactureYear;
    @FXML
    private TextField txtOwnerSearch;
    @FXML
    private TextField txtPerAdditionalKmRate;
    @FXML
    private TextField txtPerDateRate;
    @FXML
    private TextField txtPerDayKm;
    @FXML
    private TextField txtRegisterNumber;

    private String chbOwnerValue = "no";
    private String chbVehicleBookValue;

    static VehicleDto vehicleDto = new VehicleDto();
    VehicleTypeDto vehicleTypeDto = new VehicleTypeDto();
    VehicleTypeModel vehicleTypeModel = new VehicleTypeModel();
    VehicleOwnerModel vehicleOwnerModel = new VehicleOwnerModel();

    static Stage addVehicleFormController = new Stage();
    AddLicenseFormController addLicenseFormController = new AddLicenseFormController();
    private List<VehicleOwnerDto> vehicleOwnerDtoList = vehicleOwnerModel.getAllOwner();

    private Set<String> vehicleOwners = new HashSet<>();

    public AddVehicleFormController() throws SQLException {
    }

    @FXML
    void cancelBtnOnAction(ActionEvent event) {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION,
                "Are you want to cancel add vehicle ?", yes, no).showAndWait();
        if (type.orElse(no) == yes) {
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void nextBtnOnAction(ActionEvent event) throws SQLException, IOException {
        setValues();
        URL resource = this.getClass().getResource("/view/popupWindowVehicle/addLicense_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("License Details");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
        addLicenseFormController.add(addVehicleFormController,stage);
        addVehicleFormController.close();
    }


    @FXML
    void ownerCheckBoxOnAction(ActionEvent event) {
        if (ownerCheckBox.isSelected()) {
            chbOwnerValue = "yes";
            txtOwnerSearch.setVisible(true);
        }else {
            chbOwnerValue = "no";
            txtOwnerSearch.setVisible(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (chbOwnerValue.equals("no")) {
            txtOwnerSearch.setVisible(false);
        }else {
            txtOwnerSearch.setVisible(true);
        }
        loadAllVehicleType();
        cmbVehicleType.setValue(vehicleTypeDto.getName());
        cmbVehicleBrand.setValue(vehicleDto.getBrand());
        txtManufactureYear.setValue(vehicleDto.getManufactureYear());
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

        vehicleOwnerDtoList.forEach(vehicleOwnerDto -> {
            vehicleOwners.add(vehicleOwnerDto.getNic());
        });
        TextFields.bindAutoCompletion(txtOwnerSearch,vehicleOwners);



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
        vehicleDto.setManufactureYear(txtManufactureYear.getValue());
        vehicleDto.setIsCollectedBookCopy(chbVehicleBookValue);
        vehicleDto.setRegisterNumber(txtRegisterNumber.getText());
        vehicleDto.setPerDayKm(Double.parseDouble(txtPerDayKm.getText()));
        vehicleDto.setPerDayRate(Double.parseDouble(txtPerDateRate.getText()));
        vehicleDto.setPerAdditionalKmRate(Double.parseDouble(txtPerAdditionalKmRate.getText()));
        VehicleOwnerDto vehicleOwnerDtoId = new VehicleOwnerDto();
        for (VehicleOwnerDto vehicleOwnerDtos: vehicleOwnerDtoList) {
            if (txtOwnerSearch.getText().equals(vehicleOwnerDtos.getNic())) {
                vehicleOwnerDtoId.setId(vehicleOwnerDtos.getId());
                vehicleDto.setOwnerId(vehicleOwnerDtoId.getId());
            }
        }
    }

    @FXML
    void chbVehicleBookOnAction(ActionEvent event) {
        if (chbVehicleBook.isSelected()) {
            chbVehicleBookValue = "Yes";
        }else {
            chbVehicleBookValue = "No";
        }
    }

    public ObservableList<String> setCmbVehicleModelToyota() {
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

    public void add(Stage stage) {
        addVehicleFormController = stage;
    }
}
