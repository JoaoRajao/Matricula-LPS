package org.example.dao;

import org.example.model.Disciplina;
import org.example.model.Professor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {

    private static final String FILE_NAME = "disciplinas.txt";

    public void salvarDisciplina(Disciplina disciplina) {
        try {
            ArquivoUtil.adicionarLinha(FILE_NAME, disciplina.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Disciplina> carregarDisciplinas(List<Professor> professoresDisponiveis) {
        List<Disciplina> disciplinas = new ArrayList<>();
        try {
            List<String> linhas = ArquivoUtil.lerArquivo(FILE_NAME);
            for (String linha : linhas) {
                String[] dados = linha.split(";");
                Professor professor = professoresDisponiveis.stream()
                        .filter(p -> p.getId().equals(dados[2]))
                        .findFirst()
                        .orElse(null);
                Disciplina disciplina = new Disciplina(dados[0], Integer.parseInt(dados[1]), professor);
                disciplinas.add(disciplina);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return disciplinas;
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
