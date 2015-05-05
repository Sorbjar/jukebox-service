package be.lode.jukebox.service.manager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import be.lode.general.repository.Repository;
import be.lode.jukebox.business.model.OAuthApiInfo;
import be.lode.jukebox.business.repo.OAuthApiInfoRepository;
import be.lode.jukebox.service.dto.OAuthApiInfoDTO;
import be.lode.jukebox.service.mapper.JukeboxModelMapper;

public class OAuthApiInfoManager {
	private EntityManagerFactory emf;
	private Repository<OAuthApiInfo> oAuthrepo;
	private JukeboxModelMapper modelMapper;

	public OAuthApiInfoManager() {
		super();
		emf = Persistence.createEntityManagerFactory("jukebox-business");
		oAuthrepo = new OAuthApiInfoRepository(emf);
		modelMapper = new JukeboxModelMapper();
	}

	public OAuthApiInfoManager(EntityManagerFactory emf) {
		super();
		this.emf = emf;
		oAuthrepo = new OAuthApiInfoRepository(emf);
		modelMapper = new JukeboxModelMapper();
	}

	public OAuthApiInfoDTO getOAuthApiInfo(String name) {
		// name is primary key, so find works
		OAuthApiInfo oa = oAuthrepo.find(OAuthApiInfo.class, name);
		return modelMapper.map(oa, OAuthApiInfoDTO.class);
	}

}
