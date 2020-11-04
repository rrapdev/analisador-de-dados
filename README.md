[![rrapdev](https://circleci.com/gh/rrapdev/analisador-de-dados.svg?style=shield)](https://codecov.io/gh/rrapdev/analisador-de-dados) [![codecov](https://codecov.io/gh/rrapdev/analisador-de-dados/branch/main/graph/badge.svg?token=NT4GgfHsFN)](https://codecov.io/gh/rrapdev/analisador-de-dados)

# Analisador de Dados
Este sistema foi desenvolvido como parte de uma avaliação técnica para o Agibank, a partir da DBC Company.

#### Funcionamento:
Sistema para importação de lotes de arquivos de vendas. Os arquivos são processados, analisados e um relatório é
produzido com os dados consolidados.

O sistema monitora o diretório abaixo e processa arquivos de vendas (arquivos com 
terminação *.dat*):

```
   src/main/resources/data/in/{nome_do_arquivo}.dat
```

Após análise dos dados, um resumo é gerado com as seguintes informações:

```
   Clientes: {quantidade de clientes};
   Vendedores: {quantidade de vendedores};
   ID da Venda mais cara: {id venda mais cara};
   O pior vendedor: {nome da pessoa que menos vendeu};
```

Um novo arquivo, com os dados do resumo, é gerado na seguinte pasta:

```
   src/main/resources/data/out/{nome_do_arquivo}.dat
```

#### Agendamento (Scheduling)
Foi adicionado, através do Spring Framework, um agendador que de tempos em tempos verifica arquivos de vendas na pasta de 
entrada.

#### API Rest:
Apesar do sistema ficar monitorando a pasta de entrada em busca de arquivos de vendas, também foi criada uma API para
estimular o processamento de arquivos de vendas de forma reativa. Para testá-la, após executar o projeto, acesse a URL:

```
   http://localhost:8181/swagger-ui.html
```

#### Principais tecnologias utilizadas:

 * Java 8;
 * Maven;
 * Spring Boot 2.3.5;
 * Testes (Cucumber, JUnit, Mockito);
 * Documentação da API com Swagger;
 * Integração Contínua com CircleCI;
 * Cobertura de Código com Codecov.
 
 #### Testes:
 
Foram criadas suites de testes para agrupar os projetos e facilitar sua execução:
 
  * AllTests: Agrupamento de todos os testes do sistema, incluindo testes unitários e de integração / aceitação;
  * CucumberTest: Agrupamento dos testes de aceitação baseados em BDD.
  * UnitTests: Agrupamento de todos os testes de unidade do projeto.
  
  *Os testes automatizados do sistema corresponderam a mais de 90% de cobertura de código, 
  segundo o coverage.*