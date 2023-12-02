package lk.ijse.driveHub.controller.popupWindowReservation;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lk.ijse.driveHub.controller.closeWindow.CloseWindow;
import lk.ijse.driveHub.controller.popupWindowVehicle.LicenseFormController;
import lk.ijse.driveHub.controller.popupWindowVehicle.VehicleFormController;
import lk.ijse.driveHub.dto.PaymentDto;
import lk.ijse.driveHub.model.CustomerModel;
import lk.ijse.driveHub.model.PaymentModel;
import lk.ijse.driveHub.model.ReservationModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PaymentFormController implements Initializable {
    @FXML
    private JFXButton backBtn;
    @FXML
    private JFXButton closeBtn;
    @FXML
    private JFXButton submitBtn;
    @FXML
    private ComboBox<String> cmbPaymentMethod;
    @FXML
    private ComboBox<String> cmbPaymentType;
    @FXML
    private TextField txtAmount;
    @FXML
    private TextField txtDepositAmount;
    @FXML
    private TextArea txtabout;


    static CloseWindow closeWindow = new CloseWindow();
    static Stage paymentFormController;
    ReservationModel reservationModel =  new ReservationModel();
    CustomerModel customerModel = new CustomerModel();
    PaymentDto paymentDto = new PaymentDto();

    PaymentModel paymentModel = new PaymentModel();





    @FXML
    void backBtnOnAction(ActionEvent event) throws IOException {
        CustomerFormController.customerFormController.show();
        paymentFormController.close();
    }

    @FXML
    void closeBtnOnAction(ActionEvent event) {

    }

    @FXML
    void submitBtnOnAction(ActionEvent event) {
        try {
            customerModel.saveCustomer(CustomerFormController.customerDto);
            if (CustomerFormController.customerDto.getId() !=0) {
                ReservationVehicleFormController.reservationDto.setCustomerId(CustomerFormController.customerDto.getId());
                reservationModel.saveReservation(ReservationVehicleFormController.reservationDto);
                if (ReservationVehicleFormController.reservationDto.getId() != 0) {
                    setValues();
                    paymentModel.savePayment(paymentDto);
                    if (paymentDto.getId() != 0) {
                        ButtonType ok = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
                        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION,
                                "Saved successfully", ok).showAndWait();
                        if (type.isPresent()) {
                            VehicleFormController.clearDto();
                            LicenseFormController.clearDto();

                            Stage stage = (Stage) submitBtn.getScene().getWindow();
                            stage.close();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void add(Stage stage,Stage customerFormController) {
        paymentFormController= stage;
        CustomerFormController.customerFormController = customerFormController;
    }

    public void setValues() {
        paymentDto.setId(0);
        paymentDto.setReservationId(ReservationVehicleFormController.reservationDto.getId());
        paymentDto.setDepositAmount(Double.parseDouble(txtDepositAmount.getText()));
        paymentDto.setAmount(Double.parseDouble(txtAmount.getText()));
        paymentDto.setType(cmbPaymentType.getValue());
        paymentDto.setAbout(txtabout.getText());
        paymentDto.setDate(LocalDate.now());

    }

    public List<Integer> setAmount() {
        int multipliedDate = 0;
            List<Integer> multipliedDates = new ArrayList<>();
            LocalDate currentDate = ReservationVehicleFormController.reservationDto.getStartDate();

            while (!currentDate.isAfter(ReservationVehicleFormController.reservationDto.getEndDate())) {
                 multipliedDate = (int) (currentDate.getDayOfMonth() * ReservationVehicleFormController.reservationVehicleTableDto.getPerDayRate());
                multipliedDates.add(multipliedDate);
                currentDate = currentDate.plusDays(1);

            }
            txtAmount.setText(String.valueOf(multipliedDate));
            return multipliedDates;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAmount();
        cmbPaymentMethod.setItems(FXCollections.observableArrayList("Card Payment","Cash Payment"));
        cmbPaymentType.setItems(FXCollections.observableArrayList("Advanced","Ful Payment"));
    }


}
