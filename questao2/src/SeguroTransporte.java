// decorador concreto seguro de transporte = 1,5% do valor de venda do lote
public class SeguroTransporte extends ServicoAdicionalDecorator {
    public SeguroTransporte(Fatura faturaEnvolvida) {
        super(faturaEnvolvida);
    }

    @Override
    public String getDescricao() {
        double custo = getValorBaseLote() * 0.015;
        return faturaEnvolvida.getDescricao() + "\n  + Seguro de transporte (1,5%): R$ " + custo;
    }

    @Override
    public double getValorTotal() {
        return faturaEnvolvida.getValorTotal() + (getValorBaseLote() * 0.015);
    }
}