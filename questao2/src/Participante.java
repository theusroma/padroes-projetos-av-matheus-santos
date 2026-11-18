// observador concreto que signiicfa um participante do leilao
public class Participante implements ObservadorLance {
    private String nome;

    public Participante(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public void atualizar(String nomeLote, double novoValor, ObservadorLance autor) {
        // nao notifica quem deu o proprio lance
        if (this != autor) {
            System.out.println("[Participante " + nome + "] Novo lance no lote '" + nomeLote + "': R$ " + novoValor);
        }
    }
}