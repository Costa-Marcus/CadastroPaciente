package br.com.fiap.consultorio.dao;

import br.com.fiap.consultorio.model.Paciente;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {
    private List<Paciente> lista = new ArrayList<>();

    public void cadastrar(Paciente paciente) {
        lista.add(paciente);
    }

    public List<Paciente> listar() {
        return new ArrayList<>(lista);
    }

    public Paciente pesquisarPorId(int id) {
        for (Paciente p : lista) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public List<Paciente> pesquisarPorNome(String nome) {
        List<Paciente> encontrados = new ArrayList<>();
        for (Paciente p : lista) {
            if (p.getNome().toLowerCase().contains(nome.toLowerCase())) {
                encontrados.add(p);
            }
        }
        return encontrados;
    }

    public boolean atualizarStatusExame(int id, boolean pronto) {
        Paciente p = pesquisarPorId(id);
        if (p != null && p.ispossuiExames()) {
            p.setExamePronto(pronto);
            return true;
        }
        return false;
    }

    public boolean remover(int id) {
        Paciente p = pesquisarPorId(id);
        if (p != null) {
            lista.remove(p);
            return true;
        }
        return false;
    }
}