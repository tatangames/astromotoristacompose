package com.tatanstudios.astropollomotorista.model.listado

import com.google.gson.annotations.SerializedName

data class ModeloNuevasOrdenes(
    @SerializedName("success") val success: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("hayordenes") val nombre: Int,
    @SerializedName("ordenes") val lista: List<ModeloNuevasOrdenesArray>
)

data class ModeloNuevasOrdenesArray(
    @SerializedName("id") val id: Int,
    @SerializedName("id_cliente") val idcliente: Int,
    @SerializedName("id_servicio") val idservicio: Int,
    @SerializedName("id_zona") val idzona: Int,
    @SerializedName("nota_orden") val notaOrden: String?,
    @SerializedName("totalformat") val totalFormat: String,
    @SerializedName("fecha_orden") val fechaOrden: String,
    @SerializedName("estado_iniciada") val estadoIniciada: Int,
    @SerializedName("estado_cancelada") val estadoCancelada: Int,
    @SerializedName("fecha_cancelada") val fechaCancelada: String?,
    @SerializedName("haycupon") val haycupon: Int,
    @SerializedName("cliente") val cliente: String,
    @SerializedName("direccion") val direccion: String,
    @SerializedName("telefono") val telefono: String?,
    @SerializedName("referencia") val referencia: String?,
    @SerializedName("haypremio") val haypremio: Int,
    @SerializedName("textopremio") val textopremio: String?,
    @SerializedName("mensaje_cupon") val mensajeCupon: String?,
)



data class ModeloDatosBasicos(
    @SerializedName("success") val success: Int,
    @SerializedName("titulo") val titulo: String?,
    @SerializedName("mensaje") val mensaje: String?,
    @SerializedName("opcion") val opcion: Int,


)

