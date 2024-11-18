package com.rahman.learningmanagementsystem.ui.contentlist.adapter

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.rahman.learningmanagementsystem.R
import com.rahman.learningmanagementsystem.client.dto.ContentListResponse
import com.rahman.learningmanagementsystem.databinding.CoursesListItemBinding


internal class CoursesListAdapter(private val context: Context): RecyclerView.Adapter<CoursesListAdapter.CoursesListViewHolder>() {

    private val datas: ArrayList<ContentListResponse> = ArrayList<ContentListResponse>()
    private var onItemClickListener: OnItemClickListener? = null

    inner class CoursesListViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = CoursesListItemBinding.bind(view)
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, item: ContentListResponse)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CoursesListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.courses_list_item, parent, false)
        return CoursesListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoursesListViewHolder, position: Int) {
        val data = datas[position]
        holder.binding.author.text = data.presenterName
        holder.binding.title.text = data.title
        holder.binding.textDescription.text = data.description
        holder.binding.textDuration.text = formatDuration(data.videoDuration)

        Glide.with(context)
            .load(data.thumbnailURL)
            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE).fitCenter().placeholder(R.color.white))
            .into(holder.binding.imageThumbnail)

        holder.binding.container.setOnClickListener() {
            onItemClickListener?.onItemClick(it, datas[position])
        }
    }

    fun formatDuration(duration: Int): String {
        val minutes = duration / 1000 / 60
        val remainingSeconds = duration / 1000 % 60
        return String.format("%d:%02d", minutes, remainingSeconds)
    }

    override fun getItemCount(): Int = datas.size

    fun reloadData(paramData: List<ContentListResponse>){
        datas.clear()
        datas.addAll(paramData)
        notifyDataSetChanged()
    }

    fun reloadDataWithoutNotify(paramData: List<ContentListResponse>){
        datas.clear()
        datas.addAll(paramData)
    }
}
