package questao1;

import java.util.concurrent.atomic.AtomicLong;

/** Fabrica concreta da Azure. Nenhuma outra classe enxerga os produtos Azure. */
public class FabricaAzure implements FabricaNuvem {

    private static final double CUSTO_HORA_MAQUINA = 0.12;
    private static final double CUSTO_GB_MES = 0.02;
    private static final double CUSTO_FILA_MES = 0.50;

    private final AtomicLong seqMaquina = new AtomicLong();
    private final AtomicLong seqArmazenamento = new AtomicLong();
    private final AtomicLong seqFila = new AtomicLong();

    @Override
    public String nomeProvedor() {
        return "Azure";
    }

    @Override
    public MaquinaVirtual criarMaquinaVirtual() {
        long numero = seqMaquina.incrementAndGet();
        return new RecursoFixo("MaquinaVirtual", "vm-" + numero, CUSTO_HORA_MAQUINA * 730.0);
    }

    @Override
    public ArmazenamentoObjetos criarArmazenamento(int gigabytes) {
        long numero = seqArmazenamento.incrementAndGet();
        return new RecursoVariavel("Armazenamento", "blob://container-" + numero, CUSTO_GB_MES, gigabytes);
    }

    @Override
    public FilaMensagens criarFila() {
        long numero = seqFila.incrementAndGet();
        return new RecursoFixo("Fila", "sb-fila-" + numero, CUSTO_FILA_MES);
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
