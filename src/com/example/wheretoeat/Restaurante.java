package com.example.wheretoeat;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**Restaurante
 * Clase que muestra la pantalla de cada restaurante y su información
 * @author Julia Ramos 1014993
 * @author Karen Olvera 807308
 *
 */
public class Restaurante extends Activity implements OnClickListener {
	
	//contador de los check-in
	
	int numchecks;
	databaseHandler db;
	int idRest;
	Restaurant restaurante;
	TextView checkRest, webRest, addressRest, phoneRest, hoursRest,daysRest,sucRest  ;
	ImageView imageView;
	JSONParser jParser = new JSONParser(); /* Creating JSON Parser object */
	private static String url_update_check = "http://geekode.systheam.com/andriodAPI/updateChecks.php";
	
	/**Descripcion del método onCreate: 
	* Se recuperan los correspondientes Ids de 
	* cada uno de los campos o botones de la interfaz
	* @param Bundle
	*/
	@Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       //se quita la barra azul con titulo de la aplicacion
       requestWindowFeature(Window.FEATURE_NO_TITLE);
       //se asigna el layout corresponidente a la pantalla
       setContentView(R.layout.restaurante);
       
       //se buscan elementos de la vista a traves de su id
       Button menubtn = (Button) findViewById(R.id.menubtn);        
       Button checkInbtn = (Button) findViewById(R.id.checkInbtn);  
       Button returnbtn = (Button) findViewById(R.id.returnbtn);
       Button infobtn = (Button) findViewById(R.id.infobtn);
       
       imageView = (ImageView) findViewById(R.id.imgRest);
       sucRest = (TextView) findViewById(R.id.sucTxt);
       webRest = (TextView) findViewById(R.id.webRest);
       addressRest = (TextView) findViewById(R.id.addressRest);
       phoneRest = (TextView) findViewById(R.id.phoneRest);
       hoursRest = (TextView) findViewById(R.id.hoursRest);
       daysRest = (TextView) findViewById(R.id.daysRest);
       checkRest = (TextView) findViewById(R.id.checks);
       
       
       //se les asigna la accion de ClickListener
       menubtn.setOnClickListener(this);
       checkInbtn.setOnClickListener(this);
       returnbtn.setOnClickListener(this);
       infobtn.setOnClickListener(this);
       

       
       //se hace una instancia de la base datos
   		db = new databaseHandler(this);
   		idRest = 3;
   		restaurante = db.getRestaurant(idRest);   		
       
       //se toman valores necesarios
       String webpage = restaurante.webpage;
       String sucName = restaurante.sucursal;
       String bigImage = restaurante.image;
       String facebook = restaurante.facebook;
       String twitter = restaurante.twitter;
       String address = restaurante.address;
       String phone = restaurante.phone;
       String hours = restaurante.hours;
       String days = restaurante.days;
       int checkin = restaurante.checkin;
       
       //se asignan a un elemento de la vista

       webRest.setText(webpage);
       sucRest.setText(sucName);
       addressRest.setText(address);
       phoneRest.setText(phone);
       hoursRest.setText(hours);
       daysRest.setText(days);
       checkRest.setText(""+checkin);
     	 
       //imageView.setImageResource(getResources().getIdentifier(bigImage, "drawable", getPackageName()));
       Pattern pattern = Pattern.compile(webpage);
       Linkify.addLinks(webRest, pattern, "http://");
       String imagePath ="images/"+bigImage;
       if (imageView != null) {

           try {
               InputStream path = getAssets().open(imagePath);
               Bitmap bit = BitmapFactory.decodeStream(path);
               imageView.setImageBitmap(bit);
           } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
       }


       //se asigna una escala
       //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

  }

   
   
   
   /** Descripcion del método onClick(View view) 
	* Se utiliza para cuando se da clic en  
	* algún botón de la interfaz
	* @param View 
	*/
   public void onClick(View view) {  
	   if(view.getId() == R.id.infobtn){ //si dio clic en el boton de informacion
			//se manda llamar a la funcion que muestra el popup de Informacion
			showInfo();
	   }else{
			switch (view.getId()) {
			//si dio clic en el boton de menu
			case R.id.menubtn:
	    		//se crea un nuevo intent y se inicializa el intent con la clase MenuView
				Intent intent = new Intent(Restaurante.this, MenuView.class);
				//se inicia la actividad con el intent
				startActivity(intent);
				break;
			//si dio clic en el boton de check-in
			case R.id.checkInbtn:
				
				
			    List<NameValuePair> params1 = new ArrayList<NameValuePair>();
			    params1.add(new BasicNameValuePair("idSuc", idRest + ""));
				JSONObject json = jParser.makeHttpRequest(url_update_check, "GET", params1);
				Log.d("CHECK que pedo: ", json.toString());
			    //se toma el valor del elemento y se hace cast a Integer
			    //numchecks = Integer.parseInt(checks.getText().toString());
				Log.i("que pedo" , "que pedo despues ");
			    db.updateChecks(restaurante);
			  //se toma el valor de checkIn del objeto
				int numchecks = restaurante.getCheckin();
			    Log.i("que pedo" , "inga numero " + numchecks);
			    //se asigna el contador al elemento de la vista
			    checkRest.setText(""+numchecks);
				Log.i("que pedo" , "que pedo antes ");
			    
				break;
			//si dio clic en el boton de Regresar
			case R.id.returnbtn:
				finish(); 
				break;
			}
	   }
	}
   
   /**showInfo() :
    * 
    * Método para mostrar una pantalla pop up de información
    * @param void
    * 
    */
   public void showInfo() {
   	
   	//se crea una nueva alerta de dialogo
   	AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
  	 	//se le asigna el titulo a la ventana de dialogo
   	helpBuilder.setTitle("Acerca");
  	 
   	//helpBuilder.setMessage("This is a Simple Pop Up");
  	 
   	//se toma el Layout Inflater
	   	 LayoutInflater inflater = getLayoutInflater();
	   	 //se toma el layout correspondiente a la ventana del pop up
	   	 View checkboxLayout = inflater.inflate(R.layout.info, null);
	   	 //se asigna esa vista a la ventana de dialogo
	   	 helpBuilder.setView(checkboxLayout);
	   	 
	   	 
	   	 //para manejar la acción del boton OK, de la ventana de dialogo
	   	 helpBuilder.setPositiveButton("Ok",
	   	   new DialogInterface.OnClickListener() {
	
	   	    public void onClick(DialogInterface dialog, int which) {
	   	     // No hace nada mas que cerrar la ventana de dialogo
	   	    }
	   	   });

	   	 // Se crea la ventana de dialogo
	   	 AlertDialog helpDialog = helpBuilder.create();
	   	 //se muestra la ventana de dialogo
	   	 helpDialog.show();
  }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
       // Inflate the menu; this adds items to the action bar if it is present.
       getMenuInflater().inflate(R.menu.activity_where_eat, menu);
       return true;
   }
   
}
