// observador concreto que exibe o maior lance e o total de lances recebidos
public class PainelLeiloeiro implements ObservadorLance {
    private double maiorLance = 0;
    private int totalLances = 0;

    @Override
    public void atualizar(String nomeLote, double novoValor, ObservadorLance autor) {
        totalLances++;
        if (novoValor > maiorLance) {
            maiorLance = novoValor;
        }
        System.out.println("[Painel Leiloeiro - " + nomeLote + "] Maior lance: R$ " + maiorLance + " | Total de lances: " + totalLances);
    }
}