package com.example.wheretoeat;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
 

/**RestauranteAdapter
 * Clase que se encarga de desplegar las imagenes de restaurantes en el gridview de Vista General
 * 
 * @author Julia Ramos 1014993
 * @author Karen Olvera 807308
 *
 */
public class RestauranteAdapter extends BaseAdapter {
    private Context mContext;
    databaseHandler db;
    List<Restaurant> restaurant = null;
    
    // arreglo de las imagenes
   // public Integer[] mThumbIds = {
    //		  R.drawable.alitas,
      //        R.drawable.kfc,
        //      R.drawable.carls,
          //    R.drawable.mcdonalds,
            //  R.drawable.polloloco,
        //      R.drawable.salads,
          //    R.drawable.starbucks
    //};
 
    // Constructor
    public RestauranteAdapter(Context c){
        mContext = c;
        db = new databaseHandler(mContext);
        restaurant = db.getAllRestaurants(); 
    }
 
    @Override
    public int getCount() {
        //return mThumbIds.length;
    	return restaurant.size();
    }
 
    @Override
    public Object getItem(int position) {
        //return mThumbIds[position];
    	return restaurant.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return 0;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String loadedImage = restaurant.get(position).thumbnail;
    	
    	//se crea una nueva ImageView
    	ImageView imageView = new ImageView(mContext);
    	//se asigna un valor del arreglo
        imageView.setImageResource(mContext.getResources().getIdentifier(loadedImage, "drawable", mContext.getPackageName()));
        //se asigna una escala
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //se asignan parametros del layout
        imageView.setLayoutParams(new GridView.LayoutParams(70, 70));
        //se asigna padding entre imagenes
        imageView.setPadding(5, 5, 5, 5);

        //se regresa el imageView
        return imageView;
    }
 
}