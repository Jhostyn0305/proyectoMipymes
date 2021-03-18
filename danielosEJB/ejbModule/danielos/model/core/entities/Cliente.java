package danielos.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String cedula;

	private String apellido;

	private String celular;

	private String email;

	private String nombre;

	//bi-directional many-to-one association to VentaMaestro
	@OneToMany(mappedBy="cliente")
	private List<VentaMaestro> ventaMaestros;

	public Cliente() {
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<VentaMaestro> getVentaMaestros() {
		return this.ventaMaestros;
	}

	public void setVentaMaestros(List<VentaMaestro> ventaMaestros) {
		this.ventaMaestros = ventaMaestros;
	}

	public VentaMaestro addVentaMaestro(VentaMaestro ventaMaestro) {
		getVentaMaestros().add(ventaMaestro);
		ventaMaestro.setCliente(this);

		return ventaMaestro;
	}

	public VentaMaestro removeVentaMaestro(VentaMaestro ventaMaestro) {
		getVentaMaestros().remove(ventaMaestro);
		ventaMaestro.setCliente(null);

		return ventaMaestro;
	}

}