package com.projeto.DAO.interfaces;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface genérica para operações de acesso a dados (DAO).
 * Define operações CRUD básicas para objetos de um tipo genérico T.
 *
 * @param <T> O tipo de objeto que este DAO irá manipular.
 */
public interface ObjectDAO<T> {

    /**
     * Adiciona um novo objeto no sistema.
     *
     * @param object O objeto a ser adicionado.
     * @throws IllegalArgumentException Se o objeto for nulo.
     * @throws SQLException Se ocorrer um erro durante a adição no banco de dados.
     */
    void addObject(T object) throws IllegalArgumentException, SQLException;

    /**
     * Recupera um objeto pelo seu identificador.
     *
     * @param id O identificador do objeto.
     * @return O objeto encontrado, ou null se nenhum objeto for encontrado.
     * @throws IllegalArgumentException Se o ID for nulo.
     * @throws SQLException Se ocorrer um erro durante a recuperação no banco de dados.
     */
    T getObjectById(Integer id) throws IllegalArgumentException, SQLException;

    /**
     * Recupera um objeto pela sua string de busca.
     *
     * @param searchString A string de busca.
     * @return O objeto encontrado, ou null se nenhum objeto for encontrado.
     * @throws IllegalArgumentException Se a string de busca for nula.
     * @throws SQLException Se ocorrer um erro durante a recuperação no banco de dados.
     */
    T getObjectByString(String searchString) throws IllegalArgumentException, SQLException;

    /**
     * Recupera todos os objetos registrados.
     *
     * @return Uma lista de todos os objetos registrados.
     * @throws SQLException Se ocorrer um erro durante a obtenção da lista.
     */
    List<T> getAllObjects() throws SQLException;

    /**
     * Remove um objeto do sistema.
     *
     * @param object O objeto a ser removido.
     * @throws IllegalArgumentException Se o objeto for nulo.
     * @throws SQLException Se ocorrer um erro durante a remoção no banco de dados.
     */
    void removeObject(T object) throws IllegalArgumentException, SQLException;
}
