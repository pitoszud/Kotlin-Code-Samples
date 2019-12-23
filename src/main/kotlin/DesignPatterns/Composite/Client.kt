package DesignPatterns.Composite

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