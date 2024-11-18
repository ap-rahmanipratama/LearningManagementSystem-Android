package com.rahman.learningmanagementsystem.ui.contentlist

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahman.learningmanagementsystem.R
import com.rahman.learningmanagementsystem.base.BaseFragment
import com.rahman.learningmanagementsystem.base.Cache
import com.rahman.learningmanagementsystem.client.dto.ContentListResponse
import com.rahman.learningmanagementsystem.databinding.FragmentContentListBinding
import com.rahman.learningmanagementsystem.helpers.EventObserver
import com.rahman.learningmanagementsystem.helpers.viewBinding
import com.rahman.learningmanagementsystem.ui.ContentListViewModel
import com.rahman.learningmanagementsystem.ui.contentdetail.ContentDetailFragment
import com.rahman.learningmanagementsystem.ui.contentlist.adapter.CoursesListAdapter
import com.rahman.learningmanagementsystem.ui.viewdata.ContentViewData
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ContentListFragment: BaseFragment(R.layout.fragment_content_list) {

    private val binding by viewBinding(FragmentContentListBinding::bind)

    private val viewModel: ContentListViewModel by viewModels()

    @Inject lateinit var cache: Cache

    private val adapter: CoursesListAdapter by lazy {
        CoursesListAdapter(requireContext())
    }

    override fun initComponent() {
        super.initComponent()

        binding.coursesRecyclerView.setAdapter(adapter)
        binding.coursesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun initEventListener() {
        super.initEventListener()

        adapter.setOnItemClickListener(object : CoursesListAdapter.OnItemClickListener {
            override fun onItemClick(view: View, item: ContentViewData) {
                val action = ContentListFragmentDirections.actionContentListFragmentToContentDetailFragment(item)
                findNavController().navigate(action)
            }
        })

    }


    override fun initObserver() {
        super.initObserver()
        viewModel.liveGetContentSuccess.observe(
            viewLifecycleOwner,
            EventObserver { eventData ->
                eventData.content?.let {
                    cache.contentList = it
                    adapter.reloadData(it)
                    Log.d("cache saved", cache.contentList.toString())
                }
            })

        viewModel.liveGetContentFailed.observe(
            viewLifecycleOwner,
            EventObserver { eventData ->
                eventData.content?.let {
                    Log.d("cache", cache.contentList.toString())
                    adapter.reloadData(cache.contentList)
                }
            })
    }

    override fun loadData() {
        super.loadData()
        viewModel.getList()
    }
}