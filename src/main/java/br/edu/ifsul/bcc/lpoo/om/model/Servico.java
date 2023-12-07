/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.bcc.lpoo.om.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Ygor
 */
@Entity
@Table(name = "tb_servico")
public class Servico implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_servico", sequenceName = "seq_servicos_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_servico", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(precision = 2, nullable = false)
    private Float valor;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar data_inicio;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar data_fim;
    
    @OneToMany(mappedBy = "servico")
    private Collection<Pagamento> parcelas;
    
    @ManyToOne
    @JoinColumn(name = "equipe_id", nullable = false)
    private Equipe equipe;
    
    @ManyToOne
    @JoinColumn(name = "orcamento_id", nullable = false)
    private Orcamento orcamento;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusServico status;

    
    @Transient
    private SimpleDateFormat sdf;
    
       
    

    public Servico() {
         sdf = new SimpleDateFormat("dd/MM/yyyy");
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

    /**
     * @return the data_inicio
     */
    public Calendar getData_inicio() {
        return data_inicio;
    }

    /**
     * @param data_inicio the data_inicio to set
     */
    public void setData_inicio(Calendar data_inicio) {
        this.data_inicio = data_inicio;
    }

    /**
     * @return the data_fim
     */
    public Calendar getData_fim() {
        return data_fim;
    }

    /**
     * @param data_fim the data_fim to set
     */
    public void setData_fim(Calendar data_fim) {
        this.data_fim = data_fim;
    }

    /**
     * @return the pagamento
     */
    public Collection<Pagamento> getPagamento() {
        return parcelas;
    }

    /**
     * @param pagamento the pagamento to set
     */
    public void setPagamento(Collection<Pagamento> pagamento) {
        this.parcelas = pagamento;
    }

    /**
     * @return the equipe
     */
    public Equipe getEquipe() {
        return equipe;
    }

    /**
     * @param equipe the equipe to set
     */
    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    /**
     * @return the orcamento
     */
    public Orcamento getOrcamento() {
        return orcamento;
    }

    /**
     * @param orcamento the orcamento to set
     */
    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    /**
     * @return the statusServico
     */
    public StatusServico getStatusServico() {
        return status;
    }

    /**
     * @param statusServico the statusServico to set
     */
    public void setStatusServico(StatusServico statusServico) {
        this.status = statusServico;
    }
    
    
    @Override
     public String toString(){
        
        return String.valueOf(this.getId());
    }
     
     
     public void setData_inicio(String data_admmissao){

        try{
             this.data_inicio = Calendar.getInstance();
             this.data_inicio.setTimeInMillis(sdf.parse(data_admmissao).getTime());

        }catch(Exception e){

            this.data_inicio = null;
        }

    }
     
     public void setData_fim(String data_admmissao){

        try{
             this.data_fim = Calendar.getInstance();
             this.data_fim.setTimeInMillis(sdf.parse(data_admmissao).getTime());

        }catch(Exception e){

            this.data_fim = null;
        }

    }
     
     

    
     public String getData_inicio_string() {
        if(this.data_inicio != null){
            return this.data_inicio.get(Calendar.DAY_OF_MONTH) + "/"+
                   (this.data_inicio.get(Calendar.MONTH) + 1) + "/"+
                   this.data_inicio.get(Calendar.YEAR); 
        }else{
            return "";
        }
        
    }
     
      
     public String getData_fim_string() {
        if(this.data_fim != null){
            return this.data_fim.get(Calendar.DAY_OF_MONTH) + "/"+
                   (this.data_fim.get(Calendar.MONTH) + 1) + "/"+
                   this.data_fim.get(Calendar.YEAR); 
        }else{
            return "";
        }
        
    }
    
}
