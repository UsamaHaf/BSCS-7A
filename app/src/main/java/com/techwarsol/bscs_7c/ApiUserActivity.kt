package com.techwarsol.bscs_7c

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.techwarsol.bscs_7c.Adapter.ApiUserAdapter
import com.techwarsol.bscs_7c.Interfaces.RetrofitClient
import com.techwarsol.bscs_7c.Model.ApiUser
import com.techwarsol.bscs_7c.Model.UserResponse
import com.techwarsol.bscs_7c.databinding.ActivityApiUserBinding
import com.techwarsol.bscs_7c.databinding.ActivityRegistrationBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiUserActivity : AppCompatActivity() {

    lateinit var binding: ActivityApiUserBinding

    var userList = ArrayList<ApiUser>()
    lateinit var userAdapter: ApiUserAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api_user)

        binding = ActivityApiUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchUser()
        userAdapter = ApiUserAdapter(userList)
        binding.rvAPIUser.adapter = userAdapter

    }

    private fun fetchUser() {
        val call = RetrofitClient.apiService.getUsers()

        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if(response.isSuccessful && response.body() != null){
                    userList.clear()
                    userList.addAll(response.body()!!.users)
                    userAdapter.notifyDataSetChanged()

                }else{
                    Toast.makeText(this@ApiUserActivity, "Response: ${response.code()}" , Toast.LENGTH_SHORT).show()

                }
            }

            override fun onFailure(p0: Call<UserResponse?>, p1: Throwable) {
                Toast.makeText(this@ApiUserActivity, "Response Failed" , Toast.LENGTH_SHORT).show()

            }
        })
    }
}