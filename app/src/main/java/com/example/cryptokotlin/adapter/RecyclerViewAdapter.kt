package com.example.cryptokotlin.adapter

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptokotlin.R
import com.example.cryptokotlin.model.CryptoModel

class RecyclerViewAdapter(private val cryptoList: ArrayList<CryptoModel>) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {


    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {

        val name : TextView = view.findViewById(R.id.name)
        val price : TextView = view.findViewById(R.id.price)
        fun bind(cryptoModel : CryptoModel){
            name.text = cryptoModel.currency
            price.text = cryptoModel.price
        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return RowHolder(view)

    }


    override fun onBindViewHolder(holder: RowHolder, position: Int) {

        holder.bind(cryptoList[position])

    }


    override fun getItemCount(): Int {
        return cryptoList.size;
    }


}