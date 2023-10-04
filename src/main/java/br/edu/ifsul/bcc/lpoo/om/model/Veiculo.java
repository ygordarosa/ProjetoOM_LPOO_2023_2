/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.bcc.lpoo.om.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ygor
 */
@Entity
@Table(name = "tb_veiculo")
public class Veiculo implements Serializable {
    
    @Id
    private String placa;
    
    @Column(nullable = false, length = 50)
    private String modelo;
    
    @Column(nullable = true)
    private Integer ano;
    
    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_ultimo_servico;

    public Veiculo() {
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the ano
     */
    public Integer getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(Integer ano) {
        this.ano = ano;
    }

    /**
     * @return the dataUltimoServico
     */
    public Calendar getDataUltimoServico() {
        return data_ultimo_servico;
    }

    /**
     * @param dataUltimoServico the dataUltimoServico to set
     */
    public void setDataUltimoServico(Calendar dataUltimoServico) {
        this.data_ultimo_servico = dataUltimoServico;
    }
    
    
}
