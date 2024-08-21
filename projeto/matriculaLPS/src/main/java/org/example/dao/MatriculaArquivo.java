package org.example.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MatriculaArquivo {

    private static final String ARQUIVO_MATRICULA = "matriculas.txt";

    public static List<String> lerMatriculas() throws IOException {
        return ArquivoUtil.lerArquivo(ARQUIVO_MATRICULA);
    }

    public static void salvarMatricula(String matriculaInfo) throws IOException {
        ArquivoUtil.adicionarLinha(ARQUIVO_MATRICULA, matriculaInfo);
    }
}
