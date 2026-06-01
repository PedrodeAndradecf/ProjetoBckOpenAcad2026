package Turma.ProjetoBack2026.dto.response;

public record ContatoResponseDTO(
        Long id,
        String email,
        String telefone,
        Long clienteID
) {
}
