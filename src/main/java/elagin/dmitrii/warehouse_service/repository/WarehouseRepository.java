package elagin.dmitrii.warehouse_service.repository;

import elagin.dmitrii.warehouse_service.dao.WarehouseDAO;
import elagin.dmitrii.warehouse_service.entities.Warehouse;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class WarehouseRepository implements WarehouseDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Warehouse> findAll() {
        return entityManager.createQuery("from Warehouse", Warehouse.class).getResultList();
    }

    @Override
    public Warehouse save(String name) {
        final var warehouse = new Warehouse(name);

        entityManager.persist(new Warehouse(name));

        return warehouse;
    }

    @Override
    public Warehouse update(String oldName, String newName) {
        if (entityManager.find(Warehouse.class, oldName) != null) {
            final var builder = entityManager.getCriteriaBuilder();
            final var criteria = entityManager.getCriteriaBuilder().createCriteriaUpdate(Warehouse.class);
            final var root = criteria.from(Warehouse.class);

            criteria.set("name", newName);

            criteria.where(builder.equal(root.get("name"), oldName));

            entityManager.createQuery(criteria).executeUpdate();
        } else throw new EntityNotFoundException();

        return entityManager.find(Warehouse.class, newName);
    }

    @Override
    public void delete(String name) {
        final var warehouse = entityManager.find(Warehouse.class, name);

        if (warehouse != null) {
            entityManager.remove(warehouse);
        } else throw new EntityNotFoundException();
    }
}
