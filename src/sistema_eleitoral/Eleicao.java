package src.sistema_eleitoral;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Eleicao {
    private LinkedList<Candidato> candidatos = new LinkedList<>(); // Lista de candidatos
    private LinkedList<Partido> partidos = new LinkedList<>(); // Lista de partidos
    private int numVagas; // Numero de eleitos 
    private int[] dataEleicao = new int[3]; // Data passada na linha de comando

    // Getters

    public LinkedList<Candidato> getCandidatos() {
        return this.candidatos;
    }

    public LinkedList<Partido> getPartidos() {
        return this.partidos;
    }

    public int getNumVagas() {
        return this.numVagas;
    }

    public int[] getDataEleicao() {
        return this.dataEleicao;
    }

    // Setters

    public void setCandidato(Candidato c) {
        candidatos.add(c);
    }

    public void setPartido(Partido p) {
        partidos.add(p);
    }

    public void setNumVagas(int nVagas) {
        this.numVagas = nVagas;
    }

    public void setDataEleicao(String data) {

        String[] dataEleicao = data.split("/"); // Separa as informacoes da data de eleicao

        // Atribui os valores aos atributos da classe

        this.dataEleicao[2] = Integer.parseInt(dataEleicao[2]);
        this.dataEleicao[1] = Integer.parseInt(dataEleicao[1]);
        this.dataEleicao[0] = Integer.parseInt(dataEleicao[0]);
    }

    // Funções de leitura dos arquivos

    public void leArquivoCandidatos(String arquivo) {
        try (FileInputStream fp = new FileInputStream(arquivo)) {
            InputStreamReader p = new InputStreamReader(fp, "UTF-8");
            BufferedReader br = new BufferedReader(p);
            Scanner s = new Scanner(br);

            s.useDelimiter(","); 
            s.nextLine(); // pula a primeira linha 
            String linha = new String();

            // Percorre cada uma das linhas do arquivo e salva as informações de cada candidato
            while (s.hasNext()) {

                linha = s.nextLine();
                String[] info = linha.split(",");

                if (info[7].equals("Válido")) { // Descarta votos "Anulado"

                    Candidato candidato = new Candidato();

                    // Setters das propriedades de candidato
                    candidato.setNumeroCandidato(info[0]);
                    candidato.setVotosNominaisCandidato(Integer.parseInt(info[1]));
                    candidato.setSituacaoCandidato(info[2]);
                    candidato.setNomeCandidato(info[3]);
                    candidato.setNomeUrnaCandidato(info[4]);
                    candidato.setSexoCandidato(info[5]);
                    candidato.setDataNasCandidato(info[6]);
                    candidato.setDestinoVotoCandidato(info[7]);
                    candidato.setIdadeCandidato(this.dataEleicao);
                    candidato.setNumeroPartidoCandidato(Integer.parseInt(info[8]));

                    setCandidato(candidato); // Adiciona o candidato a lista de candidatos

                }
            }

            this.associaPartidoCandidato();

            s.close();
        } catch (FileNotFoundException exc) {
            System.out.println("Arquivo " + arquivo + " nao encontrado!");
            System.exit(1);
        } catch (IOException exc) {
            System.out.println("Erro durante a leitura do arquivo " + arquivo + ".");
            System.exit(1);
        }
    }

    public void leArquivoPartidos(String arquivo) {
        try (FileInputStream fp = new FileInputStream(arquivo)) {
            InputStreamReader p = new InputStreamReader(fp, "UTF-8");
            BufferedReader br = new BufferedReader(p);
            Scanner s = new Scanner(br);

            s.useDelimiter(",");
            s.nextLine(); // pula a primeira linha
            String linha = new String();

            // Percorre cada uma das linhas do arquivo e salva as informações de cada partido
            while (s.hasNext()) {
                linha = s.nextLine();
                String[] info = linha.split(",");

                Partido partido = new Partido();

                partido.setNumeroPartido(Integer.parseInt(info[0]));
                partido.setVotosLegenda(Integer.parseInt(info[1]));
                partido.setNomePartido(info[2]);
                partido.setSiglaPartido(info[3]);

                setPartido(partido); // Adiciona o partido a lista de partidos
            }
            this.setVotosTotaisDosPartidos();
            s.close();
        } catch (FileNotFoundException exc) {
            System.out.println("Arquivo " + arquivo + "nao encontrado!");
            System.exit(1);
        } catch (IOException exc) {
            System.out.println("Erro durante a leitura do arquivo" + arquivo);
            System.exit(1);
        }
    }

    public void setVotosTotaisDosPartidos() {
        for (Partido p : partidos) { // percorre a lista de partidos
            int votosTotais = 0;
            for (Candidato c : candidatos) { // percorre a lista de candidatos
                if (p.getNumeroPartido() == c.getNumeroPartidoCandidato()) { // verifica se o candidato pertence ao partido
                    votosTotais += c.getVotosNominaisCandidato(); // soma os votos do candidato aos votos totais
                }
            }
            p.setTotalVotosNominais(votosTotais);
        }
    }

    // Funções de comparação e ordenação

    public void ordenaPartidosOrdemDescrescenteVotosTotais() {
        // ordena a lista de partidos por ordem descrescente dos votos totais
        // se houver empate, ordena por ordem crescente dos numeros dos partidos
        
        Collections.sort(this.getPartidos(), new Comparator<Partido>() {
            // Sobrescreve a função compare() para comparar o total de votos por partidos 
            @Override
            public int compare(Partido p1, Partido p2) {
                if (p1.getTotalVotos() > p2.getTotalVotos()) {
                    return -1;
                } else if (p1.getTotalVotos() < p2.getTotalVotos()) {
                    return 1;
                } else {
                    if (p1.getNumeroPartido() < p2.getNumeroPartido())
                        return -1;
                    else if (p1.getNumeroPartido() > p2.getNumeroPartido())
                        return 1;
                    else
                        return 0;
                }
            }
        });
    }

    public void ordenaPartidosOrdemDescrescenteVotosLegenda(){
        // ordena a lista de partidos por ordem descrescente dos votos de legenda
        // se houver empate, ordena por ordem decrescente dos votos nominais
        // se ainda houver empate, ordena por ordem crescente dos numeros dos partidos
        
        Collections.sort(this.getPartidos(), new Comparator<Partido>() {
            // Sobrescreve a função compare() para comparar os votos de legenda do partido 
            @Override
            public int compare(Partido p1, Partido p2) {
                if(p1.getVotosLegenda() > p2.getVotosLegenda()){
                    return -1;
                }
                else if(p1.getVotosLegenda() < p2.getVotosLegenda()){
                    return 1;
                }
                else{
                    if(p1.getTotalVotosNominais() > p2.getTotalVotosNominais()){
                        return -1;
                    }
                    else if(p1.getTotalVotosNominais() < p2.getTotalVotosNominais()){
                        return 1;
                    }
                    else{
                        if(p1.getNumeroPartido() < p2.getNumeroPartido()){
                            return -1;
                        }
                        else if(p1.getNumeroPartido() > p2.getNumeroPartido()){
                            return 1;
                        }
                        else{
                            return 0;
                        }
                    }
                }
            }
        });
    }

    public void ordenaPartidosOrdemDecrescenteCandidatosMaisVotados(){
        // ordena a lista de partidos por ordem decrescente dos candidatos mais votados
        // se houver empate, ordena por ordem crescente dos numeros dos partidos

        Collections.sort(this.getPartidos(), new Comparator<Partido>() {
            // Sobrescreve a função compare() para comparar os candidatos mais votados do partido
            @Override
            public int compare(Partido p1, Partido p2) {
                if (p1.getPrimeiroColocado().getVotosNominaisCandidato() > p2.getPrimeiroColocado().getVotosNominaisCandidato()) {
                    return -1;
                } else if (p1.getPrimeiroColocado().getVotosNominaisCandidato() < p2.getPrimeiroColocado().getVotosNominaisCandidato()) {
                    return 1;
                } else {
                    if (p1.getNumeroPartido() < p2.getNumeroPartido())
                        return -1;
                    else if (p1.getVotosLegenda() > p2.getVotosLegenda())
                        return 1;
                    else
                        return 0;
                }
            }
        });
    }

    public void ordenaCandidatosPorVotoNominal(){
        // ordena a lista de candidatos por ordem crescente dos votos nominais
        // se houver empate, ordena por ordem decrescente da idade dos candidatos
        
        Collections.sort(this.getCandidatos(), new Comparator<Candidato>() {
            // Sobrescreve a função compare() para comparar os votos de nominais do candidato 
            @Override
            public int compare(Candidato c1, Candidato p2) {
                if(c1.getVotosNominaisCandidato() > p2.getVotosNominaisCandidato()){
                    return -1;
                }
                else if(c1.getVotosNominaisCandidato() < p2.getVotosNominaisCandidato()){
                    return 1;
                }
                else{
                    if(c1.getIdadeCandidato() > p2.getIdadeCandidato()){
                        return -1;
                    }
                    else if(c1.getIdadeCandidato() < p2.getIdadeCandidato()){
                        return 1;
                    }
                    else{
                        return 0;
                    }
                }
            }
        });
    }

    // Especial functions

    public void associaPartidoCandidato() { // ISSO É UMA SUGESTÃO TEM FORMA MAIS FÁCIL????
        for (Partido p : partidos) {
            for (Candidato c : candidatos) {
                if (p.getNumeroPartido() == c.getNumeroPartidoCandidato()) {
                    c.setPartidoCandidato(p);
                }
            }

        }
    }

    public void somaNumeroDeVagas() throws IOException { // Lara (1) TALVEZ IMPRIMIR AQUI DENTRO

        int soma = 0;
        for (Candidato c : candidatos) {
            if (c.getSituacaoCandidato().equals("Eleito")) {
                soma++;
            }
        }
        this.setNumVagas(soma);
        System.out.println("Número de vagas: " + this.numVagas);

    }

    public void imprimeCandidatosEleitos() throws IOException { // Lara (2) TRATAR 1 VOTO???

        System.out.println("\nVereadores eleitos:");
        this.ordenaCandidatosPorVotoNominal();

        int i = 1;
        for (Candidato c : candidatos) {
            if (c.getSituacaoCandidato().equals("Eleito")) {
                if (c.getVotosNominaisCandidato() == 1) {
                    System.out.println(i + " - " + c.getNomeCandidato() + " / " + c.getNomeUrnaCandidato() + " ("
                            + c.getPartidoCandidato().getSiglaPartido() + ", " + c.getVotosNominaisCandidato()
                            + " voto)");
                } else {
                    System.out.println(i + " - " + c.getNomeCandidato() + " / " + c.getNomeUrnaCandidato() + " ("
                            + c.getPartidoCandidato().getSiglaPartido() + ", " + c.getVotosNominaisCandidato()
                            + " votos)");
                }
                i++;
            }
        }

    }

    // TESTAR ESSA FUNCAO COM ARQUIVO MAIOR ***MUITO NECESSARIO***
    public void imprimeCandidatosMaisVotados() throws IOException { // Lara (3) COMPARACAO FEITA COM O VOTOS NOMINAIS
                                                                    // ?????

        System.out.println("\nCandidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):");

        int i = 0;
        int j = 1;
        for (Candidato c : candidatos) {
            if (i == this.numVagas) {
                break;
            }
            if (c.getVotosNominaisCandidato() == 1) {
                System.out.println(j + " - " + c.getNomeCandidato() + " / " + c.getNomeUrnaCandidato() + " ("
                        + c.getPartidoCandidato().getSiglaPartido() + ", " + c.getVotosNominaisCandidato() + " voto)");
            } else {
                System.out.println(j + " - " + c.getNomeCandidato() + " / " + c.getNomeUrnaCandidato() + " ("
                        + c.getPartidoCandidato().getSiglaPartido() + ", " + c.getVotosNominaisCandidato() + " votos)");
            }
            i++;
            j++;
        }

    }

    // Para ver se seriam eleitos devo comparar com o menos votado e que foi
    // eleito?????
    public void imprimeNaoEleitosMasSeriamEmMajoritario() throws IOException { // Lara (4) SUPLENTE CONTA ?

        System.out.println(
                "\nTeriam sido eleitos se a votação fosse majoritária, e não foram eleitos:\n(com sua posição no ranking de mais votados)");

        int i = 0;
        int cont = 1;
        for (Candidato c : candidatos) {
            if (i == this.numVagas) {
                break;
            }
            if (c.getSituacaoCandidato().equals("Não eleito") || c.getSituacaoCandidato().equals("Suplente")) {
                if (c.getVotosNominaisCandidato() == 1) {
                    System.out.println(cont + " - " + c.getNomeCandidato() + " / " + c.getNomeUrnaCandidato() + " ("
                            + c.getPartidoCandidato().getSiglaPartido() + ", " + c.getVotosNominaisCandidato()
                            + " voto)");
                } else {
                    System.out.println(cont + " - " + c.getNomeCandidato() + " / " + c.getNomeUrnaCandidato() + " ("
                            + c.getPartidoCandidato().getSiglaPartido() + ", " + c.getVotosNominaisCandidato()
                            + " votos)");
                }
            }
            i++;
            cont++;
        }
    }

    public void imprimeEleitosMasNaoSeriamEmMajoritario() throws IOException { // Lara (5)

        System.out.println(
                "\nEleitos, que se beneficiaram do sistema proporcional:\n(com sua posição no ranking de mais votados)");

        LinkedList<Candidato> eleitosMajo = new LinkedList<>();

        int i = 0;
        for (Candidato c : candidatos) {
            if (i >= this.numVagas) {
                break;
            }
            eleitosMajo.add(c);
            i++;
        }

        int cont = 1;
        for (Candidato c : candidatos) {
            if (c.getSituacaoCandidato().equals("Eleito") && !(eleitosMajo.contains(c))) {
                if (c.getVotosNominaisCandidato() == 1) {
                    System.out.println(cont + " - " + c.getNomeCandidato() + " / " + c.getNomeUrnaCandidato() + " ("
                            + c.getPartidoCandidato().getSiglaPartido() + ", " + c.getVotosNominaisCandidato()
                            + " voto)");
                } else {
                    System.out.println(cont + " - " + c.getNomeCandidato() + " / " + c.getNomeUrnaCandidato() + " ("
                            + c.getPartidoCandidato().getSiglaPartido() + ", " + c.getVotosNominaisCandidato()
                            + " votos)");
                }
            }
            cont++;
        }


    }

    public int numeroDeCandidatosEleitos() {
        int numero = 0;
        for (Candidato c : candidatos) {
            if (c.getSituacaoCandidato().equals("Eleito")) {
                numero++;
            }
        }
        return numero;
    }

    public void votosTotaisCandidatosEleitos() throws IOException { // (6)
        int i = 1;

        this.ordenaPartidosOrdemDescrescenteVotosTotais();


        System.out.println("\nVotação dos partidos e número de candidatos eleitos:");

        for (Partido p : partidos) {
            int totalEleitos = 0;
            int votosTotais = 0;
            for (Candidato c : candidatos) {
                if (p.getNumeroPartido() == c.getNumeroPartidoCandidato()) {
                    if (c.getSituacaoCandidato().equals("Eleito")) {
                        totalEleitos++;
                    }
                    votosTotais += c.getVotosNominaisCandidato();
                }
            }

            if (totalEleitos > 1) {
                System.out.println(i + " - " + p.getSiglaPartido() + " - " + p.getNumeroPartido() + ", "
                        + (p.getVotosLegenda() + votosTotais) + " votos (" + votosTotais + " nominais e "
                        + p.getVotosLegenda() + " de legenda), " + totalEleitos + " candidatos eleitos");
            } else {
                if(p.getTotalVotosNominais() + p.getVotosLegenda() > 1 && p.getTotalVotosNominais() > 1){
                    System.out.println(i + " - " + p.getSiglaPartido() + " - " + p.getNumeroPartido() + ", "
                            + (p.getVotosLegenda() + votosTotais) + " votos (" + votosTotais + " nominais e "
                            + p.getVotosLegenda() + " de legenda), " + totalEleitos + " candidato eleito");
                }
                else{
                    System.out.println(i + " - " + p.getSiglaPartido() + " - " + p.getNumeroPartido() + ", "
                            + (p.getVotosLegenda() + votosTotais) + " voto (" + votosTotais + " nominal e "
                            + p.getVotosLegenda() + " de legenda), " + totalEleitos + " candidato eleito");
                }
            }

            i++;
        }
    }

    public void primeiroUltimoColocadoPorPartido() throws IOException { // (8)
        int i = 1;


        System.out.println("\nPrimeiro e último colocados de cada partido:");

        for (Partido p : partidos) {
            Candidato primeiro = new Candidato();
            Candidato ultimo = new Candidato();
            int maior = 0;
            int menor = 100000;

            for (Candidato c : candidatos) {
                if (p.getNumeroPartido() == c.getNumeroPartidoCandidato()) {
                    if (c.getVotosNominaisCandidato() > maior) {
                        maior = c.getVotosNominaisCandidato();
                        primeiro = c;
                    }
                    if (c.getVotosNominaisCandidato() < menor) {
                        menor = c.getVotosNominaisCandidato();
                        ultimo = c;
                    }
                    if(c.getVotosNominaisCandidato() == ultimo.getVotosNominaisCandidato()){
                        if(ultimo.getIdadeCandidato() > c.getIdadeCandidato()){
                            ultimo = c;
                        }
                    }
                }
            }
            p.setPrimeiroColocado(primeiro);
            p.setUltimoColocado(ultimo);
        }

        this.ordenaPartidosOrdemDecrescenteCandidatosMaisVotados();

        for (Partido p : partidos){
            if(p.getVotosLegenda() != 0){
                if (p.getPrimeiroColocado().getVotosNominaisCandidato() > 1) {
                    System.out.print(i + " - " + p.getSiglaPartido() + " - " + p.getNumeroPartido() + ", "
                            + p.getPrimeiroColocado().getNomeUrnaCandidato() + " (" + p.getPrimeiroColocado().getNumeroCandidato() + ", "
                            + p.getPrimeiroColocado().getVotosNominaisCandidato() + " votos) / ");
                } else {
                    System.out.print(i + " - " + p.getSiglaPartido() + " - " + p.getNumeroPartido() + ", "
                            + p.getPrimeiroColocado().getNomeUrnaCandidato() + " (" + p.getPrimeiroColocado().getNumeroCandidato() + ", "
                            + p.getPrimeiroColocado().getVotosNominaisCandidato() + " voto) / ");
                }
                if (p.getUltimoColocado().getVotosNominaisCandidato() > 1) {
                    System.out.println(p.getUltimoColocado().getNomeUrnaCandidato() + " (" + p.getUltimoColocado().getNumeroCandidato() + ", "
                            + p.getUltimoColocado().getVotosNominaisCandidato() + " votos)");
                } else {
                    System.out.println(p.getUltimoColocado().getNomeUrnaCandidato() + " (" + p.getUltimoColocado().getNumeroCandidato() + ", "
                            + p.getUltimoColocado().getVotosNominaisCandidato() + " voto)");
                }
          }

            i++;
        }

    }

    public void votosLegendaPorPartidoPorcentagem() throws IOException { // (7)
        int i = 1;

        this.ordenaPartidosOrdemDescrescenteVotosLegenda();


        System.out.println("\nVotação dos partidos (apenas votos de legenda):");

        for (Partido p : partidos) {
            double votos = p.getVotosLegenda();
            double total = 0;
            for (Candidato c : candidatos) {
                if (p.getNumeroPartido() == c.getNumeroPartidoCandidato()) {
                    total += c.getVotosNominaisCandidato();
                }
            }
            if (total != 0 && p.getVotosLegenda() > 1) {
                double porcentagem = (votos * 100) / (total + p.getVotosLegenda());
                System.out.printf("%d - %s - %s, %.0f votos de legenda (%.2f%% do total do partido)\n", i,
                        p.getSiglaPartido(), p.getNumeroPartido(), votos, porcentagem);
                i++;
            }
            else{
                System.out.printf("%d - %s - %s, %.0f voto de legenda (proporção não calculada, 0 voto no partido)\n", i,
                        p.getSiglaPartido(), p.getNumeroPartido(), votos);
            }
        }
    }

    public void distribuicaoEleitosPorIdade() throws IOException {
        double menosDe30 = 0;
        double entre30e40 = 0;
        double entre40e50 = 0;
        double entre50e60 = 0;
        double maisDe60 = 0;
        double total = 0;

        for (Candidato c : candidatos) {
            if (c.getSituacaoCandidato().equals("Eleito")) {
                total++;
                if (c.getIdadeCandidato() < 30) {
                    menosDe30++;
                } else if (c.getIdadeCandidato() >= 30 && c.getIdadeCandidato() < 40) {
                    entre30e40++;
                } else if (c.getIdadeCandidato() >= 40 && c.getIdadeCandidato() < 50) {
                    entre40e50++;
                } else if (c.getIdadeCandidato() >= 50 && c.getIdadeCandidato() < 60) {
                    //System.out.println(c.getNomeCandidato() + " " + c.getIdadeCandidato() + " " + c.getDataNasCandidato());
                    entre50e60++;
                } else if (c.getIdadeCandidato() >= 60) {
                    maisDe60++;
                }
            }
        }


        System.out.printf("\nEleitos, por faixa etária (na data da eleição):\n");
        System.out.printf("      Idade < 30: %.0f (%.2f%%)\n", menosDe30, (menosDe30 * 100 / total));
        System.out.printf("30 <= Idade < 40: %.0f (%.2f%%)\n", entre30e40, entre30e40 * 100 / total);
        System.out.printf("40 <= Idade < 50: %.0f (%.2f%%)\n", entre40e50, entre40e50 * 100 / total);
        System.out.printf("50 <= Idade < 60: %.0f (%.2f%%)\n", entre50e60, entre50e60 * 100 / total);
        System.out.printf("60 <= Idade     : %.0f (%.2f%%)\n", maisDe60, maisDe60 * 100 / total);

    }

    public void eleitosPorSexo() throws IOException {
        double homens = 0;
        double mulheres = 0;
        double total = 0;

        for (Candidato c : candidatos) {
            if (c.getSituacaoCandidato().equals("Eleito")) {
                total++;
                if (c.getSexoCandidato().equals("M")) {
                    homens++;
                } else if (c.getSexoCandidato().equals("F")) {
                    mulheres++;
                }
            }
        }


        System.out.printf("\nEleitos, por sexo:\n");
        System.out.printf("Feminino:  %.0f (%.2f%%)\n", mulheres, mulheres * 100 / total);
        System.out.printf("Masculino: %.0f (%.2f%%)\n", homens, homens * 100 / total);

    }

    public void contabilizacaoDosVotos() throws IOException {
        double totalVotos = 0;
        double totalVotosNominais = 0;
        double totalVotosLegenda = 0;

        for (Partido p : partidos) {
            totalVotosLegenda += p.getVotosLegenda();
        }

        for (Candidato c : candidatos) {
            totalVotosNominais += c.getVotosNominaisCandidato();
        }

        totalVotos = totalVotosNominais + totalVotosLegenda;


        System.out.printf("\nTotal de votos válidos:    %.0f\n", totalVotos);
        System.out.printf("Total de votos nominais:   %.0f (%.2f%%)\n", totalVotosNominais,
                totalVotosNominais * 100 / totalVotos);
        System.out.printf("Total de votos de legenda: %.0f (%.2f%%)\n\n", totalVotosLegenda,
                totalVotosLegenda * 100 / totalVotos);

    }
}
