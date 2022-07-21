package br.com.exerciciofirebase.domain.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.ktx.Firebase

class RepositoryMsg {
    private val autent: FirebaseAuth = Firebase.auth
    private val dataBase = FirebaseDatabase.getInstance()
    private val reference = dataBase.getReference("firebase66/${autent.currentUser?.uid}/mensagem")

    fun dataBaseReference() = reference
    fun getListMsg(): Query {
        return reference.orderByValue()
    }
}