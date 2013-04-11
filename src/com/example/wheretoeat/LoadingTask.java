package com.example.wheretoeat;

import android.os.AsyncTask;
//import android.util.Log;
//import android.widget.ProgressBar;

/**LoadingTask: 
 * Clase que carga tareas en segundo plano
 * @author Julia Ramos 1014993
 * @author Karen Olvera 807308
 *
 */
public class LoadingTask extends AsyncTask<String, Integer, Integer> {

	public interface LoadingTaskFinishedListener {
		void onTaskFinished(); // If you want to pass something back to the listener add a param to this method
	}

	@Override
	protected Integer doInBackground(String... params) {
		// TODO Auto-generated method stub
		return null;
	}

}