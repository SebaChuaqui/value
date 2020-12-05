package com.example.value.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.value.MyViewModel.ValueViewModel
import com.example.value.R
import kotlinx.android.synthetic.main.fragment_second.*

class SecondFragment : Fragment() {


    lateinit var  mValueViewModel: ValueViewModel
    private var idDato: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mValueViewModel = ViewModelProvider(this).get(ValueViewModel::class.java)
        arguments?.let {
            idDato = it.getString("codigo")
            Log.d("OBJ", idDato.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        idDato?.let {
            mValueViewModel.getOneCode(it).observe(viewLifecycleOwner, Observer {
                Log.d("OBJ_LIV", it.codigo)
                code.setText(it.codigo)
                Log.d("OBJ_LIV", it.valor)
                value.setText(it.valor)
            })

        }

        button.setOnClickListener{

            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }


    }

}