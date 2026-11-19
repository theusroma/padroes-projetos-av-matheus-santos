package questao1;

import java.util.ArrayList;
import java.util.List;

/**
 * Montador de ambientes (papel Cliente do Abstract Factory).
 * RNF02: conhece apenas a interface FabricaNuvem; nao referencia nenhuma
 * classe concreta de provedor e nao usa if/switch para decidir provedor -
 * a fabrica correta chega pronta pelo construtor.
 * RNF06: somente aqui (e nas fabricas) recursos sao criados.
 */
public class Provisionador {

    private final FabricaNuvem fabrica;

    public Provisionador(FabricaNuvem fabrica) {
        this.fabrica = fabrica;
    }

    public void provisionar(SolicitacaoAmbiente solicitacao) {
        // RF04: custo estimado antes de criar qualquer recurso (RF05)
        double custoEstimado = calcularCustoMensal(
                solicitacao.getQuantidadeMaquinas(), solicitacao.getGigabytesArmazenamento());

        double orcamento = Configuracao.instancia().getOrcamentoMensal();
        if (custoEstimado > orcamento) {
            AuditoriaCentral.instancia().registrar(new RegistroAuditoria(
                    solicitacao.getNomeAmbiente(), fabrica.nomeProvedor(), false,
                    String.format("orcamento excedido: estimado R$ %.2f > limite R$ %.2f", custoEstimado, orcamento)));
            System.out.println("[RECUSADO] " + solicitacao.getNomeAmbiente()
                    + String.format(" custaria R$ %.2f (orcamento R$ %.2f). Nenhum recurso criado.%n",
                    custoEstimado, orcamento));
            return;
        }

        // RNF01 garantido estruturalmente: toda recurso sai da MESMA fabrica
        List<RecursoNuvem> recursos = new ArrayList<>();
        for (int i = 0; i < solicitacao.getQuantidadeMaquinas(); i++) {
            recursos.add(fabrica.criarMaquinaVirtual());
        }
        recursos.add(fabrica.criarArmazenamento(solicitacao.getGigabytesArmazenamento()));
        recursos.add(fabrica.criarFila());

        Ambiente ambiente = new Ambiente(
                solicitacao.getNomeAmbiente(),
                fabrica.nomeProvedor(),
                Configuracao.instancia().getRegiao(),
                recursos,
                custoEstimado);

        ambiente.imprimirResumo();
        AuditoriaCentral.instancia().registrar(new RegistroAuditoria(
                solicitacao.getNomeAmbiente(), fabrica.nomeProvedor(), true,
                String.format("custo estimado R$ %.2f", custoEstimado)));
    }

    private double calcularCustoMensal(int maquinas, int gigabytes) {
        return (maquinas * fabrica.precoHoraMaquina() * 730.0)
                + (gigabytes * fabrica.precoGbArmazenamentoPorMes())
                + fabrica.precoFixoFilaPorMes();
    }
}
