/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.bcc.lpoo.om;

import br.edu.ifsul.bcc.lpoo.om.gui.JFramePrincipal;
import br.edu.ifsul.bcc.lpoo.om.gui.JMenuBarHome;
import br.edu.ifsul.bcc.lpoo.om.gui.JPanelHome;
import br.edu.ifsul.bcc.lpoo.om.gui.Servico.JPanelServico;
import br.edu.ifsul.bcc.lpoo.om.gui.autenticacao.JPanelAutenticacao;
import br.edu.ifsul.bcc.lpoo.om.gui.funcionario.JPanelFuncionario;
import br.edu.ifsul.bcc.lpoo.om.gui.peca.JPanelPeca;
import br.edu.ifsul.bcc.lpoo.om.model.Funcionario;
import br.edu.ifsul.bcc.lpoo.om.model.dao.PersistenceJDBC;
import javax.swing.JOptionPane;

/**
 *
 * @author Ygor
 */
public class Controle {

    private PersistenceJDBC conexaoJDBC;
    private JFramePrincipal jframe;
    private JPanelAutenticacao telaAutenticacao;
    private JMenuBarHome menuBar;
    private JPanelHome  telaHome;
    private JPanelFuncionario telaFuncionario;
    private JPanelPeca telaPeca;
    private JPanelServico telaServico;
    
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
         
         telaAutenticacao = new JPanelAutenticacao(this);
         
         menuBar = new JMenuBarHome(this);
         
         telaHome = new JPanelHome(this);
         
         telaFuncionario = new JPanelFuncionario(this);
         
         telaPeca = new JPanelPeca(this);
         
         telaServico = new JPanelServico(this);
         
         //adicionando no baralho a tela de autenticacao
         jframe.addTela(telaAutenticacao, "tela_autenticacao");
         
         jframe.addTela(telaHome, "tela_home"); //adiciona
         
         jframe.addTela(telaFuncionario, "tela_funcionario");
         
         jframe.addTela(telaPeca, "tela_peca");
         
         jframe.addTela(telaServico, "tela_servico");
         
         jframe.showTela("tela_autenticacao");
         jframe.setVisible(true); //mostra o JFrame
     }
     
     
         public void autenticar(String cpf, String senha) {
        //  implementar o metodo doLogin da classe PersistenciaJDBC
        //  chamar o doLogin e verificar o retorno.
        // se o retorno for nulo, informar ao usuário
        //se nao for nulo, apresentar a tela de boas vindas e o menu.
        try{

            Funcionario f =  conexaoJDBC.doLogin(cpf, senha);
            
            if(f != null){

                JOptionPane.showMessageDialog(telaAutenticacao, "Funcionario "+f.getCpf()+" autenticado com sucesso!", "Autenticação", JOptionPane.INFORMATION_MESSAGE);

                 jframe.setJMenuBar(menuBar);//adiciona o menu de barra no frame
                 jframe.showTela("tela_home");//muda a tela para o painel de boas vindas (home)

            }else{

                JOptionPane.showMessageDialog(telaAutenticacao, "Dados inválidos!", "Autenticação", JOptionPane.INFORMATION_MESSAGE);
            }

        }catch(Exception e){

            JOptionPane.showMessageDialog(telaAutenticacao, "Erro ao executar a autenticação no Banco de Dados!", "Autenticação", JOptionPane.ERROR_MESSAGE);
        }
    }
         
         
         public void showTela(String nomeTela){
         
        //para cada nova tela de CRUD adicionar um elseif
        
         if(nomeTela.equals("tela_funcionario")){
             
            telaFuncionario.showTela("tela_funcionario_listagem");
            
            
         }else if(nomeTela.equals("tela_peca")){
             telaPeca.showTela("tela_peca_listagem");
         }else if(nomeTela.equals("tela_servico")){
             telaServico.showTela("tela_servico_listagem");
         }
         
         jframe.showTela(nomeTela);
         
    }
         
      
     
    
    
    
    
}
