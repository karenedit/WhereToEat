package com.example.wheretoeat;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;


/** VistaGral: 
 * Clase que muestra la Vista General de la aplicación
 * @author Julia Ramos 1014993
 * @author Karen Olvera 807308
 *
 */

public class VistaGral extends Activity implements OnClickListener {
	//se declaran variables
	LinearLayout ll;
	
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
        setContentView(R.layout.vistagral);
        
        
        //se buscan elementos de la vista a traves de su id
        Button botonVistCat = (Button) findViewById(R.id.botonVistCat);        
        Button botonVistaGral = (Button) findViewById(R.id.botonVistaGral);        
        Button returnbtn = (Button) findViewById(R.id.returnbtn);
        Button filtrarbtn = (Button) findViewById(R.id.filtrarbtn);
        Button infobtn = (Button) findViewById(R.id.infobtn);
        ll = (LinearLayout) findViewById(R.id.linear);
        GridView gridView = (GridView) findViewById(R.id.gridView1);
        
        //se les asigna la accion de ClickListener
        botonVistCat.setOnClickListener(this);
        botonVistaGral.setOnClickListener(this);
        returnbtn.setOnClickListener(this);
        filtrarbtn.setOnClickListener(this);
        infobtn.setOnClickListener(this);
        
    	gridView.setOnItemClickListener(new OnItemClickListener() {
    		@Override
    		public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
    			Intent intent = new Intent(VistaGral.this, Restaurante.class);
    			startActivity(intent);
			}

			
		});

    	//se le asigna el Adapater al gridView
        gridView.setAdapter(new RestauranteAdapter(this));
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
    	}else if(view.getId() == R.id.filtrarbtn){ //si dio clic en el boton de filtrar
    		//se manda llamar a la funcion que muestra el popup de Filtros
    		showFiltrar();
    		

    		/*
            final CharSequence[] items = { "Más Visitado", "Visitado por amigos",
                    "Más cercano"};
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Filtros");

            builder.setMultiChoiceItems(items, null,
                    new DialogInterface.OnMultiChoiceClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog,
                                int which, boolean isChecked) {
                            // TODO Auto-generated method stub
                            if (isChecked)
                                Toast.makeText(getApplicationContext(),
                                        items[which], Toast.LENGTH_SHORT)
                                        .show();
                            ll.setBackgroundColor(Color.GRAY);
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            
            */
    	}else if(view.getId() == R.id.infobtn){ //si dio clic en el boton de informacion
    		//se manda llamar a la funcion que muestra el popup de Informacion
    		showInfo();
    	}else{
    		//se crea un nuevo intent
	    	Intent intent = null;
			switch (view.getId()) {
			case R.id.botonVistCat:
				//se inicializa el intent con la clase VistaPpal
				intent = new Intent(VistaGral.this, VistaPpal.class);
				break;
			case R.id.botonVistaGral:
				//se inicializa el intent con la clase VistaGral
				intent = new Intent(VistaGral.this, VistaGral.class);
				break;
			}
			//se inicia la actividad con el intent
			startActivity(intent);
    	}
		
		
	}
    
    
    /**showFiltrar() :
     * 
     * Método para mostrar una pantalla pop up donde se pueden hacer filtros
     * @param void
     * 
     */
    public void showFiltrar() {
    	
    	//se crea una nueva alerta de dialogo
    	AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
   	 	//se le asigna el titulo a la ventana de dialogo
    	helpBuilder.setTitle("Filtros");
   	 
    	//helpBuilder.setMessage("This is a Simple Pop Up");
   	 
    	//se toma el Layout Inflater
	   	 LayoutInflater inflater = getLayoutInflater();
	   	 //se toma el layout correspondiente a la ventana del pop up
	   	 View checkboxLayout = inflater.inflate(R.layout.popup, null);
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
