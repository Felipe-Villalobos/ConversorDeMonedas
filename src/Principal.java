import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Crear un objeto Scanner para leer la entrada del usuario
        ConsultaInfoMoneda consultaInfoMoneda = new ConsultaInfoMoneda(); // Crear un objeto para consultar la información de las monedas
        HistorialConversiones historialConversiones = new HistorialConversiones(); // Crear un objeto para almacenar el historial de conversiones

        System.out.println("Bienvenido al Conversor de Monedas");

        while (true) { // Loop infinito para permitir conversiones múltiples
            System.out.println("\nIngrese el código de la moneda base (ej. USD):");
            String codigoMonedaBase = scanner.nextLine(); // Leer el código de la moneda base ingresado por el usuario

            Conversor conversor = consultaInfoMoneda.buscaMoneda(codigoMonedaBase); // Obtener la información de las tasas de cambio para la moneda base ingresada

            if (conversor == null) { // Verificar si se pudo obtener información para la moneda base
                System.out.println("No se pudo obtener información para la moneda especificada.");
                continue; // Continuar con la siguiente iteración del bucle
            }

            System.out.println("Ingrese el código de la moneda a la que desea convertir:");
            String codigoMonedaDestino = scanner.nextLine(); // Leer el código de la moneda de destino ingresado por el usuario

            if (!conversor.getConversionRates().containsKey(codigoMonedaDestino)) { // Verificar si la moneda de destino está disponible en las tasas de cambio obtenidas
                System.out.println("La moneda de destino especificada no está disponible.");
                continue; // Continuar con la siguiente iteración del bucle
            }

            System.out.println("Ingrese la cantidad a convertir:");
            double cantidad = scanner.nextDouble(); // Leer la cantidad a convertir ingresada por el usuario
            scanner.nextLine(); // Limpiar el buffer del scanner

            double tasaCambio = conversor.getConversionRates().get(codigoMonedaDestino); // Obtener la tasa de cambio para la moneda de destino
            double resultado = cantidad * tasaCambio; // Calcular el resultado de la conversión

            System.out.println(String.format("%.2f %s equivale a %.2f %s", cantidad, codigoMonedaBase, resultado, codigoMonedaDestino)); // Mostrar el resultado de la conversión

            historialConversiones.registrarConversion(codigoMonedaBase, codigoMonedaDestino, cantidad, resultado); // Registrar la conversión en el historial
            historialConversiones.mostrarHistorial(); // Mostrar el historial de conversiones
        }
    }
}