/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.bcc.lpoo.om.gui.Servico;

import br.edu.ifsul.bcc.lpoo.om.Controle;
import br.edu.ifsul.bcc.lpoo.om.model.Equipe;
import br.edu.ifsul.bcc.lpoo.om.model.Funcionario;
import br.edu.ifsul.bcc.lpoo.om.model.Orcamento;
import br.edu.ifsul.bcc.lpoo.om.model.Servico;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Ygor
 */
public class JPanelServicoFormulario extends JPanel implements ActionListener{
    
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
        
        InitComponents();
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
        
         lblOrcamento = new JLabel("Orçamento: ");
        cbxOrcamento = new JComboBox();
        modeloComboOrcamento = new DefaultComboBoxModel(new Object[]{"Selecione"});
        cbxOrcamento.setModel(modeloComboEquipe);
        
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;
        posicionador.gridx = 0;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_END;
        pnlCentro.add(lblId, posicionador);
        
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;
        posicionador.gridx = 1;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_END;
        pnlCentro.add(txfId, posicionador);
        
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;
        posicionador.gridx = 0;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_END;
        pnlCentro.add(lblValor, posicionador);
        
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;
        posicionador.gridx = 1;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlCentro.add(txfValor, posicionador);
        
        
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;
        posicionador.gridx = 0;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_END;
        pnlCentro.add(lblData_inicio, posicionador);
        
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;
        posicionador.gridx = 1;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlCentro.add(txfData_inicio, posicionador);
        
        posicionador = new GridBagConstraints();
        posicionador.gridy = 3;
        posicionador.gridx = 0;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_END;
        pnlCentro.add(lblData_fim, posicionador);
        
        posicionador = new GridBagConstraints();
        posicionador.gridy = 3;
        posicionador.gridx = 1;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlCentro.add(txfData_fim, posicionador);
        
        posicionador = new GridBagConstraints();
        posicionador.gridy = 4;
        posicionador.gridx = 0;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_END;
        pnlCentro.add(lblEquipe, posicionador);
 
        posicionador = new GridBagConstraints();
        posicionador.gridy = 4;
        posicionador.gridx = 1;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlCentro.add(cbxEquipe, posicionador);  
        
          posicionador = new GridBagConstraints();
        posicionador.gridy = 5;
        posicionador.gridx = 0;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_END;
        pnlCentro.add(lblOrcamento, posicionador);
 
        posicionador = new GridBagConstraints();
        posicionador.gridy = 5;
        posicionador.gridx = 1;
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;
        pnlCentro.add(cbxOrcamento, posicionador);  
        
        this.add(pnlCentro, BorderLayout.CENTER);
        
        
        pnlSul = new JPanel();
        btnSalvar = new JButton("Salvar");
        btnSalvar.setActionCommand("botao_salvar");
        btnSalvar.addActionListener(this);
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setActionCommand("botao_cancelar");
        btnCancelar.addActionListener(this);
        pnlSul.setLayout(new FlowLayout());
        pnlSul.add(btnSalvar);
        pnlSul.add(btnCancelar);
        
        this.add(pnlSul, BorderLayout.SOUTH);
    }
    
    public Servico getServicoFormulario(){
        
        if(txfId.getText().trim().length() > 0 &&
           txfValor.getText().trim().length() > 0  &&
           txfData_inicio.getText().trim().length() > 5 &&
           cbxEquipe.getSelectedIndex() > 0 &&
                cbxOrcamento.getSelectedIndex() > 0){
            
            Servico s = new Servico();
                s.setId(Integer.valueOf(txfId.getText().trim()));
                s.setValor(Float.valueOf(txfValor.getText().trim()));
                s.setEquipe((Equipe) cbxEquipe.getSelectedItem());
                s.setOrcamento((Orcamento) cbxOrcamento.getSelectedItem());

                if(txfData_inicio.getText().trim().length() > 0){
                    s.setData_inicio(txfData_inicio.getText().trim());
                }
                
                if(txfData_fim.getText().trim().length() > 0){
                    s.setData_fim(txfData_fim.getText().trim());
                }
                
            return s;
                
        }
        
        return null;
    }
    
    public void setServicoFormulario(Servico s){
        if(s == null){
            txfId.setText("");
            txfValor.setText("");
            txfData_inicio.setText("");
            txfData_fim.setText("");
            cbxEquipe.setSelectedIndex(0);
        }else{
            txfId.setText(String.valueOf(s.getId()));
            txfValor.setText(String.valueOf(s.getValor()));
            txfData_inicio.setText(String.valueOf(s.getData_inicio_string()));
            txfData_fim.setText(String.valueOf(s.getData_fim_string()));
            cbxEquipe.setSelectedItem(s.getEquipe());
            cbxOrcamento.setSelectedItem(s.getOrcamento());

                    
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equals(btnCancelar.getActionCommand())){
            
            pnlServico.showTela("tela_servico_listagem");
            
        }else if(ae.getActionCommand().equals(btnSalvar.getActionCommand())){
            
           Servico s = getServicoFormulario();//recupera os dados do formulario
            
            if(s != null){

                try {
                    
                    pnlServico.getControle().getConexaoJDBC().persist(s);
                    
                    JOptionPane.showMessageDialog(this, "Servico armazenado!", "Salvar", JOptionPane.INFORMATION_MESSAGE);
                    
                    pnlServico.showTela("tela_servico_listagem");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao salvar Servico! : "+ex.getMessage(), "Salvar", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }

            }else{

                JOptionPane.showMessageDialog(this, "Preencha o formulário!", "Edição", JOptionPane.INFORMATION_MESSAGE);
            }
            
        }
    }
}
