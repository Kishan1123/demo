package com.example.demo.databaseHelper;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class StockViewModal extends AndroidViewModel {
      
    // creating a new variable for course repository.
    private StockRepository repository;
     
    // below line is to create a variable for live 
    // data where all the courses are present.
    private LiveData<List<StockModel>> allHistory;
  
    // constructor for our view modal.
    public StockViewModal(@NonNull Application application) {
        super(application);
        repository = new StockRepository(application);
        allHistory = repository.getAllHistory();
    }
      
    // below method is use to insert the data to our repository.
    public void insert(StockModel model) {
        repository.insert(model);
    }
  
    // below line is to update data in our repository.
    public void update(StockModel model) {
        repository.update(model);
    }
  
    // below line is to delete the data in our repository.
    public void delete(StockModel model) {
        repository.delete(model);
    }
  
    // below method is to delete all the courses in our list.
    public void deleteAllCourses() {
        repository.getAllHistory();
    }
  
    // below method is to get all the courses in our list.
    public LiveData<List<StockModel>> getAllStock() {
        return allHistory;
    }
}