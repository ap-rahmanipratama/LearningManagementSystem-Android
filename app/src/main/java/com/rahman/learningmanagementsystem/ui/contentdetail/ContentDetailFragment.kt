package com.rahman.learningmanagementsystem.ui.contentdetail

import android.content.pm.ActivityInfo
import android.view.View
import android.widget.ImageButton
import android.widget.MediaController
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rahman.learningmanagementsystem.MainActivity
import com.rahman.learningmanagementsystem.R
import com.rahman.learningmanagementsystem.base.BaseFragment
import com.rahman.learningmanagementsystem.databinding.FragmentContentDetailBinding
import com.rahman.learningmanagementsystem.helpers.viewBinding

class ContentDetailFragment: BaseFragment(R.layout.fragment_content_detail) {

    private val binding by viewBinding(FragmentContentDetailBinding::bind)

    private val args: ContentDetailFragmentArgs by navArgs()

    private lateinit var player: ExoPlayer
    private var isFullscreen = false

    override fun initComponent() {
        super.initComponent()

        player = ExoPlayer.Builder(requireContext()).build()
        binding.videoView.player = player
        val mediaController = MediaController(this.requireContext())
        mediaController.setAnchorView(binding.videoView)
        val mediaItem = MediaItem.fromUri(args.viewdata.videoURL)
        player.setMediaItem(mediaItem)
        player.prepare()

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

        binding.exoFullscreenButton.setOnClickListener {
            toggleFullscreen()
        }
    }


    private fun toggleFullscreen() {
        if (isFullscreen) {
            requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
            (requireActivity() as MainActivity).supportActionBar?.show()
            requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            binding.toolbar.visibility = View.VISIBLE
        } else {
            // Enter fullscreen logic (e.g., hide status bar)
            requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            (requireActivity() as MainActivity).supportActionBar?.hide()
            requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            binding.toolbar.visibility = View.GONE
        }
        isFullscreen = !isFullscreen
    }

    override fun onStop() {
        super.onStop()
        player.release()
    }
}