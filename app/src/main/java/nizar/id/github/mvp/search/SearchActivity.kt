package nizar.id.github.mvp.search

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import kotlinx.android.synthetic.main.search_activity.*
import nizar.id.github.R
import nizar.id.github.data.provider.GithubProvider
import nizar.id.github.data.service.GitHubServices
import nizar.id.github.mvp.base.BaseActivity
import javax.inject.Inject
import android.view.View
import android.animation.LayoutTransition
import android.content.Intent
import android.os.Handler
import nizar.id.github.utils.StringPreference
import android.view.MenuItem
import android.widget.Toast
import com.google.gson.Gson
import nizar.id.github.data.model.HistoryModel
import nizar.id.github.data.model.UserModel
import nizar.id.github.mvp.detailuser.DetailUserActivity
import nizar.id.github.utils.NAME_USERMODEL


class SearchActivity : BaseActivity(), SearchView, SuggestListener {

    @Inject
    protected lateinit var service: GitHubServices

    @Inject
    protected lateinit var pref: StringPreference

    var mExit = false
    lateinit var mSearchView: android.support.v7.widget.SearchView
    lateinit var mSearch: MenuItem
    private var presenter: SearchPresenter? = null
    private val listHistory: MutableList<HistoryModel> = ArrayList()
    private var mAdapterSuggest: SuggestAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.search_activity)

        setSupportActionBar(toolbar);

        (application as GithubProvider).providesGitHubComponent().inject(this)

        initViewSuggest()

    }

    override fun onBackPressed() {
        if (mExit) {
            finish()
            System.exit(0)
        } else {
            Toast.makeText(this, R.string.back_to_exit, Toast.LENGTH_SHORT).show()
            mExit = true
            Handler().postDelayed({ mExit = false }, (3 * 1000).toLong())
        }
    }

    override fun onResume() {
        presenter = SearchPresenter()
        onAttach()
        super.onResume()
    }

    override fun onProgress() {
        message.visibility = View.GONE
        imageGithub.visibility = View.GONE
        progress_dialog.visibility = android.view.View.VISIBLE
    }

    override fun onSuccess(response: UserModel) {
        message.setText(getString(R.string.silahkan_lakukan_pencarian_data_user_berdasarkan_username_yang_anda_cari))
        hideLoading()
        val intent = Intent(this, DetailUserActivity::class.java)
        intent.putExtra(NAME_USERMODEL, Gson().toJson(response))
        startActivity(intent)
    }

    override fun onAttach() {
        presenter?.onAttach(this)
    }

    override fun onFail() {
        hideLoading()
        message.setText(getString(R.string.data_not_found))
    }

    override fun onDetach() {
        presenter?.onDetach()
    }

    override fun onDestroy() {
        onDetach()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_search, menu)

        mSearch = menu.findItem(R.id.action_search)
        mSearchView = mSearch.actionView as android.support.v7.widget.SearchView
        mSearchView.queryHint = "Search"
        mSearchView.isIconified = false
        mSearchView.layoutTransition = LayoutTransition()
        mSearchView.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                mSearchView.setQuery("", false);
                mSearchView.onActionViewCollapsed();
                mSearch.collapseActionView();
                presenter?.savetHistory(pref, query)
                presenter?.getUsers(query, service)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                presenter?.getHistory(newText, pref)
                return true
            }
        })

        mSearch.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {

            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                listSuggest.setVisibility(View.VISIBLE)
                return true // Return true to expand action view
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                listSuggest.visibility = View.INVISIBLE
                return true // Return true to collapse action view
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onClickSuggest(item: String) {
        presenter?.getUsers(item, service)
        mSearchView.setQuery("", false);
        mSearchView.onActionViewCollapsed();
        mSearch.collapseActionView();
    }

    override fun onHistoryLoaded(response: List<HistoryModel>) {
        listHistory.clear()
        listHistory.addAll(response)
        mAdapterSuggest?.notifyDataSetChanged()
    }

    override fun onClickRemoveSuggest(historyModel: HistoryModel) {
        listHistory.remove(HistoryModel(historyModel.item))
        presenter?.removeHistory(pref, historyModel.item.toString())
        mAdapterSuggest?.notifyDataSetChanged()
    }

    private fun initViewSuggest() {
        mAdapterSuggest = SuggestAdapter(listHistory, this)
        listSuggest.adapter = mAdapterSuggest
        val layoutManager = LinearLayoutManager(this)
        listSuggest.layoutManager = layoutManager
    }

    private fun hideLoading() {
        message.visibility = View.VISIBLE
        imageGithub.visibility = View.VISIBLE
        progress_dialog.visibility = android.view.View.GONE
    }

}
