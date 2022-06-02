package rs.ac.singidunum.isa.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.isa.app.model.Zvanje;

@Repository
public interface ZvanjeRepository extends CrudRepository<Zvanje, Long> {

    //Metoda i upit za pronalaženje naucne oblasti po nazivu
    @Query(value = "SELECT z FROM Zvanje z WHERE z.naucnaOblast.naziv = :naziv")
    public Iterable<Zvanje> findNaucnaOblast(String naziv);
}
