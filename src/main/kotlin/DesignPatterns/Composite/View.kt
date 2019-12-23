package DesignPatterns.Composite

abstract class View(val _viewName: String, val _viewCount: Int){

    abstract fun add(view: View)
    abstract fun remove(view: View)
}