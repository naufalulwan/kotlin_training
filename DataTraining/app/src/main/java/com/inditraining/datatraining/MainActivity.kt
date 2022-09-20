package com.inditraining.datatraining

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvFoods : RecyclerView

    companion object {
        const val ITEM_KEY = "INTENT_ITEM"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvFoods = findViewById(R.id.rv_food)
        rvFoods.setHasFixedSize(true)

        val foodsData = addDataList()
        showRecyclerView(foodsData)
    }

    private fun showRecyclerView(foodsData: ArrayList<Foods>) {
        val rvAdapter = FoodsAdapter(foodsData)
        rvFoods.layoutManager = LinearLayoutManager(this)
        rvFoods.adapter = rvAdapter

        rvAdapter.setOnItemClickCallBack(object : FoodsAdapter.OnItemClickCallBack {
            override fun onItemClicked(data: Foods) {
                Toast.makeText(this@MainActivity, "Kamu telah memesan makanan : ${data.name}, " +
                        "seharga ${data.price}",
                    Toast.LENGTH_LONG)
                    .show()

                val intentToDetail = Intent(this@MainActivity, DetailFoodActivity::class.java)
                intentToDetail.putExtra(ITEM_KEY,data)
                startActivity(intentToDetail)

            }
        })
    }

    @SuppressLint("Recycle")
    private fun addDataList(): ArrayList<Foods> {
        val listFoods = ArrayList<Foods>()
        val listFoodName = resources.getStringArray(R.array.data_name)
        val listFoodPrice = resources.getStringArray(R.array.data_price)
        val listFoodPic = resources.obtainTypedArray(R.array.data_pic)

        for (i in listFoodName.indices){
            listFoods.add(
                Foods(
                    listFoodName[i],
                    listFoodPrice[i],
                    listFoodPic.getResourceId(i, -1)
                )
            )
        }
        return listFoods
    }

}