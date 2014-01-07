package com.theindex.taotaokanAndroid.zxing;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.theindex.taotaokanAndroid.R;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class barcodeActivity extends Activity {
	private final static int SCANNIN_GREQUEST_CODE = 1;
	/**
	 */
	private TextView mTextView ;
	/**
	 */
	private ImageView mImageView;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mTextView = (TextView) findViewById(R.id.result); 
		mImageView = (ImageView) findViewById(R.id.qrcode_bitmap);
		
		Button mButton = (Button) findViewById(R.id.button1);
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(barcodeActivity.this, MipcaActivityCapture.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
			}
		});

	}
	
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
		case SCANNIN_GREQUEST_CODE:
			if(resultCode == RESULT_OK){
				Bundle bundle = data.getExtras();
				mTextView.setText(bundle.getString("result"));
				mImageView.setImageBitmap((Bitmap) data.getParcelableExtra("bitmap"));


                tempTestFunction(bundle.getString("result"));
                /////


			}
			break;
		}
    }


    public void tempTestFunction(String httpUrl)
    {

        try{
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads()
                    .detectDiskWrites().detectNetwork().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
            String result="";

            String url = "http://42.96.140.53:4000/books/" + httpUrl;
            HttpGet httpGet=new HttpGet();//编者按：与HttpPost区别所在，这里是将参数在地址中传递
            HttpResponse response=new DefaultHttpClient().execute(httpGet);
            if(response.getStatusLine().getStatusCode()==200){
                HttpEntity entity=response.getEntity();
                result= EntityUtils.toString(entity, HTTP.UTF_8);
                mTextView.setText(result);
            }

            //return result;
        }
        catch (Exception e)
        {
            Log.d("huangzf", "error is " +e);
        }

    }

}
