package questao1;

import java.util.ArrayList;
import java.util.List;

/** Ambiente provisionado, com os tres tipos de recurso do MESMO provedor (RNF01). */
public class Ambiente {

    private final String nome;
    private final String provedor;
    private final String regiao;
    private final List<RecursoNuvem> recursos;
    private final double custoMensalEstimado;

    public Ambiente(String nome, String provedor, String regiao,
                    List<RecursoNuvem> recursos, double custoMensalEstimado) {
        this.nome = nome;
        this.provedor = provedor;
        this.regiao = regiao;
        this.recursos = new ArrayList<>(recursos);
        this.custoMensalEstimado = custoMensalEstimado;
    }

    /** Resumo exigido pelo RF06. */
    public void imprimirResumo() {
        System.out.println();
        System.out.println("--- Ambiente provisionado: " + nome + " ---");
        System.out.println("Provedor : " + provedor);
        System.out.println("Regiao   : " + regiao);
        for (RecursoNuvem recurso : recursos) {
            System.out.println("Recurso  : [" + recurso.tipoRecurso() + "] " + recurso.identificador());
        }
        System.out.printf("Custo mensal estimado: R$ %.2f%n", custoMensalEstimado);
    }
}
