package it.objectmethod.continenti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.continenti.dao.INazioneDao;
import it.objectmethod.continenti.model.Nazione;

@RestController
public class NazioniController {

	@Autowired
	private INazioneDao nazioneDao;

	@GetMapping("/continenti")
	public List<String> index() {

		List<String> elencoContinenti = new ArrayList<>();
		elencoContinenti = nazioneDao.getContinents();

		return elencoContinenti;
	}

	@GetMapping("/nazioni")
	public List<Nazione> printNations() {

		List<Nazione> nazioni = new ArrayList<>();
		nazioni = nazioneDao.getNations();

		return nazioni;

	}

	@GetMapping("/nazioni-cont")
	public List<Nazione> printNations(@RequestParam("nomeContinente") String continente) {

		List<Nazione> nazioni = new ArrayList<>();
		nazioni = nazioneDao.getNationByContinent(continente);

		return nazioni;

	}

}
