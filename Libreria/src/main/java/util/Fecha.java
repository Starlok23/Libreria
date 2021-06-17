package util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public final class Fecha {
	
	public static long getFechaLong(LocalDate fecha) {
		String dia = String.valueOf(fecha.getDayOfMonth());
		String mes = String.valueOf(fecha.getMonth());
		String anno = String.valueOf(fecha.getYear());
		String fechaString = dia + "/" + mes + "/" + anno + "00:00:00";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date;
		long millis;
		try {
			date = sdf.parse(fechaString);
			millis = date.getTime();
		}catch (Exception ex) {
			System.out.println("Error al convertir de LocalDate a Long: " + ex);
			millis = 0;
		}
		return millis;
 	}
	
	public static LocalDate getFechaLocalDate(long millis) {
		return new java.sql.Date(millis).toLocalDate();
	}
	
	public static String getFechaString(long millis) {
		Date date = new Date(millis);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);
	}
	
}
