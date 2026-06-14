package Turma.ProjetoBack2026.mapper;

import Turma.ProjetoBack2026.dto.requests.ClienteRequestDTO;
import Turma.ProjetoBack2026.dto.response.ClienteResponseDTO;
import Turma.ProjetoBack2026.dto.response.ContatoResponseDTO;
import Turma.ProjetoBack2026.entity.Cliente;
import Turma.ProjetoBack2026.entity.Contato;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteMapper {

    private ClienteMapper(){} // impede que criem objetos dessa classe
    public static Cliente toEntity(ClienteRequestDTO requestDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(requestDTO.nome());

        if (requestDTO.contatos() != null && !requestDTO.contatos().isEmpty()){
            List<Contato> contatos = requestDTO.contatos().stream()
                    .map(contatoRequest -> {
                       return ContatoMapper.toEntity(contatoRequest, cliente);
                    })
                    .collect(Collectors.toList());
            cliente.setContatos(contatos);
        }

        return cliente;
    }


    public static ClienteResponseDTO toResponse(Cliente cliente){
        List<ContatoResponseDTO> contatosDTO = cliente.getContatos() != null
                ? cliente.getContatos().stream()
                .map(ContatoMapper::toResponse)
                .collect(Collectors.toList())
                : Collections.emptyList();

        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                contatosDTO
        );
    }

}
