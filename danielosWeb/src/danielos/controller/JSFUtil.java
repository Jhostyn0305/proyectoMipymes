package danielos.controller;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class JSFUtil {
	/**
	 * Crea un mensaje JSF
	 * @param severidad Puede tomar el valor de:
	 * <li>FacesMessage.SEVERITY_FATAL
	 * <li>FacesMessage.SEVERITY_ERROR
	 * <li>FacesMessage.SEVERITY_WARN
	 * <li>FacesMessage.SEVERITY_INFO
	 * @param mensaje Contenido del mensaje
	 */
	public static void crearMensaje(Severity severidad,String mensaje,String detalle){
		FacesMessage msg = new FacesMessage();
		msg.setSeverity(severidad);
		msg.setSummary(mensaje);
		msg.setDetail(detalle);
        FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public static void crearMensajeERROR(String mensaje){
		crearMensaje(FacesMessage.SEVERITY_ERROR,mensaje,null);
	}
	
	public static void crearMensajeWARN(String mensaje){
		crearMensaje(FacesMessage.SEVERITY_WARN,mensaje,null);
	}
	
	public static void crearMensajeINFO(String mensaje){
		crearMensaje(FacesMessage.SEVERITY_INFO,mensaje,null);
	}
}