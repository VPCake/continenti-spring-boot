package it.objectmethod.continenti.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Service;

import it.objectmethod.continenti.dao.ICittaDao;
import it.objectmethod.continenti.model.Citta;

@Service
public class CittaDaoImpl extends NamedParameterJdbcDaoSupport implements ICittaDao {
	
	@Autowired
	public CittaDaoImpl(DataSource dataSource) {
		super();
		setDataSource(dataSource);
	}

	@Override
	public List<Citta> getCityByCountryCode(String nationCode) {

		List<Citta> citta = new ArrayList<Citta>();
		citta = null;
		String sql = "SELECT city.ID, city.Name,city.CountryCode,city.Population FROM world.city WHERE city.CountryCode=?";
		BeanPropertyRowMapper<Citta> rm = new BeanPropertyRowMapper<Citta>(Citta.class);
		citta = getJdbcTemplate().query(sql, new Object[] { nationCode }, rm);
		return citta;

	}

	@Override
	public List<Citta> getSearch(String name, String code) {
		String nomeRicerca = "%" + name + "%";
		List<Citta> cities = new ArrayList<Citta>();
		String query = "SELECT ID,Name,CountryCode,Population FROM world.city WHERE Name LIKE ? AND (? = '' OR CountryCode = ?);";
		BeanPropertyRowMapper<Citta> rm = new BeanPropertyRowMapper<Citta>(Citta.class);
		cities = getJdbcTemplate().query(query, new Object[] { nomeRicerca, code, code }, rm);
		return cities;
	}

	@Override
	public int modifica(String name, int id, String codice) {

		int rows = 0;
		String queryMod = "UPDATE world.city SET Name = ?, CountryCode= ? WHERE ID=?";
		rows = getJdbcTemplate().update(queryMod, new Object[] { name, codice, id });

		return rows;
	}

	@Override
	public Citta getCityById(int id) {
		Citta citta = null;
		String query = "SELECT city.ID, city.Name,city.CountryCode,city.Population " + "FROM world.city "
				+ "WHERE city.ID=?";
		BeanPropertyRowMapper<Citta> rm = new BeanPropertyRowMapper<Citta>(Citta.class);
		citta = getJdbcTemplate().queryForObject(query, new Object[] { id }, rm);
		return citta;

	}

	@Override
	public int inserisci(String name, int id, String codice) {

		int rows = 0;
		String queryMod = "INSERT INTO world.city (ID, Name, CountryCode) VALUES (?, ?, ?)";
		rows = getJdbcTemplate().update(queryMod, new Object[] { id, name, codice });

		return rows;
	}
}
