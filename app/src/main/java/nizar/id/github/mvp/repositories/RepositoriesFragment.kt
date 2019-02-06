package nizar.id.github.mvp.repositories

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import kotlinx.android.synthetic.main.repositories_fragment.*
import nizar.id.github.R
import nizar.id.github.data.model.RepositoryModel
import nizar.id.github.data.model.UserModel
import nizar.id.github.data.provider.GithubProvider
import nizar.id.github.data.service.GitHubServices
import nizar.id.github.mvp.base.BaseFragment
import nizar.id.github.mvp.detailuser.DetailUserActivity
import nizar.id.github.mvp.detailuser.DetailUserFragment
import javax.inject.Inject

class RepositoriesFragment : BaseFragment(), RepositoriesView, RepositoryListener {

    @Inject
    protected lateinit var service: GitHubServices

    private lateinit var adapter: RepositoriesAdapter
    private var presenter: RepositoriesPresenter? = null
    private var repositories: MutableList<RepositoryModel> = ArrayList()
    private var username: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        try {
            username = Gson().fromJson((activity as DetailUserActivity).usermodel, UserModel::class.java).login.toString()
        } catch (e: Exception) {
        }
        (activity?.application as GithubProvider).providesGitHubComponent().inject(this)
        return inflater.inflate(R.layout.repositories_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onResume() {
        presenter = RepositoriesPresenter()
        onAttach()
        super.onResume()
    }

    override fun onAttach() {
        presenter?.onAttach(this)
        presenter?.getRepositories(username, service)
    }

    override fun onDetach() {
        presenter?.onDetach()
        super.onDetach()
    }

    override fun onDestroy() {
        onDetach()
        super.onDestroy()
    }

    override fun onClickItem(userModel: RepositoryModel) {

    }

    override fun onProgress() {
        message.visibility = android.view.View.GONE
        rvRepositories.visibility = android.view.View.GONE
        progress_dialog.visibility = android.view.View.VISIBLE
    }

    override fun onFailed(messageFail: String) {
        message.setText(messageFail)
        message.visibility = android.view.View.VISIBLE
    }

    override fun onRepositoriesLoaded(response: List<RepositoryModel>) {
        repositories.clear()
        repositories.addAll(response)
        adapter.notifyDataSetChanged()
        hideLoading()
    }

    private fun initView() {
        adapter = RepositoriesAdapter(repositories, this)
        rvRepositories.adapter = adapter
        val layoutManager = LinearLayoutManager(requireContext())
        rvRepositories.layoutManager = layoutManager
    }

    private fun hideLoading() {
        progress_dialog.visibility = android.view.View.GONE
        rvRepositories.visibility = android.view.View.VISIBLE
    }

    companion object {
        val TAG = RepositoriesFragment::class.simpleName
        fun newInstance(): RepositoriesFragment {
            return RepositoriesFragment()

        }
    }


}