package com.projeto.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Classe para criar e gerenciar a conexão com o banco de dados PostgreSQL.
 */
public class DatabaseConnection {
    private static final String PROPERTIES_FILE = "/com/projeto/utils/database.properties";
    private static String url;
    private static String user;
    private static String password;

    private static Connection connection;

    // Construtor privado para evitar instâncias
    private DatabaseConnection() {}

    static {
        try (InputStream input = DatabaseConnection.class.getResourceAsStream(PROPERTIES_FILE)) {
            Properties properties = new Properties();
            if (input == null) {
                throw new IOException("Arquivo de propriedades não encontrado: " + PROPERTIES_FILE);
            }
            properties.load(input);
            url = properties.getProperty("db.url");
            user = properties.getProperty("db.username");
            password = properties.getProperty("db.password");
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError("Falha ao carregar as propriedades do banco de dados");
        }
    }

    /**
     * Obtém uma conexão com o banco de dados.
     *
     * @return a conexão ao banco de dados.
     * @throws SQLException se houver um erro ao conectar.
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }

    /**
     * Define uma conexão para testes.
     *
     * @param connection a conexão a ser definida.
     */
    public static void setConnection(Connection connection) {
        DatabaseConnection.connection = connection;
    }
}