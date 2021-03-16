package danielos.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codigo_producto")
	private Integer codigoProducto;

	private String descripcion;

	private String imagen;

	private String nombre;

	private BigDecimal precio;

	//bi-directional many-to-one association to ProdBodega
	@OneToMany(mappedBy="producto")
	private List<ProdBodega> prodBodegas;

	//bi-directional many-to-one association to ProdClasificacion
	@ManyToOne
	@JoinColumn(name="id_clasificacion")
	private ProdClasificacion prodClasificacion;

	//bi-directional many-to-one association to ProvPedido
	@OneToMany(mappedBy="producto")
	private List<ProvPedido> provPedidos;

	//bi-directional many-to-one association to VentaDetalle
	@OneToMany(mappedBy="producto")
	private List<VentaDetalle> ventaDetalles;

	public Producto() {
	}

	public Integer getCodigoProducto() {
		return this.codigoProducto;
	}

	public void setCodigoProducto(Integer codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public List<ProdBodega> getProdBodegas() {
		return this.prodBodegas;
	}

	public void setProdBodegas(List<ProdBodega> prodBodegas) {
		this.prodBodegas = prodBodegas;
	}

	public ProdBodega addProdBodega(ProdBodega prodBodega) {
		getProdBodegas().add(prodBodega);
		prodBodega.setProducto(this);

		return prodBodega;
	}

	public ProdBodega removeProdBodega(ProdBodega prodBodega) {
		getProdBodegas().remove(prodBodega);
		prodBodega.setProducto(null);

		return prodBodega;
	}

	public ProdClasificacion getProdClasificacion() {
		return this.prodClasificacion;
	}

	public void setProdClasificacion(ProdClasificacion prodClasificacion) {
		this.prodClasificacion = prodClasificacion;
	}

	public List<ProvPedido> getProvPedidos() {
		return this.provPedidos;
	}

	public void setProvPedidos(List<ProvPedido> provPedidos) {
		this.provPedidos = provPedidos;
	}

	public ProvPedido addProvPedido(ProvPedido provPedido) {
		getProvPedidos().add(provPedido);
		provPedido.setProducto(this);

		return provPedido;
	}

	public ProvPedido removeProvPedido(ProvPedido provPedido) {
		getProvPedidos().remove(provPedido);
		provPedido.setProducto(null);

		return provPedido;
	}

	public List<VentaDetalle> getVentaDetalles() {
		return this.ventaDetalles;
	}

	public void setVentaDetalles(List<VentaDetalle> ventaDetalles) {
		this.ventaDetalles = ventaDetalles;
	}

	public VentaDetalle addVentaDetalle(VentaDetalle ventaDetalle) {
		getVentaDetalles().add(ventaDetalle);
		ventaDetalle.setProducto(this);

		return ventaDetalle;
	}

	public VentaDetalle removeVentaDetalle(VentaDetalle ventaDetalle) {
		getVentaDetalles().remove(ventaDetalle);
		ventaDetalle.setProducto(null);

		return ventaDetalle;
	}

}