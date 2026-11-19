package questao1;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton da auditoria (RNF05): historico unico em todo o programa,
 * acessivel por qualquer classe sem passar por parametro.
 * Todos os metodos sao sincronizados, de modo que provisionamentos
 * disparados em threads distintas nunca perdem nem corrompem registros,
 * e a ordem de insercao e preservada.
 */
public final class AuditoriaCentral {

    private final List<RegistroAuditoria> registros = new ArrayList<>();

    private AuditoriaCentral() {
    }

    private static final class Guardiao {
        private static final AuditoriaCentral UNICA = new AuditoriaCentral();
    }

    public static AuditoriaCentral instancia() {
        return Guardiao.UNICA;
    }

    public synchronized void registrar(RegistroAuditoria registro) {
        registros.add(registro);
    }

    public synchronized int totalRegistros() {
        return registros.size();
    }

    public synchronized void imprimirHistorico() {
        System.out.println();
        System.out.println("=========== HISTORICO COMPLETO DE AUDITORIA ===========");
        for (RegistroAuditoria registro : registros) {
            System.out.println(registro);
        }
        System.out.println("Total de registros: " + registros.size());
        System.out.println("=======================================================");
    }
}
