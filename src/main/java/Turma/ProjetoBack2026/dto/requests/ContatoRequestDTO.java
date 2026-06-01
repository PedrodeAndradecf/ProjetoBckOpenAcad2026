package Turma.ProjetoBack2026.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ContatoRequestDTO(
        @NotBlank(message="O telefone é obrigatorio") String telefone,
        @Email(message="O email é obrigatorio") String email,
        Long clientId
) {
}
