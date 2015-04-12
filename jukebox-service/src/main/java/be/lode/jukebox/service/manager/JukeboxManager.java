package be.lode.jukebox.service.manager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import be.lode.jukebox.business.Account;
import be.lode.jukebox.business.repo.AccountRepository;
import be.lode.jukebox.business.repo.Repository;
import be.lode.jukebox.service.dto.AccountDTO;
import be.lode.jukebox.service.mapper.JukeboxModelMapper;
import be.lode.oauth.OAuthButton.IOAuthUser;

public class JukeboxManager {
	private EntityManagerFactory emf;
	private Repository<Account> accountRepo;
	private JukeboxModelMapper modelMapper;

	public JukeboxManager() {
		super();
		emf = Persistence.createEntityManagerFactory("jukebox-business");
		accountRepo = new AccountRepository(emf);
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

}
