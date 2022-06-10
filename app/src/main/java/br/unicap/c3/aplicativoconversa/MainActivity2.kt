package br.unicap.c3.aplicativoconversa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity2 : AppCompatActivity() {

    private lateinit var btnEderson: Button
    private lateinit var btnIgor: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        btnEderson = findViewById(R.id.btn_ederson)
        btnIgor = findViewById(R.id.btn_igor)

        btnEderson.setOnClickListener{
            val intent = Intent(this, EderActivity::class.java)
            startActivity(intent)
        }

        btnIgor.setOnClickListener{
            val intent = Intent(this, IgorActivity::class.java)
            startActivity(intent)
        }

    }
}