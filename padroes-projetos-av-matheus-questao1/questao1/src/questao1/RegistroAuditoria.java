package questao1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Registro imutavel de um provisionamento para fins de auditoria (RF07). */
public final class RegistroAuditoria {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private final LocalDateTime momento;
    private final String nomeAmbiente;
    private final String provedor;
    private final boolean aceito;
    private final String motivo;

    public RegistroAuditoria(String nomeAmbiente, String provedor, boolean aceito, String motivo) {
        this.momento = LocalDateTime.now();
        this.nomeAmbiente = nomeAmbiente;
        this.provedor = provedor;
        this.aceito = aceito;
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        String resultado = aceito ? "ACEITO" : "RECUSADO";
        return FORMATO.format(momento) + " | " + nomeAmbiente + " | " + provedor
                + " | " + resultado + " | " + motivo;
    }
}
