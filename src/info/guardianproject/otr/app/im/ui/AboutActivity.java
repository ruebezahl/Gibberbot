package info.guardianproject.otr.app.im.ui;

import info.guardianproject.otr.app.im.R;
import info.guardianproject.otr.app.im.app.AccountActivity;
import info.guardianproject.otr.app.im.provider.Imps;
import android.app.Activity;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends Activity implements OnClickListener
{
	//WizardHelper wizard = null;
    // TODO get mProviderId for real
    long mProviderId = 1;
	
	private int title[] = {
			R.string.about_welcome_title,
			R.string.about_otr_title,
			R.string.about_security_title
	};
	
	private int msg[] = {
			R.string.about_welcome,
			R.string.about_otr,
			R.string.about_security,
			R.string.setup_passphrase
	};
	
	private String buttons[][] =
	{
			{null,"Next"},
			{"Back","Next"},
			{"Back","Next"},
			{"Back","Next"},
			
	};
	
	private View.OnClickListener listener[][] =
	{
			{
				null,
				new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
					
						nextContent();
						
					}
				}
			},

			{
				new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						prevContent();
						
					}
				},
				new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
					
						nextContent();
						
					}
				}
			},

			{
				new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						prevContent();
						
					}
				},
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						finish();
						Intent intent = new Intent(getBaseContext(), AccountActivity.class);
						intent.setAction(Intent.ACTION_INSERT_OR_EDIT);
						intent.setData(ContentUris.withAppendedId(Imps.Provider.CONTENT_URI, mProviderId));
						// clear the back stack of the wizard
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(intent);
					}
				}
			},

			
			
	};
	                                 
	
	private int contentIdx = -1;
	
	protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);

	}
	
	@Override
	protected void onStart() {
		
		super.onStart();
		
        if (contentIdx == -1)
        {
            setContentView(R.layout.scrollingtext_buttons_view);
        
        	nextContent ();
        }
	}
	
	private void prevContent ()
	{
		contentIdx--;
		showContent(contentIdx);
	}
	
	private void nextContent ()
	{
		contentIdx++;
		showContent(contentIdx);
	}
	
	private void showContent (int contentIdx)
	{
		TextView txtTitle  = ((TextView)findViewById(R.id.WizardTextTitle));
		txtTitle.setText(getString(title[contentIdx]));
        
        TextView txtBody = ((TextView)findViewById(R.id.WizardTextBody));
		txtBody.setText(getString(msg[contentIdx]));
        
        Button btn1 = ((Button)findViewById(R.id.btnWizard1));
        if (buttons[contentIdx][0] != null)
        {
        	btn1.setText(buttons[contentIdx][0]);
        	btn1.setOnClickListener(listener[contentIdx][0]);
        	btn1.setVisibility(Button.VISIBLE);
        	
        }
        else
        {
        	btn1.setVisibility(Button.INVISIBLE);
        }
        
        Button btn2 = ((Button)findViewById(R.id.btnWizard2));
        if (buttons[contentIdx][1] != null)
        {
        	btn2.setText(buttons[contentIdx][1]);
        	btn2.setOnClickListener(listener[contentIdx][1]);
        	btn2.setVisibility(Button.VISIBLE);
        	
        }
        else
        {
        	btn2.setVisibility(Button.INVISIBLE);
        }
        
      
      
	}
	
	

	@Override
	protected void onResume() {
		super.onResume();
	
		
	}




	@Override
	public void onClick(DialogInterface arg0, int arg1) {
		
		
	}
	
	
	
}
