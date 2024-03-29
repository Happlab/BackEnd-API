package co.edu.unicauca.APIHappLab.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.APIHappLab.model.noticia;
import co.edu.unicauca.APIHappLab.repository.I_noticia_repository;

@Service
public class noticia_service {
	@Autowired
	private I_noticia_repository repo;
	public noticia_service() {
	}
	public Optional<noticia> findById(String id_noticia){
		return repo.findById(id_noticia);
	}
	public Optional<noticia> findByLink_contenido(String link_contenido){
		return repo.findByLink_contenido(link_contenido);
	}
	public noticia create(noticia par_noticia) {
		return repo.insert(par_noticia);
	}

	public noticia update(noticia par_noticia) {
		return repo.save(par_noticia);
		
	}
	public List<noticia> findAll(){
		return this.repo.findAll();
	}
	public boolean delete(String id) {
		try {
			repo.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
