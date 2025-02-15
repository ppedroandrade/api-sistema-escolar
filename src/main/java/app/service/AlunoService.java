package app.service;

import app.entity.Aluno;
import app.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Transactional
    public String save(Aluno aluno) {
        String validacao = validarAluno(aluno);
        if (!validacao.equals("OK")) {
            return validacao; // Retorna a mensagem de erro caso a validação falhe
        }

        try {
            alunoRepository.save(aluno);
            return "Aluno salvo com sucesso!";
        } catch (DataIntegrityViolationException e) {
            return "Erro: CPF já cadastrado.";
        } catch (Exception e) {
            return "Erro inesperado ao salvar o aluno.";
        }
    }

    @Transactional
    public String delete(long id) {
        Optional<Aluno> alunoOpt = alunoRepository.findById(id);
        if (alunoOpt.isPresent()) {
            alunoRepository.deleteById(id);
            return "Aluno deletado com sucesso!";
        } else {
            return "Erro: Aluno não encontrado.";
        }
    }

    public Aluno findById(long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro: Aluno não encontrado."));
    }

    @Transactional
    public String update(Long id, Aluno aluno) {
        Aluno alunoExistente = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro: Aluno com ID " + id + " não encontrado."));

        // Se o CPF já existir em outro aluno, impedir a atualização
        if (!alunoExistente.getCpf().equals(aluno.getCpf()) && alunoRepository.existsByCpf(aluno.getCpf())) {
            throw new RuntimeException("Erro: Já existe um aluno cadastrado com este CPF.");
        }

        // Atualizando os dados do aluno
        alunoExistente.setNome(aluno.getNome());
        alunoExistente.setTelefone(aluno.getTelefone());
        alunoExistente.setCpf(aluno.getCpf());
        alunoExistente.setTurma(aluno.getTurma());

        alunoRepository.save(alunoExistente);
        return "Aluno atualizado com sucesso!";
    }

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    private String validarAluno(Aluno aluno) {
        if (aluno.getNome() == null || aluno.getNome().trim().isEmpty()) {
            return "Erro: O nome do aluno é obrigatório.";
        }

        if (aluno.getCpf() == null || aluno.getCpf().trim().length() != 11) {
            return "Erro: O CPF deve conter exatamente 11 dígitos.";
        }

        return "OK"; // Retorna "OK" se não houver erros
    }
}
