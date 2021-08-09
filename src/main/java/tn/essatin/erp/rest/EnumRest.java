package tn.essatin.erp.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.payload.response.MessageResponse;
import tn.essatin.erp.util.EnumRepresentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/enum/")
public class EnumRest {
    @GetMapping("/{EType}")
    ResponseEntity<?> getTypeTransaction(@PathVariable String EType) {
        List<String> pakages = Arrays.stream(Package.getPackages()).map(Package::getName)
                .filter(n -> n.startsWith("tn.essatin.erp.model")).collect(Collectors.toList());
        int count = 0;
        for (String pakage : pakages) {
            count++;
            try {
                Class<? extends Enum> c = (Class<? extends Enum>) Class.forName(pakage + "." + EType);
                List<EnumRepresentation> enumRepresentations = new ArrayList<>();
                for (int i = 0; i < c.getEnumConstants().length; i++) {
                    enumRepresentations.add(new EnumRepresentation(i, c.getEnumConstants()[i].name(),
                            c.getEnumConstants()[i].toString()));
                }
                return new ResponseEntity<>(enumRepresentations, HttpStatus.OK);
            } catch (Exception e) {
            }
        }
        return new ResponseEntity<>(
                new MessageResponse("Impossible de trouver l'ennum recherché"), HttpStatus.FORBIDDEN);
    }


}