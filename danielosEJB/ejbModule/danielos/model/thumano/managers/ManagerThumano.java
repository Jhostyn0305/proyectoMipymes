package danielos.model.thumano.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import danielos.model.auditoria.managers.ManagerAuditoria;
import danielos.model.core.entities.SegAsignacion;
import danielos.model.core.entities.SegModulo;
import danielos.model.core.entities.SegUsuario;
import danielos.model.core.entities.ThmCargo;
import danielos.model.core.entities.ThmTrabajador;
import danielos.model.core.managers.ManagerDAO;
import danielos.model.core.utils.ModelUtil;
import danielos.model.seguridades.dto.LoginDTO;

/**
 * Session Bean implementation class ManagerThumano
 */
@Stateless
@LocalBean
public class ManagerThumano {

	@EJB
	private ManagerDAO mDAO;
	@EJB
	private ManagerAuditoria mAuditoria;
    
    public ManagerThumano() {
        // TODO Auto-generated constructor stub
    }
    
    public LoginDTO login(int idSegUsuario,String clave) throws Exception{
    	if(ModelUtil.isEmpty(clave)) {
    		mAuditoria.mostrarLog(getClass(), "login", "Debe indicar una clave "+idSegUsuario);
    		throw new Exception("Debe indicar una clave");
    	}
    	SegUsuario usuario=(SegUsuario) mDAO.findById(SegUsuario.class, idSegUsuario);
    	if(usuario==null) {
    		mAuditoria.mostrarLog(getClass(), "login", "No existe usuario "+idSegUsuario);
    		throw new Exception("Error en credenciales.");
    	}
    	if(usuario.getClave().equals(clave)) {
    		mAuditoria.mostrarLog(getClass(), "login", "Login exitoso "+idSegUsuario);
    		//crear DTO:
    		LoginDTO loginDTO=new LoginDTO();
    		loginDTO.setIdSegUsuario(usuario.getIdSegUsuario());
    		loginDTO.setCorreo(usuario.getCorreo());
    		//obtener la lista de modulos a los que tiene acceso:
    		String consulta="o.segUsuario.idSegUsuario="+usuario.getIdSegUsuario();
    		List<SegAsignacion> listaAsignaciones=mDAO.findWhere(SegAsignacion.class, consulta, null);
    		for(SegAsignacion asig:listaAsignaciones) {
    			SegModulo modulo=asig.getSegModulo();
    			loginDTO.getListaModulos().add(modulo);
    		}
    		return loginDTO;
    	}
    	mAuditoria.mostrarLog(getClass(), "login", "No coincide la clave "+idSegUsuario);
    	throw new Exception("Error en credenciales");
    }
    
    public void cerrarSesion(int idSegUsuario) {
    	mAuditoria.mostrarLog(getClass(), "cerrarSesion", "Cerrar sesión usuario: "+idSegUsuario);
    }
    
    public List<ThmTrabajador> findAllTrabajadores(){
    	return mDAO.findAll(ThmTrabajador.class, "idThmEmpleado");
    }
    
    public List<ThmCargo> findAllCargos(){
    	return mDAO.findAll(ThmCargo.class, "idThmCargo");
    }
    
    public List<SegUsuario> findAllUsuarios(){
    	return mDAO.findAll(SegUsuario.class, "idSegUsuario");
    }
    
    public void insertarTrabajador(ThmTrabajador nuevoTrabajador, Integer idCargo, Integer idUsuario) throws Exception {
    	ThmCargo carg=(ThmCargo) mDAO.findById(ThmCargo.class, idCargo);
    	SegUsuario usu=(SegUsuario) mDAO.findById(SegUsuario.class, idUsuario);
    	nuevoTrabajador.setThmCargo(carg);
    	nuevoTrabajador.setSegUsuario(usu);
    	mDAO.insertar(nuevoTrabajador);
    }
    
    public void insertarCargo(ThmCargo nuevoCargo) throws Exception {
    	mDAO.insertar(nuevoCargo);
    }
    
    public void actualizarTrabajador(ThmTrabajador edicionTrabajador, Integer idCargo, Integer idUsuario) throws Exception {
    	ThmTrabajador trabajador=(ThmTrabajador) mDAO.findById(ThmTrabajador.class, edicionTrabajador.getIdThmEmpleado());
    	ThmCargo carg=(ThmCargo) mDAO.findById(ThmCargo.class, idCargo);
    	SegUsuario usu=(SegUsuario) mDAO.findById(SegUsuario.class, idUsuario);
    	trabajador.setThmCargo(carg);
    	trabajador.setSegUsuario(usu);
    	trabajador.setHorasTrabajo(edicionTrabajador.getHorasTrabajo());
    	mDAO.actualizar(trabajador);
    }
    
    public void actualizarCargo(ThmCargo edicionCargo) throws Exception {
    	ThmCargo carg=(ThmCargo) mDAO.findById(ThmCargo.class, edicionCargo.getIdThmCargo());
    	carg.setNombreCargo(edicionCargo.getNombreCargo());
    	carg.setRemuneracionMensual(edicionCargo.getRemuneracionMensual());
    	mDAO.actualizar(carg);
    }

    public void eliminarTrabajador(int id_Trabajador) throws Exception {
    	ThmTrabajador trabajador=(ThmTrabajador) mDAO.findById(ThmTrabajador.class, id_Trabajador);
    	mDAO.eliminar(ThmTrabajador.class, trabajador.getIdThmEmpleado());
    }

    public void eliminarCargo(int id_cargo) throws Exception {
    	ThmCargo cargo=(ThmCargo) mDAO.findById(ThmCargo.class, id_cargo);
    	mDAO.eliminar(ThmCargo.class, cargo.getIdThmCargo());
    }

}
