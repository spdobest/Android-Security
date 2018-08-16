package spinc.androidsecurity.oAuth;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Intent;
import android.os.Bundle;

import java.io.IOException;

public class OnTokenAcquired implements AccountManagerCallback<Bundle> {
    @Override
    public void run(AccountManagerFuture<Bundle> result) {
        // Get the result of the operation from the AccountManagerFuture.
        Bundle bundle = null;
        try {
            bundle = result.getResult();
        } catch (OperationCanceledException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AuthenticatorException e) {
            e.printStackTrace();
        }

        // The token is a named value in the bundle. The name of the value
        // is stored in the constant AccountManager.KEY_AUTHTOKEN.
        String token = bundle.getString(AccountManager.KEY_AUTHTOKEN);

        Intent launch = null;
        try {
            launch = (Intent) result.getResult().get(AccountManager.KEY_INTENT);
            //startActivityForResult(launch, 0);
        } catch (OperationCanceledException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AuthenticatorException e) {
            e.printStackTrace();
        }
        if (launch != null) {

            return;
        }

    }

    /**
     * URL url = new URL("https://www.googleapis.com/tasks/v1/users/@me/lists?key=" + your_api_key);
     URLConnection conn = (HttpURLConnection) url.openConnection();
     conn.addRequestProperty("client_id", your client id);
     conn.addRequestProperty("client_secret", your client secret);
     conn.setRequestProperty("Authorization", "OAuth " + token);
     */
}