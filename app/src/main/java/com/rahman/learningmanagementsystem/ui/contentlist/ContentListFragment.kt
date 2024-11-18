package com.rahman.learningmanagementsystem.ui.contentlist

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahman.learningmanagementsystem.R
import com.rahman.learningmanagementsystem.base.BaseFragment
import com.rahman.learningmanagementsystem.databinding.FragmentContentListBinding
import com.rahman.learningmanagementsystem.helpers.EventObserver
import com.rahman.learningmanagementsystem.helpers.viewBinding
import com.rahman.learningmanagementsystem.ui.ContentListViewModel
import com.rahman.learningmanagementsystem.ui.contentlist.adapter.CoursesListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContentListFragment: BaseFragment(R.layout.fragment_content_list) {

    private val binding by viewBinding(FragmentContentListBinding::bind)

    private val viewModel: ContentListViewModel by viewModels()

    private val adapter: CoursesListAdapter by lazy {
        CoursesListAdapter(requireContext())
    }

    override fun initComponent() {
        super.initComponent()

        binding.coursesRecyclerView.setAdapter(adapter)
        binding.coursesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }


    override fun initObserver() {
        super.initObserver()
        viewModel.liveGetContentSuccess.observe(
            viewLifecycleOwner,
            EventObserver { eventData ->
                eventData.content?.let {
                    adapter.reloadData(it)
                }
            })
    }

    override fun loadData() {
        super.loadData()
        viewModel.getList()
    }
}