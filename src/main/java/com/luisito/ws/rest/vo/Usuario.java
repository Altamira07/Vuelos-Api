package com.luisito.ws.rest.vo;

import java.sql.ResultSet;

import com.luisito.ws.util.Hash;

public class Usuario {

	private int id;

	private String usuario;
	private String password;
	private String token;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean validar() {
		Conexion conexion = new Conexion();
		boolean valido = false;
		if (conexion.getConexion() != null) {
			try {
				String query = "select * from usuario where usuario  = '" + usuario + "' and password = '" + password
						+ "';";
				ResultSet rs = conexion.getConexion().createStatement().executeQuery(query);
				if (rs.next()) {
					this.token = Hash.hash.encriptar(usuario, password);
					this.id = rs.getInt("id");
					agregarBitacora();
					valido = true;
				}
				conexion.getConexion().close();
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}

		return valido;
	}

	public boolean verUsuario() {
		Conexion conexion = new Conexion();
		boolean estado = false;
		if (conexion.getConexion() != null) {
			try {
				String query = "select * from usuario where id= " + id + ";";
				ResultSet rs = conexion.getConexion().createStatement().executeQuery(query);
				if (rs.next()) {
					usuario = rs.getString("usuario");
					password = rs.getString("password");
					estado = true;

				}
				conexion.getConexion().close();
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
		return estado;
	}

	private void agregarBitacora() {
		Conexion conexion = new Conexion();
		if (conexion.getConexion() != null) {
			try {
				String query = "insert into bitacora( token ) values ( '" + token + "');";
				ResultSet rs = conexion.getConexion().createStatement().executeQuery(query);
				if (rs.next())
					System.out.println("Agregado a bitacora");
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	public boolean sesionToken() {
		Conexion conexion = new Conexion();
		boolean validado = false;
		try {
			String query = "select * from  bitacora where token =  '" + token + "';";
			ResultSet rs = conexion.getConexion().createStatement().executeQuery(query);
			if (rs.next()) {
				System.out.println(rs.getString("token"));
				validado = true;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return validado;
	}

}
