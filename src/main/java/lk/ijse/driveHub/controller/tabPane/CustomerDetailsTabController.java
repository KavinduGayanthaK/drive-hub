package lk.ijse.driveHub.controller.tabPane;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.Style;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import lk.ijse.driveHub.controller.openWindow.PopupWindows;
import lk.ijse.driveHub.controller.popupWindowReservation.UpdateCustomerFormController;
import lk.ijse.driveHub.dto.CustomerDto;
import lk.ijse.driveHub.dto.tableDto.CustomerTableDto;
import lk.ijse.driveHub.model.CustomerModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerDetailsTabController implements Initializable {

    @FXML
    private TableColumn<?, ?> addressColumn;
    @FXML
    private TableColumn<?, ?> btnColumn;
    @FXML
    private TableView<CustomerTableDto> customerTable;
    @FXML
    private TableColumn<?, ?> emailColumn;
    @FXML
    private TableColumn<?, ?> firstNameColumn;
    @FXML
    private TableColumn<?, ?> idColumn;
    @FXML
    private TableColumn<?, ?> lastNameColumn;
    @FXML
    private TableColumn<?, ?> mobileNumbercolumn;
    @FXML
    private TableColumn<?, ?> nicColumn;
    @FXML
    private TableColumn<?, ?> nicSoftCopyColumn;
    @FXML
    private TableColumn<?, ?> utilityBillColumn;
    @FXML
    private TextField txtSearchCustomer;


    ObservableList<CustomerTableDto> observableList = FXCollections.observableArrayList();
   public static CustomerTableDto customerTableDto = new CustomerTableDto();
    CustomerModel customerModel = new CustomerModel();


    @FXML
    public void createBtnOnAction(ActionEvent actionEvent) throws IOException {
        PopupWindows popupWindows = new PopupWindows();
        popupWindows.window("/view/popupWindowReservation/addCustomer_form.fxml", "Register Customer");

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValueFactory();
        loadAllCustomer();
       //tableListener();

    }

    private void setCellValueFactory() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
        nicColumn.setCellValueFactory(new PropertyValueFactory<>("Nic"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        mobileNumbercolumn.setCellValueFactory(new PropertyValueFactory<>("MobileNumber"));
        utilityBillColumn.setCellValueFactory(new PropertyValueFactory<>("UtilityBill"));
        nicSoftCopyColumn.setCellValueFactory(new PropertyValueFactory<>("NicCopy"));
    }

    public  void loadAllCustomer() {
        updateButtonToTable();
        deActivateButtonToTable();
        CustomerTableDto customerTableDto = new CustomerTableDto();


        try {
            List<CustomerDto> customerDtoList = customerModel.getAllCustomer();

            for (CustomerDto customerDto : customerDtoList) {
                observableList.add(
                        new CustomerTableDto(
                                customerDto.getId(),
                                customerDto.getFirstName(),
                                customerDto.getLastName(),
                                customerDto.getAddress(),
                                customerDto.getNic(),
                                customerDto.getEmail(),
                                customerDto.getNumber(),
                                customerDto.getIsUtilityBillSoftCopy(),
                                customerDto.getIsNicSoftCopy()

                        ));
            }
            customerTable.setItems(observableList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void updatePopUpWindow() {

    }


    //this method is not completed
    @FXML
    void txtCustomerSearchOnAction(KeyEvent event) throws SQLException {
        String txtSearch = txtSearchCustomer.getText();
        System.out.println(txtSearchCustomer.getText());
        if (txtSearch.equals("")) {
            loadAllCustomer();
        } else {
            ObservableList<CustomerTableDto> observableList = FXCollections.observableArrayList();
            try {
                List<CustomerDto> list = customerModel.searchCustomerTable(txtSearch);

                for (CustomerDto customerDto : list) {
                    observableList.add(
                            new CustomerTableDto(
                                    customerDto.getId(),
                                    customerDto.getFirstName(),
                                    customerDto.getLastName(),
                                    customerDto.getAddress(),
                                    customerDto.getNic(),
                                    customerDto.getEmail(),
                                    customerDto.getNumber(),
                                    customerDto.getIsUtilityBillSoftCopy(),
                                    customerDto.getIsNicSoftCopy()

                            ));
                }
                customerTable.setItems(observableList);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    //this method is notComplete working
    private void updateButtonToTable() {
        TableColumn<CustomerTableDto, Void> colBtn = new TableColumn("Action");
        Callback<TableColumn<CustomerTableDto, Void>, TableCell<CustomerTableDto, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<CustomerTableDto, Void> call(final TableColumn<CustomerTableDto, Void> param) {
                final TableCell<CustomerTableDto, Void> cell = new TableCell<CustomerTableDto, Void>() {
                    private final JFXButton btn = new JFXButton("Update");
                    {
                        btn.setOnAction((ActionEvent event) -> {

                            CustomerTableDto data = getTableView().getItems().get(getIndex());

                            customerTableDto.setId(data.getId());
                            customerTableDto.setFirstName(data.getFirstName());
                            customerTableDto.setLastName(data.getLastName());
                            customerTableDto.setAddress(data.getAddress());
                            customerTableDto.setNic(data.getNic());
                            customerTableDto.setEmail(data.getEmail());
                            customerTableDto.setMobileNumber(data.getMobileNumber());
                            customerTableDto.setNicCopy(data.getNicCopy());
                            customerTableDto.setUtilityBill(data.getUtilityBill());
                            //System.out.println("selectedData: " + data);
                            boolean isUpdated = updateBtnOnAction(data,btn);
                            if (isUpdated) {
                                loadAllCustomer();
                            }

                        });
                    }
                    private boolean updateBtnOnAction(CustomerTableDto data,Button btn) {
                        UpdateCustomerFormController updateCustomerFormController = new UpdateCustomerFormController();

                            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);

                            Optional<ButtonType> type = new Alert(Alert.AlertType.CONFIRMATION,
                                    "Are you want to change details ?", yes, no).showAndWait();
                            if (type.orElse(no) == yes) {

                                URL resource = this.getClass().getResource("/view/popupWindowReservation/updateCustomer_form.fxml");
                                FXMLLoader fxmlLoader = new FXMLLoader(resource);
                                Parent load = null;
                                try {
                                    load = fxmlLoader.load();
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                Stage stage = new Stage();
                                stage.setTitle("Customer Form");
                                stage.setScene(new Scene(load));
                                stage.centerOnScreen();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.setResizable(false);
                                stage.show();
                                updateCustomerFormController.setData();

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
                            btn.setStyle("-fx-background-color: #42c923");
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        customerTable.getColumns().add(colBtn);
    }

    private void deActivateButtonToTable() {
        TableColumn<CustomerTableDto, Void> colBtn = new TableColumn("Action");
        Callback<TableColumn<CustomerTableDto, Void>, TableCell<CustomerTableDto, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<CustomerTableDto, Void> call(final TableColumn<CustomerTableDto, Void> param) {
                final TableCell<CustomerTableDto, Void> cell = new TableCell<CustomerTableDto, Void>() {
                    private  JFXButton btn = new JFXButton("deActivate");
                    {
                        btn.setOnAction((ActionEvent event) -> {

                            CustomerTableDto data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            boolean isDeActivate = deActivateBtnOnAction(data,btn);
                            if (isDeActivate) {
                                loadAllCustomer();
                            }
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {

                            setGraphic(null);
                        } else {
                            btn.setCursor(Cursor.HAND);
                            btn.setStyle("-fx-background-color: #cc0808");

                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        customerTable.getColumns().add(colBtn);
    }

    public  boolean deActivateBtnOnAction(CustomerTableDto data,Button btn) {

            ButtonType yes = new ButtonType("Yes",ButtonBar.ButtonData.YES);
            ButtonType no = new ButtonType("No",ButtonBar.ButtonData.NO);

            Optional<ButtonType> type = new Alert(
                    Alert.AlertType.CONFIRMATION,"Do you want to customer deActivate",yes,no).showAndWait();
            if (type.orElse(no) == yes) {
                try {
                    boolean isDeActivate = customerModel.changeStatus(data.getId(),"deActivate");
                    if (isDeActivate) {

                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        return false;
    }

    private void activateButtonToTable() {
        TableColumn<CustomerTableDto, Void> colBtn = new TableColumn("Action");
        Callback<TableColumn<CustomerTableDto, Void>, TableCell<CustomerTableDto, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<CustomerTableDto, Void> call(final TableColumn<CustomerTableDto, Void> param) {
                final TableCell<CustomerTableDto, Void> cell = new TableCell<CustomerTableDto, Void>() {
                    private  JFXButton btn = new JFXButton("activate");
                    {
                        btn.setOnAction((ActionEvent event) -> {

                            CustomerTableDto data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            boolean activate = activateBtnOnAction(data,btn);
                            if (activate) {
                                loadAllCustomer();
                            }
                        });
                    }

                    private boolean activateBtnOnAction(CustomerTableDto customerTableDto, JFXButton btn) {
                        UpdateCustomerFormController updateCustomerFormController = new UpdateCustomerFormController();
                        btn .setOnAction((e) -> {
                            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);

                            Optional<ButtonType> type = new Alert(Alert.AlertType.CONFIRMATION,
                                    "Do you want to customer activate ?", yes, no).showAndWait();
                            if (type.orElse(no) == yes) {


                            }

                            });
                        return false;
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {

                            setGraphic(null);
                        } else {
                            btn.setCursor(Cursor.HAND);
                            btn.setStyle("-fx-background-color: #cc0808");

                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        customerTable.getColumns().add(colBtn);
    }
}
