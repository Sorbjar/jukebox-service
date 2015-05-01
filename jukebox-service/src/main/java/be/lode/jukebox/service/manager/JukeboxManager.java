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
import be.lode.jukebox.business.model.SongContainer;
import be.lode.jukebox.business.model.enums.Role;
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

//TODO 700 manager uit elkaar halen
public class JukeboxManager extends Observable {
	private Repository<Account> accountRepo;
	private Jukebox currentJukebox;
	private Song currentSong;
	private int currentSongInt;
	private EntityManagerFactory emf;
	private Repository<Jukebox> jukeboxRepo;
	private JukeboxModelMapper modelMapper;
	private Repository<Playlist> playlistRepo;
	private Repository<Song> songRepo;
	private boolean mandatory;

	public JukeboxManager() {
		super();
		emf = Persistence.createEntityManagerFactory("jukebox-business");
		accountRepo = new AccountRepository(emf);
		jukeboxRepo = new JukeboxRepository(emf);
		playlistRepo = new PlaylistRepository(emf);
		songRepo = new SongRepository(emf);
		modelMapper = new JukeboxModelMapper();
		mandatory = false;
	}

	public void addSong(SongDTO sourceItemId) {
		Song source = modelMapper.map(sourceItemId, Song.class);
		currentJukebox.getCurrentPlaylist().addSong(source);

		Playlist cpl = playlistRepo.save(currentJukebox.getCurrentPlaylist());
		setCurrentPlaylist(cpl);
		setChanged();
		notifyObservers(UpdateArgs.CURRENT_PLAYLIST);

	}

	public void addSong(SongDTO sourceItemId, SongDTO targetItemId) {
		Song source = modelMapper.map(sourceItemId, Song.class);
		currentJukebox.getCurrentPlaylist().addSong(source);
		currentJukebox.getCurrentPlaylist().moveSong(
				currentJukebox.getCurrentPlaylist().getSongs().lastKey(),
				Integer.parseInt(targetItemId.getPlayListOrder()));

		Playlist cpl = playlistRepo.save(currentJukebox.getCurrentPlaylist());
		setCurrentPlaylist(cpl);

		setChanged();
		notifyObservers(UpdateArgs.CURRENT_PLAYLIST);
	}

	public void changeLoopState() {
		currentJukebox.setLooped(!currentJukebox.isLooped());
		setChanged();
		notifyObservers(UpdateArgs.CURRENT_JUKEBOX);
	}

	public void changeRandomState() {
		currentJukebox.setRandom(!currentJukebox.isRandom());
		setChanged();
		notifyObservers(UpdateArgs.CURRENT_JUKEBOX);
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

	public void deletePlaylist(PlaylistDTO playlistDTO) {
		if (currentJukebox == null)
			currentJukebox = new Jukebox("Unsaved Jukebox", new Account());
		Playlist pl = modelMapper.map(playlistDTO, Playlist.class);
		if (playlistRepo.find(pl) == null)
			pl = playlistRepo.save(pl);
		pl = playlistRepo.find(pl);
		currentJukebox.getSavedPlaylists().remove(pl);
		currentJukebox = jukeboxRepo.save(currentJukebox);
		setChanged();
		notifyObservers(UpdateArgs.CURRENT_JUKEBOX);

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
		if (currentJukebox != null) {
			if (currentJukebox.getCurrentPlaylist() == null) {
				Playlist cpl = new Playlist("Unsaved playlist");
				setCurrentPlaylist(cpl);
			}
			return modelMapper.map(currentJukebox.getCurrentPlaylist(),
					PlaylistDTO.class);
		}
		return null;
	}

	public SongDTO getCurrentSongDTO() {
		if (currentSong != null) {
			SongDTO dto = modelMapper.map(currentSong, SongDTO.class);
			dto.setPlayListOrder(String.valueOf(currentSongInt));
			dto.setMandatory(String.valueOf(mandatory));
			return dto;
		}
		return null;
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public List<JukeboxDTO> getJukeboxes(AccountDTO dto) {
		ArrayList<JukeboxDTO> retList = new ArrayList<JukeboxDTO>();
		if (dto != null) {
			for (Jukebox jbItem : jukeboxRepo.getList()) {
				Account acc = modelMapper.map(dto, Account.class);
				if (jbItem.getAccountRoles().containsKey(acc))
				{
					Role role = jbItem.getAccountRoles().get(acc);
					if(role.equals(Role.Administrator) || role.equals(Role.Manager))
						retList.add(modelMapper.map(jbItem, JukeboxDTO.class));
				}
			}
		}
		return retList;
	}

	public PlaylistDTO getMandatoryPlaylistDTO() {
		if (currentJukebox != null) {
			if (currentJukebox.getMandatoryPlaylist() == null) {
				Playlist cpl = new Playlist("Mandatory");
				currentJukebox.setMandatoryPlaylist(cpl);
			}
			return modelMapper.map(currentJukebox.getMandatoryPlaylist(),
					PlaylistDTO.class);
		}
		return null;
	}

	public SongDTO getNextSong() {
		if (currentJukebox != null) {
			removeCurrentSongFromMandatoryPlaylist();
			SongContainer sc = currentJukebox.getNextSong(currentSongInt);
			if (sc != null) {
				SongDTO dto = modelMapper.map(sc.getSong(), SongDTO.class);
				currentSongInt = sc.getPlaylistOrder();
				mandatory = sc.getMandatory();
				dto.setPlayListOrder(String.valueOf(currentSongInt));
				setCurrentSong(dto);
				return dto;
			}
		}
		return null;
	}

	private void removeCurrentSongFromMandatoryPlaylist() {
		if (currentJukebox != null && mandatory) {
			currentJukebox.removeMandatorySong(currentSong, currentSongInt);
		}

	}

	public SongDTO getPreviousSong() {
		if (currentJukebox != null) {
			removeCurrentSongFromMandatoryPlaylist();
			SongContainer sc = currentJukebox.getPreviousSong(currentSongInt);
			if (sc != null) {
				SongDTO dto = modelMapper.map(sc.getSong(), SongDTO.class);
				currentSongInt = sc.getPlaylistOrder();
				mandatory = sc.getMandatory();
				dto.setPlayListOrder(String.valueOf(currentSongInt));
				setCurrentSong(dto);
				return dto;
			}
		}
		return null;
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
			if (pl != null) {
				for (Map.Entry<Integer, Song> entry : pl.getSongs().entrySet()) {
					Song value = entry.getValue();
					SongDTO dto = modelMapper.map(value, SongDTO.class);
					dto.setPlayListOrder(entry.getKey().toString());
					ret.add(dto);
				}
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

	public boolean isLooped() {
		if (currentJukebox != null)
			return currentJukebox.isLooped();
		return false;
	}

	public boolean isRandom() {
		if (currentJukebox != null)
			return currentJukebox.isRandom();
		return false;
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

	public void removeSongFromCurrentPlaylist(SongDTO song) {
		currentJukebox.getCurrentPlaylist().removeSong(
				Integer.parseInt(song.getPlayListOrder()));
		currentJukebox = jukeboxRepo.save(currentJukebox);
		setChanged();
		notifyObservers(UpdateArgs.CURRENT_PLAYLIST);
	}

	public void reorderPlaylist(SongDTO source, SongDTO target) {
		currentJukebox.getCurrentPlaylist().moveSong(
				Integer.parseInt(source.getPlayListOrder()),
				Integer.parseInt(target.getPlayListOrder()));
		Playlist cpl = playlistRepo.save(currentJukebox.getCurrentPlaylist());
		setCurrentPlaylist(cpl);
		setChanged();
		notifyObservers(UpdateArgs.CURRENT_PLAYLIST);
	}

	public AccountDTO save(AccountDTO dto) {
		Account acc = accountRepo.save(modelMapper.map(dto, Account.class));
		return modelMapper.map(acc, AccountDTO.class);
	}

	public JukeboxDTO save(JukeboxDTO dto) {
		Jukebox jb = jukeboxRepo.find(modelMapper.map(dto, Jukebox.class));
		jb = updateEditedFields(jb, dto);
		currentJukebox = jukeboxRepo.save(jb);
		setChanged();
		notifyObservers(UpdateArgs.CURRENT_JUKEBOX);
		return modelMapper.map(jb, JukeboxDTO.class);
	}

	public void saveCurrentPlayListToJukebox() {
		Playlist cpl = playlistRepo.save(currentJukebox.getCurrentPlaylist());
		Playlist copy = copyPlaylist(cpl);
		currentJukebox.removePlaylist(cpl);
		currentJukebox = jukeboxRepo.save(currentJukebox);
		currentJukebox.getSavedPlaylists().add(copy);
		currentJukebox = jukeboxRepo.save(currentJukebox);
		setCurrentPlaylist(cpl);
		setChanged();
		notifyObservers(UpdateArgs.CURRENT_JUKEBOX);
		notifyObservers(UpdateArgs.CURRENT_PLAYLIST);
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

	public void setCurrentPlaylist(Playlist playlist) {
		if (playlistRepo.find(playlist) == null)
			playlist = playlistRepo.save(playlist);
		playlist = playlistRepo.find(playlist);
		currentJukebox.setCurrentPlaylist(playlist);
		setChanged();
		notifyObservers(UpdateArgs.CURRENT_PLAYLIST);
	}

	public void setCurrentPlaylist(PlaylistDTO playlistDTO) {
		if (currentJukebox == null) {
			Account acc = new Account();
			acc = accountRepo.save(acc);
			currentJukebox = new Jukebox("Unsaved Jukebox", acc);
		}
		Playlist pl = modelMapper.map(playlistDTO, Playlist.class);
		if (playlistRepo.find(pl) == null)
			pl = playlistRepo.save(pl);
		pl = playlistRepo.find(pl);
		pl = playlistRepo.save(pl);
		Playlist copy = copyPlaylist(pl);
		copy = playlistRepo.save(copy);
		currentJukebox.setCurrentPlaylist(copy);
		currentJukebox = jukeboxRepo.save(currentJukebox);
		setChanged();
		notifyObservers(UpdateArgs.CURRENT_PLAYLIST);
	}

	public void setCurrentPlaylistName(String name) {
		currentJukebox.getCurrentPlaylist().setName(name);
		Playlist cpl = playlistRepo.save(currentJukebox.getCurrentPlaylist());
		setCurrentPlaylist(cpl);
		setChanged();
		notifyObservers(UpdateArgs.CURRENT_PLAYLIST);
	}

	public void setCurrentSong(SongDTO songDTO) {
		Song song = modelMapper.map(songDTO, Song.class);
		song = songRepo.find(song);
		currentSong = song;
		mandatory = Boolean.parseBoolean(songDTO.getMandatory());
		try {
			currentSongInt = Integer.parseInt(songDTO.getPlayListOrder());
		} catch (NumberFormatException ex) {
			currentSongInt = 0;
		}
	}

	public void setNewCurrentPlaylist(SongDTO sDTO) {
		Song s = modelMapper.map(sDTO, Song.class);
		setCurrentPlaylist(new Playlist("Unsaved playlist"));

		currentJukebox.getCurrentPlaylist().addSong(s);
		Playlist cpl = playlistRepo.save(currentJukebox.getCurrentPlaylist());
		setCurrentPlaylist(cpl);
		setChanged();
		notifyObservers(UpdateArgs.CURRENT_PLAYLIST);
	}

	private Playlist copyPlaylist(Playlist cpl) {
		Playlist copy = new Playlist(cpl.getName());
		copy.setSongs(cpl.getSongs());
		copy = playlistRepo.save(copy);
		return copy;
	}

	private AccountDTO findAccountFromList(AccountDTO o) {
		Account acc = modelMapper.map(o, Account.class);
		for (Account accItem : accountRepo.getList()) {
			if (accItem.equals(acc))
				return modelMapper.map(accItem, AccountDTO.class);
		}
		return modelMapper.map(accountRepo.save(acc), AccountDTO.class);
	}

	private Jukebox updateEditedFields(Jukebox jb, JukeboxDTO dto) {
		Jukebox newFields = modelMapper.map(dto, Jukebox.class);
		if (jb != null) {
			if (jb.getAccountRoles() != null)
				newFields.setAccountRoles(jb.getAccountRoles());
			if (jb.getSavedPlaylists() != null)
				newFields.setSavedPlaylists(jb.getSavedPlaylists());
		}
		return newFields;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public AccountDTO getAccount(String serviceName, String serviceId) {
		for (Account acc : accountRepo.getList()) {
			if (acc.getServiceName().equals(serviceName)
					&& acc.getServiceId().equals(serviceId))
				return modelMapper.map(acc, AccountDTO.class);
		}
		return null;
	}
}