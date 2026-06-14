package Turma.ProjetoBack2026.controller;

import Turma.ProjetoBack2026.dto.response.ClienteResponseDTO;
import Turma.ProjetoBack2026.dto.response.ContatoResponseDTO;
import Turma.ProjetoBack2026.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teste")
public class TesteController {


    @GetMapping
    public ResponseEntity<String> teste(){
        return ResponseEntity.ok("Teste)");
    }




}
