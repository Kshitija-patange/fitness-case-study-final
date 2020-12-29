package com.capgemini.Zfitnessapp.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capgemini.Zfitnessapp.ZFitnessAppApplication;
import com.capgemini.fitness.dao.AdminDataDao;
import com.capgemini.fitness.entity.Admin;
import com.capgemini.fitness.entity.Role;
import com.capgemini.fitness.exception.AdminException;
import com.capgemini.fitness.exception.OperationFailedException;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ZFitnessAppApplication.class)
public class AdminTestClass
{
	/*
	 * @Test public void testAdminnameIsNull() { Throwable exception = assertThrows(
	 * OperationFailedException.class, () -> { Admin admin = new Admin();
	 * admin.setAdmin_name("ankita"); } ); assertEquals("Username cannot be blank",
	 * exception.getMessage()); }
	 */
	@Autowired
	private AdminDataDao repository;
	
	@Test
    public void whenExceptionThrown_thenAssertionSucceeds() {
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            Integer.parseInt("1a");
        });
        String expectedMessage = "For input string";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
	
	
	    @Test
	    public void testPassword() throws AdminException {
		 Admin admin = new Admin(1,"pall", "pune", "abc@gmail.com",987654321L,"ankita@15",Role.ADMIN);

		 if (admin.getPassword().length()<6) {
	           throw new AdminException("Password should contain minimum 6 character");
	        }
	    }
	    
	    @Test
	    public void testName() throws AdminException {
		 Admin admin = new Admin(1,"ankita", "pune", "abc@gmail.com",987654321L,"ankita@15",Role.ADMIN);

		 if (admin.getAdmin_name()==null) {
	           throw new AdminException("Name should not null");
	        }
	    }
	    
	    @Test
	    public void whenDeriveExceptionThrown_thenAssertionSucceeds() {
	        Exception exception = assertThrows(RuntimeException.class, () -> {
	            Integer.parseInt("1a");
	        });
	        String expectedMessage = "For input string";
	        String actualMessage = exception.getMessage();

	 

	        assertTrue(actualMessage.contains(expectedMessage));
	    }

	    
	    @Test
	    public void whenInvalidExceptionThrown_thenAssertionSucceeds() {
	         Assertions.assertThrows(NullPointerException.class, () -> {
	           String name =null;
	           name.length();
	        });
	    }
	    
	    @Test
	    public void whenAdminExceptionThrown_thenAssertionSucceeds() {
	         Assertions.assertThrows(InvalidDataAccessApiUsageException.class, () -> {
	          Admin admin = new Admin();
	          admin.setAdmin_id(null);
	          Integer id = admin.getAdmin_id();
	          admin = repository.getOne(id);
	        });
	    }
}