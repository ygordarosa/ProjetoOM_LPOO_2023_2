package br.edu.ifsul.bcc.lpoo.om.gui.funcionario;
import br.edu.ifsul.bcc.lpoo.om.Controle;
import br.edu.ifsul.bcc.lpoo.om.model.Cargo;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author telmo
 */
public class JPanelFuncionarioFormulario extends JPanel implements ActionListener {

    private JPanel pnlDadosCadastrais;
    private JPanel pnlCentroDadosCadastrais;
    
    private GridBagLayout gridBagLayoutDadosCadastrais;
    
    private JLabel lblCPF;
    private JTextField txfCPF;
    
    private JLabel lblNome;
    private JTextField txfNome;
    
    
    private JLabel lblSenha;
    private JTextField txfSenha;
    
    private JLabel lblCargo;
    private JComboBox cbxCargo;
    
    private JLabel lblDataAdmissao;
    private JTextField txfDataAdmissao;
    
    private JLabel lblNumeroCTPS;
    private JTextField txfNumeroCTPS;
    
    
    
    
    
    private JPanel pnlSul;
    private JButton btnGravar;
    private JButton btnCancelar;
    
    
    public Controle controle;
    public JPanelFuncionario pnlFuncionario;

    public JPanelFuncionarioFormulario(Controle controle, JPanelFuncionario pnlFuncionario) {
        this.controle = controle;
        this.pnlFuncionario = pnlFuncionario;
    }
    
     public void populaComboCargo(){        
        cbxCargo.removeAllItems();//zera o combo
        DefaultComboBoxModel model =  (DefaultComboBoxModel) cbxCargo.getModel();
        model.addElement("Selecione"); //primeiro item                
        
        try{
            Collection<Cargo> clcCargos = controle.getConexaoJDBC().listCargo();
            for(Cargo c : clcCargos){
                model.addElement(c.getDescricao());
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Erro ao listar Cargos -:"+ex.getLocalizedMessage(), "Jogadores", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
     
     
     
    

    @Override
    public void actionPerformed(ActionEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    




}