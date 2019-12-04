package es.iesnervion.android.rad;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.widget.RadioGroup;
import android.widget.TextView;


public class Rad extends Activity implements RadioGroup.OnCheckedChangeListener  {

	RadioGroup rbgroup ;
	TextView txtv;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rad);
        
        rbgroup = (RadioGroup) findViewById(R.id.radioGroup1);
        rbgroup.clearCheck();
        rbgroup.setOnCheckedChangeListener(this);
        
        txtv = (TextView) findViewById(R.id.textView1);
    }

	public void onCheckedChanged(RadioGroup rg, int checkedId) {
		
		switch (checkedId){
		//switch (rg.getCheckedRadioButtonId()){
		case R.id.radio0:
			txtv.setTextColor(Color.RED);
			//txtv.setTextColor(Color.parseColor("#e52025"));
			//txtv.setTextColor(Color.rgb(200,0,0));
			//También se pueden definir los colores en un fichero XML para usarlos desde java:
			//   <color name="errorColor">#f00</color>
			//   txtv.setTextColor(getResources().getColor(R.color.errorColor));
			break;
		case R.id.radio1:
			txtv.setTextColor(Color.BLACK);
			break;
		case R.id.radio2:
			txtv.setTextColor(Color.BLUE);
			break;
		}
	}
}
