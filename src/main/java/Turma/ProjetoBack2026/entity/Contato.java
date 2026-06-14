package Turma.ProjetoBack2026.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contatos")
public class Contato {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;


    @Column(nullable = false)
    String nome;

    @Column(nullable = false, length = 25)
    String email;

    @Column(nullable = false, length = 25)
    String telefone;


    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonBackReference
    private Cliente cliente;
}
