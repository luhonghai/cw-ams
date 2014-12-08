package uk.ac.gre.cw.aircraft.dao.impl;

import com.google.gson.Gson;
import org.junit.Test;
import uk.ac.gre.cw.aircraft.entities.User;
import uk.ac.gre.cw.aircraft.services.AbstractService;
import uk.ac.gre.cw.aircraft.services.ServiceException;
import uk.ac.gre.cw.aircraft.services.UserService;

public class DatabaseTest {

    @Test
    public void testInsertUser(){
        AbstractService<User> service = new UserService();
        User user = new User();
        user.setUsername("luhonghai");
        user.setPassword("hurricane");
        user.setEmail("luhonghai@gmail.com");
        user.setGender(true);
        user.setFirstName("Hai");
        user.setLastName("Lu");
        try {
            User out = service.create(user);
            Gson gson = new Gson();
            System.out.println(gson.toJson(out));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testDeleteUser() {
        AbstractService<User> service = new UserService();
        try {
            service.delete(7);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

}
