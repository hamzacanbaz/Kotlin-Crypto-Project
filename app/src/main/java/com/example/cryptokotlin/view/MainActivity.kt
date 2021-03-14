package com.example.cryptokotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptokotlin.R
import com.example.cryptokotlin.adapter.RecyclerViewAdapter
import com.example.cryptokotlin.model.CryptoModel
import com.example.cryptokotlin.service.CryptoAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

// https://api.nomics.com/v1/prices?key=3eb0f7860bf8c2170288d6776d581a06
    //3eb0f7860bf8c2170288d6776d581a06
class MainActivity : AppCompatActivity() {

    private val API_KEY = "3eb0f7860bf8c2170288d6776d581a06"
    private val BASE_URL = "https://api.nomics.com/v1/"
    private var cryptoModels : ArrayList<CryptoModel>? = null
    private var compositeDisposable : CompositeDisposable?=null
    private var recyclerviewAdapter :RecyclerViewAdapter? = null
    lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        compositeDisposable = CompositeDisposable()
         recyclerView  = findViewById(R.id.recycler_view)

        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager


        loadData()

    }

    private fun loadData(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CryptoAPI::class.java)


        compositeDisposable?.add(retrofit.getData("3eb0f7860bf8c2170288d6776d581a06")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse, System.out::println))
//        compositeDisposable.add(service.getData()
//            .subscribeOn(Schedulers.io())
//            .observeOn(Android))


    }

    private fun handleResponse(cryptoList : List<CryptoModel> ){
        println("deneme")

        cryptoModels = ArrayList(cryptoList)
        for(crypto_model:CryptoModel in cryptoModels!!){
            println(crypto_model.currency)
            println(crypto_model.price)
        }

        recyclerviewAdapter = RecyclerViewAdapter(cryptoModels!!)
        recyclerView.adapter = recyclerviewAdapter
//        cryptoModels?.let { it:ArrayList<CryptoModel> }


    }

}