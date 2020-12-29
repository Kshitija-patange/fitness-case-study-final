package com.capgemini.Zfitnessapp.controllerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import com.capgemini.Zfitnessapp.ZFitnessAppApplication;
import com.capgemini.fitness.dao.AppointmentDataDao;
import com.capgemini.fitness.entity.Appointment;
import com.capgemini.fitness.service.AppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ZFitnessAppApplication.class)
@AutoConfigureMockMvc 
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class AppointmentControllerTest {

	@MockBean
	private AppointmentService service;
	 @Autowired
	    private MockMvc mvc;
	 @MockBean
	 private AppointmentDataDao repository;
	 
	 @BeforeEach
		public void resetDb() {
			repository.deleteAll();
		}
	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	 
	 @Test
	    @DisplayName("POST /fitness/appointment")
	    void testCreatAppointment() throws Exception {
	        // Setup our mocked service
		 Appointment ap = new  Appointment(1,1,1,"female","yes",1000,"pune",LocalDate.parse("10/12/2020", formatter),null); 
	       mvc.perform(post("/fitness/appointment")
					.contentType(MediaType.APPLICATION_JSON)
					.content(JsonUtil.toJson(ap)));

			List<Appointment> found = repository.findAll();
			assertThat(found);
	 }
	       
	  
	 @Test
	    public void givenEmployees_whenGetEmployees_thenStatus200() throws Exception {
	        createTestAppointment(1);
	        createTestAppointment(2);

	        
	        mvc.perform(get("/fitness/appointment").contentType(MediaType.APPLICATION_JSON))
	          .andDo(print())
	          .andExpect(status().isOk())
	          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	          .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
	          .andExpect(jsonPath("$[0].appointment_id", is(1)))
	          .andExpect(jsonPath("$[1].appointment_id", is(2)));
	        
	    }

	   private void createTestAppointment(Integer id) {
	        Appointment ap = new Appointment(id);
	        repository.saveAndFlush(ap);
	    }    
	 
	 
	
	
		/*
		 * @Test
		 * 
		 * @DisplayName("GET /appointment success") void testGetAppointmentSuccess()
		 * throws Exception { // Setup our mocked service Appointment ap = new
		 * Appointment(); ap.setAppointment_id(1); ap.setUser_id(1);
		 * ap.setTrainer_id(1); ap.setTrainer_Preference("female");
		 * ap.setPhy_the("yes"); ap.setAmount(1000); ap.setLocation("pine");
		 * ap.setDate(LocalDate.parse("10/12/2020", formatter)); //ap.setUser(null);
		 * 
		 * Appointment ap1 = new Appointment(); ap1.setAppointment_id(2);
		 * ap1.setUser_id(2); ap1.setTrainer_id(2); ap1.setTrainer_Preference("female");
		 * ap1.setPhy_the("yes"); ap1.setAmount(1000); ap1.setLocation("pine");
		 * ap1.setDate(LocalDate.parse("10/12/2020", formatter)); // ap1.setUser(null);
		 * //Appointment widget2 = new Appointment (2l, "Widget 2 Name",
		 * "Description 2", 4);
		 * //doReturn(Lists.newArrayList(ap,ap1)).when(service).viewAppointment();
		 * when(service.viewAppointment()).thenReturn(Arrays.asList(ap,ap1)); // Execute
		 * the GET request mvc.perform(get("/fitness/appointment")) // Validate the
		 * response code and content type .andExpect(status().isOk())
		 * .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		 * 
		 * // Validate headers // .andExpect(header().string(HttpHeaders.LOCATION,
		 * "/fitness/appointment"))
		 * 
		 * // Validate the returned fields .andExpect(jsonPath("$", hasSize(2)))
		 * .andExpect(jsonPath("$[0].appointment_id", is(1)))
		 * .andExpect(jsonPath("$[0].user_id", is(1)))
		 * .andExpect(jsonPath("$[0].trainer_id", is(1)))
		 * .andExpect(jsonPath("$[0].trainer_Preference", is("female")))
		 * .andExpect(jsonPath("$[0].phy_the", is("yes")))
		 * .andExpect(jsonPath("$[0].amount", is(1000)))
		 * .andExpect(jsonPath("$[0].location", is("pine")))
		 * .andExpect(jsonPath("$[0].date", is("10/12/2020"))) //
		 * .andExpect(jsonPath("$[0].user", is(null)))
		 * .andExpect(jsonPath("$[1].appointment_id", is(2)))
		 * .andExpect(jsonPath("$[1].user_id", is(2)))
		 * .andExpect(jsonPath("$[1].trainer_id", is(2)))
		 * .andExpect(jsonPath("$[1].trainer_Preference", is("female")))
		 * .andExpect(jsonPath("$[1].phy_the", is("yes")))
		 * .andExpect(jsonPath("$[1].amount", is(1000)))
		 * .andExpect(jsonPath("$[1].location", is("pine")))
		 * .andExpect(jsonPath("$[1].date", is("10/12/2020"))); //
		 * .andExpect(jsonPath("$[1].user", is(null))); //
		 * .andExpect(jsonPath("$[1].id", is(2))) // .andExpect(jsonPath("$[1].name",
		 * is("Widget 2 Name"))) // .andExpect(jsonPath("$[1].description",
		 * is("Description 2"))) //.andExpect(jsonPath("$[1].version", is(4)));
		 * verify(service, times(1)).viewAppointment();
		 * verifyNoMoreInteractions(service);
		 * 
		 * }
		 * 
		 * static String asJsonString(final Object obj) { try { return new
		 * ObjectMapper().writeValueAsString(obj); } catch (Exception e) { throw new
		 * RuntimeException(e); } }
		 */

	  

}