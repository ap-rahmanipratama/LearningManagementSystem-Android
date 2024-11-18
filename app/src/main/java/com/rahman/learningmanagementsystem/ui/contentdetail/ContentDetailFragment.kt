package com.rahman.learningmanagementsystem.ui.contentdetail

import android.net.Uri
import android.widget.MediaController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rahman.learningmanagementsystem.R
import com.rahman.learningmanagementsystem.base.BaseFragment
import com.rahman.learningmanagementsystem.databinding.FragmentContentDetailBinding
import com.rahman.learningmanagementsystem.helpers.viewBinding
import java.net.URL

class ContentDetailFragment: BaseFragment(R.layout.fragment_content_detail) {

    private val binding by viewBinding(FragmentContentDetailBinding::bind)

    private val args: ContentDetailFragmentArgs by navArgs()


    override fun initComponent() {
        super.initComponent()

        val mediaController = MediaController(this.requireContext())
        mediaController.setAnchorView(binding.videoView)
        binding.videoView.setVideoURI(Uri.parse(args.viewdata.videoURL))
        binding.videoView.setMediaController(mediaController)
        binding.author.text = args.viewdata.presenterName
        binding.title.text  = args.viewdata.title
        binding.textDescription.text = args.viewdata.description
        binding.textDuration.text = args.viewdata.videoDuration.toString()
    }

    override fun initEventListener() {
        super.initEventListener()

        binding.toolbar.onBackButtonClick {
            findNavController().popBackStack()
        }
    }
}