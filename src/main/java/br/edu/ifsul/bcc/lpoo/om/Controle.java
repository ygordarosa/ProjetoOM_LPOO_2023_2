/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.bcc.lpoo.om;

import br.edu.ifsul.bcc.lpoo.om.gui.JFramePrincipal;
import br.edu.ifsul.bcc.lpoo.om.model.dao.PersistenceJDBC;

/**
 *
 * @author Ygor
 */
public class Controle {

    private PersistenceJDBC conexaoJDBC;
    
    private JFramePrincipal jframe;
    
    public Controle() {
    }
    
    public boolean conectarBD() throws Exception {
        conexaoJDBC = new PersistenceJDBC();
        
        if(getConexaoJDBC() != null){
            return getConexaoJDBC().conexaoAberta();
        }
        return false;
    }
    
    public PersistenceJDBC getConexaoJDBC(){
        return conexaoJDBC;
    }
    
    
     protected void initComponents(){
         
         jframe = new JFramePrincipal();
         
         jframe.setVisible(true); //mostra o JFrame
     }
    
    
    
    
}
