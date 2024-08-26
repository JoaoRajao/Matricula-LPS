package org.example.dao;

import org.example.model.Aluno;
import org.example.model.Curso;
import org.example.model.Disciplina;
import org.example.model.TipoDisciplina;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private static final String FILE_NAME = "alunos.txt";

    public void salvarAluno(Aluno aluno) {
        try {
            ArquivoUtil.adicionarLinha(FILE_NAME, aluno.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Aluno> carregarAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        try {
            List<String> linhas = ArquivoUtil.lerArquivo(FILE_NAME);
            for (String linha : linhas) {
                if (linha.trim().isEmpty()) {
                    continue;
                }

                String[] dados = linha.split(";");
                if (dados.length < 5) {
                    System.out.println("Linha com formato incorreto: " + linha);
                    continue;
                }

                String nome = dados[0];
                String matricula = dados[1];
                String nomeCurso = dados[2];
                String login = dados[3];
                String senha = dados[4];


                Curso curso = new Curso(nomeCurso, 240);
                Aluno aluno = new Aluno(nome, matricula, curso, login, senha);


                if (dados.length > 5) {
                    String[] disciplinasDados = dados[5].split(",");
                    for (String discData : disciplinasDados) {
                        String[] parts = discData.split("\\(");
                        String nomeDisciplina = parts[0].trim();
                        String tipoStr = parts[1].substring(0, 1); // Obtém apenas 'O' ou 'P'

                        TipoDisciplina tipo = tipoStr.equals("O") ? TipoDisciplina.OBRIGATORIA : TipoDisciplina.OPTATIVA;
                        Disciplina disciplina = new Disciplina(nomeDisciplina, 0, null, tipo);

                        if (tipo == TipoDisciplina.OBRIGATORIA) {
                            aluno.getDisciplinasObrigatorias().add(disciplina);
                        } else {
                            aluno.getDisciplinasOptativas().add(disciplina);
                        }
                    }
                }

                alunos.add(aluno);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return alunos;
    }
}
