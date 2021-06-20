package scene


import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.input.mouse
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.text

class Demo : Scene() {
    override suspend fun Container.sceneInit() {

        val debugText = text("")
        root.mouse {
            click {
                debugText.text += "mouse click\n"
            }
            over {
                debugText.text += "mouse over\n"
            }
            down {
                debugText.text += "mouse down\n"
            }
            up {
                debugText.text += "mouse up\n"
            }

        }

        keys{
            down(Key.SPACE){
                debugText.text += "key down\n"
            }
            up(Key.SPACE){
                debugText.text += "ket up\n"
            }
        }
    }
}




