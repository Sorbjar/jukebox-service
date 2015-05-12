package be.lode.jukebox.service.manager;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import be.lode.general.repository.Repository;
import be.lode.jukebox.business.model.Currency;
import be.lode.jukebox.business.repo.CurrencyRepository;
import be.lode.jukebox.service.dto.CurrencyDTO;
import be.lode.jukebox.service.mapper.JukeboxModelMapper;

public class CurrencyManager {
	private EntityManagerFactory emf;
	private Repository<Currency> currencyRepo;
	private JukeboxModelMapper modelMapper;

	public CurrencyManager() {
		super();
		emf = Persistence.createEntityManagerFactory("jukebox-business");
		currencyRepo = new CurrencyRepository(emf);
		modelMapper = new JukeboxModelMapper();
	}

	public CurrencyManager(EntityManagerFactory emf) {
		super();
		this.emf = emf;
		currencyRepo = new CurrencyRepository(emf);
		modelMapper = new JukeboxModelMapper();
	}

	public List<CurrencyDTO> getCurrencyList() {
		return map(currencyRepo.getList());
	}

	private List<CurrencyDTO> map(List<Currency> list) {
		List<CurrencyDTO> retList = new ArrayList<CurrencyDTO>();
		for (Currency currency : list) {
			retList.add(modelMapper.map(currency, CurrencyDTO.class));
		}
		return retList;
	}

	public CurrencyDTO getCurrency(String payPalCurrencyCode) {
		Currency cur = new Currency(payPalCurrencyCode,"");
		cur = currencyRepo.findEquals(cur);
		return modelMapper.map(cur, CurrencyDTO.class);
	}
}
