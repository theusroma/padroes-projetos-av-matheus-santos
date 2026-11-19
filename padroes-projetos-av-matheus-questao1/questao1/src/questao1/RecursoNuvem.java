package questao1;

/**
 * Contrato comum de todo recurso provisionado na nuvem.
 * Cada recurso sabe informar o proprio identificador e o custo mensal.
 */
public interface RecursoNuvem {

    String identificador();

    double custoMensal();

    String tipoRecurso();
}
