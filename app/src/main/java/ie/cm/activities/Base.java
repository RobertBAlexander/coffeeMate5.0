package ie.cm.activities;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import ie.cm.R;
import ie.cm.fragments.CoffeeFragment;
import ie.cm.fragments.EditFragment;
import ie.cm.fragments.HelpFragment;
import ie.cm.main.CoffeeMateApp;

public class Base extends AppCompatActivity {

	public static CoffeeMateApp	app = CoffeeMateApp.getInstance();
	//protected Bundle         	activityInfo; // Used for persistence (of sorts)
	//protected CoffeeFragment coffeeFragment; // How we'll 'share' our List of Coffees between Activities

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//app = (CoffeeMateApp) getApplication();
	}

	protected void goToActivity(Activity current,
								Class<? extends Activity> activityClass,
								Bundle bundle) {
		Intent newActivity = new Intent(current, activityClass);

		if (bundle != null) newActivity.putExtras(bundle);

		current.startActivity(newActivity);
	}

	public void openInfoDialog(Activity current) {
		Dialog dialog = new Dialog(current);
		dialog.setTitle("About CoffeeMate");
		dialog.setContentView(R.layout.info);

		TextView currentVersion = (TextView) dialog
				.findViewById(R.id.versionTextView);
		currentVersion.setText("1.0.0");

		dialog.setCancelable(true);
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

    public void menuInfo(MenuItem m)
    {
        openInfoDialog(this);
    }

    public void menuHelp(MenuItem m)
    {
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		Fragment fragment = HelpFragment.newInstance();
		ft.replace(R.id.homeFrame, fragment);
		ft.addToBackStack(null);
		ft.commit();
    }

    public void menuHome(MenuItem m)
    {
        goToActivity(this, Home.class, null);
    }

	//protected void toastMessage(String s) {
	//	Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
	//}
}
