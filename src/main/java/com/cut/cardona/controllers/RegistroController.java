package com.cut.cardona.controllers;

import com.cut.cardona.modelo.Usuarios.DtoRegistroUsuario;
import com.cut.cardona.modelo.Usuarios.RepositorioUsuario;
import com.cut.cardona.modelo.Usuarios.Roles.RepositorioRoles;
import com.cut.cardona.modelo.Usuarios.Usuario;
import com.cut.cardona.modelo.perros.DatosPerro;
import com.cut.cardona.modelo.perros.Perro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistroController {
    @Autowired
    RepositorioUsuario repositorioUsuario;
    @Autowired
    RepositorioRoles rerpositorioRoles;
    @Autowired
    PasswordEncoder passwordEncoder;




    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        // Añadir un nuevo objeto de usuario al modelo
        model.addAttribute("registroUsuario", new DtoRegistroUsuario("","","","","",false));
        return "registro"; // Devuelve la plantilla Thymeleaf
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute("registroUsuario") DtoRegistroUsuario registroUsuario,
                                   BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        // Validar campos (como si las contraseñas coinciden, los emails son iguales, etc.)
        validarDTO(registroUsuario, result);
        if (result.hasErrors()) {
            return "registro";
        }

        Usuario usuario = new Usuario(registroUsuario, passwordEncoder);
        try {
            usuario.setRoles(rerpositorioRoles.findByNombre("ROLE_USER"));
            repositorioUsuario.save(usuario);
            String successfulMessage = "Registro exitoso.\nAhora puedes iniciar sesión con el usuario: " + usuario.getUsername();
            redirectAttributes.addFlashAttribute("successfulRegistroUsuario", successfulMessage);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = "No se ha podido hacer el registro, por favor intente de nuevo";
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/registro";
        } catch (Exception e) {
            String errorMessage = "A ocurrido un error, por favor intente de nuevo";
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/registro";
        }
        // Guardar el usuario en la base de datos (lógica de persistencia aquí)

        // Redirigir a la página de éxito o de login
        redirectAttributes.addFlashAttribute("successfulRegistroUsuario", "Registro exitoso. Ahora puedes iniciar sesión.");
        return "redirect:/login";
    }

    private static void validarDTO(DtoRegistroUsuario registroUsuario, BindingResult result) {
        if (!registroUsuario.userName().matches("^[a-zA-Z0-9]*$")) {
            result.rejectValue("userName", "error.userName", "El nombre de usuario solo puede contener letras, números y no puede estar vacío");
        }
        if (!registroUsuario.email().equals(registroUsuario.confirmEmail())) {
            result.rejectValue("confirmEmail", "error.confirmEmail", "Los correos electrónicos no coinciden");
        }
        if (!registroUsuario.password().equals(registroUsuario.confirmPassword())) {
            result.rejectValue("confirmPassword", "error.confirmPassword", "Las contraseñas no coinciden");
        }
    }
}

