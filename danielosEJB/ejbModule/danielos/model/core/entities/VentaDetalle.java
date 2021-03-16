package danielos.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the venta_detalle database table.
 * 
 */
@Entity
@Table(name="venta_detalle")
@NamedQuery(name="VentaDetalle.findAll", query="SELECT v FROM VentaDetalle v")
public class VentaDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_detventa")
	private Integer idDetventa;

	private Long cantidad;

	private Long subtotal;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="codigo_producto")
	private Producto producto;

	//bi-directional many-to-one association to VentaMaestro
	@ManyToOne
	@JoinColumn(name="id_venta")
	private VentaMaestro ventaMaestro;

	public VentaDetalle() {
	}

	public Integer getIdDetventa() {
		return this.idDetventa;
	}

	public void setIdDetventa(Integer idDetventa) {
		this.idDetventa = idDetventa;
	}

	public Long getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Long getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(Long subtotal) {
		this.subtotal = subtotal;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public VentaMaestro getVentaMaestro() {
		return this.ventaMaestro;
	}

	public void setVentaMaestro(VentaMaestro ventaMaestro) {
		this.ventaMaestro = ventaMaestro;
	}

}