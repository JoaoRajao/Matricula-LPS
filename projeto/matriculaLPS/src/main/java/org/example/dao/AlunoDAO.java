package org.example.dao;

import org.example.model.Aluno;
import org.example.model.Curso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private static final String FILE_NAME = "alunos.txt";

    public void salvarAluno(Aluno aluno) {
        try {
            ArquivoUtil.adicionarLinha(FILE_NAME, aluno.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Aluno> carregarAlunos(List<Curso> cursosDisponiveis) {
        List<Aluno> alunos = new ArrayList<>();
        try {
            List<String> linhas = ArquivoUtil.lerArquivo(FILE_NAME);
            for (String linha : linhas) {
                String[] dados = linha.split(";");
                Curso curso = cursosDisponiveis.stream()
                        .filter(c -> c.getNome().equals(dados[2]))
                        .findFirst()
                        .orElse(null);
                Aluno aluno = new Aluno(dados[0], dados[1], curso, dados[3], dados[4]);
                alunos.add(aluno);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return alunos;
    }

    public void atualizarArquivo(List<Aluno> alunos) {
        List<String> linhas = new ArrayList<>();
        for (Aluno aluno : alunos) {
            linhas.add(aluno.toString());
        }
        try {
            ArquivoUtil.escreverArquivo(FILE_NAME, linhas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
