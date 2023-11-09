package br.edu.ifsul.bcc.lpoo.om.gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author Ygor
 */

//herança: JFramePrincipal herda as caracteristicas de JFrame
public class JFramePrincipal extends JFrame{
    
    public CardLayout cardLayout;
    
    public JPanel painel;//painel.
    
    public JFramePrincipal(){
        
        initComponents();
    }
    
    private void initComponents(){
        //customização do JFrame
        //define o titulo do Jframe
        this.setTitle("Sisteminha para CRUD - Oficina Mecânica"); //seta o título do jframe
        
        //define o tamanho mínimo
        this.setMinimumSize(new Dimension(600,600)); //tamanho minimo quando for reduzido.
        
        //define a abertura em modo maximizado
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // por padrão abre maximizado.
        
        //defini o comportamento de fechar o processo no fechamento do Jframe
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );// finaliza o processo quando o frame é fechado.  
        
        cardLayout = new CardLayout();//iniciando o gerenciador de layout para esta JFrame
        painel = new JPanel();//inicializacao
        painel.setLayout(cardLayout);//definindo o cardLayout para o paineldeFundo
        this.add(painel);  //adiciona no JFrame o paineldeFundo
    }
    
    public void addTela(JPanel p, String nome){   
        
            painel.add(p, nome); //adiciona uma "carta no baralho".
    }

    public void showTela(String nome){
        
            cardLayout.show(painel, nome); //localiza a "carta no baralho" e mostra.
    }
    
}
