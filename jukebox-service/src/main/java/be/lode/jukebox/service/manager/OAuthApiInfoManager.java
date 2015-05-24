package be.lode.jukebox.service.manager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import be.lode.general.repository.Repository;
import be.lode.jukebox.business.model.OAuthApiInfo;
import be.lode.jukebox.business.repo.OAuthApiInfoRepository;
import be.lode.jukebox.service.dto.OAuthApiInfoDTO;
import be.lode.jukebox.service.mapper.JukeboxModelMapper;

/**
 * The Class OAuthApiInfoManager.
 */
public class OAuthApiInfoManager {

	/** The entity manager factory. */
	private EntityManagerFactory emf;

	/** The model mapper. */
	private JukeboxModelMapper modelMapper;

	/** The o authrepo. */
	private Repository<OAuthApiInfo> oAuthrepo;

	/**
	 * Instantiates a new o auth api info manager.
	 */
	public OAuthApiInfoManager() {
		super();
		emf = Persistence.createEntityManagerFactory("jukebox-business");
		oAuthrepo = new OAuthApiInfoRepository(emf);
		modelMapper = new JukeboxModelMapper();
	}

	/**
	 * Instantiates a new o auth api info manager.
	 *
	 * @param emf
	 *            the entity manager factory
	 */
	public OAuthApiInfoManager(EntityManagerFactory emf) {
		super();
		this.emf = emf;
		oAuthrepo = new OAuthApiInfoRepository(emf);
		modelMapper = new JukeboxModelMapper();
	}

	/**
	 * Gets the o auth api info.
	 *
	 * @param name
	 *            the name
	 * @return the o auth api info
	 */
	public OAuthApiInfoDTO getOAuthApiInfo(String name) {
		// name is primary key, so find works
		OAuthApiInfo oa = oAuthrepo.find(OAuthApiInfo.class, name);
		return modelMapper.map(oa, OAuthApiInfoDTO.class);
	}

}
