package dao.impl;

import dao.PersonalInfoDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.enterprise.inject.Default;
import javax.inject.Singleton;
import model.PersonalInfo;
import util.JdbcPostgreSqlConnection;

@Singleton
@Default
public class PersonalInfoDaoImpl implements PersonalInfoDao {
    private static final DateTimeFormatter FORMATTER
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public Long create(PersonalInfo personalInfo) {
        String insertQuery = "INSERT INTO personal_informations "
                + "(client_id, fio, created_time, deleted_time) "
                + "VALUES (?, ?, ?, ?)";
        try (Connection connection = JdbcPostgreSqlConnection.getConnection();
                 PreparedStatement statement =
                         connection.prepareStatement(
                             insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, personalInfo.getClientId());
            statement.setString(2, personalInfo.getFio());
            statement.setString(3, personalInfo.getCreatedTime());
            statement.setString(4, null);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                personalInfo.setId(resultSet.getObject(1, Long.class));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't add a client to a data base: " + personalInfo, e);
        }
        return personalInfo.getId();
    }

    @Override
    public PersonalInfo getByIdClientId(Long clientId) {
        String selectQuery = "SELECT * FROM personal_informations "
                + "WHERE client_id = ? AND deleted_time IS NULL";
        PersonalInfo personalInfo = null;
        try (Connection connection = JdbcPostgreSqlConnection.getConnection();
                 PreparedStatement statement
                         = connection.prepareStatement(selectQuery)) {
            statement.setLong(1, clientId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String fio = resultSet.getString("fio");
                String passportNumber = resultSet.getString("passport_number");
                String birthDate = resultSet.getString("birth_date");
                String createdTime = resultSet.getString("created_time");
                String deletedTime = resultSet.getString("deleted_time");
                personalInfo = new PersonalInfo(clientId, fio, passportNumber,
                        birthDate, createdTime, deletedTime);
                personalInfo.setId(resultSet.getObject("id", Long.class));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't get user by id: " + personalInfo);
        }
        return personalInfo;
    }

    @Override
    public void update(PersonalInfo personalInfo) {
        String updatePersonalInfoRequest = "UPDATE personal_informations SET fio = ?, "
                + "passport_number = ?, date_of_birth = ?, created_time = ? "
                + "WHERE client_id = ? AND deleted_time IS NULL";
        try (Connection connection = JdbcPostgreSqlConnection.getConnection();
                 PreparedStatement statement
                        = connection.prepareStatement(updatePersonalInfoRequest)) {
            statement.setString(1, personalInfo.getFio());
            statement.setString(2, personalInfo.getPassportNumber());
            statement.setString(3, personalInfo.getBirthDate());
            statement.setString(3, LocalDateTime.now().format(FORMATTER));
            statement.setLong(4, personalInfo.getClientId());
            statement.executeUpdate();
        } catch (SQLException throwable) {
            throw new RuntimeException("Invalid updating personal info "
                    + personalInfo, throwable);
        }
    }

    @Override
    public void delete(Long clientId) {
        String query = "UPDATE personal_informations SET deleted_time = ? "
                + "WHERE client_id = ? and deleted_time IS NULL";
        try (Connection connection = JdbcPostgreSqlConnection.getConnection();
                 PreparedStatement statement
                         = connection.prepareStatement(query)) {
            PersonalInfo personalInfo = getByIdClientId(clientId);
            statement.setString(1, LocalDateTime.now().format(FORMATTER));
            statement.setLong(2, personalInfo.getClientId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Personal info with client id: "
                    + clientId + " not found!", e);
        }
    }
}
