/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.bcc.lpoo.om.gui;

import br.edu.ifsul.bcc.lpoo.om.Controle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Ygor
 */
public class JMenuBarHome extends JMenuBar implements ActionListener {
    
    private JMenu menuArquivo;
    private JMenuItem menuItemLogout;
    private JMenuItem menuItemSair;

    private JMenu menuCadastro;
    private JMenuItem menuItemFuncionario;    
    private JMenuItem menuItemFuncionarioDesigner;   
    private JMenuItem menuItemPeca;
    private JMenuItem menuItemServico;
    
    private JMenu menuListagem;
    private JMenuItem menuItemListagemMaoObra;
    

    private Controle controle;
    
    public JMenuBarHome(Controle controle){
        
        this.controle = controle;        
        
        initComponents();
    }
    
    private void initComponents(){
        
        menuArquivo = new JMenu("Arquivo");
        menuArquivo.setMnemonic(KeyEvent.VK_A);//ativa o ALT + A para acessar esse menu - acessibilidade
        menuArquivo.setToolTipText("Arquivo"); //acessibilidade
        menuArquivo.setFocusable(true); //acessibilidade
                
        menuItemSair = new JMenuItem("Sair");
        menuItemSair.setToolTipText("Sair"); //acessibilidade
        menuItemSair.setFocusable(true);     //acessibilidade
        
        menuItemLogout = new JMenuItem("Logout");
        menuItemLogout.setToolTipText("Logout"); //acessibilidade
        menuItemLogout.setFocusable(true);     //acessibilidade
        
        menuItemLogout.addActionListener(this);
        menuItemLogout.setActionCommand("menu_logout");
        menuArquivo.add(menuItemLogout);

        menuItemSair.addActionListener(this);
        menuItemSair.setActionCommand("menu_sair");
        menuArquivo.add(menuItemSair);

        menuCadastro = new JMenu("Cadastros");
        menuCadastro.setMnemonic(KeyEvent.VK_C);//ativa o ALT + C para acessar esse menu - acessibilidade
        menuCadastro.setToolTipText("Cadastro"); //acessibilidade
        menuCadastro.setFocusable(true); //acessibilidade
        
        menuItemFuncionario = new JMenuItem("Funcionário");
        menuItemFuncionario.setToolTipText("Funcionario"); //acessibilidade
        menuItemFuncionario.setFocusable(true); //acessibilidade

        menuItemFuncionario.addActionListener(this);
        menuItemFuncionario.setActionCommand("menu_funcionario");
        menuCadastro.add(menuItemFuncionario);  
        
        menuItemFuncionarioDesigner = new JMenuItem("Funcionario (Designer)");
        menuItemFuncionarioDesigner.setToolTipText("Funcionario (Desinger)"); //acessibilidade
        menuItemFuncionarioDesigner.setFocusable(true); //acessibilidade
        
                   
        menuItemFuncionarioDesigner.addActionListener(this);
        menuItemFuncionarioDesigner.setActionCommand("menu_funcionario_designer");
        menuCadastro.add(menuItemFuncionarioDesigner);  
        
        menuItemPeca = new JMenuItem("Peça");
        menuItemPeca.setToolTipText("Peça"); //acessibilidade
        menuItemPeca.setFocusable(true); //acessibilidade
        
        menuItemPeca.addActionListener(this);
        menuItemPeca.setActionCommand("menu_peca");
        menuCadastro.add(menuItemPeca); 
        
        menuItemServico = new JMenuItem("Serviço");
        menuItemServico.setToolTipText("Serviço");
        menuItemServico.setFocusable(true);
        
        menuItemServico.addActionListener(this);
        menuItemServico.setActionCommand("menu_servico");
        menuCadastro.add(menuItemServico);
        
        menuListagem = new JMenu("Listagem");
        menuListagem.setMnemonic(KeyEvent.VK_A);//ativa o ALT + A para acessar esse menu - acessibilidade
        menuListagem.setToolTipText("Listagem"); //acessibilidade
        menuListagem.setFocusable(true); //acessibilidade
        
        menuItemListagemMaoObra = new JMenuItem("Mão de Obra");
        menuItemListagemMaoObra.setToolTipText("Mão de Obra");
        menuItemListagemMaoObra.setFocusable(true);
        
        menuItemListagemMaoObra.addActionListener(this);
        menuItemListagemMaoObra.setActionCommand("menu_listagem_maoobra");
        menuListagem.add(menuItemListagemMaoObra);
        
        this.add(menuArquivo);
        this.add(menuCadastro);  
        this.add(menuListagem);
}
    
    @Override
    public void actionPerformed(ActionEvent e) {
       
        if(e.getActionCommand().equals(menuItemSair.getActionCommand())){
        
            int decisao = JOptionPane.showConfirmDialog(this, "Deseja realmente sair ?");
            if(decisao == 0){
                controle.getConexaoJDBC().fecharConexao();
                System.exit(0);
            }
            
        }else if(e.getActionCommand().equals(menuItemFuncionario.getActionCommand())){
            
                       controle.showTela("tela_funcionario");
                        
        }else if(e.getActionCommand().equals(menuItemLogout.getActionCommand())){
            
                        //->controle.showTela("tela_autenticacao");    
                        
        }else if(e.getActionCommand().equals(menuItemFuncionarioDesigner.getActionCommand())){
            
                        //controle.showTela("tela_jogador_designer");
        }else if(e.getActionCommand().equals(menuItemPeca.getActionCommand())){
            
                       controle.showTela("tela_peca");
                        
        }else if(e.getActionCommand().equals(menuItemServico.getActionCommand())){
            
                       controle.showTela("tela_servico");
                        
        }else if(e.getActionCommand().equals(menuItemListagemMaoObra.getActionCommand())){
            
                       controle.showTela("tela_listagem_maoobra");
                        
        }
        
        
    }
    
    
    
}
