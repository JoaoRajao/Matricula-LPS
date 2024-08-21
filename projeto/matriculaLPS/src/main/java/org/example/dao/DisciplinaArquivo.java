package org.example.dao;

import org.example.model.Disciplina;
import org.example.model.Professor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaArquivo {

    private static final String ARQUIVO_DISCIPLINA = "disciplinas.txt";

    public static List<Disciplina> lerDisciplinas(List<Professor> professores) throws IOException {
        List<String> linhas = ArquivoUtil.lerArquivo(ARQUIVO_DISCIPLINA);
        List<Disciplina> disciplinas = new ArrayList<>();

        for (String linha : linhas) {
            String[] dados = linha.split(";");
            String nome = dados[0];
            int creditos = Integer.parseInt(dados[1]);
            Professor professor = encontrarProfessor(dados[2], professores);
            Disciplina disciplina = new Disciplina(nome, creditos, professor);
            disciplinas.add(disciplina);
        }

        return disciplinas;
    }

    public static void salvarDisciplinas(List<Disciplina> disciplinas) throws IOException {
        List<String> linhas = new ArrayList<>();

        for (Disciplina disciplina : disciplinas) {
            String linha = disciplina.getNome() + ";" + disciplina.getCreditos() + ";" + disciplina.getProfessor().getId();
            linhas.add(linha);
        }

        ArquivoUtil.escreverArquivo(ARQUIVO_DISCIPLINA, linhas);
    }

    private static Professor encontrarProfessor(String id, List<Professor> professores) {
        for (Professor professor : professores) {
            if (professor.getId().equals(id)) {
                return professor;
            }
        }
        return null;
    }
}
