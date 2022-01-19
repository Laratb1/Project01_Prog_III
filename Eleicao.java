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

    public void imprimeNomePartidosCandidatos(){
        for(Candidato c : candidatos){
            System.out.println(c.getNomeCandidato());
            c.getPartidoCandidato().imprimePartido();
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

    public LinkedList<Candidato> ordenaCandidatosPorVotoNominal(LinkedList<Candidato> lista){
       
        Collections.sort(lista);

        return lista;
    }

    public void imprimeCandidatosMaisVotados(int nVagas){ // Lara (3) COMPARACAO FEITA COM O VOTOS NOMINAIS ?????
        System.out.println("Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas): ");

        int i = 0;
        for(Candidato c : candidatos){
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


    public int numeroDeCandidatosEleitos(){
        int numero = 0;
        for(Candidato c : candidatos){
            if(c.getSituacaoCandidato().equals("Eleito")){
                numero++;
            }
        }
        return numero;
    }

    public int numeroVotosLegenda(){
        int numero = 0;
        for(Partido p : partidos){
                numero += p.getVotosLegenda();
        }
        return numero;
    }

    public void primeiroUltimoColocadoPorPartido(){
        Candidato primeiro = new Candidato();
        Candidato ultimo = new Candidato();
        int maior = 0;
        int menor = 100000;

        for(Partido p : partidos){
            for(Candidato c : candidatos){
                if(c.getNumeroPartidoCandidato().equals(p.getNumeroPartido())){
                    if(c.getSituacaoCandidato().equals("Eleito")){
                        if(c.getVotosNominaisCandidato() > maior){
                            maior = c.getVotosNominaisCandidato();
                            primeiro = c;
                        }
                        if(c.getVotosNominaisCandidato() < menor){
                            menor = c.getVotosNominaisCandidato();
                            ultimo = c;
                        }
                    }
                    else{
                        System.out.println("Ultimo: " + c.getNomeCandidato());
                    }
                }
            }
            maior = 0;
            menor = 100000;

            System.out.println("Primeiro do partido" + p.getNomePartido() + ": " + primeiro.getNomeCandidato());
            System.out.println("Ultimo do partido" + p.getNomePartido() + ": " + ultimo.getNomeCandidato());
        }
    }

}
