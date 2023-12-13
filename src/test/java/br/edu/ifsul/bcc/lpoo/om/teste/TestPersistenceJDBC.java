/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.bcc.lpoo.om.teste;

import br.edu.ifsul.bcc.lpoo.om.model.Cargo;
import br.edu.ifsul.bcc.lpoo.om.model.Cliente;
import br.edu.ifsul.bcc.lpoo.om.model.Curso;
import br.edu.ifsul.bcc.lpoo.om.model.Funcionario;
import br.edu.ifsul.bcc.lpoo.om.model.MaoObra;
import br.edu.ifsul.bcc.lpoo.om.model.Veiculo;
import br.edu.ifsul.bcc.lpoo.om.model.dao.PersistenceJDBC;
import br.edu.ifsul.bcc.lpoo.om.model.dao.PersistenceJPA;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
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
    
    

        // Atividade 28/09
    
       // Criar um método de teste para funcionario
    //@Test
    public void testListFuncionarioJDBC() throws Exception{
        PersistenceJDBC jdbc = new PersistenceJDBC();
        Collection<Funcionario> fcl = null;
        if(jdbc.conexaoAberta()){
        //Passo 1: recuperar a colecao de funcionarios

            fcl = jdbc.listFuncionario();
        //Passo 2: caso a coleção não esteja vazia - imprimir (inclusive os cursos),
        //alterar e remover cada item.
            if(!fcl.isEmpty()){
                System.out.println("entrou");
                
                for(Funcionario f : fcl){
                    System.out.println("Cpf: " + f.getCpf());
                    System.out.println("Nome: " + f.getNome());
                    System.out.println("Cep: " + f.getCep());
                    System.out.println("Cargo id: " + f.getCargo().getId());
                    System.out.println("Cargo descricao: " + f.getCargo().getDescricao());
                    for(Curso curs : f.getCurso()){
                        System.out.println("Curso id: " + curs.getId());
                        System.out.println("Curso descricação: " + curs.getDescricao());
                        System.out.println("curso  carga horária" + curs.getCargaHoraria());
                    }
                }
                for(Funcionario f : fcl){
                    f.setNome("outro nome");
                    f.setCep("10203040");
                    }
                System.out.println("Dados alterados com sucesso");
                for(Funcionario f : fcl){
                    System.out.println("removendo o cpf: " + f.getCpf());
                    jdbc.remover(f);
                    }
                }
                else {
                Funcionario f = new Funcionario();
                Funcionario f1 = new Funcionario();
                
                f.setTipo("F");
                f.setCpf("04960317086");
                f.setNome("Ygor da Rosa da Rosa");
                f.setData_nascimento(Calendar.getInstance());
                f.setSenha("12345");
                f.setCep("99020530");
                f.setComplemento("estádio ao lado");
                f.setNumero_ctps("1234567890");
                
                Cargo c = new Cargo();
                c.setDescricao("estágiario");
                jdbc.persist(c);
                f.setCargo(c);
                
                Curso curs = new Curso();
                curs.setDescricao("descrito");
                curs.setCargaHoraria(190);
                curs.setDt_conclusao(Calendar.getInstance());
                jdbc.persist(curs);
                f.setCursos(curs);
                
                jdbc.persist(f);
                
                System.out.println("Criado funcionario 1");
                
                f1.setTipo("F");
                f1.setCpf("67241018087");
                f1.setNome("Juliana da Rosa");
                f1.setData_nascimento(Calendar.getInstance());
                f1.setSenha("54321");
                f1.setCep("99020530");
                f1.setComplemento("estádio ao lado");
                f1.setNumero_ctps("1234567890");
                
                Cargo c1 = new Cargo();
                c1.setDescricao("dono");
                jdbc.persist(c1);
                f1.setCargo(c1);
                
                Curso curs1 = new Curso();
                curs1.setDescricao("descri");
                curs1.setCargaHoraria(190);
                curs1.setDt_conclusao(Calendar.getInstance());
                jdbc.persist(curs1);
                f1.setCursos(curs1);
                
                jdbc.persist(f1);
                
                System.out.println("criado funcionario 2");
                 
                }
            }
            
        }

    
         
          //Passo 3: caso a coleção esteja vazia, criar dois funcionarios com um curso cada.
    
       // @Test
     public void testPersistFuncionarioJDBC() throws Exception {
        PersistenceJDBC jdbc = new PersistenceJDBC();

        if (jdbc.conexaoAberta()) {
            Funcionario f = new Funcionario();
            
            f.setTipo("F");
            f.setCpf("04960317086");
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
    
    
    
    
    
    
    
    //@Test
    public void TestePersistenciaJDBC() throws Exception{
        PersistenceJDBC jdbc = new PersistenceJDBC();

        Collection<Cliente> cl = null;

        if (jdbc.conexaoAberta()) {
            
            cl = jdbc.listCliente();
            
            if(!cl.isEmpty()){
                
                SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");        

                
                
                for(Cliente cli : cl){
                    System.out.println("cpf: " + cli.getCpf());
                    System.out.println("Nome: " + cli.getNome());
                    System.out.println("Cep: " + cli.getCep());
                    System.out.println("Data Nascimento: " + formatadorData.format(cli.getData_nascimento().getTime()));
                    for(Veiculo vei : cli.getVeiculo()){
                        System.out.println("placa: " + vei.getPlaca());
                        System.out.println("Ano: " + vei.getAno());
                        System.out.println("Data Ultimo Servico: " + formatadorData.format(vei.getDataUltimoServico().getTime()));
                        System.out.println("Modelo: " + vei.getModelo());
                    }
                }
                
                for(Cliente cli : cl){
                    System.out.println("removendo o cpf: " + cli.getCpf());
                    jdbc.remover(cli);
                }
                
            }else{
                Cliente c = new Cliente();
            
            c.setTipo("C");
            c.setCpf("67241018089");
            c.setCep("99020530");
            c.setComplemento("Ao lado do antigo estádio delmar sitoni");
            c.setData_nascimento(Calendar.getInstance());
            c.setNome("Juliana da Rosa");
            c.setSenha("bistoco");
            
            TestPersistenceJPA jpa = new TestPersistenceJPA();
            
            Veiculo vei = jpa.testCreateVeiculo();
            if(vei != null){
                jdbc.persist(vei);
            }
            c.setVeiculos(vei);
            jdbc.persist(c);
            System.out.println("criado cliente com sucesso com cpf: " + c.getCpf());
            
             
            }
           

            jdbc.fecharConexao();
        }
    }
    
    
     @Test
    public void testPersistenciaMaoObraJDBC() throws Exception {
        //criar um objeto do tipo PersistenciaJPA.
        PersistenceJDBC jdbc = new PersistenceJDBC();
        if(jdbc.conexaoAberta()){

            //Passo 1: encontrar o cargo id = 1
            List<MaoObra>  list=  (ArrayList) jdbc.listMaoObras("ote");
            if(list != null && !list.isEmpty()){
                for(MaoObra m : list){
                    System.out.println("MaoObra: "+m.getId() + " removendo ...");
                    m.setDescricao("alterado");
                    jdbc.persist(m);
                    jdbc.remover(m);

                }
            }else{
                 System.out.println("Não encontro a mao de obra, criando um novo ...");
                 MaoObra mb = new MaoObra();
                 mb.setDescricao("teste");
                 mb.setValor(100f);
                 SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");                
                 mb.setTempo_estimado_execucao(sdf.parse("2:30"));
                 jdbc.persist(mb);
                 
                 MaoObra mb1 = new MaoObra();
                 mb1.setDescricao("ygor");
                 mb1.setValor(100f);
                 sdf = new SimpleDateFormat("HH:mm");                
                 mb1.setTempo_estimado_execucao(sdf.parse("2:30"));
                 jdbc.persist(mb1);

            }
        }else{
            System.out.println("nao conectou no BD via jdbc ...");
            //atribuir uma instancia para o cg
            //setar a descricao
            //persistir no banco de dados.
        }
    }
    
    
    
    

}
