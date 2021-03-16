package danielos.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the seg_usuario database table.
 * 
 */
@Entity
@Table(name="seg_usuario")
@NamedQuery(name="SegUsuario.findAll", query="SELECT s FROM SegUsuario s")
public class SegUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_seg_usuario")
	private Integer idSegUsuario;

	private Boolean activo;

	private String apellidos;

	private String clave;

	private String codigo;

	private String correo;

	private String nombres;

	//bi-directional many-to-one association to SegAsignacion
	@OneToMany(mappedBy="segUsuario")
	private List<SegAsignacion> segAsignacions;

	//bi-directional many-to-one association to ThmTrabajador
	@OneToMany(mappedBy="segUsuario")
	private List<ThmTrabajador> thmTrabajadors;

	public SegUsuario() {
	}

	public Integer getIdSegUsuario() {
		return this.idSegUsuario;
	}

	public void setIdSegUsuario(Integer idSegUsuario) {
		this.idSegUsuario = idSegUsuario;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public List<SegAsignacion> getSegAsignacions() {
		return this.segAsignacions;
	}

	public void setSegAsignacions(List<SegAsignacion> segAsignacions) {
		this.segAsignacions = segAsignacions;
	}

	public SegAsignacion addSegAsignacion(SegAsignacion segAsignacion) {
		getSegAsignacions().add(segAsignacion);
		segAsignacion.setSegUsuario(this);

		return segAsignacion;
	}

	public SegAsignacion removeSegAsignacion(SegAsignacion segAsignacion) {
		getSegAsignacions().remove(segAsignacion);
		segAsignacion.setSegUsuario(null);

		return segAsignacion;
	}

	public List<ThmTrabajador> getThmTrabajadors() {
		return this.thmTrabajadors;
	}

	public void setThmTrabajadors(List<ThmTrabajador> thmTrabajadors) {
		this.thmTrabajadors = thmTrabajadors;
	}

	public ThmTrabajador addThmTrabajador(ThmTrabajador thmTrabajador) {
		getThmTrabajadors().add(thmTrabajador);
		thmTrabajador.setSegUsuario(this);

		return thmTrabajador;
	}

	public ThmTrabajador removeThmTrabajador(ThmTrabajador thmTrabajador) {
		getThmTrabajadors().remove(thmTrabajador);
		thmTrabajador.setSegUsuario(null);

		return thmTrabajador;
	}

}