package model.dto;

public class ClientResponseDto {
    private String fio;
    private String passportNumber;
    private String birthDate;
    private String firstNumber;
    private String secondNumber;

    public ClientResponseDto(String userFullNameInfo,
                             String passportNumber,
                             String birthDate,
                             String firstNumber,
                             String secondNumber) {
        this.fio = userFullNameInfo;
        this.passportNumber = passportNumber;
        this.birthDate = birthDate;
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
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
