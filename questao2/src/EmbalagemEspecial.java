// decorador concreto: embalagem especial (180 fixos)
public class EmbalagemEspecial extends ServicoAdicionalDecorator {
    public EmbalagemEspecial(Fatura faturaEnvolvida) {
        super(faturaEnvolvida);
    }

    @Override
    public String getDescricao() {
        return faturaEnvolvida.getDescricao() + "\n  + Embalagem especial: R$ 180.0";
    }

    @Override
    public double getValorTotal() {
        return faturaEnvolvida.getValorTotal() + 180.0;
    }
}