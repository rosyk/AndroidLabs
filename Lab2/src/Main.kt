open class RemoteDevice {
    protected open var isTurnedOn = false
    open fun turnOn() {
        println("Device was turned on")
        isTurnedOn = true
    }

    open fun turnOff() {
        println("Device was turned off")
        isTurnedOn = false
    }
}



class Television(val brand: String): RemoteDevice() {
    private var channel = 0

    fun changeChannel(channel: Int) {
        if (isTurnedOn) {
            this.channel = channel
            println("Current channel: $channel")
        }
        else {
            println("TV is off")
        }
    }
}



class MusicPlayer: RemoteDevice() {
    var isDiscInserted = false

    override fun turnOn() {
        super.turnOn()
        if (isDiscInserted) {
            playMusic()
        }
    }

    fun playMusic() {
        if (isTurnedOn) {
            println("Music is playing now")
        }
        else {
            println("Music player is off")
        }
    }

    fun insertDisc(disc: String) {
        isDiscInserted = true
        println("Disc was inserted: $disc")
    }

    fun extractDisc() {
        isDiscInserted = false
        println("Disc was extracted")
    }
}



class Lamp: RemoteDevice() {
    fun setBrightness(level: Int) {
        if (isTurnedOn) {
            println("level of brightness: $level%")
        }
        else {
            println("Lamp is off")
        }
    }
}


fun main() {
    val tv = Television("Samsung")
    tv.turnOn()
    tv.changeChannel(3)
    tv.turnOff()

    val musicPlayer = MusicPlayer()
    musicPlayer.turnOn()
    musicPlayer.insertDisc("Queen")
    musicPlayer.playMusic()
    musicPlayer.turnOff()
    musicPlayer.turnOn()

    val lamp = Lamp()
    lamp.turnOn()
    lamp.setBrightness(70)
    lamp.turnOff()
}