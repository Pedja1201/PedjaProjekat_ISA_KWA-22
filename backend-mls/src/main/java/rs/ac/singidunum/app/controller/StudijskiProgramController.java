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
import rs.ac.singidunum.app.aspect.LoggedStudijskiProgram;
import rs.ac.singidunum.app.dto.*;
import rs.ac.singidunum.app.model.StudijskiProgram;
import rs.ac.singidunum.app.service.StudijskiProgramService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/studijskiProgrami")
public class StudijskiProgramController {
    @Autowired
    private StudijskiProgramService studijskiProgramService;

    @LoggedStudijskiProgram //TODO:Pokrenuti Artemis ukoliko koristimo izvrsavanje metode
    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Page<StudijskiProgramDTO>> getAll(Pageable pageable) {
        Page<StudijskiProgram> studijskiProgram = studijskiProgramService.findAll(pageable);
        Page<StudijskiProgramDTO> studijskiProgrami = studijskiProgram.map(new Function<StudijskiProgram, StudijskiProgramDTO>() {
            public StudijskiProgramDTO apply(StudijskiProgram studijskiProgram) {
                StudijskiProgramDTO studijskiProgramDTO = new StudijskiProgramDTO(studijskiProgram.getId(),studijskiProgram.getNaziv(),
                        new FakultetDTO(studijskiProgram.getFakultet().getId(),studijskiProgram.getFakultet().getNaziv(),
                                null,null,null),
                        new NastavnikDTO(studijskiProgram.getNastavnik().getId(), studijskiProgram.getNastavnik().getKorisnickoIme(),
                                studijskiProgram.getNastavnik().getLozinka(),studijskiProgram.getNastavnik().getEmail(),studijskiProgram.getNastavnik().getIme(),
                                studijskiProgram.getNastavnik().getBiografija(),studijskiProgram.getNastavnik().getJmbg(),null,null),
                        new GodinaStudijaDTO(studijskiProgram.getGodinaStudija().getId(), studijskiProgram.getGodinaStudija().getGodina(),null)
                );
                // Conversion logic
                return studijskiProgramDTO;
            }
        });
        return new ResponseEntity<Page<StudijskiProgramDTO>>(studijskiProgrami, HttpStatus.OK);
    }

    @RequestMapping(path = "/{studijskiProgramId}", method = RequestMethod.GET)
    public ResponseEntity<StudijskiProgramDTO> get(@PathVariable("studijskiProgramId") Long studijskiProgramId) {
        Optional<StudijskiProgram> studijskiProgram = studijskiProgramService.findOne(studijskiProgramId);
        if (studijskiProgram.isPresent()) {
            StudijskiProgramDTO prodajaDTO = new StudijskiProgramDTO(studijskiProgram.get().getId(),studijskiProgram.get().getNaziv(),
                    new FakultetDTO(studijskiProgram.get().getFakultet().getId(),
                            studijskiProgram.get().getFakultet().getNaziv(),null,null,null),
                    new NastavnikDTO(studijskiProgram.get().getNastavnik().getId(),studijskiProgram.get().getNastavnik().getKorisnickoIme(),
                            studijskiProgram.get().getNastavnik().getLozinka(),studijskiProgram.get().getNastavnik().getEmail(),studijskiProgram.get().getNastavnik().getIme(),
                            studijskiProgram.get().getNastavnik().getBiografija(), studijskiProgram.get().getNastavnik().getJmbg(),
                                            null,null),
                    new GodinaStudijaDTO(studijskiProgram.get().getGodinaStudija().getId(),
                            studijskiProgram.get().getGodinaStudija().getGodina(), null));

            return new ResponseEntity<StudijskiProgramDTO>(prodajaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<StudijskiProgramDTO>(HttpStatus.NOT_FOUND);
    }

    @LoggedAdministrator
    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<StudijskiProgramDTO> create(@RequestBody StudijskiProgram studijskiProgram) {
        try {
            studijskiProgramService.save(studijskiProgram);
            FakultetDTO fakultetDTO = new FakultetDTO(studijskiProgram.getFakultet().getId(),studijskiProgram.getFakultet().getNaziv(),
                                                        null,null,null);
            NastavnikDTO nastavnikDTO = new NastavnikDTO(studijskiProgram.getNastavnik().getId(), studijskiProgram.getNastavnik().getKorisnickoIme(),
                    studijskiProgram.getNastavnik().getLozinka(),studijskiProgram.getNastavnik().getEmail(), studijskiProgram.getNastavnik().getIme(),
                    studijskiProgram.getNastavnik().getBiografija(),studijskiProgram.getNastavnik().getJmbg(),null,null);
            GodinaStudijaDTO godinaStudijaDTO = new GodinaStudijaDTO(studijskiProgram.getGodinaStudija().getId(), studijskiProgram.getGodinaStudija().getGodina(),null);

            StudijskiProgramDTO studijskiProgramDTO = new StudijskiProgramDTO(studijskiProgram.getId(),
                    studijskiProgram.getNaziv(),fakultetDTO, nastavnikDTO, godinaStudijaDTO);

            return new ResponseEntity<StudijskiProgramDTO>(studijskiProgramDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<StudijskiProgramDTO>(HttpStatus.BAD_REQUEST);
    }

    @LoggedAdministrator
    @RequestMapping(path = "/{studisjkiProgramId}", method = RequestMethod.PUT)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<StudijskiProgramDTO> update(@PathVariable("studisjkiProgramId") Long studisjkiProgramId,
                                               @RequestBody StudijskiProgram izmenjeniStudijskiProgram) {
        StudijskiProgram studijskiProgram = studijskiProgramService.findOne(studisjkiProgramId).orElse(null);
        if (studijskiProgram != null) {
            izmenjeniStudijskiProgram.setId(studisjkiProgramId);
            izmenjeniStudijskiProgram = studijskiProgramService.save(izmenjeniStudijskiProgram);
            FakultetDTO fakultetDTO = new FakultetDTO(izmenjeniStudijskiProgram.getFakultet().getId(),izmenjeniStudijskiProgram.getFakultet().getNaziv(),
                    null,null,null);
            NastavnikDTO nastavnikDTO = new NastavnikDTO(izmenjeniStudijskiProgram.getNastavnik().getId(), izmenjeniStudijskiProgram.getNastavnik().getKorisnickoIme(),
                    izmenjeniStudijskiProgram.getNastavnik().getLozinka(),izmenjeniStudijskiProgram.getNastavnik().getEmail(),izmenjeniStudijskiProgram.getNastavnik().getIme(),
                    izmenjeniStudijskiProgram.getNastavnik().getBiografija(),izmenjeniStudijskiProgram.getNastavnik().getJmbg(),null,null);
            GodinaStudijaDTO godinaStudijaDTO = new GodinaStudijaDTO(izmenjeniStudijskiProgram.getGodinaStudija().getId(), izmenjeniStudijskiProgram.getGodinaStudija().getGodina(),null);

            StudijskiProgramDTO studijskiProgramDTO = new StudijskiProgramDTO(izmenjeniStudijskiProgram.getId(),
                    izmenjeniStudijskiProgram.getNaziv(),fakultetDTO, nastavnikDTO, godinaStudijaDTO);
            return new ResponseEntity<StudijskiProgramDTO>(studijskiProgramDTO, HttpStatus.OK);
        }
        return new ResponseEntity<StudijskiProgramDTO>(HttpStatus.NOT_FOUND);
    }

    @LoggedAdministrator
    @RequestMapping(path = "/{studisjkiProgramId}", method = RequestMethod.DELETE)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<StudijskiProgramDTO> delete(@PathVariable("studisjkiProgramId") Long studisjkiProgramId) {
        if (studijskiProgramService.findOne(studisjkiProgramId).isPresent()) {
            studijskiProgramService.delete(studisjkiProgramId);
            return new ResponseEntity<StudijskiProgramDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<StudijskiProgramDTO>(HttpStatus.NOT_FOUND);
    }


    //DONE: Metoda i upit za pronalaženje Fakulteta u studijskom programu
    @RequestMapping(path = "/findFakultet/{fakultetNaziv}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<StudijskiProgramDTO>> findFakultetStudijskogProgrmama(@PathVariable("fakultetNaziv") String fakultetNaziv) {
        ArrayList<StudijskiProgramDTO> studijskiProgramiDTO = new ArrayList<>();
        for(StudijskiProgram studijskiProgram : studijskiProgramService.findFakultetStudijskogProgrmama(fakultetNaziv)) {
            System.out.println(studijskiProgram.getNaziv());
            FakultetDTO fakultetDTO = new FakultetDTO(studijskiProgram.getFakultet().getId(),studijskiProgram.getFakultet().getNaziv(),
                    new UniverzitetDTO(studijskiProgram.getFakultet().getUniverzitet().getId(), studijskiProgram.getFakultet().getUniverzitet().getNaziv(),
                            studijskiProgram.getFakultet().getUniverzitet().getDatumVremeOsnivanja(),null,null),
                    new AdresaDTO(studijskiProgram.getFakultet().getAdresa().getId(),studijskiProgram.getFakultet().getAdresa().getUlica(),
                            studijskiProgram.getFakultet().getAdresa().getBroj(),null),
                    null);
            studijskiProgramiDTO.add(new StudijskiProgramDTO(studijskiProgram.getId(), studijskiProgram.getNaziv(),
                    fakultetDTO, null, null));
        }
        return new ResponseEntity<Iterable<StudijskiProgramDTO>>(studijskiProgramiDTO, HttpStatus.OK);

    }

    //DONE: Metoda i upit za pronalaženje Nastavnika u studijskom programu
    @RequestMapping(path = "/findNastavnik/{nastavnikIme}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<StudijskiProgramDTO>> findNastavnikStudijskogProgrmama(@PathVariable("nastavnikIme") String nastavnikIme) {
        ArrayList<StudijskiProgramDTO> studijskiProgramiDTO = new ArrayList<>();
        for(StudijskiProgram studijskiProgram : studijskiProgramService.findNastavnikStudijskogProgrmama(nastavnikIme)) {
            System.out.println(studijskiProgram.getNaziv());
            NastavnikDTO nastavnikDTO = new NastavnikDTO(studijskiProgram.getNastavnik().getId(),studijskiProgram.getNastavnik().getKorisnickoIme(),null,
                    studijskiProgram.getNastavnik().getEmail(), studijskiProgram.getNastavnik().getIme(), studijskiProgram.getNastavnik().getBiografija(),studijskiProgram.getNastavnik().getJmbg(),
                    new AdresaDTO(studijskiProgram.getNastavnik().getAdresa().getId(),studijskiProgram.getNastavnik().getAdresa().getUlica(),
                            studijskiProgram.getNastavnik().getAdresa().getBroj(), null),
                    new ZvanjeDTO(studijskiProgram.getNastavnik().getZvanje().getId(),studijskiProgram.getNastavnik().getZvanje().getDatumIzbora(),
                            studijskiProgram.getNastavnik().getZvanje().getDatumPrestanka(),null, null));

            studijskiProgramiDTO.add(new StudijskiProgramDTO(studijskiProgram.getId(), studijskiProgram.getNaziv(),
                    null, nastavnikDTO, null));
        }
        return new ResponseEntity<Iterable<StudijskiProgramDTO>>(studijskiProgramiDTO, HttpStatus.OK);

    }
}
