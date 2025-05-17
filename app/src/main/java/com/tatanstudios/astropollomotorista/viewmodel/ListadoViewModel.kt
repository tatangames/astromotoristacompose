package com.tatanstudios.astropollomotorista.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tatanstudios.astropollomotorista.extras.Event
import com.tatanstudios.astropollomotorista.model.listado.ModeloDatosBasicos
import com.tatanstudios.astropollomotorista.model.listado.ModeloNuevasOrdenes
import com.tatanstudios.astropollomotorista.model.login.ModeloLogin
import com.tatanstudios.astropollomotorista.network.RetrofitBuilder
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers



class LoginViewModel : ViewModel() {
    private val _usuario = MutableLiveData<String>()
    val usuario: LiveData<String> get() = _usuario

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    private val _resultado = MutableLiveData<Event<ModeloLogin>>()
    val resultado: LiveData<Event<ModeloLogin>> get() = _resultado

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private var disposable: Disposable? = null
    private var isRequestInProgress = false

    fun setUsuario(usuario: String) {
        _usuario.value = usuario
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun verificarUsuarioPasssword(idonesignal: String) {

        // Verificar si ya hay una solicitud en progreso
        if (isRequestInProgress) return

        isRequestInProgress = true
        _isLoading.value = true

        // EL DEVICE IDENTIFICA QUE ESTOY MANDANDO SOLICITUD DESDE ANDROID
        disposable = RetrofitBuilder.getApiService().verificarUsuarioPassword(_usuario.value!!, _password.value!!, idonesignal)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    _isLoading.value = false
                    _resultado.value = Event(response)
                    isRequestInProgress = false
                },
                { error ->
                    _isLoading.value = false
                    isRequestInProgress = false
                }
            )
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose() // Limpiar la suscripción
    }
}




class NuevasOrdenesViewModel() : ViewModel() {

    private val _resultado = MutableLiveData<Event<ModeloNuevasOrdenes>>()
    val resultado: LiveData<Event<ModeloNuevasOrdenes>> get() = _resultado

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private var disposable: Disposable? = null
    private var isRequestInProgress = false

    fun nuevasOrdenesRetrofit(idusuario: String, idfirebase: String?) {
        if (isRequestInProgress) return

        isRequestInProgress = true

        _isLoading.value = true
        disposable = RetrofitBuilder.getApiService().listadoNuevasOrdenas(idusuario, idfirebase)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .retry()
            .subscribe(
                { response ->
                    _isLoading.value = false
                    _resultado.value = Event(response)
                    isRequestInProgress = false
                },
                { error ->
                    _isLoading.value = false
                    isRequestInProgress = false
                }
            )
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose() // Limpiar la suscripción
    }
}




class InformacionNotificacionViewModel() : ViewModel() {

    private val _resultado = MutableLiveData<Event<ModeloDatosBasicos>>()
    val resultado: LiveData<Event<ModeloDatosBasicos>> get() = _resultado

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private var disposable: Disposable? = null
    private var isRequestInProgress = false

    fun informacionNotificacionRetrofit(id: String) {
        if (isRequestInProgress) return

        isRequestInProgress = true

        _isLoading.value = true
        disposable = RetrofitBuilder.getApiService().informacionEstadoNotificacion(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .retry()
            .subscribe(
                { response ->
                    _isLoading.value = false
                    _resultado.value = Event(response)
                    isRequestInProgress = false
                },
                { error ->
                    _isLoading.value = false
                    isRequestInProgress = false
                }
            )
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose() // Limpiar la suscripción
    }
}



class ActualizarNotificacionViewModel() : ViewModel() {

    private val _resultado = MutableLiveData<Event<ModeloDatosBasicos>>()
    val resultado: LiveData<Event<ModeloDatosBasicos>> get() = _resultado

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private var disposable: Disposable? = null
    private var isRequestInProgress = false

    fun actualizarNotificacionRetrofit(id: String, disponible: Int) {
        if (isRequestInProgress) return

        isRequestInProgress = true

        _isLoading.value = true
        disposable = RetrofitBuilder.getApiService().editarEstadoNotificaciones(id, disponible)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .retry()
            .subscribe(
                { response ->
                    _isLoading.value = false
                    _resultado.value = Event(response)
                    isRequestInProgress = false
                },
                { error ->
                    _isLoading.value = false
                    isRequestInProgress = false
                }
            )
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose() // Limpiar la suscripción
    }
}