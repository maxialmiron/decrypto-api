package com.apidecrypto.apidecrypto;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.apidecrypto.model.Market;
import com.apidecrypto.model.Principal;
import com.apidecrypto.repository.repository.PrincipalRepository;

@SpringBootTest
@AutoConfigureMockMvc
class PrincipalControllerTest {

	@Autowired
	private PrincipalRepository principalRepository;

	@Autowired
	private MockMvc mockMvc;

//	@Test
	public void givenStudents_whenGetAllStudents_thenListOfStudents() throws Exception {
		// given - setup or precondition
		List<Principal> principals;
				
		Principal p1 = new Principal();
		p1.setDescription("desc");
		p1.setMarket(null);
		Market market = new Market();
		market.setCode("MAE");
		market.setDescription("desc");
		
		principalRepository.save(p1);

		// when - action
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/principal"));

		// then - verify the output
		response.andExpect(MockMvcResultMatchers.status().isOk());
	}

}