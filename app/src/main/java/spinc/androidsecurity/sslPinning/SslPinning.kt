package spinc.androidsecurity.sslPinning

import okhttp3.CertificatePinner
import okhttp3.OkHttpClient

class SslPinning {

    // https://www.netguru.co/codestories/3-ways-how-to-implement-certificate-pinning-on-android

    val certificatePinner = CertificatePinner.Builder()
            .add(
                    "www.example.com",
                    "sha256/ZC3lTYTDBJQVf1P2V7+fibTqbIsWNR/X7CWNVW+CEEA="
            ).build()

    val okHttpClient = OkHttpClient.Builder()
            .certificatePinner(certificatePinner)
            .build()
}