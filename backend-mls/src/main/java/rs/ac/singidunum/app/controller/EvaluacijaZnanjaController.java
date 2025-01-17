package rs.ac.singidunum.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.app.aspect.LoggedAdministrator;
import rs.ac.singidunum.app.aspect.LoggedNastavnik;
import rs.ac.singidunum.app.dto.EvaluacijaZnanjaDTO;
import rs.ac.singidunum.app.dto.IshodDTO;
import rs.ac.singidunum.app.dto.TipEvaluacijeDTO;
import rs.ac.singidunum.app.model.EvaluacijaZnanja;
import rs.ac.singidunum.app.service.EvaluacijaZnanjaService;

import java.util.Optional;
import java.util.function.Function;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/evaluacijeZnanja")
public class EvaluacijaZnanjaController {
    @Autowired
    private EvaluacijaZnanjaService evaluacijaZnanjaService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK", "ROLE_STUDENT"})
    public ResponseEntity<Page<EvaluacijaZnanjaDTO>> getAll(Pageable pageable) {
        Page<EvaluacijaZnanja> evaluacijaZnanja = evaluacijaZnanjaService.findAll(pageable);
        Page<EvaluacijaZnanjaDTO> evaluacijeZnanja = evaluacijaZnanja.map(new Function<EvaluacijaZnanja, EvaluacijaZnanjaDTO>() {
            public EvaluacijaZnanjaDTO apply(EvaluacijaZnanja evaluacijaZnanja) {
                EvaluacijaZnanjaDTO evaluacijaZnanjaDTO = new EvaluacijaZnanjaDTO(evaluacijaZnanja.getId(), evaluacijaZnanja.getVremePocetka(),
                        evaluacijaZnanja.getVremeZavrsetka(),
                        new TipEvaluacijeDTO(evaluacijaZnanja.getTipEvaluacije().getId(), evaluacijaZnanja.getTipEvaluacije().getNaziv(),null),
                        new IshodDTO(evaluacijaZnanja.getIshod().getId(), evaluacijaZnanja.getIshod().getOpis(), null)
                );
                // Conversion logic
                return evaluacijaZnanjaDTO;
            }
        });
        return new ResponseEntity<Page<EvaluacijaZnanjaDTO>>(evaluacijeZnanja, HttpStatus.OK);
    }


    @RequestMapping(path = "/{evaluacijaZnanjaId}", method = RequestMethod.GET)
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK", "ROLE_STUDENT"})
    public ResponseEntity<EvaluacijaZnanjaDTO> get(@PathVariable("evaluacijaZnanjaId") Long evaluacijaZnanjaId) {
        Optional<EvaluacijaZnanja> evaluacijaZnanja = evaluacijaZnanjaService.findOne(evaluacijaZnanjaId);
        if (evaluacijaZnanja.isPresent()) {
            EvaluacijaZnanjaDTO evaluacijaZnanjaDTO = new EvaluacijaZnanjaDTO(evaluacijaZnanja.get().getId(),evaluacijaZnanja.get().getVremePocetka(),evaluacijaZnanja.get().getVremeZavrsetka(),
                    new TipEvaluacijeDTO(evaluacijaZnanja.get().getTipEvaluacije().getId(), evaluacijaZnanja.get().getTipEvaluacije().getNaziv(),null),
                    new IshodDTO(evaluacijaZnanja.get().getIshod().getId(), evaluacijaZnanja.get().getIshod().getOpis(),null));
            return new ResponseEntity<EvaluacijaZnanjaDTO>(evaluacijaZnanjaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<EvaluacijaZnanjaDTO>(HttpStatus.NOT_FOUND);
    }

    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
    public ResponseEntity<EvaluacijaZnanjaDTO> create(@RequestBody EvaluacijaZnanja evaluacijaZnanja) {
        try {
            evaluacijaZnanjaService.save(evaluacijaZnanja);
            TipEvaluacijeDTO tipEvaluacijeDTO =  new TipEvaluacijeDTO(evaluacijaZnanja.getTipEvaluacije().getId(),
                    evaluacijaZnanja.getTipEvaluacije().getNaziv(),null);
            IshodDTO ishodDTO =  new IshodDTO(evaluacijaZnanja.getIshod().getId(),
                    evaluacijaZnanja.getIshod().getOpis(),null);

            EvaluacijaZnanjaDTO evaluacijaZnanjaDTO = new EvaluacijaZnanjaDTO(evaluacijaZnanja.getId(), evaluacijaZnanja.getVremePocetka(),
                    evaluacijaZnanja.getVremeZavrsetka(), tipEvaluacijeDTO, ishodDTO);

            return new ResponseEntity<EvaluacijaZnanjaDTO>(evaluacijaZnanjaDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<EvaluacijaZnanjaDTO>(HttpStatus.BAD_REQUEST);
    }

    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "/{evaluacijaZnanjaId}", method = RequestMethod.PUT)
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
    public ResponseEntity<EvaluacijaZnanjaDTO> update(@PathVariable("evaluacijaZnanjaId") Long evaluacijaZnanjaId,
                                            @RequestBody EvaluacijaZnanja izmenjenaEvaluacijaZnanja) {
        EvaluacijaZnanja evaluacijaZnanja = evaluacijaZnanjaService.findOne(evaluacijaZnanjaId).orElse(null);
        if (evaluacijaZnanja != null) {
            izmenjenaEvaluacijaZnanja.setId(evaluacijaZnanjaId);
            izmenjenaEvaluacijaZnanja = evaluacijaZnanjaService.save(izmenjenaEvaluacijaZnanja);
            TipEvaluacijeDTO tipEvaluacijeDTO =  new TipEvaluacijeDTO(izmenjenaEvaluacijaZnanja.getTipEvaluacije().getId(),
                    izmenjenaEvaluacijaZnanja.getTipEvaluacije().getNaziv(),null);
            IshodDTO ishodDTO =  new IshodDTO(izmenjenaEvaluacijaZnanja.getIshod().getId(),
                    izmenjenaEvaluacijaZnanja.getIshod().getOpis(),null);

            EvaluacijaZnanjaDTO evaluacijaZnanjaDTO = new EvaluacijaZnanjaDTO(izmenjenaEvaluacijaZnanja.getId(), izmenjenaEvaluacijaZnanja.getVremePocetka(),
                    izmenjenaEvaluacijaZnanja.getVremeZavrsetka(), tipEvaluacijeDTO, ishodDTO);
            return new ResponseEntity<EvaluacijaZnanjaDTO>(evaluacijaZnanjaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<EvaluacijaZnanjaDTO>(HttpStatus.NOT_FOUND);
    }

    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "/{evaluacijaZnanjaId}", method = RequestMethod.DELETE)
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
    public ResponseEntity<EvaluacijaZnanjaDTO> delete(@PathVariable("evaluacijaZnanjaId") Long evaluacijaZnanjaId) {
        if (evaluacijaZnanjaService.findOne(evaluacijaZnanjaId).isPresent()) {
            evaluacijaZnanjaService.delete(evaluacijaZnanjaId);
            return new ResponseEntity<EvaluacijaZnanjaDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<EvaluacijaZnanjaDTO>(HttpStatus.NOT_FOUND);
    }
}
