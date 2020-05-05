//package in_a_nutshell.com.socialresponsabilityapp.Utils;
//
//import android.app.Activity;
//        import android.content.Intent;
//        import android.graphics.Bitmap;
//        import android.provider.MediaStore;
//        import android.support.v7.app.AppCompatActivity;
//        import android.os.Bundle;
//        import android.view.View;
//        import android.widget.Button;
//        import android.widget.ImageView;
//
//        import java.nio.file.Path;
//
//import in_a_nutshell.com.socialresponsabilityapp.R;
//
//
//public class MainActivity extends AppCompatActivity {
//
//    private final int CAMERA_REQUEST =10;
//    ImageView image ;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        PermissionsUtil.checkCameraPermission(MainActivity.this);
//        Button perm = (Button) findViewById(R.id.anonymousBtn);
//        //image = (ImageView) findViewById(R.id.anonymousBtn);
//
//        perm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                takePicture();
//            }
//        });
//
//
//
//
//
//
//    }
//
//    public void takePicture(){
//        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(cameraIntent, CAMERA_REQUEST);
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode == RESULT_OK){
//            if(requestCode == CAMERA_REQUEST){
//                Bitmap cameraImage = (Bitmap) data.getExtras().get("data");
//                image.setImageBitmap(cameraImage);
//
//            }
//
//        }
//    }
//}
