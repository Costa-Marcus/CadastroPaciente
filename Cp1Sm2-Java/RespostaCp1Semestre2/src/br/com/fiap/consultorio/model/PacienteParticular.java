package br.com.fiap.consultorio.model;

public class PacienteParticular extends Paciente {

    public PacienteParticular(int id, String nome, boolean temExame, String nomeExame) {
        super(id, nome, temExame, nomeExame);
    }

    @Override
    public double calcularCustoExame() {

        return ispossuiExames() ? 170.00 : 0.00;
    }

    @Override
    public String toString() {
        return super.toString() + " | Categoria: Particular";
    }
}