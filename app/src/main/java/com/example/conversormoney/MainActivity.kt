package com.example.conversormoney

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.time.times

class MainActivity : AppCompatActivity() {

    val monedas = mapOf(
        "dolar" to 100.2,
        "yen" to 50.1,
        "libra" to 20.0,
        "real" to 10.0,
        "euro" to 150.8
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val idBtnConvertir = findViewById<Button>(R.id.idBtnConvertir)


        idBtnConvertir.setOnClickListener{
            val idSelectMoneda = findViewById<Spinner>(R.id.idSelectMoneda).selectedItem.toString()
            val cantidadMoneda = findViewById<EditText>(R.id.idCantidadMoneda).text.toString()
            val idSelectPasar = findViewById<Spinner>(R.id.idSelectPasar).selectedItem.toString()
            val idResult = findViewById<TextView>(R.id.idResult)

            if (cantidadMoneda > 0) {
                val total = convertirMonedas(idSelectMoneda, idSelectPasar, cantidadMoneda.toDoubleOrNull()?:0.0)
                idResult.text = "La cantidad de $cantidadMoneda $idSelectMoneda en $idSelectPasar son $total"
            } else {
                idResult.setTextColor(Color.RED)
                idResult.text = "No ingresaste un monto válido a convertir."
            }

        }
    }



    fun convertirMonedas(convertir:String, cantidad:Double):Double {
        var resultado: Double = 0.0

        when(convertir){
            in "Dolar" -> resultado = cantidad * monedas["dolar"]!!
            in "Yen" -> resultado = cantidad * monedas["yen"]!!
            in "Libra" -> resultado = cantidad * monedas["libra"]!!
            in "Real" -> resultado = cantidad * monedas["real"]!!
            in "Euro" -> resultado = cantidad * monedas["euro"]!!
        }
        return resultado
    }
}