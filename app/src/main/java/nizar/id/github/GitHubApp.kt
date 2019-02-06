package nizar.id.github

import android.app.Application
import nizar.id.github.data.component.DaggerGitHubComponent
import nizar.id.github.data.component.GitHubComponent
import nizar.id.github.data.module.DataModule
import nizar.id.github.data.module.NetworkModule
import nizar.id.github.data.module.PrefsModule
import nizar.id.github.data.module.ServiceModule
import nizar.id.github.data.provider.GithubProvider

class GitHubApp : Application(), GithubProvider {

    private lateinit var component: GitHubComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerGitHubComponent.builder()
                .networkModule(NetworkModule(this))
                .serviceModule(ServiceModule())
                .prefsModule(PrefsModule())
                .dataModule(DataModule(this))
                .build()
    }

    override fun providesGitHubComponent(): GitHubComponent = component


}