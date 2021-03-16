package danielos.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the proveedores database table.
 * 
 */
@Entity
@Table(name="proveedores")
@NamedQuery(name="Proveedore.findAll", query="SELECT p FROM Proveedore p")
public class Proveedore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ruc_cedula")
	private String rucCedula;

	private String celular;

	private String direccion;

	private String email;

	@Column(name="razon_social")
	private String razonSocial;

	//bi-directional many-to-one association to ProvPedido
	@OneToMany(mappedBy="proveedore")
	private List<ProvPedido> provPedidos;

	public Proveedore() {
	}

	public String getRucCedula() {
		return this.rucCedula;
	}

	public void setRucCedula(String rucCedula) {
		this.rucCedula = rucCedula;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public List<ProvPedido> getProvPedidos() {
		return this.provPedidos;
	}

	public void setProvPedidos(List<ProvPedido> provPedidos) {
		this.provPedidos = provPedidos;
	}

	public ProvPedido addProvPedido(ProvPedido provPedido) {
		getProvPedidos().add(provPedido);
		provPedido.setProveedore(this);

		return provPedido;
	}

	public ProvPedido removeProvPedido(ProvPedido provPedido) {
		getProvPedidos().remove(provPedido);
		provPedido.setProveedore(null);

		return provPedido;
	}

}