package com.cut.cardona.errores;

import com.cut.cardona.modelo.voluntarios.DatosVoluntario;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolationException(DataIntegrityViolationException ex, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        // Verifica si la excepción ocurrió en una solicitud POST a /voluntarios
        if ("POST".equals(request.getMethod()) && "/voluntarios".equals(request.getRequestURI())) {
            String errorMessage = "El correo electrónico o teléfono ya están registrados";
            redirectAttributes.addFlashAttribute("error", errorMessage);
            System.out.println(errorMessage);
            return "redirect:/voluntarios";
        }
        // Si no, deja que la excepción se maneje de forma predeterminada
        throw ex;
    }

}


