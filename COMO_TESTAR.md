# Como Executar os Testes do Projeto Tetris

## 📋 Formas de Executar os Testes

### 1. Via Terminal (Maven)

#### Executar TODOS os testes:
```bash
mvn test
```

#### Executar um teste específico:
```bash
mvn test -Dtest=NomeDaClasseTest
```

Exemplos:
```bash
mvn test -Dtest=TabuleiroTest
mvn test -Dtest=SistemaPontuacaoTest
mvn test -Dtest=JogadorTest
```

#### Executar um método de teste específico:
```bash
mvn test -Dtest=NomeDaClasseTest#nomeDoMetodo
```

Exemplo:
```bash
mvn test -Dtest=TabuleiroTest#testAdicionarNovaPeca
```

#### Executar testes de um pacote específico:
```bash
mvn test -Dtest=tetris.domain.*Test
```

#### Ver relatório detalhado:
```bash
mvn test -X
```

#### Pular os testes durante compilação:
```bash
mvn compile -DskipTests
```

### 2. Via IDE (IntelliJ IDEA / Eclipse / VS Code)

#### IntelliJ IDEA:
1. Clique com botão direito na pasta `src/test/java`
2. Selecione "Run 'All Tests'"
3. Ou clique no ícone ▶️ ao lado de uma classe de teste
4. Ou use o atalho: `Ctrl+Shift+F10` (Windows/Linux) ou `Cmd+Shift+R` (Mac)

#### Eclipse:
1. Clique com botão direito na classe de teste
2. Selecione "Run As" → "JUnit Test"
3. Ou use o atalho: `Alt+Shift+X, T`

#### VS Code:
1. Instale a extensão "Java Test Runner"
2. Clique no ícone de teste ao lado do método/classe
3. Ou use o comando: `Ctrl+Shift+P` → "Java: Run Tests"

### 3. Ver Relatórios dos Testes

Após executar `mvn test`, os relatórios são gerados em:
```
target/surefire-reports/
```

Arquivos importantes:
- `TEST-*.xml` - Relatórios XML detalhados
- `*.txt` - Relatórios em texto

### 4. Testes Disponíveis

O projeto possui **61 testes** cobrindo:

- ✅ **PosicaoTest** (4 testes) - Movimentos e imutabilidade
- ✅ **SistemaPontuacaoTest** (9 testes) - Cálculo de pontos e níveis
- ✅ **TabuleiroTest** (15 testes) - Lógica do tabuleiro
- ✅ **TetrominoTest** (9 testes) - Rotação e movimentos das peças
- ✅ **PartidaTest** (9 testes) - Lógica da partida
- ✅ **JogadorTest** (5 testes) - Entidade jogador
- ✅ **TipoTetrominoTest** (10 testes) - Factory e enum

### 5. Exemplo de Saída Esperada

```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running tetris.domain.JogadorTest
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
[INFO] Running tetris.domain.PartidaTest
[INFO] Tests run: 9, Failures: 0, Errors: 0, Skipped: 0
...
[INFO] Results:
[INFO] Tests run: 61, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

### 6. Dicas

- ✅ Execute `mvn clean test` para limpar e testar do zero
- ✅ Use `mvn test -Dtest=*Test` para executar apenas classes que terminam com "Test"
- ✅ Para ver apenas falhas: `mvn test | grep -i "failure\|error"`
- ✅ Para testar durante desenvolvimento: execute testes individuais

### 7. Troubleshooting

**Problema**: Testes não compilam
```bash
mvn clean compile test-compile
```

**Problema**: Dependências não encontradas
```bash
mvn clean install
```

**Problema**: Ver logs detalhados
```bash
mvn test -X -e
```

