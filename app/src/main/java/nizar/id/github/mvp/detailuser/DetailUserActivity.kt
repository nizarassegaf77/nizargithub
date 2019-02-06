package nizar.id.github.mvp.detailuser

import android.os.Bundle
import kotlinx.android.synthetic.main.detail_user_activity.*
import nizar.id.github.R
import nizar.id.github.mvp.base.BaseActivity
import nizar.id.github.mvp.repositories.RepositoriesFragment
import nizar.id.github.utils.NAME_USERMODEL

class DetailUserActivity : BaseActivity() {

    var usermodel = ""
    lateinit var bAdapter: TabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.detail_user_activity)

        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        initDataUser()

        initTabHost()

    }

    fun initTabHost() {
        bAdapter = TabAdapter(this.supportFragmentManager)
        bAdapter.addFragment(DetailUserFragment.newInstance(), "PROFILE")
        bAdapter.addFragment(RepositoriesFragment.newInstance(), "REPOSITORY")
        viewpager.setAdapter(bAdapter)
        viewpager.setOffscreenPageLimit(bAdapter.count)
        tablayout.setupWithViewPager(viewpager)
    }

    private fun initDataUser() {
        usermodel = intent.getStringExtra(NAME_USERMODEL)

    }

}