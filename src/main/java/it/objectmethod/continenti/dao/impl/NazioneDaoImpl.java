package it.objectmethod.continenti.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Service;

import it.objectmethod.continenti.dao.INazioneDao;
import it.objectmethod.continenti.model.Nazione;

@Service
public class NazioneDaoImpl extends NamedParameterJdbcDaoSupport implements INazioneDao {

	@Autowired
	public NazioneDaoImpl(DataSource dataSource) {
		super();
		setDataSource(dataSource);
	}

	@Override
	public List<Nazione> getNationByContinent(String continent) {
		List<Nazione> nazioni = new ArrayList<Nazione>();

		String sql = "SELECT code,name,continent,population FROM world.country WHERE continent = ?";
		BeanPropertyRowMapper<Nazione> rm = new BeanPropertyRowMapper<Nazione>(Nazione.class);
		nazioni = getJdbcTemplate().query(sql, new Object[] { continent }, rm);

		return nazioni;
	}

	@Override
	public List<Nazione> getNations() {

		List<Nazione> nazioni = new ArrayList<Nazione>();
		String sql = "SELECT country.Code,country.Name,country.Continent, country.Population FROM world.country;";
		BeanPropertyRowMapper<Nazione> rm = new BeanPropertyRowMapper<Nazione>(Nazione.class);
		nazioni = getJdbcTemplate().query(sql, new Object[] {}, rm);
		return nazioni;
	}

	@Override
	public List<String> getContinents() {

		List<String> continenti = new ArrayList<String>();
		String sql = "SELECT DISTINCT country.continent FROM world.country;";
		continenti = getJdbcTemplate().queryForList(sql, String.class);
		return continenti;
	}
}