/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.bcc.lpoo.om.gui.Servico;

import br.edu.ifsul.bcc.lpoo.om.Controle;
import br.edu.ifsul.bcc.lpoo.om.model.Servico;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ygor
 */
public class JPanelServicoListagem extends JPanel implements ActionListener {
    
   private Controle controle;
   private JPanelServico pnlServico;
   
   private JPanel pnlNorte;
   private JLabel lblFiltro;
   private JTextField txfFiltro;
   private JButton btnFiltro;
   private FlowLayout layoutFlowNorte;
   
   
   private JPanel pnlCentro;
   private JScrollPane scpCentro;
   private DefaultTableModel tableModel;
   private JTable table;
   private BorderLayout borderLayoutCentro;
   
   private JPanel pnlSul;
   private JButton btnNovo;
   private JButton btnEditar;
   private JButton btnRemover;
   private FlowLayout layoutFlowSul;
   
   private BorderLayout borderLayout;


    
    
    public JPanelServicoListagem(Controle controle, JPanelServico pnlServico){
        this.controle = controle;
        this.pnlServico = pnlServico;
        
        InitComponents();
    }
    
    private void InitComponents(){
        borderLayout = new BorderLayout();
        this.setLayout(borderLayout);
        
        pnlCentro = new JPanel();
        borderLayoutCentro = new BorderLayout();
        pnlCentro.setLayout(borderLayoutCentro);
        
        table = new JTable();
        tableModel = new DefaultTableModel(
        new String[]{
        "ID","Valor"},0);
        table.setModel(tableModel);
        
        scpCentro = new JScrollPane();
        scpCentro.setViewportView(table);
        pnlCentro.add(scpCentro, BorderLayout.CENTER);
        
        this.add(pnlCentro, BorderLayout.CENTER);
        
        pnlSul = new JPanel();
        layoutFlowSul = new FlowLayout();
        pnlSul.setLayout(layoutFlowSul);
        
        btnNovo = new JButton("Novo");
        btnNovo.setActionCommand("comando_novo");
        btnNovo.addActionListener(this);
        pnlSul.add(btnNovo);
        
        btnEditar = new JButton("Editar");
        btnEditar.setActionCommand("comando_editar");
        btnEditar.addActionListener(this);
        pnlSul.add(btnEditar);
        
        btnRemover = new JButton("Remover");
        btnRemover.setActionCommand("comando_remover");
        btnRemover.addActionListener(this);
        pnlSul.add(btnRemover);
        
        this.add(pnlSul, BorderLayout.SOUTH);
    }

    public void populaTable() throws Exception{
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        System.out.println("entrou popula table");
        model.setRowCount(0);
        
        
        Collection<Servico> listServico = controle.getConexaoJDBC().listServico();
        for(Servico s : listServico){
            
            model.addRow(new Object[]{s,
                s.getValor()
            });
        }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equals(btnNovo.getActionCommand())){
            
            pnlServico.showTela("tela_servico_formulario");            
            
            pnlServico.getFormulario();
                  //  setFuncionarioFormulario(null); //limpando o formulário.                        

        }
    }
    
}
