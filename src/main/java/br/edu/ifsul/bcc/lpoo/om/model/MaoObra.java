/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.bcc.lpoo.om.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ygor
 */
@Entity
@Table(name = "tb_maoObra")
public class MaoObra implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_maoObra", sequenceName = "seq_maoObra_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_maoObra", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(nullable = false, length = 100)
    private String descricao;
    
    @Column(nullable = true)
    @Temporal(TemporalType.TIME)
    private Date tempo_estimado_execucao;
    
    @Column(nullable = false, precision = 2)
    private Float valor;

    public MaoObra() {
    }

    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the tempo_estimado_execucao
     */
    public Date getTempo_estimado_execucao() {
        return tempo_estimado_execucao;
    }

    /**
     * @param tempo_estimado_execucao the tempo_estimado_execucao to set
     */
    public void setTempo_estimado_execucao(Date tempo_estimado_execucao) {
        this.tempo_estimado_execucao = tempo_estimado_execucao;
    }

    /**
     * @return the valor
     */
    public Float getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Float valor) {
        this.valor = valor;
    }
    
     @Override
     public String toString(){
        
        return String.valueOf(this.getId());
    }
    
    
}
