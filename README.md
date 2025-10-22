// ...existing code...
ESTRUTURA BASE DO PROJETO 

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

🧩 Função de cada pacote:

engine → controla o loop principal e estados do jogo.

model → contém a lógica do jogo (tabuleiro, peças, colisões).

view → desenha o jogo na tela (usando Graphics, Canvas, JPanel, etc.).


controller → captura e trata as entradas do jogador (KeyListener, etc.).

Main.java → cria o GameEngine e inicia o jogo.

<Para compilar>

<Mostra as pastas e os caminhos>
Get-ChildItem -Recurse -File

<Compila tudo>
javac -d out (Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName })


// ...existing code...

Board representa a estrutura do tabuleiro