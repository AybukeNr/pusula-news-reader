package com.example.pusula.Repository;


import com.example.pusula.Entity.Category;
import com.example.pusula.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO{


    private EntityManager entityManager;


    @Autowired
    public CategoryDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;

    }


    @Override
    public List<Category> getAll() {
        TypedQuery<Category> query = entityManager.createQuery("FROM Category",Category.class);
        return query.getResultList();
    }

    @Override
    public Category findById(int id) {
        TypedQuery<Category> query = entityManager.createQuery("FROM Category Where id=:category_id ",Category.class);
        query.setParameter("category_id",id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void createCategory(Category category) {
        entityManager.persist(category);

    }

    @Override
    @Transactional
    public void updateCategory(Category category) {
        entityManager.merge(category);

    }


}
