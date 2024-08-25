package org.example.controller;

import org.example.dao.ArquivoUtil;
import org.example.model.Disciplina;
import org.example.model.Professor;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ProfessorControllerTest {

    private ProfessorController professorController;
    private Professor professor1;
    private Professor professor2;
    private Disciplina disciplina;

    @Before
    public void setUp() {
        professorController = new ProfessorController();
        professor1 = new Professor("Dr. Pedro", "1", "pedro", "senha789");
        professor2 = new Professor("Dra. Ana", "2", "ana", "senha456");
        disciplina= new Disciplina("Estrutura de Dados",3,professor1);

        List<String> linhasIniciais = new ArrayList<>();
        linhasIniciais.add(professor1.getNome() + ";" + professor1.getId() + ";" + professor1.getLogin() + ";" + professor1.getSenha());
        linhasIniciais.add(professor2.getNome() + ";" + professor2.getId() + ";" + professor2.getLogin() + ";" + professor2.getSenha());

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
        assertTrue(professores.contains(professor1));
    }

    @Test
    public void testAdicionarDisciplinaAoProfessor() {

        professorController.adicionarDisciplinaAoProfessor(disciplina, professor1);
        assertTrue(professor1.getDisciplinas().contains(disciplina));
    }

    @Test
    public void testVisualizarDisciplinasLecionadas() {

        professorController.adicionarDisciplinaAoProfessor(disciplina, professor1);

        List<String> disciplinas = professorController.visualizarDisciplinasLecionadas(professor1);

        assertEquals(1, disciplinas.size());
        assertEquals("Estrutura de Dados", disciplinas.get(0));
    }
}
