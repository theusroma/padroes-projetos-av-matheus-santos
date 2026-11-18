// interface observer para quem acompanha os lances de um lote
public interface ObservadorLance {
    void atualizar(String nomeLote, double novoValor, ObservadorLance autor);
}