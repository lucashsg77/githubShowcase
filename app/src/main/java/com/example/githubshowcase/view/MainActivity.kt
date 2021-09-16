package com.example.githubshowcase.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.githubshowcase.R
import com.example.githubshowcase.databinding.ActivityMainBinding
import com.example.githubshowcase.utils.createDialog
import com.example.githubshowcase.utils.createProgressDialog
import com.example.githubshowcase.utils.hideKeyboard
import com.example.githubshowcase.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() , SearchView.OnQueryTextListener {

    private val dialog by lazy { createProgressDialog() }
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter by lazy { RepoListAdapter() }
    private val viewModel by viewModel<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.repoTitleToolbar)
        binding.repoRv.adapter = adapter
        setUpDataObserver()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let{ viewModel.repoList(it) }
        hideKeyboard()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.e("TAG", "onQueryTextChange: $newText")
        return false
    }

    private fun setUpDataObserver() {
        viewModel.repos.observe(this) {
            when (it) {
                MainViewModel.State.Loading -> dialog.show()
                is MainViewModel.State.Error -> {
                    createDialog {
                        setMessage(it.error.message)
                    }.show()
                    dialog.dismiss()
                }
                is MainViewModel.State.Success -> {
                    dialog.dismiss()
                    adapter.submitList(it.list)
                }
            }
        }
    }
}