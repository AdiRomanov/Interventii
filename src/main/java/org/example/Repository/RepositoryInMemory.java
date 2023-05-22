package org.example.Repository;

import org.example.Domain.Entity;
import org.example.Exceptions.InvalidId;

import java.util.Objects;
import java.util.Vector;

public class RepositoryInMemory implements Repository<Entity, Integer> {
    private Vector<Entity> entities;

    /**
     * Constructor for Repo in Memory.
     */
    public RepositoryInMemory() {
        this.entities = new Vector<Entity>();
    }
    public Vector<Entity> getEntities(){
        return this.entities;
    }

    /**
     * Add entity to array of entities.
     * @param entity the added entity
     * @return the entity that has been added
     */
    @Override
    public Entity add(Entity entity) {
        if(entity == null) throw new IllegalArgumentException("Entity cannot be null.");
        if(getEntity(entity.getId()) == null)
            entities.add(entity);
        return entity;
    }

    /**
     * Remove an entity
     * @param id id of entity to be removed
     * @return the entity that has been removed
     * @throws InvalidId
     */

    @Override
    public Entity delete(Integer id) throws InvalidId {
        if(id <= 0) throw new IllegalArgumentException("Id cannot be null.");
        if(getEntity(id) == null)
        {
            throw new InvalidId("the id given does not exist");
        }
        else entities.remove(getEntity(id));

        return getEntity(id);
    }

    /**
     * Update entity
     * @param id id of entity to be updated
     * @param newEntity new entity
     * @return updated entity
     */

    @Override
    public Entity update(Integer id, Entity newEntity) {
        for(int i=0; i<entities.size(); i++){
            if(Objects.equals(entities.elementAt(i).getId(), id)) {
                entities.setElementAt(newEntity, entities.elementAt(i).getId());
            }
        }
        return null;
    }

    /**
     * Search entity in array of entities
     * @param id id of searched entity
     * @return entity if it exists, null otherwise
     */
    @Override
    public Entity getEntity(Integer id) {
        for(int i=0; i<entities.size(); i++){
            if(Objects.equals(entities.elementAt(i).getId(), id))
                return entities.elementAt(i);
        }
        return null;
    }


}
