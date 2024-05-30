import java.util.Map;

public class Conversor {
    private String base_code; // Atributo para almacenar el código de la moneda base
    private Map<String, Double> conversion_rates; // Atributo para almacenar las tasas de cambio

    public String getBase_code() {
        return base_code; // Método getter para obtener el código de la moneda base
    }

    public Map<String, Double> getConversionRates() {
        return conversion_rates; // Método getter para obtener las tasas de cambio
    }
}
