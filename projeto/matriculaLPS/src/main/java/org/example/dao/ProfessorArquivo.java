package org.example.dao;

import org.example.model.Professor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorArquivo {

    private static final String ARQUIVO_PROFESSOR = "professores.txt";

    public static List<Professor> lerProfessores() throws IOException {
        List<String> linhas = ArquivoUtil.lerArquivo(ARQUIVO_PROFESSOR);
        List<Professor> professores = new ArrayList<>();

        for (String linha : linhas) {
            String[] dados = linha.split(";");
            String nome = dados[0];
            String id = dados[1];
            Professor professor = new Professor(nome, id);
            professores.add(professor);
        }

        return professores;
    }

    public static void salvarProfessores(List<Professor> professores) throws IOException {
        List<String> linhas = new ArrayList<>();

        for (Professor professor : professores) {
            String linha = professor.getNome() + ";" + professor.getId();
            linhas.add(linha);
        }

        ArquivoUtil.escreverArquivo(ARQUIVO_PROFESSOR, linhas);
    }
}

