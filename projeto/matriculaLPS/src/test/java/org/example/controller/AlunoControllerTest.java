package org.example.controller;

import org.example.model.Aluno;
import org.example.model.Curso;
import org.example.model.Disciplina;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AlunoControllerTest {

    private AlunoController alunoController;
    private Aluno aluno;
    private Curso curso;
    private Disciplina disciplina;

    @Before
    public void setUp() {

        alunoController = new AlunoController();
        curso = new Curso("Engenharia de Software", 240);
        aluno = new Aluno("João", "20220001", curso, "joao", "senha123");
        disciplina = new Disciplina("Algoritmos", 4, null);
    }

    @Test
    public void testAdicionarAluno() {
        alunoController.adicionarAluno(aluno);
        List<Aluno> alunos = alunoController.carregarAlunos();

        boolean alunoEncontrado = false;
        for (Aluno a : alunos) {
            if (a.getNome().equals(aluno.getNome()) &&
                    a.getMatricula().equals(aluno.getMatricula()) &&
                    a.getCurso().getNome().equals(aluno.getCurso().getNome()) &&
                    a.getLogin().equals(aluno.getLogin()) &&
                    a.getSenha().equals(aluno.getSenha())) {
                alunoEncontrado = true;
                break;
            }
        }

        assertTrue(alunoEncontrado);
    }

    @Test
    public void testMatricularDisciplina() {
        alunoController.matricularDisciplina(aluno, disciplina);
        assertTrue(aluno.getDisciplinasMatriculadas().contains(disciplina));
    }

    @Test
    public void testCancelarMatricula() {
        alunoController.matricularDisciplina(aluno, disciplina);
        alunoController.cancelarMatricula(aluno, disciplina);
        assertFalse(aluno.getDisciplinasMatriculadas().contains(disciplina));
    }

    @Test
    public void testVisualizarDisciplinasMatriculadas() {
        alunoController.matricularDisciplina(aluno, disciplina);
        List<String> disciplinas = alunoController.visualizarDisciplinasMatriculadas(aluno);
        assertEquals(1, disciplinas.size());
        assertEquals("Algoritmos", disciplinas.get(0));
    }
}