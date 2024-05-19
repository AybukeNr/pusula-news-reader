package com.example.pusula.Repository;

import com.example.pusula.Entity.Comment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class CommentDAOImpl implements CommentDAO {


    private EntityManager entityManager;

    @Autowired
    public CommentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Comment findById(int id) {
        Comment comment = entityManager.find(Comment.class, id);
        if (comment == null) {
            throw new NoSuchElementException("Comment not found");
        }
        return comment;
    }


    @Override
    @Transactional
    public void save(Comment comment) {
        entityManager.merge(comment);
    }

    @Override
    @Transactional
    public void delete(Comment comment) {
        if (entityManager.contains(comment)) {
            entityManager.remove(comment);
        } else {
            entityManager.remove(entityManager.merge(comment));
        }
    }

    @Override
    public void deleteByUserId(int userId) {
            TypedQuery<Comment> query = entityManager.createQuery(" FROM Comment c WHERE c.user.id = :userId",Comment.class);
            query.setParameter("userId", userId);
            entityManager.remove(query.getSingleResult());
    }

    @Override
    public List<Comment> findAll() {
        TypedQuery<Comment> query = entityManager.createQuery(" from Comment ", Comment.class);
        return query.getResultList();
    }
}
