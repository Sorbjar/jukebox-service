package be.lode.jukebox.service.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import be.lode.general.repository.Repository;
import be.lode.jukebox.business.Account;
import be.lode.jukebox.business.Jukebox;
import be.lode.jukebox.business.repo.AccountRepository;
import be.lode.jukebox.business.repo.JukeboxRepository;
import be.lode.jukebox.service.dto.AccountDTO;
import be.lode.jukebox.service.dto.JukeboxDTO;
import be.lode.jukebox.service.mapper.JukeboxModelMapper;
import be.lode.oauth.OAuthButton.IOAuthUser;

public class JukeboxManager extends Observable {
	private Repository<Account> accountRepo;
	private EntityManagerFactory emf;
	private Repository<Jukebox> jukeboxRepo;
	private JukeboxModelMapper modelMapper;

	public JukeboxManager() {
		super();
		emf = Persistence.createEntityManagerFactory("jukebox-business");
		accountRepo = new AccountRepository(emf);
		jukeboxRepo = new JukeboxRepository(emf);
		modelMapper = new JukeboxModelMapper();
	}

	public AccountDTO getAccount(AccountDTO loggedInAccount) {
		return findAccountFromList(loggedInAccount);
	}

	public List<JukeboxDTO> getJukeboxes(AccountDTO acc) {
		// TODO check speed, if required change to stored procedure
		ArrayList<JukeboxDTO> retList = new ArrayList<JukeboxDTO>();
		if (acc != null) {
			for (Jukebox jbItem : jukeboxRepo.getList()) {
				if (jbItem.getAccountRoles().containsKey(
						modelMapper.map(acc, Account.class)))
					retList.add(modelMapper.map(jbItem, JukeboxDTO.class));
			}
		}
		return retList;
	}

	public AccountDTO getUser(IOAuthUser user) {
		// TODO issue with modelmapper
		// o = modelMapper.map( user, AccountDTO.class);
		AccountDTO o = new AccountDTO();
		o.setEmailAddress(user.getEmail());
		o.setFirstName(user.getName());
		o.setLastName(user.getName());
		o.setServiceId(user.getId());
		o.setServiceName(user.getService());

		return findAccountFromList(o);
	}

	public boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}

	public AccountDTO save(AccountDTO dto) {
		Account acc = accountRepo.save(modelMapper.map(dto, Account.class));
		return modelMapper.map(acc, AccountDTO.class);
	}

	// TODO test speed, if needed change method
	private AccountDTO findAccountFromList(AccountDTO o) {
		Account acc = modelMapper.map(o, Account.class);
		for (Account accItem : accountRepo.getList()) {
			if (accItem.equals(acc))
				return modelMapper.map(accItem, AccountDTO.class);
		}
		return modelMapper.map(accountRepo.save(acc), AccountDTO.class);
	}

}
