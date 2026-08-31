package br.com.fiap.consultorio.model;

public class PacienteConvenio extends Paciente {
    private String nomeConvenio;

    public PacienteConvenio(int id, String nome, boolean temExame, String nomeExame, String nomeConvenio) {
        super(id, nome, temExame, nomeExame);
        this.nomeConvenio = nomeConvenio;
    }

    @Override
    public double calcularCustoExame() {
        // Coberto pelo convênio = Gratuito (R$ 0,00)
        return 0.00;
    }

    public String getNomeConvenio() { return nomeConvenio; }
    public void setNomeConvenio(String nomeConvenio) { this.nomeConvenio = nomeConvenio; }

    @Override
    public String toString() {
        return super.toString() + " | Categoria: Convênio (" + nomeConvenio + " - Cobertura 100%)";
    }
}