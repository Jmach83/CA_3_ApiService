package facades;

import entity.Role;
import security.IUserFacade;
import entity.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import security.IUser;
import security.PasswordStorage;

public class UserFacade implements IUserFacade {

    public UserFacade() {
        emf = Persistence.createEntityManagerFactory("pu_development");

    }

    EntityManagerFactory emf;

    public UserFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public IUser getUserByUserId(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

//    public void addUser(String username, String password, Role role) {
//        System.out.println("adduser");
//        EntityManager em = getEntityManager();
//        try {
//
//            em.getTransaction().begin();
//            User u = new User(username, password);
//            //Role userRole = new Role("User");
//            u.addRole(role);
//            //em.persist(userRole);
//            em.persist(u);
//            em.getTransaction().commit();
//
//        } finally {
//            em.close();
//        }
//
//    }

    public void addUser(User u) {
      
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            u.addRole(new Role("User"));
            em.persist(u);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public List<User> getUsers() {
        EntityManager em = getEntityManager();
        List<User> users;
        try {
            em.getTransaction().begin();
            //users = em.createQuery("Select u from User u").getResultList();
            users = em.createQuery("Select u from SEED_USER u").getResultList();;
            em.getTransaction().commit();
            return users;
        } finally {
            em.close();
        }
    }
    
    public User deleteUser(String username) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            User u = em.find(User.class, username);
            em.remove(u);
            em.getTransaction().commit();
            return u;
        } finally {
            em.close();
        }
    }

    /*
  Return the Roles if users could be authenticated, otherwise null
     */
    @Override
    public List<String> authenticateUser(String userName, String password) {

        IUser user = getUserByUserId(userName);
       
        try {
           
            return user != null && PasswordStorage.verifyPassword(password, user.getPassword()) ? user.getRolesAsStrings() : null;
        } catch (PasswordStorage.CannotPerformOperationException | PasswordStorage.InvalidHashException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
