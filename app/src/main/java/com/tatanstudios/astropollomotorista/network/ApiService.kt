package com.tatanstudios.astropollomotorista.network



import com.tatanstudios.astropollomotorista.model.listado.ModeloDatosBasicos
import com.tatanstudios.astropollomotorista.model.listado.ModeloHistorialOrdenes
import com.tatanstudios.astropollomotorista.model.listado.ModeloInfoProducto
import com.tatanstudios.astropollomotorista.model.listado.ModeloNuevasOrdenes
import com.tatanstudios.astropollomotorista.model.listado.ModeloOrdenesCancelada
import com.tatanstudios.astropollomotorista.model.listado.ModeloOrdenesCompletadas
import com.tatanstudios.astropollomotorista.model.listado.ModeloOrdenesEntregando
import com.tatanstudios.astropollomotorista.model.listado.ModeloOrdenesPreparacion
import com.tatanstudios.astropollomotorista.model.listado.ModeloProductoHistorialOrdenes
import com.tatanstudios.astropollomotorista.model.listado.ModeloProductoOrdenes
import com.tatanstudios.astropollomotorista.model.login.ModeloLogin
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    // VERIFICACION DE NUMERO
    @POST("motorista/login")
    @FormUrlEncoded
    fun verificarUsuarioPassword(@Field("usuario") usuario: String,
                          @Field("password") password: String,
                          @Field("idfirebase") idfirebase: String?
                          ): Single<ModeloLogin>



    // LISTADO DE NUEVAS ORDENES
    @POST("motorista/nuevas/ordenes")
    @FormUrlEncoded
    fun listadoNuevasOrdenas(@Field("id") id: String,
                             @Field("idfirebase") idfirebase: String?
    ): Single<ModeloNuevasOrdenes>


    // INFORMACION DE NOTIFICACIONES
    @POST("motorista/opcion/notificacion")
    @FormUrlEncoded
    fun informacionEstadoNotificacion(@Field("id") id: String,
    ): Single<ModeloDatosBasicos>


    // EDITAR ESTADO DE NOTIFICACIONES
    @POST("motorista/opcion/notificacion/editar")
    @FormUrlEncoded
    fun editarEstadoNotificaciones(@Field("id") id: String,
                                   @Field("disponible") disponible: Int,
    ): Single<ModeloDatosBasicos>


    // HISTORIAL LISTADO DE ORDENES
    @POST("motorista/historial/ordenes")
    @FormUrlEncoded
    fun listadoHistorialOrdenes(@Field("id") id: String,
                                @Field("fecha1") fecha1: String,
                                @Field("fecha2") fecha2: String,
    ): Single<ModeloHistorialOrdenes>



    // LISTADO DE PRODUCTOS HISTORIAL ORDEN
    @POST("motorista/listado/producto/orden")
    @FormUrlEncoded
    fun listadoProductosHistorialOrden(@Field("idorden") idorden: Int
    ): Single<ModeloProductoHistorialOrdenes>



    // SELECCIONAR ORDEN
    @POST("motorista/seleccionar/orden")
    @FormUrlEncoded
    fun seleccionarOrden(@Field("idorden") idorden: Int,
                         @Field("id") id: String
    ): Single<ModeloDatosBasicos>


    // LISTADO DE PRODUCTOS DE UNA ORDEN
    @POST("motorista/listado/producto/orden")
    @FormUrlEncoded
    fun listadoProductosOrden(@Field("idorden") idorden: Int
    ): Single<ModeloProductoOrdenes>



    // LISTADO DE ORDENES EN PREPARACION
    @POST("motorista/pendientes/entrega/orden")
    @FormUrlEncoded
    fun listadoOrdenesPendientes(@Field("id") id: String
    ): Single<ModeloOrdenesPreparacion>


    // SELECCIONAR PARA INICIAR ENTREGA DE LA ORDEN
    @POST("motorista/iniciar/entrega/orden")
    @FormUrlEncoded
    fun iniciarEntregaOrden(@Field("idorden") idorden: Int,
    ): Single<ModeloDatosBasicos>


    // LISTADO DE ORDENES QUE SE ESTAN ENTREGANDO
    @POST("motorista/entregando/entrega/orden")
    @FormUrlEncoded
    fun listadoOrdenesEntregando(@Field("id") id: String
    ): Single<ModeloOrdenesEntregando>


    // FINALIZAR ORDEN POR PARTE DEL MOTORISTA
    @POST("motorista/finalizar/entrega/orden")
    @FormUrlEncoded
    fun finalizarOrdenesEntregando(@Field("idorden") idorden: Int
    ): Single<ModeloDatosBasicos>


    // LISTADO DE ORDENES CANCELADAS HOY MOTORISTA
    @POST("motorista/listado/canceladas/hoy/orden")
    @FormUrlEncoded
    fun listadoOrdenesCancelada(@Field("id") id: String
    ): Single<ModeloOrdenesCancelada>



    // LISTADO DE ORDENES COMPLETADAS HOY
    @POST("motorista/listado/completadas/hoy/orden")
    @FormUrlEncoded
    fun listadoOrdenesCompletadas(@Field("id") id: String
    ): Single<ModeloOrdenesCompletadas>






}


