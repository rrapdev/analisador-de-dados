package br.com.agibank.analisadordedados.domain;

import java.util.HashMap;
import java.util.Map;

public abstract class TipoDado {
    public static TipoDado descobrir(String[] dados) {
        Map<String, TipoDado> tiposDisponiveis = new HashMap<>();
        tiposDisponiveis.put(TIPO_VENDEDOR_001, new Vendedor());
        tiposDisponiveis.put(TIPO_CLIENTE_002, new Cliente());
        tiposDisponiveis.put(TIPO_VENDA_003, new Venda());

        TipoDado entidade = tiposDisponiveis.get(dados[INDICE_IDENTIFICADOR_TIPO]);
        entidade.importarDados(dados);

        return entidade;
    }

    public abstract void importarDados(String[] dados);
    public abstract RelatorioResumoVendas adicionarAoRelatorio(RelatorioResumoVendas relatorio);

    public static final String TIPO_VENDEDOR_001 = "001";
    public static final String TIPO_CLIENTE_002 = "002";
    public static final String TIPO_VENDA_003 = "003";
    public static final Integer INDICE_IDENTIFICADOR_TIPO = 0;
}
