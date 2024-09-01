package org.example.dao;

import org.example.model.Curso;
import org.example.model.Disciplina;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    private static final String FILE_NAME = "cursos.txt";

    public void salvarCurso(Curso curso) {
        List<Curso> cursos = carregarCursos();
        boolean cursoExiste = false;

        for (int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).getNome().equals(curso.getNome())) {
                cursos.set(i, curso);
                cursoExiste = true;
                break;
            }
        }

        if (!cursoExiste) {
            cursos.add(curso);
        }

        atualizarArquivo(cursos);
    }

    public List<Curso> carregarCursos() {
        List<Curso> cursos = new ArrayList<>();
        try {
            List<String> linhas = ArquivoUtil.lerArquivo(FILE_NAME);
            for (String linha : linhas) {
                if (!linha.trim().isEmpty()) {
                    String[] dados = linha.split(";");
                    String nomeCurso = dados[0];
                    int creditos = Integer.parseInt(dados[1]);
                    Curso curso = new Curso(nomeCurso, creditos);

                    if (dados.length > 2) {
                        for (int i = 2; i < dados.length; i++) {
                            String nomeDisciplina = dados[i].trim();
                            Disciplina disciplina = new Disciplina(nomeDisciplina, 0, null, null); // Ajuste conforme necessário
                            curso.getDisciplinas().add(disciplina);
                        }
                    }

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
            StringBuilder sb = new StringBuilder();
            sb.append(curso.getNome()).append(";").append(curso.getCreditos());

            for (Disciplina disciplina : curso.getDisciplinas()) {
                sb.append(";").append(disciplina.getNome());
            }

            linhas.add(sb.toString());
        }
        try {
            ArquivoUtil.escreverArquivo(FILE_NAME, linhas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}