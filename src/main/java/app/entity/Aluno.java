package app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = false) // Chave estrangeira para Turma
    @JsonBackReference
    private Turma turma;

    public Aluno() {}

    public Aluno(String nome, String cpf, String telefone, Turma turma) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.turma = turma;
    }
}

