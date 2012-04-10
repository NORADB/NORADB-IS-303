package org.spls.app;

import android.content.Context;  
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;  
import org.apache.http.HttpResponse;  
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;  
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.impl.client.DefaultHttpClient;  
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
  
public class Connect {  
     
  
    private HttpEntity entity;
	private JSONArray jArray;
	Connect(){  
    //stub ctor  
    }  
  
    public JSONArray queryDB(Context context){  




    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

    	nameValuePairs.add(new BasicNameValuePair("id","2"));
    	String result = "";
        InputStream is = null;
		try{  
            HttpClient httpclient = new DefaultHttpClient();  
            HttpPost httppost = new HttpPost("http://192.168.0.192/android/sqlsporringtest.php"); 
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);  
            entity = response.getEntity();  
            
            is = entity.getContent();

        }catch(Exception e){

                Log.e("log_tag", "Error in http connection "+e.toString());

                String errMsg ="Cannot connect to database server.\n"+e.toString();  
                Toast.makeText(context, errMsg, Toast.LENGTH_LONG).show();  

                return new JSONArray();
        }

        //convert response to string

        try{

                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);

                StringBuilder sb = new StringBuilder();

                String line = null;

                while ((line = reader.readLine()) != null) {

                        sb.append(line + "\n");

                }

                is.close();

         

                result=sb.toString();

        }catch(Exception e){

                Log.e("log_tag", "Error converting result "+e.toString());

                String errMsg ="Converting error.\n"+e.toString();  
                Toast.makeText(context, errMsg, Toast.LENGTH_LONG).show();  

                return new JSONArray();
        }

         

        //parse json data

        try{

                jArray = new JSONArray(result);
                
                return jArray;

        }catch(Exception e){

                Log.e("log_tag", "Error parsing data "+e.toString());

                String errMsg ="JSON Error.\n"+e.toString();  
                Toast.makeText(context, errMsg, Toast.LENGTH_LONG).show();  
                return new JSONArray();
        }
    }//queryDB  
    
    public JSONArray loginFunction(Context context, String bnavn, String passord){  




    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    	
    	nameValuePairs.add(new BasicNameValuePair("bnavn",bnavn));
    	nameValuePairs.add(new BasicNameValuePair("passord",passord));
    	String result = "";
        InputStream is = null;
		try{  
            HttpClient httpclient = new DefaultHttpClient();  
            HttpPost httppost = new HttpPost("http://192.168.0.192/android/passordtest.php"); 
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            System.out.println(httppost.getURI().toString());
            System.out.println(httppost.getFirstHeader("bnavn"));
            System.out.println(httppost.getFirstHeader("passord"));
            HttpResponse response = httpclient.execute(httppost);  
            entity = response.getEntity();  
            
            is = entity.getContent();

        }catch(Exception e){

                Log.e("log_tag", "Error in http connection "+e.toString());

                String errMsg ="Cannot connect to database server.\n"+e.toString();  
                Toast.makeText(context, errMsg, Toast.LENGTH_LONG).show();  

                return new JSONArray();
        }

        //convert response to string

        try{

                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);

                StringBuilder sb = new StringBuilder();

                String line = null;

                while ((line = reader.readLine()) != null) {

                        sb.append(line + "\n");

                }

                is.close();

         

                result=sb.toString();
                System.out.print(result);

        }catch(Exception e){

                Log.e("log_tag", "Error converting result "+e.toString());

                String errMsg ="Converting error.\n"+e.toString();  
                Toast.makeText(context, errMsg, Toast.LENGTH_LONG).show();  

                return new JSONArray();
        }

         

        //parse json data

        try{

                jArray = new JSONArray(result);
                
                return jArray;

        }catch(Exception e){

                Log.e("log_tag", "Error parsing data "+e.toString());

                String errMsg ="JSON Error.\n"+e.toString();  
                Toast.makeText(context, errMsg, Toast.LENGTH_LONG).show();  
                return new JSONArray();
        }
    }//queryDB  
}//class  