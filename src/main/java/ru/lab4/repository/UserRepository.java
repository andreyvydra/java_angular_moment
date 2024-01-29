package ru.lab4.repository;

import ru.lab4.model.User;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class UserRepository {
    @PersistenceContext
    private EntityManager db;

    public User save(User user) {
        db.persist(user);
        return user;
    }

    public User findByUsername(String username) {
        TypedQuery<User> query = db.createQuery("from User u where u.username = :username", User.class).setParameter("username", username);
        return query.getResultStream().findAny().orElse(null);
    }

}
