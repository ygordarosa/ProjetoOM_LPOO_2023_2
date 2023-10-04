/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.bcc.lpoo.om.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ygor
 */
@Entity
@Table(name = "tb_orcamento")
public class Orcamento implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_orcamento", sequenceName = "seq_orcamento_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_orcamento", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(nullable = true, length = 100)
    private String observacoes;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar data;
    
    @ManyToMany
     @JoinTable(name = "tb_orcamento_maoobra", joinColumns = {@JoinColumn(name = "orcamento_id")}, 
            inverseJoinColumns = {@JoinColumn(name = "maoObra_id")})
    private Collection<MaoObra> maoObra;
    
     @ManyToMany
     @JoinTable(name = "tb_orcamento_peca", joinColumns = {@JoinColumn(name = "orcamento_id")}, 
            inverseJoinColumns = {@JoinColumn(name = "peca_id")})
    private Collection<Peca> peca;
     
     @ManyToOne
     @JoinColumn(name = "veiculo_id", nullable = false)
     private Veiculo veiculo;
     
     @ManyToOne
     @JoinColumn(name = "cliente_cpf", nullable = false)
     private Cliente cliente;
     
     @ManyToOne
     @JoinColumn(name = "funcionario_cpf", nullable = false)
     private Funcionario funcionario;

    public Orcamento() {
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
     * @return the dataOrcamento
     */
    public Calendar getDataOrcamento() {
        return data;
    }

    /**
     * @param dataOrcamento the dataOrcamento to set
     */
    public void setDataOrcamento(Calendar dataOrcamento) {
        this.data = dataOrcamento;
    }

    /**
     * @return the maoObra
     */
    public Collection<MaoObra> getMaoObra() {
        return maoObra;
    }

    /**
     * @param maoObra the maoObra to set
     */
    public void setMaoObra(Collection<MaoObra> maoObra) {
        this.maoObra = maoObra;
    }

    /**
     * @return the peca
     */
    public Collection<Peca> getPeca() {
        return peca;
    }

    /**
     * @param peca the peca to set
     */
    public void setPeca(Collection<Peca> peca) {
        this.peca = peca;
    }

    /**
     * @return the veiculo
     */
    public Veiculo getVeiculo() {
        return veiculo;
    }

    /**
     * @param veiculo the veiculo to set
     */
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the funcionario
     */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * @param funcionario the funcionario to set
     */
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
     
     
}
