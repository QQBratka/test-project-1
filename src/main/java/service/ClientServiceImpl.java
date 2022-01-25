package service;

import dao.ClientDao;
import dao.PersonalInfoDao;
import dao.PhoneNumbersDao;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Singleton;
import model.Client;
import model.PersonalInfo;
import model.PhoneNumbers;
import model.dto.UpdatedClientResponseDto;
import model.mapper.ResponseDtoMapper;

@Singleton
@Default
public class ClientServiceImpl implements ClientService {
    private static final DateTimeFormatter FORMATTER
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Inject
    private ClientDao clientDao;
    @Inject
    private PersonalInfoDao personalInfoDao;
    @Inject
    private PhoneNumbersDao phoneNumbersDao;
    @Inject
    private ResponseDtoMapper responseDtoMapper;

    @Override
    public Long create(Client client) {
        return clientDao.create(client);
    }

    @Override
    public Long findByFullNameAndMainPhoneNumber(String fio,
                                                 String firstPhoneNumber) {
        if (clientDao.findByFullName(fio) == null) {
            Client client = new Client(LocalDateTime.now().format(FORMATTER), null);
            Long clientId = clientDao.create(client);
            PersonalInfo personalInfo = new PersonalInfo(clientId, fio,
                    LocalDateTime.now().format(FORMATTER), null);
            personalInfoDao.create(personalInfo);
            PhoneNumbers phoneNumbers = new PhoneNumbers(clientId, firstPhoneNumber,
                    LocalDateTime.now().format(FORMATTER), null);
            phoneNumbersDao.create(phoneNumbers);
            return clientId;
        }
        return clientDao.findByFullName(fio);
    }

    @Override
    public UpdatedClientResponseDto update(UpdatedClientResponseDto
                                                       updatedClientResponseDto) {
        PersonalInfo personalInfo = personalInfoDao
                .getByIdClientId(updatedClientResponseDto.getClientId());
        PhoneNumbers phoneNumbers = phoneNumbersDao
                .getByIdClientId(updatedClientResponseDto.getClientId());
        if (personalInfo.getFio().equals(updatedClientResponseDto.getFio())
                && personalInfo.getPassportNumber()
                .equals(updatedClientResponseDto.getPassportNumber())
                && personalInfo.getBirthDate()
                .equals(updatedClientResponseDto.getBirthDate())
                && phoneNumbers.getSecondNumber()
                .equals(updatedClientResponseDto.getSecondNumber())) {
            return updatedClientResponseDto;
        }
        PersonalInfo newPersonalInfo = new PersonalInfo(updatedClientResponseDto.getClientId(),
                        updatedClientResponseDto.getFio(),
                        updatedClientResponseDto.getPassportNumber(),
                        updatedClientResponseDto.getBirthDate());
        personalInfoDao.update(newPersonalInfo);
        PhoneNumbers newPhoneNumbers = new PhoneNumbers(updatedClientResponseDto.getFirstNumber(),
                updatedClientResponseDto.getSecondNumber());
        phoneNumbersDao.update(newPhoneNumbers);
        return responseDtoMapper.toDto(newPersonalInfo, newPhoneNumbers);
    }

    @Override
    public Optional<UpdatedClientResponseDto> find(Long clientId) {
        if (clientDao.getById(clientId) == null) {
            return Optional.empty();
        }
        clientDao.getById(clientId);
        PersonalInfo personalInfo = personalInfoDao.getByIdClientId(clientId);
        PhoneNumbers phoneNumbers = phoneNumbersDao.getByIdClientId(clientId);
        return Optional.ofNullable(responseDtoMapper.toDto(personalInfo, phoneNumbers));
    }

    @Override
    public boolean delete(Long clientId) {
        if (clientDao.getById(clientId) == null) {
            throw new RuntimeException("Can't delete client with id: " + clientId);
        }
        personalInfoDao.delete(clientId);
        phoneNumbersDao.delete(clientId);
        clientDao.delete(clientId);
        return true;
    }
}
