package model.dto;

public class UpdatedClientResponseDto {
    private Long clientId;
    private String fio;
    private String passportNumber;
    private String birthDate;
    private String firstNumber;
    private String secondNumber;

    public UpdatedClientResponseDto() {
    }

    public UpdatedClientResponseDto(String fio,
                                    String passportNumber,
                                    String birthDate,
                                    String firstNumber,
                                    String secondNumber) {
        this.fio = fio;
        this.passportNumber = passportNumber;
        this.birthDate = birthDate;
        this.firstNumber = firstNumber;
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
}
