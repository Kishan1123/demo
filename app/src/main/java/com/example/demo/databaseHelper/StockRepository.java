package com.example.demo.databaseHelper;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import java.util.List;

public class StockRepository {

    // below line is the create a variable
    // for dao and list for all courses.
    private Dao dao;
    private LiveData<List<StockModel>> allCourses;
  
    // creating a constructor for our variables
    // and passing the variables to it.
    public StockRepository(Application application) {
        StockDatabase database = StockDatabase.getInstance(application);
        dao = database.Dao();
        allCourses = dao.getAllHistory();
    }
  
    // creating a method to insert the data to our database.
    public void insert(StockModel model) {
        new InsertHistoryAsyncTask(dao).execute(model);
    }
  
    // creating a method to update data in database.
    public void update(StockModel model) {
        new UpdateHistoryAsyncTask(dao).execute(model);
    }
  
    // creating a method to delete the data in our database.
    public void delete(StockModel model) {
        new DeleteHistoryAsyncTask(dao).execute(model);
    }
  
    // below is the method to delete all the courses.
    public void deleteAllHistory() {
        new DeleteAllHistoryAsyncTask(dao).execute();
    }
  
    // below method is to read all the courses.
    public LiveData<List<StockModel>> getAllHistory() {
        return allCourses;
    }
  
    // we are creating a async task method to insert new course.
    private static class InsertHistoryAsyncTask extends AsyncTask<StockModel, Void, Void> {
        private Dao dao;
  
        private InsertHistoryAsyncTask(Dao dao) {
            this.dao = dao;
        }
  
        @Override
        protected Void doInBackground(StockModel... model) {
            // below line is use to insert our modal in dao.
            dao.insert(model[0]);
            return null;
        }
    }
  
    // we are creating a async task method to update our course.
    private static class UpdateHistoryAsyncTask extends AsyncTask<StockModel, Void, Void> {
        private Dao dao;
  
        private UpdateHistoryAsyncTask(Dao dao) {
            this.dao = dao;
        }
  
        @Override
        protected Void doInBackground(StockModel... models) {
            // below line is use to update
            // our modal in dao.
            dao.update(models[0]);
            return null;
        }
    }
  
    // we are creating a async task method to delete course.
    private static class DeleteHistoryAsyncTask extends AsyncTask<StockModel, Void, Void> {
        private Dao dao;
  
        private DeleteHistoryAsyncTask(Dao dao) {
            this.dao = dao;
        }
  
        @Override
        protected Void doInBackground(StockModel... models) {
            // below line is use to delete 
            // our course modal in dao.
            dao.delete(models[0]);
            return null;
        }
    }
  
    // we are creating a async task method to delete all courses.
    private static class DeleteAllHistoryAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao dao;
        private DeleteAllHistoryAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            // on below line calling method
            // to delete all courses.
            dao.deleteAllHistory();
            return null;
        }
    }
}