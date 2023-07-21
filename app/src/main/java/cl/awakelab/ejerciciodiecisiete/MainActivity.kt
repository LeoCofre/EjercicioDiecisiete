package cl.awakelab.ejerciciodiecisiete

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import cl.awakelab.ejerciciodiecisiete.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val divisas = listOf("Dolar", "Pesos", "Euro")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.spinnerUno.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, divisas)
        binding.spinnerDos.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, divisas)

        initListener()
    }

    private fun initListener() {
        binding.buttonConvert.setOnClickListener {
            val monto = binding.tvEntrada.text.toString().toDouble()
            val divisaActual = binding.spinnerUno.selectedItem.toString()
            val divisaCambio = binding.spinnerDos.selectedItem.toString()
            Log.d("Estamos en el InitListener", "$monto $divisaActual $divisaCambio")
            val resultado = conversorDivisas(monto, divisaActual, divisaCambio)
            binding.tvResultado.text = resultado.toString()

        }
        binding.buttonReset.setOnClickListener {
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
                resultado = monto * 0.001
            } else if (divisaCambio == "Euro") {
                resultado = monto * 0.001
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

        binding.tvResultado.text = ""
        binding.tvEntrada.setText("")

    }
}
