package be.lode.jukebox.service.manager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import be.lode.general.repository.Repository;
import be.lode.jukebox.business.model.Account;
import be.lode.jukebox.business.model.Currency;
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
import be.lode.jukebox.service.dto.CurrencyDTO;
import be.lode.jukebox.service.dto.JukeboxDTO;
import be.lode.jukebox.service.dto.PayPalSettingsDTO;
import be.lode.jukebox.service.dto.PlaylistDTO;
import be.lode.jukebox.service.dto.SecurityAccountDTO;
import be.lode.jukebox.service.dto.SongDTO;
import be.lode.jukebox.service.mapper.JukeboxModelMapper;
import be.lode.jukebox.service.output.PDFStream;
import be.lode.jukebox.service.output.QR;
import be.lode.jukebox.service.output.QRStream;
import be.lode.oauth.OAuthButton.IOAuthUser;

import com.vaadin.server.StreamResource.StreamSource;

/**
 * The Class JukeboxManager.
 */
public class JukeboxManager extends Observable {

	/** The account repo. */
	private Repository<Account> accountRepo;

	/** The current jukebox. */
	private Jukebox currentJukebox;

	/** The current song. */
	private Song currentSong;

	/** The current song int. */
	private int currentSongInt;

	/** The entity manager factory. */
	private EntityManagerFactory emf;

	/** The jukebox repo. */
	private Repository<Jukebox> jukeboxRepo;

	/** The mandatory. */
	private boolean mandatory;

	/** The model mapper. */
	private JukeboxModelMapper modelMapper;

	/** The playlist repo. */
	private Repository<Playlist> playlistRepo;

	/** The song repo. */
	private Repository<Song> songRepo;

	/**
	 * Instantiates a new jukebox manager.
	 */
	public JukeboxManager() {
		super();
		this.emf = Persistence.createEntityManagerFactory("jukebox-business");
		accountRepo = new AccountRepository(emf);
		jukeboxRepo = new JukeboxRepository(emf);
		playlistRepo = new PlaylistRepository(emf);
		songRepo = new SongRepository(emf);
		modelMapper = new JukeboxModelMapper();
		mandatory = false;
	}

	/**
	 * Instantiates a new jukebox manager.
	 *
	 * @param emf
	 *            the entity manager factory
	 */
	public JukeboxManager(EntityManagerFactory emf) {
		super();
		this.emf = emf;
		accountRepo = new AccountRepository(emf);
		jukeboxRepo = new JukeboxRepository(emf);
		playlistRepo = new PlaylistRepository(emf);
		songRepo = new SongRepository(emf);
		modelMapper = new JukeboxModelMapper();
		mandatory = false;
	}

	/**
	 * Adds the account.
	 *
	 * @param toAdd
	 *            the to add
	 */
	public void addAccount(AccountDTO toAdd) {
		try {
			Account acc = modelMapper.map(toAdd, Account.class);
			currentJukebox.addAccountRole(acc, Role.Customer);
			currentJukebox = jukeboxRepo.save(currentJukebox);
		} catch (IllegalArgumentException | NullPointerException ex) {
			// do nothing
		}

	}

	/**
	 * Adds the song.
	 *
	 * @param sourceItemId
	 *            the source item id
	 */
	public void addSong(SongDTO sourceItemId) {
		Song source = modelMapper.map(sourceItemId, Song.class);
		currentJukebox.getCurrentPlaylist().addSong(source);

		Playlist cpl = playlistRepo.save(currentJukebox.getCurrentPlaylist());
		setCurrentPlaylist(cpl);
		setChanged();
		notifyObservers(UpdateArgs.CURRENT_PLAYLIST);

	}

	/**
	 * Adds the song.
	 *
	 * @param sourceItemId
	 *            the source item id
	 * @param targetItemId
	 *            the target item id
	 */
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

	/**
	 * Can remove.
	 *
	 * @param toDelete
	 *            the to delete
	 * @return true, if successful
	 */
	public boolean canRemove(SecurityAccountDTO toDelete) {
		try {
			Account acc = modelMapper.map(toDelete, Account.class);
			if (currentJukebox.getAccountRoles().containsKey(acc)) {
				if (currentJukebox.getAccountRoles().get(acc) != Role.Administrator)
					return true;
				else {
					int administratorCount = 0;
					for (Map.Entry<Account, Role> entry : currentJukebox
							.getAccountRoles().entrySet()) {
						if (entry.getValue() == Role.Administrator) {
							administratorCount++;
						}
					}
					if (administratorCount > 1)
						return true;
				}
			}
			return false;
		} catch (NullPointerException ex) {
			return false;
		}
	}

	/**
	 * Change loop state.
	 */
	public void changeLoopState() {
		currentJukebox.setLooped(!currentJukebox.isLooped());
		setChanged();
		notifyObservers(UpdateArgs.CURRENT_JUKEBOX);
	}

	/**
	 * Change random state.
	 */
	public void changeRandomState() {
		currentJukebox.setRandom(!currentJukebox.isRandom());
		setChanged();
		notifyObservers(UpdateArgs.CURRENT_JUKEBOX);
	}

	/**
	 * Creates the new jukebox.
	 *
	 * @param oDTO
	 *            the o dto
	 */
	public void createNewJukebox(AccountDTO oDTO) {
		Account acc = modelMapper.map(oDTO, Account.class);
		Jukebox jb = new Jukebox(acc);
		jb = jukeboxRepo.save(jb);
		currentJukebox = jb;
		setChanged();
		notifyObservers(UpdateArgs.CURRENT_JUKEBOX);
	}

	/**
	 * Delete account.
	 *
	 * @param toDelete
	 *            the to delete
	 */
	public void deleteAccount(SecurityAccountDTO toDelete) {
		try {
			Account acc = modelMapper.map(toDelete, Account.class);
			if (currentJukebox.getAccountRoles().containsKey(acc)) {
				currentJukebox.getAccountRoles().remove(acc);
			}
			currentJukebox = jukeboxRepo.save(currentJukebox);
		} catch (NullPointerException ex) {
			// do nothing
		}
	}

	/**
	 * Delete jukebox.
	 *
	 * @param jbDto
	 *            the jb dto
	 */
	public void deleteJukebox(JukeboxDTO jbDto) {
		Jukebox jb = modelMapper.map(jbDto, Jukebox.class);
		jukeboxRepo.delete(jb);
		setChanged();
		notifyObservers(UpdateArgs.JUKEBOXLIST);
	}

	/**
	 * Delete playlist.
	 *
	 * @param playlistDTO
	 *            the playlist dto
	 */
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

	/**
	 * Edits the jukebox.
	 *
	 * @param name
	 *            the name
	 * @param paymentEmail
	 *            the payment email
	 * @param currencyDTO
	 *            the currency dto
	 * @param pricePerSong
	 *            the price per song
	 */
	public void editJukebox(String name, String paymentEmail,
			CurrencyDTO currencyDTO, String pricePerSong) {
		currentJukebox.setName(name);
		currentJukebox.getPayPalSettings().setCurrency(
				modelMapper.map(currencyDTO, Currency.class));

		currentJukebox.getPayPalSettings().setPricePerSong(
				round(Double.parseDouble(pricePerSong), 2));
		currentJukebox.getPayPalSettings().setEmail(paymentEmail);
		currentJukebox = jukeboxRepo.save(currentJukebox);

	}

	/**
	 * Gets the account.
	 *
	 * @param loggedInAccount
	 *            the logged in account
	 * @return the account
	 */
	public AccountDTO getAccount(AccountDTO loggedInAccount) {
		return findAccountFromList(loggedInAccount);
	}

	/**
	 * Gets the account.
	 *
	 * @param serviceName
	 *            the service name
	 * @param serviceId
	 *            the service id
	 * @return the account
	 */
	public AccountDTO getAccount(String serviceName, String serviceId) {
		for (Account acc : accountRepo.getList()) {
			if (acc.getServiceName().equals(serviceName)
					&& acc.getServiceId().equals(serviceId))
				return modelMapper.map(acc, AccountDTO.class);
		}
		return null;
	}

	/**
	 * Gets the all non permitted accounts.
	 *
	 * @return the all non permitted accounts
	 */
	public List<AccountDTO> getAllNonPermittedAccounts() {
		List<AccountDTO> ret = new ArrayList<AccountDTO>();
		for (Account acc : accountRepo.getList()) {
			if (!currentJukebox.getAccountRoles().containsKey(acc)) {
				AccountDTO toAdd = modelMapper.map(acc, AccountDTO.class);
				ret.add(toAdd);
			}
		}
		return ret;
	}

	/**
	 * Gets the all security accounts.
	 *
	 * @return the all security accounts
	 */
	public List<SecurityAccountDTO> getAllSecurityAccounts() {
		List<SecurityAccountDTO> ret = new ArrayList<SecurityAccountDTO>();
		for (Account acc : accountRepo.getList()) {
			if (currentJukebox.getAccountRoles().containsKey(acc)) {
				SecurityAccountDTO toAdd = modelMapper.map(acc,
						SecurityAccountDTO.class);
				toAdd.setRole(currentJukebox.getAccountRoles().get(acc)
						.toString());
				ret.add(toAdd);
			}
		}
		return ret;
	}

	/**
	 * Gets the all songs.
	 *
	 * @return the all songs
	 */
	public List<SongDTO> getAllSongs() {
		List<SongDTO> ret = new ArrayList<SongDTO>();
		for (Song s : songRepo.getList()) {
			ret.add(modelMapper.map(s, SongDTO.class));
		}
		return ret;
	}

	/**
	 * Gets the current account role.
	 *
	 * @param loggedInAccount
	 *            the logged in account
	 * @return the current account role
	 */
	public Role getCurrentAccountRole(AccountDTO loggedInAccount) {
		Account acc = modelMapper.map(loggedInAccount, Account.class);
		for (Map.Entry<Account, Role> entry : currentJukebox.getAccountRoles()
				.entrySet()) {
			if (entry.getKey().equals(acc))
				return entry.getValue();
		}
		return null;
	}

	/**
	 * Gets the current jukebox dto.
	 *
	 * @return the current jukebox dto
	 */
	public JukeboxDTO getCurrentJukeboxDTO() {
		if (currentJukebox != null)
			return modelMapper.map(currentJukebox, JukeboxDTO.class);
		return null;
	}

	/**
	 * Gets the current PayPal settings dto.
	 *
	 * @return the current PayPal settings dto
	 */
	public PayPalSettingsDTO getCurrentPayPalSettingsDTO() {
		if (currentJukebox != null)
			return modelMapper.map(currentJukebox.getPayPalSettings(),
					PayPalSettingsDTO.class);
		return null;
	}

	/**
	 * Gets the current playlist dto.
	 *
	 * @return the current playlist dto
	 */
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

	/**
	 * Gets the current song dto.
	 *
	 * @return the current song dto
	 */
	public SongDTO getCurrentSongDTO() {
		if (currentSong != null) {
			SongDTO dto = modelMapper.map(currentSong, SongDTO.class);
			dto.setPlayListOrder(String.valueOf(currentSongInt));
			dto.setMandatory(String.valueOf(mandatory));
			return dto;
		}
		return null;
	}

	/**
	 * Gets the emf.
	 *
	 * @return the emf
	 */
	public EntityManagerFactory getEmf() {
		return emf;
	}

	/**
	 * Gets the first song.
	 *
	 * @return the first song
	 */
	public SongDTO getFirstSong() {
		if (currentJukebox != null) {
			SongContainer sc = currentJukebox.getFirstSong();
			if (sc != null) {
				SongDTO dto = modelMapper.map(sc.getSong(), SongDTO.class);
				currentSongInt = sc.getPlaylistOrder();
				mandatory = sc.getMandatory();
				dto.setPlayListOrder(String.valueOf(currentSongInt));
				dto.setMandatory(String.valueOf(sc.getMandatory()));
				setCurrentSong(dto);
				return dto;
			}
		}
		return null;
	}

	/**
	 * Gets the jukeboxes.
	 *
	 * @param dto
	 *            the dto
	 * @return the jukeboxes
	 */
	public List<JukeboxDTO> getJukeboxes(AccountDTO dto) {
		ArrayList<JukeboxDTO> retList = new ArrayList<JukeboxDTO>();
		if (dto != null) {
			for (Jukebox jbItem : jukeboxRepo.getList()) {
				Account acc = modelMapper.map(dto, Account.class);
				if (jbItem.getAccountRoles().containsKey(acc)) {
					Role role = jbItem.getAccountRoles().get(acc);
					if (role.equals(Role.Administrator)
							|| role.equals(Role.Manager))
						retList.add(modelMapper.map(jbItem, JukeboxDTO.class));
				}
			}
		}
		return retList;
	}

	/**
	 * Gets the mandatory playlist dto.
	 *
	 * @return the mandatory playlist dto
	 */
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

	/**
	 * Gets the mandatory songs.
	 *
	 * @return the mandatory songs
	 */
	public List<SongDTO> getMandatorySongs() {
		List<SongDTO> ret = new ArrayList<SongDTO>();
		if (currentJukebox != null
				&& currentJukebox.getMandatoryPlaylist() != null) {
			Playlist pl = playlistRepo.find(currentJukebox
					.getMandatoryPlaylist());
			if (pl != null) {
				for (Map.Entry<Integer, Song> entry : pl.getSongs().entrySet()) {
					Song value = entry.getValue();
					SongDTO dto = modelMapper.map(value, SongDTO.class);
					dto.setPlayListOrder(entry.getKey().toString());
					dto.setMandatory(String.valueOf(true));
					ret.add(dto);
				}
			}
		}

		return ret;
	}

	/**
	 * Gets the next song.
	 *
	 * @return the next song
	 */
	public SongDTO getNextSong() {
		if (currentJukebox != null) {
			removeCurrentSongFromMandatoryPlaylist();
			SongContainer sc = null;
			if (mandatory)
				sc = currentJukebox.getNextSong(-1);
			else
				sc = currentJukebox.getNextSong(currentSongInt);
			if (sc != null) {
				try {
					SongDTO dto = modelMapper.map(sc.getSong(), SongDTO.class);
					currentSongInt = sc.getPlaylistOrder();
					mandatory = sc.getMandatory();
					dto.setMandatory(String.valueOf(sc.getMandatory()));
					dto.setPlayListOrder(String.valueOf(currentSongInt));
					setCurrentSong(dto);
					return dto;
				} catch (IllegalArgumentException | NullPointerException ex) {
					// do nothing
				}
			}
		}
		return null;
	}

	/**
	 * Gets the PDF stream.
	 *
	 * @return the PDF stream
	 */
	public StreamSource getPDFStream() {
		try {
			PDFStream pdf = new PDFStream(QR
					.getQRFile(
							"http://"
									+ InetAddress.getLocalHost()
											.getHostAddress()
									+ ":8080/registercustomer?jukeboxid="
									+ String.valueOf(currentJukebox.getId()),
							350, 350).toURI().toURL(), currentJukebox.getName());
			return pdf;
		} catch (UnknownHostException | MalformedURLException e) {
			return null;
		}
	}

	/**
	 * Gets the previous song.
	 *
	 * @return the previous song
	 */
	public SongDTO getPreviousSong() {
		if (currentJukebox != null) {
			removeCurrentSongFromMandatoryPlaylist();
			SongContainer sc = null;
			if (mandatory)
				sc = currentJukebox.getPreviousSong(-1);
			else
				sc = currentJukebox.getPreviousSong(currentSongInt);
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

	/**
	 * Gets the QR image.
	 *
	 * @return the QR image
	 */
	public StreamSource getQRImage() {
		try {
			return new QRStream("http://"
					+ InetAddress.getLocalHost().getHostAddress()
					+ "/registercustomer?jukeboxid="
					+ String.valueOf(currentJukebox.getId()));
		} catch (UnknownHostException e) {
			return null;
		}
	}

	/**
	 * Gets the QR stream.
	 *
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 * @return the QR stream
	 */
	public StreamSource getQRStream(int width, int height) {
		try {
			return new QRStream("http://"
					+ InetAddress.getLocalHost().getHostAddress() + ":8080"
					+ "/registercustomer?jukeboxid="
					+ String.valueOf(currentJukebox.getId()), width, height);
		} catch (UnknownHostException e) {
			return null;
		}
	}

	/**
	 * Gets the role list.
	 *
	 * @return the role list
	 */
	public List<String> getRoleList() {
		List<String> roleList = new ArrayList<String>();
		for (Role role : Arrays.asList(Role.values())) {
			roleList.add(modelMapper.map(role, String.class));
		}
		return roleList;
	}

	/**
	 * Gets the saved playlists.
	 *
	 * @param jukeboxDTO
	 *            the jukebox dto
	 * @return the saved playlists
	 */
	public List<PlaylistDTO> getSavedPlaylists(JukeboxDTO jukeboxDTO) {
		Jukebox jb = modelMapper.map(jukeboxDTO, Jukebox.class);
		jb = jukeboxRepo.find(jb);
		List<PlaylistDTO> ret = new ArrayList<PlaylistDTO>();
		for (Playlist pl : jb.getSavedPlaylists()) {
			ret.add(modelMapper.map(pl, PlaylistDTO.class));
		}
		return ret;
	}

	/**
	 * Gets the songs.
	 *
	 * @param playlistDTO
	 *            the playlist dto
	 * @return the songs
	 */
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

	/**
	 * Gets the user.
	 *
	 * @param user
	 *            the user
	 * @return the user
	 */
	public AccountDTO getUser(IOAuthUser user) {
		AccountDTO o = new AccountDTO();
		o.setEmailAddress(user.getEmail());
		o.setFirstName(user.getName());
		o.setLastName(user.getName());
		o.setServiceId(user.getId());
		o.setServiceName(user.getService());

		return findAccountFromList(o);
	}

	/**
	 * Checks if is current account.
	 *
	 * @param loggedInAccount
	 *            the logged in account
	 * @param toDelete
	 *            the to delete
	 * @return true, if is current account
	 */
	public boolean isCurrentAccount(AccountDTO loggedInAccount,
			SecurityAccountDTO toDelete) {
		try {
			return modelMapper.map(loggedInAccount, Account.class).equals(
					modelMapper.map(toDelete, Account.class));
		} catch (NullPointerException ex) {
			return true;
		}
	}

	/**
	 * Checks if is looped.
	 *
	 * @return true, if is looped
	 */
	public boolean isLooped() {
		if (currentJukebox != null)
			return currentJukebox.isLooped();
		return false;
	}

	/**
	 * Checks if is mandatory.
	 *
	 * @return true, if is mandatory
	 */
	public boolean isMandatory() {
		return mandatory;
	}

	/**
	 * Checks if is random.
	 *
	 * @return true, if is random
	 */
	public boolean isRandom() {
		if (currentJukebox != null)
			return currentJukebox.isRandom();
		return false;
	}

	/**
	 * Checks if is valid email address.
	 *
	 * @param email
	 *            the email
	 * @return true, if is valid email address
	 */
	public boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (NullPointerException | AddressException ex) {
			result = false;
		}
		return result;
	}

	/**
	 * Mandatory empty.
	 *
	 * @return true, if successful
	 */
	public boolean mandatoryEmpty() {
		try {
			return currentJukebox.getMandatoryPlaylist().getSongs().size() <= 0;
		} catch (NullPointerException ex) {
			return true;
		}
	}

	/**
	 * Removes the all customers.
	 */
	public void removeAllCustomers() {
		List<Account> accountList = new ArrayList<Account>();
		for (Map.Entry<Account, Role> entry : currentJukebox.getAccountRoles()
				.entrySet()) {
			if (entry.getValue().equals(Role.Customer))
				accountList.add(entry.getKey());
		}
		for (Account account : accountList) {
			currentJukebox.getAccountRoles().remove(account);
		}
		currentJukebox = jukeboxRepo.save(currentJukebox);
		setChanged();
		notifyObservers(UpdateArgs.CURRENT_JUKEBOX);
	}

	/**
	 * Removes the song from current playlist.
	 *
	 * @param song
	 *            the song
	 */
	public void removeSongFromCurrentPlaylist(SongDTO song) {
		currentJukebox.getCurrentPlaylist().removeSong(
				Integer.parseInt(song.getPlayListOrder()));
		currentJukebox = jukeboxRepo.save(currentJukebox);
		setChanged();
		notifyObservers(UpdateArgs.CURRENT_PLAYLIST);
	}

	/**
	 * Reorder playlist.
	 *
	 * @param source
	 *            the source
	 * @param target
	 *            the target
	 */
	public void reorderPlaylist(SongDTO source, SongDTO target) {
		currentJukebox.getCurrentPlaylist().moveSong(
				Integer.parseInt(source.getPlayListOrder()),
				Integer.parseInt(target.getPlayListOrder()));
		Playlist cpl = playlistRepo.save(currentJukebox.getCurrentPlaylist());
		setCurrentPlaylist(cpl);
		setChanged();
		notifyObservers(UpdateArgs.CURRENT_PLAYLIST);
	}

	/**
	 * Round.
	 *
	 * @param value
	 *            the value
	 * @param places
	 *            the places
	 * @return the double
	 */
	public double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	/**
	 * Save.
	 *
	 * @param dto
	 *            the dto
	 * @return the account dto
	 */
	public AccountDTO save(AccountDTO dto) {
		Account acc = accountRepo.save(modelMapper.map(dto, Account.class));
		return modelMapper.map(acc, AccountDTO.class);
	}

	/**
	 * Save.
	 *
	 * @param dto
	 *            the dto
	 * @return the jukebox dto
	 */
	public JukeboxDTO save(JukeboxDTO dto) {
		Jukebox jb = jukeboxRepo.find(modelMapper.map(dto, Jukebox.class));
		jb = updateEditedFields(jb, dto);
		currentJukebox = jukeboxRepo.save(jb);
		setChanged();
		notifyObservers(UpdateArgs.CURRENT_JUKEBOX);
		return modelMapper.map(jb, JukeboxDTO.class);
	}

	/**
	 * Save current play list to jukebox.
	 */
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

	/**
	 * Sets the current jukebox.
	 *
	 * @param selectedJukebox
	 *            the new current jukebox
	 */
	public void setCurrentJukebox(JukeboxDTO selectedJukebox) {
		Jukebox jb = modelMapper.map(selectedJukebox, Jukebox.class);
		if (jukeboxRepo.find(jb) == null)
			jb = jukeboxRepo.save(jb);
		jb = jukeboxRepo.find(jb);
		this.currentJukebox = jb;
		setChanged();
		notifyObservers(UpdateArgs.CURRENT_JUKEBOX);

	}

	/**
	 * Sets the current playlist.
	 *
	 * @param playlist
	 *            the new current playlist
	 */
	public void setCurrentPlaylist(Playlist playlist) {
		if (playlistRepo.find(playlist) == null)
			playlist = playlistRepo.save(playlist);
		playlist = playlistRepo.find(playlist);
		currentJukebox.setCurrentPlaylist(playlist);
		setChanged();
		notifyObservers(UpdateArgs.CURRENT_PLAYLIST);
	}

	/**
	 * Sets the current playlist.
	 *
	 * @param playlistDTO
	 *            the new current playlist
	 */
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

	/**
	 * Sets the current playlist name.
	 *
	 * @param name
	 *            the new current playlist name
	 */
	public void setCurrentPlaylistName(String name) {
		currentJukebox.getCurrentPlaylist().setName(name);
		Playlist cpl = playlistRepo.save(currentJukebox.getCurrentPlaylist());
		setCurrentPlaylist(cpl);
		setChanged();
		notifyObservers(UpdateArgs.CURRENT_PLAYLIST);
	}

	/**
	 * Sets the current song.
	 *
	 * @param songDTO
	 *            the new current song
	 */
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

	/**
	 * Sets the new current playlist.
	 *
	 * @param sDTO
	 *            the new new current playlist
	 */
	public void setNewCurrentPlaylist(SongDTO sDTO) {
		Song s = modelMapper.map(sDTO, Song.class);
		setCurrentPlaylist(new Playlist("Unsaved playlist"));

		currentJukebox.getCurrentPlaylist().addSong(s);
		Playlist cpl = playlistRepo.save(currentJukebox.getCurrentPlaylist());
		setCurrentPlaylist(cpl);
		setChanged();
		notifyObservers(UpdateArgs.CURRENT_PLAYLIST);
	}

	/**
	 * Update account.
	 *
	 * @param secAcc
	 *            the sec acc
	 * @param role
	 *            the role
	 */
	public void updateAccount(SecurityAccountDTO secAcc, String role) {
		try {
			Account acc = modelMapper.map(secAcc, Account.class);
			currentJukebox.addAccountRole(acc,
					modelMapper.map(role, Role.class));
		} catch (NullPointerException ex) {
			// do nothing
		}
	}

	/**
	 * Update current user.
	 */
	public void updateCurrentUser() {
		setChanged();
		notifyObservers(UpdateArgs.CURRENT_ACCOUNT);

	}

	/**
	 * Copy playlist.
	 *
	 * @param cpl
	 *            the cpl
	 * @return the playlist
	 */
	private Playlist copyPlaylist(Playlist cpl) {
		Playlist copy = new Playlist(cpl.getName());
		copy.setSongs(cpl.getSongs());
		copy = playlistRepo.save(copy);
		return copy;
	}

	/**
	 * Find account from list.
	 *
	 * @param o
	 *            the o
	 * @return the account dto
	 */
	private AccountDTO findAccountFromList(AccountDTO o) {
		Account acc = modelMapper.map(o, Account.class);
		for (Account accItem : accountRepo.getList()) {
			if (accItem.equals(acc))
				return modelMapper.map(accItem, AccountDTO.class);
		}
		return modelMapper.map(accountRepo.save(acc), AccountDTO.class);
	}

	/**
	 * Removes the current song from mandatory playlist.
	 */
	private void removeCurrentSongFromMandatoryPlaylist() {
		if (currentJukebox != null && mandatory) {
			currentJukebox.removeMandatorySong(currentSong, currentSongInt);
			currentJukebox = jukeboxRepo.save(currentJukebox);
			setChanged();
			notifyObservers(UpdateArgs.CURRENT_PLAYLIST);
		}

	}

	/**
	 * Update edited fields.
	 *
	 * @param jb
	 *            the jb
	 * @param dto
	 *            the dto
	 * @return the jukebox
	 */
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
}