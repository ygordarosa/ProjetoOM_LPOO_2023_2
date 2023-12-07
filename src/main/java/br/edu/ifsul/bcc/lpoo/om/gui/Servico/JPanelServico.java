/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.bcc.lpoo.om.gui.Servico;

import br.edu.ifsul.bcc.lpoo.om.Controle;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Ygor
 */
public class JPanelServico extends JPanel  {
    
    private CardLayout cardLayout;
    private Controle  controle;

    private JPanelServicoFormulario formulario;
    private JPanelServicoListagem listagem;
    
    
    public JPanelServico(Controle controle){
        this.controle = controle;
        
        InitComponents();
    }
    
    private void InitComponents(){
        cardLayout = new CardLayout();
            this.setLayout(cardLayout);
            
            formulario = new JPanelServicoFormulario(getControle(), this);
            listagem = new JPanelServicoListagem(getControle(), this);
            
            // this.add(formulario, "tela_servico_formulario");
            // this.add(listagem, "tela_servico_listagem");
            
            this.add(formulario, "tela_servico_formulario");
            this.add(listagem, "tela_servico_listagem");

    }
    
    public void showTela(String nomeTela){
        
        try{
            
            if(nomeTela.equals("tela_servico_listagem")){
                System.out.println("entrou no show de servico");
                listagem.populaTable();

            }else if(nomeTela.equals("tela_servico_formulario")){
                
                //getFormulario().populaComboCargo();

            }

            cardLayout.show(this, nomeTela);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Atenção", "Erro ao acessar dados : "+e.getLocalizedMessage(), JOptionPane.ERROR);
            e.printStackTrace();
        }
    
     }
    
    
    
    public JPanelServicoFormulario getFormulario() {
        return formulario;
    }
    
    public Controle getControle() {
        return controle;
    }
    
    
}
