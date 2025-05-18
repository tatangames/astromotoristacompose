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

    // AL TOCAR LA CARD DE NUEVAS ORDENES
    object VistaEstadoNuevaOrden: Routes("ordenEstadoNuevaOrden/{idorden}") {
        fun createRoute(idorden: String) = "ordenEstadoNuevaOrden/$idorden"
    }

    // MAPA CLIENTE
    object VistaMapaClienteOrden: Routes("mapaCliente/{latitud}/{longitud}") {
        fun createRoute(latitud: String, longitud: String) = "mapaCliente/$latitud/$longitud"
    }


    // LISTADO DE ORDENES PENDIENTES
    object VistaListadoOrdenesPendientes : Routes("listadoOrdenesPendientes")


    // VER ESTADO DE ORDEN LISTA PARA ENTREGA
    object VistaEstadoIniciarOrden: Routes("ordenEstadoIniciarOrden/{idorden}") {
        fun createRoute(idorden: String) = "ordenEstadoIniciarOrden/$idorden"
    }



    // LISTADO DE ORDENES ENTREGANDO
    object VistaListadoOrdenesEntregando : Routes("listadoOrdenesEntregando")



    // VER ESTADO DE ORDEN LISTA PARA FINALIZAR
    object VistaEstadoFinalizarOrden: Routes("ordenEstadoFinalizarOrden/{idorden}") {
        fun createRoute(idorden: String) = "ordenEstadoFinalizarOrden/$idorden"
    }





}