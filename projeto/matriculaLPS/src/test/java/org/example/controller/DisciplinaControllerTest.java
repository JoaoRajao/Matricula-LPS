package org.example.controller;

import org.example.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DisciplinaControllerTest {

    private DisciplinaController disciplinaController;
    private Disciplina disciplina;
    private Aluno aluno;
    private Professor professor;

    @Before
    public void setUp() {
        disciplinaController = new DisciplinaController();
        professor = new Professor("Dr. Pedro", "P01", "pedro", "senha789");
        disciplina = new Disciplina("Algoritmos", 4, null, TipoDisciplina.OBRIGATORIA);
        aluno = new Aluno("João", "20220001", new Curso("Engenharia de Software", 240), "joao", "senha123");
    }

    @Test
    public void testAdicionarAlunoADisciplina() {
        disciplinaController.adicionarAlunoADisciplina(disciplina, aluno);
        assertTrue(disciplina.getAlunos().contains(aluno));
    }

    @Test
    public void testRemoverAlunoDaDisciplina() {
        disciplinaController.adicionarAlunoADisciplina(disciplina, aluno);
        disciplinaController.removerAlunoDaDisciplina(disciplina, aluno);
        assertFalse(disciplina.getAlunos().contains(aluno));
    }

    @Test
    public void testVerificarSeDisciplinaEstaAtiva() {
        assertFalse(disciplinaController.verificarSeDisciplinaEstaAtiva(disciplina));
        disciplinaController.adicionarAlunoADisciplina(disciplina, aluno);
        assertFalse(disciplinaController.verificarSeDisciplinaEstaAtiva(disciplina)); // Menos de 3 alunos
    }

    @Test
    public void testAlterarProfessorDaDisciplina() {
        Professor novoProfessor = new Professor("Dra. Ana", "P02", "ana", "senha456");
        disciplinaController.alterarProfessorDaDisciplina(disciplina, novoProfessor);
        assertEquals(novoProfessor, disciplina.getProfessor());
    }

    @Test
    public void testListarAlunosDaDisciplina() {
        disciplinaController.adicionarAlunoADisciplina(disciplina, aluno);
        List<Aluno> alunos = disciplinaController.listarAlunosDaDisciplina(disciplina);
        assertEquals(1, alunos.size());
        assertEquals(aluno, alunos.get(0));
    }
}
