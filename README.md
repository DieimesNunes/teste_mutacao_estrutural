# Teste Estrutural e Teste de Mutação

Este repositório contém o projeto utilizado para a realização da Prática 01, envolvendo teste baseado em especificação, teste estrutural com MC/DC, cobertura com JaCoCo e teste de mutação com PIT.

## Arquivo principal da entrega

O relatório principal está no arquivo:

```text
a02848244.md
```

O relatório contém a descrição dos testes planejados, os resultados obtidos, a análise de cobertura, a análise de mutação e o conjunto final de testes implementados.

## Tecnologias utilizadas

- Java JDK
- Maven
- JUnit 5
- JaCoCo
- PIT Mutation Testing

## Estrutura do projeto

```text
src/
  main/
    java/
      BioClusterManager.java
  test/
    java/
      BioClusterManagerTest.java
pom.xml
a02848244.md
```

## Executar os testes

Para executar os testes automatizados:

```bash
mvn test
```

Resultado final obtido:

```text
Tests run: 12, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

## Gerar relatório de cobertura com JaCoCo

Para executar os testes e gerar o relatório de cobertura:

```bash
mvn clean test
```

O relatório é gerado em:

```text
target/site/jacoco/index.html
```

Resultado final obtido:

| Métrica | Resultado |
|---|---|
| Cobertura de linhas | 100% |
| Cobertura de branches | 95% |
| Cobertura de instruções | 100% |

## Executar teste de mutação com PIT

Para gerar o relatório de mutação:

```bash
mvn org.pitest:pitest-maven:mutationCoverage
```

O relatório é gerado em:

```text
target/pit-reports/index.html
```

Resultado obtido:

| Métrica | Resultado |
|---|---|
| Mutantes gerados | 25 |
| Mutantes mortos | 22 |
| Mutantes sobreviventes | 3 |
| Score de mutação | 88% |
| Test Strength | 88% |

## Observação sobre o caso T09

Durante os testes, o caso T09 revelou um possível defeito na implementação. A especificação sugere que a saúde dos espécimes envolvidos deve ser considerada, porém o código verifica duas vezes a saúde da primeira observação (`o1`) e não considera a saúde da segunda observação (`o2`).

Esse comportamento foi registrado no relatório.