package com.academysi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.academysi.dto.CorsoRegistrazioneDto;
import com.academysi.dto.CorsoUpdateDto;
import com.academysi.jwt.JWTTokenNeeded;
import com.academysi.jwt.Secured;
import com.academysi.model.Categoria;
import com.academysi.model.Corso;
import com.academysi.service.CategoriaService;
import com.academysi.service.CorsoService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Secured(role = "Admin")
@JWTTokenNeeded
@RestController
@Path("/corsi")
public class CorsoController {

    @Autowired
    private CorsoService corsoService;

    @Autowired
    private CategoriaService categoriaService;

    @POST
    @Path("/registrazione")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registraCorso(CorsoRegistrazioneDto corsoregistrazioneDto) {
        Categoria categoria = categoriaService.getCategoriaById(corsoregistrazioneDto.getIdCategoria());
        if (categoria.getId() == 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Corso corso = new Corso();
        corso.setNomeCorso(corsoregistrazioneDto.getNomeCorso());
        corso.setDescrizioneBreve(corsoregistrazioneDto.getDescrizioneBreve());
        corso.setDescrizioneCompleta(corsoregistrazioneDto.getDescrizioneCompleta());
        corso.setDurata(corsoregistrazioneDto.getDurata());
        corso.setCategoria(categoria);
        
        corsoService.registerCorso(corso);
        
        return Response.ok().build();
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCorsoById(@PathParam("id") int id) {
        Corso corso = corsoService.getCorsoById(id);
        if (corso.getId() == 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(corso).build();
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response aggiornaCorso(@PathParam("id") int id, CorsoUpdateDto corsoDto) {
        Corso corso = corsoService.getCorsoById(id);
        if (corso.getId() == 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Categoria categoria = categoriaService.getCategoriaById(corsoDto.getIdCategoria());
        if (categoria.getId() == 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        corso.setNomeCorso(corsoDto.getNomeCorso());
        corso.setDescrizioneBreve(corsoDto.getDescrizioneBreve());
        corso.setDescrizioneCompleta(corsoDto.getDescrizioneCompleta());
        corso.setDurata(corsoDto.getDurata());
        corso.setCategoria(categoria);

        corsoService.updateCorsoData(corso);
        
        return Response.ok().build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response eliminaCorso(@PathParam("id") int id) {
        // Verifica se il corso esiste
        Corso corso = corsoService.getCorsoById(id);
        if (corso.getId() == 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        corsoService.deleteCorsoById(id);
        
        return Response.ok().build();
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCorsi() {
        List<Corso> corsi = corsoService.getCorsi();
        return Response.ok(corsi).build();
    }
    
    @DELETE
    @Path("/categoria/{id}")
    public void deleteCorsoByCategory(@PathParam("id") int idCa) {
        corsoService.deleteCorsoByCategory(idCa);
    }
}