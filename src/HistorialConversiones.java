import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HistorialConversiones {
    private List<String> registros; // Lista para almacenar los registros de conversiones
    private JTextArea textAreaHistorial; // JTextArea para mostrar el historial

    // Constructor que acepta un JTextArea como parámetro
    public HistorialConversiones(JTextArea textAreaHistorial) {
        this.textAreaHistorial = textAreaHistorial;
        registros = new ArrayList<>(); // Inicializar la lista de registros
    }

    public void registrarConversion(String monedaBase, String monedaDestino, double cantidad, double resultado) {
        LocalDateTime now = LocalDateTime.now(); // Obtener la fecha y hora actual
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Formatear la fecha y hora
        String timestamp = now.format(formatter); // Convertir la fecha y hora en una cadena formateada
        registros.add(String.format("[%s] Convertido %.2f %s a %.2f %s", timestamp, cantidad, monedaBase, resultado, monedaDestino)); // Agregar el registro de conversión a la lista
        // Actualizar el JTextArea con el nuevo registro
        actualizarTextArea(false);
    }

    public void mostrarHistorial() {
        StringBuilder historial = new StringBuilder(); // Utilizamos un StringBuilder para construir el historial
        for (String registro : registros) {
            historial.append(registro).append("\n"); // Agregar cada registro al StringBuilder
        }
        textAreaHistorial.setText(historial.toString()); // Mostrar el historial en el JTextArea
    }

    // Método privado para actualizar el JTextArea con el contenido del historial
    private void actualizarTextArea(boolean mostrar) {
        if (mostrar) {
            textAreaHistorial.setText(""); // Limpiar el contenido del JTextArea
            for (String registro : registros) {
                textAreaHistorial.append(registro + "\n"); // Agregar cada registro al JTextArea
            }
        } else {
            textAreaHistorial.setText(""); // Limpiar el contenido del JTextArea
        }
    }
}
