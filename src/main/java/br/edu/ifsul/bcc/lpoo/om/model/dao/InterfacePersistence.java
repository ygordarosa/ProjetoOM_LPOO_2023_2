/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.bcc.lpoo.om.model.dao;

/**
 *
 * @author Ygor
 */
public interface InterfacePersistence {
    
    public Boolean conexaoAberta();
    public void fecharConexao();
    public Object fund(Class c, Object id) throws Exception;
    public void persist(Object o) throws Exception;
    public void remover(Object o) throws Exception;//delete.
}
