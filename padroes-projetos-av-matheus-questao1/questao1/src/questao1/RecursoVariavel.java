package questao1;

/**
 * Recurso cujo custo mensal depende da quantidade contratada (armazenamento).
 */
final class RecursoVariavel implements ArmazenamentoObjetos {

    private final String tipo;
    private final String id;
    private final double precoPorUnidade;
    private final int unidades;

    RecursoVariavel(String tipo, String id, double precoPorUnidade, int unidades) {
        this.tipo = tipo;
        this.id = id;
        this.precoPorUnidade = precoPorUnidade;
        this.unidades = unidades;
    }

    @Override
    public String identificador() {
        return id;
    }

    @Override
    public double custoMensal() {
        return precoPorUnidade * unidades;
    }

    @Override
    public String tipoRecurso() {
        return tipo;
    }
}
