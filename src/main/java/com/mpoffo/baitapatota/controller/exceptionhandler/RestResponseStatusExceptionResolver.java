package com.mpoffo.baitapatota.controller.exceptionhandler;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class RestResponseStatusExceptionResolver extends AbstractHandlerExceptionResolver {
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if(ex.getClass().equals(EntityNotFoundException.class)) {
            return handleNotFoundError(ex);
        }
        return handleUnexpectedError(ex);
    }

    private ModelAndView handleUnexpectedError(Exception ex) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setView(new View() {
            @Override
            public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Ocorreu um erro inesperado: "+ ex.getMessage());
                logger.error("Ocorreu um erro inesperado", ex);
            }
        });
        return modelAndView;
    }

    private ModelAndView handleNotFoundError(Exception ex) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setView(new View() {
            @Override
            public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                logger.error(ex.getMessage(), ex);
                response.getWriter().write(ex.getMessage());
            }
        });
        return modelAndView;
    }
}
