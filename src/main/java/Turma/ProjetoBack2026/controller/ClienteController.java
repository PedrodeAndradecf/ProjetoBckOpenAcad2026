package Turma.ProjetoBack2026.controller;

import Turma.ProjetoBack2026.dto.requests.ClienteRequestDTO;
import Turma.ProjetoBack2026.dto.response.ClienteResponseDTO;
import Turma.ProjetoBack2026.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> cadastrarCliente(@RequestBody @Valid ClienteRequestDTO requestDTO){
        ClienteResponseDTO clienteResponse = service.salvarCliente((requestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteResponse);


    }


    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarTodos(){
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/clientes/{clienteId}/contatos")
    public ResponseEntity<List<ClienteResponseDTO>> listarContatosPorCliente(@PathVariable Long clienteId){
        return ResponseEntity.ok(service.listarContatosPorCliente(clienteId));
}
