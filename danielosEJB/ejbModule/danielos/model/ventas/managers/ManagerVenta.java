package danielos.model.ventas.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import danielos.model.core.entities.Producto;
import danielos.model.core.entities.SegAsignacion;
import danielos.model.core.entities.SegModulo;
import danielos.model.core.entities.SegUsuario;
import danielos.model.core.managers.ManagerDAO;

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
	

	public List<Producto> findAllProductos() {
		return mDAO.findAll(Producto.class);
	}
}
