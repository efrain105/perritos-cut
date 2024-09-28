package com.cut.cardona.controllers;

import com.cut.cardona.modelo.perros.*;
import com.cut.cardona.modelo.perros.repositorio.PerrosSpecifications;
import com.cut.cardona.modelo.perros.repositorio.RepositorioPerro;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class PerroController {

    @Autowired
    private RepositorioPerro repositorioPerro;


    //Controller para mostrar la vista de crear perro
    @GetMapping("/crear-perro")
    public String perrosCreate(@ModelAttribute DatosPerro datosPerro, Model model) {
        model.addAttribute("datosPerros", datosPerro);
        return "crear-perro";
    }

    //Controller para mostrar la vista de perros
    @GetMapping("/perros")
    public String perrosList(@ModelAttribute DatosPerro datosPerro, Model model) {
        model.addAttribute("datosPerros", datosPerro);
        return "perros";
    }

    //variable para mostrar los perros, se le pasa el query, page y size
    @ModelAttribute("perros")
    public String mostrarPerros(@RequestParam(value = "query", required = false) String query,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "6") int size,
                                Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Perro> perrosPage;

        if (query != null && !query.isEmpty()) {
            Specification<Perro> perroSpecification = Specification.where(PerrosSpecifications.nombreContains(query))
                    .or(PerrosSpecifications.razaContains(query))
                    .or(PerrosSpecifications.colorContains(query));
            try {
                Integer edad = Integer.valueOf(query);
                perroSpecification = perroSpecification.or(PerrosSpecifications.edadIs(edad));
            } catch (NumberFormatException e) {
                // Ignore, as the query is not a valid integer
            }

            perrosPage = repositorioPerro.findAll(perroSpecification, pageable);
        } else {
            perrosPage = repositorioPerro.findAll(pageable);
        }

        model.addAttribute("currentPagePerros", page);
        model.addAttribute("totalPagesPerros", perrosPage.getTotalPages());
        model.addAttribute("totalItemsPerros", perrosPage.getTotalElements());
        model.addAttribute("perrosPage", perrosPage);
        model.addAttribute("sizePerros", perrosPage.getSize());
        model.addAttribute("perros", perrosPage.getContent().stream().map(DatosMostrarPerros::new).toList());

        return "perros";
    }

    //Metodo post para guardar un perro
    @PostMapping("/perros")
    @Transactional
    public String crearPerro(@ModelAttribute DatosPerro datosPerro, RedirectAttributes redirectAttributes
    ) {
        Perro perro = new Perro(datosPerro);
        try {
            repositorioPerro.save(perro);
            String successfulMessage = "Se ha guardado correctamente el perrito: " + perro.getNombre();
            redirectAttributes.addFlashAttribute("successfulRegistroPerro", successfulMessage);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = "No se ha podido guardar el perrito, por favor intente de nuevo";
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/crear-perro";
        } catch (Exception e) {
            String errorMessage = "A ocurrido un error, por favor intente de nuevo";
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/crear-perro";
        }
        return "redirect:/crear-perro";
    }

    //metodo update
    @PostMapping("perros-update")
    public String actualizarPerro(@ModelAttribute DatosPerro datosActualizarPerro, Model model, @RequestParam Long perroId, RedirectAttributes redirectAttributes) {
        Perro perro = repositorioPerro.getReferenceById(perroId);

        try {
            perro.actualizarDatos(datosActualizarPerro);
            repositorioPerro.save(perro);
            String successfulMessage = "Se ha actualizado correctamente el perrito: " + perro.getNombre();
            redirectAttributes.addFlashAttribute("actualizado", successfulMessage);
        } catch (DataIntegrityViolationException e) {
            // Este bloque maneja cualquier tipo de violación de integridad (por ejemplo, email o teléfono duplicado)
            String errorMessage = "Hay campos que, no pueden estar vacíos, por favor, inténtalo de nuevo.";
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/perros";
        } catch (Exception e) {
            // Manejo de cualquier otra excepción inesperada
            String errorMessage = "Ocurrió un error inesperado. Por favor, inténtalo de nuevo.";
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/perros";
        }
        return "redirect:/perros";
    }


    @GetMapping("/eliminar-perro")
    @Transactional
    public String eliminarPerro(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        Optional<Perro> byPerroId = repositorioPerro.findById(id);
        Optional<Perro> perro = repositorioPerro.findById(id);
        perro.ifPresent(repositorioPerro::delete);
        if (perro.isPresent()) {
            String message = "Se ha eliminado correctamente el perrito: " + perro.get().getNombre();
            redirectAttributes.addFlashAttribute("eliminado", message);
        } else {
            String message = "No se encontró el perrito con el id: " + id;
            redirectAttributes.addFlashAttribute("error", message);
        }
        return "redirect:/perros";
    }


    /*@PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaPerros> crearPerro(@RequestBody DatosPerro datosPerro, UriComponentsBuilder uriComponentsBuilder){

        Perro perro = repositorioPerro.save(new Perro(datosPerro));
        repositorioPerro.findAll();
        URI uri = uriComponentsBuilder.path("/perros/{id}").buildAndExpand(perro.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosRespuestaPerros(perro));
    }*/

}
