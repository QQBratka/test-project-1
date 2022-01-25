package util;

public class Validation {
    private static final String FIO_PATTERN
            = "^[A-Z][a-z]+\\s[A-Z][a-z]+\\s[A-Z][a-z]+$";
    private static final String PHONE_PATTERN
            = "^\\+\\d{2}\\s\\(\\d{3}\\)\\s\\d{3}-\\d{2}-\\d{2}$";
    private static final String PASSPORT_PATTERN
            = "^[A-Z]{2}[0-9]{7}$";
    private static final String BIRTH_DATE_PATTERN
            = "^(0[1-9]|1[012])[-/.](0[1-9]|[12][0-9]|3[01])[-/.](19|20)\\d\\d$";

    public boolean isValidFio(String fio) {
        return fio.matches(FIO_PATTERN);
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches(PHONE_PATTERN);
    }

    public boolean isValidPassportNumber(String passportNumber) {
        return passportNumber.matches(PASSPORT_PATTERN);
    }

    public boolean isValidBirthDate(String birthDate) {
        return birthDate.matches(BIRTH_DATE_PATTERN);
    }
}
