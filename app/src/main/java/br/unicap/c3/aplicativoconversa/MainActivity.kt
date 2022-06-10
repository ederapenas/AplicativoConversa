package br.unicap.c3.aplicativoconversa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var usuarioRecyclerView: RecyclerView
    private lateinit var listaUsuario: ArrayList<Usuario>
    private lateinit var adapter: UsuarioAdapter
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        mDbRef = FirebaseDatabase.getInstance().getReference()

        listaUsuario = ArrayList()
        adapter = UsuarioAdapter(this, listaUsuario)

        usuarioRecyclerView = findViewById(R.id.usuario_recycler_view)

        usuarioRecyclerView.layoutManager = LinearLayoutManager(this)
        usuarioRecyclerView.adapter = adapter

        mDbRef.child("usuario").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                listaUsuario.clear()
                for(postSnapshot in snapshot.children){
                    val usuarioAtual = postSnapshot.getValue(Usuario::class.java)
                    if(mAuth.currentUser?.uid != usuarioAtual?.uid){
                        listaUsuario.add(usuarioAtual!!)
                    }
                }
                adapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.logout){
            mAuth.signOut()
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            finish()
            startActivity(intent)
            return true;
        }
        return true
    }
}