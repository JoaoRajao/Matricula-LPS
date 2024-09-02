package org.example;

import org.example.controller.*;
import org.example.dao.*;
import org.example.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static ProfessorDAO professorController;
    private static CursoDAO cursoDAO;

    public static void main(String[] args) {

        AlunoController alunoController = new AlunoController();
        ProfessorController professorController = new ProfessorController();
        CursoController cursoController = new CursoController();
        DisciplinaController disciplinaController = new DisciplinaController();
        SecretariaController secretariaController = new SecretariaController();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

        List<Professor> professores = professorController.carregarProfessores();
        List<Aluno> alunos = alunoController.carregarAlunos();
        Secretaria secretaria = new Secretaria("admin", "admin123");


        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {

                System.out.println("\nBem-vindo ao Sistema de Matrículas da Universidade");
                System.out.println("1. Logar como Secretaria");
                System.out.println("2. Logar como Professor");
                System.out.println("3. Logar como Aluno");
                System.out.println("4. Adicionar Usuário");
                System.out.println("5. Sair");
                System.out.print("Escolha uma opção: ");

                int opcaoInicial = Integer.parseInt(scanner.nextLine());

                if (opcaoInicial == 5) {
                    System.out.println("Saindo do sistema...");
                    break;
                }

                switch (opcaoInicial) {
                    case 1:
                        System.out.print("Digite o login da secretaria: ");
                        String loginSec = scanner.nextLine();
                        System.out.print("Digite a senha da secretaria: ");
                        String senhaSec = scanner.nextLine();

                        if (secretaria.getLogin().equals(loginSec) && secretaria.getSenha().equals(senhaSec)) {
                            executarMenuSecretaria(scanner, secretariaController, secretaria);
                        } else {
                            System.out.println("Login ou senha incorretos para a secretaria.");
                        }
                        break;

                    case 2:
                        System.out.print("Digite o login do professor: ");
                        String loginProf = scanner.nextLine();
                        System.out.print("Digite a senha do professor: ");
                        String senhaProf = scanner.nextLine();

                        Professor professorLogado = null;
                        for (Professor prof : professores) {
                            if (prof.getLogin().equals(loginProf) && prof.getSenha().equals(senhaProf)) {
                                professorLogado = prof;
                                break;
                            }
                        }

                        if (professorLogado != null) {
                            executarMenuProfessor(scanner, professorController, secretariaController, professorLogado);
                        } else {
                            System.out.println("Login ou senha incorretos para o professor.");
                        }
                        break;

                    case 3:
                        System.out.print("Digite o login do aluno: ");
                        String loginAluno = scanner.nextLine();
                        System.out.print("Digite a senha do aluno: ");
                        String senhaAluno = scanner.nextLine();

                        Aluno alunoLogado = null;
                        for (Aluno a : alunos) {
                            if (a.getLogin().equals(loginAluno) && a.getSenha().equals(senhaAluno)) {
                                alunoLogado = a;
                                break;
                            }
                        }

                        if (alunoLogado != null) {
                            executarMenuAluno(scanner, alunoController, alunoLogado);
                        } else {
                            System.out.println("Login ou senha incorretos para o aluno.");
                        }
                        break;

                    case 4:
                        System.out.println("Escolha o tipo de usuário para adicionar:");
                        System.out.println("1. Adicionar Secretaria");
                        System.out.println("2. Adicionar Professor");
                        System.out.println("3. Adicionar Aluno");
                        int tipoUsuario = Integer.parseInt(scanner.nextLine());

                        switch (tipoUsuario) {
                            case 1:
                                System.out.print("Digite o login da nova secretaria: ");
                                String loginNovaSec = scanner.nextLine();
                                System.out.print("Digite a senha da nova secretaria: ");
                                String senhaNovaSec = scanner.nextLine();
                                secretaria = new Secretaria(loginNovaSec, senhaNovaSec);
                                //secretariaController.adicionarSecretaria(secretaria);
                                System.out.println("Secretaria adicionada com sucesso!");
                                break;

                            case 2:
                                System.out.print("Digite o nome do professor: ");
                                String nomeProfessor = scanner.nextLine();
                                System.out.print("Digite o ID do professor: ");
                                String idProfessor = scanner.nextLine();
                                System.out.print("Digite o login do professor: ");
                                String loginProfessor = scanner.nextLine();
                                System.out.print("Digite a senha do professor: ");
                                String senhaProfessor = scanner.nextLine();
                                Professor novoProfessor = new Professor(nomeProfessor, idProfessor, loginProfessor, senhaProfessor);
                                professorController.adicionarProfessor(novoProfessor);
                                System.out.println("Professor adicionado com sucesso!");
                                break;

                            case 3:
                                System.out.print("Digite o nome do aluno: ");
                                String nomeAluno = scanner.nextLine();
                                System.out.print("Digite a matrícula do aluno: ");
                                String matriculaAluno = scanner.nextLine();
                                System.out.print("Digite o login do aluno: ");
                                String loginNovoAluno = scanner.nextLine();
                                System.out.print("Digite a senha do aluno: ");
                                String senhaNovoAluno = scanner.nextLine();
                                System.out.print("Digite o nome do curso do aluno: ");
                                String nomeCursoAluno = scanner.nextLine();
                                Curso cursoAluno = null;
                                for (Curso c : secretaria.getCursos()) {
                                    if (c.getNome().equals(nomeCursoAluno)) {
                                        cursoAluno = c;
                                        break;
                                    }
                                }
                                if (cursoAluno == null) {
                                    System.out.println("Curso não encontrado. Aluno não adicionado.");
                                } else {
                                    Aluno novoAluno = new Aluno(nomeAluno, matriculaAluno, cursoAluno, loginNovoAluno, senhaNovoAluno);
                                    alunoController.adicionarAluno(novoAluno);
                                    System.out.println("Aluno adicionado com sucesso!");
                                }
                                break;

                            default:
                                System.out.println("Opção inválida.");
                        }
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            } catch (Exception e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void executarMenuSecretaria(Scanner scanner, SecretariaController secretariaController, Secretaria secretaria) {
        while (true) {
            try {
                System.out.println("\nMenu da Secretaria");
                System.out.println("1. Adicionar Curso");
                System.out.println("2. Adicionar Professor");
                System.out.println("3. Adicionar Disciplina");
                System.out.println("4. Adicionar Aluno");
                System.out.println("5. Verificar Status de Todas as Disciplinas");
                System.out.println("6. Listar Cursos");
                System.out.println("7. Listar Professores");
                System.out.println("8. Listar Disciplinas");
                System.out.println("9. Listar Alunos");
                System.out.println("10. Voltar ao Menu Principal");
                System.out.print("Escolha uma opção: ");

                int opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        System.out.print("Digite o nome do curso: ");
                        String nomeCurso = scanner.nextLine();
                        System.out.print("Digite o número de créditos do curso: ");
                        int creditosCurso = Integer.parseInt(scanner.nextLine());
                        Curso curso = new Curso(nomeCurso, creditosCurso);
                        secretariaController.adicionarCurso(curso);
                        System.out.println("Curso adicionado com sucesso!");
                        break;

                    case 2:
                        System.out.print("Digite o nome do professor: ");
                        String nomeProfessor = scanner.nextLine();
                        System.out.print("Digite o ID do professor: ");
                        String idProfessor = scanner.nextLine();
                        System.out.print("Digite o login do professor: ");
                        String loginProfessor = scanner.nextLine();
                        System.out.print("Digite a senha do professor: ");
                        String senhaProfessor = scanner.nextLine();
                        Professor professor = new Professor(nomeProfessor, idProfessor, loginProfessor, senhaProfessor);
                        secretariaController.adicionarProfessor(professor);
                        System.out.println("Professor adicionado com sucesso!");
                        break;

                    case 3:
                        System.out.print("Digite o nome da disciplina: ");
                        String nomeDisciplina = scanner.nextLine();
                        System.out.print("Digite o número de créditos da disciplina: ");
                        int creditosDisciplina = Integer.parseInt(scanner.nextLine());
                        System.out.print("Digite o tipo da disciplina (1 para OBRIGATORIA, 2 para OPTATIVA): ");
                        int tipoDisciplinaInt = Integer.parseInt(scanner.nextLine());
                        TipoDisciplina tipoDisciplina = tipoDisciplinaInt == 1 ? TipoDisciplina.OBRIGATORIA : TipoDisciplina.OPTATIVA;

                        System.out.print("Digite o nome do professor para associar à disciplina: ");
                        String nomeProfessorDisc = scanner.nextLine();
                        Professor professorDisciplina = null;

                        List<Professor> professores = secretariaController.listarProfessores();
                        for (Professor p : professores) {
                            if (p.getNome().equalsIgnoreCase(nomeProfessorDisc)) {
                                professorDisciplina = p;
                                break;
                            }
                        }

                        if (professorDisciplina == null) {
                            System.out.println("Professor não encontrado. Disciplina não adicionada.");
                            break;
                        }

                        Disciplina disciplina = new Disciplina(nomeDisciplina, creditosDisciplina, professorDisciplina, tipoDisciplina);
                        secretariaController.adicionarDisciplina(disciplina);
                        secretariaController.adicionarDisciplinaAoProfessor(disciplina, professorDisciplina);

                        System.out.print("Digite o nome do curso para associar à disciplina: ");
                        String nomeCursoDisc = scanner.nextLine();
                        Curso cursoDisciplina = null;

                        List<Curso> cursos = secretariaController.listarCursos();
                        for (Curso c : cursos) {
                            if (c.getNome().equalsIgnoreCase(nomeCursoDisc)) {
                                cursoDisciplina = c;
                                break;
                            }
                        }

                        if (cursoDisciplina != null) {
                            CursoController cursoController = new CursoController();
                            cursoController.adicionarDisciplinaAoCurso(cursoDisciplina, disciplina);
                            System.out.println("Disciplina adicionada ao curso com sucesso!");
                        } else {
                            System.out.println("Curso não encontrado. Disciplina não adicionada ao curso.");
                        }

                        System.out.println("Disciplina adicionada e associada ao professor com sucesso!");
                        break;

                    case 4:
                        System.out.print("Digite o nome do aluno: ");
                        String nomeAluno = scanner.nextLine();
                        System.out.print("Digite a matrícula do aluno: ");
                        String matriculaAluno = scanner.nextLine();
                        System.out.print("Digite o login do aluno: ");
                        String loginAluno = scanner.nextLine();
                        System.out.print("Digite a senha do aluno: ");
                        String senhaAluno = scanner.nextLine();
                        System.out.print("Digite o nome do curso do aluno: ");
                        String nomeCursoAluno = scanner.nextLine();
                        Curso cursoAluno = null;
                        for (Curso c : secretariaController.listarCursos()) {
                            if (c.getNome().equalsIgnoreCase(nomeCursoAluno)) {
                                cursoAluno = c;
                                break;
                            }
                        }
                        if (cursoAluno == null) {
                            System.out.println("Curso não encontrado. Aluno não adicionado.");
                            break;
                        }
                        Aluno aluno = new Aluno(nomeAluno, matriculaAluno, cursoAluno, loginAluno, senhaAluno);
                        secretariaController.adicionarAluno(aluno);
                        System.out.println("Aluno adicionado com sucesso!");
                        break;

                    case 5:
                        secretariaController.verificarStatusDeTodasAsDisciplinas();
                        System.out.println("Status de todas as disciplinas verificado e atualizado.");
                        break;

                    case 6:
                        List<Curso> cursos1 = secretariaController.listarCursos();
                        System.out.println("Cursos disponíveis:");
                        for (Curso c : cursos1) {
                            System.out.println(c.getNome() + " - " + c.getCreditos() + " créditos");
                        }
                        break;

                    case 7:
                        List<Professor> professoresList = secretariaController.listarProfessores();
                        System.out.println("Professores:");
                        for (Professor p : professoresList) {
                            System.out.println(p.getNome());
                        }
                        break;

                    case 8:
                        List<Disciplina> disciplinas = secretariaController.listarDisciplinas();
                        System.out.println("Disciplinas:");
                        for (Disciplina d : disciplinas) {
                            System.out.println(d.getNome() + " - " + d.getTipo());
                        }
                        break;

                    case 9:
                        List<Aluno> alunos = secretariaController.listarAlunos();
                        System.out.println("Alunos:");
                        for (Aluno a : alunos) {
                            System.out.println(a.getNome() + " - " + a.getMatricula());
                        }
                        break;

                    case 10:
                        return;

                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            } catch (Exception e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
            }
        }
    }

    private static void executarMenuProfessor(Scanner scanner, ProfessorController professorController, SecretariaController secretariaController, Professor professor) {
        while (true) {
            try {
                System.out.println("\nMenu do Professor");
                System.out.println("1. Visualizar Disciplinas Lecionadas");
                System.out.println("2. Visualizar Alunos por Disciplina");
                System.out.println("3. Voltar ao Menu Principal");
                System.out.print("Escolha uma opção: ");

                int opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        List<String> disciplinasLecionadas = professorController.visualizarDisciplinasLecionadas(professor);

                        if (disciplinasLecionadas.isEmpty()) {
                            System.out.println("Nenhuma disciplina lecionada encontrada.");
                        } else {
                            System.out.println("Disciplinas lecionadas:");
                            for (String disciplina : disciplinasLecionadas) {
                                System.out.println(disciplina);
                            }
                        }
                        break;

                    case 2:
                        Map<String, List<Aluno>> alunosPorDisciplina = professorController.visualizarAlunosPorDisciplina(professor);

                        if (alunosPorDisciplina.isEmpty()) {
                            System.out.println("Nenhuma disciplina encontrada ou sem alunos matriculados.");
                        } else {
                            System.out.println("Alunos por disciplina:");
                            for (Map.Entry<String, List<Aluno>> entry : alunosPorDisciplina.entrySet()) {
                                System.out.println("Disciplina: " + entry.getKey());
                                List<Aluno> alunos = entry.getValue();
                                if (alunos.isEmpty()) {
                                    System.out.println("Nenhum aluno matriculado.");
                                } else {
                                    for (Aluno aluno : alunos) {
                                        System.out.println("Aluno: " + aluno.getNome());
                                    }
                                }
                            }
                        }
                        break;

                    case 3:
                        return;

                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            } catch (Exception e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
            }
        }
    }

    private static void executarMenuAluno(Scanner scanner, AlunoController alunoController, Aluno aluno) {
        while (true) {
            try {
                System.out.println("\nMenu do Aluno");
                System.out.println("1. Visualizar Disciplinas Matriculadas");
                System.out.println("2. Matricular-se em uma Disciplina");
                System.out.println("3. Cancelar Matrícula em uma Disciplina");
                System.out.println("4. Voltar ao Menu Principal");
                System.out.print("Escolha uma opção: ");

                int opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        List<String> disciplinasMatriculadas = alunoController.visualizarDisciplinasMatriculadas(aluno);
                        System.out.println("Disciplinas matriculadas:");
                        for (String d : disciplinasMatriculadas) {
                            System.out.println(d);
                        }
                        break;

                    case 2:
                        System.out.println("Digite o nome da disciplina que deseja se matricular: ");
                        String nomeDisciplina = scanner.nextLine();

                        List<Disciplina> todasDisciplinas = alunoController.carregarTodasDisciplinas();
                        Disciplina disciplinaEscolhida = null;

                        for (Disciplina d : todasDisciplinas) {
                            if (d.getNome().equalsIgnoreCase(nomeDisciplina)) {
                                disciplinaEscolhida = d;
                                break;
                            }
                        }

                        if (disciplinaEscolhida != null) {
                            List<Disciplina> disciplinasParaMatricular = new ArrayList<>();
                            disciplinasParaMatricular.add(disciplinaEscolhida);
                            alunoController.matricularDisciplinas(aluno, disciplinasParaMatricular);

                            alunoController.salvarAluno(aluno);

                            System.out.println("Matrícula realizada com sucesso na disciplina " + disciplinaEscolhida.getNome());
                        } else {
                            System.out.println("Disciplina não encontrada.");
                        }
                        break;

                    case 3:
                        System.out.println("Digite o nome da disciplina que deseja cancelar a matrícula: ");
                        String nomeDisciplinaCancelar = scanner.nextLine();

                        Disciplina disciplinaParaCancelar = null;
                        for (Disciplina d : aluno.getDisciplinasMatriculadas()) {
                            if (d.getNome().equalsIgnoreCase(nomeDisciplinaCancelar)) {
                                disciplinaParaCancelar = d;
                                break;
                            }
                        }

                        if (disciplinaParaCancelar != null) {
                            alunoController.cancelarMatricula(aluno, disciplinaParaCancelar);
                            alunoController.salvarAluno(aluno);
                            System.out.println("Matrícula na disciplina " + disciplinaParaCancelar.getNome() + " cancelada com sucesso.");
                        } else {
                            System.out.println("Disciplina não encontrada ou aluno não está matriculado.");
                        }
                        break;

                    case 4:
                        return;

                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
            } catch (Exception e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
            }
        }
    }
}
