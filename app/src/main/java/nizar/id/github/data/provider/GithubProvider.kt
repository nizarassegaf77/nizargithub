package nizar.id.github.data.provider

import nizar.id.github.data.component.GitHubComponent

interface GithubProvider{

    fun providesGitHubComponent(): GitHubComponent
}