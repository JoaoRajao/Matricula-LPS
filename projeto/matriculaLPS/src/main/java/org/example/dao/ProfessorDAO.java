package org.example.dao;

import org.example.model.Professor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

    private static final String FILE_NAME = "professores.txt";

    public void salvarProfessor(Professor professor) {
        List<Professor> professores = carregarProfessores();
        boolean professorExiste = false;

        for (int i = 0; i < professores.size(); i++) {
            Professor p = professores.get(i);
            if (p.getId().equals(professor.getId())) {
                professores.set(i, professor);
                professorExiste = true;
                break;
            }
        }

        if (!professorExiste) {
            professores.add(professor);
        }

        List<String> linhas = new ArrayList<>();
        for (Professor p : professores) {
            linhas.add(p.toString());
        }

        try {
            ArquivoUtil.escreverArquivo(FILE_NAME, linhas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Professor> carregarProfessores() {
        List<Professor> professores = new ArrayList<>();
        try {
            List<String> linhas = ArquivoUtil.lerArquivo(FILE_NAME);
            for (String linha : linhas) {
                String[] dados = linha.split(";");


                if (dados.length < 4) {
                    System.out.println("Linha com formato incorreto: " + linha);
                    continue;
                }

                Professor professor = new Professor(dados[0], dados[1], dados[2], dados[3]);
                professores.add(professor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return professores;
    }

    public void atualizarArquivo(List<Professor> professores) {
        List<String> linhas = new ArrayList<>();
        for (Professor professor : professores) {
            linhas.add(professor.toString());
        }
        try {
            ArquivoUtil.escreverArquivo(FILE_NAME, linhas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
