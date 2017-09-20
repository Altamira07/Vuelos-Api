package com.luisito.ws.rest.vo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Cliente 
{
	private int id;
	private String nombre;
	private String email;
	private String foto;
	private int idUsuario;
	
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public boolean buscar()
	{
		
		Conexion conexion = new Conexion();
		boolean obtuvo = false;
		if(conexion.getConexion() != null)
		{
			try {
				String query = "select * from cliente where id ="+id+";";
				ResultSet rs = conexion.getConexion().createStatement().executeQuery(query);
				Usuario usuario = new Usuario();
				
				if(rs.next())
				{
					id = rs.getInt("id");
					nombre = rs.getString("nombre");
					email = rs.getString("email");
					idUsuario = rs.getInt("idusuario");
					foto = rs.getString("foto");
					usuario.setId(idUsuario);
					usuario.verUsuario();
					this.usuario = usuario;
					obtuvo = true;
				}
				conexion.getConexion().close();
			}catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return obtuvo;
	}
	
	public List<Cliente> todos()
	{
		List<Cliente> clientes = null;
		Conexion conexion = new Conexion();
		if(conexion.getConexion() != null)
		{
			clientes = new ArrayList<Cliente>();
			try {
				String query = "select  * from cliente;";
				ResultSet rs = conexion.getConexion().createStatement().executeQuery(query);
				while(rs.next())
				{
					Usuario usuario = new Usuario();
					usuario.setId(rs.getInt("idusuario"));
					usuario.verUsuario();
					Cliente cliente = new Cliente(
							rs.getInt("id"), 
							rs.getString("nombre"), 
							rs.getString("email"), 
							rs.getString("foto"), 
							rs.getInt("idUsuario")
						);
					cliente.setUsuario(usuario);
					clientes.add(cliente);
				}
				conexion.getConexion().close();
			}catch (Exception e) {
				e.getMessage();
			}
		}
		
		return clientes;
	}
	
	public Cliente(int id, String nombre, String email, String foto, int idUsuario, Usuario usuario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.foto = foto;
		this.idUsuario = idUsuario;
		this.usuario = usuario;
	}
	public boolean guardar()
	{
		Conexion conexion = new Conexion();
		if(conexion.getConexion() != null)
		{
			try {
				String query = "insert into cliente(nombre,email,foto) values ('"+nombre+"','"+email+"','"+foto+"');";
				conexion.getConexion().createStatement().execute(query);
				conexion.getConexion().close();
			}catch(Exception ex) {
				System.err.println(ex.getMessage());
			}
			return true;
		}
		
		return false;
	}
	
	public boolean eliminar()
	{
		Conexion conexion = new Conexion();
		if(conexion.getConexion() != null)
		{
			try {
				String query = "delete from cliente where id = "+id+";";
				conexion.getConexion().createStatement().execute(query);
				conexion.getConexion().close();
			}catch(Exception ex) {
				System.err.println(ex.getMessage());
			}
			return true;
		}
		return false;
	}
	public Cliente() {
		
	}
	public Cliente(int id, String nombre, String email, String foto, int idUsuario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.foto = foto;
		this.idUsuario = idUsuario;
	}
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", email=" + email + ", foto=" + foto + ", idUsuario="
				+ idUsuario + "]";
	}
	
	
}
