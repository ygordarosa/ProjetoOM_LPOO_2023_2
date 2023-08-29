/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.bcc.lpoo.om.model;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Ygor
 */
@Entity
@Table(name = "tb_cliente")
@DiscriminatorValue(value = "C")
public class Cliente extends Pessoa {
    
    @Column(nullable = true, length = 300)
    private String observacoes;
    
    @ManyToMany
     @JoinTable(name = "tb_cliente_veiculo", joinColumns = {@JoinColumn(name = "cliente_cpf")}, 
            inverseJoinColumns = {@JoinColumn(name = "veiculo_placa")})
    private Collection<Veiculo> veiculo;

    public Cliente() {
    }

    /**
     * @return the observacoes
     */
    public String getObservacoes() {
        return observacoes;
    }

    /**
     * @param observacoes the observacoes to set
     */
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    /**
     * @return the veiculo
     */
    public Collection<Veiculo> getVeiculo() {
        return veiculo;
    }

    /**
     * @param veiculo the veiculo to set
     */
    public void setVeiculo(Collection<Veiculo> veiculo) {
        this.veiculo = veiculo;
    }
    
    
}
