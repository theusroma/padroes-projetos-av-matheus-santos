import java.util.ArrayList;
import java.util.List;

// sujeito observado (observer) e contexto da estrategia de comissao (strategy)
public class Lote {
    private String nome;
    private String categoria;
    private double precoInicial;
    private double precoReserva;
    private double lanceAtual;
    private Participante vencedor;
    private boolean aberto = true;
    private boolean vendido = false;
    private RegraComissao regraComissao;
    private List<ObservadorLance> observadores = new ArrayList<>();

    public Lote(String nome, String categoria, double precoInicial, double precoReserva, RegraComissao regraComissao) {
        this.nome = nome;
        this.categoria = categoria;
        this.precoInicial = precoInicial;
        this.precoReserva = precoReserva;
        this.lanceAtual = precoInicial;
        this.regraComissao = regraComissao;
    }

    // metodos do observer
    public void adicionarObservador(ObservadorLance obs) {
        observadores.add(obs);
    }

    public void removerObservador(ObservadorLance obs) {
        observadores.remove(obs);
    }

    private void notificarObservadores(ObservadorLance autor) {
        for (ObservadorLance obs : observadores) {
            obs.atualizar(nome, lanceAtual, autor);
        }
    }

    // permite trocar a regra de comissao em tempo de execucao (campanhas)
    public void setRegraComissao(RegraComissao novaRegra) {
        this.regraComissao = novaRegra;
    }

    // registro de lances com validacao de minimo 5% acima do lance atual
    public void darLance(Participante participante, double valor) {
        if (!aberto) {
            System.out.println("Lance recusado: lote '" + nome + "' ja esta encerrado.");
            return;
        }
        double minimo = lanceAtual * 1.05;
        if (valor < minimo) {
            System.out.println("Lance recusado de " + participante.getNome() + " (R$ " + valor + "): precisa ser pelo menos R$ " + minimo);
            return;
        }
        this.lanceAtual = valor;
        this.vencedor = participante;
        System.out.println("Lance aceito de " + participante.getNome() + " no lote '" + nome + "': R$ " + valor);
        notificarObservadores(participante);
    }

    // encerra o lote verificando o preco de reserva
    public void encerrar() {
        this.aberto = false;
        if (vencedor != null && lanceAtual >= precoReserva) {
            this.vendido = true;
            System.out.println("\nLote '" + nome + "' VENDIDO para " + vencedor.getNome() + " por R$ " + lanceAtual);
        } else {
            this.vendido = false;
            System.out.println("\nLote '" + nome + "' NAO VENDIDO (nao atingiu o preco de reserva de R$ " + precoReserva + ")");
        }
    }

    // imprime o acerto do vendedor usando a estrategia vigente
    public void imprimirAcertoVendedor() {
        if (!vendido) {
            System.out.println("Lote '" + nome + "' nao foi vendido, sem acerto.");
            return;
        }
        double comissao = regraComissao.calcular(lanceAtual);
        double valorLiquido = lanceAtual - comissao;
        System.out.println("\n--- ACERTO DO VENDEDOR (" + nome + ") ---");
        System.out.println("Valor de venda: R$ " + lanceAtual);
        System.out.println("Comissao cobrada: R$ " + comissao);
        System.out.println("Valor liquido ao vendedor: R$ " + valorLiquido);
    }

    public String getNome() {
        return nome;
    }

    public double getLanceAtual() {
        return lanceAtual;
    }

    public boolean isVendido() {
        return vendido;
    }
}