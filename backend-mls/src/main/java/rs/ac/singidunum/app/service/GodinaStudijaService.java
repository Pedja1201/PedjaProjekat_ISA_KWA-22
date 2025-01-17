package rs.ac.singidunum.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.app.model.GodinaStudija;
import rs.ac.singidunum.app.repository.GodinaStudijaRepository;

import java.util.Optional;

@Service
public class GodinaStudijaService {
    @Autowired
    private GodinaStudijaRepository godinaStudijaRepository;

    public GodinaStudijaService() {
        super();
    }

    public GodinaStudijaService(GodinaStudijaRepository godinaStudijaRepository) {
        super();
        this.godinaStudijaRepository = godinaStudijaRepository;
    }

    public GodinaStudijaRepository getGodinaStudijaRepository() {
        return godinaStudijaRepository;
    }

    public void setGodinaStudijaRepository(GodinaStudijaRepository godinaStudijaRepository) {
        this.godinaStudijaRepository = godinaStudijaRepository;
    }

    public Iterable<GodinaStudija> findAll() {
        return godinaStudijaRepository.findAll();
    }
    
    public Page<GodinaStudija> findAll(Pageable pageable) {
        return godinaStudijaRepository.findAll(pageable);
    }

    public Optional<GodinaStudija> findOne(Long id) {
        return godinaStudijaRepository.findById(id);
    }


    public GodinaStudija save(GodinaStudija godinaStudija) {
        return godinaStudijaRepository.save(godinaStudija);
    }

    public void delete(Long id) {
        godinaStudijaRepository.deleteById(id);
    }

    public void delete(GodinaStudija godinaStudija) {
        godinaStudijaRepository.delete(godinaStudija);
    }

    //Metoda za pronalazenje predmeta na Godini studija
    public Iterable<GodinaStudija> findPredmetGodineStudija(String naziv) {
        return godinaStudijaRepository.findPredmetGodineStudija(naziv);
    }
}
