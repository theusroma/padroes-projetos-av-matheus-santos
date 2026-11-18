// estrategia de comissao para eletronicos: 12% com minimo de 50
public class ComissaoEletronicos implements RegraComissao {
    @Override
    public double calcular(double valorVenda) {
        double comissao = valorVenda * 0.12;
        return comissao < 50.0 ? 50.0 : comissao;
    }
}