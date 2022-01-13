public class App {
    public static void main(String[] args) {
    
        LeituraArquivo.lerArquivoPartidos(args);
        System.out.println("\nCandidatos: \n");
        LeituraArquivo.lerArquivoCandidatos(args);

    }

    
}