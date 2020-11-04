# language: pt
Funcionalidade: Processar novos arquivos de venda

  Cenario: Gerar arquivo na pasta de saida
    Dado que existe arquivo com o nome "vendas-10-2020.dat" na pasta "data/in"
    Quando processar novos arquivos de venda
    Entao deveria gerar arquivo com nome "vendas-10-2020.done.dat" na pasta "data/out"

  Cenario: Arquivo na pasta de entrada no formato inválido
    Dado que existe arquivo com o nome "vendas-invalido.csv" na pasta "data/in"
    Quando processar novos arquivos de venda
    Entao deveria nao gerar nenhum arquivo na pasta "data/out"

  Cenario: Analisar dados de arquivo gerando arquivo consolidado com dados de resumo
    Dado que existe um arquivo, a ser processado, com os seguintes dados
      | Dados                                       |
      | 001ç1234567891234çPedroç50000               |
      | 001ç3245678865434çPauloç40000.99            |
      | 002ç2345675434544345çJose da SilvaçRural    |
      | 002ç2345675433444345çEduardo PereiraçRural  |
      | 003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro |
      | 003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo  |
    Quando processar novos arquivos de venda
    Entao deveria gerar arquivo contendo o seguinte resumo
      | Quantidade Clientes | Quantidade Vendedores | Id Venda Mais Cara | Nome Pior Vendedor |
      | 2                   | 2                     | 10                 | Paulo              |