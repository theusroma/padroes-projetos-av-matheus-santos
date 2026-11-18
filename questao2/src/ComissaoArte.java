// estrategia de comissao para arte: 20% do valor
public class ComissaoArte implements RegraComissao {
    @Override
    public double calcular(double valorVenda) {
        return valorVenda * 0.20;
    }
}