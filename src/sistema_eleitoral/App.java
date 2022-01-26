package src.sistema_eleitoral;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {

        if (args.length != 3) {
            System.out.println("Argumentos insuficientes ou em excesso.");
            System.exit(1);
        }

        //new File("Relatorios").mkdirs();

        Eleicao eleicao = new Eleicao();

        eleicao.setDataEleicao(args[2]);

        // Leitura do arquivo de partidos

        eleicao.leArquivoCandidatos(args[0]);

        // Leitura do arquivo de candidatos

        eleicao.leArquivoPartidos(args[1]);

        // ============= MAIN ====================

        eleicao.associaPartidoCandidato();

        eleicao.somaNumeroDeVagas(); // Relatorio 1

        eleicao.imprimeCandidatosEleitos(); // Relatorio 2

        eleicao.imprimeCandidatosMaisVotados(); // Relatorio 3

        eleicao.imprimeNaoEleitosMasSeriamEmMajoritario(); // Relatorio 4
        eleicao.imprimeEleitosMasNaoSeriamEmMajoritario(); // Relatorio 5

        // Votos totalizados por partidos e número de candidatos eleitos (6)

        eleicao.votosTotaisCandidatosEleitos();

        // Votos legenda com porcentagem (7)

        eleicao.votosLegendaPorPartidoPorcentagem();

        // Primeiros e Ultimos colocados (8)

        eleicao.primeiroUltimoColocadoPorPartido(); // Tratar casos que não tem último colocado

        // Distribuição de eleitos para cada faixa etária (9)

        eleicao.distribuicaoEleitosPorIdade();

        // Eleitos por sexo (10)

        eleicao.eleitosPorSexo();

        // Contabilizacao dos votos (11)

        eleicao.contabilizacaoDosVotos();

    }
}