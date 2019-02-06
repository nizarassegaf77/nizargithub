package nizar.id.github.data.module

import dagger.Module
import dagger.Provides
import nizar.id.github.data.ApiRoute
import nizar.id.github.data.service.GitHubServices
import javax.inject.Singleton

/**
 *
 * Created by Nizar Assegaf on 3/2/2019.
 *
 */

@Module
open class ServiceModule{

    @Provides
    @Singleton
    protected fun providesArticleService(api: ApiRoute) = GitHubServices(api)
}