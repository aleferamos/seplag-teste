package br.gov.seplag_api_teste.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @Column(name = "pes_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pes_nome")
    private String nome;

    @Column(name = "pes_data_nascimento")
    private Date dataNascimento;

    @Column(name = "pes_sexo")
    private String sexo;

    @Column(name = "pes_mae")
    private String nomeMae;

    @Column(name = "pes_pai")
    private String nomePai;
}
