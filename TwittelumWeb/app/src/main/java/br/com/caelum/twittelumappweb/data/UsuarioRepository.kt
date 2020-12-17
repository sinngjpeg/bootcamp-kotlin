package br.com.caelum.twittelumappweb.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.caelum.twittelumappweb.modelo.Usuario
import br.com.caelum.twittelumappweb.webservices.UsuarioWebClient

class UsuarioRepository(private val webclient: UsuarioWebClient) {

    private val usuarioDaSessao = MutableLiveData<Usuario>()
    private val errorLiveData = MutableLiveData<Throwable>()

    fun getUsuario(): LiveData<Usuario> = usuarioDaSessao
    fun getErro(): LiveData<Throwable> = errorLiveData

    fun entra(usuario: Usuario) = webclient.fazLogin(usuario, sucesso, falha)

    fun cadastra(usuario: Usuario) = webclient.registra(usuario, sucesso, falha)


    private val sucesso = fun(usuario: Usuario) {
        usuarioDaSessao.postValue(usuario)
    }
    private val falha = fun(excecao: Throwable) {
        errorLiveData.postValue(excecao)
    }
}