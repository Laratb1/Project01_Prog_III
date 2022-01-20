import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Eleicao eleicao = new Eleicao();

        //Leitura do arquivo de partidos

        if(args.length != 2){
            System.out.println("Argumentos insuficientes ou em excesso.");
            System.exit(1);
        }

        try(FileInputStream fp = new FileInputStream(args[0])){
            InputStreamReader p = new InputStreamReader(fp, "UTF-8");
            BufferedReader br = new BufferedReader(p);
            Scanner s = new Scanner(br);

            s.useDelimiter(",");
            s.nextLine(); //pula a primeira linha 
            String linha = new String();
            
            while(s.hasNext()){
                Partido partido = new Partido();
                linha = s.nextLine();
                String[] info = linha.split(",");

                partido.setNumeroPartido(info[0]);
                partido.setVotosLegenda(Integer.parseInt(info[1]));
                partido.setNomePartido(info[2]);
                partido.setSiglaPartido(info[3]);

                eleicao.setPartido(partido);
            }
            s.close();
        }
        catch(FileNotFoundException exc){
            System.out.println("Arquivo " + args[0] + "nao encontrado!");
            System.exit(1);
        }
        catch(IOException exc){
            System.out.println("Erro durante a leitura do arquivo" + args[0]);
            System.exit(1);
        }

        //Leitura do arquivo de candidatos

        if(args.length != 2){
            System.out.println("Argumentos insuficientes ou em excesso.");
            System.exit(1);
        }

        try(FileInputStream fp = new FileInputStream(args[1])){
            InputStreamReader p = new InputStreamReader(fp, "UTF-8");
            BufferedReader br = new BufferedReader(p);
            Scanner s = new Scanner(br);

            s.useDelimiter(",");
            s.nextLine(); 
            String linha = new String();

            //Candidato candidato = new Candidato();
            
            while(s.hasNext()){
                Candidato candidato = new Candidato();
                linha = s.nextLine();
                String[] info = linha.split(",");
                

                candidato.setNumeroCandidato(info[0]);
                candidato.setVotosNominaisCandidato(Integer.parseInt(info[1]));
                candidato.setSituacaoCandidato(info[2]);
                candidato.setNomeCandidato(info[3]);
                candidato.setNomeUrnaCandidato(info[4]);
                candidato.setSexoCandidato(info[5]);
                candidato.setDataNasCandidato(info[6]);
                candidato.setDestinoVotoCandidato(info[7]);
                candidato.setNumeroPartidoCandidato(info[8]);

                eleicao.setCandidato(candidato);                

            }
            s.close();
        }
        catch(FileNotFoundException exc){
            System.out.println("Arquivo candidatos.csv nao encontrado!");
            System.exit(1);
        }
        catch(IOException exc){
            System.out.println("Erro durante a leitura do arquivo candidatos.csv");
            System.exit(1);
        }

        // ============= MAIN ====================

        /*eleicao.associaPartidoCandidato();


        int nVagas = eleicao.somaNumeroDeVagas();
        System.out.println("Numero de vagas (numeros de eleitos): " + nVagas);
        System.out.print("\n");

        eleicao.imprimeCandidatosEleitos();
        System.out.print("\n");

        eleicao.imprimeCandidatosMaisVotados(nVagas);
        System.out.print("\n");

        eleicao.imprimeNaoEleitosMasSeriamEmMajoritario();*/
        
        // ============= TESTES DA MILLA ====================

        //eleicao.ordenaCandidatosPorVotoNominal(eleicao.getCandidatos());

        eleicao.imprimeNomeCandidatos();

        System.out.print("\n");
        System.out.println("====== Votos totalizados por partidos e número de candidatos eleitos (7) ======");
        System.out.print("\n");

        eleicao.votosTotaisCandidatosEleitos();

        System.out.print("\n");
        System.out.println("====== Primeiros e Ultimos colocados (9) ======");
        System.out.print("\n");

        eleicao.primeiroUltimoColocadoPorPartido();  //Tratar casos que não tem último colocado

        System.out.print("\n");
        System.out.println("====== Votos legenda com porcentagem (8) ======");
        System.out.print("\n");

        eleicao.votosLegendaPorPartidoPorcentagem();

        System.out.print("\n");
        System.out.println("====== Distribuição de eleitos para cada faixa etária (10) ======");
        System.out.print("\n");

        eleicao.distribuicaoEleitosPorIdade();

        System.out.print("\n");
        System.out.println("====== Eleitos por sexo (11) ======");
        System.out.print("\n");

        eleicao.eleitosPorSexo();

        System.out.print("\n");
        System.out.println("====== Contabilizacao dos votos (12) ======");
        System.out.print("\n");

        eleicao.contabilizacaoDosVotos();

    }
}