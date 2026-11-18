// decorador concreto: certificado de autenticidade com 300 fixos
public class CertificadoAutenticidade extends ServicoAdicionalDecorator {
    public CertificadoAutenticidade(Fatura faturaEnvolvida) {
        super(faturaEnvolvida);
    }

    @Override
    public String getDescricao() {
        return faturaEnvolvida.getDescricao() + "\n  + Certificado de autenticidade: R$ 300.0";
    }

    @Override
    public double getValorTotal() {
        return faturaEnvolvida.getValorTotal() + 300.0;
    }
}