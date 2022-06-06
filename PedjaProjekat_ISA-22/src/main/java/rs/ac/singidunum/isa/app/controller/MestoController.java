package rs.ac.singidunum.isa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.ac.singidunum.isa.app.dto.DrzavaDTO;
import rs.ac.singidunum.isa.app.dto.MestoDTO;
import rs.ac.singidunum.isa.app.model.Mesto;
import rs.ac.singidunum.isa.app.service.MestoService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

@Controller
@RequestMapping(path = "/api/mesta")
public class MestoController {
    @Autowired
    private MestoService mestoService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Page<MestoDTO>> getAll(Pageable pageable) {
        Page<Mesto> mesto = mestoService.findAll(pageable);
        Page<MestoDTO> karte = mesto.map(new Function<Mesto, MestoDTO>() {
            public MestoDTO apply(Mesto mesto) {
                MestoDTO mestoDTO = new MestoDTO(mesto.getId(), mesto.getNaziv(),
                        new DrzavaDTO(mesto.getDrzava().getId(), mesto.getDrzava().getNaziv(),null)

                );
                // Conversion logic
                return mestoDTO;
            }
        });
        return new ResponseEntity<Page<MestoDTO>>(karte, HttpStatus.OK);
    }

    @RequestMapping(path = "/{mestoId}", method = RequestMethod.GET)
    public ResponseEntity<MestoDTO> get(@PathVariable("mestoId") Long mestoId) {
        Optional<Mesto> mesto = mestoService.findOne(mestoId);
        if (mesto.isPresent()) {
            MestoDTO mestoDTO = new MestoDTO(mesto.get().getId(),mesto.get().getNaziv(),
                    new DrzavaDTO(mesto.get().getDrzava().getId(), mesto.get().getDrzava().getNaziv(), null));
            return new ResponseEntity<MestoDTO>(mestoDTO, HttpStatus.OK);
        }
        return new ResponseEntity<MestoDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<MestoDTO> create(@RequestBody Mesto mesto) {
        try {
            mestoService.save(mesto);
            DrzavaDTO drzavaDTO = new DrzavaDTO(mesto.getDrzava().getId(), mesto.getDrzava().getNaziv(),null);

            MestoDTO mestoDTO = new MestoDTO(mesto.getId(), mesto.getNaziv(), drzavaDTO);

            return new ResponseEntity<MestoDTO>(mestoDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<MestoDTO>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{mestoId}", method = RequestMethod.PUT)
    public ResponseEntity<MestoDTO> update(@PathVariable("mestoId") Long mestoId,
                                                   @RequestBody Mesto izmenjenoMesto) {
        Mesto mesto = mestoService.findOne(mestoId).orElse(null);
        if (mesto != null) {
            izmenjenoMesto.setId(mestoId);
            izmenjenoMesto = mestoService.save(izmenjenoMesto);
            DrzavaDTO drzavaDTO = new DrzavaDTO(izmenjenoMesto.getDrzava().getId(), izmenjenoMesto.getDrzava().getNaziv(),null);

            MestoDTO mestoDTO = new MestoDTO(izmenjenoMesto.getId(), izmenjenoMesto.getNaziv(), drzavaDTO);
            return new ResponseEntity<MestoDTO>(mestoDTO, HttpStatus.OK);
        }
        return new ResponseEntity<MestoDTO>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/{mestoId}", method = RequestMethod.DELETE)
    public ResponseEntity<MestoDTO> delete(@PathVariable("mestoId") Long mestoId) {
        if (mestoService.findOne(mestoId).isPresent()) {
            mestoService.delete(mestoId);
            return new ResponseEntity<MestoDTO>(HttpStatus.OK);
        }
        return new ResponseEntity<MestoDTO>(HttpStatus.NOT_FOUND);
    }

    //DOne: Metoda i upit za pronalaženje drzave po nazivu
    @RequestMapping(path = "/findDrzava/{drzavaNaziv}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<MestoDTO>> findDrzava(@PathVariable("drzavaNaziv") String drzavaNaziv) {
        ArrayList<MestoDTO> mestaDTO = new ArrayList<>();
        for(Mesto mesto : mestoService.findDrzava(drzavaNaziv)) {
            System.out.println(mesto.getNaziv());
            DrzavaDTO drzavaDTO = new DrzavaDTO(mesto.getDrzava().getId(),mesto.getDrzava().getNaziv(), null);
            mestaDTO.add(new MestoDTO(mesto.getId(), mesto.getNaziv(), drzavaDTO));
        }
        return new ResponseEntity<Iterable<MestoDTO>>(mestaDTO, HttpStatus.OK);

    }
}
