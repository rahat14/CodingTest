package com.rahat.gtaf.codingtest.ui.commitList

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahat.gtaf.codingtest.R
import com.rahat.gtaf.codingtest.adapters.CommitAdapter
import com.rahat.gtaf.codingtest.databinding.FragmentCommitListBinding
import com.rahat.gtaf.codingtest.models.CommitModel
import com.rahat.gtaf.codingtest.utils.Const
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommitListFragment : Fragment(R.layout.fragment_commit_list), CommitAdapter.Interaction {
    private lateinit var binding: FragmentCommitListBinding
    private val viewModel: CommitViewModel by viewModels()

    private lateinit var mAdapter: CommitAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentCommitListBinding.bind(view)
        mAdapter = CommitAdapter(this)
        setupView()
        callForData()
    }

    private fun setupView() {
        binding.commitList.apply {
            setHasFixedSize(true)
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun callForData() {

        // making this method dynamic
        //rather than init the method on viewmodel and observing it for more live data
        viewModel.getCommitList(Const.REPO_OWNER, Const.REPO_NAME, Const.PER_PAGE)
            .observe(viewLifecycleOwner) {
                if (it.first != null) {
                    val list = it.first
                    list?.let { it1 -> mAdapter.submitList(it1) }
                } else {
                    Toast.makeText(context, "Error : ${it.second?.message}", Toast.LENGTH_LONG)
                        .show()
                }
            }


    }

    override fun onItemSelected(position: Int, item: CommitModel) {

    }
}