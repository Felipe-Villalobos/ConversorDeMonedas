import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HistorialConversiones {
    private List<String> registros; // Lista para almacenar los registros de conversiones

    public HistorialConversiones() {
        registros = new ArrayList<>(); // Inicializar la lista de registros
    }

    public void registrarConversion(String monedaBase, String monedaDestino, double cantidad, double resultado) {
        LocalDateTime now = LocalDateTime.now(); // Obtener la fecha y hora actual
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Formatear la fecha y hora
        String timestamp = now.format(formatter); // Convertir la fecha y hora en una cadena formateada
        registros.add(String.format("[%s] Convertido %.2f %s a %.2f %s", timestamp, cantidad, monedaBase, resultado, monedaDestino)); // Agregar el registro de conversión a la lista
    }

    public void mostrarHistorial() {
        System.out.println("\nHistorial de Conversiones:"); // Mostrar encabezado del historial
        for (String registro : registros) { // Iterar sobre cada registro en la lista
            System.out.println(registro); // Mostrar el registro de conversión
        }
    }
}
