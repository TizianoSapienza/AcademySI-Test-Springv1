package com.academysi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.academysi.dto.CategoriaDto;
import com.academysi.service.CategoriaService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@RestController
@Path("/categorie")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	
	@GET
	@Path("")
	public Response getCategorie() {
		try {
			List<CategoriaDto> categorieDto = categoriaService.getCategorie();
			
			return Response.status(Response.Status.OK).entity(categorieDto).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	
	}
	
}
