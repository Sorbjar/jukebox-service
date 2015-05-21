package be.lode.jukebox.service.mapper.maps;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import be.lode.jukebox.business.model.Account;
import be.lode.jukebox.business.model.enums.Role;
import be.lode.jukebox.service.dto.SecurityAccountDTO;
import be.lode.jukebox.service.mapper.JukeboxModelMapper;
import be.lode.setup.ResetDBSetupLiveData;
import be.lode.setup.ResetDBSetupTestData;

public class SecurityAccountMapTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ResetDBSetupTestData.run();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ResetDBSetupLiveData.run();
	}

	private JukeboxModelMapper modelMapper;

	@Before
	public void setUp() throws Exception {
		modelMapper = new JukeboxModelMapper();
	}

	@Test
	public void mapObjToDTO() {

		Account o = new Account("mapObjToDTOemailAddress",
				"mapObjToDTOfirstName", "smapObjToDTOlastName", "123",
				"facebook");
		o.setId(10);

		SecurityAccountDTO oDTO = modelMapper.map(o, SecurityAccountDTO.class);

		assertEquals(String.valueOf(o.getId()), oDTO.getId());
		assertEquals(o.getEmailAddress(), oDTO.getEmailAddress());
		assertEquals(o.getFirstName(), oDTO.getFirstName());
		assertEquals(o.getLastName(), oDTO.getLastName());
		assertEquals(o.getServiceId(), oDTO.getServiceId());
		assertEquals(o.getServiceName(), oDTO.getServiceName());
		assertEquals("", oDTO.getRole());
		assertEquals(o.getFirstName() + " " + o.getLastName(), oDTO.getFullName());
	}

	@Test
	public void mapDTOToObj() {
		SecurityAccountDTO oDTO = new SecurityAccountDTO();
		oDTO.setEmailAddress("emailAddress");
		oDTO.setFirstName("firstName");
		oDTO.setLastName("lastName");
		oDTO.setServiceId("serviceId");
		oDTO.setServiceName("serviceName");
		oDTO.setRole("Manager");
		oDTO.setId("10");

		Account o = modelMapper.map(oDTO, Account.class);

		assertEquals(Long.valueOf(oDTO.getId()).longValue(), o.getId());
		assertEquals(oDTO.getEmailAddress(), o.getEmailAddress());
		assertEquals(oDTO.getFirstName(), o.getFirstName());
		assertEquals(oDTO.getLastName(), o.getLastName());
		assertEquals(oDTO.getServiceId(), o.getServiceId());
		assertEquals(oDTO.getServiceName(), o.getServiceName());
		assertEquals(oDTO.getRole(), Role.Manager.toString());
	}

}
