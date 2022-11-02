package org.homework.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import org.homework.di.annotation.Service;

@Service
public class DatabaseConnector {

    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3307/accounts_lesson?serverTimezone=UTC";

    public <T> T select(String query, Function<ResultSet, T> resultSetConsumer) {
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, "root", "root");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            return resultSetConsumer.apply(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T selectPrepared(String query, Map<Integer, Object> params, Function<ResultSet, T> resultSetConsumer) {
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, "root", "root");
             PreparedStatement statement = connection.prepareStatement(query)) {
            for (Map.Entry<Integer, Object> integerObjectEntry : params.entrySet()) {
                statement.setObject(integerObjectEntry.getKey(), integerObjectEntry.getValue());
            }
            ResultSet resultSet = statement.executeQuery();
            T result = resultSetConsumer.apply(resultSet);
            resultSet.close();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void execute(String query) {
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, "root", "root");
             Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void executePrepared(String query, Map<Integer, Object> params) {
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, "root", "root");
             PreparedStatement statement = connection.prepareStatement(query)) {
            for (Map.Entry<Integer, Object> integerObjectEntry : params.entrySet()) {
                statement.setObject(integerObjectEntry.getKey(), integerObjectEntry.getValue());
            }
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
