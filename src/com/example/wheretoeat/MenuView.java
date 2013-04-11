package com.example.wheretoeat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;

/**MenuView: 
 * Clase que muestra el menu de cada restaurante
 * @author Julia Ramos 1014993
 * @author Karen Olvera 807308
 *
 */
public class MenuView extends Activity implements OnClickListener{
	
	//declaracion de variables globales
	GridView gridView;
	static final String[] menuitems = new String[] { 
		"Santafe", "Sandwich","Caldo", "Torta" };
	static final String[] precioitems = new String[] { 
		"$100.00", "$89.34","$76.00", "$90.45" };
	 
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
       setContentView(R.layout.menu);
       
       
       //se buscan elementos de la vista a traves de su id
       Button returnbtn = (Button) findViewById(R.id.returnbtn);
       Button infobtn = (Button) findViewById(R.id.infobtn);
       gridView = (GridView) findViewById(R.id.gridView1);
       
       //se les asigna la accion de ClickListener
       returnbtn.setOnClickListener(this);
       infobtn.setOnClickListener(this);
       
       //se le asigna el Adapater al gridView
		gridView.setAdapter(new MenuAdapater(this, menuitems, precioitems));

  }


   /** Descripcion del método onClick(View view) 
	* Se utiliza para cuando se da clic en  
	* algún botón de la interfaz
	* @param View view
	*/
   public void onClick(View view) {
  
	   //si dio clic en el boton de Regresar
   		if(view.getId() == R.id.returnbtn){
   			//se termina la actividad
   			finish(); 
   		}else if(view.getId() == R.id.infobtn){ //si dio clic en el boton de informacion
    		//se manda llamar a la funcion que muestra el popup de Informacion
    		showInfo();
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