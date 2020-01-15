package it.objectmethod.continenti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.objectmethod.continenti.dao.ICittaDao;
import it.objectmethod.continenti.dao.INazioneDao;
import it.objectmethod.continenti.model.Citta;
import it.objectmethod.continenti.model.Nazione;

@Controller
public class CittaController {

	@Autowired
	private INazioneDao nazioneDao;

	@Autowired
	private ICittaDao cittaDao;
	
	@GetMapping("/citta/{codNaz}/show")
	public String printCities(@PathVariable("codNaz") String codice, ModelMap model) {

		List<Citta> citta = new ArrayList<Citta>();
		citta = cittaDao.getCityByCountryCode(codice);

		model.addAttribute("citta", citta);
		return "citta";
	}
	
	@RequestMapping("/ricerca")
	public String search(ModelMap model) {

		List<Nazione> nazioni = new ArrayList<Nazione>();
		nazioni = nazioneDao.getNations();
		model.addAttribute("nazioni", nazioni);
		return "ricerca";
	}

	@GetMapping("/risultati-ricerca")
	public String search(@RequestParam("name") String nome, @RequestParam("code") String code, ModelMap model) {

		List<Nazione> nazioni = new ArrayList<Nazione>();
		nazioni = nazioneDao.getNations();
		List<Citta> citta = new ArrayList<Citta>();

		citta = cittaDao.getSearch(nome, code);

		model.addAttribute("citta", citta);
		model.addAttribute("nazioni", nazioni);
		return "ricerca";
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
