package service;

import model.LogEntry;
import util.LogParser;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class LogAnalyzer {
    private final Map<String, Integer> ipCounter = new HashMap<>();
    private final Map<String, Integer> eventCounter = new HashMap<>();

    public void analyze(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Linha lida: " + line);
                LogEntry entry = LogParser.parseLine(line);
                if (entry != null) {
                    System.out.println("Entrada válida: " + entry);
                    String ip = entry.getIp();
                    String eventType = entry.getEventType();

                    ipCounter.put(ip, ipCounter.getOrDefault(ip, 0) + 1);
                    if (eventType != null) {
                        eventCounter.put(eventType, eventCounter.getOrDefault(eventType, 0) + 1);
                    }
                } else {
                    System.out.println("Entrada inválida, ignorada");
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    public void printTopIps(int topN) {
        System.out.println("\n🔎 Top " + topN + " IPs suspeitos:");
        ipCounter.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(topN)
                .forEach(entry ->
                        System.out.println("IP: " + entry.getKey() + " | Ocorrências: " + entry.getValue()));
    }

    public void printEventSummary() {
        System.out.println("\n📊 Ocorrências por tipo de evento:");
        eventCounter.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    // Método para retornar top N IPs ordenados (para gráfico)
    public Map<String, Integer> getTopIps(int topN) {
        return ipCounter.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(topN)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    // Método para retornar o mapa completo de contagem de eventos (para gráfico)
    public Map<String, Integer> getEventCounts() {
        return new LinkedHashMap<>(eventCounter);
    }
}
