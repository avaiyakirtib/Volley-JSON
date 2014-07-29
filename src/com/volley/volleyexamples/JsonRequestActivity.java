package com.volley.volleyexamples;


import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.volley.volleyexamples.app.AppController;
import com.volley.volleyexamples.volley.utils.Const;

public class JsonRequestActivity extends Activity implements OnClickListener {

	private String TAG = JsonRequestActivity.class.getSimpleName();
	private Button btnJsonObj, btnJsonArray,btnJSONPOST;
	private TextView msgResponse;
	private ProgressDialog pDialog;

	// These tags will be used to cancel the requests
	private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_json);

		btnJsonObj = (Button) findViewById(R.id.btnJsonObj);
		btnJSONPOST = (Button) findViewById(R.id.JSONReqPOST);

		btnJsonArray = (Button) findViewById(R.id.btnJsonArray);
		msgResponse = (TextView) findViewById(R.id.msgResponse);
		
		pDialog = new ProgressDialog(this);
		pDialog.setMessage("Loading...");
		pDialog.setCancelable(false);

		btnJsonObj.setOnClickListener(this);
		btnJSONPOST.setOnClickListener(this);
		btnJsonArray.setOnClickListener(this);
	}

	private void showProgressDialog() {
		if (!pDialog.isShowing())
			pDialog.show();
	}

	private void hideProgressDialog() {
		if (pDialog.isShowing())
			pDialog.hide();
	}

	/**
	 * Making json object request
	 **/
	private void makeJsonObjReq() {
		showProgressDialog();
		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
				Const.URL_JSON_OBJECT, null,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG, response.toString());
						Log.i("JSON response", "JSON getting " + response.toString());
						
						msgResponse.setText(response.toString());
						hideProgressDialog();
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hideProgressDialog();
					}
				}) {

			/**
			 * Passing some request headers
			 * */
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> headers = new HashMap<String, String>();
				headers.put("Content-Type", "application/json");
				return headers;
			}

			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<String, String>();
				params.put("name", "Kirti Kumar");
				params.put("email", "avaiyakirtib@yahoo.com");
				//params.put("pass", "password123");
				return params;
			}
		};

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(jsonObjReq,
				tag_json_obj);

		// Cancelling request
		// ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);		
	}
	
	private void makeJsonObjReqPOST() {
		RequestQueue queue = Volley.newRequestQueue(this);
		StringRequest postRequest = new StringRequest(Method.POST, "SPECIFY YOUR POST WEB API URI", 
		    new Response.Listener<String>() 
		    {
		        @Override
		        public void onResponse(String response) {
		            // response
		            Log.d("Response", response);
		        }	
		    }, 
		    new Response.ErrorListener() 
		    {
		         @Override
		         public void onErrorResponse(VolleyError error) {
		             // error
		        	 VolleyLog.d(TAG, "Error: " + error.getMessage());
		        	 }
		    }
		) {     
		    @Override
		    protected Map<String, String> getParams() 
		    {  
		            Map<String, String>  params = new HashMap<String, String>();  
		            params.put("user_id", "27");
 		             
		            return params;  
		    }
		};
		
		// Adding request to request queue
		queue.add(postRequest);
 	}
	/**
	 * Making json array request
	 * */
	private void makeJsonArryReq() {
		showProgressDialog();
		JsonArrayRequest req = new JsonArrayRequest(Const.URL_JSON_ARRAY,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, response.toString());
						msgResponse.setText(response.toString());
						hideProgressDialog();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hideProgressDialog();
					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(req,
				tag_json_arry);

		// Cancelling request
		// ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_arry);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnJsonObj:
			makeJsonObjReq();
			break;
		case R.id.btnJsonArray:
			makeJsonArryReq();
			break;
		case R.id.JSONReqPOST:
			makeJsonObjReqPOST();
		break;
	}

	}

}
