package rs.ac.singidunum.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.app.model.Zvanje;
import rs.ac.singidunum.app.repository.ZvanjeRepository;

import java.util.Optional;

@Service
public class ZvanjeService {
    @Autowired
    private ZvanjeRepository zvanjeRepository;

    public ZvanjeService() {
        super();
    }

    public Iterable<Zvanje> findAll() {
        return zvanjeRepository.findAll();
    }

    public Page<Zvanje> findAll(Pageable pageable) {
        return zvanjeRepository.findAll(pageable);
    }

    public Optional<Zvanje> findOne(Long id) {
        return zvanjeRepository.findById(id);
    }


    public Zvanje save(Zvanje zvanje) {
        return zvanjeRepository.save(zvanje);
    }

    public void delete(Long id) {
        zvanjeRepository.deleteById(id);
    }

    public void delete(Zvanje zvanje) {
        zvanjeRepository.delete(zvanje);
    }

    //Metoda za pronalazenje naucne obleasti po nazivu
    public Iterable<Zvanje> findNaucnaOblast(String naziv) {
        return zvanjeRepository.findNaucnaOblast(naziv);
    }

}
