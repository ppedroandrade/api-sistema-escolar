package app.service;

import app.entity.Professor;
import app.repository.ProfessorRepository;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    /**
     * Lista todos os professores cadastrados.
     */
    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    /**
     * Busca um professor pelo ID.
     */
    public Professor findById(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro: Professor com ID " + id + " não encontrado."));
    }

    /**
     * Cadastra um novo professor.
     */
    @Transactional
    public String save(Professor professor) {
        validarProfessor(professor);
        professorRepository.save(professor);
        return "Professor salvo com sucesso!";
    }

    /**
     * Atualiza um professor existente.
     */
    @Transactional
    public String update(Long id, Professor professor) {
        Optional<Professor> professorExistente = professorRepository.findById(id);

        if (professorExistente.isPresent()) {
            Professor profAtualizado = professorExistente.get();

            validarProfessor(professor);

            profAtualizado.setNome(professor.getNome());
            profAtualizado.setCpf(professor.getCpf());
            profAtualizado.setEmail(professor.getEmail());
            profAtualizado.setEspecialidade(professor.getEspecialidade());

            professorRepository.save(profAtualizado);
            return "Professor atualizado com sucesso!";
        } else {
            return "Erro: Professor com ID " + id + " não encontrado.";
        }
    }

    /**
     * Exclui um professor pelo ID.
     */
    @Transactional
    public String delete(Long id) {
        if (!professorRepository.existsById(id)) {
            return "Erro: Professor com ID " + id + " não encontrado.";
        }
        professorRepository.deleteById(id);
        return "Professor deletado com sucesso!";
    }

    /**
     * Valida as regras de negócio do professor.
     */
    private void validarProfessor(Professor professor) {
        if (professor.getNome() == null || professor.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Erro: O nome do professor é obrigatório.");
        }

        if (professor.getCpf() == null || professor.getCpf().trim().length() != 11) {
            throw new IllegalArgumentException("Erro: CPF inválido. Deve conter exatamente 11 dígitos.");
        }

        if (professor.getId() == null && professorRepository.existsByCpf(professor.getCpf())) {
            throw new IllegalArgumentException("Erro: Já existe um professor cadastrado com este CPF.");
        }

        if (professor.getEmail() == null || professor.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Erro: O email do professor é obrigatório.");
        }

        if (professor.getId() == null && professorRepository.existsByEmail(professor.getEmail())) {
            throw new IllegalArgumentException("Erro: Já existe um professor cadastrado com este email.");
        }
    }
}
