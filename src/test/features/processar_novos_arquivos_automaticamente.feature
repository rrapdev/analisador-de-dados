# language: pt
Funcionalidade: Processar novos arquivos automaticamente

  Cenario: Processar arquivos de 10 em 10 segundos
    Dado que existe arquivo com o nome "vendas-10-2020.dat" na pasta "data/in"
    Quando passarem dez segundos
    Entao deveria gerar arquivo com nome "vendas-10-2020.done.dat" na pasta "data/out" atraves de agendador