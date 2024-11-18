package com.example.appsupermercado;

import com.example.appsupermercado.Model.Produto;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

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

    // Lista de produtos disponíveis
    private List<Produto> produtosDisponiveis = new ArrayList<>();

    // Total geral das compras
    private double totalGeral = 0.0;

    @FXML
    public void initialize() {
        // Inicialize o total com 0.00
        lblTotalValue.setText("0.00");

        // Adicione alguns produtos à lista (simulação)
        produtosDisponiveis.add(new Produto("Desodorante", 11.90));
        produtosDisponiveis.add(new Produto("Sabonete", 3.50));
        produtosDisponiveis.add(new Produto("Shampoo", 15.75));
        produtosDisponiveis.add(new Produto("Pasta de dente", 7.30));
    }

    @FXML
    public void adicionarNaLista() {
        String codigo = txtCode.getText();
        String quantidade = txtQuantity.getText();

        if (!codigo.isEmpty() && !quantidade.isEmpty()) {
            try {
                int index = Integer.parseInt(codigo) - 1; // Código começa em 1
                if (index < 0 || index >= produtosDisponiveis.size()) {
                    lblProductInfo.setText("Produto não encontrado.");
                    return;
                }

                Produto produto = produtosDisponiveis.get(index);
                int qtd = Integer.parseInt(quantidade);
                double totalItem = produto.getPreco() * qtd;

                // Add o item
                productList.getItems().add(
                        "Produto: " + produto.getDescricao() +
                                " - Qtd: " + qtd +
                                " - Unit: R$ " + produto.getPreco() +
                                " - Total: R$ " + String.format("%.2f", totalItem)
                );

                // Atualiza o total geral
                totalGeral += totalItem;
                lblTotalValue.setText(String.format("%.2f", totalGeral));

                lblProductInfo.setText("Adicionado: " + produto.getDescricao() + " - R$ " + produto.getPreco());
            } catch (NumberFormatException e) {
                lblProductInfo.setText("Código ou quantidade inválidos.");
            }
        } else {
            lblProductInfo.setText("Por favor, preencha o código e a quantidade.");
        }
    }

    @FXML
    public void removerItem() {
        String itemSelecionado = productList.getSelectionModel().getSelectedItem();

        if (itemSelecionado != null) {
            productList.getItems().remove(itemSelecionado);

            String[] partes = itemSelecionado.split("Total: R\\$ ");
            double totalDoItem = Double.parseDouble(partes[1]);
            totalGeral -= totalDoItem;
            lblTotalValue.setText(String.format("%.2f", totalGeral));

            lblProductInfo.setText("Item removido.");
        } else {
            lblProductInfo.setText("Selecione um item para remover.");
        }
    }

    @FXML
    public void calcularTroco() {
        try {
            double pago = Double.parseDouble(txtPaid.getText());
            double troco = pago - totalGeral;

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
