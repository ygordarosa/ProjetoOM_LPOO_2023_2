/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.bcc.lpoo.om.teste;

import br.edu.ifsul.bcc.lpoo.om.model.Cargo;
import br.edu.ifsul.bcc.lpoo.om.model.Curso;
import br.edu.ifsul.bcc.lpoo.om.model.Funcionario;
import br.edu.ifsul.bcc.lpoo.om.model.dao.PersistenceJPA;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import javax.swing.text.html.parser.DTDConstants;
import org.junit.Test;

/**
 *
 * @author Ygor
 */
public class TestPersistenceJPA {
    
     
    public void testConexaoGeracaoTabelas(){
        
        PersistenceJPA persistencia = new PersistenceJPA();
        if(persistencia.conexaoAberta()){
            System.out.println("abriu a conexao com o BD via JPA");
            
            persistencia.fecharConexao();
            
            System.out.println("fechou a conexao com o BD via JPA");
            
        }else{
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }
        
    } 
    
    
     public void testPersistenceCargoJPA() throws Exception{
        PersistenceJPA jpa = new PersistenceJPA();
        if(jpa.conexaoAberta()){
            
           
            Cargo c = (Cargo) jpa.find(Cargo.class, new Integer("1"));
                
            if(c != null){
                System.out.println("Id: " + c.getId());
                System.out.println("Descricao: " + c.getDescricao());
                jpa.persist(c);
                System.out.println("Alterado: " + c.getId() + c.getDescricao());
                jpa.remover(c);
                System.out.println("removido: " + c.getId() + c.getDescricao());
            }else {
                System.out.println("criando novo Cargo");
                c = new Cargo();
                c.setDescricao("abc");
                jpa.persist(c);
            }
               
            
            
            System.out.println("abriu a conexao com o BD via JPA");
            jpa.fecharConexao(); 
        }else{
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }
        
    }
     
     
     public void testPersistenceListCargoJPA() throws Exception{
        PersistenceJPA jpa = new PersistenceJPA();
        if(jpa.conexaoAberta()){
            //passo 1: recuperar a coleção de cargos
            Collection <Cargo> c = jpa.listCargo();
            if(!(c.isEmpty())){
                System.out.println(c);
                //passo 2: caso a colecao nao esteja vazia, imprimir os dados da selecao, alterar cada e remover cada item
                for(Cargo ch : c){
                    System.out.println("Id: " + ch.getId());
                    System.out.println("Descricao: " + ch.getDescricao());
                }
                for(Cargo ch : c){
                    ch.setDescricao("abcd");
                    jpa.persist(ch);
                }
                for(Cargo ch : c){
                    System.out.println("Id: " + ch.getId());
                    System.out.println("Descricao: " + ch.getDescricao());
                }
                for(Cargo ch : c){
                    jpa.remover(ch);
                }
                
            }else {
                System.out.println("2 Cargos criados");
                Cargo car = new Cargo(), asb = new Cargo();
                car.setDescricao("desc");
                jpa.persist(car);
                asb.setDescricao("descria");
                jpa.persist(asb);
            }

            System.out.println("abriu a conexao com o BD via JPA");
            jpa.fecharConexao(); 
        }else{
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }
        
    }
     
     //Com Erro
     @Test
     public void testPersistenceListFuncionarioJPA() throws Exception{
        PersistenceJPA jpa = new PersistenceJPA();
        if(jpa.conexaoAberta()){
             //Passo 1: recuperar a coleção de funcionario.
            Collection <Funcionario> f = jpa.listFuncionario();
            if(!(f.isEmpty())){
                
                SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");        

                
                System.out.println(f);
                //Passo 2: caso a coleção não esteja vazia - imprimir (inclusive os cursos).
                for(Funcionario fh : f){
                    System.out.println("Cpf: " + fh.getCpf());
                    System.out.println("Nome: " + fh.getNome());
                    System.out.println("Senha: " + fh.getSenha());
                    System.out.println("Data de Nascimento: " + formatadorData.format(fh.getData_nascimento().getTime()));
                    System.out.println("Cep: " + fh.getCep());
                    System.out.println("Complemento: " + fh.getComplemento());
                    System.out.println("Numero CTPS: " + fh.getNumero_ctps());
                    System.out.println("Data Admissao: " + formatadorData.format(fh.getData_admissao().getTime()));
                    System.out.println("Cargo ID: " + (fh.getCargo()).getId());
                    System.out.println("Cargo Descricao: " + (fh.getCargo()).getDescricao());
                    Collection<Curso> cursos = fh.getCurso();
                    for(Curso ch : cursos){
                        System.out.println("Curso ID: " + ch.getId());
                        System.out.println("Curso Descricao: " + ch.getDescricao());
                        System.out.println("Curso Carga Horaria: " + ch.getCargaHoraria());
                        System.out.println("Curso DT conclusao: " + formatadorData.format(ch.getDt_conclusao().getTime()));
                    }
                }
                
                for(Funcionario fh : f){
                    System.out.println("removendo funcionario com cpf: " + fh.getCpf());
                    jpa.remover(fh);
                    }
                
                //Passo 3: caso a coleção esteja vazia, criar dois funcionarios com um Curso cada.
            }else {
                System.out.println("2 Funcionarios criados");
                Funcionario fh = new Funcionario(), fa = new Funcionario();
                    fh.setCpf("04960317086");
                    fh.setNome("Ygor da Rosa");
                    fh.setSenha("123");
                    fh.setData_nascimento(Calendar.getInstance());
                    fh.setCep("99020530");
                    fh.setComplemento("Ao lado do antigo estádio delmar sitoni");
                    fh.setNumero_ctps("1234567890");
                    fh.setData_admissao(Calendar.getInstance());
                    Cargo cargo = new Cargo();
                    cargo.setDescricao("abcde");
                    
                    jpa.persist(cargo);
                    
                    fh.setCargo(cargo);
                    
                    
                    Curso curso = new Curso();
                    curso.setDescricao("abcde");
                    curso.setCargaHoraria(300);
                    curso.setDt_conclusao(Calendar.getInstance());
                    jpa.persist(curso);
                    
                    Collection<Curso> cursos = new ArrayList();
                    cursos.add(curso);
                    fh.setCurso(cursos);
                    jpa.persist(fh);
                    
                    
                    fa.setCpf("04960317087");
                    fa.setNome("Ygor da Rosa");
                    fa.setSenha("123");
                    fa.setData_nascimento(Calendar.getInstance());
                    fa.setCep("99020530");
                    fa.setComplemento("Ao lado do antigo estádio delmar sitoni");
                    fa.setNumero_ctps("1234567891");
                    fa.setData_admissao(Calendar.getInstance());
                    Cargo cargo1 = new Cargo();
                    cargo1.setDescricao("abcdefg");
                    jpa.persist(cargo1);
                    
                    fa.setCargo(cargo1);
                    
                    Curso curso1 = new Curso();
                    curso1.setDescricao("edcba");
                    curso1.setCargaHoraria(300);
                    curso1.setDt_conclusao(Calendar.getInstance());
                    jpa.persist(curso1);
                    
                    Collection<Curso> cursos1 = new ArrayList();
                    cursos1.add(curso1);
                    //persistir curso para não dar erro
                    fa.setCurso(cursos1);
                    jpa.persist(fa);
                
            }

            System.out.println("abriu a conexao com o BD via JPA");
            jpa.fecharConexao(); 
        }else{
            System.out.println("Nao abriu a conexao com o BD via JPA");
        }
        
    }
     
     

}
