package com.example.appsupermercado;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class HelloController {
    @FXML
    protected void abrirCaixa() throws IOException {
        new JanelaCaixa();
    }
    @FXML
    public void onSairButtonClick() throws IOException {
        System.exit(0);
    }

}



