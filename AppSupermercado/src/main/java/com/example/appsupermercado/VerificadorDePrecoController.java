package com.example.appsupermercado;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.example.appsupermercado.Model.Produto;

import java.util.HashMap;
import java.util.Map;

public class VerificadorDePrecoController {

    // Elementos da interface
    @FXML
    private Label verificadorPreco;
    @FXML
    private Label produtoLabel;
    @FXML
    private Label valorLabel;
    @FXML
    private TextField codigoProdutoTextField;

    //Simula um BD
    private Map<String, Produto> produtos;

    public VerificadorDePrecoController() {
        inicializarProdutos();
    }

    // Método para inicializar o banco de dados de produtos
    private void inicializarProdutos() {
        produtos = new HashMap<>();
        produtos.put("1", new Produto("Sabonete", 3.50));
        produtos.put("2", new Produto("Shampoo", 9.90));
        produtos.put("3", new Produto("Desodorante", 11.90));
        produtos.put("4", new Produto("Pasta de Dente", 5.75));
    }

    // Evento acionado quando digita algo no campo de texto
    @FXML
    public void onCodigoTyped() {
        String codigo = codigoProdutoTextField.getText().trim();

        // Verificar se o código é válido
        if (codigo.isEmpty()) {
            atualizarInterface("", 0.0, false);
            return;
        }

        Produto produto = produtos.get(codigo);

        if (produto != null) {
            atualizarInterface(produto.getDescricao(), produto.getPreco(), true);
        } else {
            atualizarInterface("Produto não encontrado", 0.0, false);
        }
    }

    private void atualizarInterface(String descricao, double valor, boolean encontrado) {
        if (encontrado) {
            produtoLabel.setText(descricao);
            valorLabel.setText(String.format("R$ %.2f", valor));
        } else {
            produtoLabel.setText(descricao);
            valorLabel.setText("R$ 0,00");
        }
    }
}
