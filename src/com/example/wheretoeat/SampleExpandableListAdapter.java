package com.example.wheretoeat;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.util.Log;
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
    	Log.i("CATEGORIES", "CATEGORIES = " + categories.size());
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
        //String child = getChild(groupPosition, childPosition);
        //String child = null;
        int category = categories.get(groupPosition).id;
        //int id_res = 0;
        String restaurantName = "";
        
        Log.i("EXPANDABLE", "CHILD = " + childPosition);
        Log.i("EXPANDABLE", "CHILD CATEGORY = " + category);

		switch(category){
			case 1: 
				restaurantName = comidaRapida.get(childPosition).restaurant + " " + comidaRapida.get(childPosition).sucursal;
				break;
			case 2: 
				restaurantName = comidaOriental.get(childPosition).restaurant + " " + comidaOriental.get(childPosition).sucursal;
				break;
			case 3: 
				restaurantName = ensaladas.get(childPosition).restaurant + " " + ensaladas.get(childPosition).sucursal;
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
		
		v = vi.inflate(CHILD_ITEM_RESOURCE, null);
        ViewHolder holder = new ViewHolder(v);
        holder.text.setText(Html.fromHtml(restaurantName));
        holder.imageview.setImageResource(R.drawable.salads);
    	
        /*if (child != null) {
            v = vi.inflate(CHILD_ITEM_RESOURCE, null);
            ViewHolder holder = new ViewHolder(v);
            holder.text.setText(Html.fromHtml("Hola"));
            holder.imageview.setImageResource(R.drawable.salads);
        }*/
        
        /*if(groupPosition == 0){
        	if (child != null) {
                v = vi.inflate(CHILD_ITEM_RESOURCE, null);
                ViewHolder holder = new ViewHolder(v);
                holder.text.setText(Html.fromHtml("Hola"));
                holder.imageview.setImageResource(R.drawable.salads);
            }
        }else if(groupPosition == 1){
        	if (child != null) {
                v = vi.inflate(CHILD_ITEM_RESOURCE, null);
                ViewHolder holder = new ViewHolder(v);
                holder.text.setText(Html.fromHtml("Hola"));
                holder.imageview.setImageResource(R.drawable.salads);
            }
        }else if(groupPosition == 2){
        	if (child != null) {
                v = vi.inflate(CHILD_ITEM_RESOURCE, null);
                ViewHolder holder = new ViewHolder(v);
                holder.text.setText(Html.fromHtml("Hola"));
                holder.imageview.setImageResource(R.drawable.salads);
            }
        }*/

        return v;
    }
    
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
        //int id_res = 0;
        //long group_id = getGroupId(groupPosition);
       
        Log.i("EXPANDABLE", "GROUP = " + groupPosition);
       /* if(group_id == 0){
                group = "Cafeterias";
                id_res = R.drawable.cafe;
        }
        else if(group_id == 1){
                group = "Pasteles";
                id_res = R.drawable.pasteleria;
        }
        else if(group_id == 2){
                group = "FastFood";
                id_res = R.drawable.comidarapida;
        }*/
       
        group = categories.get(groupPosition).category;
        Log.i("CATEGORIES", "CATEGORY = " + group);
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