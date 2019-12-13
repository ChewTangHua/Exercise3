package com.example.exercise3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var myData: PremiumModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myData = ViewModelProviders.of(this).get(PremiumModel::class.java)

        display()

        buttonCalculate.setOnClickListener{
            var ageSelected: Int = spinnerAge.selectedItemPosition
            var radioId: Int = radioGroupGender.checkedRadioButtonId
            val radioBtnChosen: RadioButton = findViewById(radioId)
            var smoker: Int = 0;

            if (checkBoxSmoker.isChecked()) {
                smoker = 1;
            }

            var premiumAmount = Calculate(ageSelected,radioBtnChosen,smoker)

            myData.premiumAmount = Calculate(ageSelected,radioBtnChosen,smoker)
            display()

            textViewPremium.setText("Insurance Premium : RM" + String.format("%.2f", premiumAmount))
        }

        buttonReset.setOnClickListener{
            spinnerAge.setSelection(0)
            radioGroupGender.clearCheck()
            checkBoxSmoker.setChecked(false)
            textViewPremium.setText("Insurance Premium :")
            myData.premiumAmount = 0.0
        }
    }

    fun Calculate(age : Int, radio: RadioButton, smoke : Int) : Double{
        var Premium = arrayOf<Double>(60.00, 70.00, 90.00, 120.00, 150.00, 150.00)
        var insurance : Double = Premium[age]

        if(age == 0){
            return insurance
        }else if(age == 1 && radio == radioButtonMale) {
            insurance += 50
        }else if(age == 2 && radio == radioButtonMale) {
            insurance += 100
        }else if(age == 3 && radio == radioButtonMale) {
            insurance += 150
        }else if(age == 4 && radio == radioButtonMale) {
            insurance += 200
        }else if(age == 5 && radio == radioButtonMale) {
            insurance += 200
        }

        if(age == 1 && smoke == 1) {
            insurance += 100
        }else if(age == 2 && smoke == 1) {
            insurance += 150
        }else if(age == 3 && smoke == 1) {
            insurance += 200
        }else if(age == 4 && smoke == 1) {
            insurance += 250
        }else if(age == 5 && smoke == 1) {
            insurance += 300
        }

        return insurance
    }

    fun display(){
        if(myData.premiumAmount != 0.0)
            textViewPremium.text = "Insurance Premium : RM" + myData.premiumAmount.toString()
    }
}
