package com.example.appsupermercado;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CaixaController {
    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtQuantity;

    @FXML
    private Label lblProductInfo;

    @FXML
    private Button btnAddToList;

    @FXML
    private ListView<String> productList;

    @FXML
    private Label lblTotalValue;

    @FXML
    private TextField txtPaid;

    @FXML
    private Label lblChangeValue;

    // Método para adicionar itens à lista
    @FXML
    public void adicionarNaLista() {
        String codigo = txtCode.getText();
        String quantidade = txtQuantity.getText();

        if (!codigo.isEmpty() && !quantidade.isEmpty()) {
            double preco = 11.90; // Exemplo de preço fixo
            int qtd = Integer.parseInt(quantidade);
            double total = preco * qtd;

            // Adiciona o item na lista
            productList.getItems().add("Produto: Desodorante - Qtd: " + qtd + " - Unit: R$ " + preco + " - Total: R$ " + total);

            // Atualiza o total geral
            double totalAtual = Double.parseDouble(lblTotalValue.getText());
            lblTotalValue.setText(String.format("%.2f", totalAtual + total));

            // Exibe mensagem com informações do produto
            lblProductInfo.setText("Adicionado: Desodorante - R$ " + preco);
        } else {
            lblProductInfo.setText("Por favor, preencha o código e a quantidade.");
        }
    }

    // Método para remover itens da lista
    @FXML
    public void removerItem() {
        String itemSelecionado = productList.getSelectionModel().getSelectedItem();

        if (itemSelecionado != null) {
            productList.getItems().remove(itemSelecionado);

            // Atualiza o total geral
            String[] partes = itemSelecionado.split("Total: R\\$ ");
            double totalDoItem = Double.parseDouble(partes[1]);
            double totalAtual = Double.parseDouble(lblTotalValue.getText());
            lblTotalValue.setText(String.format("%.2f", totalAtual - totalDoItem));

            lblProductInfo.setText("Item removido.");
        } else {
            lblProductInfo.setText("Selecione um item para remover.");
        }
    }

    // Método para calcular o troco
    @FXML
    public void calcularTroco() {
        try {
            double total = Double.parseDouble(lblTotalValue.getText());
            double pago = Double.parseDouble(txtPaid.getText());
            double troco = pago - total;

            if (troco < 0) {
                lblChangeValue.setText("0.00");
                lblProductInfo.setText("Valor pago insuficiente.");
            } else {
                lblChangeValue.setText(String.format("%.2f", troco));
                lblProductInfo.setText("Troco calculado: R$ " + troco);
            }
        } catch (NumberFormatException e) {
            lblProductInfo.setText("Insira um valor válido para o pagamento.");
        }
    }
}