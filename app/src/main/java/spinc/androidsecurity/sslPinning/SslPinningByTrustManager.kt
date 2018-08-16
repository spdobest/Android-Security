package spinc.androidsecurity.sslPinning

import android.content.Context
import spinc.androidsecurity.R
import java.net.URL
import java.security.KeyStore
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory

class SslPinningByTrustManager constructor(context:Context){

// put the ssl certificate in rawa folder inside res

//    Load KeyStore with Certificate file from resources (as InputStream)
    val resourceStream = context.resources.openRawResource(R.raw.cert)
    val keyStoreType = KeyStore.getDefaultType()
    val keyStore = KeyStore.getInstance(keyStoreType)

   val v = keyStore.load(resourceStream, null)
//Get TrustManagerFactory and init it with KeyStore
    val trustManagerAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
    val trustManagerFactory = TrustManagerFactory.getInstance(trustManagerAlgorithm)

    val f = trustManagerFactory.init(keyStore)

    // Get instance of SSLContext, bind it with TrustManager and create sslContext with URL connection
    val sslContext = SSLContext.getInstance("TLS")
    var vs = sslContext.init(null, trustManagerFactory.trustManagers, null)
    val url = URL("http://www.example.com/")
    val urlConnection = url.openConnection() as HttpsURLConnection
   // urlConnection.sslSocketFactory = sslContext.socketFactory
}