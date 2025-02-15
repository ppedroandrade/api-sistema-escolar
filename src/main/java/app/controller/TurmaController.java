package app.controller;

import app.entity.Turma;
import app.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turma")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    /**
     * Retorna todas as turmas cadastradas.
     */
    @GetMapping("/findAll")
    public ResponseEntity<List<Turma>> listarTodas() {
        try {
            List<Turma> turmas = turmaService.listarTodas();
            return new ResponseEntity<>(turmas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Retorna uma turma específica pelo ID.
     */
    @GetMapping("/findById/{id}")
    public ResponseEntity<Turma> buscarPorId(@PathVariable Long id) {
        try {
            Turma turma = turmaService.buscarPorId(id);
            return new ResponseEntity<>(turma, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Cadastra uma nova turma.
     */
    @PostMapping("/save")
    public ResponseEntity<String> salvar(@RequestBody Turma turma) {
        try {
            turmaService.salvar(turma);
            return new ResponseEntity<>("Turma salva com sucesso!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao salvar a turma.", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Atualiza uma turma existente.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody Turma turma) {
        try {
            turmaService.atualizar(id, turma);
            return new ResponseEntity<>("Turma atualizada com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao atualizar a turma.", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Exclui uma turma pelo ID.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> excluir(@PathVariable Long id) {
        try {
            turmaService.excluir(id);
            return new ResponseEntity<>("Turma deletada com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao excluir a turma.", HttpStatus.BAD_REQUEST);
        }
    }
}
