package kr.co.devstory.mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.co.devstory.mvvm.R
import kr.co.devstory.mvvm.databinding.ItemUserBinding
import kr.co.devstory.mvvm.model.data.UserResponse

class MainAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class VH(val b: ItemUserBinding): RecyclerView.ViewHolder(b.root)

    private var userList = ArrayList<UserResponse.User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val b: ItemUserBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_user,
            parent,
            false)
        return VH(b)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as VH).b.user = userList[position]
    }

    override fun getItemCount(): Int = userList.size


    fun setItems(userList: ArrayList<UserResponse.User>) {
        this.userList = userList
        notifyDataSetChanged()
    }
}