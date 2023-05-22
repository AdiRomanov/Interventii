package org.example.Repository;

import org.example.Exceptions.InvalidId;

public interface Repository<E, T> {
    /**
     * Adauga o entitate in repository.
     * @param entity entitatea pe care o adaugam
     * @return entitatea pe care o adaugam
     */
    E add(E entity);

    /**
     * Sterge o entitate.
     * @param type identificator pentru entitate
     * @return entitatea stearsa
     * @throws InvalidId daca identificatorul dat nu exista
     */
    E delete(T type) throws InvalidId;

    /**
     * Actualizeaza o prietenie.
     * @param id id-ul prieteniei
     * @param newEntity entitatea cu care actualizam entitatea veche
     * @return entitatea actualizata
     */
    E update(Integer id, E newEntity);

    /**
     * Getter pentru o entitate.
     * @param type identificatorul entitatii
     * @return entitatea daca exista, null altfel
     */
    E getEntity(T type);

}
