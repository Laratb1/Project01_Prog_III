public class Candidato implements Comparable<Candidato>{
    private String numero;
    private int votos_nominais;
    private String situacao;
    private String nome;
    private String nome_urna;
    private String sexo;
    private String data_nasc;
    private String destino_voto;
    private String numero_partido;
    private int idade;
    private Partido partido;

    //Getters

    public String getNumeroCandidato(){
        return this.numero;
    }
    public int getVotosNominaisCandidato(){
        return this.votos_nominais;
    }
    public String getSituacaoCandidato(){
        return this.situacao;
    }
    public String getNomeCandidato(){
        return this.nome;
    }
    public String getNomeUrnaCandidato(){
        return this.nome_urna;
    }
    public String getSexoCandidato(){
        return this.sexo;
    }
    public String getDataNasCandidato(){
        return this.data_nasc;
    }
    public String getDestinoVotoCandidato(){
        return this.destino_voto;
    }
    public String getNumeroPartidoCandidato(){
        return this.numero_partido;
    }
    public int getIdadeCandidato(){
        return this.idade;
    }
    public Partido getPartidoCandidato(){
        return this.partido;
    }

    //Setters

    public void setNumeroCandidato(String n){
        this.numero = n;
    }
    public void setVotosNominaisCandidato(int v){
        this.votos_nominais = v;
    }
    public void setSituacaoCandidato(String s){
        this.situacao = s;
    }
    public void setNomeCandidato(String nome){
        this.nome = nome;
    }
    public void setNomeUrnaCandidato(String nomeUrna){
        this.nome_urna = nomeUrna;
    }
    public void setSexoCandidato(String sexo){
        this.sexo = sexo;
    }
    public void setDataNasCandidato(String dataNasc){
        this.data_nasc = dataNasc;
    }
    public void setDestinoVotoCandidato(String destinoVoto){
        this.destino_voto = destinoVoto;
    }
    public void setNumeroPartidoCandidato(String numPartido){
        this.numero_partido = numPartido;
    }
    public void setIdadeCandidato(int data[]){
        int idade = 0;

        int anoComp = data[2];
        int mesComp = data[1];
        int diaComp = data[0];

        String[] dataNasc = this.getDataNasCandidato().split("/");

        int ano = Integer.parseInt(dataNasc[2]);
        int mes = Integer.parseInt(dataNasc[1]);
        int dia = Integer.parseInt(dataNasc[0]);


        if (mes < mesComp){
            idade = anoComp - ano - 1;
        }
        else if (mes == mesComp){
            if(dia < diaComp){
                idade = anoComp - ano - 1;
            }
            else{
                idade = anoComp - ano;
            }
        }
        else{
            idade = anoComp - ano;
        }
        
        this.idade = idade;
        
    }
    public void setPartidoCandidato(Partido p){
        this.partido = p;
    }

    //Functions

    @Override
    public int compareTo(Candidato c){ 
        if(this.votos_nominais == c.getVotosNominaisCandidato()){
            if(this.idade > c.getIdadeCandidato()){
                return -1;
            }
            if(this.idade < c.getIdadeCandidato()){
                return 1;
            }
        }
        if(this.votos_nominais > c.getVotosNominaisCandidato()){ 
            return -1; 
        } 
        if(this.votos_nominais < c.getVotosNominaisCandidato()){ 
            return 1; 
        } 
        
        return 0; 
    }

}

