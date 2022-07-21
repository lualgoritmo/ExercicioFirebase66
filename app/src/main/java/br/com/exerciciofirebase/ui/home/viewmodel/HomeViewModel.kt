package br.com.exerciciofirebase.ui.home.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.exerciciofirebase.domain.repository.AuthenticationRepository
import br.com.exerciciofirebase.domain.repository.RepositoryMsg
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class HomeViewModel : ViewModel() {
    private val authenticationRepository = AuthenticationRepository()
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage
    private val repositoryMsg = RepositoryMsg()
    private val _listMsg = MutableLiveData<List<String>>()

    //listMsg é para ver no viewModel
    val listMsg: LiveData<List<String>> = _listMsg
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun getUserName() = authenticationRepository.getNameUser()

    fun getUserEmail() = authenticationRepository.getEmailUser()

    fun logout() = authenticationRepository.exitSystem()

    //PEGANDO O CAMINHO DO DATABASE NO REPOSITÓRIO
    private fun getPath(msg: String): String {
        val uri: Uri = Uri.parse(msg)
        return uri.toString()
    }

    fun getListAllMsg() {
        repositoryMsg.getListMsg().addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<String>()
                for (resultSnapshot in snapshot.children) {
                    val response = resultSnapshot.getValue(String::class.java)
                    response?.let {
                        list.add(it)
                    }
                }
                _listMsg.value = list //ALTERA NO VIEWMODEL
            }

            override fun onCancelled(error: DatabaseError) {
                _errorMessage.value = error.message
            }
        })
    }
    fun saveDatabaseMsg(msg:String) {
        val path = getPath(msg)
        repositoryMsg.dataBaseReference().child("$path").setValue(msg)
    }
}