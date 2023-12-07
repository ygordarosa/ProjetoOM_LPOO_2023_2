/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.bcc.lpoo.om.gui.Servico;

import br.edu.ifsul.bcc.lpoo.om.Controle;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Ygor
 */
public class JPanelServicoFormulario extends JPanel{
    
   private Controle controle;
   private JPanelServico pnlServico;
   
    private BorderLayout borderLayout;
    private JPanel pnlCentro;
   
   private GridBagLayout gridBagLayout;
   private GridBagConstraints posicionador;
   
   private JLabel lblId;
   private TextField txfId;
   
   private JLabel lblValor;
   private TextField txfValor;
   
   private JLabel lblData_inicio;
   private TextField txfData_inicio;
   
   private JLabel lblData_fim;
   private TextField txfData_fim;
   
   private JLabel lblEquipe;
   private JComboBox cbxEquipe;
   private DefaultComboBoxModel modeloComboEquipe;
   
   private JLabel lblOrcamento;
   private JComboBox cbxOrcamento;
   private DefaultComboBoxModel modeloComboOrcamento;
    
    private JPanel pnlSul;
    private JButton btnSalvar;
    private JButton btnCancelar;
    
    public JPanelServicoFormulario(Controle controle, JPanelServico pnlServico){
        this.controle = controle;
        this.pnlServico = pnlServico;
    }
    
    private void InitComponents(){
        
        borderLayout = new BorderLayout();
        this.setLayout(borderLayout);
        
        pnlCentro = new JPanel();
        gridBagLayout = new GridBagLayout();
        pnlCentro.setLayout(gridBagLayout);
        
        lblId = new JLabel("Id:");
        txfId = new TextField(15);
        txfId.setEditable(false);
        
        lblValor = new JLabel("Valor:");
        txfValor = new TextField(15);
        
        lblData_inicio = new JLabel("Data Inicio:");
        txfData_inicio = new TextField(15);
        
        lblData_fim = new JLabel("Data Fim  :");
        txfData_fim = new TextField(15);
        
        lblEquipe = new JLabel("Equipe: ");
        cbxEquipe = new JComboBox();
        modeloComboEquipe = new DefaultComboBoxModel(new Object[]{"Selecione"});
        cbxEquipe.setModel(modeloComboEquipe);
    }
}
