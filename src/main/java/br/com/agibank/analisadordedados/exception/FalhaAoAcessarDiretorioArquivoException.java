package br.com.agibank.analisadordedados.exception;

public class FalhaAoAcessarDiretorioArquivoException extends RuntimeException {

    public FalhaAoAcessarDiretorioArquivoException(String mensagem) { super(mensagem); }

    public static final String MENSAGEM_FALHA_AO_LER_DIRETORIO = "Não foi possível listar os arquivos do diretório." +
            " Verifique se o mesmo existe!";

    public static final String MENSAGEM_FALHA_AO_CRIAR_ARQUIVO = "Não foi possível criar arquivo.";

    public static final String MENSAGEM_FALHA_AO_LER_ARQUIVO = "Não foi possível ler o arquivo.";
    public static final String MENSAGEM_FALHA_AO_APAGAR_ARQUIVO = "Não foi possível apagar o arquivo processado.";
}
