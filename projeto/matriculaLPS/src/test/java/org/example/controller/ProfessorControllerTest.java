package org.example.controller;

import org.example.dao.ArquivoUtil;
import org.example.model.Aluno;
import org.example.model.Curso;
import org.example.model.Disciplina;
import org.example.model.Professor;
import org.example.model.TipoDisciplina;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.*;

public class ProfessorControllerTest {

    private ProfessorController professorController;
    private DisciplinaController disciplinaController;
    private Professor professor1;
    private Disciplina disciplinaMatematica;
    private Disciplina disciplinaFisica;
    private Aluno aluno1;
    private Aluno aluno2;

    @Before
    public void setUp() {
        professorController = new ProfessorController();
        disciplinaController = new DisciplinaController();
        professor1 = new Professor("Dr. Pedro", "1", "pedro", "senha789");


        disciplinaMatematica = new Disciplina("Matemática", 4, professor1, TipoDisciplina.OBRIGATORIA);
        disciplinaFisica = new Disciplina("Física", 4, professor1, TipoDisciplina.OBRIGATORIA);


        professor1.getDisciplinas().add(disciplinaMatematica);
        professor1.getDisciplinas().add(disciplinaFisica);


        Curso curso = new Curso("Engenharia", 240);
        aluno1 = new Aluno("Ana", "20220001", curso, "ana", "senha123");
        aluno2 = new Aluno("Carlos", "20220002", curso, "carlos", "senha123");


        disciplinaController.adicionarAlunoADisciplina(disciplinaMatematica, aluno1);
        disciplinaController.adicionarAlunoADisciplina(disciplinaFisica, aluno2);


        List<String> linhasIniciais = new ArrayList<>();
        linhasIniciais.add(professor1.getNome() + ";" + professor1.getId() + ";" + professor1.getLogin() + ";" + professor1.getSenha());

        try {
            ArquivoUtil.escreverArquivo("professores.txt", linhasIniciais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAdicionarProfessor() {
        professorController.adicionarProfessor(professor1);
        List<Professor> professores = professorController.carregarProfessores();

        boolean professorEncontrado = false;
        for (Professor p : professores) {
            if (p.getNome().equals(professor1.getNome()) &&
                    p.getId().equals(professor1.getId()) &&
                    p.getLogin().equals(professor1.getLogin()) &&
                    p.getSenha().equals(professor1.getSenha())) {
                professorEncontrado = true;
                break;
            }
        }

        assertTrue(professorEncontrado);
    }

    @Test
    public void testVisualizarDisciplinasLecionadas() {
        List<String> disciplinas = professorController.visualizarDisciplinasLecionadas(professor1);

        assertEquals(2, disciplinas.size());
        assertTrue(disciplinas.contains("Matemática"));
        assertTrue(disciplinas.contains("Física"));
    }

    @Test
    public void testVisualizarAlunosPorDisciplina() {
        Map<String, List<Aluno>> alunosPorDisciplina = professorController.visualizarAlunosPorDisciplina(professor1);


        assertTrue(alunosPorDisciplina.containsKey("Matemática"));
        assertTrue(alunosPorDisciplina.containsKey("Física"));


        List<Aluno> alunosMatematica = alunosPorDisciplina.get("Matemática");
        assertEquals(1, alunosMatematica.size());
        assertEquals("Ana", alunosMatematica.get(0).getNome());

        List<Aluno> alunosFisica = alunosPorDisciplina.get("Física");
        assertEquals(1, alunosFisica.size());
        assertEquals("Carlos", alunosFisica.get(0).getNome());
    }
}
