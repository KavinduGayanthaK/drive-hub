package lk.ijse.driveHub.controller.tabPane;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import lk.ijse.driveHub.dto.VehicleDto;
import lk.ijse.driveHub.dto.VehicleLicenseDto;
import lk.ijse.driveHub.dto.VehicleOwnerDto;
import lk.ijse.driveHub.dto.tableDto.InsuranceTableDto;
import lk.ijse.driveHub.dto.tableDto.LicenseTableDto;
import lk.ijse.driveHub.dto.tableDto.ReservationVehicleTableDto;
import lk.ijse.driveHub.dto.tableDto.VehicleTableDto;
import lk.ijse.driveHub.model.VehicleInsuranceModel;
import lk.ijse.driveHub.model.VehicleLicenseModel;
import lk.ijse.driveHub.model.VehicleModel;
import lk.ijse.driveHub.model.VehicleOwnerModel;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LicenseInsuranceTabFormController implements Initializable {
    @FXML
    private TableColumn<?, ?> iTblExpiryDateColumn;

    @FXML
    private TableColumn<?, ?> iTblIdColumn;

    @FXML
    private TableColumn<?, ?> iTblIssueDateColumn;

    @FXML
    private TableColumn<?, ?> iTblInsuranceNumberColumn;

    @FXML
    private TableColumn<?, ?> iTblVehicleIDColumn;

    @FXML
    private TableView<InsuranceTableDto> insuranceDetailsTbl;

    @FXML
    private TableColumn<?, ?> lTblExpiryDateColumn1;

    @FXML
    private TableColumn<?, ?> lTblIdColumn1;

    @FXML
    private TableColumn<?, ?> lTblIssueDateColumn1;

    @FXML
    private TableColumn<?, ?> lTblLicenseNumberColumn1;

    @FXML
    private TableColumn<?, ?> lTblVehicleIDColumn1;

    @FXML
    private TableView<LicenseTableDto> licenseDetailsTbl;

    @FXML
    private TextField txtEmail;
    @FXML
    private TextArea txtReson;

    VehicleLicenseModel vehicleLicenseModel = new VehicleLicenseModel();
    VehicleInsuranceModel vehicleInsuranceModel = new VehicleInsuranceModel();
    VehicleModel vehicleModel = new VehicleModel();
    VehicleOwnerModel vehicleOwnerModel = new VehicleOwnerModel();

    @FXML
    void sendBtnOnAction(ActionEvent event) throws Exception {
        String email = txtEmail.getText();
        sendEmail(email);
    }

    private Message prepareMessage(Session session, String myAccountEmail, String recepient) {


        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipients(Message.RecipientType.TO , new InternetAddress[]{
                    new InternetAddress(recepient)
            });

            message.setSubject("License & Insurance expire information");



            message.setText(txtReson.getText());

            return message;
        } catch (Exception e) {
            Logger.getLogger(LicenseInsuranceTabFormController.class.getName()).log(Level.SEVERE,null,e);
        }
        return null;
    }

    public void sendEmail(String recepient) throws Exception {

        Properties properties = new Properties();

        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        String myAccountEmail = "gayanthakavindu3@gmail.com";
        String password = "tksj cpft usvf fewb";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail , password);
            }
        });

        Message message = prepareMessage(session,myAccountEmail,recepient);
        if (message!=null){
            new Alert(Alert.AlertType.CONFIRMATION,"Send Email Successfully").show();
        }else {
            new Alert(Alert.AlertType.WARNING,"Please Enter Recipient's Email Address").show();
        }

        Transport.send(message);
    }

    private void selectToLicenseTable() {
        TableColumn<LicenseTableDto, Void> colBtn = new TableColumn("Action");
        Callback<TableColumn<LicenseTableDto, Void>, TableCell<LicenseTableDto, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<LicenseTableDto, Void> call(final TableColumn<LicenseTableDto, Void> param) {
                final TableCell<LicenseTableDto, Void> cell = new TableCell<LicenseTableDto, Void>() {
                    private JFXButton btn = new JFXButton("SELECT");
                    {
                        btn.setOnAction((ActionEvent event) -> {

                            LicenseTableDto data = getTableView().getItems().get(getIndex());
                            try {
                                boolean isGetOwnerEmail = selectBtnOnActionLtable(data,btn);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }


                        });
                    }

                    private boolean selectBtnOnActionLtable(LicenseTableDto data, JFXButton btn) throws SQLException {
                        List<VehicleDto> vehicleDtoList = vehicleModel.getAllVehicle();
                        for (VehicleDto vehicleDto:vehicleDtoList) {
                            if (data.getVehicleId() == vehicleDto.getId()) {
                                List<VehicleOwnerDto> vehicleOwnerDtoList = VehicleOwnerModel.getAllOwner();
                                for (VehicleOwnerDto vehicleOwnerDto: vehicleOwnerDtoList) {
                                    if (vehicleDto.getOwnerId() == vehicleOwnerDto.getId()) {
                                        txtEmail.setText(vehicleOwnerDto.getEmail());
                                    }
                                }
                            }
                        }
                        return false;
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {

                            setGraphic(null);
                        } else {
                            btn.setCursor(Cursor.HAND);
                            btn.setStyle("-fx-background-color: #ff8b33");

                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        licenseDetailsTbl.getColumns().add(colBtn);
    }

    private void selectToInsuranceTable() {
        TableColumn<InsuranceTableDto, Void> colBtn = new TableColumn("Action");
        Callback<TableColumn<InsuranceTableDto, Void>, TableCell<InsuranceTableDto, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<InsuranceTableDto, Void> call(final TableColumn<InsuranceTableDto, Void> param) {
                final TableCell<InsuranceTableDto, Void> cell = new TableCell<InsuranceTableDto, Void>() {
                    private JFXButton btn = new JFXButton("SELECT");
                    {
                        btn.setOnAction((ActionEvent event) -> {

                            InsuranceTableDto data = getTableView().getItems().get(getIndex());
                            try {
                                boolean isGetOwnerEmail = selectBtnOnActionItable(data,btn);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }


                        });
                    }

                    private boolean selectBtnOnActionItable(InsuranceTableDto data, JFXButton btn) throws SQLException {
                         List<VehicleDto> vehicleDtoList = vehicleModel.getAllVehicle();
                        for (VehicleDto vehicleDto:vehicleDtoList) {
                            if (data.getVehicleId() == vehicleDto.getId()) {
                                List<VehicleOwnerDto> vehicleOwnerDtoList = VehicleOwnerModel.getAllOwner();
                                for (VehicleOwnerDto vehicleOwnerDto: vehicleOwnerDtoList) {
                                    if (vehicleDto.getOwnerId() == vehicleOwnerDto.getId()) {
                                        txtEmail.setText(vehicleOwnerDto.getEmail());
                                    }
                                }
                            }
                        }
                        return false;
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {

                            setGraphic(null);
                        } else {
                            btn.setCursor(Cursor.HAND);
                            btn.setStyle("-fx-background-color: #ff8b33");

                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        insuranceDetailsTbl.getColumns().add(colBtn);
    }

    private void getAllLicenseDetails() throws SQLException {
        ObservableList<LicenseTableDto> observableList = FXCollections.observableArrayList();
        List<LicenseTableDto> licenseTableDtos = vehicleLicenseModel.getVehicleTableDto();
        observableList.addAll(licenseTableDtos);
        licenseDetailsTbl.setItems(observableList);
    }
    private void setCellValueFactoryLicenseTable(){
        lTblIdColumn1.setCellValueFactory(new PropertyValueFactory<>("Id"));
        lTblVehicleIDColumn1.setCellValueFactory(new PropertyValueFactory<>("VehicleId"));
        lTblLicenseNumberColumn1.setCellValueFactory(new PropertyValueFactory<>("LicenseNumber"));
        lTblIssueDateColumn1.setCellValueFactory(new PropertyValueFactory<>("IssueDate"));
        lTblExpiryDateColumn1.setCellValueFactory(new PropertyValueFactory<>("ExpiryDate"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            getAllLicenseDetails();
            setCellValueFactoryLicenseTable();
            selectToLicenseTable();
            getAllInsuranceDetails();
            setCellValueFactoryInsuranceTable();
            selectToInsuranceTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactoryInsuranceTable(){
        iTblIdColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        iTblVehicleIDColumn.setCellValueFactory(new PropertyValueFactory<>("VehicleId"));
        iTblInsuranceNumberColumn.setCellValueFactory(new PropertyValueFactory<>("InsuranceNumber"));
        iTblIssueDateColumn.setCellValueFactory(new PropertyValueFactory<>("IssueDate"));
        iTblExpiryDateColumn.setCellValueFactory(new PropertyValueFactory<>("ExpiryDate"));
    }

    private void getAllInsuranceDetails() throws SQLException {
        ObservableList<InsuranceTableDto> observableList = FXCollections.observableArrayList();
        List<InsuranceTableDto> insuranceTableDtos =vehicleInsuranceModel.getVehicleTableDto();
        observableList.addAll(insuranceTableDtos);
        insuranceDetailsTbl.setItems(observableList);
    }
}
