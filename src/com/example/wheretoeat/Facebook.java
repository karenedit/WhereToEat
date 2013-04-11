package com.example.wheretoeat;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

/**Facebook: 
 * Clase que simula el login de Facebook
 * @author Julia Ramos 1014993
 * @author Karen Olvera 807308
 *
 */
public class Facebook extends Activity implements OnClickListener {
	 
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
        setContentView(R.layout.facebook);
       
        //se buscan elementos de la vista a traves de su id
        Button iniciarBtn = (Button) findViewById(R.id.iniciarbtn);
        //se les asigna la accion de ClickListener
        iniciarBtn.setOnClickListener(this);  
 
   }
    
    
    /** Descripcion del método onClick(View view) 
	* Se utiliza para cuando se da clic en  
	* algún botón de la interfaz
	* @param View view
	*/
    public void onClick(View view) {
    	//se crea un nuevo intent
    	Intent intent = null;
		switch (view.getId()) {
		case R.id.iniciarbtn:
			//se inicializa el intent con la clase VistaPpal
			intent = new Intent(Facebook.this, VistaPpal.class);
			break;
		}
		//se inicia la actividad con el intent
		startActivity(intent);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_where_eat, menu);
        return true;
    }
    
}
