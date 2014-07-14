package com.arjinmc.updatedemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.arjinmc.database.DBOpenHelper;
import com.arjinmc.model.Person;
import com.arjinmc.updatedemo.R;


/**
 * @usage MainActivity to show the effects
 * @author Eminem Lu
 * @email arjinmc@hotmail.com
 * @date 2014年7月14日
 */
public class MainActivity extends Activity {
	
	private Button btnInsert;
	private ListView lvData;
	private List<Person> persons;
	private ListViewAdapter adapter;
	
	private final int GET_DATA_SUCCESS = 1;
	
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch(msg.what){
				//when insert info to table successfully
				case GET_DATA_SUCCESS:
					persons.clear();
					showData();
					adapter.notifyDataSetChanged();
					break;
			}
			
		};
	};
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
		
	}
	
	private void init(){
		
		persons = new ArrayList<Person>();
		
		btnInsert = (Button) findViewById(R.id.add);
		btnInsert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addData();

			}
		});
		lvData = (ListView) findViewById(R.id.data);
		adapter = new ListViewAdapter();
		lvData.setAdapter(adapter);
		showData();
	}
	
	//read the data frome database and show it on listview
	private void showData(){
		DBOpenHelper dbHelper = new DBOpenHelper(getApplicationContext());
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cur = db.query("INFOTB", new String[]{"ID","NAME"}, null, null, null, null, null);
		//content for update
		//Cursor cur = db.query("INFOTB", new String[]{"ID","NAME","PHONE"}, null, null, null, null, null);
		Log.e("cursor", cur.getCount()+"");
		while(cur.moveToNext()){
			Person tempPerson = new Person();
			tempPerson.setId(cur.getInt(cur.getColumnIndex("ID")));
			tempPerson.setName(cur.getString(cur.getColumnIndex("NAME")));
			//content for update
			//tempPerson.setPhone(cur.getString(cur.getColumnIndex("PHONE")));
			Log.e("person temp", tempPerson.getId()+" : "
					+tempPerson.getName()+" : "
					+tempPerson.getPhone());
			persons.add(tempPerson);
		}
		Log.e("persons", persons.size()+"");
		cur.close();
		db.close();
		dbHelper.close();
	}
	
	//add a record into database
	private void addData(){
		DBOpenHelper dbHelper = new DBOpenHelper(getApplicationContext());
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		Random radom = new Random();
		values.put("NAME", "JIN"+radom.nextDouble());
		//content for update
		//values.put("PHONE", "Phone"+radom.nextDouble());
		db.insert("INFOTB", null, values );
		db.close();
		dbHelper.close();
		
		handler.sendEmptyMessage(GET_DATA_SUCCESS);
	}
	
	/**
	 * @usage listview adapter
	 * @author Eminem Lu
	 * @email arjinmc@hotmail.com
	 * @date 2014年7月14日
	 */
	private class ListViewAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return persons.size();
		}

		@Override
		public Object getItem(int position) {
			return persons.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			ViewHolder viewholder = null;
			if(convertView == null){
				viewholder = new ViewHolder();
				convertView = LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.item, null);
				viewholder.tvId = (TextView) convertView.findViewById(R.id.id);
				viewholder.tvName = (TextView) convertView.findViewById(R.id.name);
				//content for update
				//viewholder.tvPhone = (TextView) convertView.findViewById(R.id.phone);
				
				convertView.setTag(viewholder);
				
			}else{
				viewholder = (ViewHolder) convertView.getTag();
			}
			
			viewholder.tvId.setText(String.valueOf(persons.get(position).getId()));
			viewholder.tvName.setText(persons.get(position).getName());
			//content for update
			//viewholder.tvPhone.setText(persons.get(position).getPhone());
			return convertView;
		}
		
		
		private class ViewHolder{
			private TextView tvId;
			private TextView tvName;
			private TextView tvPhone;
		}
	}
	
	
}
