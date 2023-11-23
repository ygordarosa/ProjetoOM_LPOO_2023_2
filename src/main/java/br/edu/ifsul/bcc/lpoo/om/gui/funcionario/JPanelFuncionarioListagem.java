package br.edu.ifsul.bcc.lpoo.om.gui.funcionario;
import br.edu.ifsul.bcc.lpoo.om.Controle;
import br.edu.ifsul.bcc.lpoo.om.model.Funcionario;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author telmo
 */
public class JPanelFuncionarioListagem extends JPanel implements ActionListener {
    
    private JPanel pnlNorte;
    private JLabel lblFiltro;
    private JTextField txtFiltro;
    private JButton btnFiltro;
    private FlowLayout layoutFlowNorte;
    

    private JPanel pnlCentro;
    private JScrollPane scpCentro;
    private DefaultTableModel modeloTabela;
    private JTable tabela;
    private BorderLayout layoutBorderCentro;
    
    
    private JPanel pnlSul;
    private JButton btnNovo;
    private JButton btnEditar;
    private JButton btnRemover;
    private FlowLayout layoutFlowSul;
    
    private BorderLayout layoutBorder;
    

    
    
    public Controle controle;
    public JPanelFuncionario pnlFuncionario;

    public JPanelFuncionarioListagem(Controle controle, JPanelFuncionario pnlFuncionario) {
        this.controle = controle;
        this.pnlFuncionario = pnlFuncionario;
        
        initComponents();
    }
    
    public void populaTable(){
        
        DefaultTableModel model =  (DefaultTableModel) tabela.getModel();//recuperacao do modelo da tabela

        model.setRowCount(0);//elimina as linhas existentes (reset na tabela)

        try {

            Collection<Funcionario> listFuncionarios = controle.getConexaoJDBC().listFuncionario();
            for(Funcionario f : listFuncionarios){
                                
                model.addRow(new Object[]{f,  //por ser apenas o objeto funcinario ele chama o toString()                                       
                                          f.getNome(), 
                                          });

            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this, "Erro ao listar Funcionarios -:"+ex.getLocalizedMessage(), "Jogadores", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }        
        
    }

    private void initComponents(){
        
        layoutBorder = new BorderLayout();
        this.setLayout(layoutBorder);//seta o gerenciado border para este painel
        
        layoutFlowNorte = new FlowLayout();
        
        pnlNorte = new JPanel();
        pnlNorte.setLayout(layoutFlowNorte);
        
        lblFiltro = new JLabel("Filtar por CPF: ");
        pnlNorte.add(lblFiltro);
        
        txtFiltro = new JTextField(11);
        pnlNorte.add(txtFiltro);
        
        btnFiltro = new JButton("Filtrar");
        btnFiltro.addActionListener(this);
        btnFiltro.setFocusable(true);    //acessibilidade    
        btnFiltro.setToolTipText("btnFiltrar"); //acessibilidade  
        btnFiltro.setActionCommand("botao_filtro");
        pnlNorte.add(btnFiltro);
        
        
        
        this.add(pnlNorte, BorderLayout.NORTH);
        
        
        
        
        tabela = new JTable();
        pnlCentro = new JPanel();
        layoutBorderCentro = new BorderLayout();
        pnlCentro.setLayout(layoutBorderCentro);
        
        
        modeloTabela = new DefaultTableModel(
        new String [] {"CPF", "Nome"}, 0);
        
        tabela.setModel(modeloTabela);
        
        
        scpCentro = new JScrollPane();
        scpCentro.setViewportView(tabela);
        pnlCentro.add(scpCentro, BorderLayout.CENTER);
        
        this.add(pnlCentro, BorderLayout.CENTER);
        
        layoutFlowSul = new FlowLayout();
        pnlSul = new JPanel();
        pnlSul.setLayout(layoutFlowSul);
        
        btnNovo = new JButton ("Novo");
        btnNovo.addActionListener(this);
        btnNovo.setFocusable(true);
        btnNovo.setToolTipText("btnNovo");
        btnNovo.setActionCommand("botao_novo");
        pnlSul.add(btnNovo);
        
        btnEditar = new JButton ("Editar");
        btnEditar.addActionListener(this);
        btnEditar.setFocusable(true);
        btnEditar.setToolTipText("btnEditar");
        btnEditar.setActionCommand("botao_editar");
        pnlSul.add(btnEditar);
        
        btnRemover = new JButton ("Remover");
        btnRemover.addActionListener(this);
        btnRemover.setFocusable(true);
        btnRemover.setToolTipText("btnRemover");
        btnRemover.setActionCommand("botao_remover");
        pnlSul.add(btnRemover);
        
        
        this.add(pnlSul, BorderLayout.SOUTH);

        
        
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}