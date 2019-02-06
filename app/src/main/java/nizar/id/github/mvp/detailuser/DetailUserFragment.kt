package nizar.id.github.mvp.detailuser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.detail_user_fragment.*
import nizar.id.github.R
import nizar.id.github.data.model.UserModel
import nizar.id.github.data.provider.GithubProvider
import nizar.id.github.mvp.base.BaseFragment
import nizar.id.github.utils.loadRoundedBitmap

class DetailUserFragment : BaseFragment(), DetailUserView {

    private var presenter: DetailUserPresenter? = null
    private var mUserModel: UserModel? = UserModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initView()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        (activity?.application as GithubProvider).providesGitHubComponent().inject(this)
        return inflater.inflate(R.layout.detail_user_fragment, container, false)
    }

    override fun onAttach() {
        presenter?.onAttach(this)
        presenter?.loadDataUser((activity as DetailUserActivity).usermodel)
    }

    override fun onDestroy() {
        onDetach()
        super.onDestroy()
    }

    override fun onDetach() {
        presenter?.onDetach()
        super.onDetach()
    }

    override fun onResume() {
        presenter = DetailUserPresenter()
        onAttach()
        super.onResume()
    }

    override fun onUserLoaded(userModel: UserModel) {
        this.mUserModel = userModel
        initView()
    }

    fun initView() {
        name.setText(mUserModel?.name)
        if (mUserModel?.avatar_url != "") {
            loadRoundedBitmap(requireContext(), mUserModel?.avatar_url, profilePicture)
        }
        location.setText(mUserModel?.location)
        repos.setText(mUserModel?.public_repos.toString())
        follower.setText(mUserModel?.followers.toString())
        following.setText(mUserModel?.following.toString())
    }

    companion object {
        val TAG = DetailUserFragment::class.simpleName
        fun newInstance(): DetailUserFragment {
            return DetailUserFragment()

        }
    }

}