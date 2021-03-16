package danielos.model.core.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ModelUtil {
	/**
	 * Verifica si una cadena es igual a null o tiene longitud igual a cero.
	 * 
	 * @param cadena Cadena que va a verificarse.
	 * @return
	 */
	public static boolean isEmpty(String cadena) {
		if (cadena == null || cadena.length() == 0)
			return true;
		return false;
	}

	/**
	 * Devuelve el valor del anio actual.
	 * 
	 * @return valor entero del anio actual.
	 */
	public static int getAnioActual() {
		Date fechaActual = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy");
		int anioActual = Integer.parseInt(formato.format(fechaActual));
		return anioActual;
	}

	/**
	 * Adiciona o resta dias a una fecha de tipo Date.
	 * 
	 * @param date la fecha original.
	 * @param days el numero de dias a sumar o restar.
	 * @return la fecha resultante.
	 */
	public static Date addDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);

		return cal.getTime();
	}
}
