package Package;
public class Partido {
    private String numero_partido;
    private Integer votos_legenda;
    private String nome_partido;
    private String sigla_partido;

    // Getters

    public String getNumeroPartido() {
        return this.numero_partido;
    }

    public Integer getVotosLegenda() {
        return this.votos_legenda;
    }

    public String getNomePartido() {
        return this.nome_partido;
    }

    public String getSiglaPartido() {
        return this.sigla_partido;
    }

    // Setters

    public void setNumeroPartido(String num) {
        this.numero_partido = num;
    }

    public void setVotosLegenda(Integer votos) {
        this.votos_legenda = votos;
    }

    public void setNomePartido(String nome) {
        this.nome_partido = nome;
    }

    public void setSiglaPartido(String sigla) {
        this.sigla_partido = sigla;
    }

    // Functions

    public void imprimePartido() {
        System.out.println("Numero partido: " + this.numero_partido);
        System.out.println("Sigla: " + this.sigla_partido);
    }
}
