package com.tatanstudios.astropollomotorista.network



import com.tatanstudios.astropollomotorista.model.listado.ModeloDatosBasicos
import com.tatanstudios.astropollomotorista.model.listado.ModeloNuevasOrdenes
import com.tatanstudios.astropollomotorista.model.login.ModeloLogin
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    // VERIFICACION DE NUMERO
    @POST("motorista/login")
    @FormUrlEncoded
    fun verificarUsuarioPassword(@Field("usuario") telefono: String,
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





}


