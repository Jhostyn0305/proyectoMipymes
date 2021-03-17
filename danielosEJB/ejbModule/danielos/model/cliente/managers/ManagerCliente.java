package danielos.model.cliente.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import danielos.model.auditoria.managers.ManagerAuditoria;
import danielos.model.core.entities.Cliente;
import danielos.model.core.entities.SegAsignacion;
import danielos.model.core.entities.SegModulo;
import danielos.model.core.entities.SegUsuario;
import danielos.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerCliente
 */
@Stateless
@LocalBean
public class ManagerCliente {

	@PersistenceContext
	private EntityManager em;
	@EJB
	private ManagerDAO mDAO;
	@EJB
	private ManagerAuditoria mAuditoria;

	/**
	 * Default constructor.
	 */
	public ManagerCliente() {

	}

	public void cerrarSesion(int idSegUsuario) {
		mAuditoria.mostrarLog(getClass(), "cerrarSesion", "Cerrar sesión usuario: " + idSegUsuario);
	}

	public void accesoNoPermitido(int idSegUsuario, String ruta) {
		mAuditoria.mostrarLog(getClass(), "accesoNoPermitido",
				"Usuario " + idSegUsuario + " intento no autorizado a " + ruta);
	}

	public List<Cliente> findAllClientes() {
		return mDAO.findAll(Cliente.class, "nombre");
	}

	public void insertarUsuario(SegUsuario nuevoUsuario) throws Exception {
		nuevoUsuario.setCodigo("n/a");
		mDAO.insertar(nuevoUsuario);
	}

	public void insertarCliente(String cedula, String nombre, String apellido, String celular, String email)
			throws Exception {
		Cliente nuevoCliente = new Cliente();
		nuevoCliente.setCedula(cedula);
		nuevoCliente.setApellido(apellido);
		nuevoCliente.setNombre(nombre);
		nuevoCliente.setCelular(celular);
		nuevoCliente.setEmail(email);
		mDAO.insertar(nuevoCliente);
	}

	public void actualizarCliente(Cliente edicionCliente) throws Exception {

		Cliente cliente = (Cliente) mDAO.findById(Cliente.class, edicionCliente.getCedula());
		cliente.setApellido(edicionCliente.getApellido());
		cliente.setNombre(edicionCliente.getNombre());
		cliente.setCelular(edicionCliente.getCelular());
		cliente.setEmail(edicionCliente.getEmail());
		mDAO.actualizar(cliente);
	}

	public void eliminarCliente(String cedula) throws Exception {
		Cliente cliente = (Cliente) mDAO.findById(Cliente.class, cedula);
		mDAO.eliminar(Cliente.class, cliente.getCedula());
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
