package questao1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Singleton (GoF criacional) da configuracao (RNF04).
 * O arquivo config.properties e lido uma unica vez, na criacao da instancia,
 * feita pelo holder estatico (thread-safe sem bloqueio explicito).
 * Qualquer classe obtem os mesmos valores via Configuracao.instancia(),
 * sem receber configuracao por parametro.
 */
public final class Configuracao {

    private final String regiao;
    private final double orcamentoMensal;

    private Configuracao() {
        Properties propriedades = new Properties();
        try (InputStream entrada = abrirArquivo()) {
            if (entrada == null) {
                throw new IllegalStateException("config.properties nao encontrado no diretorio de execucao nem no classpath.");
            }
            propriedades.load(entrada);
        } catch (IOException e) {
            throw new IllegalStateException("Falha ao ler config.properties: " + e.getMessage(), e);
        }
        this.regiao = propriedades.getProperty("regiao", "sem-regiao");
        this.orcamentoMensal = Double.parseDouble(propriedades.getProperty("orcamento.mensal.maximo", "0"));
    }

    private InputStream abrirArquivo() throws IOException {
        File arquivoLocal = new File("config.properties");
        if (arquivoLocal.exists()) {
            return new FileInputStream(arquivoLocal);
        }
        return Configuracao.class.getResourceAsStream("/config.properties");
    }

    private static final class Guardiao {
        private static final Configuracao UNICA = new Configuracao();
    }

    public static Configuracao instancia() {
        return Guardiao.UNICA;
    }

    public String getRegiao() {
        return regiao;
    }

    public double getOrcamentoMensal() {
        return orcamentoMensal;
    }
}
