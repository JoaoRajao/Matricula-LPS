package org.example.dao;

import org.example.model.Curso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    private static final String FILE_NAME = "cursos.txt";

    public void salvarCurso(Curso curso) {
        List<Curso> cursos = carregarCursos();

        if (!cursos.contains(curso)) {
            try {
                ArquivoUtil.adicionarLinha(FILE_NAME, curso.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Curso já existente: " + curso.getNome());
        }
    }

    public List<Curso> carregarCursos() {
        List<Curso> cursos = new ArrayList<>();
        try {
            List<String> linhas = ArquivoUtil.lerArquivo(FILE_NAME);
            for (String linha : linhas) {
                if (!linha.trim().isEmpty()) { // Verifica se a linha não é vazia
                    String[] dados = linha.split(";");
                    Curso curso = new Curso(dados[0], Integer.parseInt(dados[1]));
                    cursos.add(curso);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cursos;
    }

    public void atualizarArquivo(List<Curso> cursos) {
        List<String> linhas = new ArrayList<>();
        for (Curso curso : cursos) {
            linhas.add(curso.toString());
        }
        try {
            ArquivoUtil.escreverArquivo(FILE_NAME, linhas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
