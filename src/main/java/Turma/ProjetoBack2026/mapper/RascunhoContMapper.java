package Turma.ProjetoBack2026.mapper;

import Turma.ProjetoBack2026.dto.requests.ContatoRequestDTO;
import Turma.ProjetoBack2026.dto.response.ContatoResponseDTO;
import Turma.ProjetoBack2026.entity.Cliente;
import Turma.ProjetoBack2026.entity.Contato;

import java.util.ArrayList;
import java.util.List;

public class RascunhoContMapper {

    private RascunhoContMapper(){}

    /*public static List<ContatoResponseDTO> toResponse(List<Contato> contatos){
        List<ContatoResponseDTO> contatosR = contatos.stream()
                .map(c -> {
                    ContatoResponseDTO contR = new ContatoResponseDTO(
                            c.getId(),
                            c.getTelefone(),
                            c.getEmail(),
                            c.getCliente().getId()

                    );
                    return contR;
                }).toList();
        return contatosR;
    }*/

    public static ContatoResponseDTO toResponse(Contato contato){
        ContatoResponseDTO contatoResp = new ContatoResponseDTO(
                contato.getId(),
                contato.getEmail(),
                contato.getTelefone(),
                contato.getNome(),
                contato.getCliente().getId());
        return contatoResp;
    }


    public static Contato toEntity(ContatoRequestDTO dto, Cliente cliente){
        Contato contato = new Contato();
        contato.setTelefone(dto.telefone());
        contato.setEmail(dto.email());
        contato.setNome(dto.nome());
        contato.setCliente(cliente);
        return contato;
    }


}
