package questao1;

import java.util.concurrent.atomic.AtomicLong;

/** Fabrica concreta da Aws. Unico lugar do sistema que conhece os produtos Aws. */
public class FabricaAws implements FabricaNuvem {

    private static final double CUSTO_HORA_MAQUINA = 0.10;
    private static final double CUSTO_GB_MES = 0.023;
    private static final double CUSTO_FILA_MES = 0.40;

    // Sequencia por tipo de recurso (RF03), protegida contra threads simultaneas
    private final AtomicLong seqMaquina = new AtomicLong();
    private final AtomicLong seqArmazenamento = new AtomicLong();
    private final AtomicLong seqFila = new AtomicLong();

    @Override
    public String nomeProvedor() {
        return "Aws";
    }

    @Override
    public MaquinaVirtual criarMaquinaVirtual() {
        long numero = seqMaquina.incrementAndGet();
        return new RecursoFixo("MaquinaVirtual", "i-" + numero, CUSTO_HORA_MAQUINA * 730.0);
    }

    @Override
    public ArmazenamentoObjetos criarArmazenamento(int gigabytes) {
        long numero = seqArmazenamento.incrementAndGet();
        return new RecursoVariavel("Armazenamento", "s3://bucket-" + numero, CUSTO_GB_MES, gigabytes);
    }

    @Override
    public FilaMensagens criarFila() {
        long numero = seqFila.incrementAndGet();
        return new RecursoFixo("Fila", "sqs-fila-" + numero, CUSTO_FILA_MES);
    }

    @Override
    public double precoHoraMaquina() {
        return CUSTO_HORA_MAQUINA;
    }

    @Override
    public double precoGbArmazenamentoPorMes() {
        return CUSTO_GB_MES;
    }

    @Override
    public double precoFixoFilaPorMes() {
        return CUSTO_FILA_MES;
    }
}
