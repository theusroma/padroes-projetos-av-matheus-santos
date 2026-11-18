// componente concreto que representa a fatura inicial apenas com o lote arrematado
public class FaturaBase implements Fatura {
    private String nomeLote;
    private double valorVenda;

    public FaturaBase(Lote lote) {
        this.nomeLote = lote.getNome();
        this.valorVenda = lote.getLanceAtual();
    }

    @Override
    public String getDescricao() {
        return "Lote: " + nomeLote + " | Valor de venda: R$ " + valorVenda;
    }

    @Override
    public double getValorTotal() {
        return valorVenda;
    }

    @Override
    public double getValorBaseLote() {
        return valorVenda;
    }
}