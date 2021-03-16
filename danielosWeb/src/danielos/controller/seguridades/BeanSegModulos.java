package danielos.controller.seguridades;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import danielos.controller.JSFUtil;
import danielos.model.core.entities.SegModulo;
import danielos.model.seguridades.managers.ManagerSeguridades;


@Named
@SessionScoped
public class BeanSegModulos implements Serializable {

	@EJB
	private ManagerSeguridades mSeguridades;
	private List<SegModulo> listaModulos;
	private SegModulo nuevoModulo;
	private SegModulo edicionModulo;

	public BeanSegModulos() {

	}

	@PostConstruct
	public void inicializacion() {
		System.out.println("BeanSegModulos inicializado...");
		nuevoModulo = new SegModulo();
	}

	public String actionCargarMenuModulos() {
		listaModulos = mSeguridades.findAllModulos();
		return "modulos?faces-redirect=true";
	}

	public void actionListenerInsertarModulo() {
		try {
			mSeguridades.insertarModulo(nuevoModulo);
			listaModulos = mSeguridades.findAllModulos();
			JSFUtil.crearMensajeINFO("Módulo creado.");
			nuevoModulo = new SegModulo();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerCargarModulo(SegModulo modulo) {
		edicionModulo = modulo;
	}

	public void actionListenerGuardarEdicionModulo() {
		try {
			mSeguridades.actualizarModulo(edicionModulo);
			JSFUtil.crearMensajeINFO("Módulo editado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerEliminarModulo(int idSegModulo) {
		try {
			mSeguridades.eliminarModulo(idSegModulo);
			listaModulos = mSeguridades.findAllModulos();
			JSFUtil.crearMensajeINFO("Módulo eliminado");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<SegModulo> getListaModulos() {
		return listaModulos;
	}

	public void setListaModulos(List<SegModulo> listaModulos) {
		this.listaModulos = listaModulos;
	}

	public SegModulo getNuevoModulo() {
		return nuevoModulo;
	}

	public void setNuevoModulo(SegModulo nuevoModulo) {
		this.nuevoModulo = nuevoModulo;
	}

	public SegModulo getEdicionModulo() {
		return edicionModulo;
	}

	public void setEdicionModulo(SegModulo edicionModulo) {
		this.edicionModulo = edicionModulo;
	}

}
