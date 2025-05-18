package com.tatanstudios.astropollomotorista.vistas.opciones.pendienteordenes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import com.tatanstudios.astropollomotorista.R
import com.tatanstudios.astropollomotorista.componentes.BarraToolbarColor
import com.tatanstudios.astropollomotorista.componentes.CustomModal1BotonTitulo
import com.tatanstudios.astropollomotorista.componentes.CustomModal2Botones
import com.tatanstudios.astropollomotorista.componentes.CustomToasty
import com.tatanstudios.astropollomotorista.componentes.LoadingModal
import com.tatanstudios.astropollomotorista.componentes.ProductoItemCard
import com.tatanstudios.astropollomotorista.componentes.ToastType
import com.tatanstudios.astropollomotorista.extras.TokenManager
import com.tatanstudios.astropollomotorista.model.listado.ModeloProductoOrdenesArray
import com.tatanstudios.astropollomotorista.model.rutas.Routes
import com.tatanstudios.astropollomotorista.network.RetrofitBuilder
import com.tatanstudios.astropollomotorista.viewmodel.IniciarEntregaOrdenViewModel
import com.tatanstudios.astropollomotorista.viewmodel.ProductosOrdenViewModel
import kotlinx.coroutines.flow.first

import kotlinx.coroutines.launch

@Composable
fun EstadoPreapracionOrdenScreen(navController: NavHostController, _idorden: Int,
                                 viewModelIniciarEntregaOrden: IniciarEntregaOrdenViewModel = viewModel(),
                                 viewModelProductosOrden: ProductosOrdenViewModel = viewModel(),
) {
    val ctx = LocalContext.current
    val scope = rememberCoroutineScope()

    // listado de productos
    val isLoadingProductos by viewModelProductosOrden.isLoading.observeAsState(initial = false)
    val resultadoProductos by viewModelProductosOrden.resultado.observeAsState()
    var boolDatosCargados by remember { mutableStateOf(false) }


    // para datos de seleccionar orden
    val isLoadingSeleccionarIniciar by viewModelIniciarEntregaOrden.isLoading.observeAsState(initial = false)
    val resultadoSeleccionarIniciar by viewModelIniciarEntregaOrden.resultado.observeAsState()

    var showDialogIniciarOrden by remember { mutableStateOf(false) }
    var showDialogInfoIniciarApi by remember { mutableStateOf(false) }

    // titulo y mensaje de respuestas
    var textoTituloApi by remember { mutableStateOf("") }
    var textoMensajeApi by remember { mutableStateOf("") }

    var _latitudCliente by remember { mutableStateOf("") }
    var _longitudCliente by remember { mutableStateOf("") }


    var modeloListaProductosArray by remember { mutableStateOf(listOf<ModeloProductoOrdenesArray>()) }


    val tokenManager = remember { TokenManager(ctx) }
    var idusuario by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()
    //val keyboardController = LocalSoftwareKeyboardController.current
    val textoError = stringResource(R.string.no_se_encontro_ubicacion)


    // Lanzar la solicitud cuando se carga la pantalla
    LaunchedEffect(Unit) {
        scope.launch {
            idusuario = tokenManager.idUsuario.first()
            viewModelProductosOrden.productosOrdenRetrofit(idorden = _idorden)
        }
    }


    Scaffold(
        topBar = {
            BarraToolbarColor(
                navController,
                stringResource(R.string.estado_de_orden),
                colorResource(R.color.colorRojo)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Orden #: $_idorden",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            // VER MAPA
                            if(_latitudCliente.isBlank() || _longitudCliente.isBlank()){
                                CustomToasty(
                                    ctx,
                                    textoError,
                                    ToastType.ERROR
                                )
                            }else{
                                navController.navigate(
                                    Routes.VistaMapaClienteOrden.createRoute(
                                        _latitudCliente,
                                        _longitudCliente
                                    ),
                                    navOptions {
                                        launchSingleTop = true
                                    }
                                )
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red,
                            contentColor = Color.White
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(stringResource(R.string.mapa))
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        onClick = { showDialogIniciarOrden = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50),
                            contentColor = Color.White
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(stringResource(R.string.iniciar_entrega))
                    }
                }
            }

            // Listado dinámico
            items(modeloListaProductosArray) { tipoProducto ->
                // Aquí colocas tu componente personalizado o vista para cada producto


                ProductoItemCard(
                    cantidad = tipoProducto.cantidad.toString(),
                    hayImagen = tipoProducto.utilizaImagen,
                    imagenUrl = "${RetrofitBuilder.urlImagenes}${tipoProducto.imagen}",
                    titulo = tipoProducto.nombreProducto,
                    descripcion = tipoProducto.nota,
                    precio = tipoProducto.precio,
                    onClick = {

                        /*navController.navigate(
                            Routes.VistaInfoProductoOrden.createRoute(
                                tipoProducto.id.toString(),
                            ),
                            navOptions {
                                launchSingleTop = true
                            }
                        )*/
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }


        // CONFIRMAR PARA SELECCIONAR ORDEN
        if(showDialogIniciarOrden){
            CustomModal2Botones(
                showDialog = true,
                message = stringResource(R.string.iniciar_entrega),
                onDismiss = { showDialogIniciarOrden = false },
                onAccept = {
                    showDialogIniciarOrden = false
                    textoTituloApi = ""
                    textoMensajeApi = ""

                    coroutineScope.launch {
                        viewModelIniciarEntregaOrden.iniciarEntregaOrdenRetrofit(
                            idorden = _idorden,
                        )
                    }

                },
                stringResource(R.string.si),
                stringResource(R.string.no),
            )
        }

        // MENSAJE DE API AL SELECCIONAR ORDEN
        if(showDialogInfoIniciarApi){

            CustomModal1BotonTitulo(
                showDialog = showDialogInfoIniciarApi,
                title = textoTituloApi,
                message = textoMensajeApi,
                onDismiss = {
                    showDialogInfoIniciarApi = false
                    navController.popBackStack()
                }
            )
        }





        if (isLoadingSeleccionarIniciar) {
            LoadingModal(isLoading = true)
        }

        if (isLoadingProductos) {
            LoadingModal(isLoading = true)
        }


    } // end-scalfold


    resultadoProductos?.getContentIfNotHandled()?.let { result ->
        when (result.success) {
            1 -> {
                // LISTADO DE PRODUCTOS

                _latitudCliente = result.latitudCliente?: ""
                _longitudCliente = result.longitudCliente?: ""

                modeloListaProductosArray = result.lista
                boolDatosCargados = true
            }
            else -> {
                // Error, recargar de nuevo
                CustomToasty(
                    ctx,
                    stringResource(id = R.string.error_reintentar_de_nuevo),
                    ToastType.ERROR
                )
            }
        }
    }

    resultadoSeleccionarIniciar?.getContentIfNotHandled()?.let { result ->
        when (result.success) {
            1 -> {

                // REGLA ORDEN FUE CANCELADA
                textoTituloApi = result.titulo?: ""
                textoMensajeApi = result.mensaje?: ""
                showDialogInfoIniciarApi = true
            }
            2 -> {
                // ORDEN NO ESTA LISTA PARA ENTREGA
                textoTituloApi = result.titulo?: ""
                textoMensajeApi = result.mensaje?: ""
                showDialogInfoIniciarApi = true
            }
            3 -> {
                // ORDEN INICIA ENTREGA
                textoTituloApi = result.titulo?: ""
                textoMensajeApi = result.mensaje?: ""
                showDialogInfoIniciarApi = true
            }
            else -> {
                // Error, recargar de nuevo
                CustomToasty(
                    ctx,
                    stringResource(id = R.string.error_reintentar_de_nuevo),
                    ToastType.ERROR
                )
            }
        }
    }


}

