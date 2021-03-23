package danielos.controller.pedidos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import danielos.controller.JSFUtil;
import danielos.model.core.entities.Producto;
import danielos.model.core.entities.ProvPedido;
import danielos.model.core.entities.Proveedore;
import danielos.model.pedidos.managers.ManagerProvPedido;



@Named
@SessionScoped
public class BeanProvPedido implements Serializable {
	@EJB
	private ManagerProvPedido managerProvPedido;

	private List<Proveedore> listaProvedor;
	private List<Producto> listaProducto;
	private List<ProvPedido> listaProvPedido;
	private ProvPedido nuevoProvPedido;
	private ProvPedido edicionProvPedido;
	private Integer idPedido;
	private Long cantidad;
	private Boolean estado;
	private Date fechaPedido;
	private Integer codigo_producto;
	private String ruc_cedula;

	public BeanProvPedido() {

	}
	@PostConstruct
	public void inicializacion() {
		System.out.println("BeanProvPedido  Inicializado....");
		listaProvedor = managerProvPedido.findAllProveedores();
		listaProducto = managerProvPedido.findAllProduto();
		edicionProvPedido = new ProvPedido();
	}

	public String actionMenuProvPedido() {
		listaProvPedido = managerProvPedido.findAllProvPedido();
		return "provPedido";
	}

	public String actionMenuNuevoProveedor() {
		nuevoProvPedido = new ProvPedido();
		return "provPedido_nuevo";
	}

	public void actionListenerInsertarNuevoProvPedido() {
		try {
			managerProvPedido.insertarProvPedido(cantidad, codigo_producto, ruc_cedula, estado);
			listaProvPedido = managerProvPedido.findAllProvPedido();
			JSFUtil.crearMensajeINFO("Pedido registrado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public String actionSeleccionarEdicionProvPedido(ProvPedido provPedido) {
		edicionProvPedido = provPedido;
		return "provPedido_edicion";
	}

	public void actionListenerActualizarEdicionProveedor() {
		try {
			managerProvPedido.actualizarProvPedido(edicionProvPedido);
			listaProvPedido = managerProvPedido.findAllProvPedido();
			JSFUtil.crearMensajeINFO("Pedido actualizado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerEliminarProveedor(Integer idPedido) {
		try {
			managerProvPedido.eliminarProvPedido(idPedido);
			listaProvPedido = managerProvPedido.findAllProvPedido();
			JSFUtil.crearMensajeINFO("Pedido eliminado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Proveedore> getListaProvedor() {
		return listaProvedor;
	}

	public void setListaProvedor(List<Proveedore> listaProvedor) {
		this.listaProvedor = listaProvedor;
	}

	public List<Producto> getListaProducto() {
		return listaProducto;
	}

	public void setListaProducto(List<Producto> listaProducto) {
		this.listaProducto = listaProducto;
	}

	public List<ProvPedido> getListaProvPedido() {
		return listaProvPedido;
	}

	public void setListaProvPedido(List<ProvPedido> listaProvPedido) {
		this.listaProvPedido = listaProvPedido;
	}

	public ProvPedido getNuevoProvPedido() {
		return nuevoProvPedido;
	}

	public void setNuevoProvPedido(ProvPedido nuevoProvPedido) {
		this.nuevoProvPedido = nuevoProvPedido;
	}

	public ProvPedido getEdicionProvPedido() {
		return edicionProvPedido;
	}

	public void setEdicionProvPedido(ProvPedido edicionProvPedido) {
		this.edicionProvPedido = edicionProvPedido;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public Integer getCodigo_producto() {
		return codigo_producto;
	}

	public void setCodigo_producto(Integer codigo_producto) {
		this.codigo_producto = codigo_producto;
	}

	public String getRuc_cedula() {
		return ruc_cedula;
	}

	public void setRuc_cedula(String ruc_cedula) {
		this.ruc_cedula = ruc_cedula;
	}


}
