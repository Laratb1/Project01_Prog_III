/**
 * Autoras: Lara Tagarro e Milla Pereira
 * Data: 10/02/2022
 * O programa visa analisar os dados referentes
 * a uma eleicao e gerar relatórios com a informações
 * dos candidatos e partidos.
 */

package src.sistema_eleitoral;

public class App {
    public static void main(String[] args) {

        if (args.length != 3) { // Verifica se o número de argumentos é igual a 3
            System.out.println("Argumentos insuficientes ou em excesso.");
            System.exit(1);
        }

        Eleicao eleicao = new Eleicao(); // Instancia a classe Eleicao

        eleicao.setDataEleicao(args[2]); // Atribui a data da eleicao a propriedade dataEleicao

        eleicao.leArquivoCandidatos(args[0]); // Leitura do arquivo de partidos

        eleicao.leArquivoPartidos(args[1]); // Leitura do arquivo de candidatos

        // ============= Gerção dos relatórios ====================

        eleicao.associaPartidoCandidato();

        // Número de vagas = quantidade de candidatos eleitos (Relatorio 1)

        eleicao.somaNumeroDeVagas();

        // Candidatos eleitos na eleição (Relatorio 2)

        eleicao.candidatosEleitos(); 

        // Candidatos mais votados, por votos nominal (Relatorio 3)

        eleicao.candidatosMaisVotados(); 

        // Candidatos que nao foram eleitos, mas seriam se a elaição fosse majoritária (Relatorio 4)  

        eleicao.naoForamEleitosMasSeriamEmMajoritario();

        // Candidatos que foram eleitos, mas não seriam se fosse por voto majoritario (Relatorio 5)
        
        eleicao.foramEleitosMasNaoSeriamEmMajoritario(); 

        // Votos totalizados por partidos e número de candidatos eleitos (Relatorio 6)

        eleicao.votosTotaisCandidatosEleitos();

        // Votos legenda com porcentagem (Relatorio 7)

        eleicao.votosLegendaPorPartidoPorcentagem();

        // Primeiros e Ultimos colocados (Relatorio 8)

        eleicao.primeiroUltimoColocadoPorPartido();

        // Distribuição de eleitos para cada faixa etária (Relatorio 9)

        eleicao.distribuicaoEleitosPorIdade();

        // Eleitos por sexo (Relatorio 10)

        eleicao.eleitosPorSexo();

        // Contabilizacao dos votos (Relatorio 11)

        eleicao.contabilizacaoDosVotos();

    }
}