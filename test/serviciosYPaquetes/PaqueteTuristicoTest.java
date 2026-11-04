package serviciosYPaquetes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;

public class PaqueteTuristicoTest {

    PaquetesTuristicos paqueteTuristico;
    ItemCatalogo item;
    ItemCatalogo item2;

    //Set up
    @BeforeEach
    public void setUp() {
        paqueteTuristico = new PaquetesTuristicos("1", "PaqueteTuristico", "Proveedor", 10.0);
        item = mock(ItemCatalogo.class);
        item2 = mock(ItemCatalogo.class);
    }

    //Testing
    @Test
    public void getIdTest() {
        //Verify (Assert)
        assertEquals("1", paqueteTuristico.getId());
    }

    @Test
    public void getNombreTest() {
        //Verify (Assert)
        assertEquals("PaqueteTuristico", paqueteTuristico.getNombre());
    }

    @Test
    public void getProveedorTest() {
        //Verify (Assert)
        assertEquals("Proveedor", paqueteTuristico.getProveedor());
    }

    @Test
    public void getDescuentoTest() {
        //Verify (Assert)
        assertEquals(0.1, paqueteTuristico.getDescuento());
    }

    @Test
    public void precioBaseSinItemsTest() {
        //Verify (Assert)
        assertEquals(0.0, paqueteTuristico.precioBase());
    }

    @Test
    public void precioBaseSumaLosItemsTest() {
        //Setup
        paqueteTuristico.agregarItem(item);
        paqueteTuristico.agregarItem(item2);
        //Stubbing del comportamiento esperado
        when(item.precioBase()).thenReturn(100.0);
        when(item2.precioBase()).thenReturn(50.0);
        //Verify (Assert)
        assertEquals(150.0, paqueteTuristico.precioBase());
        //Verify (Mockito)
        verify(item).precioBase();
        verify(item2).precioBase();
    }

    @Test
    public void precioFinalAplicaElDescuentoTest() {
        //Setup
        paqueteTuristico.agregarItem(item);
        paqueteTuristico.agregarItem(item2);
        //Stubbing del comportamiento esperado
        when(item.precioBase()).thenReturn(100.0);
        when(item2.precioBase()).thenReturn(50.0);
        //Verify (Assert)
        assertEquals(135.0, paqueteTuristico.precioFinal());
    }

    @Test
    public void esValidoSinItemsTest() {
        //Verify (Assert)
        assertTrue(paqueteTuristico.esValido());
    }

    @Test
    public void esValidoConTodosLosItemsValidosTest() {
        //Setup
        paqueteTuristico.agregarItem(item);
        paqueteTuristico.agregarItem(item2);
        //Stubbing del comportamiento esperado
        when(item.esValido()).thenReturn(true);
        when(item2.esValido()).thenReturn(true);
        //Verify (Assert)
        assertTrue(paqueteTuristico.esValido());
        //Verify (Mockito)
        verify(item).esValido();
        verify(item2).esValido();
    }

    @Test
    public void noEsValidoConUnItemInvalidoTest() {
        //Setup
        paqueteTuristico.agregarItem(item);
        paqueteTuristico.agregarItem(item2);
        //Stubbing del comportamiento esperado
        when(item.esValido()).thenReturn(true);
        when(item2.esValido()).thenReturn(false);
        //Verify (Assert)
        assertFalse(paqueteTuristico.esValido());
        //Verify (Mockito)
        verify(item).esValido();
        verify(item2).esValido();
    }

    @Test
    public void eliminarItemTest() {
        //Setup
        paqueteTuristico.agregarItem(item);
        paqueteTuristico.agregarItem(item2);
        //Stubbing del comportamiento esperado
        when(item.precioBase()).thenReturn(100.0);
        when(item2.precioBase()).thenReturn(50.0);
        //Exercise
        paqueteTuristico.eliminarItem(item2);
        //Verify (Assert)
        assertEquals(100.0, paqueteTuristico.precioBase());
        //Verify (Mockito)
        verify(item2, never()).precioBase();
    }

    // CUPOS
    @Test
    void paqueteTieneCuposCuandoTodosSusItemsTienen() {
        ItemCatalogo vueloMock = mock(ItemCatalogo.class);
        ItemCatalogo hotelMock = mock(ItemCatalogo.class);
        when(vueloMock.tieneCupoDisponible()).thenReturn(true);
        when(hotelMock.tieneCupoDisponible()).thenReturn(true);
        PaquetesTuristicos paquete = new PaquetesTuristicos("P001", "Paquete Bariloche", "Agencia", 10, List.of(vueloMock, hotelMock));
        assertTrue(paquete.tieneCupoDisponible());
    }

    @Test
    void paqueteNoTieneCuposSiUnItemEstaAgotado() {
        ItemCatalogo vueloMock = mock(ItemCatalogo.class);
        ItemCatalogo hotelMock = mock(ItemCatalogo.class);
        when(vueloMock.tieneCupoDisponible()).thenReturn(true);
        when(hotelMock.tieneCupoDisponible()).thenReturn(false);
        PaquetesTuristicos paquete = new PaquetesTuristicos("P001", "Paquete Bariloche", "Agencia", 10, List.of(vueloMock, hotelMock));
        assertFalse(paquete.tieneCupoDisponible());
    }

    @Test
    void obtenerServiciosDeUnPaquete() {
        ServicioIndividual vuelo = mock(ServicioIndividual.class);
        ServicioIndividual hotel = mock(ServicioIndividual.class);

        when(vuelo.obtenerServicios()).thenReturn(List.of(vuelo));
        when(hotel.obtenerServicios()).thenReturn(List.of(hotel));

        PaquetesTuristicos paquete = new PaquetesTuristicos("P1", "Bariloche", "Agencia", 10, List.of(vuelo, hotel));
        assertEquals(List.of(vuelo, hotel), paquete.obtenerServicios());
    }

    @Test
    void obtenerServiciosDePaquetesAnidados() {

        ServicioIndividual vuelo = mock(ServicioIndividual.class);
        ServicioIndividual excursion = mock(ServicioIndividual.class);
        ServicioIndividual paseo = mock(ServicioIndividual.class);

        when(vuelo.obtenerServicios()).thenReturn(List.of(vuelo));
        when(excursion.obtenerServicios()).thenReturn(List.of(excursion));
        when(paseo.obtenerServicios()).thenReturn(List.of(paseo));

        PaquetesTuristicos paqueteCordoba = new PaquetesTuristicos("P2", "Cordoba", "Agencia", 10, List.of(excursion, paseo));

        PaquetesTuristicos paqueteBariloche = new PaquetesTuristicos("P1", "Bariloche", "Agencia", 10, List.of(vuelo, paqueteCordoba));

        assertEquals(List.of(vuelo, excursion, paseo), paqueteBariloche.obtenerServicios());
    }

    @Test
    void obtenerServiciosConservaLosRepetidos() {

        ServicioIndividual vuelo = mock(ServicioIndividual.class);
        ServicioIndividual hotel = mock(ServicioIndividual.class);

        when(vuelo.obtenerServicios()).thenReturn(List.of(vuelo));
        when(hotel.obtenerServicios()).thenReturn(List.of(hotel));

        PaquetesTuristicos paquete = new PaquetesTuristicos(
                "P1", "AventuraLoca", "Agencia", 10,
                List.of(vuelo, hotel, vuelo)
        );

        assertEquals(List.of(vuelo, hotel, vuelo), paquete.obtenerServicios()
        );
    }

}
