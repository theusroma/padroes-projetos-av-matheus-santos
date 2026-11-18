public class Main {
    public static void main(String[] args) {
        // criacao dos interessados (observer)
        Participante matheus = new Participante("Matheus");
        Participante leticia = new Participante("Leticia");
        Participante montserrat = new Participante("Montserrat");
        PainelLeiloeiro painel = new PainelLeiloeiro();
        AuditorLeilao auditor = new AuditorLeilao();

        // dois lotes de categorias diferentes com suas estrategias iniciais
        Lote loteArte = new Lote("Quadro Abstrato", "Arte", 1000.0, 1500.0, new ComissaoArte());
        Lote loteEletronico = new Lote("Notebook Gamer", "Eletronicos", 3000.0, 5000.0, new ComissaoEletronicos());

        // inscricao dos observadores no lote de arte
        loteArte.adicionarObservador(matheus);
        loteArte.adicionarObservador(leticia);
        loteArte.adicionarObservador(montserrat);
        loteArte.adicionarObservador(painel);
        loteArte.adicionarObservador(auditor);

        // inscricao dos observadores no lote de eletronicos
        loteEletronico.adicionarObservador(leticia);
        loteEletronico.adicionarObservador(matheus);
        loteEletronico.adicionarObservador(auditor);

        System.out.println("LANCES NO LOTE DE ARTE");
        loteArte.darLance(leticia, 1020.0); // recusado (menor que 5%)
        loteArte.darLance(leticia, 1100.0); // aceito

        // saida de um participante do acompanhamento
        loteArte.removerObservador(montserrat);

        loteArte.darLance(matheus, 1600.0); // aceito (carlos nao recebe mais)

        // campanha: troca da regra de comissao de arte (20%) para eletronicos (12%) antes de encerrar
        loteArte.setRegraComissao(new ComissaoEletronicos());

        // encerramento do lote vendido
        loteArte.encerrar();
        loteArte.imprimirAcertoVendedor();

        // fatura com 3 servicos adicionais, sendo embalagem especial contratada duas vezes (decorator)
        Fatura fatura = new FaturaBase(loteArte);
        fatura = new SeguroTransporte(fatura);
        fatura = new EmbalagemEspecial(fatura);
        fatura = new EmbalagemEspecial(fatura);
        fatura = new CertificadoAutenticidade(fatura);

        System.out.println("\nFATURA DO COMPRADOR");
        System.out.println(fatura.getDescricao());
        System.out.println("Total a pagar: R$ " + fatura.getValorTotal());

        System.out.println("\nLANCES NO LOTE DE ELETRONICOS");
        loteEletronico.darLance(leticia, 3500.0); // aceito, mas abaixo do preco de reserva (5000)
        loteEletronico.encerrar(); // encerrado como nao vendido
        loteEletronico.darLance(matheus, 5500.0); // recusado pois o lote ja encerrou

        // historico do auditor
        auditor.imprimirRegistros();
    }
}