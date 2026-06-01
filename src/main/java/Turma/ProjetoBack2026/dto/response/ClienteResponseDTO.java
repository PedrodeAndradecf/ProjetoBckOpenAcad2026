package Turma.ProjetoBack2026.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ClienteResponseDTO(
        Long id,
        String nome,
        List<ClienteResponseDTO> cliente
) {
}
