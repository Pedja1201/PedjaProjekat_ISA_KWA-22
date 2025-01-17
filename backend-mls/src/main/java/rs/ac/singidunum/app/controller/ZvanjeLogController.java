package rs.ac.singidunum.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.ac.singidunum.app.dto.ZvanjeLogDTO;
import rs.ac.singidunum.app.model.ZvanjeLog;
import rs.ac.singidunum.app.service.ZvanjeLogService;

import java.util.ArrayList;

@Controller
@RequestMapping(path = "/api/logs/zvanje")
public class ZvanjeLogController {
    @Autowired
    private ZvanjeLogService zvanjeLogService;


    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<ZvanjeLogDTO>> getAll() {
        ArrayList<ZvanjeLogDTO> zvanjaLog = new ArrayList<ZvanjeLogDTO>();
        for (ZvanjeLog zvanjeLog : zvanjeLogService.findAll()) {
            zvanjaLog.add(new ZvanjeLogDTO(zvanjeLog.getId(),zvanjeLog.getSignature(),zvanjeLog.getMessage(),zvanjeLog.getDateTime(),
                    zvanjeLog.getType()));
        }

        return new ResponseEntity<Iterable<ZvanjeLogDTO>>(zvanjaLog, HttpStatus.OK);
    }
}
