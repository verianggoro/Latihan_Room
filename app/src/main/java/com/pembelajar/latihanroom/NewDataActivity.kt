package com.pembelajar.latihanroom

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.databinding.DataBindingUtil
import com.pembelajar.latihanroom.databinding.ActivityNewDataBinding

class NewDataActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityNewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_data)

        binding.saveData.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        if(view?.id == R.id.save_data){
            val resultActivity = Intent()
            if(TextUtils.isEmpty(binding.inputData.text)){
                setResult(Activity.RESULT_CANCELED, resultActivity)
            }else{
                val data = binding.inputData.text.toString()
                resultActivity.putExtra(EXTRA_DATA, data)
                setResult(Activity.RESULT_OK, resultActivity)
            }
            finish()
        }
    }

    companion object{
        const val EXTRA_DATA = "data"
    }
}
