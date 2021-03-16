package danielos.model.seguridades.dto;

import java.util.ArrayList;
import java.util.List;

import danielos.model.core.entities.SegModulo;



public class LoginDTO {
	private int idSegUsuario;
	private String correo;
	private List<SegModulo> listaModulos;

	public LoginDTO() {
		listaModulos = new ArrayList<SegModulo>();
	}

	public int getIdSegUsuario() {
		return idSegUsuario;
	}

	public void setIdSegUsuario(int idSegUsuario) {
		this.idSegUsuario = idSegUsuario;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public List<SegModulo> getListaModulos() {
		return listaModulos;
	}

	public void setListaModulos(List<SegModulo> listaModulos) {
		this.listaModulos = listaModulos;
	}
}
