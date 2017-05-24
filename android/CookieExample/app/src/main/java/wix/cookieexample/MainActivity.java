package wix.cookieexample;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.CookieManager;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends Activity implements MainView.Listener {
	public static final String ENDPOINT = "https://stark-atoll-33661.herokuapp.com/cookie.php";

	private interface Callback<T> {
		void run(T result);
	}

	private MainView view;

	@Override
	protected void onCreate(@Nullable final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = new MainView(this, this);
		setContentView(view);
		requestWithLowLevelUrlConnection();
	}

	private void requestWithLowLevelUrlConnection() {
		if (CookieManager.getDefault() == null) {
			CookieManager.setDefault(new CookieManager());
		}

		doRequestWithUrlConnection(new Callback<String>() {
			@Override
			public void run(final String result) {
				view.setFirstLabel(result);

				doRequestWithUrlConnection(new Callback<String>() {
					@Override
					public void run(final String result) {
						view.setSecondLabel(result);
					}
				});
			}
		});
	}

	private void doRequestWithUrlConnection(final Callback<String> callback) {
		new AsyncTask<Void, Void, String>() {
			@Override
			protected String doInBackground(final Void... params) {
				try {
					URL url = new URL(ENDPOINT);
					HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
					connection.setDoInput(true);
					connection.connect();
					InputStream inputStream = connection.getInputStream();
					return inputStreamToString(inputStream);
				} catch (Exception e) {
					return e.getMessage();
				}
			}

			@Override
			protected void onPostExecute(final String s) {
				callback.run(s);
			}
		}.execute();
	}

	@Override
	public void onClickClearAllCookies() {
		((CookieManager) CookieManager.getDefault()).getCookieStore().removeAll();
	}

	private static String inputStreamToString(InputStream is) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			StringBuilder builder = new StringBuilder(is.available());
			String nextLine;
			while ((nextLine = reader.readLine()) != null) {
				builder.append(nextLine).append('\n');
			}
			reader.close();
			return builder.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
