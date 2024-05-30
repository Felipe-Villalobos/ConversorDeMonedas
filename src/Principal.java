import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Principal {
    // Declaración de componentes gráficos
    private static JComboBox<String> comboBoxMonedaBase;
    private static JComboBox<String> comboBoxMonedaDestino;
    private static JTextField textFieldCantidad;
    private static HistorialConversiones historialConversiones;

    public static void main(String[] args) {
        // Configuración de la ventana principal
        JFrame frame = new JFrame("Conversor de Monedas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(4, 2)); // Cuadrícula de 4 filas y 2 columnas

        // Componentes gráficos: etiquetas, menús desplegables, campo de texto y botones
        JLabel labelMonedaBase = new JLabel("  Moneda Base:");
        frame.add(labelMonedaBase);

        comboBoxMonedaBase = new JComboBox<>();
        frame.add(comboBoxMonedaBase);

        JLabel labelMonedaDestino = new JLabel("  Moneda Destino:");
        frame.add(labelMonedaDestino);

        comboBoxMonedaDestino = new JComboBox<>();
        frame.add(comboBoxMonedaDestino);

        JLabel labelCantidad = new JLabel("  Cantidad:");
        frame.add(labelCantidad);

        textFieldCantidad = new JTextField();
        frame.add(textFieldCantidad);

        JButton buttonConvertir = new JButton("Convertir");
        buttonConvertir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verificar si el campo de cantidad está vacío
                if (textFieldCantidad.getText().isEmpty()) {
                    // Mostrar mensaje de error solicitando ingresar un valor
                    JOptionPane.showMessageDialog(null, "Por favor, ingresa un valor en el campo de cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Si el campo de cantidad no está vacío, realizar la conversión
                    convertirMoneda();
                }
            }
        });
        frame.add(buttonConvertir);

        historialConversiones = new HistorialConversiones();

        JButton buttonMostrarHistorial = new JButton("Mostrar Historial");
        buttonMostrarHistorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarHistorial();
            }
        });
        frame.add(buttonMostrarHistorial);

        frame.setVisible(true); // Hacer visible la ventana

        inicializarComboBoxMonedas(); // Inicializar los menús desplegables con las monedas disponibles
    }

    // Método para inicializar los menús desplegables con las monedas disponibles
    private static void inicializarComboBoxMonedas() {
        ConsultaInfoMoneda consultaInfoMoneda = new ConsultaInfoMoneda();
        Conversor conversor = consultaInfoMoneda.buscaMoneda("USD"); // Obtener tasas de cambio para moneda base predeterminada

        if (conversor != null) {
            List<String> monedas = List.copyOf(conversor.getConversionRates().keySet());
            for (String moneda : monedas) {
                comboBoxMonedaBase.addItem(moneda); // Agregar moneda al menú desplegable de moneda base
                comboBoxMonedaDestino.addItem(moneda); // Agregar moneda al menú desplegable de moneda destino
            }
        }
    }

    // Método para realizar la conversión de moneda
    private static void convertirMoneda() {
        String monedaBase = comboBoxMonedaBase.getSelectedItem().toString(); // Obtener la moneda base seleccionada
        String monedaDestino = comboBoxMonedaDestino.getSelectedItem().toString(); // Obtener la moneda destino seleccionada
        double cantidad = Double.parseDouble(textFieldCantidad.getText()); // Obtener la cantidad ingresada por el usuario

        ConsultaInfoMoneda consultaInfoMoneda = new ConsultaInfoMoneda();
        Conversor conversor = consultaInfoMoneda.buscaMoneda(monedaBase); // Obtener tasas de cambio para la moneda base

        if (conversor != null) {
            if (conversor.getConversionRates().containsKey(monedaDestino)) {
                double tasaCambio = conversor.getConversionRates().get(monedaDestino);
                double resultado = cantidad * tasaCambio;

                JOptionPane.showMessageDialog(null, String.format("%.2f %s equivale a %.2f %s", cantidad, monedaBase, resultado, monedaDestino));

                historialConversiones.registrarConversion(monedaBase, monedaDestino, cantidad, resultado); // Registrar la conversión en el historial
            } else {
                JOptionPane.showMessageDialog(null, "La moneda de destino especificada no está disponible.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo obtener información para la moneda especificada.");
        }
    }

    // Método para mostrar el historial de conversiones
    private static void mostrarHistorial() {
        historialConversiones.mostrarHistorial();
    }
}
