/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.bcc.lpoo.om.gui.maoObra;

import br.edu.ifsul.bcc.lpoo.om.Controle;
import br.edu.ifsul.bcc.lpoo.om.gui.funcionario.JPanelFuncionario;
import br.edu.ifsul.bcc.lpoo.om.model.Funcionario;
import br.edu.ifsul.bcc.lpoo.om.model.MaoObra;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ygor
 */
public class JPanelMaoObraListagem extends JPanel implements ActionListener {
    
    
    private JPanel pnlNorte;
    private JLabel lblFiltro;
    private JTextField txtFiltro;
    private JButton btnFiltro;
    private JButton btnRemover;
    private FlowLayout layoutFlowNorte;
    
    private JPanel pnlCentro;
    private JScrollPane scpCentro;
    private DefaultTableModel modeloTabela;
    private JTable tabela;
    private BorderLayout layoutBorderCentro;
    
    private BorderLayout layoutBorder;
    
    public Controle controle;
    
    
    public JPanelMaoObraListagem(Controle controle){

        this.controle = controle;
        InitComponents();
    }
    
    
    
    private void InitComponents(){
        
        layoutBorder = new BorderLayout();
        this.setLayout(layoutBorder);
        
        
        pnlNorte = new JPanel();
        layoutFlowNorte = new FlowLayout();
        pnlNorte.setLayout(layoutFlowNorte);
        
        lblFiltro = new JLabel("Filtro: ");
        pnlNorte.add(lblFiltro);
        txtFiltro = new JTextField(20);
        pnlNorte.add(txtFiltro);

        
        btnFiltro = new JButton("Filtrar");
        btnFiltro.setActionCommand("comando_filtrar");
        btnFiltro.addActionListener(this);
        pnlNorte.add(btnFiltro);
        
        btnRemover = new JButton("Remover");
        btnRemover.setActionCommand("comando_remover");
        btnRemover.addActionListener(this);
        pnlNorte.add(btnRemover);
        
        this.add(pnlNorte, BorderLayout.NORTH);
        
        pnlCentro = new JPanel();
        layoutBorderCentro = new BorderLayout();
        pnlCentro.setLayout(layoutBorderCentro);
        tabela = new JTable();
        
        modeloTabela = new DefaultTableModel(
          new String [] {
                  "Código", "Descrição", "Valor"}, 0);
          
        tabela.setModel(modeloTabela);
        
        scpCentro = new JScrollPane();
        scpCentro.setViewportView(tabela);
        pnlCentro.add(scpCentro, BorderLayout.CENTER);
        
        this.add(pnlCentro, BorderLayout.CENTER);
    }
    
    
    
    public void populaTable(String filtro) throws Exception{
        
        DefaultTableModel model =  (DefaultTableModel) tabela.getModel();//recuperacao do modelo da tabela

        model.setRowCount(0);//elimina as linhas existentes (reset na tabela)

      
                if(filtro == null){
                    Collection<MaoObra> listMaoObra =  controle.getConexaoJDBC().listMaoObras(null);

                    for(MaoObra m : listMaoObra){

                        model.addRow(new Object[]{m, //chame o toString
                                                  m.getDescricao(),
                                                  m.getValor()
                                                  });

                    }
                }else {
                    Collection<MaoObra> listMaoObra =  controle.getConexaoJDBC().listMaoObras(filtro);

                    for(MaoObra m : listMaoObra){

                        model.addRow(new Object[]{m, //chame o toString
                                                  m.getDescricao(),
                                                  m.getValor()
                                                  });

                    }
                }
            
    }
    

    @Override
    public void actionPerformed(ActionEvent ae) {
       if(ae.getActionCommand().equals(btnFiltro.getActionCommand())){

               try {
                if(txtFiltro.getText().trim().length() > 0){

                txtFiltro.setBorder(new LineBorder(Color.green,1));
                   populaTable(txtFiltro.getText());
                   
                   
               }else {

                    JOptionPane.showMessageDialog(this, "Informe pelo menos 1 digito", "Autenticação", JOptionPane.ERROR_MESSAGE);
                    txtFiltro.setBorder(new LineBorder(Color.red,1));
                    txtFiltro.requestFocus();                        

                }
               }catch (Exception ex) {
                  JOptionPane.showMessageDialog(this, "Atenção", "Erro ao acessar dados : "+ex.getLocalizedMessage(), JOptionPane.ERROR);
                  ex.printStackTrace();
               }

                    
            
                                    

        }else if(ae.getActionCommand().equals(btnRemover.getActionCommand())){
             int indice = tabela.getSelectedRow();//recupera a linha selecionada
            if(indice > -1){

                DefaultTableModel model =  (DefaultTableModel) tabela.getModel(); //recuperacao do modelo da table

                Vector linha = (Vector) model.getDataVector().get(indice);//recupera o vetor de dados da linha selecionada

                MaoObra o = (MaoObra) linha.get(0); //model.addRow(new Object[]{u, u.getNome(), ...

                try {
                    controle.getConexaoJDBC().remover(o);
                    JOptionPane.showMessageDialog(this, "Mao de Obra removida!", "Mao de Obra", JOptionPane.INFORMATION_MESSAGE);
                    populaTable(null); //refresh na tabela

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao remover Mao de Obra -:"+ex.getLocalizedMessage(), "Funcionario", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }                        

            }else{
                  JOptionPane.showMessageDialog(this, "Selecione uma linha para remover!", "Remoção", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    
    public void showTela(){
        
        try{
            
            
                
                this.populaTable(null);

            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Atenção", "Erro ao acessar dados : "+e.getLocalizedMessage(), JOptionPane.ERROR);
            e.printStackTrace();
        }
    
     }
    
}
