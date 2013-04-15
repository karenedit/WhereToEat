package com.example.wheretoeat;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Clase DatabaseHandler que realiza todas las operaiones entre la aplicacion y la base de datos
 * @author Karen
 *
 */
public class databaseHandler extends SQLiteOpenHelper{
	
	// Database Version
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "restaurantsManager";
	// database tables name
	private static final String TABLE_RESTAURANTS = "restaurants";
	private static final String TABLE_CATEGORIES = "categories";
	
	// Restaurants table columns names
	private static final String KEY_PID = "pid";
	private static final String KEY_RESTAURANT = "restaurant";
	private static final String KEY_SUCURSAL = "sucursal";
	private static final String KEY_CATEGORY = "category";
	private static final String KEY_THUMBNAIL = "thumbnail";
	private static final String KEY_XCOORDINATE = "xCoordinate";
	private static final String KEY_YCOORDINATE = "yCoordinate";
	private static final String KEY_ADDRESS = "address";
	private static final String KEY_PHONE = "phone";
	private static final String KEY_HOURS = "hours";
	private static final String KEY_IMAGE = "image";
	private static final String KEY_WEBPAGE = "webpage";
	private static final String KEY_TWITTER = "twitter";
	private static final String KEY_FACEBOOK = "facebook";
	private static final String KEY_CHECKIN = "checkin";
	
	//Categories table columns names
	private static final String KEY_PID_CATEGORY = "pid";
	private static final String KEY_CATEGORY_NAME = "category";
	private static final String KEY_CATEGORY_THUMB = "thumbnail";
	
	public databaseHandler(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	/**
	 * Metodo que crea una tabla en la base de datos
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_RESTAURANTS_TABLE = "CREATE TABLE " + TABLE_RESTAURANTS + "(" + 
				KEY_PID + " INTEGER PRIMARY KEY," +
				KEY_RESTAURANT +" TEXT," + 
				KEY_SUCURSAL +" TEXT," + 
				KEY_CATEGORY +" INTEGER," + 
				KEY_THUMBNAIL +" TEXT," +
				KEY_XCOORDINATE +" INTEGER," +
				KEY_YCOORDINATE +" INTEGER," +
				KEY_ADDRESS +" TEXT," +
				KEY_PHONE +" TEXT," +
				KEY_HOURS +" TEXT," +
				KEY_IMAGE +" TEXT," +
				KEY_WEBPAGE +" TEXT," +
				KEY_TWITTER +" TEXT," +
				KEY_FACEBOOK +" TEXT," +
				KEY_CHECKIN + " INTEGER" + ")";
		
		String CREATE_CATEGORY_TABLE = "CREATE TABLE " + TABLE_CATEGORIES + "(" + 
				KEY_PID_CATEGORY + " INTEGER PRIMARY KEY," +
				KEY_CATEGORY_NAME + " TEXT," + 
				KEY_CATEGORY_THUMB + " TEXT" + ")";
				
		db.execSQL(CREATE_RESTAURANTS_TABLE);
		db.execSQL(CREATE_CATEGORY_TABLE);
	}
	
	/**
	 * Metodo que actualiza una tabla en la base de datos
	 * @param db
	 * @param oldVersion
	 * @param newVersion
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		db.execSQL("DROP TABLE IF EXISTS "+ TABLE_RESTAURANTS); // Drop older table if existed 
		db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CATEGORIES);
		onCreate(db); //Create tables again
	}
	
	/**
	 * Metodo que agrega un nuevo restaurante a la base de datos
	 * @param contact
	 */
	void addRestaurant(Restaurant restaurant){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		Log.i("INSERT", "INSERT = " + restaurant.getId());
		values.put(KEY_PID, restaurant.getId());		
		values.put(KEY_RESTAURANT, restaurant.getRestaurant());
		values.put(KEY_SUCURSAL, restaurant.getSucursal());
		values.put(KEY_CATEGORY, restaurant.getCategory());
		values.put(KEY_THUMBNAIL, restaurant.getThumbnail());
		values.put(KEY_XCOORDINATE, restaurant.getXCoordinate());
		values.put(KEY_YCOORDINATE, restaurant.getYCoordinate());
		values.put(KEY_ADDRESS, restaurant.getAddress());
		values.put(KEY_PHONE, restaurant.getPhone());
		values.put(KEY_HOURS, restaurant.getHours());
		values.put(KEY_IMAGE, restaurant.getImage());
		values.put(KEY_WEBPAGE, restaurant.getWebpage());
		values.put(KEY_TWITTER, restaurant.getTwitter());
		values.put(KEY_FACEBOOK, restaurant.getFacebook());
		values.put(KEY_CHECKIN, restaurant.getCheckin());
		
		db.insert(TABLE_RESTAURANTS, null, values);
		db.close(); //Closing database connection
	}
	
	/**
	 * Metodo que agrega una nueva categoria a la base de datos
	 * @param category
	 */
	void addCategory(Category category){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		Log.i("INSERT", "INSERT = " + category.getId());
		values.put(KEY_PID_CATEGORY, category.getId());		
		values.put(KEY_CATEGORY_NAME, category.getCategory());
		values.put(KEY_CATEGORY_THUMB, category.getThumbnail());
		
		db.insert(TABLE_CATEGORIES, null, values);
		db.close(); //Closing database connection
	}
	
	/**
	 * Metodo que regresa un contacto de la base de datos el cual pertenece al id que se 
	 * envia como parametro
	 * @param id 
	 * @return contact
	 */
	//Getting single contact
	Restaurant getRestaurant(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_RESTAURANTS, 
				new String[]{KEY_PID, KEY_RESTAURANT, KEY_SUCURSAL, KEY_CATEGORY, KEY_THUMBNAIL, KEY_XCOORDINATE,
				KEY_YCOORDINATE, KEY_ADDRESS, KEY_PHONE, KEY_HOURS, KEY_IMAGE, KEY_WEBPAGE, KEY_TWITTER, KEY_FACEBOOK,
				KEY_CHECKIN}, KEY_PID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
		
		if (cursor != null) cursor.moveToFirst();
		
		Restaurant restaurant = new Restaurant(
				Integer.parseInt(cursor.getString(0)), 
				Integer.parseInt(cursor.getString(1)), 
				Integer.parseInt(cursor.getString(2)), 
				Integer.parseInt(cursor.getString(3)), 
				Integer.parseInt(cursor.getString(4)), 
				cursor.getString(5), 
				cursor.getString(6), 
				cursor.getString(7), 
				cursor.getString(8), 
				cursor.getString(9), 
				cursor.getString(10), 
				cursor.getString(11), 
				cursor.getString(12), 
				cursor.getString(13), 
				cursor.getString(14)
		);
		
		return restaurant; //return restaurant
	}
	
	/**
	 * Metodo que regresa una lista con todos los restaurantes de la base de datos
	 * @return List Restaurant
	 */
	public List<Restaurant> getAllRestaurants() {
		List<Restaurant> restaurantsList = new ArrayList<Restaurant>();
		//Select All Query
		String selectQuery = "SELECT * FROM " + TABLE_RESTAURANTS;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do{
				Restaurant restaurant = new Restaurant();
				restaurant.setId(Integer.parseInt(cursor.getString(0)));
				restaurant.setRestaurant(cursor.getString(1));
				restaurant.setSucursal(cursor.getString(2));
				restaurant.setCategory(Integer.parseInt(cursor.getString(3)));
				restaurant.setThumbnail(cursor.getString(4));
				restaurant.setXCoordinate(Integer.parseInt(cursor.getString(5)));
				restaurant.setYCoordinate(Integer.parseInt(cursor.getString(6)));
				restaurant.setAddress(cursor.getString(7));
				restaurant.setPhone(cursor.getString(8));
				restaurant.setHours(cursor.getString(9));
				restaurant.setImage(cursor.getString(10));
				restaurant.setWebpage(cursor.getString(11));
				restaurant.setTwitter(cursor.getString(12));
				restaurant.setFacebook(cursor.getString(13));
				restaurant.setCheckin(Integer.parseInt(cursor.getString(14)));
				//Adding contact to list
				restaurantsList.add(restaurant);
			}while(cursor.moveToNext());
		}
		//return contact list
		return restaurantsList;
	}
	
	/**
	 * Metodo que regresa una lista con todos los restaurantes de la base de datos
	 * @return List Restaurant
	 */
	public List<Category> getAllCategories() {
		List<Category> categoriesList = new ArrayList<Category>();
		//Select All Query
		String selectQuery = "SELECT * FROM " + TABLE_CATEGORIES;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do{
				Category category = new Category();
				category.setId(Integer.parseInt(cursor.getString(0)));
				category.setCategory(cursor.getString(1));
				category.setThumbnail(cursor.getString(2));
				
				categoriesList.add(category);
			}while(cursor.moveToNext());
		}
		
		return categoriesList;
	}
	
	/**
	 * Metodo que borra el restaurante pasado como parametro de la base de datos
	 * @param contact
	 */
	//Deleting single contact
	public void deleteRestaurant(Restaurant restaurant){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_RESTAURANTS, KEY_PID +"=?", new String[]{String.valueOf(restaurant.getId())});
		db.close();
	}
	
	/**
	 * Metodo que regresa la cantidad de restaurantes en la base de datos
	 * @return count
	 */
	//Getting contacts Count
	public int getRestaurantsCount(){
		int count = 0;
		String countQuery = "SELECT * FROM " + TABLE_RESTAURANTS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		count = cursor.getCount();
		cursor.close();
		return count;
	}
	
	public List<Restaurant> getByCategory(int category){
		List<Restaurant> restaurantsList = new ArrayList<Restaurant>();
		//Select All Query
		String selectQuery = "SELECT * FROM " + TABLE_RESTAURANTS + " WHERE " + KEY_CATEGORY + " = " + category;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do{
				Restaurant restaurant = new Restaurant();
				restaurant.setId(Integer.parseInt(cursor.getString(0)));
				restaurant.setRestaurant(cursor.getString(1));
				restaurant.setSucursal(cursor.getString(2));
				restaurant.setCategory(Integer.parseInt(cursor.getString(3)));
				restaurant.setThumbnail(cursor.getString(4));
				restaurant.setXCoordinate(Integer.parseInt(cursor.getString(5)));
				restaurant.setYCoordinate(Integer.parseInt(cursor.getString(6)));
				restaurant.setAddress(cursor.getString(7));
				restaurant.setPhone(cursor.getString(8));
				restaurant.setHours(cursor.getString(9));
				restaurant.setImage(cursor.getString(10));
				restaurant.setWebpage(cursor.getString(11));
				restaurant.setTwitter(cursor.getString(12));
				restaurant.setFacebook(cursor.getString(13));
				restaurant.setCheckin(Integer.parseInt(cursor.getString(14)));
				//Adding contact to list
				restaurantsList.add(restaurant);
			}while(cursor.moveToNext());
		} else {return null;}
		
		return restaurantsList;
	}
}