package danielos.model.ventas.managers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import danielos.model.core.entities.ProdBodega;
import danielos.model.core.entities.Producto;
import danielos.model.core.entities.SegAsignacion;
import danielos.model.core.entities.SegModulo;
import danielos.model.core.entities.SegUsuario;
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

	/**
	 * Calcula el subtotal entre los Productos escogidos
	 * 
	 * @param carrito, lista de productos que han sido escogidos
	 * @return la suma de precios
	 * @author J Benalcazar
	 */
	public double calcularTotalCarrito(List<VentaDTO> carrito) {
		double suma = 0;
		for (VentaDTO ventaDTO : carrito) {
			suma += ventaDTO.getCantidad() * ventaDTO.getProducto().getPrecio().doubleValue();
		}
		return suma;
	}

}
