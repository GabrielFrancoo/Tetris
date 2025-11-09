# 🎮 Tetris - Projeto Acadêmico

Projeto de implementação do clássico jogo Tetris em Java, desenvolvido seguindo princípios de Domain-Driven Design (DDD) e arquitetura em camadas.

## 📋 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Requisitos](#requisitos)
- [Como Executar](#como-executar)
- [Como Testar](#como-testar)
- [Funcionalidades](#funcionalidades)
- [Controles](#controles)
- [Arquitetura](#arquitetura)

## 🎯 Sobre o Projeto

Este projeto implementa o jogo Tetris completo com:
- ✅ Lógica de jogo completa (movimentação, rotação, eliminação de linhas)
- ✅ Sistema de pontuação e níveis progressivos
- ✅ Interface gráfica com Swing
- ✅ Persistência em banco de dados SQL Server
- ✅ Salvamento local de partidas
- ✅ Sistema de replay
- ✅ Testes unitários abrangentes

## 📁 Estrutura do Projeto

```
src/
├── main/java/tetris/
│   ├── domain/          # Entidades do domínio (DDD)
│   │   ├── Jogador.java
│   │   ├── Partida.java
│   │   ├── Posicao.java
│   │   ├── SistemaPontuacao.java
│   │   ├── Tabuleiro.java
│   │   ├── Tetromino.java
│   │   └── [Subclasses de Tetromino]
│   ├── engine/          # Loop do jogo e controle
│   │   ├── GameEngine.java
│   │   ├── InputHandler.java
│   │   └── ThreadLoop.java
│   ├── io/              # Serialização e salvamento
│   │   ├── ReplayManager.java
│   │   └── SaveManager.java
│   ├── persistence/     # Banco de dados
│   │   ├── ConexaoSQL.java
│   │   ├── JogadorDAO.java
│   │   └── PartidaDAO.java
│   └── ui/              # Interface gráfica
│       ├── GamePanel.java
│       ├── Main.java
│       ├── ScorePanel.java
│       └── TelaPrincipal.java
└── test/java/tetris/
    └── domain/          # Testes unitários (61 testes)
        └── [Classes de teste]
```

## 🛠️ Tecnologias Utilizadas

- **Java 17** - Linguagem de programação
- **Maven** - Gerenciamento de dependências
- **Swing** - Interface gráfica
- **JUnit 4** - Testes unitários
- **SQL Server** - Banco de dados (opcional)
- **H2 Database** - Banco em memória (alternativa)

## 📦 Requisitos

- Java 17 ou superior
- Maven 3.6 ou superior
- SQL Server (opcional - para persistência completa)

## 🚀 Como Executar

### Pré-requisitos

1. Clone o repositório ou extraia os arquivos
2. Certifique-se de ter Java 17 e Maven instalados

### Executar o Jogo

```bash
# Navegue até o diretório do projeto
cd Tetris

# Compile o projeto
mvn clean compile

# Execute o jogo
mvn exec:java -Dexec.mainClass="tetris.ui.Main"
```

### Executar via IDE

1. Abra o projeto na sua IDE (IntelliJ IDEA, Eclipse, VS Code)
2. Localize a classe `tetris.ui.Main`
3. Execute o método `main`

## 🧪 Como Testar

### Executar Todos os Testes

```bash
mvn test
```

### Executar Teste Específico

```bash
# Exemplo: executar apenas TabuleiroTest
mvn test -Dtest=TabuleiroTest
```

### Executar Método Específico

```bash
# Exemplo: executar apenas um método
mvn test -Dtest=TabuleiroTest#testAdicionarNovaPeca
```

**Resultado esperado:**
```
Tests run: 61, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

Para mais detalhes, consulte o arquivo `COMO_TESTAR.md`.

## 🎮 Funcionalidades

### Jogo
- ✅ 7 tipos de peças (I, O, T, S, Z, J, L)
- ✅ Movimentação horizontal e vertical
- ✅ Rotação de peças
- ✅ Eliminação automática de linhas completas
- ✅ Sistema de pontuação progressivo
- ✅ Aumento de velocidade por nível
- ✅ Detecção de Game Over

### Persistência
- ✅ Salvamento local de partidas (pasta `saves/`)
- ✅ Persistência em banco de dados SQL Server
- ✅ Sistema de replay de partidas
- ✅ Carregamento de partidas salvas

### Interface
- ✅ Visualização do tabuleiro em tempo real
- ✅ Exibição de pontuação e nível
- ✅ Indicador de pausa
- ✅ Mensagem de Game Over

## 🎯 Controles

| Tecla | Ação |
|-------|------|
| **←** | Mover peça para esquerda |
| **→** | Mover peça para direita |
| **↓** | Mover peça para baixo (acelerar) |
| **↑** | Rotacionar peça |
| **Espaço** | Pausar/Despausar |

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas com Domain-Driven Design:

### 1. Domain (Domínio)
Contém as entidades principais do jogo:
- **Posicao** - Value Object representando coordenadas
- **Tetromino** - Entidade base para peças do jogo
- **Tabuleiro** - Agregado raiz que gerencia o estado do jogo
- **SistemaPontuacao** - Lógica de pontuação e níveis
- **Jogador** - Entidade representando o jogador
- **Partida** - Agregado raiz que orquestra o jogo

### 2. Engine (Motor)
Implementa o loop principal do jogo:
- **GameEngine** - Controla o ciclo de vida do jogo
- **ThreadLoop** - Gerencia threads para atualização do jogo
- **InputHandler** - Processa entrada do usuário

### 3. Persistence (Persistência)
Gerencia dados no banco SQL Server:
- **ConexaoSQL** - Gerenciamento de conexão
- **JogadorDAO** - Repositório para Jogador
- **PartidaDAO** - Repositório para Partida

### 4. IO (Entrada/Saída)
Serialização e salvamento:
- **SaveManager** - Salvamento local de partidas
- **ReplayManager** - Sistema de replay

### 5. UI (Interface)
Interface gráfica com Swing:
- **TelaPrincipal** - Janela principal
- **GamePanel** - Painel de renderização do jogo
- **ScorePanel** - Painel de pontuação

### 6. Test (Testes)
61 testes unitários validando todas as regras de negócio.

## 📊 Cobertura de Testes

O projeto possui **61 testes unitários** cobrindo:

- ✅ Movimentos e imutabilidade (Posicao)
- ✅ Cálculo de pontos e níveis (SistemaPontuacao)
- ✅ Lógica do tabuleiro (Tabuleiro)
- ✅ Rotação e movimentos das peças (Tetromino)
- ✅ Lógica da partida (Partida)
- ✅ Entidade jogador (Jogador)
- ✅ Factory e enum (TipoTetromino)

## 🗄️ Banco de Dados (Opcional)

O projeto suporta persistência em SQL Server:

### Configuração

1. Crie o banco de dados:
   ```sql
   CREATE DATABASE TetrisDB;
   ```

2. As tabelas são criadas automaticamente na primeira execução

3. Configurações em `ConexaoSQL.java`:
   - Servidor: `localhost:1433`
   - Banco: `TetrisDB`
   - Usuário: `sa`
   - Senha: `senha123`

**Nota:** O jogo funciona normalmente mesmo sem banco de dados (modo offline).

## 📝 Sistema de Pontuação

- **1 linha:** 40 × nível
- **2 linhas:** 100 × nível
- **3 linhas:** 300 × nível
- **4 linhas (Tetris):** 1200 × nível

O nível aumenta a cada 500 pontos.

## 🎨 Melhorias Futuras

- [ ] Preview da próxima peça
- [ ] Sistema de ranking online
- [ ] Diferentes modos de jogo
- [ ] Efeitos visuais e sonoros
- [ ] Multiplayer online

## 👨‍💻 Desenvolvimento

### Compilar

```bash
mvn clean compile
```

### Executar Testes

```bash
mvn test
```

### Gerar JAR

```bash
mvn package
```

## 📄 Licença

Este é um projeto acadêmico desenvolvido para fins educacionais.

## 🙏 Agradecimentos

Projeto desenvolvido seguindo princípios de:
- Domain-Driven Design (DDD)
- Arquitetura em Camadas
- Clean Code
- Test-Driven Development (TDD)

---

**Desenvolvido com ❤️ usando Java e Swing**

