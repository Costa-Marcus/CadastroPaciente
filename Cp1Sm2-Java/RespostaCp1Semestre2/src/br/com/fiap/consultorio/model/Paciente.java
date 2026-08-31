package br.com.fiap.consultorio.model;

public class Paciente {

    private int id;
    private String nome ;
    private boolean possuiExames;
    private String nomeExame;
    private boolean examePronto;

    public Paciente(int id, String nome, boolean possuiExames, String nomeExame) {
        this.id = id;
        this.nome = nome;
        this.possuiExames = possuiExames;
        this.nomeExame = possuiExames ? nomeExame : "Nenhum";
        this.examePronto = false;
    }

    public double calcularCustoExame() {
        return 0;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public boolean ispossuiExames() { return possuiExames; }
    public void setpossuiExames(boolean possuiExames) { this.possuiExames = possuiExames; }

    public String getNomeExame() { return nomeExame; }
    public void setNomeExame(String nomeExame) { this.nomeExame = nomeExame; }

    public boolean isExamePronto() { return examePronto; }
    public void setExamePronto(boolean examePronto) { this.examePronto = examePronto; }

    @Override
    public String toString() {
        String status = !possuiExames ? "Sem exames solicitados" : (examePronto ? "PRONTO" : "EM ANDAMENTO");
        return "ID: " + id +
                " | Nome: " + nome +
                " | Exame: " + nomeExame +
                " | Status: " + status +
                " | Valor do Exame: R$ " + String.format("%.2f", calcularCustoExame());
    }
}






