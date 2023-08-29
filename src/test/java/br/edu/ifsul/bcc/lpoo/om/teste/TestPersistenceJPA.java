/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.bcc.lpoo.om.teste;

import br.edu.ifsul.bcc.lpoo.om.model.dao.PersistenceJPA;
import org.junit.Test;

/**
 *
 * @author Ygor
 */
public class TestPersistenceJPA {
    
     @Test 
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
}
