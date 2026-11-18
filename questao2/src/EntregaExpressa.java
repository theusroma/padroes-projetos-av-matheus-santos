// decorador concreto: entrega expressa (450 fixos)
public class EntregaExpressa extends ServicoAdicionalDecorator {
    public EntregaExpressa(Fatura faturaEnvolvida) {
        super(faturaEnvolvida);
    }

    @Override
    public String getDescricao() {
        return faturaEnvolvida.getDescricao() + "\n  + Entrega expressa: R$ 450.0";
    }

    @Override
    public double getValorTotal() {
        return faturaEnvolvida.getValorTotal() + 450.0;
    }
}