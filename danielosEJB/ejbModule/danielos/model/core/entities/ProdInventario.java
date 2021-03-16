package danielos.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the prod_inventario database table.
 * 
 */
@Entity
@Table(name="prod_inventario")
@NamedQuery(name="ProdInventario.findAll", query="SELECT p FROM ProdInventario p")
public class ProdInventario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_inventario")
	private Integer idInventario;

	private String evento;

	@Column(name="fecha_evento")
	private Timestamp fechaEvento;

	private String usuario;

	public ProdInventario() {
	}

	public Integer getIdInventario() {
		return this.idInventario;
	}

	public void setIdInventario(Integer idInventario) {
		this.idInventario = idInventario;
	}

	public String getEvento() {
		return this.evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public Timestamp getFechaEvento() {
		return this.fechaEvento;
	}

	public void setFechaEvento(Timestamp fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}