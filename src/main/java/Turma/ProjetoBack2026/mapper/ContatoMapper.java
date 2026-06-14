package Turma.ProjetoBack2026.mapper;

import Turma.ProjetoBack2026.dto.requests.ContatoRequestDTO;
import Turma.ProjetoBack2026.dto.response.ContatoResponseDTO;
import Turma.ProjetoBack2026.entity.Cliente;
import Turma.ProjetoBack2026.entity.Contato;

public class ContatoMapper {

    private ContatoMapper(){}

    public static Contato toEntity(ContatoRequestDTO requestDTO, Cliente cliente){
        Contato contato = new Contato();
        contato.setEmail(requestDTO.email());
        contato.setTelefone(requestDTO.telefone());
        contato.setNome(requestDTO.nome());
        contato.setCliente(cliente);

        return contato;
    }

    public static ContatoResponseDTO toResponse(Contato contato){
        return new ContatoResponseDTO(
                contato.getId(),
                contato.getEmail(),
                contato.getTelefone(),
                contato.getNome(),
                contato.getCliente().getId()
        );
    }
}

