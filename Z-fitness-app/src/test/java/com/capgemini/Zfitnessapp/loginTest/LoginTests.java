package com.capgemini.Zfitnessapp.loginTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import com.capgemini.fitness.entity.LogOutPayload;
import com.capgemini.fitness.entity.Login;

class LoginTests {
    /*
     * Sign in 
     */
	@Test
	public void testSignIn() {
		Login user = mock(Login.class);
		when(user.getId()).thenReturn(05);
		assertEquals(user.getId(),05);
		when(user.getPassword()).thenReturn("abc@01");
		assertEquals(user.getPassword(),"abc@01");
	
	}
	
	/*
	 * sign out
	 */
	 @Test
	  void testSignOut() { 
		 LogOutPayload user = mock(LogOutPayload.class);
	 when(user.getId()).thenReturn(02);
	 assertEquals(user.getId(),02);
	}
	
	
	
}