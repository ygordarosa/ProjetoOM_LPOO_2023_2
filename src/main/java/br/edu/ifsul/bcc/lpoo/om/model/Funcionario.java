/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.bcc.lpoo.om.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Ygor
 */
@Entity
@Table(name = "tb_funcionario")
@DiscriminatorValue("F")
@NamedQueries({@NamedQuery(name="Funcionario.orderbynome", query="select f from Funcionario f order by f.nome asc")})

public class Funcionario extends Pessoa {

    @Column(nullable = false, length = 10)
    private String numero_ctps;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar data_admissao;

    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private Calendar data_demissao;
    
    @ManyToOne
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargo cargo;
    
     @ManyToMany
    @JoinTable(name = "tb_funcionario_curso", joinColumns = {@JoinColumn(name = "funcionario_cpf")}, //agregacao, vai gerar uma tabela associativa.
                                       inverseJoinColumns = {@JoinColumn(name = "curso_id")})  
    private Collection<Curso> curso;
     
     @Transient
    private SimpleDateFormat sdf;

    public Funcionario() {
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }
     

    
    
    
    /**
     * @return the numero_ctps
     */
    public String getNumero_ctps() {
        return numero_ctps;
    }

    /**
     * @param numero_ctps the numero_ctps to set
     */
    public void setNumero_ctps(String numero_ctps) {
        this.numero_ctps = numero_ctps;
    }

    /**
     * @return the data_admissao
     */
    public Calendar getData_admissao() {
        return data_admissao;
    }

    /**
     * @param data_admissao the data_admissao to set
     */
    public void setData_admissao(Calendar data_admissao) {
        this.data_admissao = data_admissao;
    }

    /**
     * @return the data_demissao
     */
    public Calendar getData_demissao() {
        return data_demissao;
    }

    /**
     * @param data_demissao the data_demissao to set
     */
    public void setData_demissao(Calendar data_demissao) {
        this.data_demissao = data_demissao;
    }

    /**
     * @return the cargo
     */
    public Cargo getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    /**
     * @return the curso
     */
    public Collection<Curso> getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(Collection<Curso> curso) {
        this.curso = curso;
    }
    
    public void setCursos(Curso curso){
        if(this.curso == null){
            this.curso = new ArrayList();
        }
        this.curso.add(curso);
    }
    
    
    @Override
    public String toString(){
        
        return this.getCpf();
    }
    
    public void setData_admmissao(String data_admmissao){

        try{
             this.data_admissao = Calendar.getInstance();
             this.data_admissao.setTimeInMillis(sdf.parse(data_admmissao).getTime());

        }catch(Exception e){

            this.data_admissao = null;
        }

    }
    
    public void setData_demissaoString(String data_demissao) {
        try{
             this.data_demissao = Calendar.getInstance();
             this.data_demissao.setTimeInMillis(sdf.parse(data_demissao).getTime());

        }catch(Exception e){

            this.data_demissao = null;
        }

    }
    
     public String getData_admmissao_string() {
        if(this.data_admissao != null){
            return this.data_admissao.get(Calendar.DAY_OF_MONTH) + "/"+
                   (this.data_admissao.get(Calendar.MONTH) + 1) + "/"+
                   this.data_admissao.get(Calendar.YEAR); 
        }else{
            return "";
        }
        
    }
     
     
  


}
