package br.com.avaliacao4.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.avaliacao4.model.Partido;

public interface PartidoRepository extends JpaRepository<Partido, Long> {

	static Page<Partido> findByRegiao(String upperCase, Pageable paginacion) {
		return null;
	}

	static Page<Partido> findByIdeologia(String upperCase, Pageable paginacion) {
		return null;
	}
}
