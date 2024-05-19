package com.example.pusula.Repository;


import com.example.pusula.Entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAOImpl implements RoleDAO{

    private EntityManager entityManager;

    @Autowired
    public RoleDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Role findById(int id) {
        TypedQuery<Role> query = entityManager.createQuery("from Role where id=:id",Role.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }
}
