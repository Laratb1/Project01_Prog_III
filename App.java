import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Locale;

public class App {
    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        try(FileInputStream fp = new FileInputStream(args[0])){
            InputStreamReader p = new InputStreamReader(fp, "UTF-8");
            BufferedReader br = new BufferedReader(p);
            //String a = br.readLine();
            java.util.Scanner s = new java.util.Scanner(br);
            s.useDelimiter(",");
            while(br != null){
                String numero = s.next();
                System.out.println(numero);
                int votos_nominais = s.nextInt();
                System.out.println(votos_nominais);
                String situacao = s.next();
                System.out.println(situacao);
                String nome = s.next();
                System.out.println(nome);
                String nome_urna = s.next();
                System.out.println(nome_urna);
                String sexo = s.next();
                System.out.println(sexo);
                String data_nasc = s.next();
                System.out.println(data_nasc);
                String destino_voto = s.next();
                System.out.println(destino_voto);
                String numero_partido = s.next();
                System.out.println(numero_partido);
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
    }

}