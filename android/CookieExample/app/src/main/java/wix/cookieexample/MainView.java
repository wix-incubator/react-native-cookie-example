package wix.cookieexample;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class MainView extends LinearLayout {

	private final Listener listener;

	public interface Listener {
		void onClickClearAllCookies();
	}

	public static float dp(float px) {
		return MainApplication.instance.getResources().getDisplayMetrics().density * px;
	}


	private Button clearCookieBtn;
	private ProgressBar progressBar;
	private TextView firstLabel;
	private TextView secondLabel;

	public MainView(final Activity activity, Listener listener) {
		super(activity);
		this.listener = listener;
		setOrientation(VERTICAL);
		setGravity(Gravity.CENTER);
		int p = (int) dp(16);
		setPadding(p, p, p, p);
		createFirstLabel();
		createSecondLabel();
		createProgressIndicator();
		createClearCookiesBtn();
	}

	private void createClearCookiesBtn() {
		clearCookieBtn = new Button(getContext());
		clearCookieBtn.setText("Clear All Cookies");
		clearCookieBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View v) {
				listener.onClickClearAllCookies();
			}
		});
		addView(clearCookieBtn);
	}

	private void createProgressIndicator() {
		progressBar = new ProgressBar(getContext());
		progressBar.setIndeterminate(true);
		addView(progressBar);
	}

	private void createFirstLabel() {
		firstLabel = new TextView(getContext());
		firstLabel.setTextSize(24);
		firstLabel.setVisibility(GONE);
		LayoutParams params = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
		int m = (int) dp(8);
		params.setMargins(m, m, m, m);
		firstLabel.setLayoutParams(params);
		addView(firstLabel);
	}

	private void createSecondLabel() {
		secondLabel = new TextView(getContext());
		secondLabel.setTextSize(24);
		secondLabel.setVisibility(GONE);
		LayoutParams params = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
		int m = (int) dp(8);
		params.setMargins(m, m, m, m);
		secondLabel.setLayoutParams(params);
		addView(secondLabel);
	}

	public void setFirstLabel(final String firstLabel) {
		this.firstLabel.setText(firstLabel);
		this.firstLabel.setVisibility(VISIBLE);
	}

	public void setSecondLabel(final String secondLabel) {
		progressBar.setVisibility(GONE);
		this.secondLabel.setText(secondLabel);
		this.secondLabel.setVisibility(VISIBLE);
	}
}
