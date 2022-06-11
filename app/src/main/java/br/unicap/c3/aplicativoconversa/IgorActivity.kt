package br.unicap.c3.aplicativoconversa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class IgorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_igor)
        val abraco = findViewById<View>(R.id.btn_igorabraco)

        abraco.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "obrigado man :))", Toast.LENGTH_SHORT).show()
        })
    }
}