package danielos.model.pedidos.managers;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import danielos.model.auditoria.managers.ManagerAuditoria;
import danielos.model.core.entities.Producto;
import danielos.model.core.entities.ProvPedido;
import danielos.model.core.entities.Proveedore;
import danielos.model.core.entities.SegAsignacion;
import danielos.model.core.entities.SegModulo;
import danielos.model.core.entities.SegUsuario;
import danielos.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerCliente
 */
@Stateless
@LocalBean
public class ManagerProvPedido {

	@PersistenceContext
	private EntityManager em;
	@EJB
	private ManagerDAO mDAO;
	@EJB
	private ManagerAuditoria mAuditoria;

	/**
	 * Default constructor.
	 */
	public ManagerProvPedido() {

	}

	public void cerrarSesion(int idSegUsuario) {
		mAuditoria.mostrarLog(getClass(), "cerrarSesion", "Cerrar sesión usuario: " + idSegUsuario);
	}

	public void accesoNoPermitido(int idSegUsuario, String ruta) {
		mAuditoria.mostrarLog(getClass(), "accesoNoPermitido",
				"Usuario " + idSegUsuario + " intento no autorizado a " + ruta);
	}

	public List<ProvPedido> findAllProvPedido() {
		return mDAO.findAll(ProvPedido.class, "idPedido");
	}

	public void insertarProvPedido(ProvPedido nuevoProvPedido) throws Exception {
		// nuevoProveedor.setCodigo("n/a");
		mDAO.insertar(nuevoProvPedido);
	}
	
	public List<Proveedore> findAllProveedores() {
		return mDAO.findAll(Proveedore.class, "rucCedula");
	}

	public List<Producto> findAllProduto() {
		return mDAO.findAll(Producto.class, "codigoProducto");
	}

	public void insertarProvPedido(Long cantidad , int codigoProducto, String rucCedula)
			throws Exception {
		ProvPedido nuevoProveedor = new ProvPedido();
		Proveedore prov = em.find(Proveedore.class, rucCedula);
		Producto prod = em.find(Producto.class, codigoProducto);
		nuevoProveedor.setCantidad(cantidad);
		nuevoProveedor.setProveedore(prov);
		nuevoProveedor.setProducto(prod);
		nuevoProveedor.setFechaPedido(new Date());
		nuevoProveedor.setEstado(false);
		mDAO.insertar(nuevoProveedor);
	}

	public void actualizarProvPedido(ProvPedido edicionProeveedor) throws Exception {
		ProvPedido proveedor = (ProvPedido) mDAO.findById(ProvPedido.class, edicionProeveedor.getIdPedido());
		proveedor.setCantidad(edicionProeveedor.getCantidad());
		proveedor.setProducto(edicionProeveedor.getProducto());
		proveedor.setProveedore(edicionProeveedor.getProveedore());
		proveedor.setEstado(edicionProeveedor.getEstado());
		mDAO.actualizar(proveedor);
	}

	public void eliminarProvPedido(Integer idPedido) throws Exception {
		ProvPedido proveedor = (ProvPedido) mDAO.findById(ProvPedido.class, idPedido);
		mDAO.eliminar(Proveedore.class, proveedor.getIdPedido());
	}

	public List<SegModulo> findAllModulos() {
		return mDAO.findAll(SegModulo.class, "nombreModulo");
	}

	/**
	 * Permite asignar el acceso a un modulo del inventario de sistemas.
	 * 
	 * @param idSegUsuario El codigo del usuario.
	 * @param idSegModulo  El codigo del modulo que se va a asignar.
	 * @throws Exception
	 */
	public void asignarModulo(int idSegUsuario, int idSegModulo) throws Exception {
		// Buscar los objetos primarios:
		SegUsuario usuario = (SegUsuario) mDAO.findById(SegUsuario.class, idSegUsuario);
		SegModulo modulo = (SegModulo) mDAO.findById(SegModulo.class, idSegModulo);
		// crear la relacion:
		SegAsignacion asignacion = new SegAsignacion();
		asignacion.setSegModulo(modulo);
		asignacion.setSegUsuario(usuario);
		// guardar la asignacion:
		mDAO.insertar(asignacion);
		mAuditoria.mostrarLog(getClass(), "asignarModulo",
				"Modulo " + idSegModulo + " asigando a usuario " + idSegUsuario);
	}

}
