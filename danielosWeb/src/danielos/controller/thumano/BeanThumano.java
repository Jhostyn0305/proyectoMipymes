package danielos.controller.thumano;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import danielos.controller.JSFUtil;
import danielos.model.core.entities.SegUsuario;
import danielos.model.core.entities.ThmCargo;
import danielos.model.core.entities.ThmTrabajador;
import danielos.model.thumano.managers.ManagerThumano;

@Named
@SessionScoped
public class BeanThumano implements Serializable {

	@EJB
	private ManagerThumano mTrabajador;
	
	private List<ThmTrabajador> listaTrabajador;
	private List<ThmCargo> listaCargo;
	private List<SegUsuario> listaUsuario;
	private ThmTrabajador nuevoTrabajador;
	private ThmCargo nuevoCargo;

	private ThmTrabajador edicionTrabajador;
	private ThmCargo edicionCargo;
	private Integer idCargo;
	private Integer idUsuario;

	
	public BeanThumano() {
		
	}
	
	public void inicializacion() {
		System.out.println("BeanSegIdiomas inicializado");
	}
 
	public String actionCargarMenuTrabajador() {
		listaTrabajador = mTrabajador.findAllTrabajadores();
		return "Listatrabajador";
	}
	
	public String actionCargarMenuCargos() {
		listaCargo = mTrabajador.findAllCargos();
		return "Listacargos";
	}

	public String actionMenuNuevoTrabajador() {
		nuevoTrabajador=new ThmTrabajador();
		listaCargo = mTrabajador.findAllCargos();
		listaUsuario = mTrabajador.findAllUsuarios();
		return "trabajador_nuevo";
	}
	
	public String actionMenuNuevoCargo() {
		nuevoCargo=new ThmCargo();
		return "cargo_nuevo";
	}
	
	public void actionListenerInsertarNuevoTrabajador() {
		try {
			mTrabajador.insertarTrabajador(nuevoTrabajador, idCargo, idUsuario);
			listaTrabajador=mTrabajador.findAllTrabajadores();
			nuevoTrabajador=new ThmTrabajador();
			JSFUtil.crearMensajeINFO("Trabajador insertado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerInsertarNuevoCargo() {
		try {
			mTrabajador.insertarCargo(nuevoCargo);
			JSFUtil.crearMensajeINFO("Cargo insertado.");
			nuevoCargo=new ThmCargo();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String actionSeleccionarEdicionTrabajador(ThmTrabajador trabajador) {
		edicionTrabajador=trabajador;
		listaCargo = mTrabajador.findAllCargos();
		listaUsuario = mTrabajador.findAllUsuarios();
		return "trabajador_edicion";
	}
	
	public String actionSeleccionarEdicionCargos(ThmCargo cargo) {
		edicionCargo=cargo;
		return "cargo_edicion";
	}
	
	public void actionListenerActualizarEdicionTrabajador() {
		try {
			mTrabajador.actualizarTrabajador(edicionTrabajador, idCargo, idUsuario);
			listaTrabajador=mTrabajador.findAllTrabajadores();
			JSFUtil.crearMensajeINFO("Trabajador actualizado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerActualizarEdicionCargo() {
		try {
			mTrabajador.actualizarCargo(edicionCargo);
			listaCargo=mTrabajador.findAllCargos();
			JSFUtil.crearMensajeINFO("Cargo actualizado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerEliminarTrabajador(int id_Trabajador) {
		try {
			mTrabajador.eliminarTrabajador(id_Trabajador);
			listaTrabajador=mTrabajador.findAllTrabajadores();
			JSFUtil.crearMensajeINFO("Trabajador eliminado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerEliminarCargo(int id_cargo) {
		try {
			mTrabajador.eliminarCargo(id_cargo);
			listaCargo=mTrabajador.findAllCargos();
			JSFUtil.crearMensajeINFO("Cargo eliminado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
////////////

	public List<ThmTrabajador> getListaTrabajador() {
		return listaTrabajador;
	}

	public void setListaTrabajador(List<ThmTrabajador> listaTrabajador) {
		this.listaTrabajador = listaTrabajador;
	}

	public List<ThmCargo> getListaCargo() {
		return listaCargo;
	}

	public void setListaCargo(List<ThmCargo> listaCargo) {
		this.listaCargo = listaCargo;
	}

	public ThmTrabajador getNuevoTrabajador() {
		return nuevoTrabajador;
	}

	public void setNuevoTrabajador(ThmTrabajador nuevoTrabajador) {
		this.nuevoTrabajador = nuevoTrabajador;
	}

	public List<SegUsuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<SegUsuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public Integer getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(Integer idCargo) {
		this.idCargo = idCargo;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public ThmTrabajador getEdicionTrabajador() {
		return edicionTrabajador;
	}

	public void setEdicionTrabajador(ThmTrabajador edicionTrabajador) {
		this.edicionTrabajador = edicionTrabajador;
	}

	public ThmCargo getNuevoCargo() {
		return nuevoCargo;
	}

	public void setNuevoCargo(ThmCargo nuevoCargo) {
		this.nuevoCargo = nuevoCargo;
	}

	public ThmCargo getEdicionCargo() {
		return edicionCargo;
	}

	public void setEdicionCargo(ThmCargo edicionCargo) {
		this.edicionCargo = edicionCargo;
	}
}
