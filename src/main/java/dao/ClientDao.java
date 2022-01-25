package dao;

import model.Client;
import model.dto.ClientResponseDto;

public interface ClientDao {
    Long create(Client user);

    ClientResponseDto update(ClientResponseDto userDto);

    Client getById(Long clientId);

    void delete(Long clientId);

    Long findByFullName(String fio);
}
