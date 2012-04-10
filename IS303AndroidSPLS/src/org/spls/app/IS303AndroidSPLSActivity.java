package org.spls.app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.TableRow.LayoutParams;

public class IS303AndroidSPLSActivity extends Activity {
    private JSONArray jArray;
    private JSONArray loginArray;
    public String id = "";
    private TextView bnavn;
    private TextView passord;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        try {

	        Connect c = new Connect();  
	        


	        Button button = (Button) findViewById(R.id.button1);
	        button.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	login();
	            	
	             }
	        });
	         
	        jArray = c.queryDB(this);
	        for(int i=0;i<jArray.length();i++){

	            JSONObject json_data;
				
				json_data = jArray.getJSONObject(i);
					
			    TableLayout tl = (TableLayout)findViewById(R.id.tableLayout1);
		        /* Create a new row to be added. */
		        TableRow tr = new TableRow(this);
		             
		        tr.setLayoutParams(new LayoutParams(
		                        LayoutParams.FILL_PARENT,
		                       LayoutParams.WRAP_CONTENT));
		        /* Create a Text to be the row-content. */
	
		      	TextView tx = new TextView(this);
	
		      	TextView tx2 = new TextView(this);
		        /* Add Button to row. */
		        createView(tr, tx, "SystembrukerID: "+json_data.getInt("systembrukerID"));
		        createView(tr, tx2, "ModuliD: "+json_data.getString("modulID"));
	
	
		        /* Add row to TableLayout. */
		        tl.addView(tr,new TableLayout.LayoutParams(
		                  LayoutParams.FILL_PARENT,
		                  LayoutParams.WRAP_CONTENT));
	
	            // t.append("\n AntInnleveringer: "+json_data.getString("Antall_innleveringer"));
	            // t.append("\n--");
            
	        }
        


        }catch (JSONException e) {
        	
			//IGONER!
		}
//
    }

    public void createView(TableRow tr, TextView t, String viewdata) {
	    t.setText(viewdata);
	    //adjust the porperties of the textView
	    t.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
	    t.setTextColor(Color.DKGRAY);
	    t.setBackgroundColor(Color.CYAN);
	    t.setPadding(20, 0, 0, 0);
	    tr.setPadding(0, 1, 0, 1);
	    tr.setBackgroundColor(Color.BLACK);
	    tr.addView(t); // add TextView to row.
    }
    
    public void login(){
    	Connect c = new Connect();
    	bnavn = (TextView) findViewById(R.id.editText1);
    	passord = (TextView) findViewById(R.id.editText2);
        loginArray = c.loginFunction(this, bnavn.getText().toString(), passord.getText().toString());

    	TextView tx= (TextView) findViewById(R.id.textView1);
    	if(loginArray.length()==0){
    		tx.setText("Feil Passord, sannsynligvis");
    	}else{
    		
    		try {
				tx.setText("Korekt Passord, " + loginArray.getJSONObject(0).getString("fornavn"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    }
    
        
}