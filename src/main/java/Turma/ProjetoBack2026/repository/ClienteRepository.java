package Turma.ProjetoBack2026.repository;

import Turma.ProjetoBack2026.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT DISTINCT c FROM Cliente c LEFT JOIN FETCH c.contatos")
    List<Cliente> findAllComContatos();
}
