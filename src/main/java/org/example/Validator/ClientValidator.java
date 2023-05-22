package org.example.Validator;

import org.example.Domain.Client;
import org.example.Exceptions.InvalidId;
import org.example.Exceptions.InvalidName;


import java.util.Objects;

public class ClientValidator{
    public void validate(Client client) throws InvalidId, InvalidName {
        if(Objects.equals(client.getId(), "")) {
            throw new InvalidId("the id cannot be an empty string. Reenter id: ");
        }
        if(Objects.equals(client.getFirstName(), "")){
            throw new InvalidName("the first name cannot be an empty string");
        }
        if(Objects.equals(client.getLastName(), "")){
            throw new InvalidName("the last name cannot be an empty string");
        }

    }
}
