package be.lode.jukebox.service.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import be.lode.jukebox.business.Account;
import be.lode.jukebox.business.OAuthApiInfo;
import be.lode.jukebox.service.dto.AccountDTO;
import be.lode.jukebox.service.dto.OAuthApiInfoDTO;

public class JukeboxModelMapperTest {
	private JukeboxModelMapper modelMapper;

	@Before
	public void setUp() throws Exception {
		modelMapper = new JukeboxModelMapper();
	}

	@Test
	public void mapOAuthApiInfoValidate() {
		// modelMapper.createTypeMap(OAuthApiInfo.class, OAuthApiInfoDTO.class);
		modelMapper.validate();
	}

	@Test
	public void mapOAuthApiInfoToDTO() {

		OAuthApiInfo o = new OAuthApiInfo("TestName", "FacebookApi", "TestKey",
				"TestSecret", "TestURL");

		OAuthApiInfoDTO oDTO = modelMapper.map(o, OAuthApiInfoDTO.class);
		assertEquals(o.getApiKey(), oDTO.getApiKey());
		assertEquals(o.getApiSecret(), oDTO.getApiSecret());
		assertEquals(o.getExampleGetRequest(), oDTO.getExampleGetRequest());
		assertEquals(o.getName(), oDTO.getName());
		assertEquals(o.getScribeApiName(), oDTO.getScribeApiName());
	}

	@Test
	public void mapOAuthApiInfoToObj() {
		// no test, since no setters are defined for object
	}

	@Test
	public void testJukeboxModelMapper() {
		// no test, constructor adds maps, these are tested in actual mappings
	}

	@Test
	public void mapOAuthUserToAccountDTO() {
		// can't be tested, since it's get built by facebook service
	}

	@Test
	public void mapAccountDTOToOAuthUser() {
		// no test, since no setters are defined for object
	}

	@Test
	public void mapAccountToDTO() {

		Account o = new Account("email", "firstname", "lastName", "serviceId",
				"serviceName");
		o.setId(10);

		AccountDTO oDTO = modelMapper.map(o, AccountDTO.class);

		assertEquals(String.valueOf(o.getId()), oDTO.getId());
		assertEquals(o.getEmailAddress(), oDTO.getEmailAddress());
		assertEquals(o.getFirstName(), oDTO.getFirstName());
		assertEquals(o.getLastName(), oDTO.getLastName());
		assertEquals(o.getServiceId(), oDTO.getServiceId());
		assertEquals(o.getServiceName(), oDTO.getServiceName());
	}
	
	@Test
	public void mapAccountDTOToObj() {
		AccountDTO oDTO = new AccountDTO();
		oDTO.setEmailAddress("emailAddress");
		oDTO.setFirstName("firstName");
		oDTO.setLastName("lastName");
		oDTO.setServiceId("serviceId");
		oDTO.setServiceName("serviceName");

		oDTO.setId("10");

		Account o = modelMapper.map(oDTO, Account.class);

		assertEquals(Long.valueOf(oDTO.getId()).longValue(), o.getId());
		assertEquals(oDTO.getEmailAddress(), o.getEmailAddress());
		assertEquals(oDTO.getFirstName(), o.getFirstName());
		assertEquals(oDTO.getLastName(), o.getLastName());
		assertEquals(oDTO.getServiceId(), o.getServiceId());
		assertEquals(oDTO.getServiceName(), o.getServiceName());
	}
}
