package Turma.ProjetoBack2026.mapper;

import Turma.ProjetoBack2026.dto.requests.ClienteRequestDTO;
import Turma.ProjetoBack2026.dto.response.ClienteResponseDTO;
import Turma.ProjetoBack2026.dto.response.ContatoResponseDTO;
import Turma.ProjetoBack2026.entity.Cliente;
import Turma.ProjetoBack2026.entity.Contato;
import Turma.ProjetoBack2026.repository.ClienteRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;



public class RascunhoMapper {
    private final ClienteRepository repository;

    private RascunhoMapper(ClienteRepository repository) {
        this.repository = repository;
    }


    public static Cliente toEntity(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());

        if (dto.contatos() != null && !dto.contatos().isEmpty()) {
            List<Contato> contatos = dto.contatos().stream()
                    .map(contReq -> {
                        return RascunhoContMapper.toEntity(contReq, cliente);
                    }).collect(Collectors.toList());
            cliente.setContatos(contatos);
        }

        return cliente;
    }



    public static ClienteResponseDTO toResponse(Cliente cliente){
        List<ContatoResponseDTO> contatosDTO = new ArrayList<>();

        if (cliente.getContatos() != null && !cliente.getContatos().isEmpty()){
            contatosDTO = cliente.getContatos().stream()
                    .map(RascunhoContMapper::toResponse).collect(Collectors.toList());

        } else{
            contatosDTO = Collections.emptyList();
        }

        ClienteResponseDTO clienteResp = new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                contatosDTO
        );

        return clienteResp;
    }


}






    /*public ClienteResponseDTO toReponse(ClienteRequestDTO dto){
        Cliente cliente = new Cliente();

        cliente.setNome(dto.nome());

        if (dto.contatos() != null && !dto.contatos().isEmpty()){
            List<Contato> contatos = dto.contatos().stream()
                    .map(Creq -> {
                        Contato contato = new Contato();
                        contato.setTelefone(Creq.telefone());
                        contato.setEmail(Creq.email());
                        contato.setCliente(cliente);
                        return contato;
                    }).collect(Collectors.toList());
            cliente.setContatos(contatos);
        }

        Cliente clienteSalvo = repository.save(cliente);


        List<ContatoResponseDTO> contatosR = clienteSalvo.getContatos().stream()
                .map(RascunhoContMapper::toResponse).toList();*/
      /*  List<ContatoResponseDTO> contatosR = clienteSalvo.getContatos().stream()
                .map(contato -> {
                    ContatoResponseDTO cdto = new ContatoResponseDTO(
                            contato.getId(),
                            contato.getEmail(),
                            contato.getTelefone(),
                            contato.getId()
                    );

                    return cdto;

                }).collect(Collectors.toList());*/
      // return new ClienteResponseDTO(clienteSalvo.getId(), clienteSalvo.getNome(), contatosR);
   // }
//}
