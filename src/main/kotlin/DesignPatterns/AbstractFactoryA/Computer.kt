// 0    Component
data class Component(val name: String)


// 1a   Abstract Item
// 1b   Concrete Items

interface Toolbar{
    fun getToolbarComponents(): Component
}


class DarkToolbar: Toolbar{
    override fun getToolbarComponents(): Component{
        return Component("dark toolbar components")
    }

}


class LightToolbar: Toolbar{
    override fun getToolbarComponents(): Component{
        return Component("light toolbar components")
    }

}


// 2a   Abstract Item
// 2b   Concrete Items

interface Drawer{
    fun getDrawerComponents(): Component
}


class DarkDrawer: Drawer{
    override fun getDrawerComponents(): Component {
        return Component("dark drawer components")
    }
}


class LightDrawer: Drawer{
    override fun getDrawerComponents(): Component {
        return Component("light drawer components")
    }
}


// 3. Abstract Factory -------------------------------------------

abstract class AbstractThemeFactory{
    abstract fun createToolbar(): Toolbar
    abstract fun createDrawer(): Drawer
}


// 4. Concrete Factories --------------------------------------------

// 4.a

class LightThemeFactory: AbstractThemeFactory(){
    override fun createToolbar(): Toolbar {
        println("creating light toolbar")
        return LightToolbar()
    }

    override fun createDrawer(): Drawer {
        println("creating light drawer")
        return LightDrawer()
    }
}


// 4.b


class DarkThemeFactory: AbstractThemeFactory(){
    override fun createToolbar(): Toolbar {
        println("creating dark toolbar")
        return DarkToolbar()
    }

    override fun createDrawer(): Drawer {
        println("creating dark drawer")
        return DarkDrawer()
    }
}

// 5. Factory Provider ----------------------------------------------

class FactoryProvider{
    companion object {
        fun getFactory(factoryType: String): AbstractThemeFactory? {
            return when (factoryType) {
                "LightTheme" -> LightThemeFactory()
                "DarkTheme" -> DarkThemeFactory()
                else -> null
            }
        }
    }
}

// 6. Client --------------------------------------------------

fun main(args: Array<String>) {
    // 1. get factory
    var abstractThemeFactory: AbstractThemeFactory? = null

    // 2.
    abstractThemeFactory = FactoryProvider.getFactory("LightTheme")
    val lightToolbar: Toolbar = abstractThemeFactory!!.createToolbar()
    val lightDrawer: Drawer = abstractThemeFactory.createDrawer()

    // 3.
    abstractThemeFactory = FactoryProvider.getFactory("DarkTheme")
    val darkToolbar: Toolbar = abstractThemeFactory!!.createToolbar()
    val darkDrawer: Drawer = abstractThemeFactory.createDrawer()
}


