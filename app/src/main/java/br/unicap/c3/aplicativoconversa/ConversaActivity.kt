package br.unicap.c3.aplicativoconversa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ConversaActivity : AppCompatActivity() {

    private lateinit var conversaRecyclerView: RecyclerView
    private lateinit var caixaMensagem: EditText
    private lateinit var btnEnviar: ImageView
    private lateinit var mensagemAdapter: MensagemAdapter
    private lateinit var listaMensagens: ArrayList<Mensagem>
    private lateinit var mDbRef: DatabaseReference

    var receiverRoom: String? = null
    var senderRoom: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversa)

        val nome = intent.getStringExtra("nome")
        val receiverUid = intent.getStringExtra("uid")

        val senderUid = FirebaseAuth.getInstance().currentUser?.uid
        mDbRef = FirebaseDatabase.getInstance().getReference()

        senderRoom = receiverUid + senderUid
        receiverRoom = senderUid + receiverUid

        supportActionBar?.title = nome

        conversaRecyclerView = findViewById(R.id.conversa_recycler_view)
        caixaMensagem = findViewById(R.id.caixa_mensagem)
        btnEnviar = findViewById(R.id.btn_enviar)
        listaMensagens = ArrayList()
        mensagemAdapter = MensagemAdapter(this, listaMensagens)

        conversaRecyclerView.layoutManager = LinearLayoutManager(this)
        conversaRecyclerView.adapter = mensagemAdapter



        mDbRef.child("conversas").child(senderRoom!!).child("mensagens")
            .addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    listaMensagens.clear()
                    for (postSnapshot in snapshot.children) {
                        val mensagem = postSnapshot.getValue(Mensagem::class.java)
                        listaMensagens.add(mensagem!!)

                    }
                    mensagemAdapter.notifyDataSetChanged()
                }
                override fun onCancelled(error: DatabaseError) {

                }

                })

        btnEnviar.setOnClickListener {
            val mensagem = caixaMensagem.text.toString()
            val mensagemObject = Mensagem(mensagem, senderUid)

            mDbRef.child("conversas").child(senderRoom!!).child("mensagens").push()
                .setValue(mensagemObject).addOnSuccessListener {
                    mDbRef.child("conversas").child(receiverRoom!!).child("mensagens").push()
                        .setValue(mensagemObject)
                }
            caixaMensagem.setText("")
        }

    }
}