package com.cut.cardona.controllers;

import com.cut.cardona.modelo.voluntarios.DatosMostrarVoluntario;
import com.cut.cardona.modelo.voluntarios.DatosVoluntario;
import com.cut.cardona.modelo.voluntarios.Rol;
import com.cut.cardona.modelo.voluntarios.repositorio.RepositorioVoluntario;
import com.cut.cardona.modelo.voluntarios.Voluntario;
import com.cut.cardona.modelo.voluntarios.repositorio.VoluntarioSpecifications;
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
public class VoluntarioController {

    @Autowired
    private RepositorioVoluntario repositorioVoluntario;


    @GetMapping("/voluntarios")
    public String voluntariosList(@ModelAttribute DatosVoluntario datosVoluntario, Model model) {
        model.addAttribute("datosVoluntario", datosVoluntario);
        return "/voluntarios";
    }

    @GetMapping("/crear-voluntario")
    public String voluntariosCreate(@ModelAttribute DatosVoluntario datosVoluntario, Model model) {
        model.addAttribute("datosVoluntario", datosVoluntario);
        return "/crear-voluntario";
    }

    //metodo api, para hacer un fetch a la tabla voluntarios, se puede consultar ewl codigo y elñ script en /test/voluntarios
   /* @GetMapping("/api/voluntarios")
    @ResponseBody
    public Page<DatosMostrarVoluntario> getVoluntarios(@RequestParam(value = "query", required = false) String query,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "6") int size) {
        try {
            // Simular tiempo de espera de 5 segundos
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<Voluntario> voluntariosPage;

        if (query != null && !query.isEmpty()) {
            Specification<Voluntario> spec = Specification.where(VoluntarioSpecifications.nombreContains(query))
                    .or(VoluntarioSpecifications.apellidoContains(query))
                    .or(VoluntarioSpecifications.telefonoContains(query))
                    .or(VoluntarioSpecifications.emailContains(query));
            voluntariosPage = repositorioVoluntario.findAll(spec, pageable);
        } else {
            voluntariosPage = repositorioVoluntario.findAll(pageable);
        }

        return voluntariosPage.map(DatosMostrarVoluntario::new);
    }*/

    @ModelAttribute("voluntarios")
    public String mostrarVoluntarios(@RequestParam(value = "query", required = false) String query,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "6") int size,
                                     Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Voluntario> voluntariosPage;

        if (query != null && !query.isEmpty()) {
            Specification<Voluntario> spec = Specification.where(VoluntarioSpecifications.nombreContains(query))
                    .or(VoluntarioSpecifications.apellidoContains(query))
                    .or(VoluntarioSpecifications.telefonoContains(query))
                    .or(VoluntarioSpecifications.emailContains(query));
            voluntariosPage = repositorioVoluntario.findAll(spec, pageable);
        } else {
            voluntariosPage = repositorioVoluntario.findAll(pageable);
        }
        model.addAttribute("currentPageVoluntarios", page);
        model.addAttribute("totalPagesVoluntarios", voluntariosPage.getTotalPages());
        model.addAttribute("totalItemsVoluntarios", voluntariosPage.getTotalElements());
        model.addAttribute("voluntariosPage", voluntariosPage);
        model.addAttribute("sizeVoluntarios", voluntariosPage.getSize());
        model.addAttribute("voluntarios", voluntariosPage.getContent().stream().map(DatosMostrarVoluntario::new).toList());

        return "voluntarios";
    }


    @PostMapping("/crear-voluntario")
    public String crearVoluntario(@ModelAttribute DatosVoluntario datosVoluntario, RedirectAttributes redirectAttributes) {

        Voluntario voluntario = new Voluntario(datosVoluntario);
        try {
            repositorioVoluntario.save(voluntario);
            String successfulMessage = "Registro exitoso";
            redirectAttributes.addFlashAttribute("successfulRegistroVoluntario", successfulMessage);
        } catch (DataIntegrityViolationException e) {
            // Este bloque maneja cualquier tipo de violación de integridad (por ejemplo, email o teléfono duplicado)
            String errorMessage = "Los datos proporcionados ya están registrados (correo o teléfono duplicados)";
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/crear-voluntario";
        } catch (Exception e) {
            // Manejo de cualquier otra excepción inesperada
            String errorMessage = "Ocurrió un error inesperado. Por favor, inténtalo de nuevo.";
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/crear-voluntario";
        }
        return "redirect:/crear-voluntario";
    }


    //metodo update

    @PostMapping("/voluntariosact")
    public String actualizarVoluntario(@ModelAttribute DatosVoluntario datosVoluntario,
                                       @RequestParam Long voluntarioId,
                                       RedirectAttributes redirectAttributes) {

        Voluntario voluntario = repositorioVoluntario.getReferenceById(voluntarioId);
        try {
            voluntario.actualizarDatos(datosVoluntario);
            repositorioVoluntario.save(voluntario);
            String message = "Se ha actualizado correctamente el usuario con el id: " + voluntarioId;
            redirectAttributes.addFlashAttribute("actualizado", message);
            // Enviar el mensaje al modelo
        } catch (DataIntegrityViolationException e) {
            String errorMessage = "Los datos proporcionados ya están registrados (correo o teléfono duplicados)";
            redirectAttributes.addFlashAttribute("error", errorMessage);  // Enviar el error al modelo
        } catch (Exception e) {
            String errorMessage = "Ocurrió un error inesperado. Por favor, inténtalo de nuevo.";
            redirectAttributes.addFlashAttribute("error", errorMessage);  // Enviar el error al modelo
        }

        return "redirect:/voluntarios";  // Aquí pones el nombre de la vista que deseas renderizar
    }


    @GetMapping("/eliminar-voluntario")
    @Transactional
    public String eliminarVoluntario(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        Optional<Voluntario> voluntario = repositorioVoluntario.findById(id);
        if (voluntario.isPresent()) {
            repositorioVoluntario.delete(voluntario.get());
            String message = "Se ha eliminado correctamente el usuario con el id: " + id;
            redirectAttributes.addFlashAttribute("eliminado", message);
        } else {
            String message = "No se encontró el usuario con el id: " + id;
            redirectAttributes.addFlashAttribute("error", message);
        }
        return "redirect:/voluntarios";
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
