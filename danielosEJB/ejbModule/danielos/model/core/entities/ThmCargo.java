package danielos.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the thm_cargo database table.
 * 
 */
@Entity
@Table(name="thm_cargo")
@NamedQuery(name="ThmCargo.findAll", query="SELECT t FROM ThmCargo t")
public class ThmCargo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_thm_cargo")
	private Integer idThmCargo;

	@Column(name="nombre_cargo")
	private String nombreCargo;

	@Column(name="remuneracion_mensual")
	private BigDecimal remuneracionMensual;

	//bi-directional many-to-one association to ThmTrabajador
	@OneToMany(mappedBy="thmCargo")
	private List<ThmTrabajador> thmTrabajadors;

	public ThmCargo() {
	}

	public Integer getIdThmCargo() {
		return this.idThmCargo;
	}

	public void setIdThmCargo(Integer idThmCargo) {
		this.idThmCargo = idThmCargo;
	}

	public String getNombreCargo() {
		return this.nombreCargo;
	}

	public void setNombreCargo(String nombreCargo) {
		this.nombreCargo = nombreCargo;
	}

	public BigDecimal getRemuneracionMensual() {
		return this.remuneracionMensual;
	}

	public void setRemuneracionMensual(BigDecimal remuneracionMensual) {
		this.remuneracionMensual = remuneracionMensual;
	}

	public List<ThmTrabajador> getThmTrabajadors() {
		return this.thmTrabajadors;
	}

	public void setThmTrabajadors(List<ThmTrabajador> thmTrabajadors) {
		this.thmTrabajadors = thmTrabajadors;
	}

	public ThmTrabajador addThmTrabajador(ThmTrabajador thmTrabajador) {
		getThmTrabajadors().add(thmTrabajador);
		thmTrabajador.setThmCargo(this);

		return thmTrabajador;
	}

	public ThmTrabajador removeThmTrabajador(ThmTrabajador thmTrabajador) {
		getThmTrabajadors().remove(thmTrabajador);
		thmTrabajador.setThmCargo(null);

		return thmTrabajador;
	}

}