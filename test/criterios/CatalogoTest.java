package criterios;

import serviciosYPaquetes.ItemCatalogo;
import serviciosYPaquetes.PaqueteTuristico;
import serviciosYPaquetes.ServicioIndividual;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reportes.VisitanteReportes;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.util.List;

public class CatalogoTest {

    Catalogo catalogo;
    CriterioBusqueda criterio;
    ItemCatalogo item;
    ItemCatalogo item2;
    ItemCatalogo item3;
    VisitanteReportes visitante;

    //Set up
    @BeforeEach
    public void setUp() {
        catalogo = new Catalogo();
        criterio = mock(CriterioBusqueda.class);
        item = mock(ItemCatalogo.class);
        item2 = mock(ItemCatalogo.class);
        item3 = mock(ItemCatalogo.class);
        visitante = mock(VisitanteReportes.class);
    }

    //Testing
    @Test
    public void buscarEnCatalogoVacioTest() {
        //Verify (Assert)
        assertTrue(catalogo.buscar(criterio).isEmpty());
        //Verify (Mockito)
        verifyNoInteractions(criterio);
    }

    @Test
    public void buscarDevuelveSoloLosItemsQueCumplenElCriterioTest() {
        //Setup
        catalogo.agregarItem(item);
        catalogo.agregarItem(item2);
        catalogo.agregarItem(item3);
        //Stubbing del comportamiento esperado
        when(criterio.evaluar(item)).thenReturn(true);
        when(criterio.evaluar(item2)).thenReturn(false);
        when(criterio.evaluar(item3)).thenReturn(true);
        //Verify (Assert)
        assertEquals(List.of(item, item3), catalogo.buscar(criterio));
        //Verify (Mockito)
        verify(criterio).evaluar(item);
        verify(criterio).evaluar(item2);
        verify(criterio).evaluar(item3);
    }

    @Test
    public void buscarSinCoincidenciasDevuelveListaVaciaTest() {
        //Setup
        catalogo.agregarItem(item);
        catalogo.agregarItem(item2);
        //Stubbing del comportamiento esperado
        when(criterio.evaluar(any())).thenReturn(false);
        //Verify (Assert)
        assertTrue(catalogo.buscar(criterio).isEmpty());
        //Verify (Mockito)
        verify(criterio, times(2)).evaluar(any());
    }

    @Test
    public void buscarConTodasLasCoincidenciasDevuelveTodosLosItemsTest() {
        //Setup
        catalogo.agregarItem(item);
        catalogo.agregarItem(item2);
        //Stubbing del comportamiento esperado
        when(criterio.evaluar(any())).thenReturn(true);
        //Verify (Assert)
        assertEquals(List.of(item, item2), catalogo.buscar(criterio));
    }

    @Test
    public void buscarNoModificaElCatalogoTest() {
        //Setup
        catalogo.agregarItem(item);
        catalogo.agregarItem(item2);
        //Stubbing del comportamiento esperado
        when(criterio.evaluar(item)).thenReturn(true);
        when(criterio.evaluar(item2)).thenReturn(false);
        //Exercise
        catalogo.buscar(criterio);
        CriterioBusqueda todos = mock(CriterioBusqueda.class);
        when(todos.evaluar(any())).thenReturn(true);
        //Verify (Assert)
        assertEquals(List.of(item, item2), catalogo.buscar(todos));
    }
    
    //Tests de generarReporte
    @Test
public void generarReporteVisitaServicioIndividualTest() {
    ServicioIndividual servicio = new ServicioIndividual(
        "1", "Hotel", "Proveedor A", "Alojamiento",
        10, LocalDate.of(2026, 2, 1),
        LocalDate.of(2026, 2, 5), 100.0
    );

    catalogo.agregarItem(servicio);
    catalogo.generarReporte(visitante);

    verify(visitante).visitar(servicio);
}

@Test
public void generarReporteVisitaPaqueteTuristicoTest() {
    PaqueteTuristico paquete = new PaqueteTuristico(
        "2", "Paquete", "Proveedor B", "Turismo",
        10, LocalDate.of(2026, 3, 1),
        LocalDate.of(2026, 3, 5), 10.0
    );

    catalogo.agregarItem(paquete);
    catalogo.generarReporte(visitante);

    verify(visitante).visitar(paquete);
}

@Test
public void generarReporteVisitaTodosLosItemsTest() {
    ServicioIndividual servicio = new ServicioIndividual(
        "1", "Hotel", "Proveedor A", "Alojamiento",
        10, LocalDate.of(2026, 2, 1),
        LocalDate.of(2026, 2, 5), 100.0
    );

    PaqueteTuristico paquete = new PaqueteTuristico(
        "2", "Paquete", "Proveedor B", "Turismo",
        10, LocalDate.of(2026, 3, 1),
        LocalDate.of(2026, 3, 5), 10.0
    );

    catalogo.agregarItem(servicio);
    catalogo.agregarItem(paquete);
    catalogo.generarReporte(visitante);

    verify(visitante).visitar(servicio);
    verify(visitante).visitar(paquete);
}

}
