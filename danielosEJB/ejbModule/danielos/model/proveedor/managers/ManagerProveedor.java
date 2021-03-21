package danielos.model.proveedor.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import danielos.model.auditoria.managers.ManagerAuditoria;
import danielos.model.core.entities.Cliente;
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
public class ManagerProveedor {

	@PersistenceContext
	private EntityManager em;
	@EJB
	private ManagerDAO mDAO;
	@EJB
	private ManagerAuditoria mAuditoria;

	/**
	 * Default constructor.
	 */
	public ManagerProveedor() {

	}

	public void cerrarSesion(int idSegUsuario) {
		mAuditoria.mostrarLog(getClass(), "cerrarSesion", "Cerrar sesión usuario: " + idSegUsuario);
	}

	public void accesoNoPermitido(int idSegUsuario, String ruta) {
		mAuditoria.mostrarLog(getClass(), "accesoNoPermitido",
				"Usuario " + idSegUsuario + " intento no autorizado a " + ruta);
	}

	public List<Proveedore> findAllProveedor() {
		return mDAO.findAll(Proveedore.class, "razon_social");
	}

	public void insertarProveedor(Proveedore nuevoProveedor) throws Exception {
		// nuevoProveedor.setCodigo("n/a");
		mDAO.insertar(nuevoProveedor);
	}

	public void insertarProveedor(String rucCedula, String celular, String direccion, String email, String razonSocial)
			throws Exception {
		Proveedore nuevoProveedore = new Proveedore();
		nuevoProveedore.setRucCedula(rucCedula);
		nuevoProveedore.setCelular(celular);
		nuevoProveedore.setDireccion(direccion);
		nuevoProveedore.setEmail(email);
		nuevoProveedore.setRazonSocial(razonSocial);
		mDAO.insertar(nuevoProveedore);
	}

	public void actualizarProveedor(Proveedore edicionProeveedor) throws Exception {
		Proveedore proveedor = (Proveedore) mDAO.findById(Proveedore.class, edicionProeveedor.getRucCedula());
		proveedor.setCelular(edicionProeveedor.getCelular());
		proveedor.setDireccion(edicionProeveedor.getDireccion());
		proveedor.setEmail(edicionProeveedor.getEmail());
		proveedor.setRazonSocial(edicionProeveedor.getRazonSocial());
		mDAO.actualizar(proveedor);
	}

	public void eliminarProveedor(String rucCedula) throws Exception {
		Proveedore proveedor = (Proveedore) mDAO.findById(Proveedore.class, rucCedula);
		mDAO.eliminar(Proveedore.class, proveedor.getRucCedula());
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
