/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.bcc.lpoo.om.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Ygor
 */
@Entity
@Table(name = "tb_equipe")
public class Equipe implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_equipe", sequenceName = "seq_equipe_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_equipe", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(nullable = false, length = 50)
    private String nome;
    
    @Column(nullable = false, length = 100)
    private String especialidades;
    
    @ManyToMany
    @JoinTable(name = "tb_equipe_funcionario", joinColumns = {@JoinColumn(name = "equipe_id")}, 
            inverseJoinColumns = {@JoinColumn(name = "funcionario_cpf")})
    private Collection<Funcionario> funcionario;

    public Equipe() {
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
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the especialidades
     */
    public String getEspecialidades() {
        return especialidades;
    }

    /**
     * @param especialidades the especialidades to set
     */
    public void setEspecialidades(String especialidades) {
        this.especialidades = especialidades;
    }

    /**
     * @return the funcionario
     */
    public Collection<Funcionario> getFuncionario() {
        return funcionario;
    }

    /**
     * @param funcionario the funcionario to set
     */
    public void setFuncionario(Collection<Funcionario> funcionario) {
        this.funcionario = funcionario;
    }
    
    
}
