package br.com.herbario.repository;

import br.com.herbario.model.Planta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantaRepository extends JpaRepository<Planta, Long> {
    List<Planta> findByNomeContainingIgnoreCaseOrNomeCientificoContainingIgnoreCaseOrderByNomeAsc(String nome, String nomeCientifico);
    List<Planta> findAllByOrderByNomeAsc();
}
