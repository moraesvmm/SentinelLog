package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Map;

public class ChartGenerator extends Application {

    public static Map<String, Integer> topIPs;
    public static Map<String, Integer> eventCounts;

    @Override
    public void start(Stage stage) {
        stage.setTitle("🔍 SentinelLog - Visualização de Logs");

        // Gráfico de Barras - Top IPs
        CategoryAxis xAxisIPs = new CategoryAxis();
        NumberAxis yAxisIPs = new NumberAxis();
        xAxisIPs.setLabel("Endereço IP");
        yAxisIPs.setLabel("Ocorrências");

        BarChart<String, Number> barChartIPs = new BarChart<>(xAxisIPs, yAxisIPs);
        barChartIPs.setTitle("Top IPs Suspeitos");
        barChartIPs.setLegendVisible(false);

        XYChart.Series<String, Number> seriesIPs = new XYChart.Series<>();
        if (topIPs != null) {
            topIPs.forEach((ip, count) -> seriesIPs.getData().add(new XYChart.Data<>(ip, count)));
        }
        barChartIPs.getData().add(seriesIPs);

        // Gráfico de Pizza - Tipos de Eventos
        PieChart pieChartEvents = new PieChart();
        pieChartEvents.setTitle("Distribuição de Tipos de Evento");
        if (eventCounts != null) {
            eventCounts.forEach((event, count) -> pieChartEvents.getData().add(new PieChart.Data(event, count)));
        }

        VBox vbox = new VBox(20, barChartIPs, pieChartEvents);
        Scene scene = new Scene(vbox, 900, 650);

        // Aplica o CSS
        scene.getStylesheets().add(getClass().getResource("/resources/style.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }
}
