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

            Partido partido = new Partido();
            
            while(s.hasNext()){
                linha = s.nextLine();
                String[] info = linha.split(",");

                partido.setNumeroPartido(info[0]);
                partido.setVotosLegenda(Integer.parseInt(info[1]));
                partido.setNomePartido(info[2]);
                partido.setSiglaPartido(info[3]);

                eleicao.setPartido(partido);

                eleicao.imprimeNomePartidos(); //teste

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

            Candidato candidato = new Candidato();
            
            while(s.hasNext()){
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

                eleicao.imprimeNomeCandidatos();
                

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

    }

    
}