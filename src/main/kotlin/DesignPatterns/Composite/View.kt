package DesignPatterns.Composite

import java.lang.UnsupportedOperationException

// Component
abstract class View(val _viewName: String, val _viewCount: Int){

    abstract fun add(view: View)
    abstract fun remove(view: View)
}


// Leaf node
class ViewComponent(private val viewComponentName: String, componentCount: Int = 1): View(viewComponentName, componentCount){

    // View component has an empty implementation, since it is the smallest unit possible.
    override fun add(view: View) {
        throw UnsupportedOperationException("Leaf node doesn't support additions")
    }

    override fun remove(view: View) {
        throw UnsupportedOperationException("Leaf node doesn't support removal")
    }


}

// Composite
class Layout(val viewName: String, val viewCount: Int) : View(viewName, viewCount){

    var viewList = mutableListOf<View>()

    override fun add(view: View) {
        viewList.add(view)
    }

    override fun remove(view: View) {
        viewList.remove(view)
    }

    fun getViews(): MutableList<View> = viewList

    fun getCount(): Int = viewList.sumBy { it._viewCount }

}



fun main() {
    val button = ViewComponent("Save button")
    val buttonText = ViewComponent("Save")

    val mainLayout = Layout("Main Layout", 2)
    mainLayout.add(button)
    mainLayout.add(buttonText)

    // ----------------------------------------

    val editText = ViewComponent("Enter name")

    val editLayout = Layout("List layout", 1 + mainLayout.viewCount)
    editLayout.add(editText)
    editLayout.add(mainLayout)

    println(
        editLayout.viewCount
    )
}