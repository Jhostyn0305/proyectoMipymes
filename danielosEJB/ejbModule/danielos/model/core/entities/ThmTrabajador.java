package danielos.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the thm_trabajador database table.
 * 
 */
@Entity
@Table(name="thm_trabajador")
@NamedQuery(name="ThmTrabajador.findAll", query="SELECT t FROM ThmTrabajador t")
public class ThmTrabajador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_thm_empleado")
	private Integer idThmEmpleado;

	@Column(name="horas_trabajo")
	private Integer horasTrabajo;

	//bi-directional many-to-one association to SegUsuario
	@ManyToOne
	@JoinColumn(name="id_seg_usuario")
	private SegUsuario segUsuario;

	//bi-directional many-to-one association to ThmCargo
	@ManyToOne
	@JoinColumn(name="id_thm_cargo")
	private ThmCargo thmCargo;

	public ThmTrabajador() {
	}

	public Integer getIdThmEmpleado() {
		return this.idThmEmpleado;
	}

	public void setIdThmEmpleado(Integer idThmEmpleado) {
		this.idThmEmpleado = idThmEmpleado;
	}

	public Integer getHorasTrabajo() {
		return this.horasTrabajo;
	}

	public void setHorasTrabajo(Integer horasTrabajo) {
		this.horasTrabajo = horasTrabajo;
	}

	public SegUsuario getSegUsuario() {
		return this.segUsuario;
	}

	public void setSegUsuario(SegUsuario segUsuario) {
		this.segUsuario = segUsuario;
	}

	public ThmCargo getThmCargo() {
		return this.thmCargo;
	}

	public void setThmCargo(ThmCargo thmCargo) {
		this.thmCargo = thmCargo;
	}

}