package com.example.value.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.value.Adapter.Value
import com.example.value.Adapter.ValueAdapter
import com.example.value.Model.ValueItem
import com.example.value.MyViewModel.ValueViewModel
import com.example.value.R
import kotlinx.android.synthetic.main.fragment_first.*

open class FirstFragment : Fragment(), Value {

    lateinit var mValueViewModel: ValueViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        mValueViewModel = ViewModelProvider(this).get(ValueViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val mRecyclerView = recyclerView
        val mAdapter = ValueAdapter(this)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(context)

        mValueViewModel.mAllValue.observe(viewLifecycleOwner, Observer {
            mAdapter.updateListValue(it)
            Log.d("funciona", it.toString())

        })

    }

    override fun passValue(mValue: ValueItem) {
        val mBundle = Bundle()
        mBundle.putString("codigo", mValue.codigo)
        mBundle.putString("nombre", mValue.nombre)
        mBundle.putString("valor", mValue.valor)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, mBundle)
    }
}
