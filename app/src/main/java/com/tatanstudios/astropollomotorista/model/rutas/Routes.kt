package com.tatanstudios.astropollomotorista.model.rutas

sealed class Routes(val route: String) {
    object VistaSplash : Routes("splash")
    object VistaLogin : Routes("login")
    object VistaPrincipal : Routes("principal")

    object VistaNotificaciones : Routes("notificaciones")
    object VistaHistorialFecha : Routes("historialFecha")


    // HISTORIAL LISTADO ORDENES
    object VistaHistorialListadoOrden: Routes("historialListadoOrdenes/{fecha1}/{fecha2}") {
        fun createRoute(fecha1: String, fecha2: String) = "historialListadoOrdenes/$fecha1/$fecha2"
    }


    // LISTADO DE PRODUCTOS DE UNA ORDEN
    object VistaListadoProductosHistorialOrden: Routes("listadoProductosHistorialOrden/{idorden}") {
        fun createRoute(idorden: String) = "listadoProductosHistorialOrden/$idorden"
    }




}