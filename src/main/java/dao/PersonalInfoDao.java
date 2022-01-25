package dao;

import model.PersonalInfo;

public interface PersonalInfoDao {
    Long create(PersonalInfo personalInfo);

    PersonalInfo getByIdClientId(Long clientId);

    void delete(Long clientId);

    void update(PersonalInfo personalInfo);
}
