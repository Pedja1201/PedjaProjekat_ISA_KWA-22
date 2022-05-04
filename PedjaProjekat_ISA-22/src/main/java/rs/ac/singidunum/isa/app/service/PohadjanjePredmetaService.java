package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.PohadjanjePredmeta;
import rs.ac.singidunum.isa.app.repository.PohadjanjePredmetaRepository;

import java.util.Optional;

@Service
public class PohadjanjePredmetaService {
    @Autowired
    private PohadjanjePredmetaRepository pohadjanjePredmetaRepository;

    public PohadjanjePredmetaService() {
        super();
    }

    public PohadjanjePredmetaService(PohadjanjePredmetaRepository pohadjanjePredmetaRepository) {
        super();
        this.pohadjanjePredmetaRepository = pohadjanjePredmetaRepository;
    }

    public PohadjanjePredmetaRepository getPohadjanjePredmetaRepository() {
        return pohadjanjePredmetaRepository;
    }

    public void setPohadjanjePredmetaRepository(PohadjanjePredmetaRepository pohadjanjePredmetaRepository) {
        this.pohadjanjePredmetaRepository = pohadjanjePredmetaRepository;
    }

    public Iterable<PohadjanjePredmeta> findAll() {
        return pohadjanjePredmetaRepository.findAll();
    }

    public Optional<PohadjanjePredmeta> findOne(Long id) {
        return pohadjanjePredmetaRepository.findById(id);
    }


    public PohadjanjePredmeta save(PohadjanjePredmeta pohadjanjePredmeta) {
        return pohadjanjePredmetaRepository.save(pohadjanjePredmeta);
    }

    public void delete(Long id) {
        pohadjanjePredmetaRepository.deleteById(id);
    }

    public void delete(PohadjanjePredmeta pohadjanjePredmeta) {
        pohadjanjePredmetaRepository.delete(pohadjanjePredmeta);
    }
}
