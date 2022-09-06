package elagin.dmitrii.warehouse_service.repository;

import elagin.dmitrii.warehouse_service.dao.ProductDAO;
import elagin.dmitrii.warehouse_service.entities.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProductRepository implements ProductDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findAll() {
        return entityManager.createQuery("from Product", Product.class).getResultList();
    }

    @Override
    public List<Product> findByName(String name) {
        return entityManager.createQuery(
                String.format("from Product p where p.name = '%s'", name),
                Product.class).getResultList();
    }
}
