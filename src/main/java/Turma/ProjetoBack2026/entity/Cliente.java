package Turma.ProjetoBack2026.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yaml.snakeyaml.events.Event;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    long id;

    @Column(nullable = false, length = 50)
    String nome;


    @Column(nullable = false, length = 25)
    String email;

    @Column(nullable = false, length = 25)
    String telefone;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Contato> contatos = new ArrayList<>();


}
