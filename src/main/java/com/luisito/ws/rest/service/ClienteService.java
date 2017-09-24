package com.luisito.ws.rest.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.luisito.ws.rest.vo.Cliente;

@Path("/cliente")
public class ClienteService {
	@GET
	@Path("/{id}")
	public Response obtenerCliente(@PathParam("id") int id) {
		Cliente cliente = new Cliente();
		cliente.setId(id);
		if (cliente.buscar()) {
			return Response.ok(cliente, MediaType.APPLICATION_JSON).build();
		}
		return Response.ok("{\"mensaje\":\"No se encontro el cliente\"}").build();
	}

	@GET
	public Response obtenerClientes() {
		Cliente cliente = new Cliente();
		List<Cliente> clientes = cliente.todos();
		if (clientes != null)
			return Response.ok(clientes, MediaType.APPLICATION_JSON).build();
		return Response.status(Response.Status.NOT_FOUND).build();

	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response guardarCliente(Cliente cliente) {
		if (cliente.guardar())
			return Response.ok("{\"guardado\":true}", MediaType.APPLICATION_JSON).build();
		return Response.ok("{\"guardado\":false}", MediaType.APPLICATION_JSON).build();
	}

	@DELETE
	@Path("{id}")
	public Response eliminar(@PathParam("id") int id) {
		Cliente cliente = new Cliente();
		cliente.setId(id);
		if (cliente.eliminar())
			return Response.ok("{\"eliminado\":true}", MediaType.APPLICATION_JSON).build();
		return Response.ok("{\"eliminado\":false}", MediaType.APPLICATION_JSON).build();
	}

}
