package com.example.tipcalculator
//Grzegorz Salzburg 252912

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private const val INITIAL_TIP = 5

class MainActivity : AppCompatActivity() {

    private lateinit var edittextvar: EditText
    private lateinit var seekbartip: SeekBar
    private lateinit var tipviewamount: TextView
    private lateinit var procentview: TextView
    private lateinit var totalviewamount: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edittextvar = findViewById(R.id.edit_text)
        seekbartip = findViewById(R.id.seekBarTip)
        tipviewamount = findViewById(R.id.tip_view_amount)
        procentview = findViewById(R.id.procent_view)
        totalviewamount = findViewById(R.id.total_view_amount)

        seekbartip.progress = INITIAL_TIP
        procentview.text = "$INITIAL_TIP%"

        seekbartip.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                procentview.text = "$p1%"
              tipandtotal()

            }
            override fun onStartTrackingTouch(p0: SeekBar?) {

            }
            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

        edittextvar.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
              tipandtotal()
            }

        })
    }

    @SuppressLint("SetTextI18n")
    private fun tipandtotal() {
        if(edittextvar.text.toString().isEmpty())
        {
            tipviewamount.text =""
            totalviewamount.text=""
            return
        }

        val baseAmount = edittextvar.text.toString().toDouble()
        val tipPercent = seekbartip.progress

        val tipAmount = baseAmount * tipPercent.toDouble() / 100
        val totalAmount = baseAmount + tipAmount

        tipviewamount.text = "%.2f".format(tipAmount)
        totalviewamount.text = "%.2f".format(totalAmount)
    }
}