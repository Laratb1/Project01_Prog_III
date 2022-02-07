package src.sistema_eleitoral;
public class Partido {
    private int numero_partido;
    private Integer votos_legenda;
    private String nome_partido;
    private String sigla_partido;
    private int totalVotosNominais;
    private int totalVotos;
    private Candidato primeiroColocado;
    private Candidato ultimoColocado;

    // Getters

    public int getNumeroPartido() {
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

    public int getTotalVotosNominais() {
        return this.totalVotosNominais;
    }

    public int getTotalVotos() {
        return this.totalVotos;
    }

    public Candidato getPrimeiroColocado() {
        return this.primeiroColocado;
    }

    public Candidato getUltimoColocado() {
        return this.ultimoColocado;
    }

    // Setters

    public void setNumeroPartido(int num) {
        this.numero_partido = num;
    }

    public void setVotosLegenda(Integer votos) {
        this.votos_legenda = votos;
    }

    public void setNomePartido(String nome) {
        this.nome_partido = nome.trim();
    }

    public void setSiglaPartido(String sigla) {
        this.sigla_partido = sigla.trim();
    }

    // Atribui a soma do total de votos nominais e do total de votos gerais
    public void setTotalVotosNominais(int totalNominal) { 
        this.setTotalVotos(totalNominal + this.getVotosLegenda());
        this.totalVotosNominais = totalNominal;
    }

    public void setTotalVotos(int total) {
        this.totalVotos = total;
    }

    public void setPrimeiroColocado(Candidato primeiro) {
        this.primeiroColocado = primeiro;
    }

    public void setUltimoColocado(Candidato ultimo) {
        this.ultimoColocado = ultimo;
    }
}
