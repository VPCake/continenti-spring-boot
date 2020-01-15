package it.objectmethod.continenti.dao;

import java.util.List;

import it.objectmethod.continenti.model.Nazione;

public interface INazioneDao {

	public List<Nazione> getNationByContinent(String continentNation);

	public List<Nazione> getNations();

	public List<String> getContinents();
}
