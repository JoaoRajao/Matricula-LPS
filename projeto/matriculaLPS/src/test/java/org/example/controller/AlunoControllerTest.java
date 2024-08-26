package org.example.controller;

import org.example.model.Aluno;
import org.example.model.Curso;
import org.example.model.Disciplina;
import org.example.model.TipoDisciplina;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AlunoControllerTest {

    private AlunoController alunoController;
    private Aluno aluno;
    private Curso curso;
    private Disciplina disciplinaObrigatoria;
    private Disciplina disciplinaOptativa;
    private List<Disciplina> disciplinas;

    @Before
    public void setUp() {
        alunoController = new AlunoController();
        curso = new Curso("Engenharia de Software", 240);
        aluno = new Aluno("João", "20220001", curso, "joao", "senha123");

        disciplinaObrigatoria = new Disciplina("Algoritmos", 4, null, TipoDisciplina.OBRIGATORIA);
        disciplinaOptativa = new Disciplina("Filosofia", 2, null, TipoDisciplina.OPTATIVA);

        disciplinas = new ArrayList<>();
        disciplinas.add(disciplinaObrigatoria);
        disciplinas.add(disciplinaOptativa);
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
    public void testMatricularDisciplinas() {
        alunoController.matricularDisciplinas(aluno, disciplinas);
        assertTrue(aluno.getDisciplinasObrigatorias().contains(disciplinaObrigatoria));
        assertTrue(aluno.getDisciplinasOptativas().contains(disciplinaOptativa));
    }

    @Test
    public void testCancelarMatricula() {
        alunoController.matricularDisciplinas(aluno, disciplinas);
        alunoController.cancelarMatricula(aluno, disciplinaObrigatoria);
        assertFalse(aluno.getDisciplinasMatriculadas().contains(disciplinaObrigatoria));
    }

    @Test
    public void testVisualizarDisciplinasMatriculadas() {
        alunoController.matricularDisciplinas(aluno, disciplinas);
        List<String> disciplinasMatriculadas = alunoController.visualizarDisciplinasMatriculadas(aluno);
        assertEquals(2, disciplinasMatriculadas.size());
        assertTrue(disciplinasMatriculadas.contains("Algoritmos"));
        assertTrue(disciplinasMatriculadas.contains("Filosofia"));
    }

    @Test
    public void testLimiteDeDisciplinas() {
        List<Disciplina> novasDisciplinas = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            novasDisciplinas.add(new Disciplina("Disciplina " + i, 2, null, TipoDisciplina.OBRIGATORIA));
        }

        alunoController.matricularDisciplinas(aluno, novasDisciplinas);
        assertTrue(aluno.getDisciplinasMatriculadas().size() <= 6);
    }
}

