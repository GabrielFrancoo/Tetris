package tetris.domain;

import java.util.UUID;

public class Jogador {
    private UUID id;
    private String nome;

    public Jogador(String nome) {
        this.id = UUID.randomUUID();
        this.nome = nome;
    }

    // Construtor para carregar do banco de dados
    public Jogador(UUID id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

