package danielos.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the prod_bodega database table.
 * 
 */
@Entity
@Table(name="prod_bodega")
@NamedQuery(name="ProdBodega.findAll", query="SELECT p FROM ProdBodega p")
public class ProdBodega implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_bodega")
	private Integer idBodega;

	private Long cantidad;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="codigo")
	private Producto producto;

	public ProdBodega() {
	}

	public Integer getIdBodega() {
		return this.idBodega;
	}

	public void setIdBodega(Integer idBodega) {
		this.idBodega = idBodega;
	}

	public Long getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}