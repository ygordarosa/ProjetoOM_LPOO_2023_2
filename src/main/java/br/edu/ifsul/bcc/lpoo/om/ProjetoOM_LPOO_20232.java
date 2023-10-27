/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.bcc.lpoo.om;

import javax.swing.JOptionPane;

/**
 *
 * @author Ygor
 */
public class ProjetoOM_LPOO_20232 {
    
    private Controle controle;

    public ProjetoOM_LPOO_20232() {
        
        try{
            controle = new Controle();
            
            if(controle.conectarBD()){
                controle.initComponents();
            }else {
                JOptionPane.showMessageDialog(null, "não conectou no Banco de Dados !", "Banco de dados", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao tentar conectar ao "
            + "Banco de Dados: "+ex.getLocalizedMessage(), "Banco de Dados", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        
    }
    
    
    public static void main(String[] args){
        //cria instancia
        new ProjetoOM_LPOO_20232();
        
      
    }
    
    
    
}
