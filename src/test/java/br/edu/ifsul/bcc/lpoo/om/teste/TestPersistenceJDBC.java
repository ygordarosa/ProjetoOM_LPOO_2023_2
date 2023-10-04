/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.bcc.lpoo.om.teste;

import br.edu.ifsul.bcc.lpoo.om.model.Cargo;
import br.edu.ifsul.bcc.lpoo.om.model.Curso;
import br.edu.ifsul.bcc.lpoo.om.model.Funcionario;
import br.edu.ifsul.bcc.lpoo.om.model.dao.PersistenceJDBC;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import org.junit.Test;

/**
 *
 * @author Ygor
 */
public class TestPersistenceJDBC {

    public void testConexaoGeracaoTabelas() throws Exception {

        PersistenceJDBC persistencia = new PersistenceJDBC();
        if (persistencia.conexaoAberta()) {
            System.out.println("abriu a conexao com o BD via JDBC");

            persistencia.fecharConexao();

        } else {
            System.out.println("Nao abriu a conexao com o BD via JDBC");
        }

    }

    public void testFechar() throws ClassNotFoundException, SQLException {
        PersistenceJDBC jdbc = new PersistenceJDBC();

        if (jdbc.conexaoAberta()) {

            jdbc.fecharConexao();
        }
    }

    public void testFindCargoJDBC() throws Exception {
        PersistenceJDBC jdbc = new PersistenceJDBC();

        if (jdbc.conexaoAberta()) {
            Cargo c;
            c = (Cargo) jdbc.find(Cargo.class, 1);
            if (c != null) {
                c.setDescricao("foi po");
                jdbc.persist(c);
                System.out.println("mudou um valor com sucesso");
                System.out.println("Cargo: id - " + c.getId() + " descricao - " + c.getDescricao());
            } else {
                c = new Cargo();
                c.setDescricao("foi po");
                jdbc.persist(c);
                System.out.println("criou um valor com sucesso");
            }
            jdbc.fecharConexao();
        }

    }

   // @Test
    public void testListCargoJDBC() throws Exception {
        PersistenceJDBC jdbc = new PersistenceJDBC();
        Collection<Cargo> crg = null;
        if (jdbc.conexaoAberta()) {
            //Passo 1: Recuperar coleção de cargos
            crg = jdbc.listCargo();
            Integer cont = 0;
            if (!crg.isEmpty()) {
                //Passo 2: Caso a coleção não esteja vazia imprimir, alterar e remover.
                for (Cargo carg : crg) {
                    System.out.println("Id: " + carg.getId());
                    System.out.println("Descricao: " + carg.getDescricao() + "\n");
                }
                for (Cargo carg : crg) {
                    cont++;
                    carg.setDescricao("mudou " + cont);

                    jdbc.persist(carg);
                }
              
                for (Cargo carg : crg) {
                    System.out.println("Id: " + carg.getId());
                    System.out.println("Descricao: " + carg.getDescricao() + "\n");
                }
                System.out.println("Removendo os seguintes cargos:");

                for (Cargo carg : crg) {
                    System.out.println("Id: " + carg.getId());
                    System.out.println("Descricao: " + carg.getDescricao());

                    jdbc.remover(carg);
                }
            } else {
                //Passo 3: Caso esteja vazia criar 2 Cargos e imprimir.
                Cargo cargo = new Cargo();
                Cargo cargo1 = new Cargo();
                System.out.println("Lista vazia criando elementos.");
                cargo.setDescricao("recriando " + ++cont);
                jdbc.persist(cargo);
                
                System.out.println("Id: " + cargo.getId());
                System.out.println("Descricao: " + cargo.getDescricao() + "\n");

                
                
                cargo1.setDescricao("recriando " + ++cont);
                jdbc.persist(cargo1);
                
                System.out.println("Id: " + cargo1.getId());
                System.out.println("Descricao: " + cargo1.getDescricao() + "\n");
                

            }
        }
        jdbc.fecharConexao();
    }
    
    
   // @Test
     public void testPersistFuncionarioJDBC() throws Exception {
        PersistenceJDBC jdbc = new PersistenceJDBC();

        if (jdbc.conexaoAberta()) {
            Funcionario f = new Funcionario();
            
            f.setTipo("F");
            f.setCpf("67241018088");
            f.setCep("99020530");
            f.setComplemento("Ao lado do antigo estádio delmar sitoni");
            f.setData_nascimento(Calendar.getInstance());
            f.setNome("Juliana da Rosa");
            f.setSenha("bistoco");
            f.setNumero_ctps("1234567899");
            
            Cargo c = new Cargo();
            c.setDescricao("Dono");
            jdbc.persist(c);
            f.setCargo(c);
            
            Curso cur = new Curso();
            cur.setCargaHoraria(160);
            cur.setDescricao("curso de gestão");
            cur.setDt_conclusao(Calendar.getInstance());
            jdbc.persist(cur);
            f.setCursos(cur);
            jdbc.persist(f);
            System.out.println("criado funcionario com sucesso");
            
            
            f.setNome("teste123");
            jdbc.persist(f);
            System.out.println("atualizado funcionario com sucesso");

            jdbc.fecharConexao();
        }

    }
        // Atividade 28/09
    /*
        Criar um método de teste para funcionario
            Passo 1: recuperar a colecao de funcionarios
            Passo 2: caso a coleção não esteja vazia - imprimir (inclusive os cursos),
            alterar e remover cada item.
            Passo 3: caso a coleção esteja vazia, criar dois funcionarios com um curso cada.
    */
    
    
    
    
    
    
    
    
    
    
    
    
    

}
