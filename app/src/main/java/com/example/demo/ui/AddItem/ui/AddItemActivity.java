package com.example.demo.ui.AddItem.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.TypeConverter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.databaseHelper.StockModel;
import com.example.demo.databaseHelper.StockViewModal;
import com.example.demo.ui.Categories;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddItemActivity extends AppCompatActivity {

    @BindView(R.id.tv_add_image)
    TextView tvAddImage;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_image_name)
    TextView tvImagename;

    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.spinner_category)
    Spinner spinner_category;
    @BindView(R.id.inStock)
    CheckBox inStock;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    StockModel stockModel;
    String title, spinner;
    String price;
    boolean stockIn;
    byte[] data1;
    int RESULT_LOAD_IMAGE = 100;
    String id;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        ButterKnife.bind(this);

        price = etPrice.getText().toString();
        title = etPrice.getText().toString();
        spinner = spinner_category.getSelectedItem().toString();
        stockIn = inStock.isChecked();

        if ((stockModel = (StockModel) getIntent().getSerializableExtra("stockList")) != null) {
            id = String.valueOf(stockModel.id);
            etTitle.setText(stockModel.title);
            spinner_category.setSelection(getIndex(spinner_category, stockModel.category));

            etPrice.setText(stockModel.price);
            if (stockModel.inStock) {
                inStock.isChecked();
            }
            btnSubmit.setText("Update");
        }
        tvAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "select a picture"), RESULT_LOAD_IMAGE);

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stockData();
            }
        });
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private int getIndex(Spinner spinner_category, String category) {
        for (int i = 0; i < spinner_category.getCount(); i++) {
            if (spinner_category.getItemAtPosition(i).toString().equalsIgnoreCase(category)) {
                return i;
            }
        }

        return 0;
    }

    private void stockData() {
        stockModel = new StockModel(getBitmapAsByteArray(bitmap),etTitle.getText().toString(), spinner_category.getSelectedItem().toString(), etPrice.getText().toString(), inStock.isChecked());
        StockViewModal viewModal = ViewModelProviders.of(this).get(StockViewModal.class);
        if (id != null) {
            stockModel.setId(Integer.parseInt(id));
            viewModal.update(stockModel);
            Toast.makeText(this, "Data Update", Toast.LENGTH_SHORT).show();
        } else {
            viewModal.insert(stockModel);
            Toast.makeText(this, "Data Save", Toast.LENGTH_SHORT).show();
        }


        Intent intent = new Intent(AddItemActivity.this, Categories.class);
        startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == RESULT_LOAD_IMAGE) {
                Uri selectedImage = data.getData();
                if (selectedImage != null) {
                    try {
                         bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        byte[] b = outputStream.toByteArray();
        return b;
    }


}