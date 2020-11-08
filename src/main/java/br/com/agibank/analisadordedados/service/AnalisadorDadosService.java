package br.com.agibank.analisadordedados.service;

import br.com.agibank.analisadordedados.domain.Arquivo;
import br.com.agibank.analisadordedados.domain.Resumo;

public interface AnalisadorDadosService {

    Resumo analisarConteudo(Arquivo arquivo);

}
