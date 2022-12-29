package com.example.firebasetry3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    val shoppinglist = mutableListOf<ShoppingItems>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Firebase.firestore


        /*   val item1 = ShoppingItems("gurka",false,"grönsak")
        val item2 = ShoppingItems("tomat",false,"grönsak")
        val item3 = ShoppingItems("ost",false,"mejeri")

        //db.collection("item").add(item1)
        db.collection("item").add(item3)
  db.collection("item").add(item2) */

        val docRef = db.collection("item")

        /*   docRef.get().addOnSuccessListener {

            documentSnapShot ->
            for (document in documentSnapShot.documents){
                val item = document.toObject<ShoppingItems>()

                if (item != null){
                    shoppinglist.add(item)
                }

            }
            printShoppingItems()
        }
*/
        docRef.addSnapshotListener { snapshot, e ->
            if (snapshot != null) {
                shoppinglist.clear()
                for (document in snapshot.documents) {
                    val item = document.toObject<ShoppingItems>()

                    if (item != null) {
                        shoppinglist.add(item)
                    }

                }


            }
            printShoppingItems()
        }

    }

    fun printShoppingItems() {

        for (item in shoppinglist){
            Log.d("###", "${item.name}")
        }

    }


}