package loyarte.ivan.iguanafixmobilechallenge

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class IguanaFixApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }

}