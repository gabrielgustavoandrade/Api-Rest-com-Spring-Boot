package com.erppsicologo.erppsicologo.entities;

import javax.persistence.*;

import org.hibernate.envers.Audited;

@Entity
@Audited
public class TipoRecebimento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    private String descricao;

    public TipoRecebimento(TipoRecebimento tipoRecebimento) {
	}

	public TipoRecebimento() {
	}

	public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}