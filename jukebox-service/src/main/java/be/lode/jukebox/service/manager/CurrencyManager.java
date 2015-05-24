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

/**
 * The Class CurrencyManager.
 */
public class CurrencyManager {

	/** The currency repo. */
	private Repository<Currency> currencyRepo;

	/** The entity manager factory. */
	private EntityManagerFactory emf;

	/** The model mapper. */
	private JukeboxModelMapper modelMapper;

	/**
	 * Instantiates a new currency manager.
	 */
	public CurrencyManager() {
		super();
		emf = Persistence.createEntityManagerFactory("jukebox-business");
		currencyRepo = new CurrencyRepository(emf);
		modelMapper = new JukeboxModelMapper();
	}

	/**
	 * Instantiates a new currency manager.
	 *
	 * @param emf
	 *            the emf
	 */
	public CurrencyManager(EntityManagerFactory emf) {
		super();
		this.emf = emf;
		currencyRepo = new CurrencyRepository(emf);
		modelMapper = new JukeboxModelMapper();
	}

	/**
	 * Gets the currency.
	 *
	 * @param payPalCurrencyCode
	 *            the pay pal currency code
	 * @return the currency
	 */
	public CurrencyDTO getCurrency(String payPalCurrencyCode) {
		Currency cur = new Currency(payPalCurrencyCode, "");
		cur = currencyRepo.findEquals(cur);
		return modelMapper.map(cur, CurrencyDTO.class);
	}

	/**
	 * Gets the currency list.
	 *
	 * @return the currency list
	 */
	public List<CurrencyDTO> getCurrencyList() {
		return map(currencyRepo.getList());
	}

	/**
	 * Map.
	 *
	 * @param list
	 *            the list
	 * @return the list
	 */
	private List<CurrencyDTO> map(List<Currency> list) {
		List<CurrencyDTO> retList = new ArrayList<CurrencyDTO>();
		for (Currency currency : list) {
			retList.add(modelMapper.map(currency, CurrencyDTO.class));
		}
		return retList;
	}
}
