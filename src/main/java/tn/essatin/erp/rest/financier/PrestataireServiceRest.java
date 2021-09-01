package tn.essatin.erp.rest.financier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.dao.financier.PrestataireServiceDao;
import tn.essatin.erp.model.financier.PrestataireService;
import tn.essatin.erp.payload.response.MessageResponse;
import tn.essatin.erp.util.ApiInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/prestataireservice/")
public class PrestataireServiceRest {
    final PrestataireServiceDao prestataireServiceDao;
    final MessageSource messageSource;

    @Autowired
    public PrestataireServiceRest(PrestataireServiceDao prestataireServiceDao, MessageSource messageSource) {
        this.prestataireServiceDao = prestataireServiceDao;
        this.messageSource = messageSource;
    }
    @GetMapping("/")
    public ResponseEntity<?> infoApi() {
        List<ApiInfo> infos = new ArrayList<>();
        List<MessageResponse> responses;
        ApiInfo info;
        /////////////////////
        responses = new ArrayList<>();
        info = new ApiInfo("/api/prestataireservice/getallprestataireservice", "Get",
                "retourne un JSON avec la liste de tout les prestataires de services dans la base",
                "/api/prestataireservice/getallprestataireservice",
                "JSON text Message", responses);
        infos.add(info);
        /////////////////////
        responses = new ArrayList<>();
        responses.add(new MessageResponse(messageSource.getMessage("error.introuvable.prestataireService", null, Locale.FRENCH), 403));
        info = new ApiInfo("/api/prestataireservice/getprestataireservice/{id}", "Get",
                "retourne un JSON avec la liste des prestataires de services par leur id",
                "/api/prestataireservice/getprestataireservice/1",
                "JSON text Message", responses);
        infos.add(info);
        /////////////////////
        return new ResponseEntity<>(infos, HttpStatus.OK);

    }
    @GetMapping("/getallprestataireservice")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(prestataireServiceDao.findAll(), HttpStatus.OK);
    }
    @PostMapping("getprestataireservice/{id}")
    public  ResponseEntity<?> getPrestataireById(@PathVariable int id){
        Optional<PrestataireService> prestataireService = prestataireServiceDao.findById(id);
        if (prestataireService.isEmpty()){
            return new ResponseEntity<>(new MessageResponse(messageSource.getMessage("error.introuvable.prestataireService", null, Locale.FRENCH)), HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(prestataireService.get(), HttpStatus.OK);
    }



}
