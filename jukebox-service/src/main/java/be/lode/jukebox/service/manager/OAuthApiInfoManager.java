package be.lode.jukebox.service.manager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import be.lode.jukebox.business.OAuthApiInfo;
import be.lode.jukebox.business.repo.OAuthApiInfoRepository;
import be.lode.jukebox.business.repo.Repository;
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

	public OAuthApiInfoDTO getOAuthApiInfo(String name) {
		for (OAuthApiInfo oaai : oAuthrepo.getList()) {
			if (oaai.getName().equalsIgnoreCase(name))
				return modelMapper.map(oaai, OAuthApiInfoDTO.class);
		}
		return null;
	}

}
