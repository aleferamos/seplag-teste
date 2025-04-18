package br.gov.seplag_api_teste.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "unidade")
public class Unidade {
    @Id
    @Column(name = "unid_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unid_nome")
    private String nome;

    @Column(name = "unid_sigla")
    private String sigla;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "unidade_endereco",
            joinColumns = @JoinColumn(name = "unid_id"),
            inverseJoinColumns = @JoinColumn(name = "end_id")
    )
    private Endereco endereco;
}
