package ca.andre.spgboot.application.service;

import ca.andre.spgboot.application.model.Client;
import ca.andre.spgboot.application.repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientsService {

    private ClientsRepository clientsRepository;

    @Autowired
    public ClientsService(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    public  void saveClient(Client client)
    {
        validationClient(client);
        clientsRepository.persist(client);
    }

    public void validationClient(Client client)
    {
        // validation client
    }
}
