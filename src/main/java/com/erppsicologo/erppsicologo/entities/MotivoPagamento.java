package com.erppsicologo.erppsicologo.entities;

import javax.persistence.*;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class MotivoPagamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    private String descricao;

    public MotivoPagamento() {
	}

    public MotivoPagamento(MotivoPagamento motivoPagamento) {
	}

	public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}