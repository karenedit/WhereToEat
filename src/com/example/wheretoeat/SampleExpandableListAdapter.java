package com.example.wheretoeat;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;

/**SampleExpandableListAdapter: 
 * Clase que se encarga de manejar los elementos del expandable list
 * @author Julia Ramos 1014993
 * @author Karen Olvera 807308
 */
public class SampleExpandableListAdapter extends BaseExpandableListAdapter implements ExpandableListAdapter  {
    
	//declaracion de variables
	public Context context;
    private LayoutInflater vi;
    //private String[][] data;
    int _objInt;
    private static final int GROUP_ITEM_RESOURCE = R.layout.groupitem;
    private static final int CHILD_ITEM_RESOURCE = R.layout.explistchild;
    List<Category> categories = null;
    
    List<Restaurant> comidaRapida = null;
    List<Restaurant> comidaOriental = null;
    List<Restaurant> ensaladas = null;
    List<Restaurant> cafeterias = null;
    List<Restaurant> pastelerias = null;
    List<Restaurant> buffet = null;
    List<Restaurant> mariscos = null;
    List<Restaurant> barGrill = null;
    List<Restaurant> comidaItaliana = null;
    List<Restaurant> comidaMexicana = null;
    List<Restaurant> pizzas = null;
    List<Restaurant> otros = null;
   
    databaseHandler db;
    DrawableManager imagemg;
   
    String imageHttpAddress = "http://geekode.systheam.com/images/carlss.png";
  
    //constructor
    public SampleExpandableListAdapter(Context context, Activity activity) {
       // this.restaurants = restaurants;
        this.context = context;
        db = new databaseHandler(this.context);
        updateRestaurants();
        //restaurants = db.getAllRestaurants();
        vi = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        _objInt = categories.size();
    }
    
    public void updateRestaurants(){
    	int category = 0;
    	categories = db.getAllCategories();
    	for(int i = 0; i < categories.size(); i++){
    		category = categories.get(i).id;
    		switch(category){
    			case 1: comidaRapida = db.getByCategory(1); break;
    			case 2: comidaOriental = db.getByCategory(2); break;
    			case 3: ensaladas = db.getByCategory(3); break;
    			case 4: cafeterias = db.getByCategory(4); break;
    			case 5: pastelerias = db.getByCategory(5); break;
    			case 6: buffet = db.getByCategory(6); break;
    			case 7: mariscos = db.getByCategory(7); break;
    			case 8: barGrill = db.getByCategory(8); break;
    			case 9: comidaItaliana = db.getByCategory(9); break;
    			case 10: comidaMexicana = db.getByCategory(10); break;
    			case 11: pizzas = db.getByCategory(11); break;
    			case 12: otros = db.getByCategory(12); break;
    		}
    	}
    }

    /**getChild: obtiene el hijo del expandable list
     * @param groupPosition - posicion del padre al que pertenece
     * @param childPosition - posicion del mismo hijo
     * @return data
     */
    public String getChild(int groupPosition, int childPosition) {
        //return data[groupPosition][childPosition];
        return "PRueba";
    }

    /**getChildId : obtiene el id un hijo del expandable list
     * @param groupPosition - posicion del padre al que pertenece
     * @param childPosition - posicion del mismo hijo
     * @return childPosition 
     */
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**getChildrenCount : obtiene el numero de hijos del expandable list
     * @param groupPosition - posicion del padre al que pertenece
     * 
     * @return data 
     */
    public int getChildrenCount(int groupPosition) {
        //return data[groupPosition].length;
        int count = 0;
        int category = categories.get(groupPosition).id;
    	switch(category){
			case 1: count = comidaRapida.size(); break;
			case 2: count = comidaOriental.size(); break;
			case 3: count = ensaladas.size(); break;
			case 4: count = cafeterias.size(); break;
			case 5: count = pastelerias.size(); break;
			case 6: count = buffet.size(); break;
			case 7: count = mariscos.size(); break;
			case 8: count = barGrill.size(); break;
			case 9: count = comidaItaliana.size(); break;
			case 10: count = comidaMexicana.size(); break;
			case 11: count = pizzas.size(); break;
			case 12: count = otros.size(); break;
		}
        return count;
    }


    /**getChildView : obtiene la vista del hijo
     * @param groupPosition - posicion del padre al que pertenece
     * @param childPosition - posicion del mismo hijo
     * @param isLastChild - boleano si es o no el ultimo hijo
     * @param convertView
     * @param parent - vista del padre del expandable list
     * @return convertView 
     */
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View v = convertView;
        int category = categories.get(groupPosition).id;
        String restaurantName = "";
        String loadedImage = "";
        
        v = vi.inflate(CHILD_ITEM_RESOURCE, null);
        final ViewHolder holder = new ViewHolder(v);
        
		switch(category){
			case 1: 
				restaurantName = comidaRapida.get(childPosition).restaurant + " " + comidaRapida.get(childPosition).sucursal;
				loadedImage = comidaRapida.get(childPosition).thumbnail;
				break;
			case 2: 
				restaurantName = comidaOriental.get(childPosition).restaurant + " " + comidaOriental.get(childPosition).sucursal;
				break;
			case 3: 
				restaurantName = ensaladas.get(childPosition).restaurant + " " + ensaladas.get(childPosition).sucursal;
				loadedImage = ensaladas.get(childPosition).thumbnail;
				break;
			case 4: 
				restaurantName = cafeterias.get(childPosition).restaurant + " " + cafeterias.get(childPosition).sucursal;
				break;
			case 5: 
				restaurantName = pastelerias.get(childPosition).restaurant + " " + pastelerias.get(childPosition).sucursal;
				break;
			case 6: 				
				restaurantName = buffet.get(childPosition).restaurant + " " + buffet.get(childPosition).sucursal;
				break;
			case 7: 
				restaurantName = mariscos.get(childPosition).restaurant + " " + mariscos.get(childPosition).sucursal;
				break;
			case 8: 
				restaurantName = barGrill.get(childPosition).restaurant + " " + barGrill.get(childPosition).sucursal;
				break;
			case 9: 
				restaurantName = comidaItaliana.get(childPosition).restaurant + " " + comidaItaliana.get(childPosition).sucursal;
				break;
			case 10: 
				restaurantName = comidaMexicana.get(childPosition).restaurant + " " + comidaMexicana.get(childPosition).sucursal;
				break;
			case 11: 
				restaurantName = pizzas.get(childPosition).restaurant + " " + pizzas.get(childPosition).sucursal;
				break;
			case 12: 
				restaurantName = otros.get(childPosition).restaurant + " " + otros.get(childPosition).sucursal;
				break;
		}
				
        holder.text.setText(Html.fromHtml(restaurantName));
        holder.imageview.setImageResource(context.getResources().getIdentifier(loadedImage, "drawable", context.getPackageName()));
        //holder.imageview.setImageResource(R.drawable.salads);
        //holder.imageview.setImageBitmap(loadedImage);
        //holder.imageview.setImageDrawable(loadedImage);

        return v;
    }
    
    /*private InputStream OpenHttpConnection(String urlString) throws IOException{
    	InputStream in = null; 
    	int response = -1;
    	                
    	URL url = new URL(urlString); 
    	URLConnection conn = url.openConnection();
    	                  
    	if (!(conn instanceof HttpURLConnection)) throw new IOException("Not an HTTP connection");
    	         
    	try{
    		HttpURLConnection httpConn = (HttpURLConnection) conn;
    	    Log.i("Connecting ... ", "Connection = " + httpConn);
    		httpConn.setAllowUserInteraction(false);
    	    httpConn.setInstanceFollowRedirects(true);
    	    httpConn.setRequestMethod("GET");
    	    httpConn.connect(); 
    	 
    	    response = httpConn.getResponseCode();                 
    	    if (response == HttpURLConnection.HTTP_OK) {
    	    	in = httpConn.getInputStream();                                 
    	    }                     
    	} catch (Exception ex){
    		throw new IOException("Error connecting");            
    	}
    	
    	return in;     
    }
    	    
    private Bitmap DownloadImage(String URL){        
    	Bitmap bitmap = null;
    	InputStream in = null;        
    	try {
    		in = OpenHttpConnection(URL);
    		bitmap = BitmapFactory.decodeStream(in);
    	    in.close();
    	} catch (IOException e1) {
    		// TODO Auto-generated catch block
    		e1.printStackTrace();
    	}
    	        
    	return bitmap;                
    }*/
    
    /**getGroup : obtiene el grupo (Parent o Child)
     * @param groupPosition - posicion del grupo
     * 
     * @return nombre del grupo 
     */
    public String getGroup(int groupPosition) {
        return "group-" + groupPosition;
    }
    
    /**getGroup : obtiene el numero de grupos
     * @param void
     * 
     * @return cantidad de grupos 
     */
    public int getGroupCount() {
        return categories.size();
    }
    
    /**getGroupId : obtiene el id del grupo
     * @param groupPosition - posicion del grupo
     * 
     * @return posicion del grupo 
     */
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    
    /**getGroupView : obtiene la vista del padre(grupo)
     * @param groupPosition - posicion del padre al que pertenece
     * @param isExpanded - si esta expandido o no
     * @param convertView
     * @param parent - vista del padre del expandable list
     * @return convertView 
     */
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View v = convertView;
        String group = null;
       
        group = categories.get(groupPosition).category;
        if (group != null) {
            v = vi.inflate(GROUP_ITEM_RESOURCE, null);
            ViewHolder holder = new ViewHolder(v);
            holder.text.setText(Html.fromHtml(group));
            holder.imageview.setImageResource(R.drawable.cafe);
        }
        
        return v;
    }
    
    
    /**isChildSelectable : para saber si esta seleccionado o no
     * @param groupPosition - posicion del grupo
     * @param childPosition - posicion del hijo
     * 
     * @return boleano-true o false
     */
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    
    /**hasStableIds
     * 
     * @param void
     * 
     * @return boleano-true o false
     */
    public boolean hasStableIds() {
        return true;
    }
    
}