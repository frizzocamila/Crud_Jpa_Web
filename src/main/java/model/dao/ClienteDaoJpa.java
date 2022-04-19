/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import model.Cliente;

/**
 *
 * @author frizz
 */
public class ClienteDaoJpa implements InterfaceDao<Cliente> {

    @Override
    public void incluir(Cliente entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entidade);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void editar(Cliente entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entidade);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void excluir(Cliente entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            Cliente c = em.find(Cliente.class, entidade.getId());
            em.remove(c);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Cliente pesquisarPorId(int id) throws Exception {
        Cliente c = null;
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            c = em.find(Cliente.class, id);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return c;
    }

    @Override
    public List<Cliente> listar(String param) throws Exception {
        List<Cliente> lista = null;
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            if (param == ""){
                lista = em.createQuery("FROM Cliente c").getResultList();
            } else {
                String macroBusca = "SELECT c FROM Cliente c WHERE c.nome like ";
                Query buscaSQL = em.createQuery(macroBusca + "'%" + param + "%'");
                lista = buscaSQL.getResultList();
            }
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return lista;
    }

}
