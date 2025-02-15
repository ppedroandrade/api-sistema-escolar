package app.service;

import app.entity.Turma;
import app.repository.TurmaRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    /**
     * Retorna todas as turmas cadastradas.
     */
    public List<Turma> listarTodas() {
        return turmaRepository.findAll();
    }

    /**
     * Retorna uma turma pelo ID.
     */
    public Turma buscarPorId(Long id) {
        return turmaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro: Turma com ID " + id + " não encontrada."));
    }

    /**
     * Cadastra uma nova turma.
     */
    @Transactional
    public Turma salvar(Turma turma) {
        return turmaRepository.save(turma);
    }

    /**
     * Atualiza uma turma existente.
     */
    @Transactional
    public Turma atualizar(Long id, Turma turmaAtualizada) {
        Turma turmaExistente = buscarPorId(id);

        turmaExistente.setNome(turmaAtualizada.getNome());
        turmaExistente.setSemestre(turmaAtualizada.getSemestre());
        turmaExistente.setTurno(turmaAtualizada.getTurno());
        turmaExistente.setAno(turmaAtualizada.getAno());
        turmaExistente.setCurso(turmaAtualizada.getCurso());

        return turmaRepository.save(turmaExistente);
    }

    /**
     * Exclui uma turma pelo ID.
     */
    @Transactional
    public void excluir(Long id) {
        Turma turma = buscarPorId(id);
        turmaRepository.delete(turma);
    }
}
