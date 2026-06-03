package Turma.ProjetoBack2026.services;

import Turma.ProjetoBack2026.dto.requests.ClienteRequestDTO;
import Turma.ProjetoBack2026.dto.response.ClienteResponseDTO;
import Turma.ProjetoBack2026.dto.response.ContatoResponseDTO;
import Turma.ProjetoBack2026.entity.Cliente;
import Turma.ProjetoBack2026.mapper.ClienteMapper;
import Turma.ProjetoBack2026.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ClienteService {

    private final ClienteRepository repository;



    public ClienteService(ClienteRepository repository){
        this.repository = repository;
    }


    public List<ClienteResponseDTO> listarTodos(){
        List<Cliente> clientes = repository.findAllComContatos();

        return clientes.stream()
                .map(ClienteMapper::toResponse)
                .collect(Collectors.toList());
    }








    /*public List<ClienteResponseDTO> listarTodos() {
        List<Cliente> clientes = repository.findAll();
        return clientes.stream()
                .map(c -> new ClienteResponseDTO(
                        c.getId(),
                        c.getNome(),
                        c.getContatos().stream()
                                .map(contato -> new ContatoResponseDTO(
                                        contato.getId(),
                                        contato.getEmail(),
                                        contato.getTelefone(),
                                        contato.getCliente().getId()
                                )).collect(Collectors.toList())

                )).collect(Collectors.toList());

                Assim era feito manualmente em todos os metodos, mas agora temos o Mapper para fazer a conversão
    }*/



}

