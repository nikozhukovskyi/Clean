package com.example.clean;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {


    static void errorAlert(String Errores){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Have an Error");
        alert.setHeaderText(Errores + " ");
        alert.setContentText("Fuck!");
        alert.showAndWait().ifPresent(wait -> {
            if (wait == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
    }
    static void iNFORM(String informes){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Have an INFORMATION");
        alert.setHeaderText(informes + " ");
        alert.showAndWait().ifPresent(wait -> {
        });
    }


    @FXML
    void close_img(MouseEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void minimize_img(MouseEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.setIconified(true);
    }


    @FXML
    void Recycle_bin() {
        Runtime rs = Runtime.getRuntime();
        try {
            rs.exec("cmd.exe /C rmdir /q /s С:\\$Recycle.Bin" + "cmd.exe /C rmdir /q /s D:\\$Recycle.Bin");
            iNFORM("Проведено очищення корзини");

        } catch (IOException e) {
            errorAlert(e.getMessage());
        }
    }

    @FXML
    void battery() {
        String user = System.getProperty("user.name");
        Runtime rs = Runtime.getRuntime();
        try {
            rs.exec("cmd.exe /C powercfg /batteryreport /output C:/Users/"+user+"/battery-report.html").waitFor();
            rs.exec("cmd.exe /C C:/Users/"+user+"/battery-report.html");
            iNFORM("Створено battery report по шляху 'C:/Users/"+user+"/battery-report.html'");

        } catch (IOException | InterruptedException e) {
            errorAlert(e.getMessage());
        }


    }


    @FXML
    void ram() {
        Runtime rs = Runtime.getRuntime();
        try {
            rs.exec("cmd.exe /C nircmd.exe sendkeypress ctrl+f1");
            iNFORM("Ощищено тимчасові файли, nircmd.exe - потрібно інсталювати");
        } catch (IOException e) {
            errorAlert(e.getMessage());
        }
        temp();
    }

    @FXML
    void startSys() {
        Runtime rs = Runtime.getRuntime();
        try {
            rs.exec("cmd.exe /C net start sysmain").waitFor();
            iNFORM("Запущено SysMain");
        } catch (IOException | InterruptedException e) {
            errorAlert(e.getMessage());
        }
    }

    @FXML
    void stopSys() {
        Runtime rs = Runtime.getRuntime();
        try {
            rs.exec("cmd.exe /C net stop sysmain").waitFor();
            iNFORM("Зупинено SysMain");
        } catch (IOException | InterruptedException e) {
            errorAlert(e.getMessage());
        }
    }

    void temp() {
        Runtime rs = Runtime.getRuntime();
        try {
            rs.exec("cmd.exe /C rmdir /s /q %Temp%").waitFor();
            iNFORM("Проведена очистка тимчасових файлів");
        } catch (IOException | InterruptedException e) {
            errorAlert(e.getMessage());
        }
    }

    @FXML
    void close_all_prog_work(MouseEvent event) {

        Runtime rs = Runtime.getRuntime();
        try {
            rs.exec("cmd.exe /C " +
                    "taskkill /f /fi \"imagename eq AnyDesk.exe\" /fi \"username eq %username%\" &&" +
                    "taskkill /f /fi \"imagename eq Skype.exe\" /fi \"username eq %username%\" &&" +
                    "taskkill /f /fi \"imagename eq Telegram.exe\" /fi \"username eq %username%\" &&" +
                    "taskkill /f /fi \"imagename eq mstsc.exe\" /fi \"username eq %username%\" &&" +
                    "taskkill /f /fi \"imagename eq TeamViewer.exe\" /fi \"username eq %username%\" &&" +
                    "taskkill /f /fi \"imagename eq TeamViewer*\" /fi \"username eq %username%\"").waitFor();
            iNFORM("Закрито, AnyDesk, Skype, Telegram, mstsc, TeamViewer. ");
        } catch (IOException | InterruptedException e) {
            errorAlert(e.getMessage());
        }

    }


}