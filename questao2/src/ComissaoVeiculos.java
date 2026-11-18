// estrategia de comissao para veiculos: 8% limitada a 5000
public class ComissaoVeiculos implements RegraComissao {
    @Override
    public double calcular(double valorVenda) {
        double comissao = valorVenda * 0.08;
        return comissao > 5000.0 ? 5000.0 : comissao;
    }
}