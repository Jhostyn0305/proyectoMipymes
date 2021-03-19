package danielos.controller.venta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import danielos.controller.JSFUtil;
import danielos.model.cliente.managers.ManagerCliente;
import danielos.model.core.entities.Cliente;
import danielos.model.core.entities.Producto;
import danielos.model.core.entities.VentaMaestro;
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
	private List<VentaDTO> carrito2;
	private List<Cliente> listaClientes;
	private List<VentaMaestro> listaFacturas;
	private Producto producto;
	private int cantidad;
	private Cliente cliente;
	private double subtotalCarrito;
	private double totalCarrito;
	private Date fechaInicio;
	private Date fechaFin;
	private int cantidadStock;

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

	public String actionVistaMaestros() {
		listaFacturas = mVenta.findAllVentasMaestros();
		return "maestro";
	}
	// Métodos, son llamados desde el Manager Venta

	public void actionListenerSeleccionarProduto(Producto prod) {
		producto = prod;
	}

	public void actionListenerSeleccionarCliente(Cliente clt) {
		cliente = clt;
	}

	public void actionListenerAgregarProductosCarrito() {
		try {
			carrito2 = mVenta.agregarCarritoFinal(carrito2, producto, cantidad);
			JSFUtil.crearMensajeINFO("Se ha agregado al carrito");
			subtotalCarrito = mVenta.calcularSubTotalCarrito(carrito2);
			totalCarrito = mVenta.calcularTotalCarrito(carrito2);
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR("" + e);
			e.printStackTrace();
		}

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
			JSFUtil.crearMensajeWARN("Ha ocurrido un error");
			return "";
		}
	}

	public void actionListenerSumarCarrito() {
		try {
			mVenta.sumarCantidad(carrito2, producto);
			subtotalCarrito = mVenta.calcularSubTotalCarrito(carrito2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JSFUtil.crearMensajeERROR("" + e);
			e.printStackTrace();
		}

	}

	public void actionListenerRestarCarrito() {
		try {
			mVenta.restarCantidad(carrito2, producto);
			subtotalCarrito = mVenta.calcularSubTotalCarrito(carrito2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void actionListenerEliminarDelCarrito() {
		try {
			mVenta.eliminarDelCarrito(carrito2, producto);
			subtotalCarrito = mVenta.calcularSubTotalCarrito(carrito2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Consultas
	public void actionListenerConsultarFacturas() {
		listaFacturas = mVenta.findFacturaByFecha(fechaInicio, fechaFin);
		JSFUtil.crearMensajeINFO("Registros encontrados: " + listaFacturas.size());
	}

	// Getters and Setter de parámetros
	// El manager no debe ser manipulado
	// J Benalcázar
	public List<Producto> getListaProductos() {
		return listaProductos;
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

	public List<VentaMaestro> getListaFacturas() {
		return listaFacturas;
	}

	public void setListaFacturas(List<VentaMaestro> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public int getCantidadStock() {
		return cantidadStock;
	}

	public void setCantidadStock(int cantidadStock) {
		this.cantidadStock = cantidadStock;
	}

}
