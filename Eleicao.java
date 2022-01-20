import java.util.*;

public class Eleicao{
    private LinkedList<Candidato> candidatos = new LinkedList<>();
    private LinkedList<Partido> partidos = new LinkedList<>();
    //private Set<Candidato> candidatos = new HashSet<>();
    //private Set<Partido> partidos = new HashSet<>();

    //Getters

    public LinkedList<Candidato> getCandidatos(){
        return this.candidatos;
    }
    public LinkedList<Partido> getPartidos(){
        return this.partidos;
    }

    /*public Set<Candidato> getCandidatos(){
        return this.candidatos;
    }
    public Set<Partido> getPartidos(){
        return this.partidos;
    }*/

    //Setters

    public void setCandidato(Candidato c){
        candidatos.add(c);
    }
    public void setPartido(Partido p){
        partidos.add(p);
    }

    //Functions

    public void imprimeNomePartidos(){
        for(Partido p : partidos){
            System.out.println(p.getNomePartido());
        }
    }

    public void imprimeNomeCandidatos(){
        for(Candidato c : candidatos){
            System.out.println(c.getNomeCandidato() + " / Votos nominais: " + c.getVotosNominaisCandidato());
            //c.getPartidoCandidato().imprimePartido();
        }
    }

    // Especial functions

    public void associaPartidoCandidato(){ // ISSO É UMA SUGESTÃO TEM FORMA MAIS FÁCIL????
        for(Partido p : partidos){
            for(Candidato c : candidatos){
                if(p.getNumeroPartido().equals(c.getNumeroPartidoCandidato())){
                    c.setPartidoCandidato(p);
                }
            }
            
        }
    }

    public int somaNumeroDeVagas(){ //Lara (1) TALVEZ IMPRIMIR AQUI DENTRO 
        int soma = 0;
        for (Candidato c : candidatos){
            if(c.getSituacaoCandidato().equals("Eleito")){
                soma++;
            }
        }
        return soma;
    }

    public void imprimeCandidatosEleitos(){ //Lara (2) TRATAR 1 VOTO???
        System.out.println("Vereadores eleitos: ");

        int i = 1;
        for (Candidato c : candidatos){
            if(c.getSituacaoCandidato().equals("Eleito")){
                if(c.getVotosNominaisCandidato() == 1){
                    System.out.println(i + " - " + c.getNomeCandidato() + " / " + c.getNomeUrnaCandidato() + " (" + c.getPartidoCandidato().getNomePartido() + ", " + c.getVotosNominaisCandidato() + " voto)");
                }
                System.out.println(i + " - " + c.getNomeCandidato() + " / " + c.getNomeUrnaCandidato() + " (" + c.getPartidoCandidato().getNomePartido() + ", " + c.getVotosNominaisCandidato() + " votos)");

                i++;
            }
        }
    }

    public static LinkedList<Candidato> ordenaCandidatosPorVotoNominal(LinkedList<Candidato> lista){
       
        Collections.sort(lista);

        return lista;
    }

    // TESTAR ESSA FUNCAO COM ARQUIVO MAIOR ***MUITO NECESSARIO***
    public void imprimeCandidatosMaisVotados(int nVagas){ // Lara (3) COMPARACAO FEITA COM O VOTOS NOMINAIS ?????
        System.out.println("Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas): ");

        LinkedList<Candidato> lista = new LinkedList<>();
        lista = ordenaCandidatosPorVotoNominal(this.candidatos);

        int i = 0;
        for(Candidato c : lista){
            if(i == nVagas){
                break;
            }
            if(c.getVotosNominaisCandidato() == 1){
                System.out.println(i+1 + " - " + c.getNomeCandidato() + " / " + c.getNomeUrnaCandidato() + " (" + c.getPartidoCandidato().getNomePartido() + ", " + c.getVotosNominaisCandidato() + " voto)");
            }
            System.out.println(i+1 + " - " + c.getNomeCandidato() + " / " + c.getNomeUrnaCandidato() + " (" + c.getPartidoCandidato().getNomePartido() + ", " + c.getVotosNominaisCandidato() + " votos)");

            i++;
        }
    }

    // Para ver se seriam eleitos devo comparar com o menos votado e que foi eleito?????
    public void imprimeNaoEleitosMasSeriamEmMajoritario(){ // Lara (4) SUPLENTE CONTA  ?
        System.out.println("Teriam sido eleitos se a votação fosse majoritária, e não foram eleitos: (com sua posição no ranking de mais votados)");

        LinkedList<Candidato> listaNaoEleitos = new LinkedList<>();
        for(Candidato c : candidatos){
            if(c.getSituacaoCandidato().equals("Não Eleito") || c.getSituacaoCandidato().equals("Suplente")){
               listaNaoEleitos.add(c);
            }
        }
        listaNaoEleitos = ordenaCandidatosPorVotoNominal(listaNaoEleitos);

        LinkedList<Candidato> listaEleitos = new LinkedList<>();
        for(Candidato c : candidatos){
            if(c.getSituacaoCandidato().equals("Eleito")){
               listaEleitos.add(c);
            }
        }
        listaEleitos = ordenaCandidatosPorVotoNominal(listaEleitos);

        // AQUI É MAIOR OU IGUAL OU SÓ MAIOR ????    
        int i = 1;
        for(Candidato c : listaNaoEleitos){
            if(c.getVotosNominaisCandidato() >= listaEleitos.getLast().getVotosNominaisCandidato()){
                if(c.getVotosNominaisCandidato() == 1){
                    System.out.println(i + " - " + c.getNomeCandidato() + " / " + c.getNomeUrnaCandidato() + " (" + c.getPartidoCandidato().getNomePartido() + ", " + c.getVotosNominaisCandidato() + " voto)");
                }
                System.out.println(i + " - " + c.getNomeCandidato() + " / " + c.getNomeUrnaCandidato() + " (" + c.getPartidoCandidato().getNomePartido() + ", " + c.getVotosNominaisCandidato() + " votos)");
    
                i++;
            }
        }


        
    }

    /*public void imprimeEleitosMasNaoSeriamEmMajoritario(){ // Lara (5)

    }*/

    public int numeroDeCandidatosEleitos(){
        int numero = 0;
        for(Candidato c : candidatos){
            if(c.getSituacaoCandidato().equals("Eleito")){
                numero++;
            }
        }
        return numero;
    }

    public void votosTotaisCandidatosEleitos(){
        int i = 1;
        for (Partido p : partidos){
            int totalEleitos = 0;
            int votosTotais = 0;
            for(Candidato c : candidatos){
                if(c.getNumeroPartidoCandidato().equals(p.getNumeroPartido())){
                    if(c.getSituacaoCandidato().equals("Eleito")){
                        totalEleitos++;
                    }
                    votosTotais += c.getVotosNominaisCandidato();
                }
            }
            System.out.println(i + " - " + p.getSiglaPartido() + " - " + p.getNumeroPartido() + ", " + (p.getVotosLegenda() + votosTotais) + " votos (" + votosTotais + "  nominais e " + p.getVotosLegenda() + " de legenda), " + totalEleitos + " candidatos eleitos");
            i++;
        }
    }

    public void primeiroUltimoColocadoPorPartido(){
        int i = 1;

        for(Partido p : partidos){
            Candidato primeiro = new Candidato();
            Candidato ultimo = new Candidato();
            int maior = 0;
            int menor = 100000;

            for(Candidato c : candidatos){
                if(c.getNumeroPartidoCandidato().equals(p.getNumeroPartido())){
                    if(c.getSituacaoCandidato().equals("Eleito")){
                        if(c.getVotosNominaisCandidato() > maior){
                            maior = c.getVotosNominaisCandidato();
                            primeiro = c;
                        }
                        else if(c.getVotosNominaisCandidato() < menor){
                            menor = c.getVotosNominaisCandidato();
                            ultimo = c;
                        }
                    }
                }
            }

            System.out.print(i + " - " + p.getSiglaPartido() + " - " + p.getNumeroPartido() + ", " + primeiro.getNomeUrnaCandidato() + " (" + primeiro.getNumeroCandidato() + ", " + primeiro.getVotosNominaisCandidato() + " votos) / ");
            System.out.println(ultimo.getNomeUrnaCandidato() + " (" + ultimo.getNumeroCandidato() + ", " + ultimo.getVotosNominaisCandidato() + " votos)");
            i++;
        }
    }

    public void votosLegendaPorPartidoPorcentagem(){
        int i = 1;

        for(Partido p : partidos){
            int votos = p.getVotosLegenda();
            int total = 0;
            for(Candidato c : candidatos){
                if(c.getNumeroPartidoCandidato().equals(p.getNumeroPartido())){
                    total += c.getVotosNominaisCandidato();
                }
            }
            if (total != 0){
                double porcentagem = (votos * 100) / total;
                System.out.println(i + " - " + p.getSiglaPartido() + " - " + p.getNumeroPartido() + ", " + votos + " votos de legenda (" + porcentagem + "% do total do partido)");
                i++;
            }
        }
    }

    public void distribuicaoEleitosPorIdade(){
        double menosDe30 = 0;
        double entre30e40 = 0;
        double entre40e50 = 0;
        double entre50e60 = 0;
        double maisDe60 = 0;
        double total = 0;

        for(Candidato c : candidatos){
            if(c.getSituacaoCandidato().equals("Eleito")){
                total++;
                if(c.idadeCandidato() < 30){
                    menosDe30++;
                }
                else if(c.idadeCandidato() >= 30 && c.idadeCandidato() < 40){
                    entre30e40++;
                }
                else if(c.idadeCandidato() >= 40 && c.idadeCandidato() < 50){
                    entre40e50++;
                }
                else if(c.idadeCandidato() >= 50 && c.idadeCandidato() < 60){
                    entre50e60++;
                }
                else if(c.idadeCandidato() >= 60){
                    maisDe60++;
                }
            }
        }

        System.out.println("Eleitos, por faixa etária (na data da eleição):");
        System.out.printf(" Idade < 30: %.0f (%.2f)\n", menosDe30, (menosDe30 * 100 / total));
        System.out.printf("30 <= Idade < 40: %.0f (%.2f)\n", entre30e40, entre30e40 * 100 / total);
        System.out.printf("40 <= Idade < 50: %.0f (%.2f)\n", entre40e50, entre40e50 * 100 / total);
        System.out.printf("50 <= Idade < 60: %.0f (%.2f)\n", entre50e60, entre50e60 * 100 / total);
        System.out.printf("60 <= Idade: %.0f (%.2f)\n", maisDe60, maisDe60 * 100 / total);
    }

    public void eleitosPorSexo(){
        double homens = 0;
        double mulheres = 0;
        double total = 0;

        for(Candidato c : candidatos){
            if(c.getSituacaoCandidato().equals("Eleito")){
                total++;
                if(c.getSexoCandidato().equals("M")){
                    homens++;
                }
                else if(c.getSexoCandidato().equals("F")){
                    mulheres++;
                }
            }
        }

        System.out.println("Eleitos, por sexo");
        System.out.printf("Feminino: %.0f (%.2f)\n", mulheres, mulheres * 100 / total);
        System.out.printf("Masculino: %.0f (%.2f)\n", homens, homens * 100 / total);
    }

    public void contabilizacaoDosVotos(){
        double totalVotos = 0;
        double totalVotosNominais = 0;
        double totalVotosLegenda = 0;

        for (Partido p : partidos){
            totalVotosLegenda += p.getVotosLegenda();
        }

        for (Candidato c : candidatos){
            totalVotosNominais += c.getVotosNominaisCandidato();
        }

        totalVotos = totalVotosNominais + totalVotosLegenda;

        System.out.println("Total de votos válidos: " + totalVotos);
        System.out.printf("Total de votos nominais: %.0f (%.2f)\n", totalVotosNominais, totalVotosNominais * 100 / totalVotos);
        System.out.printf("Total de votos legenda: %.0f (%.2f)\n", totalVotosLegenda, totalVotosLegenda * 100 / totalVotos);
    }
}
