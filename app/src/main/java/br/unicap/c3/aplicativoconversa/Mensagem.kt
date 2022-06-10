package br.unicap.c3.aplicativoconversa

class Mensagem {
    var mensagem: String? = null
    var senderId: String? = null

    constructor(){}

    constructor(mensagem: String?, senderId: String?){
        this.mensagem = mensagem
        this.senderId = senderId
    }

}