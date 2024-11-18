package com.example.appsupermercado;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SobreController {

    @FXML
    public void voltarParaMenuPrincipal(ActionEvent event) {
        try {
            // Carregar o arquivo FXML do menu principal
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HelloView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            // Recuperar o Stage atual e trocar a cena
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Supermercado Compre Bem - Menu Principal");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
