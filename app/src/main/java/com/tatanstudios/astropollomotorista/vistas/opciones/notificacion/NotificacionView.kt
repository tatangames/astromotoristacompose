package com.tatanstudios.astropollomotorista.vistas.opciones.notificacion

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.PowerManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tatanstudios.astropollomotorista.R
import com.tatanstudios.astropollomotorista.componentes.BarraToolbarColor
import android.provider.Settings
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.tatanstudios.astropollomotorista.componentes.CustomToasty
import com.tatanstudios.astropollomotorista.componentes.LoadingModal
import com.tatanstudios.astropollomotorista.componentes.ToastType
import com.tatanstudios.astropollomotorista.viewmodel.ActualizarNotificacionViewModel
import com.tatanstudios.astropollomotorista.viewmodel.InformacionNotificacionViewModel

@Composable
fun NotificacionScreen(
    navController: NavHostController,
    viewModel: InformacionNotificacionViewModel = viewModel(),
    viewModelActualizar: ActualizarNotificacionViewModel = viewModel(),
) {
    val ctx = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    keyboardController?.hide()

    var _idusuario by remember { mutableStateOf("") }
    val isIgnoringOptimizations = remember { mutableStateOf(false) }
    val isLoading by viewModel.isLoading.observeAsState(true)
    val resultado by viewModel.resultado.observeAsState()

    val isLoadingActualizar by viewModelActualizar.isLoading.observeAsState(true)
    val resultadoActualizar by viewModelActualizar.resultado.observeAsState()

    val tokenManager = remember { TokenManager(ctx) }
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    val lifecycleOwner = LocalLifecycleOwner.current
    val powerManager = ctx.getSystemService(Context.POWER_SERVICE) as PowerManager


    var estadoCheck by remember { mutableStateOf(false) }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                isIgnoringOptimizations.value = powerManager.isIgnoringBatteryOptimizations(ctx.packageName)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(Unit) {
        isIgnoringOptimizations.value = isIgnoringBatteryOptimizations(ctx)

        scope.launch {
            _idusuario = tokenManager.idUsuario.first()
            viewModel.informacionNotificacionRetrofit(id = _idusuario)
        }
    }

    Scaffold(
        topBar = {
            BarraToolbarColor(
                navController,
                stringResource(R.string.notificaciones),
                colorResource(R.color.colorRojo),
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = stringResource(R.string.estado_de_recibir_notificaciones),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(15.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = if (estadoCheck) stringResource(R.string.si) else stringResource(R.string.no),
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Switch(
                        checked = estadoCheck,
                        onCheckedChange = { estadoCheck = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = colorResource(R.color.colorAzul),
                            uncheckedThumbColor = Color.White,
                            uncheckedTrackColor = Color.LightGray
                        )
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))


                Button(
                    onClick = {
                        viewModelActualizar.actualizarNotificacionRetrofit(_idusuario,  if (estadoCheck) 1 else 0)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.colorVerde)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.actualizar))
                }

                Divider(
                    color = Color.LightGray, // Puedes personalizar el color
                    thickness = 1.dp         // O el grosor
                )

                Spacer(modifier = Modifier.height(15.dp))

                if (!isIgnoringOptimizations.value) {
                    Text(
                        text = stringResource(R.string.para_recibir_notificaciones_siempre),
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Button(
                        onClick = {
                            requestBatteryOptimizationPermission(ctx)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(stringResource(R.string.permitir_notificacion_permantente))
                    }
                } else {
                    Text(
                        text = stringResource(R.string.optimizacion_bateria_desactivada),
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

                Divider(
                    color = Color.LightGray, // Puedes personalizar el color
                    thickness = 1.dp         // O el grosor
                )

                Spacer(modifier = Modifier.height(15.dp))


                Button(
                    onClick = {
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                            data = Uri.fromParts("package", ctx.packageName, null)
                        }
                        ctx.startActivity(intent)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.administrar_permisos_en_ajustes))
                }

            }
        }


        if (isLoading) {
            LoadingModal(isLoading = true)
        }

        if (isLoadingActualizar) {
            LoadingModal(isLoading = true)
        }

        resultado?.getContentIfNotHandled()?.let { result ->
            when (result.success) {
                1 -> {

                   // ESTADO DE NOTIFICACION
                    estadoCheck = result.opcion == 1

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


        resultadoActualizar?.getContentIfNotHandled()?.let { result ->
            when (result.success) {
                1 -> {

                    // ACTUALIZADO
                    CustomToasty(
                        ctx,
                        stringResource(id = R.string.actualizado),
                        ToastType.SUCCESS
                    )
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

fun isIgnoringBatteryOptimizations(context: Context): Boolean {
    val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
    return powerManager.isIgnoringBatteryOptimizations(context.packageName)
}

fun requestBatteryOptimizationPermission(context: Context) {
    val intent = Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS).apply {
        data = Uri.parse("package:${context.packageName}")
    }
    context.startActivity(intent)
}