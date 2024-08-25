package org.example.model;
import java.util.ArrayList;
import java.util.List;

public class Secretaria {
    private List<Curso> cursos;
    private List<Disciplina> disciplinas;
    private List<Professor> professores;
    private List<Aluno> alunos;

    public Secretaria() {
        this.cursos = new ArrayList<>();
        this.disciplinas = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.alunos = new ArrayList<>();
    }

    
     public List<Curso> getCursos() {
        return cursos;
    }

    public void adicionarCurso(Curso curso) {
        if (!cursos.contains(curso)) {
            cursos.add(curso);
            System.out.println("Curso adicionado: " + curso.getNome());
        } else {
            System.out.println("O curso já existe: " + curso.getNome());
        }
    }

    public void removerCurso(Curso curso) {
        if (cursos.remove(curso)) {
            System.out.println("Curso removido: " + curso.getNome());
        } else {
            System.out.println("O curso não foi encontrado: " + curso.getNome());
        }
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        if (!disciplinas.contains(disciplina)) {
            disciplinas.add(disciplina);
            System.out.println("Disciplina adicionada: " + disciplina.getNome());
        } else {
            System.out.println("A disciplina já existe: " + disciplina.getNome());
        }
    }

    public void removerDisciplina(Disciplina disciplina) {
        if (disciplinas.remove(disciplina)) {
            System.out.println("Disciplina removida: " + disciplina.getNome());
        } else {
            System.out.println("A disciplina não foi encontrada: " + disciplina.getNome());
        }
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void adicionarProfessor(Professor professor) {
        if (!professores.contains(professor)) {
            professores.add(professor);
            System.out.println("Professor adicionado: " + professor.getNome());
        } else {
            System.out.println("O professor já existe: " + professor.getNome());
        }
    }

    public void removerProfessor(Professor professor) {
        if (professores.remove(professor)) {
            System.out.println("Professor removido: " + professor.getNome());
        } else {
            System.out.println("O professor não foi encontrado: " + professor.getNome());
        }
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void adicionarAluno(Aluno aluno) {
        if (!alunos.contains(aluno)) {
            alunos.add(aluno);
            System.out.println("Aluno adicionado: " + aluno.getNome());
        } else {
            System.out.println("O aluno já existe: " + aluno.getNome());
        }
    }

    public void removerAluno(Aluno aluno) {
        if (alunos.remove(aluno)) {
            System.out.println("Aluno removido: " + aluno.getNome());
        } else {
            System.out.println("O aluno não foi encontrado: " + aluno.getNome());
        }
    }
}
