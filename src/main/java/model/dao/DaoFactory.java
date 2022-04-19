package model.dao;

/**
 *
 * @author frizzocamila
 */
public class DaoFactory {

    public static ClienteDaoJpa novoClienteDao() throws Exception {
        return new ClienteDaoJpa();
    }
}
