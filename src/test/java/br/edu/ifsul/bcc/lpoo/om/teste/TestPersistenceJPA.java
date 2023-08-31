/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.bcc.lpoo.om.teste;

import br.edu.ifsul.bcc.lpoo.om.model.Cargo;
import br.edu.ifsul.bcc.lpoo.om.model.dao.PersistenceJPA;
import java.util.Collection;
import javax.swing.text.html.parser.DTDConstants;
import org.junit.Test;

/**
 *
 * @author Ygor
 */
public class TestPersistenceJPA {
    
     
    public void testConexaoGeracaoTabelas(){
        
        PersistenceJPA persistencia = new PersistenceJPA();
        if(persistencia.conexaoAberta()){
            System.out.println("abriu a conexao com o BD via JPA");
            
            persistencia.fecharConexao();
            
            System.out.println("fechou a conexao com o BD via JPA");
            
        }else{
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }
        
    } 
    
    
     public void testPersistenceCargoJPA() throws Exception{
        PersistenceJPA jpa = new PersistenceJPA();
        if(jpa.conexaoAberta()){
            
           
            Cargo c = (Cargo) jpa.find(Cargo.class, new Integer("1"));
                
            if(c != null){
                System.out.println("Id: " + c.getId());
                System.out.println("Descricao: " + c.getDescricao());
                jpa.persist(c);
                System.out.println("Alterado: " + c.getId() + c.getDescricao());
                jpa.remover(c);
                System.out.println("removido: " + c.getId() + c.getDescricao());
            }else {
                System.out.println("criando novo Cargo");
                c = new Cargo();
                c.setDescricao("abc");
                jpa.persist(c);
            }
               
            
            
            System.out.println("abriu a conexao com o BD via JPA");
            jpa.fecharConexao(); 
        }else{
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }
        
    }
     
      @Test
     public void testPersistenceListCargoJPA() throws Exception{
        PersistenceJPA jpa = new PersistenceJPA();
        if(jpa.conexaoAberta()){
            //passo 1: recuperar a coleção de cargos
            Collection <Cargo> c = jpa.listCargo();
            if(!(c.isEmpty())){
                System.out.println(c);
                //passo 2: caso a colecao nao esteja vazia, imprimir os dados da selecao, alterar cada e remover cada item
                for(Cargo ch : c){
                    System.out.println("Id: " + ch.getId());
                    System.out.println("Descricao: " + ch.getDescricao());
                }
                for(Cargo ch : c){
                    ch.setDescricao("abcd");
                    jpa.persist(ch);
                }
                for(Cargo ch : c){
                    System.out.println("Id: " + ch.getId());
                    System.out.println("Descricao: " + ch.getDescricao());
                }
                for(Cargo ch : c){
                    jpa.remover(ch);
                }
                
            }else {
                System.out.println("2 Cargos criados");
                Cargo car = new Cargo(), asb = new Cargo();
                car.setDescricao("desc");
                jpa.persist(car);
                asb.setDescricao("descria");
                jpa.persist(asb);
            }

            System.out.println("abriu a conexao com o BD via JPA");
            jpa.fecharConexao(); 
        }else{
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }
        
    }
     
     
     /* 
     Criar um método de teste para funcionario
     
     Passo 1: recuperar a coleção de funcionario.
     Passo 2: caso a coleção não esteja vazia - imprimir (inclusive os cursos).
     Passo 3: caso a coleção esteja vazia, criar dois funcionarios com um Curso cada.
     */

}
