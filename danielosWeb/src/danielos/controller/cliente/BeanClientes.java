package danielos.controller.cliente;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import danielos.controller.JSFUtil;
import danielos.model.cliente.managers.ManagerCliente;
import danielos.model.core.entities.Cliente;

@Named
@SessionScoped
public class BeanClientes implements Serializable {
	@EJB
	private ManagerCliente managerCliente;

	private List<Cliente> listaClientes;
	private Cliente nuevoCliente;
	private Cliente edicionCliente;
	private String cedula;
	private String apellido;
	private String nombre;
	private String email;
	private String celular;

	public BeanClientes() {

	}

	public String actionMenuClientes() {
		listaClientes = managerCliente.findAllClientes();
		return "cliente";
	}

	public String actionMenuNuevoCliente() {
		nuevoCliente = new Cliente();
		return "cliente_nuevo";
	}

	public void actionListenerInsertarNuevoCliente() {
		try {
			managerCliente.insertarCliente(cedula, apellido, nombre, celular, email);
			listaClientes = managerCliente.findAllClientes();
			JSFUtil.crearMensajeINFO("Cliente insertado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public String actionSeleccionarEdicionCliente(Cliente cliente) {
		edicionCliente = cliente;
		return "cliente_edicion";
	}

	public void actionListenerActualizarEdicionCliente() {
		try {
			managerCliente.actualizarCliente(edicionCliente);
			listaClientes = managerCliente.findAllClientes();
			JSFUtil.crearMensajeINFO("Cliente actualizado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerEliminarCliente(String cedula) {
		try {
			managerCliente.eliminarCliente(cedula);
			listaClientes = managerCliente.findAllClientes();
			JSFUtil.crearMensajeINFO("Cliente eliminado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public Cliente getNuevoCliente() {
		return nuevoCliente;
	}

	public void setNuevoCliente(Cliente nuevoCliente) {
		this.nuevoCliente = nuevoCliente;
	}

	public Cliente getEdicionCliente() {
		return edicionCliente;
	}

	public void setEdicionCliente(Cliente edicionCliente) {
		this.edicionCliente = edicionCliente;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
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

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

}
