[![rrapdev](https://circleci.com/gh/rrapdev/analisador-de-dados.svg?style=shield)](https://github.com/rrapdev/analisador-de-dados) [![codecov](https://codecov.io/gh/rrapdev/analisador-de-dados/branch/main/graph/badge.svg?token=NT4GgfHsFN)](https://codecov.io/gh/rrapdev/analisador-de-dados)

# Analisador de Dados
Este sistema foi desenvolvido como parte de uma avaliação técnica de Programação Orienta a Objetos com Java, SpringBoot e Testes Automáticos.

#### Alterações importantes na versão 0.0.2-SNAPSHOT:

 * Cobertura de testes aumentada, sendo 100% segundo o coverage e 97% segundo o codecov;

 * Adoção de princípios SOLID e refatoramento nas classes dos projetos para melhorar código limpo e reduzir 
 responsabilidades das classes, aumentando
  o desacoplamento e a coesão, assim como facilitando a criação de novos testes automáticos;
  
 * O projeto foi refatorado para utilizar interfaces em vez de implementações, melhorando a componentização e flexibilidade
 da aplicação;
 
 * A versão do Java foi atualizada para 11 e importantes recursos de manipulação de arquivos e strings foram utilizados, como 
 `Files.readString()`, `Files.createFile()` e `Files.writeString()`. Além de programação funcional com lambda.
 
 * SpringBoot foi utilizado para injeção de dependência das classes, executar o monitoramento de tempos em tempos, 
 assim como para carregar o contexto da aplicação e para facilitar a criação e execução dos testes.
 
 * Removida API Rest que estimulava processamento de arquivos. O Processamento de arquivos de vendas agora roda em 
  background através do uso das anotações do Spring @EnableScheduling e @Scheduled, melhorando a performance.
  
 * Para representar a lógica de negócio, foi adicionada chamada de validação dos arquivos de vendas na classe de serviço ```ProcessadorArquivosImpl.class```. No
 entanto a implementação da validação foi incluída na classe ```ArquivoVenda.class```.

 * O projeto foi integrado ao docker a partir do Jib. Há instruções de como criar um container e rodá-lo no final desta página.
 
 
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

Um novo arquivo (com terminação *.done.dat*), com os dados do resumo, é gerado na seguinte pasta:

```
   src/main/resources/data/out/{nome_do_arquivo}.done.dat
```


 #### Integração com Docker:
 
Para rodar a sua aplicação a partir de um container docker, certifique-se de que sua máquina possui docker e 
docker-compose instalados. Depois crie uma imagem docker da aplicação executando o comando a partir da raiz do projeto:
 
```
./mvnw verify jib:dockerBuild
```
 Por fim, rode seu container utilizando o comando:
 
 ```
docker-compose -f src/main/docker/app.yml up -d
 ```
 
 
#### Principais tecnologias utilizadas:

 * Java 8;
 * Spring Boot 2.3.5;
 * Maven;
 * Lombok;
 * Docker com Jit;
 * Testes (Cucumber, JUnit, Mockito);
 * Integração Contínua com CircleCI;
 * Cobertura de Código com Codecov.
 
 
 #### Testes:
 
Foram criadas suites de testes para agrupar os projetos e facilitar sua execução:
 
  * AllTests: Agrupamento de todos os testes do sistema, incluindo testes unitários e de integração / aceitação;
  * CucumberTest: Agrupamento dos testes de aceitação baseados em BDD.
  * UnitTests: Agrupamento de todos os testes de unidade do projeto.
  * ApplicationTest: Agrupa testes para verificação do contexto do spring e sua execução.
  
  
Status do projeto quanto aos testes e cobertura de código:

[![rrapdev](https://circleci.com/gh/rrapdev/analisador-de-dados.svg?style=shield)](https://github.com/rrapdev/analisador-de-dados)
 [![codecov](https://codecov.io/gh/rrapdev/analisador-de-dados/branch/main/graph/badge.svg?token=NT4GgfHsFN)](https://codecov.io/gh/rrapdev/analisador-de-dados)
