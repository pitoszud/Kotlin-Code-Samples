package Core.Other

import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JComponent

interface Listener{
    fun addMouseListener()
}

class AnonymObject{

    fun perf(window : JComponent) {
        window.addMouseListener(
            object : MouseAdapter() {
                override fun mouseClicked(e: MouseEvent) {
                    // ...
                }

                override fun mouseEntered(e: MouseEvent) {
                    // ...
                }
            }
        )
    }
}