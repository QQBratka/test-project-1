package dao;

import model.PhoneNumbers;

public interface PhoneNumbersDao {
    Long create(PhoneNumbers phoneNumbers);

    PhoneNumbers getByIdClientId(Long clientId);

    void delete(Long clientId);

    void update(PhoneNumbers phoneNumbers);
}
