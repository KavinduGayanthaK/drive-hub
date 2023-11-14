package lk.ijse.driveHub.controller.tabPane;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import lk.ijse.driveHub.dto.VehicleOwnerDto;
import lk.ijse.driveHub.dto.tableDto.CustomerTableDto;
import lk.ijse.driveHub.dto.tableDto.OwnerTableDto;
import lk.ijse.driveHub.model.VehicleOwnerModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

//import static sun.security.pkcs11.wrapper.Functions.getId;

public class OwnerDetailsTabController {

    @FXML
    private TableColumn<?, ?> addressColumn;

    @FXML
    private TableColumn<?, ?> emailColumn;

    @FXML
    private TableColumn<?, ?> firstNameColumn;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> lastNameColumn;

    @FXML
    private TableColumn<?, ?> nicColumn;

    @FXML
    private TableColumn<?, ?> numberColumn;

    @FXML
    private TableView<OwnerTableDto> ownerTable;


    public void initialize() {
        setCellValueFactory();
        loadAllOwner();
    }
    private void setCellValueFactory() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
        nicColumn.setCellValueFactory(new PropertyValueFactory<>("Nic"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("MobileNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
    }
    public void createNewBtnOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/popupWindowVehicle/addOwner_form.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Register Owner");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        loadAllOwner();
    }

    public void loadAllOwner() {
        updateButtonToTable();
        deleteButtonToTable();
        var model = new VehicleOwnerModel();
        ObservableList<OwnerTableDto> observableList = FXCollections.observableArrayList();

        try {
            List<VehicleOwnerDto> vehicleOwnerDtoList = model.getAllOwner();

            for (VehicleOwnerDto vehicleOwnerDto:vehicleOwnerDtoList) {
                observableList.add(new OwnerTableDto(
                        vehicleOwnerDto.getId(),
                        vehicleOwnerDto.getFirstName(),
                        vehicleOwnerDto.getLastName(),
                        vehicleOwnerDto.getAddress(),
                        vehicleOwnerDto.getNic(),
                        vehicleOwnerDto.getMobileNumber(),
                        vehicleOwnerDto.getEmail()
                ));
            }
            ownerTable.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteButtonToTable() {
        TableColumn<OwnerTableDto, Void> colBtn = new TableColumn("Action");
        Callback<TableColumn<OwnerTableDto, Void>, TableCell<OwnerTableDto, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<OwnerTableDto, Void> call(final TableColumn<OwnerTableDto, Void> param) {
                final TableCell<OwnerTableDto, Void> cell = new TableCell<OwnerTableDto, Void>() {
                    private  JFXButton button = new JFXButton("delete");
                    {
                        button.setOnAction((ActionEvent event) -> {

                            OwnerTableDto data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            boolean isUpdated = deleteBtnOnAction(data.getId(),button);
                            if (isUpdated) {
                                loadAllOwner();
                            }
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {

                            setGraphic(null);
                        } else {
                            button.setCursor(Cursor.HAND);
                            button.setStyle("-fx-background-color: #ff3737;-fx-text-fill: #FFFFFF");


                            setGraphic(button);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        ownerTable.getColumns().add(colBtn);
    }

    public boolean deleteBtnOnAction(int id, JFXButton button) {

        return false;
    }

    private void updateButtonToTable() {
        TableColumn<OwnerTableDto, Void> colBtn = new TableColumn("Action");
        Callback<TableColumn<OwnerTableDto, Void>, TableCell<OwnerTableDto, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<OwnerTableDto, Void> call(final TableColumn<OwnerTableDto, Void> param) {
                final TableCell<OwnerTableDto, Void> cell = new TableCell<OwnerTableDto, Void>() {
                    private  JFXButton button = new JFXButton("update");
                    {
                        button.setOnAction((ActionEvent event) -> {

                            OwnerTableDto data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            boolean isUpdated = deleteBtnOnAction(data.getId(),button);
                            if (isUpdated) {
                                loadAllOwner();
                            }
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {

                            setGraphic(null);
                        } else {
                            button.setCursor(Cursor.HAND);
                            button.setStyle("-fx-background-color: #42C923FF");

                            setGraphic(button);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        ownerTable.getColumns().add(colBtn);
    }

//    private void tableListener() {
//        tblItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
////            System.out.println(newValue);
//            setData(newValue);
//        });
//    }


}
