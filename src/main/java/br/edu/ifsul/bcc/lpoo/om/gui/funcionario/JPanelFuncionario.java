/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.bcc.lpoo.om.gui.funcionario;

import br.edu.ifsul.bcc.lpoo.om.Controle;
import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author Ygor
 */
public class JPanelFuncionario extends JPanel {
    
    private CardLayout cardLayout;
    private Controle controle;
    
    private JPanelFuncionarioFormulario formulario;
    private JPanelFuncionarioListagem listagem;
    
    public JPanelFuncionario(Controle controle){
        
        this.controle = controle;
        InitComponents();
        
    }
    
    private void InitComponents(){
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        
        formulario = new JPanelFuncionarioFormulario(this, controle);
        listagem = new JPanelFuncionarioListagem(this, controle);
        
        this.add(getFormulario(), "tela_funcionario_formulario");
        this.add(listagem, "tela_funcionario_listagem");
    }
    
    
    public void showTela(String nomeTela){
        
        if(nomeTela.equals("tela_funcionario_listagem")){
            
            //listagem.populaTable();
            
        }else if(nomeTela.equals("tela_funcionario_formulario")){
            
           // getFormulario().populaComboCargo();
            
        }
        
        cardLayout.show(this, nomeTela);
        
    }
    
    
    public Controle getControle() {
        return controle;
    }
    
    public JPanelFuncionarioFormulario getFormulario() {
        return formulario;
    }
}
