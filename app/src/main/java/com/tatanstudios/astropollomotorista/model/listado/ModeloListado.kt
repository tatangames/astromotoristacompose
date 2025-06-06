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




data class ModeloHistorialOrdenes(
    @SerializedName("success") val success: Int,
    @SerializedName("ordenes") val lista: List<ModeloHistorialOrdenesArray>
)


data class ModeloHistorialOrdenesArray(
    @SerializedName("id") val id: Int,
    @SerializedName("id_cliente") val idcliente: Int,
    @SerializedName("id_servicio") val idservicio: Int,
    @SerializedName("id_zona") val idzona: Int,
    @SerializedName("nota_orden") val notaOrden: String?,
    @SerializedName("totalformat") val totalFormat: String,
    @SerializedName("estado") val estado: String?,
    @SerializedName("fecha_orden") val fechaOrden: String,
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






data class ModeloProductoHistorialOrdenes(
    @SerializedName("success") val success: Int,
    @SerializedName("productos") val lista: List<ModeloProductoHistorialOrdenesArray>
)

data class ModeloProductoHistorialOrdenesArray(
    @SerializedName("id") val id: Int,
    @SerializedName("id_ordenes") val idordenes: Int,
    @SerializedName("id_producto") val idproducto: Int,
    @SerializedName("cantidad") val cantidad: Int,
    @SerializedName("nota") val nota: String?,
    @SerializedName("precio") val precio: String,
    @SerializedName("nombreproducto") val nombreProducto: String,
    @SerializedName("utiliza_imagen") val utilizaImagen: Int,
    @SerializedName("imagen") val imagen: String?,
    @SerializedName("descripcion") val descripcion: String?,
    @SerializedName("multiplicado") val multiplicado: String?,
)




data class ModeloInfoProducto(
    @SerializedName("success") val success: Int,
    @SerializedName("productos") val lista: List<ModeloInfoProductoArray>
)

data class ModeloInfoProductoArray(
    @SerializedName("id") val success: Int,
    @SerializedName("id_ordenes") val idordenes: Int,
    @SerializedName("id_producto") val idproducto: Int,
    @SerializedName("cantidad") val cantidad: Int,
    @SerializedName("nota") val nota: String?,
    @SerializedName("precio") val precio: String?,
    @SerializedName("nombreproducto") val nombreProducto: String?,
    @SerializedName("utiliza_imagen") val utilizaImagen: Int,
    @SerializedName("imagen") val imagen: String?,
    @SerializedName("descripcion") val descripcion: String?,
    @SerializedName("multiplicado") val multiplicado: String?,
)





data class ModeloProductoOrdenes(
    @SerializedName("success") val success: Int,
    @SerializedName("latitud") val latitudCliente: String?,
    @SerializedName("longitud") val longitudCliente: String?,
    @SerializedName("productos") val lista: List<ModeloProductoOrdenesArray>
)

data class ModeloProductoOrdenesArray(
    @SerializedName("id") val id: Int,
    @SerializedName("id_ordenes") val idordenes: Int,
    @SerializedName("id_producto") val idproducto: Int,
    @SerializedName("cantidad") val cantidad: Int,
    @SerializedName("nota") val nota: String?,
    @SerializedName("precio") val precio: String,
    @SerializedName("nombreproducto") val nombreProducto: String,
    @SerializedName("utiliza_imagen") val utilizaImagen: Int,
    @SerializedName("imagen") val imagen: String?,
    @SerializedName("descripcion") val descripcion: String?,
    @SerializedName("multiplicado") val multiplicado: String?,
)



data class ModeloOrdenesPreparacion(
    @SerializedName("success") val success: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("hayordenes") val nombre: Int,
    @SerializedName("ordenes") val lista: List<ModeloOrdenesPreparacionArray>
)

data class ModeloOrdenesPreparacionArray(
    @SerializedName("id") val id: Int,
    @SerializedName("id_cliente") val idcliente: Int,
    @SerializedName("id_servicio") val idservicio: Int,
    @SerializedName("id_zona") val idzona: Int,
    @SerializedName("nota_orden") val notaOrden: String?,
    @SerializedName("total_orden") val totalOrden: String?,
    @SerializedName("totalformat") val totalFormat: String,
    @SerializedName("fecha_orden") val fechaOrden: String,
    @SerializedName("fecha_estimada") val fechaEstimada: String?,
    @SerializedName("estado_iniciada") val estadoIniciada: Int,
    @SerializedName("fecha_iniciada") val fechaIniciada: String?,
    @SerializedName("estado_preparada") val estadoPreparada: Int,
    @SerializedName("fecha_preparada") val fechaPreparada: String?,
    @SerializedName("estado_camino") val estadoEnCamino: Int,
    @SerializedName("fecha_camino") val fechaEnCamino: String?,
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
    @SerializedName("nota_cancelada") val notaCancelada: String?,
    @SerializedName("visible") val visible: Int,
    @SerializedName("estado") val estado: String?,
)



data class ModeloOrdenesEntregando(
    @SerializedName("success") val success: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("hayordenes") val nombre: Int,
    @SerializedName("ordenes") val lista: List<ModeloOrdenesEntregandoArray>
)

data class ModeloOrdenesEntregandoArray(
    @SerializedName("id") val id: Int,
    @SerializedName("id_cliente") val idcliente: Int,
    @SerializedName("id_servicio") val idservicio: Int,
    @SerializedName("id_zona") val idzona: Int,
    @SerializedName("nota_orden") val notaOrden: String?,
    @SerializedName("total_orden") val totalOrden: String?,
    @SerializedName("totalformat") val totalFormat: String,
    @SerializedName("fecha_orden") val fechaOrden: String,
    @SerializedName("fecha_estimada") val fechaEstimada: String?,
    @SerializedName("estado_iniciada") val estadoIniciada: Int,
    @SerializedName("fecha_iniciada") val fechaIniciada: String?,
    @SerializedName("estado_preparada") val estadoPreparada: Int,
    @SerializedName("fecha_preparada") val fechaPreparada: String?,
    @SerializedName("estado_camino") val estadoEnCamino: Int,
    @SerializedName("fecha_camino") val fechaEnCamino: String?,
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
    @SerializedName("nota_cancelada") val notaCancelada: String?,
    @SerializedName("visible") val visible: Int,
    @SerializedName("estado") val estado: String?,
)








data class ModeloOrdenesCancelada(
    @SerializedName("success") val success: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("hayordenes") val nombre: Int,
    @SerializedName("ordenes") val lista: List<ModeloOrdenesCanceladaArray>
)

data class ModeloOrdenesCanceladaArray(
    @SerializedName("id") val id: Int,
    @SerializedName("id_cliente") val idcliente: Int,
    @SerializedName("id_servicio") val idservicio: Int,
    @SerializedName("id_zona") val idzona: Int,
    @SerializedName("nota_orden") val notaOrden: String?,
    @SerializedName("total_orden") val totalOrden: String?,
    @SerializedName("totalformat") val totalFormat: String,
    @SerializedName("fecha_orden") val fechaOrden: String,
    @SerializedName("fecha_estimada") val fechaEstimada: String?,
    @SerializedName("estado_iniciada") val estadoIniciada: Int,
    @SerializedName("fecha_iniciada") val fechaIniciada: String?,
    @SerializedName("estado_preparada") val estadoPreparada: Int,
    @SerializedName("fecha_preparada") val fechaPreparada: String?,
    @SerializedName("estado_camino") val estadoEnCamino: Int,
    @SerializedName("fecha_camino") val fechaEnCamino: String?,
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
    @SerializedName("nota_cancelada") val notaCancelada: String?,
    @SerializedName("visible") val visible: Int,
    @SerializedName("estado") val estado: String?,
)





data class ModeloOrdenesCompletadas(
    @SerializedName("success") val success: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("hayordenes") val nombre: Int,
    @SerializedName("ordenes") val lista: List<ModeloOrdenesCompletadasArray>
)

data class ModeloOrdenesCompletadasArray(
    @SerializedName("id") val id: Int,
    @SerializedName("id_cliente") val idcliente: Int,
    @SerializedName("id_servicio") val idservicio: Int,
    @SerializedName("id_zona") val idzona: Int,
    @SerializedName("nota_orden") val notaOrden: String?,
    @SerializedName("total_orden") val totalOrden: String?,
    @SerializedName("totalformat") val totalFormat: String,
    @SerializedName("fecha_orden") val fechaOrden: String,
    @SerializedName("fecha_estimada") val fechaEstimada: String?,
    @SerializedName("estado_iniciada") val estadoIniciada: Int,
    @SerializedName("fecha_iniciada") val fechaIniciada: String?,
    @SerializedName("estado_preparada") val estadoPreparada: Int,
    @SerializedName("fecha_preparada") val fechaPreparada: String?,
    @SerializedName("estado_camino") val estadoEnCamino: Int,
    @SerializedName("fecha_camino") val fechaEnCamino: String?,
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
    @SerializedName("nota_cancelada") val notaCancelada: String?,
    @SerializedName("visible") val visible: Int,
    @SerializedName("estado") val estado: String?,
)



