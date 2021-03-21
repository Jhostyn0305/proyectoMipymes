package danielos.controller.proveedor;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import danielos.controller.JSFUtil;
import danielos.model.core.entities.Proveedore;
import danielos.model.proveedor.managers.ManagerProveedor;

@Named
@SessionScoped
public class BeanProveedores implements Serializable {
	@EJB
	private ManagerProveedor managerProveedor;

	private List<Proveedore> listaProveedores;
	private Proveedore nuevoProveedor;
	private Proveedore edicionProveedor;
	private String rucCedula;
	private String celular;
	private String direccion;
	private String email;
	private String razonSocial;

	public BeanProveedores() {

	}

	public String actionMenuProveedor() {
		listaProveedores = managerProveedor.findAllProveedor();
		return "proveedor";
	}

	public String actionMenuNuevoProveedor() {
		nuevoProveedor = new Proveedore();
		return "proveedor_nuevo";
	}

	public void actionListenerInsertarNuevoProveedor() {
		try {
			managerProveedor.insertarProveedor(rucCedula, celular, direccion, email, razonSocial);
			listaProveedores = managerProveedor.findAllProveedor();
			JSFUtil.crearMensajeINFO("Proveedor insertado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public String actionSeleccionarEdicionProveedor(Proveedore proveedor) {
		edicionProveedor = proveedor;
		return "proveedor_edicion";
	}

	public void actionListenerActualizarEdicionProveedor() {
		try {
			managerProveedor.actualizarProveedor(edicionProveedor);
			listaProveedores = managerProveedor.findAllProveedor();
			JSFUtil.crearMensajeINFO("Proveedor actualizado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerEliminarProveedor(String rucCedula) {
		try {
			managerProveedor.eliminarProveedor(rucCedula);
			listaProveedores = managerProveedor.findAllProveedor();
			JSFUtil.crearMensajeINFO("Proveedor eliminado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Proveedore> getListaProveedores() {
		return listaProveedores;
	}

	public void setListaProveedores(List<Proveedore> listaProveedores) {
		this.listaProveedores = listaProveedores;
	}

	public Proveedore getNuevoProveedor() {
		return nuevoProveedor;
	}

	public void setNuevoProveedor(Proveedore nuevoProveedor) {
		this.nuevoProveedor = nuevoProveedor;
	}

	public Proveedore getEdicionProveedor() {
		return edicionProveedor;
	}

	public void setEdicionProveedor(Proveedore edicionProveedor) {
		this.edicionProveedor = edicionProveedor;
	}

	public String getRucCedula() {
		return rucCedula;
	}

	public void setRucCedula(String rucCedula) {
		this.rucCedula = rucCedula;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

}
