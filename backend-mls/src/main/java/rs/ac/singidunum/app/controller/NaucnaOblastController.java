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
import rs.ac.singidunum.app.dto.NaucnaOblastDTO;
import rs.ac.singidunum.app.dto.TipZvanjaDTO;
import rs.ac.singidunum.app.dto.ZvanjeDTO;
import rs.ac.singidunum.app.model.NaucnaOblast;
import rs.ac.singidunum.app.model.Zvanje;
import rs.ac.singidunum.app.service.NaucnaOblastService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/naucneOblasti")
public class NaucnaOblastController {
    @Autowired
    private NaucnaOblastService naucnaOblastService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Page<NaucnaOblastDTO>> getAllNaucnaOblast(Pageable pageable) {
        Page<NaucnaOblast> naucneOblasti = naucnaOblastService.findAll(pageable);
        return new ResponseEntity<Page<NaucnaOblastDTO>>(
                naucneOblasti.map(naucnaOblast -> new NaucnaOblastDTO(naucnaOblast.getId(), naucnaOblast.getNaziv(),
                        (ArrayList<ZvanjeDTO>) naucnaOblast.getZvanja().stream()
                                .map(zvanje -> new ZvanjeDTO(zvanje.getId(), zvanje.getDatumIzbora(), zvanje.getDatumPrestanka(), null,
                                        new TipZvanjaDTO(zvanje.getTipZvanja().getId(),
                                                zvanje.getTipZvanja().getNaziv(), null)))
                                .collect(Collectors.toList()))),
                HttpStatus.OK);
    }

    @RequestMapping(path = "/{naucnaOblastId}", method = RequestMethod.GET)
    public ResponseEntity<NaucnaOblastDTO> getNaucnaOblast(@PathVariable("naucnaOblastId") Long naucnaOblastId) {
        Optional<NaucnaOblast> naucnaOblast = naucnaOblastService.findOne(naucnaOblastId);

        NaucnaOblastDTO naucnaOblastDTO;

        if (naucnaOblast.isPresent()) {

            ArrayList<ZvanjeDTO> zvanja = new ArrayList<ZvanjeDTO>();
            for (Zvanje zvanje : naucnaOblast.get().getZvanja()) {
                zvanja.add(new ZvanjeDTO(zvanje.getId(), zvanje.getDatumIzbora(),zvanje.getDatumPrestanka(),
                        null,
                         null));
            }

            naucnaOblastDTO = new NaucnaOblastDTO(naucnaOblast.get().getId(), naucnaOblast.get().getNaziv(), zvanja);

            return new ResponseEntity<NaucnaOblastDTO>(naucnaOblastDTO, HttpStatus.OK);
        }
        return new ResponseEntity<NaucnaOblastDTO>(HttpStatus.NOT_FOUND);
    }

    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "", method = RequestMethod.POST)
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
    public ResponseEntity<NaucnaOblastDTO> createNaucnaOblast(@RequestBody NaucnaOblast naucnaOblast) {
        try {
            naucnaOblastService.save(naucnaOblast);
            ArrayList<ZvanjeDTO> zvanja = new ArrayList<ZvanjeDTO>();
            for(Zvanje zvanje : naucnaOblast.getZvanja()) {
                zvanja.add(new ZvanjeDTO(zvanje.getId(), zvanje.getDatumIzbora(), zvanje.getDatumPrestanka(),
                        null,
                       null));
            }
            NaucnaOblastDTO naucnaOblastDTO = new NaucnaOblastDTO(naucnaOblast.getId(), naucnaOblast.getNaziv(), zvanja);
            return new ResponseEntity<NaucnaOblastDTO>(naucnaOblastDTO,HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<NaucnaOblastDTO>(HttpStatus.BAD_REQUEST);
    }

    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "/{naucnaOblastId}", method = RequestMethod.PUT)
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
    public ResponseEntity<NaucnaOblastDTO> updateNaucnaOblast(@PathVariable("naucnaOblastId") Long naucnaOblastId,
                                               @RequestBody NaucnaOblast izmenjenaNaucnaOblast) {
        NaucnaOblast naucnaOblast = naucnaOblastService.findOne(naucnaOblastId).orElse(null);
        if (naucnaOblast != null) {
            izmenjenaNaucnaOblast.setId(naucnaOblastId);
            izmenjenaNaucnaOblast = naucnaOblastService.save(izmenjenaNaucnaOblast);
            ArrayList<ZvanjeDTO> zvanja = new ArrayList<ZvanjeDTO>();
            for(Zvanje zvanje : naucnaOblast.getZvanja()) {
                zvanja.add(new ZvanjeDTO(zvanje.getId(), zvanje.getDatumIzbora(), zvanje.getDatumPrestanka(),
                        null,
                     null));
            }
            NaucnaOblastDTO naucnaOblastDTO = new NaucnaOblastDTO(izmenjenaNaucnaOblast.getId(), izmenjenaNaucnaOblast.getNaziv(), zvanja);
            return new ResponseEntity<NaucnaOblastDTO>(naucnaOblastDTO, HttpStatus.OK);
        }
        return new ResponseEntity<NaucnaOblastDTO>(HttpStatus.NOT_FOUND);
    }

    @LoggedAdministrator
    @LoggedNastavnik
    @RequestMapping(path = "/{naucnaOblastId}", method = RequestMethod.DELETE)
    @Secured({"ROLE_ADMIN", "ROLE_NASTAVNIK"})
    public ResponseEntity<NaucnaOblastDTO> deleteNaucnaOblast(@PathVariable("naucnaOblastId") Long naucnaOblastId) {
        if (naucnaOblastService.findOne(naucnaOblastId).isPresent()) {
            naucnaOblastService.delete(naucnaOblastId);
            return new ResponseEntity<NaucnaOblastDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<NaucnaOblastDTO>(HttpStatus.NOT_FOUND);
    }
}
