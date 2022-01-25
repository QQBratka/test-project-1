package model.mapper;

import javax.enterprise.inject.Default;
import javax.inject.Singleton;
import model.PersonalInfo;
import model.PhoneNumbers;
import model.dto.UpdatedClientResponseDto;

@Singleton
@Default
public class ResponseDtoMapper {
    public UpdatedClientResponseDto toDto(PersonalInfo personalInfo,
                                          PhoneNumbers phoneNumbers) {
        UpdatedClientResponseDto updated = new UpdatedClientResponseDto();
        updated.setBirthDate(personalInfo.getBirthDate());
        updated.setPassportNumber(personalInfo.getPassportNumber());
        updated.setFirstNumber(phoneNumbers.getFirstNumber());
        updated.setSecondNumber(phoneNumbers.getSecondNumber());
        return updated;
    }
}
