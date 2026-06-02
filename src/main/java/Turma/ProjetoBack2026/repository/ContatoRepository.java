package Turma.ProjetoBack2026.repository;

import Turma.ProjetoBack2026.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long>{
}
