package Package;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Eleicao {
    private LinkedList<Candidato> candidatos = new LinkedList<>();
    private LinkedList<Partido> partidos = new LinkedList<>();
    private int numVagas;
    private int[] dataEleicao = new int[3];

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

        String[] dataEleicao = data.split("/");

        this.dataEleicao[2] = Integer.parseInt(dataEleicao[2]);
        this.dataEleicao[1] = Integer.parseInt(dataEleicao[1]);
        this.dataEleicao[0] = Integer.parseInt(dataEleicao[0]);
    }

    // Functions

    public void imprimeNomePartidos() {
        for (Partido p : partidos) {
            System.out.println(p.getNomePartido());
        }
    }

    public void imprimeNomeCandidatos() {
        for (Candidato c : candidatos) {
            System.out.println(c.getNomeCandidato() + " / Votos nominais: " + c.getVotosNominaisCandidato());
            // c.getPartidoCandidato().imprimePartido();
        }
    }

    public void leArquivoCandidatos(String arquivo) {
        try (FileInputStream fp = new FileInputStream(arquivo)) {
            InputStreamReader p = new InputStreamReader(fp, "UTF-8");
            BufferedReader br = new BufferedReader(p);
            Scanner s = new Scanner(br);

            s.useDelimiter(",");
            s.nextLine();
            String linha = new String();

            // Candidato candidato = new Candidato();

            while (s.hasNext()) {

                linha = s.nextLine();
                String[] info = linha.split(",");

                if (info[7].equals("Válido")) {

                    Candidato candidato = new Candidato();

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

                    setCandidato(candidato);

                }

            }
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

            while (s.hasNext()) {
                Partido partido = new Partido();
                linha = s.nextLine();
                String[] info = linha.split(",");

                partido.setNumeroPartido(Integer.parseInt(info[0]));
                partido.setVotosLegenda(Integer.parseInt(info[1]));
                partido.setNomePartido(info[2]);
                partido.setSiglaPartido(info[3]);

                setPartido(partido);
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
        for (Partido p : partidos) {
            int votosTotais = 0;
            for (Candidato c : candidatos) {
                if (p.getNumeroPartido() == c.getNumeroPartidoCandidato()) {
                    votosTotais += c.getVotosNominaisCandidato();
                }
            }
            p.setTotalVotosNominais(votosTotais);
        }
    }

    // Funções de comparação e ordenação

    public void ordenaPartidosOrdemDescrescenteVotosTotais() {
        Collections.sort(this.getPartidos(), new Comparator<Partido>() {
            @Override
            public int compare(Partido p1, Partido p2) {
                if (p1.getTotalVotos() > p2.getTotalVotos()) {
                    return -1;
                } else if (p1.getTotalVotos() < p2.getTotalVotos()) {
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

    public void ordenaPartidosOrdemDescrescenteVotosLegenda(){
        Collections.sort(this.getPartidos(), new Comparator<Partido>() {
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
        Collections.sort(this.getPartidos(), new Comparator<Partido>() {
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
        Collections.sort(this.getCandidatos(), new Comparator<Candidato>() {
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
        FileWriter arq = new FileWriter("Relatorio.txt");
        PrintWriter gravarArq = new PrintWriter(arq);

        int soma = 0;
        for (Candidato c : candidatos) {
            if (c.getSituacaoCandidato().equals("Eleito")) {
                soma++;
            }
        }
        this.setNumVagas(soma);
        gravarArq.println("Número de vagas: " + this.numVagas);

        arq.close();
    }

    public void imprimeInformacoesCandidato(Candidato c, int indice) {
        if (c.getVotosNominaisCandidato() == 1) {
            System.out.println(indice + " - " + c.getNomeCandidato() + " / " + c.getNomeUrnaCandidato() + " ("
                    + c.getPartidoCandidato().getNomePartido() + ", " + c.getVotosNominaisCandidato() + " voto)");
        }
        System.out.println(indice + " - " + c.getNomeCandidato() + " / " + c.getNomeUrnaCandidato() + " ("
                + c.getPartidoCandidato().getNomePartido() + ", " + c.getVotosNominaisCandidato() + " votos)");
    }

    public void imprimeCandidatosEleitos() throws IOException { // Lara (2) TRATAR 1 VOTO???
        FileWriter arq = new FileWriter("Relatorio.txt", true);
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.println("\nVereadores eleitos:");
        this.ordenaCandidatosPorVotoNominal();

        int i = 1;
        for (Candidato c : candidatos) {
            if (c.getSituacaoCandidato().equals("Eleito")) {
                if (c.getVotosNominaisCandidato() == 1) {
                    gravarArq.println(i + " - " + c.getNomeCandidato() + " / " + c.getNomeUrnaCandidato() + " ("
                            + c.getPartidoCandidato().getSiglaPartido() + ", " + c.getVotosNominaisCandidato()
                            + " voto)");
                } else {
                    gravarArq.println(i + " - " + c.getNomeCandidato() + " / " + c.getNomeUrnaCandidato() + " ("
                            + c.getPartidoCandidato().getSiglaPartido() + ", " + c.getVotosNominaisCandidato()
                            + " votos)");
                }
                i++;
            }
        }

        arq.close();
    }

    // TESTAR ESSA FUNCAO COM ARQUIVO MAIOR ***MUITO NECESSARIO***
    public void imprimeCandidatosMaisVotados() throws IOException { // Lara (3) COMPARACAO FEITA COM O VOTOS NOMINAIS
                                                                    // ?????
        FileWriter arq = new FileWriter("Relatorio.txt", true);
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.println("\nCandidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):");

        int i = 0;
        int j = 1;
        for (Candidato c : candidatos) {
            if (i == this.numVagas) {
                break;
            }
            if (c.getVotosNominaisCandidato() == 1) {
                gravarArq.println(j + " - " + c.getNomeCandidato() + " / " + c.getNomeUrnaCandidato() + " ("
                        + c.getPartidoCandidato().getSiglaPartido() + ", " + c.getVotosNominaisCandidato() + " voto)");
            } else {
                gravarArq.println(j + " - " + c.getNomeCandidato() + " / " + c.getNomeUrnaCandidato() + " ("
                        + c.getPartidoCandidato().getSiglaPartido() + ", " + c.getVotosNominaisCandidato() + " votos)");
            }
            i++;
            j++;
        }

        arq.close();
    }

    // Para ver se seriam eleitos devo comparar com o menos votado e que foi
    // eleito?????
    public void imprimeNaoEleitosMasSeriamEmMajoritario() throws IOException { // Lara (4) SUPLENTE CONTA ?
        FileWriter arq = new FileWriter("Relatorio.txt", true);
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.println(
                "\nTeriam sido eleitos se a votação fosse majoritária, e não foram eleitos:\n(com sua posição no ranking de mais votados)");

        int i = 0;
        int cont = 1;
        for (Candidato c : candidatos) {
            if (i > this.numVagas) {
                break;
            }
            if (c.getSituacaoCandidato().equals("Não eleito") || c.getSituacaoCandidato().equals("Suplente")) {
                if (c.getVotosNominaisCandidato() == 1) {
                    gravarArq.println(cont + " - " + c.getNomeCandidato() + " / " + c.getNomeUrnaCandidato() + " ("
                            + c.getPartidoCandidato().getSiglaPartido() + ", " + c.getVotosNominaisCandidato()
                            + " voto)");
                } else {
                    gravarArq.println(cont + " - " + c.getNomeCandidato() + " / " + c.getNomeUrnaCandidato() + " ("
                            + c.getPartidoCandidato().getSiglaPartido() + ", " + c.getVotosNominaisCandidato()
                            + " votos)");
                }
            }
            i++;
            cont++;
        }
        arq.close();
    }

    public void imprimeEleitosMasNaoSeriamEmMajoritario() throws IOException { // Lara (5)
        FileWriter arq = new FileWriter("Relatorio.txt", true);
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.println(
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
                    gravarArq.println(cont + " - " + c.getNomeCandidato() + " / " + c.getNomeUrnaCandidato() + " ("
                            + c.getPartidoCandidato().getSiglaPartido() + ", " + c.getVotosNominaisCandidato()
                            + " voto)");
                } else {
                    gravarArq.println(cont + " - " + c.getNomeCandidato() + " / " + c.getNomeUrnaCandidato() + " ("
                            + c.getPartidoCandidato().getSiglaPartido() + ", " + c.getVotosNominaisCandidato()
                            + " votos)");
                }
            }
            cont++;
        }

        arq.close();

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

        FileWriter arq = new FileWriter("Relatorio.txt", true);
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.println("\nVotação dos partidos e número de candidatos eleitos:");

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
                gravarArq.println(i + " - " + p.getSiglaPartido() + " - " + p.getNumeroPartido() + ", "
                        + (p.getVotosLegenda() + votosTotais) + " votos (" + votosTotais + " nominais e "
                        + p.getVotosLegenda() + " de legenda), " + totalEleitos + " candidatos eleitos");
            } else {
                gravarArq.println(i + " - " + p.getSiglaPartido() + " - " + p.getNumeroPartido() + ", "
                        + (p.getVotosLegenda() + votosTotais) + " votos (" + votosTotais + " nominais e "
                        + p.getVotosLegenda() + " de legenda), " + totalEleitos + " candidato eleito");
            }

            i++;
        }
        arq.close();
    }

    public void primeiroUltimoColocadoPorPartido() throws IOException { // (8)
        int i = 1;

        FileWriter arq = new FileWriter("Relatorio.txt", true);
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.println("\nPrimeiro e último colocados de cada partido:");

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
            if (p.getPrimeiroColocado().getVotosNominaisCandidato() > 1) {
                gravarArq.print(i + " - " + p.getSiglaPartido() + " - " + p.getNumeroPartido() + ", "
                        + p.getPrimeiroColocado().getNomeUrnaCandidato() + " (" + p.getPrimeiroColocado().getNumeroCandidato() + ", "
                        + p.getPrimeiroColocado().getVotosNominaisCandidato() + " votos) / ");
            } else {
                gravarArq.print(i + " - " + p.getSiglaPartido() + " - " + p.getNumeroPartido() + ", "
                        + p.getPrimeiroColocado().getNomeUrnaCandidato() + " (" + p.getPrimeiroColocado().getNumeroCandidato() + ", "
                        + p.getPrimeiroColocado().getVotosNominaisCandidato() + " voto) / ");
            }
            if (p.getUltimoColocado().getVotosNominaisCandidato() > 1) {
                gravarArq.println(p.getUltimoColocado().getNomeUrnaCandidato() + " (" + p.getUltimoColocado().getNumeroCandidato() + ", "
                        + p.getUltimoColocado().getVotosNominaisCandidato() + " votos)");
            } else {
                gravarArq.println(p.getUltimoColocado().getNomeUrnaCandidato() + " (" + p.getUltimoColocado().getNumeroCandidato() + ", "
                        + p.getUltimoColocado().getVotosNominaisCandidato() + " voto)");
            }

            i++;
        }

        arq.close();
    }

    public void votosLegendaPorPartidoPorcentagem() throws IOException { // (7)
        int i = 1;

        this.ordenaPartidosOrdemDescrescenteVotosLegenda();

        FileWriter arq = new FileWriter("Relatorio.txt", true);
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.println("\nVotação dos partidos (apenas votos de legenda):");

        for (Partido p : partidos) {
            double votos = p.getVotosLegenda();
            double total = 0;
            for (Candidato c : candidatos) {
                if (p.getNumeroPartido() == c.getNumeroPartidoCandidato()) {
                    total += c.getVotosNominaisCandidato();
                }
            }
            if (total != 0) {
                double porcentagem = (votos * 100) / (total + p.getVotosLegenda());
                gravarArq.printf("%d - %s - %s, %.0f votos de legenda (%.2f%% do total do partido)\n", i,
                        p.getSiglaPartido(), p.getNumeroPartido(), votos, porcentagem);
                i++;
            }
        }
        arq.close();
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
                    //System.out.println(c.getNomeCandidato() + " " + c.getIdadeCandidato());
                    entre50e60++;
                } else if (c.getIdadeCandidato() >= 60) {
                    maisDe60++;
                }
            }
        }

        FileWriter arq = new FileWriter("Relatorio.txt", true);
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.printf("\nEleitos, por faixa etária (na data da eleição):\n");
        gravarArq.printf("      Idade < 30: %.0f (%.2f%%)\n", menosDe30, (menosDe30 * 100 / total));
        gravarArq.printf("30 <= Idade < 40: %.0f (%.2f%%)\n", entre30e40, entre30e40 * 100 / total);
        gravarArq.printf("40 <= Idade < 50: %.0f (%.2f%%)\n", entre40e50, entre40e50 * 100 / total);
        gravarArq.printf("50 <= Idade < 60: %.0f (%.2f%%)\n", entre50e60, entre50e60 * 100 / total);
        gravarArq.printf("60 <= Idade     : %.0f (%.2f%%)\n", maisDe60, maisDe60 * 100 / total);

        arq.close();
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

        FileWriter arq = new FileWriter("Relatorio.txt", true);
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.printf("\nEleitos, por sexo:\n");
        gravarArq.printf("Feminino:  %.0f (%.2f%%)\n", mulheres, mulheres * 100 / total);
        gravarArq.printf("Masculino: %.0f (%.2f%%)\n", homens, homens * 100 / total);

        arq.close();
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

        FileWriter arq = new FileWriter("Relatorio.txt", true);
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.printf("\nTotal de votos válidos:    %.0f\n", totalVotos);
        gravarArq.printf("Total de votos nominais:   %.0f (%.2f%%)\n", totalVotosNominais,
                totalVotosNominais * 100 / totalVotos);
        gravarArq.printf("Total de votos de legenda: %.0f (%.2f%%)\n", totalVotosLegenda,
                totalVotosLegenda * 100 / totalVotos);

        arq.close();
    }
}
