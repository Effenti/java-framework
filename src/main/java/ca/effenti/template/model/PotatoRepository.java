package ca.effenti.template.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("potatoRepository")
public interface PotatoRepository extends JpaRepository<Potato, Long> {
}
