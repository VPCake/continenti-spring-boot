package it.objectmethod.continenti.dao;

import java.util.List;

import it.objectmethod.continenti.model.Citta;

public interface ICittaDao {

	public List<Citta> getCityByCountryCode(String nationCode);

	public List<Citta> getSearch(String name, String code);

	public int modifica(String name, int id, String codice);

	public int inserisci(String name, int id, String codice);

	public Citta getCityById(int id);

}