package mr.iscae.Bibliotheque.controller;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import mr.iscae.Bibliotheque.entity.Client;
import mr.iscae.Bibliotheque.exception.ClientException;
import mr.iscae.Bibliotheque.repository.ClientRepository;

@RestController
@RequestMapping("/shop/clients")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
//	@GetMapping
//	public List<Client> getAllClient(){
//		return this.clientRepository.findAll();
//	}
	
	@GetMapping
	public RedirectView getAllClient2(){
		RedirectView redirectView = new RedirectView("/index.html");
		return redirectView;
	}
	
	@GetMapping("/{id}")
	public Client getClientById(@PathVariable(value = "id") int clientId) {
		return this.clientRepository.findById(clientId)
				.orElseThrow(()-> new ClientException("Client introuvable!! avec l'ID"+clientId));
	}
	
	@PostMapping
	public Client createClient(@RequestBody Client client) {
		return this.clientRepository.save(client);
	}
	
	@PutMapping("/{id}")
	public Client updateClient(@RequestBody Client client, @PathVariable ("id") int clientId) {
		Client existing = this.clientRepository.findById(clientId)
				.orElseThrow(()-> new ClientException("Client introuvable!! avec l'ID"+clientId));
		existing.setNom(client.getNom());
		existing.setTel(client.getTel());
		return this.clientRepository.save(existing);
	}
	
	
	@DeleteMapping("/supp/{id}")
	public ResponseEntity<Client> deleteClient(@PathVariable ("id") int clientId){
		Client existing = this.clientRepository.findById(clientId)
				.orElseThrow(()-> new ClientException("Client introuvable!! avec l'ID"+clientId));
		this.clientRepository.delete(existing);
		return ResponseEntity.ok().build();
		
	}
	
}