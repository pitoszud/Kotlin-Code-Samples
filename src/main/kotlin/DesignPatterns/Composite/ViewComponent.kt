package DesignPatterns.Composite

class ViewComponent(private val viewComponentName: String, componentCount: Int = 1): View(viewComponentName, componentCount){

    // View component has an empty implementation, since it is the smallest unit possible.
    override fun add(view: View) {

    }

    override fun remove(view: View) {

    }


}