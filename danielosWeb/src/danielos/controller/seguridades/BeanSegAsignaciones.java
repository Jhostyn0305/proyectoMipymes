package danielos.controller.seguridades;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import danielos.controller.JSFUtil;
import danielos.model.core.entities.SegAsignacion;
import danielos.model.core.entities.SegModulo;
import danielos.model.core.entities.SegUsuario;
import danielos.model.seguridades.managers.ManagerSeguridades;


@Named
@SessionScoped
public class BeanSegAsignaciones implements Serializable {

	@EJB
	private ManagerSeguridades managerSeguridades;
	
	private List<SegUsuario> listaUsuarios;
	private List<SegModulo> listaModulos;
	private int idSegUsuarioSeleccionado;
	private List<SegAsignacion> listaAsignaciones;
	
	
	public BeanSegAsignaciones() {
		
	}
	
	public String actionMenuAsignaciones() {
		listaUsuarios=managerSeguridades.findAllUsuarios();
		listaModulos=managerSeguridades.findAllModulos();
		return "asignaciones";
	}
	
	public void actionListenerSeleccionarUsuario() {
		listaAsignaciones=managerSeguridades.findAsignacionByUsuario(idSegUsuarioSeleccionado);
	}
	
	public void actionListenerAsignarModulo(int idSegModulo) {
		try {
			managerSeguridades.asignarModulo(idSegUsuarioSeleccionado, idSegModulo);
			listaAsignaciones=managerSeguridades.findAsignacionByUsuario(idSegUsuarioSeleccionado);
			JSFUtil.crearMensajeINFO("Módulo asignado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerEliminarAsignacionModulo(int idSegAsignacion) {
		try {
			managerSeguridades.eliminarAsignacion(idSegAsignacion);
			listaAsignaciones=managerSeguridades.findAsignacionByUsuario(idSegUsuarioSeleccionado);
			JSFUtil.crearMensajeINFO("Asignación eliminada.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<SegUsuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<SegUsuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public List<SegModulo> getListaModulos() {
		return listaModulos;
	}

	public void setListaModulos(List<SegModulo> listaModulos) {
		this.listaModulos = listaModulos;
	}

	public int getIdSegUsuarioSeleccionado() {
		return idSegUsuarioSeleccionado;
	}

	public void setIdSegUsuarioSeleccionado(int idSegUsuarioSeleccionado) {
		this.idSegUsuarioSeleccionado = idSegUsuarioSeleccionado;
	}

	public List<SegAsignacion> getListaAsignaciones() {
		return listaAsignaciones;
	}

	public void setListaAsignaciones(List<SegAsignacion> listaAsignaciones) {
		this.listaAsignaciones = listaAsignaciones;
	}

}
