package danielos.model.venta.dto;

import danielos.model.core.entities.Producto;

public class VentaDTO {
	private Producto producto;
	private int cantidad;

	public VentaDTO(Producto producto, int cantidad) {

		this.producto = producto;
		this.cantidad = cantidad;
	}

	public VentaDTO() {

	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
