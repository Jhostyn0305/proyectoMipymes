package danielos.model.ventas.managers;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

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
    
	/*public void venderProducto(int idSegUsuario, int idSegModulo) throws Exception {
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
	}*/

}
