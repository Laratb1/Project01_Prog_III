package src.sistema_eleitoral;
public class Candidato {
    private String numero;
    private int votos_nominais;
    private String situacao;
    private String nome;
    private String nome_urna;
    private String sexo;
    private String data_nasc;
    private String destino_voto;
    private int numero_partido;
    private int idade;
    private Partido partido;

    // Getters

    public String getNumeroCandidato() {
        return this.numero;
    }

    public int getVotosNominaisCandidato() {
        return this.votos_nominais;
    }

    public String getSituacaoCandidato() {
        return this.situacao;
    }

    public String getNomeCandidato() {
        return this.nome;
    }

    public String getNomeUrnaCandidato() {
        return this.nome_urna;
    }

    public String getSexoCandidato() {
        return this.sexo;
    }

    public String getDataNasCandidato() {
        return this.data_nasc;
    }

    public String getDestinoVotoCandidato() {
        return this.destino_voto;
    }

    public int getNumeroPartidoCandidato() {
        return this.numero_partido;
    }

    public int getIdadeCandidato() {
        return this.idade;
    }

    public Partido getPartidoCandidato() {
        return this.partido;
    }

    // Setters

    public void setNumeroCandidato(String n) {
        this.numero = n.trim();
    }

    public void setVotosNominaisCandidato(int v) {
        this.votos_nominais = v;
    }

    public void setSituacaoCandidato(String s) {
        this.situacao = s.trim();
    }

    public void setNomeCandidato(String nome) {
        this.nome = nome.trim();
    }

    public void setNomeUrnaCandidato(String nomeUrna) {
        this.nome_urna = nomeUrna.trim();
    }

    public void setSexoCandidato(String sexo) {
        this.sexo = sexo.trim();
    }

    public void setDataNasCandidato(String dataNasc) {
        this.data_nasc = dataNasc.trim();
    }

    public void setDestinoVotoCandidato(String destinoVoto) {
        this.destino_voto = destinoVoto.trim();
    }

    public void setNumeroPartidoCandidato(int numPartido) {
        this.numero_partido = numPartido;
    }

    public void setIdadeCandidato(int data[]) { // recebe como parametro a data da eleicao
        int idade = 0;
        
        // Atribui ano, mes e dia da eleicao as variáveis  
        int anoEleicao = data[2];
        int mesEleicao = data[1];
        int diaEleicao = data[0];

        String[] dataNasc = this.getDataNasCandidato().split("/"); // separa as informaçoes da data de nascimento do candidato 

        // Atribui ano, mes e dia do nascimento do candidato as variáveis 
        int ano = Integer.parseInt(dataNasc[2]);
        int mes = Integer.parseInt(dataNasc[1]);
        int dia = Integer.parseInt(dataNasc[0]);

        if (mes > mesEleicao) { // se o mes de nascimento eh maior que o mes da eleicao
            idade = anoEleicao - ano - 1;
        } else if (mes == mesEleicao) { // se o mes de nascimento eh igual ao mes da eleicao
            if (dia > diaEleicao) { // se o dia de nascimento eh maior que o dia da eleicao
                idade = anoEleicao - ano - 1;
            } else {
                idade = anoEleicao - ano;
            }
        } else {
            idade = anoEleicao - ano;
        }

        this.idade = idade; // Atribui a propriedade idade da classe 
    }

    public void setPartidoCandidato(Partido p) {
        this.partido = p;
    }

}
