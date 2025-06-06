package com.tatanstudios.astropollomotorista.vistas.opciones.ordencanceladas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.tatanstudios.astropollomotorista.extras.TokenManager
import com.tatanstudios.astropollomotorista.viewmodel.OrdenPreparacionBuscarViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.navOptions
import com.tatanstudios.astropollomotorista.R
import com.tatanstudios.astropollomotorista.componentes.BarraToolbarColor
import com.tatanstudios.astropollomotorista.componentes.CardCanceladaOrden
import com.tatanstudios.astropollomotorista.componentes.CardPreparacionOrden
import com.tatanstudios.astropollomotorista.componentes.CustomToasty
import com.tatanstudios.astropollomotorista.componentes.LoadingModal
import com.tatanstudios.astropollomotorista.componentes.ToastType
import com.tatanstudios.astropollomotorista.model.listado.ModeloOrdenesCanceladaArray
import com.tatanstudios.astropollomotorista.model.listado.ModeloOrdenesEntregandoArray
import com.tatanstudios.astropollomotorista.model.listado.ModeloOrdenesPreparacionArray
import com.tatanstudios.astropollomotorista.model.rutas.Routes
import com.tatanstudios.astropollomotorista.viewmodel.OrdenCanceladaBuscarViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListadoCanceladaOrdenScreen(navController: NavHostController,
                                 viewModel: OrdenCanceladaBuscarViewModel = viewModel()
) {

    val ctx = LocalContext.current
    var boolDatosCargados by remember { mutableStateOf(false) }
    val isLoading by viewModel.isLoading.observeAsState(true)
    val tokenManager = remember { TokenManager(ctx) }
    val resultado by viewModel.resultado.observeAsState()
    val scope = rememberCoroutineScope() // Crea el alcance de coroutine
    val keyboardController = LocalSoftwareKeyboardController.current
    var _idusuario by remember { mutableStateOf("") }

    var modeloListaOrdenesCanceladaArray by remember { mutableStateOf(listOf<ModeloOrdenesCanceladaArray>()) }

    val refreshing by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = {
            viewModel.canceladasOrdenRetrofit(_idusuario)
        }
    )

    LaunchedEffect(Unit) {
        scope.launch {
            _idusuario = tokenManager.idUsuario.first()
            viewModel.canceladasOrdenRetrofit(_idusuario)
        }
    }

    // ocultar teclado
    keyboardController?.hide()


    Scaffold(
        topBar = {
            BarraToolbarColor(
                navController,
                stringResource(R.string.ordenes_canceladas),
                colorResource(R.color.colorRojo),
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .pullRefresh(pullRefreshState) // 🔄 Aquí va el pull refresh
        ) {

            if (modeloListaOrdenesCanceladaArray.isEmpty() && boolDatosCargados) {

                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.carrovacio),
                        contentDescription = stringResource(R.string.sin_ordenes),
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .aspectRatio(1f),
                        contentScale = ContentScale.Fit
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = stringResource(R.string.no_hay_ordenes),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black,
                        fontSize = 18.sp
                    )
                }

            } else {
                LazyColumn(
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp,
                        top = 0.dp
                    ),
                    modifier = Modifier
                        .fillMaxSize()
                        .imePadding(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(modeloListaOrdenesCanceladaArray) { tipoOrden ->
                        CardCanceladaOrden(
                            orden = tipoOrden.id.toString(),
                            fecha = tipoOrden.fechaOrden,
                            venta = tipoOrden.totalFormat,
                            haycupon = tipoOrden.haycupon,
                            cupon = tipoOrden.mensajeCupon,
                            haypremio = tipoOrden.haypremio,
                            premio = tipoOrden.textopremio,
                            cliente = tipoOrden.cliente,
                            direccion = tipoOrden.direccion,
                            referencia = tipoOrden.referencia,
                            telefono = tipoOrden.telefono,
                            nota = tipoOrden.notaOrden,
                            onClick = {

                            }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }

            // 🔽 Indicador visual de pull-to-refresh
            PullRefreshIndicator(
                refreshing = refreshing,
                state = pullRefreshState,
                modifier = Modifier
                    .align(Alignment.TopCenter) // ✅ ahora dentro de BoxScope
            )
        }

        if (isLoading) {
            LoadingModal(isLoading = true)
        }

        resultado?.getContentIfNotHandled()?.let { result ->
            when (result.success) {
                1 -> {
                    modeloListaOrdenesCanceladaArray = result.lista
                    boolDatosCargados = true

                }

                else -> {
                    CustomToasty(
                        ctx,
                        stringResource(id = R.string.error_reintentar_de_nuevo),
                        ToastType.ERROR
                    )

                }
            }
        }
    } // end-scalfold




}