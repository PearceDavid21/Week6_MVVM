package com.example.week6_daily2_mvvm.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week6_daily2_mvvm.R
import com.example.week6_daily2_mvvm.adapter.CommitAdapter
import com.example.week6_daily2_mvvm.model.Commits
import com.example.week6_daily2_mvvm.viewmodel.GitViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.repo_fragment_layout.*

class RepoFragment : Fragment() {

    private val compositeDisposable = CompositeDisposable()

    private lateinit var viewModel: GitViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.repo_fragment_layout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GitViewModel::class.java)

        arguments?.let {
            title_repo_textview.text = it.getString("repositoryName")
            compositeDisposable.add(
                viewModel.getRepoCommits
                    ("ShamarcusW-MAC", it.getString("repositoryName") ?: "")
                    .subscribe({
                        displayResults(it)

                    }, { throwable ->
                        Log.e("TAG_ERROR", throwable.message)

                    })
            )

        }
    }

    private fun displayResults(commitList: List<Commits>) {
        commit_recyclerview.adapter = CommitAdapter(commitList)
        commit_recyclerview.layoutManager = LinearLayoutManager(activity)
    }
}