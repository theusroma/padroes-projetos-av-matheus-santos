package questao1;

/**
 * RF08: quatro provisionamentos - dois na Aws, um no Azure e um recusado
 * por orcamento. Dois deles rodam simultaneamente em threads distintas
 * e o historico final da auditoria contem os quatro registros.
 *
 * A escolha do provedor acontece somente aqui (RNF03/RNF06): basta trocar
 * a fabrica passada ao Provisionador; nenhuma outra classe muda.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Configuracao config = Configuracao.instancia();
        System.out.println("Regiao padrao: " + config.getRegiao()
                + String.format(" | Orcamento mensal por ambiente: R$ %.2f%n", config.getOrcamentoMensal()));

        Provisionador provedorAws = new Provisionador(new FabricaAws());
        Provisionador provedorAzure = new Provisionador(new FabricaAzure());

        // 1) Aws - executado na thread principal
        provedorAws.provisionar(new SolicitacaoAmbiente("portal-web", 2, 120));

        // 2) e 3) disparados SIMULTANEAMENTE em threads distintas
        Thread threadAws = new Thread(() ->
                provedorAws.provisionar(new SolicitacaoAmbiente("data-lake", 1, 500)));
        Thread threadAzure = new Thread(() ->
                provedorAzure.provisionar(new SolicitacaoAmbiente("apis-internas", 3, 200)));
        threadAws.start();
        threadAzure.start();
        threadAws.join();
        threadAzure.join();

        // 4) Aws - recusado por estourar o orcamento (nenhum recurso criado)
        provedorAws.provisionar(new SolicitacaoAmbiente("cluster-bigdata", 40, 5000));

        // RF07: historico completo, na ordem em que os registros foram feitos
        AuditoriaCentral.instancia().imprimirHistorico();
    }
}
