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
    List<Restaurant> restaurants = null;
    databaseHandler db;
    private static final String[][] data = 
	{
		{"Pollo Loco","Starbucks","Carls Jr"},
		{"Mc Donalds","Costeñito"},
		{"KFC","Las Alitas","Super Salads"}
	};
  
    //constructor
    public SampleExpandableListAdapter(Context context, Activity activity) {
       // this.restaurants = restaurants;
        this.context = context;
        db = new databaseHandler(this.context);
        restaurants = db.getAllRestaurants();
        vi = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        _objInt = data.length;
    }

    /**getChild: obtiene el hijo del expandable list
     * @param groupPosition - posicion del padre al que pertenece
     * @param childPosition - posicion del mismo hijo
     * @return data
     */
    public String getChild(int groupPosition, int childPosition) {
        return data[groupPosition][childPosition];
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
        return data[groupPosition].length;
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
        String child = getChild(groupPosition, childPosition);
        int id_res = 0;
        
        Log.i("EXPANDABLE", "CHILD = " + childPosition);
        
        if(groupPosition == 0){
                if(childPosition == 0) id_res = R.drawable.polloloco;
                if(childPosition == 1) id_res = R.drawable.starbucks;
                if(childPosition == 2) id_res = R.drawable.carls;
        }
        else if(groupPosition == 1){
                if(childPosition == 0) id_res = R.drawable.mcdonalds;
                if(childPosition == 1) id_res = R.drawable.costeno;
        }
        else if(groupPosition == 2){
                if(childPosition == 0) id_res = R.drawable.kfc;
                if(childPosition == 1) id_res = R.drawable.alitas;
                if(childPosition == 2) id_res = R.drawable.salads;
        }
       
        if (child != null) {
            v = vi.inflate(CHILD_ITEM_RESOURCE, null);
            ViewHolder holder = new ViewHolder(v);
            holder.text.setText(Html.fromHtml(child));
            holder.imageview.setImageResource(id_res);
        }
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
        return data.length;
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
        int id_res = 0;
        long group_id = getGroupId(groupPosition);
       
        //Log.i("EXPANDABLE", "GROUP = " + groupPosition);
        
        if(group_id == 0){
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
        }
       
        if (group != null) {
            v = vi.inflate(GROUP_ITEM_RESOURCE, null);
            ViewHolder holder = new ViewHolder(v);

            holder.text.setText(Html.fromHtml(group));
            holder.imageview.setImageResource(id_res);
             
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