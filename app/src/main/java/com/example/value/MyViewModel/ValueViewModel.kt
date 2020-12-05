package com.example.value.MyViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.value.Model.Repository
import com.example.value.Model.ValueDB
import com.example.value.Model.ValueItem

class ValueViewModel(application: Application): AndroidViewModel(application) {

    private val mRepository: Repository
    val mAllValue: LiveData<List<ValueItem>>

    init {
        val mValueDAO = ValueDB.getDataBase(application).getValueDAO()
        mRepository = Repository(mValueDAO)
        mAllValue = mRepository.mMoney
        mRepository.getTiempoFromServer()
    }

    fun getOneCode(codigo: String): LiveData<ValueItem> {
        return mRepository.getOneByCode(codigo)
    }

    fun getOneNombre(nombre: String): LiveData<ValueItem> {
        return mRepository.getOneByNombre(nombre)
    }

    fun getOneValor(valor: String): LiveData<ValueItem> {
        return mRepository.getOneByValor(valor)
    }

}

