package danielos.model.auditoria.managers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import danielos.model.core.entities.AudBitacora;
import danielos.model.core.managers.ManagerDAO;


/**
 * Session Bean implementation class ManagerAuditoria
 */
@Stateless
@LocalBean
public class ManagerAuditoria {
	@EJB
	private ManagerDAO mDAO;

	/**
	 * Default constructor.
	 */
	public ManagerAuditoria() {

	}

	/**
	 * Metodo basico para mostrar mensajes de depuracion.
	 * 
	 * @param clase        Informacion de la clase que se esta depurando.
	 * @param nombreMetodo Metodo que genera el mensaje para depuracion.
	 * @param mensaje      El mensaje a desplegar.
	 */
	public void mostrarLog(Class clase, String nombreMetodo, String mensaje) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(
				format.format(new Date()) + "[" + clase.getSimpleName() + "/" + nombreMetodo + "]: " + mensaje);
		AudBitacora pista = new AudBitacora();
		pista.setDescripcionEvento(mensaje);
		pista.setDireccionIp("localhost");
		Timestamp tiempo = new Timestamp(System.currentTimeMillis());
		pista.setFechaEvento(tiempo);
		pista.setIdUsuario("anonimo");
		pista.setNombreClase(clase.getSimpleName());
		pista.setNombreMetodo(nombreMetodo);
		try {
			mDAO.insertar(pista);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<AudBitacora> findBitacoraByFecha(Date fechaInicio, Date fechaFin) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println("fecha inicio: " + format.format(fechaInicio));
		System.out.println("fecha fin: " + format.format(fechaFin));
		String consulta = "select b from AudBitacora b where b.fechaEvento between :fechaInicio and :fechaFin order by b.fechaEvento";
		Query q = mDAO.getEntityManager().createQuery(consulta, AudBitacora.class);
		q.setParameter("fechaInicio", new Timestamp(fechaInicio.getTime()));
		q.setParameter("fechaFin", new Timestamp(fechaFin.getTime()));
		return q.getResultList();

	}

	public void eliminarBitacora() {
		System.out.println("La bitacora se ha eliminado.");
	}

}
