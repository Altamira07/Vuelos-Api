package com.luisito.ws.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.luisito.ws.rest.vo.Usuario;


@Path("/usuario")
public class UsuarioServices {
	@POST
	@Path("/login")
	@Consumes({MediaType.APPLICATION_JSON})
	public Response login(Usuario usuario)
	{
		if (usuario.validar())
			return Response.ok(usuario,MediaType.APPLICATION_JSON).build();
		return Response.ok("{\"mensaje\": \"Usuario y/o contraseña invalido \"}").build();
	}
}
