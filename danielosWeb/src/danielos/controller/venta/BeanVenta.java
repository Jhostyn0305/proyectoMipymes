package danielos.controller.venta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import danielos.controller.JSFUtil;
import danielos.model.cliente.managers.ManagerCliente;
import danielos.model.core.entities.Cliente;
import danielos.model.core.entities.Producto;
import danielos.model.venta.dto.VentaDTO;
import danielos.model.ventas.managers.ManagerVenta;

@Named
@SessionScoped
public class BeanVenta implements Serializable {
	@EJB
	private ManagerVenta mVenta;
	@EJB
	private ManagerCliente mCliente;
	private List<Producto> listaProductos;
	private List<Producto> carrito;
	private List<VentaDTO> carrito2;
	private List<Cliente> listaClientes;
	private Producto producto;
	private int cantidad;
	private Cliente cliente;
	private double subtotalCarrito;
	private double totalCarrito;

	public BeanVenta() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void inicializacion() {
		System.out.println("BeanVenta  Inicializado....");
		producto = new Producto();
		cliente = new Cliente();
	}

	// Redireccionamiento de la página
	public String actionMenuVenta() {
		listaProductos = mVenta.findProductosStock();
		return "venta";
	}

	public String actionVistaCliente() {
		listaClientes = mCliente.findAllClientes();
		return "clientes";
	}

	// Métodos, son llamados desde el Manager Venta

	public void actionListenerSeleccionarProduto(Producto prod) {
		producto = prod;
	}

	public void actionListenerSeleccionarCliente(Cliente clt) {
		cliente = clt;
	}

	public void actionListenerAgregarProductosCarrito() {
		carrito2 = mVenta.agregarCarritoFinal(carrito2, producto, cantidad);
		JSFUtil.crearMensajeINFO("Se ha agregado al carrito");
		subtotalCarrito = mVenta.calcularSubTotalCarrito(carrito2);
		totalCarrito = mVenta.calcularTotalCarrito(carrito2);
	}

	public String actionListenerVenderCarrito() {
		try {
			mVenta.registrarVenta(carrito2, cliente);
			JSFUtil.crearMensajeINFO("La venta se ha realizado correctamente");
			listaProductos = mVenta.findProductosStock();
			subtotalCarrito = 0;
			totalCarrito = 0;
			carrito2 = new ArrayList<VentaDTO>();
			return "venta";
		} catch (Exception e) {
			JSFUtil.crearMensajeWARN("Ha ocurrido un erro" + e);
			return "";
		}
	}

	// Getters and Setter de parámetros
	// El manager no debe ser manipulado
	// J Benalcázar
	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public List<Producto> getCarrito() {
		return carrito;
	}

	public void setCarrito(List<Producto> carrito) {
		this.carrito = carrito;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public List<VentaDTO> getCarrito2() {
		return carrito2;
	}

	public void setCarrito2(List<VentaDTO> carrito2) {
		this.carrito2 = carrito2;
	}

	public double getSubtotalCarrito() {
		return subtotalCarrito;
	}

	public void setSubtotalCarrito(double subtotalCarrito) {
		this.subtotalCarrito = subtotalCarrito;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getTotalCarrito() {
		return totalCarrito;
	}

	public void setTotalCarrito(double totalCarrito) {
		this.totalCarrito = totalCarrito;
	}

}
