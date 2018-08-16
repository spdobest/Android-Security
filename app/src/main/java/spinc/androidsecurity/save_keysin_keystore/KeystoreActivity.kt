package spandroid.dev.security.save_keysin_keystore

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_keystore.*
import spinc.androidsecurity.R
import spinc.androidsecurity.save_keysin_keystore.DeCryptorFromKeystore
import spinc.androidsecurity.save_keysin_keystore.EnCryptorFromKeystore
import java.io.IOException
import java.security.*
import java.security.cert.CertificateException
import javax.crypto.BadPaddingException
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException


/**
 * _____                       _        _    _
 * / ____|                     | |      | |  | |
 * | (___   __ _ _ __ ___  _ __ | | ___  | |  | |___  __ _  __ _  ___
 * \___ \ / _` | '_ ` _ \| '_ \| |/ _ \ | |  | / __|/ _` |/ _` |/ _ \
 * ____) | (_| | | | | | | |_) | |  __/ | |__| \__ \ (_| | (_| |  __/
 * |_____/ \__,_|_| |_| |_| .__/|_|\___|  \____/|___/\__,_|\__, |\___|
 * | |                               __/ |
 * |_|                              |___/
 */
class KeystoreActivity : AppCompatActivity(),View.OnClickListener {
    override fun onClick(v: View?) {
         when(v!!.id){
             R.id.buttonEncrypt ->{
                 encryptText()
             }
             R.id.buttonDecrypt ->{
                 decryptText()
             }
         }
    }


    private var encryptor: EnCryptorFromKeystore? = null
    private var decryptor: DeCryptorFromKeystore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keystore)

        encryptor = EnCryptorFromKeystore()

        try {
            decryptor = DeCryptorFromKeystore()
        } catch (e: CertificateException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: KeyStoreException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        buttonDecrypt.setOnClickListener(this)
        buttonDecrypt.setOnClickListener(this)

    }

    /*@OnClick(R.id.btn_encrypt, R.id.btn_decrypt)
    fun onClick(view: View) {

        val id = view.getId()

        when (id) {
            R.id.btn_encrypt -> encryptText()
            R.id.btn_decrypt -> decryptText()
        }
    }*/

    private fun decryptText() {
        try {
            textViewDecryptedText!!.setText(decryptor!!
                    .decryptData(SAMPLE_ALIAS, encryptor!!.getEncryption(), encryptor!!.getIv()))
        } catch (e: UnrecoverableEntryException) {
            Log.e(TAG, "decryptData() called with: " + e.message, e)
        } catch (e: NoSuchAlgorithmException) {
            Log.e(TAG, "decryptData() called with: " + e.message, e)
        } catch (e: KeyStoreException) {
            Log.e(TAG, "decryptData() called with: " + e.message, e)
        } catch (e: NoSuchPaddingException) {
            Log.e(TAG, "decryptData() called with: " + e.message, e)
        } catch (e: NoSuchProviderException) {
            Log.e(TAG, "decryptData() called with: " + e.message, e)
        } catch (e: IOException) {
            Log.e(TAG, "decryptData() called with: " + e.message, e)
        } catch (e: InvalidKeyException) {
            Log.e(TAG, "decryptData() called with: " + e.message, e)
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        } catch (e: InvalidAlgorithmParameterException) {
            e.printStackTrace()
        }

    }

    private fun encryptText() {

        try {
            val encryptedText = encryptor!!
                    .encryptText(SAMPLE_ALIAS, edittextEncriptText!!.getText().toString())
            textViewEncryptedText!!.setText(Base64.encodeToString(encryptedText, Base64.DEFAULT))
        } catch (e: UnrecoverableEntryException) {
            Log.e(TAG, "onClick() called with: " + e.message, e)
        } catch (e: NoSuchAlgorithmException) {
            Log.e(TAG, "onClick() called with: " + e.message, e)
        } catch (e: NoSuchProviderException) {
            Log.e(TAG, "onClick() called with: " + e.message, e)
        } catch (e: KeyStoreException) {
            Log.e(TAG, "onClick() called with: " + e.message, e)
        } catch (e: IOException) {
            Log.e(TAG, "onClick() called with: " + e.message, e)
        } catch (e: NoSuchPaddingException) {
            Log.e(TAG, "onClick() called with: " + e.message, e)
        } catch (e: InvalidKeyException) {
            Log.e(TAG, "onClick() called with: " + e.message, e)
        } catch (e: InvalidAlgorithmParameterException) {
            e.printStackTrace()
        } catch (e: SignatureException) {
            e.printStackTrace()
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        }

    }

    companion object {

        private val TAG = KeystoreActivity::class.java.simpleName
        private val SAMPLE_ALIAS = "MYALIAS"
    }
}
