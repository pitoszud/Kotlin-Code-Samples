package DesignPatterns.Composite

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