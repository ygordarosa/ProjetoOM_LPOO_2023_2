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
import java.util.Collection;

/**
 *
 * @author Ygor
 */
public class PersistenceJDBC implements InterfacePersistence{
    
      private final String DRIVER = "org.postgresql.Driver";
    private final String USER = "postgres";
    private final String SENHA = "postgres";
    public static final String URL = "jdbc:postgresql://localhost:5432/db_om_lpoo_20232";
    private Connection con = null;
    

    public PersistenceJDBC() throws ClassNotFoundException, SQLException  {
        
        Class.forName(DRIVER); //carregamento do driver postgresql em tempo de execução
        System.out.println("Tentando estabelecer conexao JDBC com : "+URL+" ...");
            
        this.con = (Connection) DriverManager.getConnection(URL, USER, SENHA); 

    }

    
    @Override
    public Boolean conexaoAberta() {
         
        try {
            if(con != null)
                return !con.isClosed();//verifica se a conexao está aberta
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void fecharConexao() {
        try{                               
            this.con.close();//fecha a conexao.
            System.out.println("Fechou conexao JDBC");
        }catch(SQLException e){            
            e.printStackTrace();//gera uma pilha de erro na saida.
        } 
    }

    @Override
    public Object find(Class c, Object id) throws Exception {
       if(c == Cargo.class){
           PreparedStatement ps = this.con.prepareStatement(""
           + "select id, descricao from tb_cargo where id = ?"); //usado para comandos sql.
           
           ps.setInt(1, Integer.valueOf(id.toString()));
           
           ResultSet rs = ps.executeQuery();
           

           if(rs.next()){
               Cargo crg = new Cargo();
               crg.setId(rs.getInt("id"));//atribui o id do sql para o id do cargo;
               crg.setDescricao(rs.getString("descricao")); //atribui a descricao do sql para o id do cargo;
               return crg;
           }
           ps.close();
       }else if(c == Curso.class){
           
       }    
       return null;
    }

    @Override
    public void persist(Object o) throws Exception {
      if(o instanceof Cargo){
          Cargo crg = (Cargo) o;
          if(crg.getId() == null){
              // executar um insert
              PreparedStatement ps = this.con.prepareStatement("insert into tb_cargo values (nextval('seq_cargo_id'),?) returning id");
              ps.setString(1, crg.getDescricao());
             ResultSet rs = ps.executeQuery();
             if(rs.next()){
                 crg.setId(rs.getInt("id"));
             }
              ps.close();
          }else {
              //executar um update
               PreparedStatement ps = this.con.prepareStatement("update tb_cargo set descricao = ? where id = ?");
              ps.setString(1, crg.getDescricao());
              ps.setInt(2,crg.getId());
              
              ps.execute();
              ps.close();
          }
      }else if(o instanceof Curso){
          
      }else if(o instanceof Funcionario){
          
      }
    }

    @Override
    public void remover(Object o) throws Exception {
        if(o instanceof Cargo){
          Cargo crg = (Cargo) o;
          
              PreparedStatement ps = this.con.prepareStatement("delete from tb_cargo where id = ?;");
              ps.setInt(1, crg.getId());
              ps.execute();
              ps.close();
         
      }else {
            
        }
    }

    @Override
    public Collection<Cargo> listCargo() throws Exception {
        Collection<Cargo> crg = null;
        
        PreparedStatement ps = this.con.prepareStatement("select * from tb_cargo order by id asc");

        ResultSet rs = ps.executeQuery();
           
        crg = new ArrayList();

        while(rs.next()){
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
