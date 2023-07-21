package com.example.ejer17cambiodivisa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.ejer17cambiodivisa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val divisas = listOf("Dolar", "Pesos", "Euro")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.actualDivisaS.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, divisas)
        binding.canvioDivisaS.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, divisas)

        initListener()
    }

    private fun initListener() {
        binding.conversionB.setOnClickListener {
            val monto = binding.montoTxt.text.toString().toDouble()
            val divisaActual = binding.actualDivisaS.selectedItem.toString()
            val divisaCambio = binding.canvioDivisaS.selectedItem.toString()
            val resultado = conversorDivisas(monto, divisaActual, divisaCambio)
            binding.mostrarResultsdo.text = resultado.toString()

        }
        binding.resetB.setOnClickListener {
            limpiar()
        }
    }

    fun conversorDivisas(monto: Double, divisaActual: String, divisaCambio: String): Double {
        var resultado = monto
        when (divisaActual) {
            "Dolar" -> {
                if (divisaCambio == "Pesos") {
                    resultado = monto * 817

                } else if (divisaCambio == "Dolar") {
                    resultado = monto * 1
                } else if (divisaCambio == "Euro") {
                    resultado = monto * 0.89
                }
            }

            "Pesos" -> if (divisaCambio == "Pesos") {
                resultado = monto * 1

            } else if (divisaCambio == "Dolar") {
                resultado = monto / 817
            } else if (divisaCambio == "Euro") {
                resultado = monto / 910
            }

            "Euro" -> if (divisaCambio == "Pesos") {
                resultado = monto * 910
            } else if (divisaCambio == "Dolar") {
                resultado = monto * 1.11
            } else if (divisaCambio == "Euro") {
                resultado = monto * 1
            }

            else -> {
                resultado = monto
            }
        }
        return resultado
    }

    fun limpiar() {

        binding.mostrarResultsdo.text = "0"
        binding.montoTxt.setText("")

    }
}