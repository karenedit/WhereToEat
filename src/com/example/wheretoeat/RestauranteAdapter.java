package com.example.wheretoeat;
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
    
    // arreglo de las imagenes
    public Integer[] mThumbIds = {
    		  R.drawable.alitas,
              R.drawable.kfc,
              R.drawable.carls,
              R.drawable.mcdonalds,
              R.drawable.polloloco,
              R.drawable.salads,
              R.drawable.starbucks
    };
 
    // Constructor
    public RestauranteAdapter(Context c){
        mContext = c;
    }
 
    @Override
    public int getCount() {
        return mThumbIds.length;
    }
 
    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }
 
    @Override
    public long getItemId(int position) {
        return 0;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //se crea una nueva ImageView
    	ImageView imageView = new ImageView(mContext);
    	//se asigna un valor del arreglo
        imageView.setImageResource(mThumbIds[position]);
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