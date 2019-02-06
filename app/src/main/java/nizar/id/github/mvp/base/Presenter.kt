package nizar.id.github.mvp.base


interface Presenter<in T : View> {

    fun onAttach(view: T)

    fun onDetach()
}