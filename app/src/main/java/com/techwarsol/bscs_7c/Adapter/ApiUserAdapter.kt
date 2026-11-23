package com.techwarsol.bscs_7c.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.techwarsol.bscs_7c.Model.ApiUser
import com.techwarsol.bscs_7c.R
import com.techwarsol.bscs_7c.databinding.ItemUserApiBinding

class ApiUserAdapter(private var userList: ArrayList<ApiUser>) :
    RecyclerView.Adapter<ApiUserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        val binding = ItemUserApiBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: UserViewHolder,
        position: Int
    ) {
        val user = userList[position]
        holder.binding.tvFullName.text = "${user.firstName} ${user.lastName}"
        holder.binding.tvJobTitle.text = user.company.title


        //Load Network Image
        Glide.with(holder.itemView.context)
            .load(user.image)
            .circleCrop()
            .placeholder(R.drawable.account)
            .error(R.drawable.logout)
            .into(holder.binding.ivUserAvatar)


    }

    override fun getItemCount(): Int {
        return userList.size
    }


    class UserViewHolder(val binding: ItemUserApiBinding) :
        RecyclerView.ViewHolder(binding.root)


}