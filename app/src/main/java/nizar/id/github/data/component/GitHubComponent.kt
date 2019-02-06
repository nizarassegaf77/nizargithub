package nizar.id.github.data.component

import dagger.Component
import nizar.id.github.data.module.DataModule
import nizar.id.github.data.module.NetworkModule
import nizar.id.github.data.module.PrefsModule
import nizar.id.github.data.module.ServiceModule
import nizar.id.github.mvp.detailuser.DetailUserFragment
import nizar.id.github.mvp.repositories.RepositoriesFragment
import nizar.id.github.mvp.search.SearchActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetworkModule::class, ServiceModule::class, PrefsModule::class, DataModule::class))
interface GitHubComponent {

    fun inject(searchActivity: SearchActivity)

    fun inject(repositoriesActivity: RepositoriesFragment)

    fun inject(detailUserFragment: DetailUserFragment)
}