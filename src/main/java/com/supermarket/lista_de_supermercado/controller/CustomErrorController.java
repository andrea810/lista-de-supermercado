package com.supermarket.lista_de_supermercado.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(ERROR_PATH)
    public String handleError(HttpServletRequest request, Model model) {
        // Get error attributes
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        Object exception = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        Object requestUri = request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
        
        // Default values
        Integer statusCode = 500;
        String errorTitle = "Erro interno do servidor";
        String errorMessage = "Ocorreu um erro interno. Tente novamente mais tarde.";
        
        if (status != null) {
            statusCode = Integer.valueOf(status.toString());
            
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                errorTitle = "Página não encontrada";
                errorMessage = "A página que você está procurando não existe.";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                errorTitle = "Acesso negado";
                errorMessage = "Você não tem permissão para acessar este recurso.";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                errorTitle = "Erro interno do servidor";
                errorMessage = "Ocorreu um erro interno. Tente novamente mais tarde.";
            } else if (statusCode == HttpStatus.BAD_REQUEST.value()) {
                errorTitle = "Requisição inválida";
                errorMessage = "A requisição enviada é inválida.";
            } else {
                errorTitle = "Erro";
                errorMessage = message != null ? message.toString() : "Ocorreu um erro inesperado.";
            }
        }
        
        // Add attributes to model
        model.addAttribute("status", statusCode);
        model.addAttribute("error", errorTitle);
        model.addAttribute("message", errorMessage);
        model.addAttribute("path", requestUri);
        
        // Enhanced logging for debugging
        System.err.println("=== ERROR CONTROLLER DEBUG ===");
        System.err.println("Status Code: " + statusCode);
        System.err.println("Request URI: " + requestUri);
        System.err.println("Error Message: " + message);
        if (exception != null) {
            System.err.println("Exception: " + exception.toString());
            if (exception instanceof Exception) {
                ((Exception) exception).printStackTrace();
            }
        }
        System.err.println("=== END ERROR DEBUG ===");
        
        return "error";
    }

    public String getErrorPath() {
        return ERROR_PATH;
    }
}