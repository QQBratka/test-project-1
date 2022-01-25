package dao.impl;

import dao.PhoneNumbersDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.enterprise.inject.Default;
import javax.inject.Singleton;
import model.PhoneNumbers;
import util.JdbcPostgreSqlConnection;

@Singleton
@Default
public class PhoneNumbersDaoImpl implements PhoneNumbersDao {
    private static final DateTimeFormatter FORMATTER
            = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    @Override
    public Long create(PhoneNumbers phoneNumbers) {
        String insertQuery = "INSERT INTO phone_numbers "
                + "(client_id, first_number, second_number, created_time, deleted_time) "
                + "VALUES (?, ?, ?, ?)";
        try (Connection connection = JdbcPostgreSqlConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(
                             insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, phoneNumbers.getClientId());
            statement.setString(2, phoneNumbers.getFirstNumber());
            statement.setString(3, phoneNumbers.getSecondNumber());
            statement.setString(4, LocalDateTime.now().format(FORMATTER));
            statement.setString(5, null);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                phoneNumbers.setId(resultSet.getObject(1, Long.class));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't add phone numbers to a data base: "
                    + phoneNumbers, e);
        }
        return phoneNumbers.getId();
    }

    @Override
    public PhoneNumbers getByIdClientId(Long clientId) {
        String selectQuery = "SELECT * FROM phone_numbers "
                + "WHERE client_id = ? AND deleted_time IS NULL";
        PhoneNumbers phoneNumbers = null;
        try (Connection connection = JdbcPostgreSqlConnection.getConnection();
                 PreparedStatement statement
                         = connection.prepareStatement(selectQuery)) {
            statement.setLong(1, clientId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String firstNumber = resultSet.getString("first_number");
                String secondNumber = resultSet.getString("second_number");
                String createdTime = resultSet.getString("created_time");
                String deletedTime = resultSet.getString("deleted_time");
                phoneNumbers = new PhoneNumbers(clientId, firstNumber, secondNumber,
                        createdTime, deletedTime);
                phoneNumbers.setId(resultSet.getObject("id", Long.class));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't get phone numbers by client id: " + clientId);
        }
        return phoneNumbers;
    }

    @Override
    public void update(PhoneNumbers phoneNumbers) {
        String updatePersonalInfoRequest = "UPDATE phone_numbers "
                + "SET first_number = ?, second_number = ?, created_time = ? "
                + "WHERE client_id = ? AND deleted_time IS NULL";
        try (Connection connection = JdbcPostgreSqlConnection.getConnection();
                 PreparedStatement statement
                         = connection.prepareStatement(updatePersonalInfoRequest)) {
            statement.setString(1, phoneNumbers.getFirstNumber());
            statement.setString(2, phoneNumbers.getSecondNumber());
            statement.setString(3, LocalDateTime.now().format(FORMATTER));
            statement.setLong(4, phoneNumbers.getClientId());
            statement.executeUpdate();
        } catch (SQLException throwable) {
            throw new RuntimeException("Invalid updating phone numbers "
                    + phoneNumbers, throwable);
        }
    }

    @Override
    public void delete(Long clientId) {
        String query = "UPDATE phone_numbers SET deleted_time = ? "
                + "WHERE client_id = ? and deleted_time IS NULL";
        try (Connection connection = JdbcPostgreSqlConnection.getConnection();
                 PreparedStatement statement
                         = connection.prepareStatement(query)) {
            PhoneNumbers phoneNumbers = getByIdClientId(clientId);
            statement.setString(1, LocalDateTime.now().format(FORMATTER));
            statement.setLong(2, phoneNumbers.getClientId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Phone numbers with client id: "
                    + clientId + " not found!", e);
        }
    }
}
