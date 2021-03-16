package danielos.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the prov_pedido database table.
 * 
 */
@Entity
@Table(name="prov_pedido")
@NamedQuery(name="ProvPedido.findAll", query="SELECT p FROM ProvPedido p")
public class ProvPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pedido")
	private Integer idPedido;

	private Long cantidad;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_pedido")
	private Date fechaPedido;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="codigo_producto")
	private Producto producto;

	//bi-directional many-to-one association to Proveedore
	@ManyToOne
	@JoinColumn(name="ruc_cedula")
	private Proveedore proveedore;

	public ProvPedido() {
	}

	public Integer getIdPedido() {
		return this.idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Long getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaPedido() {
		return this.fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Proveedore getProveedore() {
		return this.proveedore;
	}

	public void setProveedore(Proveedore proveedore) {
		this.proveedore = proveedore;
	}

}