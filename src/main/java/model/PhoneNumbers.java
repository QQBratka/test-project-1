package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class PhoneNumbers {
    private static final DateTimeFormatter FORMATTER
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private Long id;
    private Long clientId;
    private String firstNumber;
    private String secondNumber;
    private String createdTime;
    private String deletedTime;

    public PhoneNumbers(Long clientId, String firstNumber, String createdTime, String deletedTime) {
        this.clientId = clientId;
        this.firstNumber = firstNumber;
        this.createdTime = createdTime;
        this.deletedTime = deletedTime;
    }

    public PhoneNumbers(String firstNumber, String secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public PhoneNumbers(Long userId,
                        String firstNumber,
                        String secondNumber,
                        String createdTime,
                        String deletedTime) {
        this.clientId = userId;
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
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

    public String getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(String firstNumber) {
        this.firstNumber = firstNumber;
    }

    public String getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(String secondNumber) {
        this.secondNumber = secondNumber;
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
        PhoneNumbers that = (PhoneNumbers) o;
        return Objects.equals(id, that.id)
                && Objects.equals(clientId, that.clientId)
                && Objects.equals(firstNumber, that.firstNumber)
                && Objects.equals(secondNumber, that.secondNumber)
                && Objects.equals(createdTime, that.createdTime)
                && Objects.equals(deletedTime, that.deletedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, firstNumber, secondNumber,
                createdTime, deletedTime);
    }

    @Override
    public String toString() {
        return "PhoneNumbers{"
                + "id=" + id
                + ", userId=" + clientId
                + ", firstNumber='" + firstNumber + '\''
                + ", secondNumber='" + secondNumber + '\''
                + ", createdTime=" + createdTime
                + ", deletedTime=" + deletedTime
                + '}';
    }
}
