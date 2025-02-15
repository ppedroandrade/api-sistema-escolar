package app.controller;

import app.entity.Professor;
import app.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professor")  // Caminho base correto
public class ProfessorController {

	@Autowired
	private ProfessorService professorService;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Professor professor) {
		try {
			String mensagem = professorService.save(professor);
			return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Erro ao salvar professor.", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Professor> findById(@PathVariable long id) {
		try {
			Professor professor = professorService.findById(id);
			return new ResponseEntity<>(professor, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<Professor>> findAll() {
		try {
			List<Professor> lista = professorService.findAll();
			return new ResponseEntity<>(lista, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable long id, @RequestBody Professor professor) {
		try {
			String mensagem = professorService.update(id, professor);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Erro ao atualizar professor.", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable long id) {
		try {
			String mensagem = professorService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Erro ao deletar professor.", HttpStatus.BAD_REQUEST);
		}
	}
}
