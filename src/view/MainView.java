package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainView extends VBox {

    private BarChart<String, Number> barChart;

    public MainView() {
        setPadding(new Insets(20));
        setSpacing(15);
        getStyleClass().add("root");

        Label title = new Label("Analisador de Logs SSH");
        title.getStyleClass().add("title");

        Button analyzeButton = new Button("Analisar Logs");
        analyzeButton.getStyleClass().add("analyze-button");

        // Cria o gráfico de barras
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Tipo de Evento");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Quantidade");

        barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Eventos SSH Detectados");
        barChart.setPrefHeight(400);

        // Ação do botão
        analyzeButton.setOnAction(e -> exibirGrafico());

        getChildren().addAll(title, analyzeButton, barChart);
    }

    private void exibirGrafico() {
        // Uso isso para limpar os dados
        barChart.getData().clear();

        // Simula os dados de análise
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Eventos");

        series.getData().add(new XYChart.Data<>("Tentativas de Login", 10));
        series.getData().add(new XYChart.Data<>("Acessos Bem-Sucedidos", 4));
        series.getData().add(new XYChart.Data<>("IPs Bloqueados", 2));

        barChart.getData().add(series);
    }
}
