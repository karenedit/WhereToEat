package com.example.wheretoeat;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
/**MenuAdapater
 * Clase que se encarga de desplegar las imagenes de restaurantes en el gridview de Menu
 * @author Julia Ramos 1014993
 * @author Karen Olvera 807308
 *
 */
public class MenuAdapater extends BaseAdapter {

	//declaracion de variables globales
	private Context context;			
	private final String[] menuValues;		//arreglo con los valores de cada elemento del menu
	private final String[] precioValues;	//arreglo con los precios de cada elemento del menu
 
	public MenuAdapater(Context context, String[] menuValues, String[] precioValues) {
		this.context = context;
		//se asigan valores
		this.menuValues = menuValues;
		this.precioValues = precioValues;
	}
 
	/**getView
	 * 
	 * @param position
	 * @param convertView
	 * @param parent
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
 
		//declaracion e inicializacion de variables
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View gridView;
 
		if (convertView == null) {
			
			//se crea un nuevo View y se asigna a gridview
			gridView = new View(context);
 
			// obtiene el layout correspondiente
			gridView = inflater.inflate(R.layout.menu_item, null);
 
			// se asigna el texto del elemento del menu en la vista
			TextView menuItem = (TextView) gridView.findViewById(R.id.menuitemtxt);
			menuItem.setText(menuValues[position]);

			// se asigna el texto del precio del menu en la vista
			TextView precioitem = (TextView) gridView.findViewById(R.id.preciotxt);
			precioitem.setText(precioValues[position]);
 
			//se obtiene elemento a traves de su id
			ImageView imageView = (ImageView) gridView
					.findViewById(R.id.imageView1);
 
			String menu = menuValues[position];		
			
			//se asignan imagenes dependiendo del nombre de cada elemento del menu
			if (menu.equals("Santafe")) {
				imageView.setImageResource(R.drawable.santafe);
			} else if (menu.equals("Sandwich")) {
				imageView.setImageResource(R.drawable.sandwich);
			} else if (menu.equals("Caldo")) {
				imageView.setImageResource(R.drawable.caldo);
			} else {
				imageView.setImageResource(R.drawable.torta);
			}
 
		} else {
			gridView = (View) convertView;
		}
 
		return gridView;
	}
 
	@Override
	public int getCount() {
		return menuValues.length;
	}
 
	@Override
	public Object getItem(int position) {
		return null;
	}
 
	@Override
	public long getItemId(int position) {
		return 0;
	}
 
}