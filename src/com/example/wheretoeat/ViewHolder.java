package com.example.wheretoeat;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**ViewHolder: 
 * Clase que maneja la imagen y el texto correspondiente de cada parent y child del expandable list 
 * @author Julia Ramos 1014993
 * @author Karen Olvera 807308
 */
public class ViewHolder {

	 public TextView text;
	    public ImageView imageview;
	    public ViewHolder(View v) {
	        this.text = (TextView)v.findViewById(R.id.titCategoria);
	        this.imageview = (ImageView)v.findViewById(R.id.imgCategoria);
	    }

}
