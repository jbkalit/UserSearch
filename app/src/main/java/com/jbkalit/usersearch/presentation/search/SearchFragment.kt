package com.jbkalit.usersearch.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jbkalit.usersearch.R
import com.jbkalit.usersearch.databinding.FragmentSearchBinding
import com.jbkalit.usersearch.presentation.ext.setGone
import com.jbkalit.usersearch.presentation.ext.setVisible
import com.jbkalit.usersearch.presentation.ext.showIf
import com.jbkalit.usersearch.presentation.ext.showSnackbar
import com.jbkalit.usersearch.presentation.search.adapter.SearchAdapter
import com.jbkalit.usersearch.presentation.search.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*

@AndroidEntryPoint
class SearchFragment : Fragment(), SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    private val searchViewModel: SearchViewModel by viewModels()

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupSearchView()
        setupErrorObserver()
        setupEmptyObserver()
        setupLoadingObserver()
        setupObserver()
        setupLoadMoreObserver()
        setupLoadMoreErrorObserver()
    }

    private fun setupView() {
        emptyLayout.setVisible()
        with(binding) {
            linearLayoutManager = LinearLayoutManager(activity)
            userRecyclerView.layoutManager = linearLayoutManager
            adapter = SearchAdapter(mutableListOf())
            userRecyclerView.adapter = adapter

            userRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0) {
                        val visibleItemCount = linearLayoutManager.childCount
                        val pastVisibleItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition()
                        val total = adapter.itemCount
                        if ((visibleItemCount + pastVisibleItem) >= total) {
                            searchViewModel.loadMore()
                        }
                    }
                    super.onScrolled(recyclerView, dx, dy)
                }
            })
        }
    }

    private fun setupSearchView() {
        with(binding) {
            searchView.setIconifiedByDefault(false)
            searchView.onActionViewExpanded()
            searchView.setOnQueryTextListener(this@SearchFragment)
            searchView.clearFocus()

            // Remove magnifier icon on searchView
            val magImage = searchView
                .findViewById<ImageView>(R.id.search_mag_icon)
            magImage.setImageDrawable(null)
            magImage.visibility = View.GONE

            // Remove plate on searchView
            val plate = searchView
                .findViewById<View>(androidx.appcompat.R.id.search_plate)
            plate.setBackgroundResource(R.drawable.bg_search)
        }
    }

    private fun setupErrorObserver() {
        searchViewModel.isError.observe(viewLifecycleOwner, { response ->
            response?.let {
                errorLayout.showIf(it)
                if (it) {
                    userRecyclerView.setGone()
                    loadingLayout.setGone()
                    emptyLayout.setGone()
                }
            }
        })
    }

    private fun setupLoadingObserver() {
        searchViewModel.isLoading.observe(viewLifecycleOwner, { response ->
            response?.let {
                loadingLayout.showIf(it)
                if (it) {
                    userRecyclerView.setGone()
                    errorLayout.setGone()
                    emptyLayout.setGone()
                }
            }
        })
    }

    private fun setupEmptyObserver() {
        searchViewModel.isEmpty.observe(viewLifecycleOwner, { response ->
            response?.let {
                emptyLayout.showIf(it)
                if (it) {
                    userRecyclerView.setGone()
                    errorLayout.setGone()
                    loadingLayout.setGone()
                }
            }
        })
    }

    private fun setupLoadMoreObserver() {
        searchViewModel.isLoadMore.observe(viewLifecycleOwner, { response ->
            response?.let {
                loadMore.showIf(it)
            }
        })
    }

    private fun setupLoadMoreErrorObserver() {
        searchViewModel.loadMoreError.observe(viewLifecycleOwner, { response ->
            response?.let {
                view?.showSnackbar(it)
            }
        })
    }

    private fun setupObserver() {
        searchViewModel.user.observe(viewLifecycleOwner, { response ->
            response?.let {
                emptyLayout.setGone()
                userRecyclerView.setVisible()
                adapter.addToList(it.toMutableList())
            }
        })
    }

    private fun clearKeyword() {
        with(binding) {
            adapter.clearList()
            searchView.requestFocus()
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        with(binding) {
            if (query != "") {
                query?.let {
                    searchViewModel.fetchUser(it)
                }
                searchView.clearFocus()
                return true
            }
        }
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query == "" || query == null) return true

        clearKeyword()

        return false
    }

    override fun onClose(): Boolean {
        clearKeyword()

        return true
    }

}
