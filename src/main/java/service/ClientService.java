package service;

import java.util.Optional;
import model.Client;
import model.dto.UpdatedClientResponseDto;

public interface ClientService {
    Long create(Client user);

    Long findByFullNameAndMainPhoneNumber(String fio,
                                          String firstPhoneNumber);

    UpdatedClientResponseDto update(UpdatedClientResponseDto updatedClientResponseDto);

    Optional<UpdatedClientResponseDto> find(Long userId);

    boolean delete(Long userId);
}
