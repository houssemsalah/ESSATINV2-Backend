package tn.essatin.erp.rest;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.essatin.erp.payload.response.MessageResponse;
import tn.essatin.erp.util.ApiInfo;
import tn.essatin.erp.util.EnumRepresentation;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/enum/")
public class EnumRest {
    @GetMapping("/{EType}")
    ResponseEntity<?> getTypeTransaction(@PathVariable String EType) {
        List<String> pakages = Arrays.stream(Package.getPackages()).map(Package::getName)
                .filter(n -> n.startsWith("tn.essatin.erp.model")).collect(Collectors.toList());
        for (String pakage : pakages) {
            try {
                Class<? extends Enum> c = (Class<? extends Enum>) Class.forName(pakage + "." + EType);
                List<EnumRepresentation> enumRepresentations = new ArrayList<>();
                for (int i = 0; i < c.getEnumConstants().length; i++) {
                    enumRepresentations.add(new EnumRepresentation(i+1, c.getEnumConstants()[i].name(),
                            c.getEnumConstants()[i].toString()));
                }
                return new ResponseEntity<>(enumRepresentations, HttpStatus.OK);
            } catch (Exception e) {
                continue;
            }
        }
        return new ResponseEntity<>(
                new MessageResponse("Impossible de trouver l'ennum recherché"), HttpStatus.FORBIDDEN);
    }

    @GetMapping("/")
    ResponseEntity<?> getTypeTransaction() {
        List<String> pakages = Arrays.stream(Package.getPackages()).map(Package::getName)
                .filter(n -> n.startsWith("tn.essatin.erp.model")).collect(Collectors.toList());
        Reflections reflections;
        Set<Class<?>> allClasses = new HashSet<>();
        List<ClassLoader> classLoadersList = new LinkedList<>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());
        for (String pakage : pakages) {
            reflections = new Reflections(new ConfigurationBuilder()
                    .setScanners(new SubTypesScanner(false), new ResourcesScanner())
                    .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                    .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(pakage))));
            allClasses.addAll(reflections.getSubTypesOf(Enum.class));
        }
        List<String> l = new ArrayList<>();
        for (Class enumcls : allClasses)
            l.add("/api/enum/" + enumcls.toString().substring(enumcls.toString().lastIndexOf('.') + 1));
        List<ApiInfo> infos = new ArrayList<>();
        List<MessageResponse> responses;
        ApiInfo info;
        responses = new ArrayList<>();
        info = new ApiInfo("/api/enum/{EType}", "Get",
                "retourne un JSON avec la liste des Constante de l'enumeration",
                l,
                "une texte JSON avec une liste de constante de l'enumeration", responses);
        infos.add(info);
        return new ResponseEntity<>(infos, HttpStatus.OK);
    }

}