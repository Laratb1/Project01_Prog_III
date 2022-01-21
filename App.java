import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {

        if(args.length != 2){
            System.out.println("Argumentos insuficientes ou em excesso.");
            System.exit(1);
        }

        Eleicao eleicao = new Eleicao();

        //Leitura do arquivo de partidos

        eleicao.leArquivoPartidos(args[0]);    

        //Leitura do arquivo de candidatos

        eleicao.leArquivoCandidatos(args[1]);

        // ============= MAIN ====================

        eleicao.associaPartidoCandidato();


        eleicao.somaNumeroDeVagas();
        System.out.println("Numero de vagas (numeros de eleitos): " + eleicao.getNumVagas());
        System.out.print("\n");

        eleicao.imprimeCandidatosEleitos();
        System.out.print("\n");

        eleicao.imprimeCandidatosMaisVotados();
        System.out.print("\n");

        eleicao.imprimeNaoEleitosMasSeriamEmMajoritario();
        eleicao.imprimeEleitosMasNaoSeriamEmMajoritario();


        
        // ============= TESTES DA MILLA ====================

        //eleicao.ordenaCandidatosPorVotoNominal(eleicao.getCandidatos());

        /*eleicao.imprimeNomeCandidatos();

        System.out.print("\n");
        System.out.println("====== Votos totalizados por partidos e número de candidatos eleitos (6) ======");
        System.out.print("\n");

        eleicao.votosTotaisCandidatosEleitos();

        System.out.print("\n");
        System.out.println("====== Primeiros e Ultimos colocados (8) ======");
        System.out.print("\n");

        eleicao.primeiroUltimoColocadoPorPartido();  //Tratar casos que não tem último colocado

        System.out.print("\n");
        System.out.println("====== Votos legenda com porcentagem (7) ======");
        System.out.print("\n");

        eleicao.votosLegendaPorPartidoPorcentagem();

        System.out.print("\n");
        System.out.println("====== Distribuição de eleitos para cada faixa etária (9) ======");
        System.out.print("\n");

        eleicao.distribuicaoEleitosPorIdade();

        System.out.print("\n");
        System.out.println("====== Eleitos por sexo (10) ======");
        System.out.print("\n");

        eleicao.eleitosPorSexo();

        System.out.print("\n");
        System.out.println("====== Contabilizacao dos votos (11) ======");
        System.out.print("\n");

        eleicao.contabilizacaoDosVotos();*/

    }
}