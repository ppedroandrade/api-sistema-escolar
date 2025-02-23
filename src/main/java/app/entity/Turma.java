package app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

@Entity
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String semestre;

    @Column(nullable = false)
    private String turno;

    @Column(nullable = false)
    private int ano;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Aluno> alunos;

    @ManyToMany
    @JoinTable(
            name = "turma_professor",
            joinColumns = @JoinColumn(name = "turma_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private List<Professor> professores;

    // Construtor vazio obrigatório para JPA
    public Turma() {
    }

    // Construtor completo
    public Turma(String nome, String semestre, String turno, int ano, Curso curso) {
        this.nome = nome;
        this.semestre = semestre;
        this.turno = turno;
        this.ano = ano;
        this.curso = curso;
    }

}
