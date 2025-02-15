package app.entity;

import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotBlank(message = "O nome do curso é obrigatório.")
    @Column(nullable = false, unique = true)
    private String nome;

    public Curso() {}

    public Curso(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
