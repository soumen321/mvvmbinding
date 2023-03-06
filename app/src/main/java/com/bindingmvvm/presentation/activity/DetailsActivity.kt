package com.bindingmvvm.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bindingmvvm.R
import com.bindingmvvm.databinding.ActivityDetailsBinding
import com.dogmvvm.utility.ImageUtil
import com.squareup.picasso.Picasso

class DetailsActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpToolbar()
        setUpDetails()
    }

    private fun setUpToolbar(){
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.txt_details)
    }

    private fun setUpDetails(){
        val info = intent?.getBundleExtra("DETAILS") ?: Bundle()
        if(info.isEmpty){
            finish()
            return
        }
        binding.apply {
            Picasso.get().load(info.getString("image")).placeholder(ImageUtil.getPlaceholder()).into(binding.ivImage)

            tvTitle.text = info.getString("title")
            tvDescription.text = getString(R.string.txt_description)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}