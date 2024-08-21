package org.example;





import org.example.dao.AlunoArquivo;
import org.example.dao.CursoArquivo;
import org.example.dao.DisciplinaArquivo;
import org.example.dao.ProfessorArquivo;
import org.example.model.Aluno;
import org.example.model.Curso;
import org.example.model.Disciplina;
import org.example.model.Professor;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Carregar os dados do sistema
            List<Aluno> alunos = AlunoArquivo.lerAlunos();
            List<Professor> professores = ProfessorArquivo.lerProfessores();
            List<Curso> cursos = CursoArquivo.lerCursos();
            List<Disciplina> disciplinas = DisciplinaArquivo.lerDisciplinas(professores);

            // Simulação do fluxo de matrícula
            Aluno aluno = alunos.get(0); // Pegando o primeiro aluno para exemplo
            Disciplina disciplina = disciplinas.get(0); // Pegando a primeira disciplina para exemplo

            aluno.matricularDisciplina(disciplina);

            // Salvar as mudanças nos arquivos
            AlunoArquivo.salvarAlunos(alunos);
            DisciplinaArquivo.salvarDisciplinas(disciplinas);

            System.out.println("Matrícula realizada com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao ler ou escrever os arquivos: " + e.getMessage());
        }
    }
}
