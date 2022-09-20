package com.inditraining.datatraining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailFoodActivity : AppCompatActivity() {

    private lateinit var foodName : TextView
    private lateinit var foodPic : ImageView
    private lateinit var foodDesc : TextView
    private lateinit var foodPrice : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_food)

        val foodDetail = intent.getParcelableExtra<Foods>(MainActivity.ITEM_KEY)
        foodName = findViewById(R.id.food_detail_name_tv)
        foodDesc = findViewById(R.id.food_detail_desc_tv)
        foodPrice = findViewById(R.id.food_detail_price_tv)
        foodPic = findViewById(R.id.food_detail_photo_iv)
        foodPic.setImageResource(foodDetail!!.pic)
        foodName.text = foodDetail.name
        foodPrice.text = foodDetail.price
    }
}