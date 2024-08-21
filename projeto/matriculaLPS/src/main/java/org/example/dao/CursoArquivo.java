package org.example.dao;

import org.example.model.Curso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CursoArquivo {

    private static final String ARQUIVO_CURSO = "cursos.txt";

    public static List<Curso> lerCursos() throws IOException {
        List<String> linhas = ArquivoUtil.lerArquivo(ARQUIVO_CURSO);
        List<Curso> cursos = new ArrayList<>();

        for (String linha : linhas) {
            String[] dados = linha.split(";");
            String nome = dados[0];
            int creditos = Integer.parseInt(dados[1]);
            Curso curso = new Curso(nome, creditos);
            cursos.add(curso);
        }

        return cursos;
    }

    public static void salvarCursos(List<Curso> cursos) throws IOException {
        List<String> linhas = new ArrayList<>();

        for (Curso curso : cursos) {
            String linha = curso.getNome() + ";" + curso.getCreditos();
            linhas.add(linha);
        }

        ArquivoUtil.escreverArquivo(ARQUIVO_CURSO, linhas);
    }
}
