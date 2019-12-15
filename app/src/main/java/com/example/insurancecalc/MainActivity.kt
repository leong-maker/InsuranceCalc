package com.example.insurancecalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var myData: ViewModelInsurans



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myData = ViewModelProviders.of(this).get(ViewModelInsurans::class.java)
        display()



        btncalc.setOnClickListener() {

            if (radmale.isChecked  ||  radmale.isChecked) {
                myData.premiumAmount = getPremium()
                txttotalcalc.setText(String.format("RM %.2f ", myData.premiumAmount))
            } else {
                val toast:Toast = Toast.makeText(this, "invalid input", Toast.LENGTH_LONG )
                toast.setGravity(Gravity.CENTER, 0,0)
                toast.show()
            }

        }

        fun onRadioButtonClicked(view: View) {
            if (view is RadioButton) {
                // Is the button now checked?
                val checked = view.isChecked

                // Check which radio button was clicked
                when (view.getId()) {
                    R.id.radmale ->
                        if (checked) {
                            // Pirates are the best
                        }
                    R.id.radfemale ->
                        if (checked) {
                            // Ninjas rule
                        }
                }
            }
        }


        btnreset.setOnClickListener() {
            spinner.setSelection(0)
            chkbox.setChecked(false)
            radmale.setChecked(false)
            radfemale.setChecked(false)
            myData.premiumAmount = 0.0
        }
    }

    fun display(){
           txttotalcalc.setText(String.format("RM %.2f ", myData.premiumAmount))
        }

    fun getPremium(): Double{
        return when (spinner.selectedItemPosition){
            0 -> 60.00
            1 -> 70.00 + (if (radmale.isChecked) 50.00 else 0.0) + (if (chkbox.isChecked) 100.00 else 0.0)
            2 -> 90.00 + (if (radmale.isChecked) 100.00 else 0.0) + (if (chkbox.isChecked) 150.00 else 0.0)
            3 -> 120.00 + (if (radmale.isChecked) 150.00 else 0.0) + (if(chkbox.isChecked )200.00 else 0.0)
            4 -> 150.00 + (if (radmale.isChecked) 200.00 else 0.0) + (if(chkbox.isChecked )250.00 else 0.0)
            else -> 150.00 + (if (radmale.isChecked) 200.00 else 0.0) + (if(chkbox.isChecked )300.00 else 0.0)
        }
    }
}
