/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.bcc.lpoo.om.model.dao;

import br.edu.ifsul.bcc.lpoo.om.model.Cargo;
import br.edu.ifsul.bcc.lpoo.om.model.Curso;
import br.edu.ifsul.bcc.lpoo.om.model.Funcionario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

/**
 *
 * @author Ygor
 */
public class PersistenceJDBC implements InterfacePersistence {

    private final String DRIVER = "org.postgresql.Driver";
    private final String USER = "postgres";
    private final String SENHA = "postgres";
    public static final String URL = "jdbc:postgresql://localhost:5432/db_om_lpoo_20232";
    private Connection con = null;

    public PersistenceJDBC() throws ClassNotFoundException, SQLException {

        Class.forName(DRIVER); //carregamento do driver postgresql em tempo de execução
        System.out.println("Tentando estabelecer conexao JDBC com : " + URL + " ...");

        this.con = (Connection) DriverManager.getConnection(URL, USER, SENHA);

    }

    @Override
    public Boolean conexaoAberta() {

        try {
            if (con != null) {
                return !con.isClosed();//verifica se a conexao está aberta
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void fecharConexao() {
        try {
            this.con.close();//fecha a conexao.
            System.out.println("Fechou conexao JDBC");
        } catch (SQLException e) {
            e.printStackTrace();//gera uma pilha de erro na saida.
        }
    }

    @Override
    public Object find(Class c, Object id) throws Exception {
        if (c == Cargo.class) {
            PreparedStatement ps = this.con.prepareStatement(""
                    + "select id, descricao from tb_cargo where id = ?"); //usado para comandos sql.

            ps.setInt(1, Integer.valueOf(id.toString()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Cargo crg = new Cargo();
                crg.setId(rs.getInt("id"));//atribui o id do sql para o id do cargo;
                crg.setDescricao(rs.getString("descricao")); //atribui a descricao do sql para o id do cargo;
                return crg;
            }
            ps.close();
        } else if (c == Curso.class) {

        }
        return null;
    }

    @Override
    public void persist(Object o) throws Exception {
        if (o instanceof Cargo) {
            Cargo crg = (Cargo) o;
            if (crg.getId() == null) {
                // executar um insert
                PreparedStatement ps = this.con.prepareStatement("insert into tb_cargo values (nextval('seq_cargo_id'),?) returning id");
                ps.setString(1, crg.getDescricao());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    crg.setId(rs.getInt("id"));
                }
                ps.close();
            } else {
                //executar um update
                PreparedStatement ps = this.con.prepareStatement("update tb_cargo set descricao = ? where id = ?");
                ps.setString(1, crg.getDescricao());
                ps.setInt(2, crg.getId());

                ps.execute();
                ps.close();
            }
        } else if (o instanceof Curso) {
            Curso curs = (Curso)o;
                if(curs.getId() == null){
                     PreparedStatement ps = this.con.prepareStatement("insert into tb_curso "
                                                             + "(id, cargahoraria, descricao, dt_conclusao) "
                                                             + "values (nextval('seq_curso_id'), ?, ?, ?)"
                                                             + "returning id");
                ps.setInt(1, curs.getCargaHoraria());
                ps.setString(2, curs.getDescricao());
                ps.setDate(3, new java.sql.Date(curs.getDt_conclusao().getTimeInMillis()));
                
                 ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    curs.setId(rs.getInt("id"));
                }
                ps.close();
                
                }
                else {
                //executar um update
                
                
                PreparedStatement ps = this.con.prepareStatement("update tb_curso set cargahoraria = ? descricao = ?"
                                                                + "dt_conclusao = ?"
                                                                + " where id = ?");
                ps.setInt(1, curs.getCargaHoraria());
                ps.setString(2, curs.getDescricao());
                ps.setDate(3, new java.sql.Date(curs.getDt_conclusao().getTimeInMillis()));
                ps.setInt(4, curs.getId());

                ps.execute();
                ps.close();
            }
                
        } else if (o instanceof Funcionario) {
            Funcionario func = (Funcionario) o;
            if (func.getData_admissao() == null) {

                //insert tb_pessoa DONE
                PreparedStatement ps = this.con.prepareStatement("insert into tb_pessoa (tipo, cpf, cep, complemento, "
                                                                + "data_nascimento, nome, senha) "
                                                                + "values (?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, func.getTipo());
                ps.setString(2, func.getCpf());
                ps.setString(3, func.getCep());
                ps.setString(4, func.getComplemento());
                ps.setDate(5, new java.sql.Date(func.getData_nascimento().getTimeInMillis()));
                ps.setString(6, func.getNome());
                ps.setString(7, func.getSenha());

                ps.execute();
                ps.close();

                //insert tb_funcionario
                PreparedStatement ps2 = this.con.prepareStatement("insert into tb_funcionario (data_admmissao, data_demissao, numero_ctps, "
                                                                   + "cpf, cargo_id)"
                                                                   + " values (now(), ?, ?, ?, ?)"
                                                                   + " returning data_admmissao");

                if (func.getData_demissao() != null) {
                    ps2.setDate(1, new java.sql.Date(func.getData_demissao().getTimeInMillis()));
                } else {
                    ps2.setDate(1, null);
                }
                ps2.setString(2, func.getNumero_ctps());
                ps2.setString(3, func.getCpf());
                ps2.setInt(4, func.getCargo().getId());

                ResultSet rs = ps2.executeQuery();

                if (rs.next()) {
                    
                    Calendar dt_adm = Calendar.getInstance();
                    dt_adm.setTimeInMillis(rs.getDate("data_admmissao").getTime());
                    func.setData_admissao(dt_adm);

                    //se necessário o insert em tb_funcionario_curso
                    if (!func.getCurso().isEmpty()) {

                        for (Curso crs : func.getCurso()) {
                            
                            PreparedStatement psc = this.con.prepareStatement("insert into tb_funcionario_curso "
                                                                            + "(funcionario_cpf, curso_id) values (?,?) ");

                            psc.setString(1, func.getCpf());
                            psc.setInt(2, crs.getId());

                            psc.execute();
                            psc.close();
                        }
                    }

                }
                ps2.close();

            } else {
                
                 //update tb_pessoa done
                     PreparedStatement ps = this.con.prepareStatement("update tb_pessoa set tipo = ?,  cep = ?, complemento = ?, "
                                                                + "data_nascimento = ?, nome = ?, senha = ? "
                                                                + "where cpf = ?");
                     
                    ps.setString(1, func.getTipo());
                    ps.setString(2, func.getCep());
                    ps.setString(3, func.getComplemento());
                    ps.setDate(4, new java.sql.Date(func.getData_nascimento().getTimeInMillis()));
                    ps.setString(5, func.getNome());
                    ps.setString(6, func.getSenha());
                    ps.setString(7, func.getCpf());
                    
                     ps.execute();
                     ps.close();
                    
                     
                      //update tb_funcionario done
                     PreparedStatement ps2 = this.con.prepareStatement("update tb_funcionario set data_admmissao = ?, data_demissao = ?, numero_ctps = ?, "
                                                                   + " cargo_id = ?"
                                                                   + " where cpf = ?");

                     
                ps2.setDate(1, new java.sql.Date(func.getData_admissao().getTimeInMillis()));
                if (func.getData_demissao() != null) {
                    ps2.setDate(2, new java.sql.Date(func.getData_demissao().getTimeInMillis()));
                } else {
                    ps2.setDate(2, null);
                }
                ps2.setString(3, func.getNumero_ctps());
                ps2.setInt(4, func.getCargo().getId());
                ps2.setString(5, func.getCpf());
                
                ps2.execute();
                ps2.close();

                //atualizar os respectivos registros em tb_funcionario_curso para o func
                
                 //passo 1 - remove todos os cursos do funcionario na tabela associativa 
                     PreparedStatement ps3 = 
                          this.con.prepareStatement("delete from tb_funcionario_curso where funcionario_cpf = ?");
                     ps3.setString(1, func.getCpf());
                     ps3.execute();
                     
                    //passo 2: insere novamente, caso necessario. 
                    if (!func.getCurso().isEmpty()){
                            
                            for(Curso crs : func.getCurso()){
                                
                                PreparedStatement ps4 = this.con.prepareStatement("insert into tb_funcionario_curso "
                                                                                + "(funcionario_cpf, curso_id) "
                                                                                + "values "
                                                                                + "(?, ?)");
                                ps4.setString(1, func.getCpf());
                                ps4.setInt(2, crs.getId());
                                
                                ps4.execute();
                                ps4.close();
                            }
                            
                        }
            }

        }
    }

    @Override
    public void remover(Object o) throws Exception {
        if (o instanceof Cargo) {
            Cargo crg = (Cargo) o;

            PreparedStatement ps = this.con.prepareStatement("delete from tb_cargo where id = ?;");
            ps.setInt(1, crg.getId());
            ps.execute();
            ps.close();

        } else if (o instanceof Funcionario) {
            
            Funcionario f = (Funcionario) o;
            
            //removendo da tabela associativa primeiro
            PreparedStatement ps1 = this.con.prepareStatement("delete from tb_funcionario_curso where funcionario_cpf = ?;");
            ps1.setString(1, f.getCpf());
            ps1.execute();
            ps1.close();
            
            
            //removendo linhas da tabela funcionario
            PreparedStatement ps2 = this.con.prepareStatement("delete from tb_funcionario where cpf = ?;");
            ps2.setString(1, f.getCpf());
            ps2.execute();
            ps2.close();
            
            //removendo linhas da tablea
            PreparedStatement ps3 = this.con.prepareStatement("delete from tb_pessoa where cpf = ?;");
            ps3.setString(1, f.getCpf());
            ps3.execute();
            ps3.close();
        }
    }

    @Override
    public Collection<Cargo> listCargo() throws Exception {
        Collection<Cargo> crg = null;

        PreparedStatement ps = this.con.prepareStatement("select * from tb_cargo order by id asc");

        ResultSet rs = ps.executeQuery();

        crg = new ArrayList();

        while (rs.next()) {
            Cargo carg = new Cargo();
            carg.setId(rs.getInt("id"));//atribui o id do sql para o id do cargo;
            carg.setDescricao(rs.getString("descricao")); //atribui a descricao do sql para o id do cargo;
            crg.add(carg);
        }

        ps.close();

        return crg;
    }

    @Override
    public Collection<Funcionario> listFuncionario() throws Exception {
        Collection<Funcionario> fcl = null;
        
        PreparedStatement ps = this.con.prepareStatement("select p.cpf, p.nome, p.senha, "
                                                        + "p.data_nascimento, p.cep, p.complemento, f.numero_ctps, "
                                                        + "f.data_admmissao, f.data_demissao, c.id, c.descricao "
                                                        + "from tb_pessoa p, tb_funcionario f, tb_cargo c where "
                                                        + "p.cpf = f.cpf and f.cargo_id = c.id order by cpf asc ");
        
        ResultSet rs = ps.executeQuery();
        
        fcl = new ArrayList();
        
        while(rs.next()){
            Funcionario func = new Funcionario();
            func.setCpf(rs.getString("cpf"));
            func.setNome(rs.getString("nome"));
            func.setSenha(rs.getString("senha"));
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(rs.getDate("data_nascimento").getTime());
            func.setData_nascimento(c);
            func.setCep(rs.getString("cep"));
            func.setComplemento(rs.getString("complemento"));
            func.setNumero_ctps(rs.getString("numero_ctps"));
            c.setTimeInMillis(rs.getDate("data_admmissao").getTime());
            func.setData_admissao(c);
            if(rs.getDate("data_demissao") != null){
                Calendar d = Calendar.getInstance();
                d.setTimeInMillis(rs.getDate("data_demissao").getTime());
                func.setData_demissao(d);
            }
            else {
                func.setData_demissao(null);
            }
            
            
            Cargo carg = new Cargo();
            carg.setId(rs.getInt("id"));
            carg.setDescricao("descricao");
            func.setCargo(carg);
            
            PreparedStatement ps2 = this.con.prepareStatement("select c.id, c.descricao, c.dt_conclusao, c.cargahoraria"
                                                            + " from tb_curso c, tb_funcionario_curso tfc "
                                                            + " where c.id = tfc.curso_id and "
                                                            + "tfc.funcionario_cpf = ? order by c.id asc");
            
            ps2.setString(1, func.getCpf());
            
            ResultSet rs2 = ps2.executeQuery();
            
            while(rs2.next()){
                Curso curs = new Curso();
                
                curs.setId(rs2.getInt("id"));
                curs.setDescricao(rs2.getString("descricao"));
                c.setTimeInMillis(rs2.getDate("dt_conclusao").getTime());
                curs.setDt_conclusao(c);
                curs.setCargaHoraria(rs2.getInt("cargahoraria"));
                
                func.setCursos(curs);
                
            }
            rs2.close();
            ps2.close();
            
            
            
            fcl.add(func);

        }
        rs.close();
        
        ps.close();
        
        
        return fcl;
    }

}
