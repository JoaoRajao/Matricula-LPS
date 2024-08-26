package org.example.controller;

import org.example.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SecretariaControllerTest {

    private SecretariaController secretariaController;
    private Secretaria secretaria;
    private Curso curso;
    private Professor professor;
    private Professor professor1;
    private Disciplina disciplina;
    private Aluno aluno;

    @Before
    public void setUp() {
        secretariaController = new SecretariaController();
        secretaria = new Secretaria("admin", "senha123");
        curso = new Curso("Engenharia de Software", 240);
        professor = new Professor("Dr. Pedro", "P01", "pedro", "senha789");
        professor1 = new Professor("Dr. Pedro", "P01", "pedro", "senha789");
        disciplina = new Disciplina("Algoritmos", 4, null, TipoDisciplina.OBRIGATORIA);
        aluno = new Aluno("João", "20220001", curso, "joao", "senha123");
    }



    @Test
    public void testAdicionarCurso() {
        secretariaController.adicionarCurso(secretaria, curso);
        assertTrue(secretaria.getCursos().contains(curso));
    }

    @Test
    public void testAdicionarProfessor() {
        secretariaController.adicionarProfessor(secretaria, professor);
        assertTrue(secretaria.getProfessores().contains(professor));
    }

    @Test
    public void testAdicionarDisciplina() {
        secretariaController.adicionarDisciplina(secretaria, disciplina);
        assertTrue(secretaria.getDisciplinas().contains(disciplina));
    }

    @Test
    public void testAdicionarAluno() {
        secretariaController.adicionarAluno(secretaria, aluno);
        assertTrue(secretaria.getAlunos().contains(aluno));
    }

    @Test
    public void testListarCursos() {
        secretariaController.adicionarCurso(secretaria, curso);
        List<Curso> cursos = secretariaController.listarCursos(secretaria);
        assertEquals(1, cursos.size());
        assertEquals(curso, cursos.get(0));
    }

    @Test
    public void testListarProfessores() {
        secretariaController.adicionarProfessor(secretaria, professor);
        List<Professor> professores = secretariaController.listarProfessores(secretaria);
        assertEquals(1, professores.size());
        assertEquals(professor, professores.get(0));
    }

    @Test
    public void testListarDisciplinas() {
        secretariaController.adicionarDisciplina(secretaria, disciplina);
        List<Disciplina> disciplinas = secretariaController.listarDisciplinas(secretaria);
        assertEquals(1, disciplinas.size());
        assertEquals(disciplina, disciplinas.get(0));
    }

    @Test
    public void testListarAlunos() {
        secretariaController.adicionarAluno(secretaria, aluno);
        List<Aluno> alunos = secretariaController.listarAlunos(secretaria);
        assertEquals(1, alunos.size());
        assertEquals(aluno, alunos.get(0));
    }

    @Test
    public void testAdicionarDisciplinaAoProfessor() {

        secretariaController.adicionarDisciplinaAoProfessor(disciplina, professor1);
        assertTrue(professor1.getDisciplinas().contains(disciplina));
    }
}
