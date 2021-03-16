package danielos.controller.venta;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import danielos.model.core.entities.Producto;
import danielos.model.ventas.managers.ManagerVenta;

@Named
@SessionScoped
public class BeanVenta implements Serializable {
	@EJB
	private ManagerVenta mVenta;
	private List<Producto> listaProductos;

	public BeanVenta() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void inicializacion() {
		System.out.println("BeanVenta  Inicializado....");
		listaProductos = mVenta.findAllProductos();
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

}
