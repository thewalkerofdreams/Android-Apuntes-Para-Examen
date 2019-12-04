package es.iesnervion.android.migue.spinnerper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
//import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity implements Spinner.OnItemSelectedListener {
	
	private Spinner spinner;
	private TextView txtv1;
	private String [] equipos = {"Boston Celtics", "Chicago Bulls", "Los Angeles Lakers"};
	private int[] logos = {R.drawable.celtics,R.drawable.bulls,R.drawable.lakers};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		NbaAdapter<String> adaptador= new NbaAdapter<String>(this, R.layout.row, R.id.nombre, equipos);
		adaptador.setDropDownViewResource(R.layout.row);
		spinner = (Spinner) findViewById(R.id.sp1);
		txtv1 = (TextView) findViewById(R.id.txtv1);
		spinner.setAdapter(adaptador);
		spinner.setOnItemSelectedListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int pos,
			long arg3) {
		// TODO Auto-generated method stub
		txtv1.setText(equipos[pos]);
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	class NbaAdapter<T> extends ArrayAdapter<T> {
		
		public NbaAdapter(Context context,int row, int txtv, T[] array) {
			// TODO Auto-generated constructor stub
			super(context, row, txtv, array);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View row;
			TextView tv;
			//Si usamos Compound Drawable, no nos hace falta esto:
			//ImageView iv;
			ViewHolder holder;
			
			row = convertView;
			
			if (convertView==null){
				LayoutInflater inflater = getLayoutInflater();
				row=inflater.inflate(R.layout.row, parent, false);
				holder=new ViewHolder(row);
				row.setTag(holder);
			}
			else {
				holder=(ViewHolder)row.getTag();
			}
		
			tv= (TextView) holder.getTv() ;
			//Si usamos Compound Drawable, no nos hace falta esto:
			//iv= (ImageView) holder.getIv();	
			
			tv.setText(equipos[position]);
			//Si usamos Compound Drawable, no nos hace falta esto:
			//iv.setImageResource(logos[position]);
			
			////Si usamos Compound Drawable, HACEMOS ESTO PARA PONER LA IMAGEN:
			tv.setCompoundDrawablesWithIntrinsicBounds(logos[position], 0, 0, 0);
			//Si la aplicación se ejecuta en dispositivos con API mayores de 17 solamente, entonces usamos:
			//tv.setCompoundDrawablesRelativeWithIntrinsicBounds(logos[position], 0, 0, 0);
			
			
			return row;
		}
		
		@Override
		public View getDropDownView(int position, View convertView,
				ViewGroup parent) {
			// TODO Auto-generated method stub
			View row;
			TextView tv;
			//Si usamos Compound Drawable, no nos hace falta esto:
			//ImageView iv;
			ViewHolder holder;
			
			row = convertView;
			
			if (convertView==null){
				LayoutInflater inflater = getLayoutInflater();
				row=inflater.inflate(R.layout.row, parent, false);
				holder=new ViewHolder(row);
				row.setTag(holder);
			}
			else {
				holder=(ViewHolder)row.getTag();
			}
		
			tv= (TextView) holder.getTv() ;
			//Si usamos Compound Drawable, no nos hace falta esto:
			//iv= (ImageView) holder.getIv();	
			
			tv.setText(equipos[position]);
			//Si usamos Compound Drawable, no nos hace falta esto:
			//iv.setImageResource(logos[position]);
			
			////Si usamos Compound Drawable, HACEMOS ESTO PARA PONER LA IMAGEN:
			tv.setCompoundDrawablesWithIntrinsicBounds(logos[position], 0, 0, 0);
			//Si la aplicación se ejecuta en dispositivos con API mayores de 17 solamente, entonces usamos:
			//tv.setCompoundDrawablesRelativeWithIntrinsicBounds(logos[position], 0, 0, 0);
			
			
			return row;
		}
		
	}

	
	class ViewHolder {
		TextView tv;
		//Si usamos Compound Drawable, no nos hace falta esto:
		//ImageView iv;
		
		public ViewHolder(View row) {
			// TODO Auto-generated constructor stub
			tv=(TextView)row.findViewById(R.id.nombre);
			//Si usamos Compound Drawable, no nos hace falta esto:
			//iv=(ImageView)row.findViewById(R.id.logo);
			
		}

		public TextView getTv() {
			return tv;
		}
		
		//Si usamos Compound Drawable, no nos hace falta esto:
//		public ImageView getIv() {
//			return iv;
//		}

	}


}
