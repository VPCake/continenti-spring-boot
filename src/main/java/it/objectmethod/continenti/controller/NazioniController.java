package it.objectmethod.continenti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.objectmethod.continenti.dao.INazioneDao;
import it.objectmethod.continenti.model.Nazione;

@Controller
public class NazioniController {
	
	@Autowired
	private INazioneDao nazioneDao;

	@GetMapping("/index")
	public String index(ModelMap model) {

		List<String> elencoContinenti = new ArrayList<>();
		elencoContinenti = nazioneDao.getContinents();

		model.addAttribute("continents", elencoContinenti);
		return "continenti";
	}
	
	@GetMapping("/nazioni")
	public String printNations(@RequestParam("nomeContinente") String continente, ModelMap model) {

		List<Nazione> nazioni = new ArrayList<>();
		nazioni = nazioneDao.getNationByContinent(continente);

		model.addAttribute("nazioni", nazioni);
		return "nazioni";

	}

}
