package com.pembelajar.latihanroom

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pembelajar.latihanroom.ViewModel.WordViewModel
import com.pembelajar.latihanroom.adapter.WordAdapter
import com.pembelajar.latihanroom.databinding.ActivityMainBinding
import com.pembelajar.latihanroom.model.Word

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        val adapter = WordAdapter(this)
        binding.rvMain.adapter = adapter
        binding.rvMain.layoutManager = LinearLayoutManager(this)

        viewModel.allData.observe(this, Observer { word ->
            word?.let { adapter.setWord(it) }
        })

        binding.buttonAdd.setOnClickListener(this)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewDataActivity.EXTRA_DATA)?.let {
                val word = Word(it)
                viewModel.insert(word)
                Unit
            }
        } else {
            Toast.makeText(this, "Data Gagal Tersimpan", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(view: View?) {
        if(view?.id == R.id.button_add){
            val intent = Intent(this, NewDataActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }
}
