package com.example.webtoon;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.webtoon_app.R;

public class Login extends AppCompatActivity {
    EditText username,password;
    Button btnlogin , logreg;
    DBHelper DB;
    private static final int PICK_IMG=1;
    private ImageView pfpimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pfpimg = findViewById(R.id.imgpfp);
        username = findViewById(R.id.edunm);
        password = findViewById(R.id.edpass);
        logreg = findViewById(R.id.logreg);
        DB = new DBHelper(this);

        btnlogin = findViewById(R.id.btnlog);
        logreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(Login.this, "All Fields are Required", Toast.LENGTH_LONG).show();
                } else {
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if (checkuserpass) {
                        Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), Home.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_LONG).show();
                }

            }
        }
        });
    }
        public void onProfileImageClick(View view) {
            openImageChooser();
        }
        private void openImageChooser() {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMG);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == PICK_IMG && resultCode == Activity.RESULT_OK && data != null) {
// Get the selected image URI
                if (data.getData() != null) {
                    Uri selectedImageUri = data.getData();
                    try {
                        // Load and crop the image
                        Bitmap selectedBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                        Bitmap croppedBitmap = cropCircular(selectedBitmap);

                        // Set the cropped image to the ImageView
                        pfpimg.setImageBitmap(croppedBitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    private Bitmap cropCircular(Bitmap source) {
        int size = Math.min(source.getWidth(), source.getHeight());
        Bitmap output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(output);
        BitmapShader shader = new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);

        canvas.drawOval(new RectF(0, 0, size, size), paint);

        return output;
    }
}