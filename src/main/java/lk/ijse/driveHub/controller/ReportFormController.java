package lk.ijse.driveHub.controller;

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
import java.util.ResourceBundle;

public class ReportFormController implements Initializable {
    @FXML
    private ComboBox<?> cmbSelectReport;

    @FXML
    private AnchorPane reportPane;

    @FXML
    private DatePicker txtFromDate;

    @FXML
    private DatePicker txtToDate;

    @FXML
    void GenerateReportBtnOnAction(ActionEvent event) {

        try {

            InputStream resourceAsStream = getClass().getResourceAsStream("/report/Blank_A4_1.jrxml");
            JasperPrint jasperPrint = getJasperPrint(resourceAsStream);

            JasperViewer viewer = new JasperViewer(jasperPrint);

            SwingNode swingNode = new SwingNode();
            swingNode.setContent((JComponent) viewer.getContentPane());

            reportPane.getChildren().add(swingNode);
            reportPane.setPrefWidth(viewer.getContentPane().getWidth());
            reportPane.setPrefHeight(viewer.getContentPane().getHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JasperPrint getJasperPrint(InputStream inputStream) throws SQLException {
        try {
            JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            return JasperFillManager.fillReport(
                    jasperReport,
                    null,
                    DbConnection.getInstance().getConnection()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        }
    }


