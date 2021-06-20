package scene

import com.soywiz.korau.sound.PlaybackTimes
import com.soywiz.korau.sound.SoundChannel
import com.soywiz.korau.sound.readMusic
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.ui.uiTextButton
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.position
import com.soywiz.korio.async.launchImmediately
import com.soywiz.korio.file.std.resourcesVfs

class Demo : Scene() {
    override suspend fun Container.sceneInit() {

        var channel: SoundChannel? = null

        uiTextButton(100.0, 50.0) {
            text = "Play Music"
            position(50.0, 50.0)
            launchImmediately {
                onClick {
                    channel?.stop()//停止前一次播放
                    val music = resourcesVfs["music.mp3"].readMusic()//播放音樂
                    channel = music.play(PlaybackTimes.INFINITE)
                }
            }
        }
        uiTextButton(100.0, 50.0) {
            text = "Stop Music"
            position(50.0, 150.0)
            launchImmediately {
                onClick {
                    channel?.stop()//停止播放
                }
            }
        }
        uiTextButton(100.0, 50.0) {
            text = "Pause Music"
            position(50.0, 250.0)
            launchImmediately {
                onClick {
                    channel?.pause()//暫停播放
                }
            }
        }
        uiTextButton(100.0, 50.0) {
            text = "Resume Music"
            position(50.0, 350.0)
            launchImmediately {
                onClick {
                    channel?.resume()//從暫停處再播放
                }
            }

        }


    }
}


