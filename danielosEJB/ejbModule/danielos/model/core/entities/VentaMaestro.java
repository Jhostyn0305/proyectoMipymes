package danielos.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the venta_maestro database table.
 * 
 */
@Entity
@Table(name="venta_maestro")
@NamedQuery(name="VentaMaestro.findAll", query="SELECT v FROM VentaMaestro v")
public class VentaMaestro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_venta")
	private Integer idVenta;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_venta")
	private Date fechaVenta;

	private BigDecimal iva;

	private BigDecimal subtotal;

	private BigDecimal total;

	//bi-directional many-to-one association to VentaDetalle
	@OneToMany(mappedBy="ventaMaestro")
	private List<VentaDetalle> ventaDetalles;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="cedula")
	private Cliente cliente;

	public VentaMaestro() {
	}

	public Integer getIdVenta() {
		return this.idVenta;
	}

	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
	}

	public Date getFechaVenta() {
		return this.fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public BigDecimal getIva() {
		return this.iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public BigDecimal getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<VentaDetalle> getVentaDetalles() {
		return this.ventaDetalles;
	}

	public void setVentaDetalles(List<VentaDetalle> ventaDetalles) {
		this.ventaDetalles = ventaDetalles;
	}

	public VentaDetalle addVentaDetalle(VentaDetalle ventaDetalle) {
		getVentaDetalles().add(ventaDetalle);
		ventaDetalle.setVentaMaestro(this);

		return ventaDetalle;
	}

	public VentaDetalle removeVentaDetalle(VentaDetalle ventaDetalle) {
		getVentaDetalles().remove(ventaDetalle);
		ventaDetalle.setVentaMaestro(null);

		return ventaDetalle;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}