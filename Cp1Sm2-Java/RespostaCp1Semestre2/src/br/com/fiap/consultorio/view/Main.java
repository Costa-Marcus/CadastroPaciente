package br.com.fiap.consultorio.view;
import br.com.fiap.consultorio.dao.PacienteDAO;
import br.com.fiap.consultorio.model.Paciente;
import br.com.fiap.consultorio.model.PacienteConvenio;
import br.com.fiap.consultorio.model.PacienteParticular;
import javax.swing.JOptionPane;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        PacienteDAO dao = new PacienteDAO();
        int opcao = -1;

        do {
            String menu = "--------- SISTEMA CONSULTÓRIO ---------\n"
                    + "1. Cadastrar Paciente Particular (Exame: R$ 170,00)\n"
                    + "2. Cadastrar Paciente Convênio (Exame: Gratuito)\n"
                    + "3. Listar Todos os Pacientes\n"
                    + "4. Pesquisar Paciente por ID\n"
                    + "5. Pesquisar Paciente por Nome\n"
                    + "6. Consultar se Tem Exame e Status\n"
                    + "7. Atualizar Status do Exame (Pronto / Pendente)\n"
                    + "8. Excluir Cadastro de Paciente\n"
                    + "0. Sair\n\n"
                    + "Escolha uma opção:";

            String inputOpcao = JOptionPane.showInputDialog(null, menu, "Menu Principal", JOptionPane.QUESTION_MESSAGE);


            if (inputOpcao == null) {
                break;
            }

            try {
                opcao = Integer.parseInt(inputOpcao.trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opção inválida! Digite apenas números inteiros.", "Erro", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            switch (opcao) {
                case 1: {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o ID do Paciente:", "Cadastro Particular", JOptionPane.QUESTION_MESSAGE));
                    String nome = JOptionPane.showInputDialog(null, "Informe o Nome:", "Cadastro Particular", JOptionPane.QUESTION_MESSAGE);

                    int respExame = JOptionPane.showConfirmDialog(null, "Possui exame a realizar?", "Exame", JOptionPane.YES_NO_OPTION);
                    boolean temExame = (respExame == JOptionPane.YES_OPTION);
                    String nomeExame = "Nenhum";

                    if (temExame) {
                        nomeExame = JOptionPane.showInputDialog(null, "Informe o Nome do Exame:", "Cadastro Particular", JOptionPane.QUESTION_MESSAGE);
                    }

                    dao.cadastrar(new PacienteParticular(id, nome, temExame, nomeExame));
                    JOptionPane.showMessageDialog(null, "Paciente Particular cadastrado com sucesso!\nTaxa fixa do exame: R$ 170,00", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }

                case 2: {
                    int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o ID do Paciente:", "Cadastro Convênio", JOptionPane.QUESTION_MESSAGE));
                    String nome = JOptionPane.showInputDialog(null, "Informe o Nome:", "Cadastro Convênio", JOptionPane.QUESTION_MESSAGE);

                    int respExame = JOptionPane.showConfirmDialog(null, "Possui exame a realizar?", "Exame", JOptionPane.YES_NO_OPTION);
                    boolean temExame = (respExame == JOptionPane.YES_OPTION);
                    String nomeExame = "Nenhum";

                    if (temExame) {
                        nomeExame = JOptionPane.showInputDialog(null, "Informe o Nome do Exame:", "Cadastro Convênio", JOptionPane.QUESTION_MESSAGE);
                    }

                    String convenio = JOptionPane.showInputDialog(null, "Informe o Nome do Convênio:", "Cadastro Convênio", JOptionPane.QUESTION_MESSAGE);

                    dao.cadastrar(new PacienteConvenio(id, nome, temExame, nomeExame, convenio));
                    JOptionPane.showMessageDialog(null, "Paciente Convênio cadastrado com sucesso!\nExame 100% coberto pelo convênio (Gratuito).", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }

                case 3: {
                    List<Paciente> lista = dao.listar();
                    if (lista.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nenhum paciente cadastrado.", "Lista Vazia", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        StringBuilder sb = new StringBuilder("=== RELATÓRIO DE PACIENTES ===\n\n");
                        for (Paciente p : lista) {
                            sb.append(p.toString()).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, sb.toString(), "Pacientes Cadastrados", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                }

                case 4: {
                    int idBusca = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o ID a pesquisar:", "Pesquisa por ID", JOptionPane.QUESTION_MESSAGE));
                    Paciente p = dao.pesquisarPorId(idBusca);
                    if (p != null) {
                        JOptionPane.showMessageDialog(null, p.toString(), "Paciente Encontrado", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Paciente não encontrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    }
                    break;
                }

                case 5: {
                    String buscaNome = JOptionPane.showInputDialog(null, "Informe o nome ou parte dele:", "Pesquisa por Nome", JOptionPane.QUESTION_MESSAGE);
                    List<Paciente> encontrados = dao.pesquisarPorNome(buscaNome);
                    if (encontrados.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nenhum paciente localizado com esse nome.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    } else {
                        StringBuilder sb = new StringBuilder("--- PACIENTES ENCONTRADOS ---\n\n");
                        for (Paciente p : encontrados) {
                            sb.append(p.toString()).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, sb.toString(), "Resultado da Pesquisa", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                }

                case 6: {
                    int idConsulta = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o ID do paciente:", "Consultar Exame", JOptionPane.QUESTION_MESSAGE));
                    Paciente pac = dao.pesquisarPorId(idConsulta);

                    if (pac == null) {
                        JOptionPane.showMessageDialog(null, "Paciente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                    } else if (!pac.ispossuiExames()) {
                        JOptionPane.showMessageDialog(null, "O paciente " + pac.getNome() + " NÃO possui exames solicitados.", "Informação", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        String statusExame = pac.isExamePronto() ? "PRONTO PARA RETIRADA" : "EM ANDAMENTO";
                        String detalhes = "Paciente: " + pac.getNome() + "\n"
                                + "Exame: " + pac.getNomeExame() + "\n"
                                + "Status: " + statusExame + "\n"
                                + "Valor a Pagar: R$ " + String.format("%.2f", pac.calcularCustoExame());
                        JOptionPane.showMessageDialog(null, detalhes, "Status do Exame", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                }

                case 7: {
                    int idAtualizar = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o ID do paciente:", "Atualizar Status", JOptionPane.QUESTION_MESSAGE));
                    int respStatus = JOptionPane.showConfirmDialog(null, "O exame já está pronto?", "Atualizar Status", JOptionPane.YES_NO_OPTION);
                    boolean statusNovo = (respStatus == JOptionPane.YES_OPTION);

                    if (dao.atualizarStatusExame(idAtualizar, statusNovo)) {
                        JOptionPane.showMessageDialog(null, "Status do exame atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Não foi possível atualizar (paciente inexistente ou sem exames).", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }

                case 8: {
                    int idRemover = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o ID a remover:", "Exclusão", JOptionPane.QUESTION_MESSAGE));
                    if (dao.remover(idRemover)) {
                        JOptionPane.showMessageDialog(null, "Cadastro removido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Paciente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }

                case 0:
                    JOptionPane.showMessageDialog(null, "Encerrando o sistema...", "Saindo", JOptionPane.INFORMATION_MESSAGE);
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida! Escolha um número de 0 a 8.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } while (opcao != 0);
    }
}