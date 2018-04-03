package loyarte.ivan.iguanafixmobilechallenge

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig

class IguanaFixApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        val frescoConfig : ImagePipelineConfig = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .build()
        Fresco.initialize(this, frescoConfig)
    }

}