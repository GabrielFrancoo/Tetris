🧱 Estrutura Base do Projeto
src/
└── com/
    └── seuNomeOuProjeto/
        ├── engine/         ← núcleo do jogo
        │   └── GameEngine.java
        │
        ├── model/          ← regras e dados do jogo
        │   ├── Board.java
        │   ├── Tetromino.java
        │   └── (outras peças, ex: TetrominoL.java, TetrominoT.java)
        │
        ├── view/           ← parte visual (desenho, interface)
        │   └── GameView.java
        │
        ├── controller/     ← controle de entrada (teclado, eventos)
        │   └── GameController.java
        │
        └── Main.java       ← ponto de entrada do programa

🧩 Função de cada pacote

engine → controla o loop principal e os estados do jogo.

model → contém a lógica do jogo (tabuleiro, peças, colisões).

view → desenha o jogo na tela (usando Graphics, Canvas, JPanel, etc.).

controller → captura e trata as entradas do jogador (KeyListener, etc.).

Main.java → cria o GameEngine e inicia o jogo.

⚙️ Comandos Úteis (PowerShell)
📂 Mostrar as pastas e os caminhos
Get-ChildItem -Recurse -File

🧰 Compilar tudo
javac -d out (Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName })

▶️ Executar o jogo
java -cp out com.seuNomeOuProjeto.Main
