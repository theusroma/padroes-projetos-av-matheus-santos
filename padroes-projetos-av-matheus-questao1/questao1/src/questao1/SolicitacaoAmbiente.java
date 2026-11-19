package questao1;

/** Dados de uma solicitacao de ambiente (RF03). */
public class SolicitacaoAmbiente {

    private final String nomeAmbiente;
    private final int quantidadeMaquinas;
    private final int gigabytesArmazenamento;

    public SolicitacaoAmbiente(String nomeAmbiente, int quantidadeMaquinas, int gigabytesArmazenamento) {
        this.nomeAmbiente = nomeAmbiente;
        this.quantidadeMaquinas = quantidadeMaquinas;
        this.gigabytesArmazenamento = gigabytesArmazenamento;
    }

    public String getNomeAmbiente() {
        return nomeAmbiente;
    }

    public int getQuantidadeMaquinas() {
        return quantidadeMaquinas;
    }

    public int getGigabytesArmazenamento() {
        return gigabytesArmazenamento;
    }
}
