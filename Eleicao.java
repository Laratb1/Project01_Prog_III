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
            System.out.print(p.getNomePartido());
        }
    }

}
