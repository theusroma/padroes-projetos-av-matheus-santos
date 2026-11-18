// estrategia de comissao para imoveis: 3% mais 1500 fixos
public class ComissaoImoveis implements RegraComissao {
    @Override
    public double calcular(double valorVenda) {
        return (valorVenda * 0.03) + 1500.0;
    }
}