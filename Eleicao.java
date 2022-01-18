import java.util.HashSet;
import java.util.Set;

public class Eleicao{
    private Set<Candidato> candidatos = new HashSet<>();
    private Set<Partido> partidos = new HashSet<>();

    //Getters

    public Set<Candidato> getCandidatos(){
        return this.candidatos;
    }
    public Set<Partido> getPartidos(){
        return this.partidos;
    }

    //Setters

    public void setCandidato(Candidato c){
        this.candidatos.add(c);
    }
    public void setPartido(Partido p){
        this.partidos.add(p);
    }

    //Functions

    public void imprimeNomePartidos(){
        for(Partido p : partidos){
            System.out.println(p.getNomePartido());
        }
    }

    public void imprimeNomeCandidatos(){
        for(Candidato c : candidatos){
            System.out.println(c.getNomeCandidato());
        }
    }

    // Especial functions

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
