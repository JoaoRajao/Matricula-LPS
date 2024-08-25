package org.example.dao;

import org.example.model.Secretaria;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SecretariaDAO {

    private static final String FILE_NAME = "secretarias.txt";

    public void salvarSecretaria(Secretaria secretaria) {
        try {
            ArquivoUtil.adicionarLinha(FILE_NAME, secretaria.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Secretaria> carregarSecretarias() {
        List<Secretaria> secretarias = new ArrayList<>();
        try {
            List<String> linhas = ArquivoUtil.lerArquivo(FILE_NAME);
            for (String linha : linhas) {
                String[] dados = linha.split(";");
                Secretaria secretaria = new Secretaria(dados[0], dados[1]);
                secretarias.add(secretaria);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return secretarias;
    }

    public void atualizarArquivo(List<Secretaria> secretarias) {
        List<String> linhas = new ArrayList<>();
        for (Secretaria secretaria : secretarias) {
            linhas.add(secretaria.toString());
        }
        try {
            ArquivoUtil.escreverArquivo(FILE_NAME, linhas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
