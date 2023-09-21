/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.bcc.lpoo.om.model.dao;

import br.edu.ifsul.bcc.lpoo.om.model.Cargo;
import br.edu.ifsul.bcc.lpoo.om.model.Funcionario;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ygor
 */
public class PersistenceJPA implements InterfacePersistence{

   
    
    public EntityManagerFactory factory;    //fabrica de gerenciadores de entidades
    public EntityManager entity;            //gerenciador de entidades JPA
    
     public PersistenceJPA() {
         factory = Persistence.createEntityManagerFactory("pu_db_om_lpoo_20232");
        entity = factory.createEntityManager();
    }
    
    @Override
    public Boolean conexaoAberta() {
        return entity.isOpen(); 
    }

    @Override
    public void fecharConexao() {
               entity.close();  
    }

    @Override
     public Object find(Class c, Object id) throws Exception {
        return entity.find(c, id);//encontra um determinado registro              
    }
    @Override
    public void persist(Object o) throws Exception {
        
        entity.getTransaction().begin();// abrir a transacao.
        entity.persist(o); //realiza o insert ou update.
        entity.getTransaction().commit(); //comita a transacao (comando sql)                
    }

     @Override
    public void remover(Object o) throws Exception {
        
        entity.getTransaction().begin();// abrir a transacao.
        entity.remove(o); //realiza o delete
        entity.getTransaction().commit(); //comita a transacao (comando sql)                
    }

    @Override
    public Collection<Cargo> listCargo() throws Exception {
       
        return  entity.createNamedQuery("Cargo.orderbyid").getResultList();
        
    }
    
    @Override
    public Collection<Funcionario> listFuncionario() throws Exception {
       
        return  entity.createNamedQuery("Funcionario.orderbycpf").getResultList();
        
    }
    
}
