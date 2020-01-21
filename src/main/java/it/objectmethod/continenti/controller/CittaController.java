package it.objectmethod.continenti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.continenti.dao.ICittaDao;
import it.objectmethod.continenti.dao.INazioneDao;
import it.objectmethod.continenti.model.Citta;
import it.objectmethod.continenti.model.Nazione;

@RestController
public class CittaController {

	@Autowired
	private INazioneDao nazioneDao;

	@Autowired
	private ICittaDao cittaDao;

	@GetMapping("/citta/{codNaz}/show")
	public List<Citta> printCities(@PathVariable("codNaz") String codice) {

		List<Citta> citta = new ArrayList<Citta>();
		citta = cittaDao.getCityByCountryCode(codice);

		return citta;
	}

	@RequestMapping("/ricerca")
	public List<Nazione> search(@RequestBody Citta c) {

		List<Nazione> nazioni = new ArrayList<Nazione>();
		nazioni = nazioneDao.getNations();
		return nazioni;
	}

	@GetMapping("/risultati-ricerca")
	public List<Citta> ricerca(@RequestBody Citta c) {

		List<Citta> citta = new ArrayList<Citta>();

		citta = cittaDao.getSearch(c.getName(), c.getCountryCode());

		return citta;
	}

	@GetMapping("/modifica-city/{ID}/show")
	public String preform(@PathVariable("ID") Integer id, ModelMap model) {
		Citta citta = new Citta();
		if (id != 0) {
			citta = cittaDao.getCityById(id);
		} else {
			citta.setId(id);
		}
		List<Nazione> nazioni = new ArrayList<Nazione>();
		nazioni = nazioneDao.getNations();
		model.addAttribute("nazioni", nazioni);
		model.addAttribute("city", citta);
		return "form-preparazione";
	}

	@PostMapping("/salva-city")
	public String modificaInserisci(@RequestParam("modificaID") Integer id, @RequestParam("modificaNome") String nome,
			@RequestParam("modificaCodice") String codice, ModelMap model) {
		int check = 0;
		String conferma = "";
		if (id != 0) {
			check = cittaDao.modifica(nome, id, codice);
			conferma = "Modifica avvenuta correttamente";
		} else {
			check = cittaDao.inserisci(nome, id, codice);
			conferma = "Inserimento avvenuto correttamente";
		}

		if (check != 1) {
			conferma = "Qualcosa è andato storto";
		}

		model.addAttribute("conferma", conferma);
		return "forward:/ricerca";
	}
}
