/*
* Software Development
* Karel de Grote-hogeschool
* 2013-2014
*/

import be.kdg.model.User;
import be.kdg.persistence.api.UserDbOperations;
import be.kdg.persistence.impl.UserDbOperationsImplementation;

import static junit.framework.Assert.assertEquals;


public class Test {

    @org.junit.Test
    public void testBasicDatabaseInsert() {
        User user = new User("username", "password", "email");
        UserDbOperations operations = new UserDbOperationsImplementation();
        operations.insertNewUser(user);
        assertEquals("users should be the same", user.toString(), operations.getUserById(user.getId()).toString());
    }


}
