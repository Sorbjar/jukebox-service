package be.lode.jukebox.service.mapper.maps;

import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;

import be.lode.jukebox.business.model.Account;
import be.lode.jukebox.service.dto.SecurityAccountDTO;

public class SecurityAccountMap extends
		PropertyMap<Account, SecurityAccountDTO> {

	@Override
	protected void configure() {
		map().setId(String.valueOf(source.getId()));

		
		Converter<String, String> c = new Converter<String, String>() {
			@Override
			public String convert(MappingContext<String, String> context) {
				Account src = (Account) context.getParent().getSource();
				return src.getFirstName() + " " + src.getLastName();
			}
		};
		using(c).map().setFullName(null);
	}

}
