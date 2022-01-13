public class App {
    public static void main(String[] args) {

        Eleicao eleicao = new Eleicao();
    
        LeituraArquivo.lerArquivoPartidos(args, eleicao);
        System.out.println("\nCandidatos: \n");
        LeituraArquivo.lerArquivoCandidatos(args, eleicao);

    }

    
}