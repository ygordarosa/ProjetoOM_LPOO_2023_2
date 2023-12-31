/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.bcc.lpoo.om.model.dao;

import br.edu.ifsul.bcc.lpoo.om.model.Cargo;
import br.edu.ifsul.bcc.lpoo.om.model.Cliente;
import br.edu.ifsul.bcc.lpoo.om.model.Funcionario;
import br.edu.ifsul.bcc.lpoo.om.model.MaoObra;
import br.edu.ifsul.bcc.lpoo.om.model.Servico;
import java.util.Collection;

/**
 *
 * @author Ygor
 */
public interface InterfacePersistence {
    
    public Boolean conexaoAberta();
    public void fecharConexao();
    public Object find(Class c, Object id) throws Exception;
    public void persist(Object o) throws Exception;
    public void remover(Object o) throws Exception;//delete.
    
    public Collection<Cargo> listCargo () throws Exception;
    public Collection<Funcionario> listFuncionario () throws Exception;
    public Collection<Cliente> listCliente () throws Exception;
    public Funcionario doLogin(String cpf, String senha) throws Exception;
    public Collection<Servico> listServico() throws Exception;
    public Collection<MaoObra> listMaoObras(String filtro) throws Exception;
}
