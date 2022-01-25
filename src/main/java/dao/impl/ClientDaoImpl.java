package dao.impl;

import dao.ClientDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.enterprise.inject.Default;
import javax.inject.Singleton;
import model.Client;
import model.dto.ClientResponseDto;
import util.JdbcPostgreSqlConnection;

@Singleton
@Default
public class ClientDaoImpl implements ClientDao {
    private static final DateTimeFormatter FORMATTER
            = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    @Override
    public Long create(Client client) {
        String insertQuery = "INSERT INTO users (created_time, deleted_time) "
                + "VALUES (?, ?)";
        try (Connection connection = JdbcPostgreSqlConnection.getConnection();
                 PreparedStatement statement =
                         connection.prepareStatement(
                             insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, client.getCreatedTime());
            statement.setString(2, client.getDeletedTime());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                client.setId(resultSet.getObject(1, Long.class));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't add a client to a data base: " + client, e);
        }
        return client.getId();
    }

    @Override
    public ClientResponseDto update(ClientResponseDto clientResponseDto) {
        return null;
    }

    @Override
    public Client getById(Long clientId) {
        String selectQuery = "SELECT * FROM users WHERE id = ?";
        Client client = null;
        try (Connection connection = JdbcPostgreSqlConnection.getConnection();
                 PreparedStatement statement
                         = connection.prepareStatement(selectQuery)) {
            statement.setLong(1, clientId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                client = getClient(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't get user by id: " + clientId);
        }
        return client;
    }

    @Override
    public Long findByFullName(String fio) {
        String selectQuery = "SELECT u.id as id, pi.full_name "
                + "FROM users u "
                + "JOIN personal_infos pi on u.id = pi.user_id "
                + "WHERE pi.full_name = ? u.deleted_time IS NULL";
        Client client = null;
        try (Connection connection = JdbcPostgreSqlConnection.getConnection();
                 PreparedStatement statement =
                         connection.prepareStatement(selectQuery)) {
            statement.setString(1, fio);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                client = getClient(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return client.getId();
    }

    @Override
    public void delete(Long clientId) {
        String query = "UPDATE users SET deleted_time = ? "
                + "WHERE id = ? and deleted_time IS NULL";
        try (Connection connection = JdbcPostgreSqlConnection.getConnection();
                 PreparedStatement statement
                         = connection.prepareStatement(query)) {
            Client client = getById(clientId);
            statement.setString(1, LocalDateTime.now().format(FORMATTER));
            statement.setLong(2, client.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Client with id: " + clientId + " not found!", e);
        }
    }

    private Client getClient(ResultSet resultSet) throws SQLException {
        Long userId = resultSet.getObject("id", Long.class);
        String createdTime = resultSet.getString("created_time");
        String deletedTime = resultSet.getString("deleted_time");
        Client client = new Client();
        client.setId(userId);
        client.setCreatedTime(createdTime);
        client.setDeletedTime(deletedTime);
        return client;
    }
}
