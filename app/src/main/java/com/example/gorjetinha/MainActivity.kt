package com.example.gorjetinha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gorjetinha.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateBtn.setOnClickListener { calculateTip() }
    }

    private fun calculateTip(){
        val costOfTip = binding.costOfService.text.toString()
        val cost = costOfTip.toDoubleOrNull()

        val percent = when (binding.tipOptions.checkedRadioButtonId){
            R.id.tip_choise_1 -> 0.20
            R.id.tip_choise_2 -> 0.18
            R.id.tip_choise_3 -> 0.15
            R.id.tip_choise_4 -> 0.10
            else -> 0.00
        }
        if(cost == null || cost == 0.0){
            formatAndShowTip(0.0)
            return
        }
        var tip = percent * cost

        if (binding.switchChoise.isChecked) { tip = kotlin.math.ceil(tip)}

        formatAndShowTip(tip)
    }

    private fun formatAndShowTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipAmountFinalCost.text = getString(R.string.tip_amount, formattedTip)
    }
}