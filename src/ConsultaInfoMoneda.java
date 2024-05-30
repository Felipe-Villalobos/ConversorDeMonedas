import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaInfoMoneda {
    public Conversor buscaMoneda(String codigoMoneda){
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/cba0fcc645cb5380e8acb012/latest/"+codigoMoneda); // Crear la URI para la solicitud HTTP
        HttpClient client = HttpClient.newHttpClient(); // Crear un cliente HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build(); // Crear una solicitud HTTP

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString()); // Enviar la solicitud HTTP y obtener la respuesta como una cadena de texto
            return new Gson().fromJson(response.body(), Conversor.class); // Analizar la respuesta JSON y devolver un objeto Conversor
        } catch (Exception e) {
            throw new RuntimeException("No se pudo realizar la conversión"); // Manejar cualquier excepción y lanzar una RuntimeException
        }
    }
}
