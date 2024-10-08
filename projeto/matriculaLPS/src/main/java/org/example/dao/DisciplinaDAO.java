package org.example.dao;

import org.example.model.Disciplina;
import org.example.model.Professor;
import org.example.model.TipoDisciplina;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {
    private static final String FILE_NAME = "disciplinas.txt";
    private ProfessorDAO professorDAO;

    public DisciplinaDAO() {
        this.professorDAO = new ProfessorDAO();
    }

    public List<Disciplina> carregarDisciplinas() {
        List<Professor> professoresDisponiveis = professorDAO.carregarProfessores();
        List<Disciplina> disciplinas = new ArrayList<>();

        try {
            List<String> linhas = ArquivoUtil.lerArquivo(FILE_NAME);
            for (String linha : linhas) {
                if (linha.trim().isEmpty()) {
                    continue;
                }

                String[] dados = linha.split(";");


                if (dados.length < 4) {
                    System.out.println("Linha com formato incorreto: " + linha);
                    continue;
                }

                String nomeDisciplina = dados[0];
                int creditos;
                try {
                    creditos = Integer.parseInt(dados[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Número de créditos inválido: " + dados[1]);
                    continue;
                }

                String nomeProfessor = dados[2];
                TipoDisciplina tipo;
                try {
                    tipo = TipoDisciplina.valueOf(dados[3]);
                } catch (IllegalArgumentException e) {
                    System.out.println("Tipo de disciplina inválido: " + dados[3]);
                    continue;
                }

                Professor professor = professoresDisponiveis.stream()
                        .filter(p -> p.getNome().equals(nomeProfessor))
                        .findFirst()
                        .orElse(null);

                Disciplina disciplina = new Disciplina(nomeDisciplina, creditos, professor, tipo);
                disciplinas.add(disciplina);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return disciplinas;
    }

    public void salvarDisciplina(Disciplina disciplina) {
        try {
            ArquivoUtil.adicionarLinha(FILE_NAME, disciplina.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void atualizarArquivo(List<Disciplina> disciplinas) {
        List<String> linhas = new ArrayList<>();
        for (Disciplina disciplina : disciplinas) {
            linhas.add(disciplina.toString());
        }
        try {
            ArquivoUtil.escreverArquivo(FILE_NAME, linhas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}