package app.service;

import app.entity.Curso;
import app.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

	@Autowired
	private CursoRepository cursoRepository;

	@Transactional
	public String save(Curso curso) {
		validarCurso(curso);
		cursoRepository.save(curso);
		return "Curso salvo com sucesso!";
	}

	@Transactional
	public String delete(long id) {
		Optional<Curso> cursoOpt = cursoRepository.findById(id);
		if (cursoOpt.isPresent()) {
			cursoRepository.deleteById(id);
			return "Curso deletado com sucesso!";
		} else {
			throw new RuntimeException("Curso não encontrado.");
		}
	}

	public Curso findById(long id) {
		return cursoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Curso não encontrado."));
	}

	@Transactional
	public String update(long id, Curso curso) {
		Curso cursoExistente = findById(id);
		//cursoExistente.setNome(curso.getNome());
		cursoRepository.save(cursoExistente);
		return "Curso atualizado com sucesso!";
	}

	public List<Curso> findAll() {
		return cursoRepository.findAll();
	}

	private void validarCurso(Curso curso) {
		//if (curso.getNome() == null || curso.getNome().trim().isEmpty()) {
			//throw new IllegalArgumentException("O nome do curso é obrigatório.");
		//}
	}
}
