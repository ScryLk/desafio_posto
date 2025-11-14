package br.com.desafioposto.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() {
        try {
            // Carrega o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            Properties props = new Properties();
            InputStream in = getClass()
                    .getClassLoader()
                    .getResourceAsStream("db.properties");

            if (in == null) {
                throw new RuntimeException("Arquivo db.properties não encontrado");
            }

            props.load(in);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            connection = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao conectar ao banco: " + e.getMessage(), e);
        }
    }

    public static DatabaseConnection getInstance() {
        if (Objects.isNull(instance)) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection connection() {
        return connection;
    }
}
