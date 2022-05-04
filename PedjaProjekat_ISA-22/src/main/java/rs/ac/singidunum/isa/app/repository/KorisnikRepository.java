package rs.ac.singidunum.isa.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.isa.app.model.Korisnik;

import java.util.Optional;

@Repository
public interface KorisnikRepository extends CrudRepository<Korisnik, Long> {
    ///Metoda koja dobovalja Korisnika iz baze podataka.
    Optional<Korisnik> findByKorisnickoIme(String korisnickoIme);
}

