import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {

        if(args.length != 3){
            System.out.println("Argumentos insuficientes ou em excesso.");
            System.exit(1);
        }

        Eleicao eleicao = new Eleicao();

        //Leitura do arquivo de partidos

        eleicao.setDataEleicao(args[2]);

        eleicao.leArquivoCandidatos(args[0]);   

        //Leitura do arquivo de candidatos

        eleicao.leArquivoPartidos(args[1]); 

        // ============= MAIN ====================

        eleicao.associaPartidoCandidato();


        eleicao.somaNumeroDeVagas(); //Relatorio 1

        eleicao.imprimeCandidatosEleitos(); // Relatorio 2
        System.out.print("\n");

        eleicao.imprimeCandidatosMaisVotados(); // Relatorio 3
        System.out.print("\n");

        eleicao.imprimeNaoEleitosMasSeriamEmMajoritario(); // Relatorio 4
        eleicao.imprimeEleitosMasNaoSeriamEmMajoritario(); // Relatorio 5


        
        // ============= TESTES DA MILLA ====================

        //eleicao.ordenaCandidatosPorVotoNominal(eleicao.getCandidatos());

        eleicao.imprimeNomeCandidatos();  

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

        eleicao.contabilizacaoDosVotos();

    }
}