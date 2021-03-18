package danielos.model.ventas.managers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import danielos.model.core.entities.Cliente;
import danielos.model.core.entities.ProdBodega;
import danielos.model.core.entities.Producto;
import danielos.model.core.entities.SegAsignacion;
import danielos.model.core.entities.SegModulo;
import danielos.model.core.entities.SegUsuario;
import danielos.model.core.entities.VentaDetalle;
import danielos.model.core.entities.VentaMaestro;
import danielos.model.core.managers.ManagerDAO;
import danielos.model.venta.dto.VentaDTO;
import jdk.javadoc.internal.doclets.toolkit.taglets.SummaryTaglet;

/**
 * Session Bean implementation class ManagerVenta
 */
@Stateless
@LocalBean
public class ManagerVenta {
	@EJB
	private ManagerDAO mDAO;

	/**
	 * Default constructor.
	 */
	public ManagerVenta() {
		// TODO Auto-generated constructor stub
	}

	// Métodos para listar la información
	/**
	 * Método para buscar todos los productos existentes en Stock pero cuya cantidad
	 * sea mayor a 0
	 * 
	 * @return lista de productos
	 * @author J Benalcázar
	 */
	public List<Producto> findProductosStock() {
		List<ProdBodega> listaStock = mDAO.findAll(ProdBodega.class);
		List<Producto> listaProductos = new ArrayList<Producto>();
		for (ProdBodega producto : listaStock) {
			if (producto.getCantidad() > 0) {
				listaProductos.add(producto.getProducto());
			}
		}
		return listaProductos;
	}

	// Métodos para manipular el carrito
	/**
	 * Método para agregar productos al carrito
	 * 
	 * @param carrito
	 * @param p
	 * @param cantidad
	 * @return lista de productos seleccionados y también su cantidad
	 */
	public List<VentaDTO> agregarCarritoFinal(List<VentaDTO> carrito, Producto p, int cantidad) {
		if (carrito == null) {
			carrito = new ArrayList<VentaDTO>();
			VentaDTO nuevo = new VentaDTO(p, cantidad);
			carrito.add(nuevo);
		} else {
			VentaDTO nuevo = new VentaDTO(p, cantidad);
			carrito.add(nuevo);
		}
		return carrito;
	}

	
	// Cálculos respectivos
	// Subtotal y total
	/**
	 * Calcula el subtotal entre los Productos escogidos
	 * 
	 * @param carrito, lista de productos que han sido escogidos
	 * @return la suma de precios y tomando en cuenta la cantidad
	 * @author J Benalcazar
	 */
	public double calcularSubTotalCarrito(List<VentaDTO> carrito) {
		double suma = 0;
		for (VentaDTO ventaDTO : carrito) {
			suma += ventaDTO.getCantidad() * ventaDTO.getProducto().getPrecio().doubleValue();
		}
		return suma;
	}

	/**
	 * Calcula el total, que el cliente tendrá que pagar
	 * 
	 * @param carrito /Lista de productos con su cantidad d comprar
	 * @return el total que debe ser pagado
	 * @author J Benalcázar
	 */
	public double calcularTotalCarrito(List<VentaDTO> carrito) {
		double subtotal = calcularSubTotalCarrito(carrito);
		double calculoIVA = 0;
		double total = 0;
		calculoIVA = subtotal * 0.12;// Se aplica el 12% ya que las leyes de Ecuaor esteblecen dicho iva
		total = subtotal + calculoIVA;
		return total;
	}

	// Métodos para el registro en la base de datos

	/**
	 * Registra al Maestro que es encargada la Venta
	 * 
	 * @param cliente
	 * @return Venta maestro, ya que el detalle hace uso de este
	 * @throws Exception
	 * @author J Benalcázar
	 */
	public VentaMaestro registrarMaestroVenta(Cliente cliente) throws Exception {
		VentaMaestro vMaestro = new VentaMaestro();
		Date fechaActual = new Date();
		vMaestro.setCliente(cliente);
		vMaestro.setFechaVenta(fechaActual);
		vMaestro.setIva(new BigDecimal(12));
		vMaestro.setSubtotal(new BigDecimal(0));
		vMaestro.setTotal(new BigDecimal(0));
		mDAO.insertar(vMaestro);
		return vMaestro;
	}

	/**
	 * Registra el detalle de la venta, es decir los productos
	 * 
	 * @return carrito
	 * @author J Benalcázar
	 * @throws Exception
	 */
	public void registrarDetalleVenta(VentaMaestro vMaestro, List<VentaDTO> carrito) throws Exception {
		for (VentaDTO ventaDTO : carrito) {
			double subtotal = ventaDTO.getCantidad() * ventaDTO.getProducto().getPrecio().doubleValue();
			VentaDetalle vDetalle = new VentaDetalle();
			vDetalle.setVentaMaestro(vMaestro);
			vDetalle.setProducto(ventaDTO.getProducto());
			vDetalle.setCantidad((long) ventaDTO.getCantidad());
			vDetalle.setSubtotal((long) subtotal);
			mDAO.insertar(vDetalle);
		}
		VentaMaestro vMaestroActualizar = (VentaMaestro) mDAO.findById(VentaMaestro.class, vMaestro.getIdVenta());
		vMaestroActualizar.setSubtotal(new BigDecimal(calcularSubTotalCarrito(carrito)));
		vMaestroActualizar.setTotal(new BigDecimal(calcularTotalCarrito(carrito)));
		mDAO.actualizar(vMaestroActualizar);
	}

	/**
	 * Registro de la venta en la base de datos, Registra el meastro y el detalle
	 * con todos sus cambios
	 * 
	 * @param carrito Los porductos
	 * @param cliente El cliente que adquiere dichos productos
	 * @throws Exception
	 * @author J Benalcázar
	 */
	public void registrarVenta(List<VentaDTO> carrito, Cliente cliente) throws Exception {
		VentaMaestro nuevo = registrarMaestroVenta(cliente);
		registrarDetalleVenta(nuevo, carrito);
	}

}
