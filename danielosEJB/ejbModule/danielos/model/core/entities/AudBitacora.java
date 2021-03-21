package danielos.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the aud_bitacora database table.
 * 
 */
@Entity
@Table(name="aud_bitacora")
@NamedQuery(name="AudBitacora.findAll", query="SELECT a FROM AudBitacora a")
public class AudBitacora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_aud_bitacora")
	private Integer idAudBitacora;

	@Column(name="descripcion_evento")
	private String descripcionEvento;

	@Column(name="direccion_ip")
	private String direccionIp;

	@Column(name="fecha_evento")
	private Timestamp fechaEvento;

	@Column(name="id_usuario")
	private String idUsuario;

	@Column(name="nombre_clase")
	private String nombreClase;

	@Column(name="nombre_metodo")
	private String nombreMetodo;

	public AudBitacora() {
	}

	public Integer getIdAudBitacora() {
		return this.idAudBitacora;
	}

	public void setIdAudBitacora(Integer idAudBitacora) {
		this.idAudBitacora = idAudBitacora;
	}

	public String getDescripcionEvento() {
		return this.descripcionEvento;
	}

	public void setDescripcionEvento(String descripcionEvento) {
		this.descripcionEvento = descripcionEvento;
	}

	public String getDireccionIp() {
		return this.direccionIp;
	}

	public void setDireccionIp(String direccionIp) {
		this.direccionIp = direccionIp;
	}

	public Timestamp getFechaEvento() {
		return this.fechaEvento;
	}

	public void setFechaEvento(Timestamp fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	public String getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreClase() {
		return this.nombreClase;
	}

	public void setNombreClase(String nombreClase) {
		this.nombreClase = nombreClase;
	}

	public String getNombreMetodo() {
		return this.nombreMetodo;
	}

	public void setNombreMetodo(String nombreMetodo) {
		this.nombreMetodo = nombreMetodo;
	}

}