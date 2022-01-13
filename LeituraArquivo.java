import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class LeituraArquivo {

    public static void lerArquivoPartidos(String[] arquivo) {
        if(arquivo.length != 1){
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        try(FileInputStream fp = new FileInputStream(arquivo[0])){
            InputStreamReader p = new InputStreamReader(fp, "UTF-8");
            BufferedReader br = new BufferedReader(p);
            Scanner s = new Scanner(br);

            s.useDelimiter(",");
            s.nextLine(); //pula a primeira linha 
            String linha = new String();

            Partido partido = new Partido();
            Eleicao eleicao = new Eleicao();
            
            while(s.hasNext()){
                linha = s.nextLine();
                String[] info = linha.split(",");

                partido.setNumeroPartido(info[0]);
                partido.setVotosLegenda(Integer.parseInt(info[1]));
                partido.setNomePartido(info[2]);
                partido.setSiglaPartido(info[3]);

                eleicao.setPartido(partido);

                eleicao.imprimeNomePartidos(); //teste
                System.out.print("\n");

            }
            s.close();
        }
        catch(FileNotFoundException exc){
            System.out.println("Arquivo " + arquivo + "nao encontrado!");
            System.exit(1);
        }
        catch(IOException exc){
            System.out.println("Erro durante a leitura do arquivo" + arquivo);
            System.exit(1);
        }

    }
}
