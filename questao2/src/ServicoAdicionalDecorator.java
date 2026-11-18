// decorator abs para embrulhar a fatura com servico adicional
public abstract class ServicoAdicionalDecorator implements Fatura {
    protected Fatura faturaEnvolvida;

    public ServicoAdicionalDecorator(Fatura faturaEnvolvida) {
        this.faturaEnvolvida = faturaEnvolvida;
    }

    @Override
    public double getValorBaseLote() {
        return faturaEnvolvida.getValorBaseLote();
    }
}