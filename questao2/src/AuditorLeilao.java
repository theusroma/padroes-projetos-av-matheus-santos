import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// observador concreto que registra todos os lances aceitos com data e hora
public class AuditorLeilao implements ObservadorLance {
    private List<String> registros = new ArrayList<>();

    @Override
    public void atualizar(String nomeLote, double novoValor, ObservadorLance autor) {
        String registro = LocalDateTime.now() + " | Lote: " + nomeLote + " | Valor: R$ " + novoValor;
        registros.add(registro);
        System.out.println("[Auditor] Registrado: " + registro);
    }

    public void imprimirRegistros() {
        System.out.println("\n--- AUDITORIA DO LEILAO ---");
        registros.forEach(System.out::println);
    }
}