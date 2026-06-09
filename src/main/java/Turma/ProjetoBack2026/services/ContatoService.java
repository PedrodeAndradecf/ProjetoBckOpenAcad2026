package Turma.ProjetoBack2026.services;

import Turma.ProjetoBack2026.dto.requests.ContatoRequestDTO;
import Turma.ProjetoBack2026.dto.response.ContatoResponseDTO;
import Turma.ProjetoBack2026.entity.Cliente;
import Turma.ProjetoBack2026.entity.Contato;
import Turma.ProjetoBack2026.exception.ClientNotFoundException;
import Turma.ProjetoBack2026.mapper.ContatoMapper;
import Turma.ProjetoBack2026.repository.ClienteRepository;
import Turma.ProjetoBack2026.repository.ContatoRepository;
import org.springframework.stereotype.Service;

@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;
    private final ClienteRepository clienteRepository;


    public ContatoService(ContatoRepository contatoRepository, ClienteRepository clienteRepository){
        this.contatoRepository = contatoRepository;
        this.clienteRepository = clienteRepository;
    }


    public ContatoResponseDTO salvarContato(ContatoRequestDTO requestDTO){
        Cliente cliente = clienteRepository.findById(requestDTO.clientId())
                .orElseThrow( () -> new ClientNotFoundException("Cliente não encontrado pelo id: " + requestDTO.clientId()));

        Contato contato = ContatoMapper.toEntity(requestDTO, cliente);
        Contato contatoSalvo = contatoRepository.save(contato);
        ContatoResponseDTO contatoDTO = ContatoMapper.toResponse(contatoSalvo);


        return contatoDTO;
    }



}
