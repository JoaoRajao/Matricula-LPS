package org.example.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtil {

    public static List<String> lerArquivo(String nomeArquivo) throws IOException {
        List<String> linhas = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo));
        String linha;

        while ((linha = reader.readLine()) != null) {
            linhas.add(linha);
        }

        reader.close();
        return linhas;
    }

    public static void escreverArquivo(String nomeArquivo, List<String> linhas) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo));

        for (String linha : linhas) {
            writer.write(linha);
            writer.newLine();
        }

        writer.close();
    }

    public static void adicionarLinha(String nomeArquivo, String linha) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true));
        writer.write(linha);
        writer.newLine();
        writer.close();
    }
}
