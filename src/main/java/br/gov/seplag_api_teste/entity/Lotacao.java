package br.gov.seplag_api_teste.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "lotacao")
public class Lotacao {
    @Id
    @Column(name = "lot_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;

    @OneToOne
    @JoinColumn(name = "unid_id")
    private Unidade unidade;

    @Column(name = "lot_data_lotacao")
    private Date dataLotacao;

    @Column(name = "lot_data_remocao")
    private Date dataRemocao;

    @Column(name = "lot_portaria")
    private String portaria;
}
