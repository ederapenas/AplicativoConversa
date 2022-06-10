package br.unicap.c3.aplicativoconversa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class EderActivity : AppCompatActivity() {

    lateinit var btnSocorro: Button
    lateinit var tvNumero: TextView
    var numero: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eder)

        btnSocorro = findViewById(R.id.btn_socorro)
        tvNumero = findViewById(R.id.tv_numero)

        btnSocorro.setOnClickListener{
            numero++
            tvNumero.text = numero.toString()
        }
    }
}