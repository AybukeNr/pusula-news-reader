package com.example.pusula.Repository;

import com.example.pusula.DTO.UserDTO;
import com.example.pusula.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.List;


@Repository
public class UserDAOImpl implements UserDAO {

    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager){
      this.entityManager=entityManager;
  }

    @Override
    public User findById(int id) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        User theUser = null;
        try {
            theUser = query.getSingleResult();
        } catch (Exception e) {
            theUser = null;
        }

        return theUser;
    }

    @Override
    public List<User> findYoneticiler() {
        TypedQuery<User> query = entityManager.createQuery(
                "FROM User where Role.id=2", User.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void setRole(User user) {
        entityManager.persist(user);
    }


    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);

    }

    @Override
    @Transactional
    public void delete(User user) {
        if (entityManager.contains(user)) {
            entityManager.remove(user);
        } else {
            entityManager.remove(entityManager.merge(user));
        }
    }


}
