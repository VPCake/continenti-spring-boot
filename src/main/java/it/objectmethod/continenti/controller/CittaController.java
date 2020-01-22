package it.objectmethod.continenti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.continenti.dao.ICittaDao;
import it.objectmethod.continenti.model.Citta;

@RestController
public class CittaController {

	@Autowired
	private ICittaDao cittaDao;

	@GetMapping("/citta/{codNaz}/show")
	public List<Citta> printCities(@PathVariable("codNaz") String codice) {

		List<Citta> citta = new ArrayList<Citta>();
		citta = cittaDao.getCityByCountryCode(codice);

		return citta;
	}

	@GetMapping("/ricerca")
	public List<Citta> ricerca(@RequestParam(name = "name", required = false) String nome,
			@RequestParam(name = "code", required = false) String code) {

		List<Citta> citta = new ArrayList<Citta>();
		citta = cittaDao.getSearch(nome, code);
		return citta;
	}

	@PutMapping("/modifica-city")
	public String modificaCitta(@RequestBody Citta c) {
		int check = 0;
		String conferma = "";
		check = cittaDao.modifica(c.getName(), c.getId(), c.getCountryCode());

		if (check == 1) {
			conferma = "Modifica avvenuta correttamente";
		} else {
			conferma = "Qualcosa è andato storto";
		}
		return conferma;
	}

	@PutMapping("/inserisci-city")
	public String inserisciCitta(@RequestBody Citta c) {
		int check = 0;
		String conferma = "";
		check = cittaDao.inserisci(c.getName(), c.getId(), c.getCountryCode());

		if (check == 1) {
			conferma = "Inserimento avvenuto correttamente";
		} else {
			conferma = "Qualcosa è andato storto";
		}
		return conferma;
	}

}
