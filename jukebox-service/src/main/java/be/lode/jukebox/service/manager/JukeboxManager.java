package be.lode.jukebox.service.manager;

import java.util.ArrayList;
import java.util.List;

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

public class JukeboxManager {
	private EntityManagerFactory emf;
	private Repository<Account> accountRepo;
	private Repository<Jukebox> jukeboxRepo;
	private JukeboxModelMapper modelMapper;

	public JukeboxManager() {
		super();
		emf = Persistence.createEntityManagerFactory("jukebox-business");
		accountRepo = new AccountRepository(emf);
		jukeboxRepo = new JukeboxRepository(emf);
		modelMapper = new JukeboxModelMapper();
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

		Account acc = modelMapper.map(o, Account.class);

		for (Account accItem : accountRepo.getList()) {
			if (accItem.equals(acc))
				return modelMapper.map(accItem, AccountDTO.class);
		}
		accountRepo.save(acc);
		return o;
	}

	// FIXME testing getJukeboxes
	public List<JukeboxDTO> getJukeboxes(AccountDTO acc) {
		// FIXME create getjukeboxes
		return new ArrayList<JukeboxDTO>();

	}

}
