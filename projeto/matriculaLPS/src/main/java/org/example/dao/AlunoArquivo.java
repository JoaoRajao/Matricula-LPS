package org.example.dao;

import org.example.model.Aluno;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AlunoArquivo {

    private static final String ARQUIVO_ALUNO = "alunos.txt";

   /* public static List<Aluno> lerAlunos() throws IOException {
        List<String> linhas = ArquivoUtil.lerArquivo(ARQUIVO_ALUNO);
        List<Aluno> alunos = new ArrayList<>();

        for (String linha : linhas) {
            String[] dados = linha.split(";");
            String nome = dados[0];
            String matricula = dados[1];
            Aluno aluno = new Aluno(nome, matricula);
            alunos.add(aluno);
        }

        return alunos;
    }
*/
    public static void salvarAlunos(List<Aluno> alunos) throws IOException {
        List<String> linhas = new ArrayList<>();

        for (Aluno aluno : alunos) {
            String linha = aluno.getNome() + ";" + aluno.getMatricula();
            linhas.add(linha);
        }

        ArquivoUtil.escreverArquivo(ARQUIVO_ALUNO, linhas);
    }
}
