package com.tatanstudios.astropollomotorista.vistas.login


import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tatanstudios.astropollomotorista.R
import com.tatanstudios.astropollomotorista.extras.TokenManager
import com.tatanstudios.astropollomotorista.model.rutas.Routes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.LottieConstants
import com.tatanstudios.astropollomotorista.vistas.opciones.completadashoy.ListadoCompletadasOrdenScreen
import com.tatanstudios.astropollomotorista.vistas.opciones.entregandoordenes.EstadoEntregandoOrdenScreen
import com.tatanstudios.astropollomotorista.vistas.opciones.entregandoordenes.ListadoEntregandoOrdenScreen
import com.tatanstudios.astropollomotorista.vistas.opciones.historial.HistorialFechaScreen
import com.tatanstudios.astropollomotorista.vistas.opciones.historial.HistorialOrdenScreen
import com.tatanstudios.astropollomotorista.vistas.opciones.historial.ListadoProductosHistorialScreen
import com.tatanstudios.astropollomotorista.vistas.opciones.notificacion.NotificacionScreen
import com.tatanstudios.astropollomotorista.vistas.opciones.nuevasordenes.EstadoNuevaOrdenScreen
import com.tatanstudios.astropollomotorista.vistas.opciones.nuevasordenes.MapaClienteScreen
import com.tatanstudios.astropollomotorista.vistas.opciones.ordencanceladas.ListadoCanceladaOrdenScreen
import com.tatanstudios.astropollomotorista.vistas.opciones.pendienteordenes.EstadoPreapracionOrdenScreen
import com.tatanstudios.astropollomotorista.vistas.opciones.pendienteordenes.ListadoPreparacionOrdenScreen
import com.tatanstudios.astropollomotorista.vistas.principal.PrincipalScreen


class SplashApp : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // MODO VERTICAL
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        enableEdgeToEdge()
        setContent {
            // INICIO DE APLICACION
            AppNavigation()
        }
    }
}



// *** RUTAS DE NAVEGACION ***
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.VistaSplash.route) {

        composable(Routes.VistaSplash.route) { SplashScreen(navController) }
        composable(Routes.VistaLogin.route) { LoginScreen(navController) }
        composable(Routes.VistaPrincipal.route) { PrincipalScreen(navController) }


        // NOTIFICACIONES
        composable(Routes.VistaNotificaciones.route) { NotificacionScreen(navController) }

        // HISTORIAL FECHA
        composable(Routes.VistaHistorialFecha.route) { HistorialFechaScreen(navController) }

        // HISTORIAL LISTADO ORDENES
        composable(Routes.VistaHistorialListadoOrden.route) { backStackEntry ->
            val fecha1 = backStackEntry.arguments?.getString("fecha1") ?: ""
            val fecha2 = backStackEntry.arguments?.getString("fecha2") ?: ""

            HistorialOrdenScreen(navController = navController, _fecha1 = fecha1, _fecha2 = fecha2)
        }


        // LISTADO DE PRODUCTOS HISTORIAL ORDEN
        composable(Routes.VistaListadoProductosHistorialOrden.route) { backStackEntry ->
            val idordenStr = backStackEntry.arguments?.getString("idorden") ?: "0"
            val idorden = idordenStr.toIntOrNull() ?: 0

            ListadoProductosHistorialScreen(navController = navController, _idorden = idorden)
        }


        // Cuando se Toca la Card de nuevas ordenes
        composable(Routes.VistaEstadoNuevaOrden.route) { backStackEntry ->
            val idordenStr = backStackEntry.arguments?.getString("idorden") ?: "0"
            val idorden = idordenStr.toIntOrNull() ?: 0

            EstadoNuevaOrdenScreen(navController = navController, _idorden = idorden)
        }


        // MAPA CLIENTE
        composable(Routes.VistaMapaClienteOrden.route) { backStackEntry ->
            val latitud = backStackEntry.arguments?.getString("latitud") ?: ""
            val longitud = backStackEntry.arguments?.getString("longitud") ?: ""

            MapaClienteScreen(navController = navController, _latitud = latitud, _longitud = longitud)
        }

        // VISTA LISTADO ORDENES PENDIENTES
        composable(Routes.VistaListadoOrdenesPendientes.route) { ListadoPreparacionOrdenScreen(navController) }


        // ESTADO DE ORDEN LISTA PARA INICIAR ENTREGA
        composable(Routes.VistaEstadoIniciarOrden.route) { backStackEntry ->
            val idordenStr = backStackEntry.arguments?.getString("idorden") ?: "0"
            val idorden = idordenStr.toIntOrNull() ?: 0

            EstadoPreapracionOrdenScreen(navController = navController, _idorden = idorden)
        }

        // VISTA LISTADO ORDENES ENTREGANDO
        composable(Routes.VistaListadoOrdenesEntregando.route) { ListadoEntregandoOrdenScreen(navController) }


        // ESTADO DE ORDEN LISTA PARA FINALIZAR ENTREGA
        composable(Routes.VistaEstadoFinalizarOrden.route) { backStackEntry ->
            val idordenStr = backStackEntry.arguments?.getString("idorden") ?: "0"
            val idorden = idordenStr.toIntOrNull() ?: 0

            EstadoEntregandoOrdenScreen(navController = navController, _idorden = idorden)
        }

        // LISTADO DE PRODUCTOS HISTORIAL ORDEN
        composable(Routes.VistaListadoProductosHistorialOrden.route) { backStackEntry ->
            val idordenStr = backStackEntry.arguments?.getString("idorden") ?: "0"
            val idorden = idordenStr.toIntOrNull() ?: 0

            ListadoProductosHistorialScreen(navController = navController, _idorden = idorden)
        }


        // VISTA LISTADO ORDENES CANCELADAS
        composable(Routes.VistaListadoOrdenesCancelada.route) { ListadoCanceladaOrdenScreen(navController) }


        // VISTA LISTADO ORDENES COMPLETADAS
        composable(Routes.VistaListadoOrdenesCompletadas.route) { ListadoCompletadasOrdenScreen(navController) }





    }
}

@Composable
fun SplashScreen(navController: NavHostController) {
    val ctx = LocalContext.current
    val tokenManager = remember { TokenManager(ctx) }

    // Ejecutar la migración desde SharedPreferences solo una vez
    LaunchedEffect(Unit) {
        tokenManager.migrateFromSharedPreferencesIfNeeded()
    }

    val idusuario by tokenManager.idUsuario.collectAsState(initial = "")

    // Evitar que el usuario vuelva al splash con el botón atrás
    DisposableEffect(Unit) {
        onDispose {
            navController.popBackStack(Routes.VistaSplash.route, true)
        }
    }

    // Control de la navegación tras un retraso
    LaunchedEffect(idusuario) {
        delay(2000)

        if (idusuario.isNotEmpty()) {
            navController.navigate(Routes.VistaPrincipal.route) {
                popUpTo(Routes.VistaSplash.route) { inclusive = true }
            }
        } else {
            navController.navigate(Routes.VistaLogin.route) {
                popUpTo(Routes.VistaSplash.route) { inclusive = true }
            }
        }
    }

    // Animación y diseño
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.jsonmoto))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFD84B4B)),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .height(225.dp)
                .align(Alignment.Center)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(bottom = 36.dp, start = 8.dp, end = 12.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 22.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.montserratmedium)),
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Image(
                painter = painterResource(id = R.drawable.hamburguesa),
                contentDescription = stringResource(id = R.string.logotipo),
                modifier = Modifier
                    .size(width = 120.dp, height = 100.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    val navController = rememberNavController()
    SplashScreen(navController = navController)
}