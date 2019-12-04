package com.example.miguel.listadinamica;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListaDinamica extends ListActivity {
  TextView selection;
  private static final String[] items={"lorem", "ipsum", "dolor",
          "sit", "amet",
          "consectetuer", "adipiscing", "elit", "morbi", "vel",
          "ligula", "vitae", "arcu", "aliquet", "mollis","alarma",
          "etiam", "vel", "erat", "placerat", "ante", "calendario",
          "porttitor", "sodales", "pellentesque", "GPS" ,"augue", "purus"};
  
  @Override
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    setContentView(R.layout.activity_lista_dinamica);
    setListAdapter(new IconicAdapter<String>(this, R.layout.row, R.id.label, items));
    selection=(TextView)findViewById(R.id.selection);
  }
  
  public void onListItemClick(ListView parent, View v,
                              int position, long id) {
    selection.setText(items[position]);
  }
  
  class IconicAdapter<T> extends ArrayAdapter<T> {
    IconicAdapter( Context c, int resourceId, int textId, T[] objects) {
      super(c, resourceId, textId, objects);
    }
    
    public View getView(int position, View convertView,
                        ViewGroup parent) {
      View row=super.getView(position, convertView, parent);
     
      ImageView icon=(ImageView)row.findViewById(R.id.icon);
        
      if (items[position].equals("calendario")) {
        icon.setImageResource(R.drawable.calendar48x48);
      }
      else if (items[position].equals("GPS")){
        icon.setImageResource(R.drawable.compass48x48);
      }
      else if (items[position].equals("alarma")){
          icon.setImageResource(R.drawable.alarm48x48);
      }
      else{
    	  icon.setImageResource(R.drawable.delete);
      }
      
      return(row);
    }
  }
}