package be.lode.jukebox.service.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import be.lode.general.repository.Repository;
import be.lode.jukebox.business.model.Account;
import be.lode.jukebox.business.model.Jukebox;
import be.lode.jukebox.business.model.Playlist;
import be.lode.jukebox.business.model.Song;
import be.lode.jukebox.business.repo.AccountRepository;
import be.lode.jukebox.business.repo.JukeboxRepository;
import be.lode.jukebox.business.repo.PlaylistRepository;
import be.lode.jukebox.business.repo.SongRepository;
import be.lode.jukebox.service.UpdateArgs;
import be.lode.jukebox.service.dto.AccountDTO;
import be.lode.jukebox.service.dto.JukeboxDTO;
import be.lode.jukebox.service.dto.PlaylistDTO;
import be.lode.jukebox.service.dto.SongDTO;
import be.lode.jukebox.service.mapper.JukeboxModelMapper;
import be.lode.oauth.OAuthButton.IOAuthUser;

public class JukeboxManager extends Observable {
	private Repository<Account> accountRepo;
	private Jukebox currentJukebox;
	private Playlist currentPlaylist;
	private EntityManagerFactory emf;
	private Repository<Jukebox> jukeboxRepo;
	private JukeboxModelMapper modelMapper;
	private Repository<Playlist> playlistRepo;
	private Repository<Song> songRepo;

	public JukeboxManager() {
		super();
		emf = Persistence.createEntityManagerFactory("jukebox-business");
		accountRepo = new AccountRepository(emf);
		jukeboxRepo = new JukeboxRepository(emf);
		playlistRepo = new PlaylistRepository(emf);
		songRepo = new SongRepository(emf);
		modelMapper = new JukeboxModelMapper();
	}

	public void createNewJukebox(AccountDTO oDTO) {
		Account acc = modelMapper.map(oDTO, Account.class);
		Jukebox jb = new Jukebox(acc);
		jb = jukeboxRepo.save(jb);
		currentJukebox = jb;
		setChanged();
		notifyObservers(UpdateArgs.CURRENT_JUKEBOX);
	}

	public void deleteJukebox(JukeboxDTO jbDto) {
		Jukebox jb = modelMapper.map(jbDto, Jukebox.class);
		jukeboxRepo.delete(jb);
		setChanged();
		notifyObservers(UpdateArgs.JUKEBOXLIST);
	}

	public AccountDTO getAccount(AccountDTO loggedInAccount) {
		return findAccountFromList(loggedInAccount);
	}

	public List<SongDTO> getAllSongs() {
		List<SongDTO> ret = new ArrayList<SongDTO>();
		for (Song s : songRepo.getList()) {
			ret.add(modelMapper.map(s, SongDTO.class));
		}
		return ret;
	}

	public JukeboxDTO getCurrentJukeboxDTO() {
		if (currentJukebox != null)
			return modelMapper.map(currentJukebox, JukeboxDTO.class);
		return null;
	}

	public PlaylistDTO getCurrentPlaylistDTO() {
		if (currentPlaylist != null)
			return modelMapper.map(currentPlaylist, PlaylistDTO.class);
		return null;
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public List<JukeboxDTO> getJukeboxes(AccountDTO acc) {
		// TODO 900 check speed, if required change to stored procedure
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

	public List<PlaylistDTO> getSavedPlaylists(JukeboxDTO jukeboxDTO) {
		Jukebox jb = modelMapper.map(jukeboxDTO, Jukebox.class);
		jb = jukeboxRepo.find(jb);
		List<PlaylistDTO> ret = new ArrayList<PlaylistDTO>();
		for (Playlist pl : jb.getSavedPlaylists()) {
			ret.add(modelMapper.map(pl, PlaylistDTO.class));
		}
		return ret;
	}

	public List<SongDTO> getSongs(PlaylistDTO playlistDTO) {

		List<SongDTO> ret = new ArrayList<SongDTO>();
		if (playlistDTO != null) {
			Playlist pl = modelMapper.map(playlistDTO, Playlist.class);
			pl = playlistRepo.find(pl);
			for (Map.Entry<Integer, Song> entry : pl.getSongs().entrySet()) {
				Song value = entry.getValue();
				ret.add(modelMapper.map(value, SongDTO.class));
			}
		}
		return ret;
	}

	public AccountDTO getUser(IOAuthUser user) {
		// TODO 860 issue with modelmapper
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

	public void setCurrentJukebox(JukeboxDTO selectedJukebox) {
		Jukebox jb = modelMapper.map(selectedJukebox, Jukebox.class);
		if (jukeboxRepo.find(jb) == null)
			jb = jukeboxRepo.save(jb);
		jb = jukeboxRepo.find(jb);
		this.currentJukebox = jb;
		setChanged();
		notifyObservers(UpdateArgs.CURRENT_JUKEBOX);

	}

	public void setCurrentPlaylist(PlaylistDTO playlistDTO) {
		Playlist pl = modelMapper.map(playlistDTO, Playlist.class);
		if (playlistRepo.find(pl) == null)
			pl = playlistRepo.save(pl);
		pl = playlistRepo.find(pl);
		this.currentPlaylist = pl;
		setChanged();
		notifyObservers(UpdateArgs.CURRENT_PLAYLIST);

	}

	// TODO 900 test speed, if needed change method
	private AccountDTO findAccountFromList(AccountDTO o) {
		Account acc = modelMapper.map(o, Account.class);
		for (Account accItem : accountRepo.getList()) {
			if (accItem.equals(acc))
				return modelMapper.map(accItem, AccountDTO.class);
		}
		return modelMapper.map(accountRepo.save(acc), AccountDTO.class);
	}

}
