package model.dto;

public class ClientRequestDto {
    private Long clientId;
    private String fio;
    private String passportNumber;
    private String birthDate;
    private String secondNumber;

    public ClientRequestDto(Long clientId,
                            String userFullNameInfo,
                            String passportNumber,
                            String birthDate,
                            String secondNumber) {
        this.clientId = clientId;
        this.fio = userFullNameInfo;
        this.passportNumber = passportNumber;
        this.birthDate = birthDate;
        this.secondNumber = secondNumber;
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

    public String getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(String secondNumber) {
        this.secondNumber = secondNumber;
    }
}
