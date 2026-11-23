package com.techwarsol.bscs_7c

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.techwarsol.bscs_7c.Adapter.ProductAdapter
import com.techwarsol.bscs_7c.Model.Product
import com.techwarsol.bscs_7c.databinding.ActivityShowProductBinding

class ShowProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowProductBinding
    private lateinit var database: DatabaseReference
    private lateinit var productAdapter: ProductAdapter
    private val productList = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_product)

        binding = ActivityShowProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchProductsFromFirebase()


    }

    private fun fetchProductsFromFirebase() {
         database = FirebaseDatabase.getInstance().getReference("Product")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                productList.clear()
                for (productSnapShot in snapshot.children){
                    val product = productSnapShot.getValue(Product::class.java)
                    if (product!=null){
                        productList.add(product)
                    }
                }
                binding.pbProductsLoader.visibility = View.GONE
                productAdapter = ProductAdapter(productList)
                binding.rvProducts.adapter = productAdapter

            }

            override fun onCancelled(error: DatabaseError) {
                binding.pbProductsLoader.visibility = View.GONE
                Toast.makeText(this@ShowProductActivity , "Failed To Load Data" , Toast.LENGTH_SHORT).show()
            }
        })
    }
}