package questao1;

/**
 * Recurso cujo custo mensal nao depende de quantidade (maquina e fila).
 * Classe interna de implementacao compartilhada pelas duas fabricas.
 */
final class RecursoFixo implements MaquinaVirtual, ArmazenamentoObjetos, FilaMensagens {

    private final String tipo;
    private final String id;
    private final double custoMensal;

    RecursoFixo(String tipo, String id, double custoMensal) {
        this.tipo = tipo;
        this.id = id;
        this.custoMensal = custoMensal;
    }

    @Override
    public String identificador() {
        return id;
    }

    @Override
    public double custoMensal() {
        return custoMensal;
    }

    @Override
    public String tipoRecurso() {
        return tipo;
    }
}
