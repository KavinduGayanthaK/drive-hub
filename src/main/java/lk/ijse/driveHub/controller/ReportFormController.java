package lk.ijse.driveHub.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import lk.ijse.driveHub.db.DbConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ReportFormController implements Initializable{
    @FXML
    private ComboBox<String> cmbSelectReport;

      @FXML
      private ScrollPane scrollPane;

    @FXML
    private DatePicker txtFromDate;

    @FXML
    private DatePicker txtToDate;

    @FXML
    void GenerateReportBtnOnAction(ActionEvent event) {
        if (cmbSelectReport.getValue().equals("Payment Report")) {
            try {

                InputStream resourceAsStream = getClass().getResourceAsStream("/report/Pymentreport.jrxml");
                JasperPrint jasperPrint = getJasperPrint(resourceAsStream);

                JasperViewer viewer = new JasperViewer(jasperPrint);

                SwingNode swingNode = new SwingNode();
                swingNode.setContent((JComponent) viewer.getContentPane());

                Platform.runLater(() ->{
                    scrollPane.setContent(swingNode);
                    scrollPane.setFitToWidth(true);
                    scrollPane.setFitToHeight(true);
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            try {

                InputStream resourceAsStream = getClass().getResourceAsStream("/report/ReservationReport.jrxml");
                JasperPrint jasperPrint = getJasperPrint(resourceAsStream);

                JasperViewer viewer = new JasperViewer(jasperPrint);

                SwingNode swingNode = new SwingNode();
                swingNode.setContent((JComponent) viewer.getContentPane());

                Platform.runLater(() ->{
                    scrollPane.setContent(swingNode);
                    scrollPane.setFitToWidth(true);
                    scrollPane.setFitToHeight(true);
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private JasperPrint getJasperPrint(InputStream inputStream) throws SQLException {
        HashMap parameters = new HashMap<>();
        parameters.put("fromDate",String.valueOf(txtFromDate.getValue()));
        parameters.put("toDate",String.valueOf(txtToDate.getValue()));

        try {
            JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            return JasperFillManager.fillReport(
                    jasperReport,
                    parameters,
                    DbConnection.getInstance().getConnection()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbSelectReport.setItems(FXCollections.observableArrayList("Payment Report","Reservation Report"));
        txtFromDate.setValue(LocalDate.now());
    }
}


