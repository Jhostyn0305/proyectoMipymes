package danielos.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the prod_clasificacion database table.
 * 
 */
@Entity
@Table(name="prod_clasificacion")
@NamedQuery(name="ProdClasificacion.findAll", query="SELECT p FROM ProdClasificacion p")
public class ProdClasificacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_clasificacion")
	private Integer idClasificacion;

	@Column(name="tipo_clasificacion")
	private String tipoClasificacion;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="prodClasificacion")
	private List<Producto> productos;

	public ProdClasificacion() {
	}

	public Integer getIdClasificacion() {
		return this.idClasificacion;
	}

	public void setIdClasificacion(Integer idClasificacion) {
		this.idClasificacion = idClasificacion;
	}

	public String getTipoClasificacion() {
		return this.tipoClasificacion;
	}

	public void setTipoClasificacion(String tipoClasificacion) {
		this.tipoClasificacion = tipoClasificacion;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setProdClasificacion(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setProdClasificacion(null);

		return producto;
	}

}