import com.google.gson.Gson;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        final String URL_BASE = "https://v6.exchangerate-api.com/v6/";
        final String API_KEY = "69231b2489404f255c3773a9";
        ConsumoAPI consumoApi = new ConsumoAPI();
        Scanner teclado = new Scanner(System.in);

        System.out.println("Bienvenido al conversor de monedas");

        var opcion = -1;
        var monto = 0;

        String par1 = null;
        String par2 = null;

        while (opcion != 0) {
            System.out.println("""
                    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                    Seleccione el par de divisas que desea convertir:
                                        
                    1) Dólar --> Peso Argentino
                    2) Dólar --> Real Brasileño
                    3) Peso Argentino --> Dólar
                    4) Peso Argentino --> Real Brasileño
                    5) Real Brasileño --> Peso Argentino
                    6) Real Brasileño --> Dólar
                                        
                    0)Salir
                    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                    """);
            opcion = teclado.nextInt();
            teclado.nextLine();
            System.out.println("Escriba la cantidad que quiera convertir:");
            monto = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    par1 = "USD";
                    par2 = "ARS";
                    break;
                case 2:
                    par1 = "USD";
                    par2 = "BRL";
                    break;
                case 3:
                    par1 = "ARS";
                    par2 = "USD";
                    break;
                case 4:
                    par1 = "ARS";
                    par2 = "BRL";
                    break;
                case 5:
                    par1 = "BRL";
                    par2 = "ARS";
                    break;
                case 6:
                    par1 = "BRL";
                    par2 = "USD";
                    break;
                case 0:
                    System.out.println("Gracias por usar la aplicación!");
                    break;
                default:
                    System.out.println("Elija una opcion válida");
            }

            String json = consumoApi.obtenerDatos(URL_BASE,API_KEY,par1);
            Divisa respuesta = new Gson().fromJson(json,Divisa.class);

            double total = 0;

            switch (par2){
                case "ARS":
                    total = monto * respuesta.conversion_rates().ARS();
                    break;
                case "USD":
                    total = monto * respuesta.conversion_rates().USD();
                    break;
                case "BRL":
                    total = monto * respuesta.conversion_rates().BRL();
                    break;
            }

            System.out.println(monto+" "+par1+" equivalen a "+ total+" "+par2);
        }
    }
}