/*
* Software Development
* Karel de Grote-hogeschool
* 2013-2014
*/

import be.kdg.model.User;
import be.kdg.persistence.api.UserDbOperations;
import be.kdg.persistence.impl.UserDbOperationsImplementation;

import static junit.framework.Assert.*;


public class Test {
    private User user = new User("username", "password", "email");
    private UserDbOperations operations = new UserDbOperationsImplementation();

    @org.junit.Test
    public void testBasicDatabaseInsert() {
        operations.insertNewUser(user);
        assertEquals("users should be the same", user.toString(), operations.getUserById(user.getId()).toString());
    }

    @org.junit.Test
    public void testCorrectLogin() {
        assertTrue(operations.checkLogin("username", "password"));
    }

    @org.junit.Test
    public void testIncorrectLogin() {
        assertFalse(operations.checkLogin("username", "wrongPassword"));
    }
}
