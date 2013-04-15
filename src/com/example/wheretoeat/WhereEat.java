package com.example.wheretoeat;

import com.example.wheretoeat.LoadingTask.LoadingTaskFinishedListener;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.widget.ProgressBar;


/**
 * 
 * @author Julia Ramos 1014993
 * @author Karen Olvera 807308
 *
 */
public class WhereEat extends Activity implements LoadingTaskFinishedListener {
	 
	
	protected boolean _active = true;	//se inicializa active
	protected int _splashTime = 2000;	//se define el tiempo de inicio

	JSONParser jParser = new JSONParser(); /* Creating JSON Parser object */
	databaseHandler db = new databaseHandler(this);

	/* url to get all products list */
	private static String url_all_restaurants = "http://geekode.systheam.com/andriodAPI/get_restaurants.php";
	private static String url_all_categories = "http://geekode.systheam.com/andriodAPI/get_categories.php";
	
	/* JSON Node names */
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_RESTAURANTS = "restaurants";
	private static final String TAG_CATEGORIES = "categories";
	private static final String TAG_PID = "pid";
	private static final String TAG_RESTAURANT = "restaurant";
	private static final String TAG_SUCURSAL = "sucursal";
	private static final String TAG_CATEGORY = "category";
	private static final String TAG_THUMBNAIL = "thumbnail";
	private static final String TAG_XCOORDINATE = "xCoordinate";
	private static final String TAG_YCOORDINATE = "yCoordinate";
	private static final String TAG_ADDRESS = "address";
	private static final String TAG_PHONE = "phone";
	private static final String TAG_HOURS = "hours";
	private static final String TAG_IMAGE = "image";
	private static final String TAG_WEBPAGE = "webpage";
	private static final String TAG_TWITTER = "twitter";
	private static final String TAG_FACEBOOK = "facebook";
	private static final String TAG_CHECKIN = "checkin";
	
	JSONArray restaurants = null; /* products JSONArray */
	JSONArray categories = null; /* categories JSONARRAY */
 
	/**
	* Descripcion del método onCreate: 
	* Se recuperan los correspondientes Ids de 
	* cada uno de los campos o botones de la interfaz
	* @param Bundle
	*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); /* se quita la barra azul con titulo de la aplicacion */
        setContentView(R.layout.activity_where_eat); /*se asigna el layout corresponidente a la pantalla */
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar1); /*se buscan elementos de la vista a traves de su id */
        
        /* se comienza a cargar */
        new LoadingTask(progressBar, this).execute("www.google.com"); /* por el momento carga Google, despues cargará el servidor */
   }

    /** onTaskFinished: 
     * Callback para cuando el async task se haya terminado
     * @param void
     */
    public void onTaskFinished() {
    	completeSplash(); /* manda llamar funcion */
    }
    
    /** completeSplash: 
     * Metodo que manda llamar una funcion para comenzar
     * @param void
     */
    private void completeSplash(){
    	startApp(); /* manda llamar funcion */
        finish(); /* para terminar la actividad (si le dan clic al boton de atras no puedan regresar a esta pantalla) */
    }
 
    /** startApp: 
     * Metodo que comienza una nueva actividad
     * @param void
     */
    private void startApp() {
        Intent intent = new Intent(WhereEat.this, Login.class); /* se inicializa el intent con la clase Login */
        startActivity(intent); /* se inicia la actividad con el intent */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_where_eat, menu);
        return true;
    }        

	/**LoadingTask: 
	 * Clase que carga tareas en segundo plano
	 * @author Julia Ramos 1014993
	 * @author Karen Olvera 807308
	 *
	 */
	public class LoadingTask extends AsyncTask<String, Integer, Integer> {
		private final ProgressBar progressBar; /* This is the progress bar you want to update while the task is in progress */ 
		private final LoadingTaskFinishedListener finishedListener; /* This is the listener that will be told when this task is finished */
	
		/**LoadingTask: 
		 * Carga algunos recursos que son necesarios para que la aplicacion empiece
		 * @param progressBar - el progress bar que se estara actualizando 
		 * @param finishedListener - el listener que dira cuando se termine el task
		 */
		public LoadingTask(ProgressBar progressBar, LoadingTaskFinishedListener finishedListener) {
			this.progressBar = progressBar;
			this.finishedListener = finishedListener;
		}
	
		@Override
		protected Integer doInBackground(String... params) {
			// Building Parameters
			List<NameValuePair> params1 = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(url_all_restaurants, "GET", params1);
			Log.d("All Restaurants: ", json.toString());
			
			JSONObject jsonCategories = jParser.makeHttpRequest(url_all_categories, "GET", params1);
			Log.d("All Categories: ", jsonCategories.toString());

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);
				int successCategories = jsonCategories.getInt(TAG_SUCCESS);
				Log.d("SUCCESS: ", "SUCCESS = " + success + "  CATEGORIES = " + successCategories);
				if (success == 1 && successCategories == 1) {
					
					restaurants = json.getJSONArray(TAG_RESTAURANTS);
					categories = jsonCategories.getJSONArray(TAG_CATEGORIES);
					
					int total = restaurants.length() + categories.length();
					int loading = 1;
					
					// looping through All Products
					for (int i = 0; i < restaurants.length(); i++) {
						downloadResources(loading, total);
						JSONObject c = restaurants.getJSONObject(i);
						Log.i("JSON" , "JSON = " + c.toString());
						
						// Storing each json item in variable
						int id = c.getInt(TAG_PID);
						int category = c.getInt(TAG_CATEGORY);
						int xCoordinate = c.getInt(TAG_XCOORDINATE);
						int yCoordinate = c.getInt(TAG_YCOORDINATE);
						int checkin = c.getInt(TAG_CHECKIN);
						String restaurant = c.getString(TAG_RESTAURANT);
						String sucursal = c.getString(TAG_SUCURSAL);
						String thumbnail = c.getString(TAG_THUMBNAIL);
						String image = c.getString(TAG_IMAGE);
						String address = c.getString(TAG_ADDRESS);
						String phone = c.getString(TAG_PHONE);
						String hours = c.getString(TAG_HOURS);
						String webpage = c.getString(TAG_WEBPAGE);
						String twitter = c.getString(TAG_TWITTER);
						String facebook = c.getString(TAG_FACEBOOK);
						
						Log.d("JSON", "JSON id = " + id);
						Log.d("JSON", "JSON category = " + category);
						Log.d("JSON", "JSON xCoordinate = " + xCoordinate);
						Log.d("JSON", "JSON yCoordinate = " + yCoordinate);
						Log.d("JSON", "JSON checkin = " + checkin);
						Log.d("JSON", "JSON rsturant = " + restaurant);
						Log.d("JSON", "JSON sucursal = " + sucursal);
						Log.d("JSON", "JSON thumbnail = " + thumbnail);
						Log.d("JSON", "JSON address = " + address);
						Log.d("JSON", "JSON phone = " + phone);
						Log.d("JSON", "JSON hours = " + hours);
						Log.d("JSON", "JSON image = " + image);
						Log.d("JSON", "JSON webpage = " + webpage);
						Log.d("JSON", "JSON twitter = " + twitter);
						Log.d("JSON", "JSON facebook = " + facebook);
						
						db.addRestaurant(new Restaurant(id, category, xCoordinate, yCoordinate, checkin, restaurant,
							sucursal, thumbnail, address, phone, hours, image, webpage, twitter, facebook));					
						
						loading ++;
					}
					
					for(int i = 0; i < categories.length(); i++){
						downloadResources(loading, total);
						JSONObject c = categories.getJSONObject(i);
						Log.i("JSON" , "JSON = " + c.toString());

						int id = c.getInt(TAG_PID);
						String category = c.getString(TAG_CATEGORY);
						String thumbnail = c.getString(TAG_THUMBNAIL);
						
						Log.d("JSON", "JSON id = " + id);
						Log.d("JSON", "JSON category = " + category);
						Log.d("JSON", "JSON thumbnail = " + thumbnail);
						
						db.addCategory(new Category(id, category, thumbnail));					
						
						loading ++;
					}
				} 							
			} catch (JSONException e) {
				e.printStackTrace();
			}

			//return null;
			return 1234; /* Perhaps you want to return something to your post execute */
		}
	
		//private boolean resourcesDontAlreadyExist() {
			 //Here you would query your app's internal state to see if this download had been performed before
			 //Perhaps once checked save this in a shared preference for speed of access next time
			//return true; // returning true so we show the splash every time
		//}
	
	
		private void downloadResources(int i, int count) {
			int progress = (int) ((i / (float) count) * 100);
			publishProgress(progress);
			try { Thread.sleep(1000); } catch (InterruptedException ignore) {}
		}
	
		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			progressBar.setProgress(values[0]); // Esto se corrió en el subproceso de interfaz de usuario, así que es buena idea actualizar nuestra barra de progreso (una vista UI) aquí.
		}
	
		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			finishedListener.onTaskFinished(); // para avisar que ya se termino
		}
	}

}




