package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class PersonalInfo {
    private static final DateTimeFormatter FORMATTER
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private Long id;
    private Long clientId;
    private String fio;
    private String passportNumber;
    private String birthDate;
    private String createdTime;
    private String deletedTime;

    public PersonalInfo(Long clientId, String fio, String createdTime, String deletedTime) {
        this.clientId = clientId;
        this.fio = fio;
        this.createdTime = createdTime;
        this.deletedTime = deletedTime;
    }

    public PersonalInfo(Long userId,
                        String userFullNameInfo,
                        String passportNumber,
                        String birthDate,
                        String createdTime,
                        String deletedTime) {
        this.clientId = userId;
        this.fio = userFullNameInfo;
        this.passportNumber = passportNumber;
        this.birthDate = birthDate;
        this.createdTime = LocalDateTime.now().format(FORMATTER);
        this.deletedTime = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getDeletedTime() {
        return deletedTime;
    }

    public void setDeletedTime(String deletedTime) {
        this.deletedTime = deletedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersonalInfo that = (PersonalInfo) o;
        return Objects.equals(id, that.id)
                && Objects.equals(clientId, that.clientId)
                && Objects.equals(fio, that.fio)
                && Objects.equals(passportNumber, that.passportNumber)
                && Objects.equals(birthDate, that.birthDate)
                && Objects.equals(createdTime, that.createdTime)
                && Objects.equals(deletedTime, that.deletedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, fio, passportNumber,
                birthDate, createdTime, deletedTime);
    }

    @Override
    public String toString() {
        return "PersonalInfo{"
                + "id=" + id
                + ", userId=" + clientId
                + ", userFullNameInfo='" + fio + '\''
                + ", passportNumber='" + passportNumber + '\''
                + ", birthDate='" + birthDate + '\''
                + ", createdTime=" + createdTime
                + ", deletedTime=" + deletedTime
                + '}';
    }
}
