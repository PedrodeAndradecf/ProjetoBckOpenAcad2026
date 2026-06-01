package Turma.ProjetoBack2026.dto.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ClienteRequestDTO(
        @NotBlank(message="Nome é obrigatório")
        String nome,
        List<@Valid ContatoRequestDTO> contatos
) {

}
