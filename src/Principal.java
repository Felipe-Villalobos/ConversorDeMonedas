import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Principal {
    // Declaración de componentes gráficos
    private static JComboBox<String> comboBoxMonedaBase;
    private static JComboBox<String> comboBoxMonedaDestino;
    private static JTextField textFieldCantidad;
    private static HistorialConversiones historialConversiones;
    private static JTextArea textAreaHistorial; // JTextArea para el historial

    // Mapa de asociación del nombre de la moneda
    private static Map<String, String> codigoMonedaMap = new HashMap<>();

    static {
        // Inicializar el mapa con las asociaciones de nombre de moneda y código
        codigoMonedaMap.put("Dólar estadounidense (USD)", "USD");
        codigoMonedaMap.put("Dírham de los Emiratos Árabes Unidos (AED)", "AED");
        codigoMonedaMap.put("Afgani afgano (AFN)", "AFN");
        codigoMonedaMap.put("Lek albanés (ALL)", "ALL");
        codigoMonedaMap.put("Dram armenio (AMD)", "AMD");
        codigoMonedaMap.put("Florín de las Antillas Neerlandesas (ANG)", "ANG");
        codigoMonedaMap.put("Kwanza angoleño (AOA)", "AOA");
        codigoMonedaMap.put("Peso argentino (ARS)", "ARS");
        codigoMonedaMap.put("Dólar australiano (AUD)", "AUD");
        codigoMonedaMap.put("Florín arubeño (AWG)", "AWG");
        codigoMonedaMap.put("Manat azerbaiyano (AZN)", "AZN");
        codigoMonedaMap.put("Marco convertible de Bosnia y Herzegovina (BAM)", "BAM");
        codigoMonedaMap.put("Dólar de Barbados (BBD)", "BBD");
        codigoMonedaMap.put("Taka bangladesí (BDT)", "BDT");
        codigoMonedaMap.put("Lev búlgaro (BGN)", "BGN");
        codigoMonedaMap.put("Dinar bahreiní (BHD)", "BHD");
        codigoMonedaMap.put("Franco burundés (BIF)", "BIF");
        codigoMonedaMap.put("Dólar bermudeño (BMD)", "BMD");
        codigoMonedaMap.put("Dólar de Brunéi (BND)", "BND");
        codigoMonedaMap.put("Boliviano boliviano (BOB)", "BOB");
        codigoMonedaMap.put("Real brasileño (BRL)", "BRL");
        codigoMonedaMap.put("Dólar bahameño (BSD)", "BSD");
        codigoMonedaMap.put("Ngultrum butanés (BTN)", "BTN");
        codigoMonedaMap.put("Pula botsuano (BWP)", "BWP");
        codigoMonedaMap.put("Rublo bielorruso (BYN)", "BYN");
        codigoMonedaMap.put("Dólar beliceño (BZD)", "BZD");
        codigoMonedaMap.put("Dólar canadiense (CAD)", "CAD");
        codigoMonedaMap.put("Franco congoleño (CDF)", "CDF");
        codigoMonedaMap.put("Franco suizo (CHF)", "CHF");
        codigoMonedaMap.put("Peso chileno (CLP)", "CLP");
        codigoMonedaMap.put("Yuan chino (CNY)", "CNY");
        codigoMonedaMap.put("Peso colombiano (COP)", "COP");
        codigoMonedaMap.put("Colón costarricense (CRC)", "CRC");
        codigoMonedaMap.put("Peso cubano (CUP)", "CUP");
        codigoMonedaMap.put("Escudo caboverdiano (CVE)", "CVE");
        codigoMonedaMap.put("Corona checa (CZK)", "CZK");
        codigoMonedaMap.put("Franco yibutiano (DJF)", "DJF");
        codigoMonedaMap.put("Corona danesa (DKK)", "DKK");
        codigoMonedaMap.put("Peso dominicano (DOP)", "DOP");
        codigoMonedaMap.put("Dinar argelino (DZD)", "DZD");
        codigoMonedaMap.put("Libra egipcia (EGP)", "EGP");
        codigoMonedaMap.put("Nakfa eritreo (ERN)", "ERN");
        codigoMonedaMap.put("Birr etíope (ETB)", "ETB");
        codigoMonedaMap.put("Euro (EUR)", "EUR");
        codigoMonedaMap.put("Dólar fiyiano (FJD)", "FJD");
        codigoMonedaMap.put("Libra malvinense (FKP)", "FKP");
        codigoMonedaMap.put("Corona feroesa (FOK)", "FOK");
        codigoMonedaMap.put("Libra esterlina británica (GBP)", "GBP");
        codigoMonedaMap.put("Lari georgiano (GEL)", "GEL");
        codigoMonedaMap.put("Libra de Guernsey (GGP)", "GGP");
        codigoMonedaMap.put("Cedi ghanés (GHS)", "GHS");
        codigoMonedaMap.put("Libra de Gibraltar (GIP)", "GIP");
        codigoMonedaMap.put("Dalasi gambiano (GMD)", "GMD");
        codigoMonedaMap.put("Franco guineano (GNF)", "GNF");
        codigoMonedaMap.put("Quetzal guatemalteco (GTQ)", "GTQ");
        codigoMonedaMap.put("Dólar guyanés (GYD)", "GYD");
        codigoMonedaMap.put("Dólar de Hong Kong (HKD)", "HKD");
        codigoMonedaMap.put("Lempira hondureño (HNL)", "HNL");
        codigoMonedaMap.put("Kuna croata (HRK)", "HRK");
        codigoMonedaMap.put("Gourde haitiano (HTG)", "HTG");
        codigoMonedaMap.put("Forinto húngaro (HUF)", "HUF");
        codigoMonedaMap.put("Rupia indonesia (IDR)", "IDR");
        codigoMonedaMap.put("Nuevo séquel israelí (ILS)", "ILS");
        codigoMonedaMap.put("Libra de la Isla de Man (IMP)", "IMP");
        codigoMonedaMap.put("Rupia india (INR)", "INR");
        codigoMonedaMap.put("Dinar iraquí (IQD)", "IQD");
        codigoMonedaMap.put("Rial iraní (IRR)", "IRR");
        codigoMonedaMap.put("Corona islandesa (ISK)", "ISK");
        codigoMonedaMap.put("Libra de Jersey (JEP)", "JEP");
        codigoMonedaMap.put("Dólar jamaicano (JMD)", "JMD");
        codigoMonedaMap.put("Dinar jordano (JOD)", "JOD");
        codigoMonedaMap.put("Yen japonés (JPY)", "JPY");
        codigoMonedaMap.put("Chelín keniata (KES)", "KES");
        codigoMonedaMap.put("Som kirguís (KGS)", "KGS");
        codigoMonedaMap.put("Riel camboyano (KHR)", "KHR");
        codigoMonedaMap.put("Dólar de Kiribati (KID)", "KID");
        codigoMonedaMap.put("Franco comorano (KMF)", "KMF");
        codigoMonedaMap.put("Won surcoreano (KRW)", "KRW");
        codigoMonedaMap.put("Dinar kuwaití (KWD)", "KWD");
        codigoMonedaMap.put("Dólar de las Islas Caimán (KYD)", "KYD");
        codigoMonedaMap.put("Tenge kazajo (KZT)", "KZT");
        codigoMonedaMap.put("Kip laosiano (LAK)", "LAK");
        codigoMonedaMap.put("Libra libanesa (LBP)", "LBP");
        codigoMonedaMap.put("Rupia de Sri Lanka (LKR)", "LKR");
        codigoMonedaMap.put("Dólar liberiano (LRD)", "LRD");
        codigoMonedaMap.put("Loti de Lesoto (LSL)", "LSL");
        codigoMonedaMap.put("Dinar libio (LYD)", "LYD");
        codigoMonedaMap.put("Dírham marroquí (MAD)", "MAD");
        codigoMonedaMap.put("Leu moldavo (MDL)", "MDL");
        codigoMonedaMap.put("Ariary malgache (MGA)", "MGA");
        codigoMonedaMap.put("Dinar macedonio (MKD)", "MKD");
        codigoMonedaMap.put("Kyat birmano (MMK)", "MMK");
        codigoMonedaMap.put("Tugrik mongol (MNT)", "MNT");
        codigoMonedaMap.put("Pataca de Macao (MOP)", "MOP");
        codigoMonedaMap.put("Ouguiya mauritana (MRU)", "MRU");
        codigoMonedaMap.put("Rupia de Mauricio (MUR)", "MUR");
        codigoMonedaMap.put("Rufiyaa de Maldivas (MVR)", "MVR");
        codigoMonedaMap.put("Kwacha malauí (MWK)", "MWK");
        codigoMonedaMap.put("Peso mexicano (MXN)", "MXN");
        codigoMonedaMap.put("Ringgit malasio (MYR)", "MYR");
        codigoMonedaMap.put("Metical mozambiqueño (MZN)", "MZN");
        codigoMonedaMap.put("Dólar de Namibia (NAD)", "NAD");
        codigoMonedaMap.put("Naira nigeriano (NGN)", "NGN");
        codigoMonedaMap.put("Córdoba nicaragüense (NIO)", "NIO");
        codigoMonedaMap.put("Corona noruega (NOK)", "NOK");
        codigoMonedaMap.put("Rupia nepalí (NPR)", "NPR");
        codigoMonedaMap.put("Dólar neozelandés (NZD)", "NZD");
        codigoMonedaMap.put("Rial omaní (OMR)", "OMR");
        codigoMonedaMap.put("Balboa panameño (PAB)", "PAB");
        codigoMonedaMap.put("Sol peruano (PEN)", "PEN");
        codigoMonedaMap.put("Kina de Papúa Nueva Guinea (PGK)", "PGK");
        codigoMonedaMap.put("Peso filipino (PHP)", "PHP");
        codigoMonedaMap.put("Rupia paquistaní (PKR)", "PKR");
        codigoMonedaMap.put("Zloty polaco (PLN)", "PLN");
        codigoMonedaMap.put("Guaraní paraguayo (PYG)", "PYG");
        codigoMonedaMap.put("Rial qatarí (QAR)", "QAR");
        codigoMonedaMap.put("Leu rumano (RON)", "RON");
        codigoMonedaMap.put("Dinar serbio (RSD)", "RSD");
        codigoMonedaMap.put("Rublo ruso (RUB)", "RUB");
        codigoMonedaMap.put("Franco ruandés (RWF)", "RWF");
        codigoMonedaMap.put("Riyal saudita (SAR)", "SAR");
        codigoMonedaMap.put("Dólar de las Islas Salomón (SBD)", "SBD");
        codigoMonedaMap.put("Rupia seychelense (SCR)", "SCR");
        codigoMonedaMap.put("Libra sudanesa (SDG)", "SDG");
        codigoMonedaMap.put("Corona sueca (SEK)", "SEK");
        codigoMonedaMap.put("Dólar singapurense (SGD)", "SGD");
        codigoMonedaMap.put("Libra de Santa Elena (SHP)", "SHP");
        codigoMonedaMap.put("Leone de Sierra Leona (SLE)", "SLE");
        codigoMonedaMap.put("Leone de Sierra Leona (SLL)", "SLL");
        codigoMonedaMap.put("Chelín somalí (SOS)", "SOS");
        codigoMonedaMap.put("Dólar surinamés (SRD)", "SRD");
        codigoMonedaMap.put("Libra sursudanesa (SSP)", "SSP");
        codigoMonedaMap.put("Dobra santotomense (STN)", "STN");
        codigoMonedaMap.put("Libra siria (SYP)", "SYP");
        codigoMonedaMap.put("Lilangeni de Suazilandia (SZL)", "SZL");
        codigoMonedaMap.put("Baht tailandés (THB)", "THB");
        codigoMonedaMap.put("Somoni tayiko (TJS)", "TJS");
        codigoMonedaMap.put("Manat turcomano (TMT)", "TMT");
        codigoMonedaMap.put("Dinar tunecino (TND)", "TND");
        codigoMonedaMap.put("Pa'anga tongano (TOP)", "TOP");
        codigoMonedaMap.put("Lira turca (TRY)", "TRY");
        codigoMonedaMap.put("Dólar de Trinidad y Tobago (TTD)", "TTD");
        codigoMonedaMap.put("Dólar de Tuvalu (TVD)", "TVD");
        codigoMonedaMap.put("Nuevo dólar taiwanés (TWD)", "TWD");
        codigoMonedaMap.put("Chelín tanzano (TZS)", "TZS");
        codigoMonedaMap.put("Grivna ucraniana (UAH)", "UAH");
        codigoMonedaMap.put("Chelín ugandés (UGX)", "UGX");
        codigoMonedaMap.put("Peso uruguayo (UYU)", "UYU");
        codigoMonedaMap.put("Som uzbeko (UZS)", "UZS");
        codigoMonedaMap.put("Bolívar venezolano (VES)", "VES");
        codigoMonedaMap.put("Dong vietnamita (VND)", "VND");
        codigoMonedaMap.put("Vatu vanuatuense (VUV)", "VUV");
        codigoMonedaMap.put("Tala samoano (WST)", "WST");
        codigoMonedaMap.put("Franco CFA de África Central (XAF)", "XAF");
        codigoMonedaMap.put("Dólar del Caribe Oriental (XCD)", "XCD");
        codigoMonedaMap.put("Derechos especiales de giro (XDR)", "XDR");
        codigoMonedaMap.put("Franco CFA de África Occidental (XOF)", "XOF");
        codigoMonedaMap.put("Franco CFP (XPF)", "XPF");
        codigoMonedaMap.put("Rial yemení (YER)", "YER");
        codigoMonedaMap.put("Rand sudafricano (ZAR)", "ZAR");
        codigoMonedaMap.put("Kwacha zambiano (ZMW)", "ZMW");
        codigoMonedaMap.put("Dólar zimbabuense (ZWL)", "ZWL");

        // Agrega más monedas según sea necesario
    }

    public static void main(String[] args) {
        // Configuración de la ventana principal
        JFrame frame = new JFrame("Conversor de Monedas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());

        // Crear el panel principal con GridBagLayout
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Componentes gráficos: etiquetas, menús desplegables, campo de texto y botones
        JLabel labelMonedaBase = new JLabel("Moneda Base:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelPrincipal.add(labelMonedaBase, gbc);

        comboBoxMonedaBase = new JComboBox<>();
        gbc.gridx = 1;
        panelPrincipal.add(comboBoxMonedaBase, gbc);

        JLabel labelMonedaDestino = new JLabel("Moneda Destino:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelPrincipal.add(labelMonedaDestino, gbc);

        comboBoxMonedaDestino = new JComboBox<>();
        gbc.gridx = 1;
        panelPrincipal.add(comboBoxMonedaDestino, gbc);

        JLabel labelCantidad = new JLabel("Cantidad:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelPrincipal.add(labelCantidad, gbc);

        textFieldCantidad = new JTextField();
        gbc.gridx = 1;
        panelPrincipal.add(textFieldCantidad, gbc);

        JButton buttonConvertir = new JButton("Convertir");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        // Manejador de eventos para el botón de conversión
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
        panelPrincipal.add(buttonConvertir, gbc);

        JButton buttonMostrarHistorial = new JButton("Mostrar Historial");
        gbc.gridy = 4;

        // Manejador de eventos para el botón de historial
        buttonMostrarHistorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                historialConversiones.mostrarHistorial(); // Mostrar el historial de conversiones
            }
        });
        panelPrincipal.add(buttonMostrarHistorial, gbc);

        // JTextArea y JScrollPane para mostrar el historial
        textAreaHistorial = new JTextArea(10, 30);
        textAreaHistorial.setEditable(false); // Para que el usuario no pueda editar el historial
        JScrollPane scrollPaneHistorial = new JScrollPane(textAreaHistorial); // Agregar el JTextArea dentro de un JScrollPane para permitir desplazamiento
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panelPrincipal.add(scrollPaneHistorial, gbc);

        // Inicialización del objeto para manejar el historial de conversiones
        historialConversiones = new HistorialConversiones(textAreaHistorial);

        // Botón para ocultar el historial
        JButton buttonOcultarHistorial = new JButton("Ocultar Historial");
        gbc.gridy = 6;

        // Manejador de eventos para el botón de ocultar historial
        buttonOcultarHistorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Limpiar el JTextArea al hacer clic en el botón
                textAreaHistorial.setText("");
            }
        });
        panelPrincipal.add(buttonOcultarHistorial, gbc);

        // Agregar el panel principal al frame
        frame.add(panelPrincipal, BorderLayout.CENTER);

        frame.setVisible(true); // Hacer visible la ventana

        inicializarComboBoxMonedas(); // Inicializar los menús desplegables con las monedas disponibles
    }

    // Método para inicializar los menús desplegables con las monedas disponibles
    private static void inicializarComboBoxMonedas() {
        // Crear una lista ordenada alfabéticamente de las claves del mapa
        List<String> monedasOrdenadas = new ArrayList<>(codigoMonedaMap.keySet());
        Collections.sort(monedasOrdenadas);

        // Limpiar los JComboBox
        comboBoxMonedaBase.removeAllItems();
        comboBoxMonedaDestino.removeAllItems();

        // Agregar las monedas ordenadas a los JComboBox
        for (String moneda : monedasOrdenadas) {
            comboBoxMonedaBase.addItem(moneda);
            comboBoxMonedaDestino.addItem(moneda);
        }
    }

    // Método para obtener el código de la moneda seleccionada
    private static String obtenerCodigoMoneda(String nombreMoneda) {
        return codigoMonedaMap.get(nombreMoneda);
    }

    // Método para realizar la conversión de moneda
    private static void convertirMoneda() {
        String monedaBase = comboBoxMonedaBase.getSelectedItem().toString(); // Obtener la moneda base seleccionada
        String monedaDestino = comboBoxMonedaDestino.getSelectedItem().toString(); // Obtener la moneda destino seleccionada
        double cantidad = Double.parseDouble(textFieldCantidad.getText()); // Obtener la cantidad ingresada por el usuario

        String codigoMonedaBase = obtenerCodigoMoneda(monedaBase); // Obtener el código de la moneda base
        String codigoMonedaDestino = obtenerCodigoMoneda(monedaDestino); // Obtener el código de la moneda destino

        ConsultaInfoMoneda consultaInfoMoneda = new ConsultaInfoMoneda();
        Conversor conversor = consultaInfoMoneda.buscaMoneda(codigoMonedaBase); // Obtener tasas de cambio para la moneda base

        if (conversor != null) {
            if (conversor.getConversionRates().containsKey(codigoMonedaDestino)) {
                double tasaCambio = conversor.getConversionRates().get(codigoMonedaDestino);
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

}
