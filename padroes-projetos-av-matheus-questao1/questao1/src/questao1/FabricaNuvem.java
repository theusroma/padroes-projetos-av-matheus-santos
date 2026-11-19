package questao1;

/**
 * Fabrica abstrata (Abstract Factory - GoF criacional).
 * Quem monta o ambiente enxerga apenas esta interface, nunca as classes
 * concretas de um provedor. Incluir um novo provedor (ex.: Gcp) significa
 * criar uma nova classe que implemente esta interface e nada mais.
 */
public interface FabricaNuvem {

    String nomeProvedor();

    MaquinaVirtual criarMaquinaVirtual();

    ArmazenamentoObjetos criarArmazenamento(int gigabytes);

    FilaMensagens criarFila();

    double precoHoraMaquina();

    double precoGbArmazenamentoPorMes();

    double precoFixoFilaPorMes();
}
