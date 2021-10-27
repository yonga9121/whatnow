package com.whatnow.ui.register;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.whatnow.R;
import com.whatnow.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class Fragment_Skills_4 extends Fragment {

    private Logger logger = Logger.getLogger("Skills");

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Button txtVideo;
    private VideoView videoDescripcion;

    //Definir el id del permiso
    private final int CAMERA_PERMISSION_ID = 101;
    private final int GALLERY_PERMISSION_ID = 102;
    String cameraPerm = Manifest.permission.CAMERA;

    //Para mostrar imagen/video de la camara
    VideoView imageView;
    String currentPhotoPath;

    // Create a Cloud Storage reference from the app
    FirebaseStorage storage = FirebaseStorage.getInstance();

    StorageReference storageRef = storage.getReference();

    private String token;
    private String ownerId;

    public String imageUrl;

    View rootView;

    public Fragment_Skills_4() {
        // Required empty public constructor
    }

    public static Fragment_Skills_4 newInstance(String param1, String param2) {
        Fragment_Skills_4 fragment = new Fragment_Skills_4();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        token = Utils.getString("token", this.getContext());
        ownerId = Utils.getString("owner_id", this.getContext());

        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment__skills_4, container, false);

        txtVideo = rootView.findViewById(R.id.txtVideo);
        imageView = rootView.findViewById(R.id.videoDescripcion);

        txtVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getActivity().getBaseContext(), cameraPerm)
                        == PackageManager.PERMISSION_GRANTED) {
                    videoHandler();
                } else {
                    logger.warning("Failed to getting the permission :(");
                }
            }
        });

        return rootView;
    }

    private void videoHandler() {
        Intent takePictureIntent = new Intent( MediaStore.ACTION_VIDEO_CAPTURE );
        if ( takePictureIntent != null) {
            File videoFile = null;
            try {
                videoFile = createVideoFile();
            } catch ( IOException e ) {
                System.out.println( "Error creating file." );
            }

            if (videoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getActivity(),
                        "com.whatnow.fileprovider",
                        videoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                //intent.putExtra( MediaStore.EXTRA_OUTPUT, videoUri );
                //takePictureIntent.putExtra( MediaStore.EXTRA_VIDEO_QUALITY, 1 );
                //takePictureIntent.putExtra( MediaStore.EXTRA_SIZE_LIMIT, 15000000L );
                //takePictureIntent.putExtra( MediaStore.EXTRA_DURATION_LIMIT,  90);
                startActivityForResult(takePictureIntent, CAMERA_PERMISSION_ID);
            }

        }
    }


    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                logger.warning(ex.getMessage());
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getActivity(),
                        "com.whatnow.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_PERMISSION_ID);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private File createVideoFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat( "yyyyMMdd_HHmmss" ).format( new Date() );
        String imageFileName = "MP4_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(
                Environment.DIRECTORY_PICTURES );
                File video = File.createTempFile(
                imageFileName,  /* prefix */
                ".mp4",         /* suffix */
                storageDir      /* directory */
        );

        //this.videoUri = Uri.fromFile( video );
        currentPhotoPath = Uri.fromFile( video ).getPath();
        //Intent mediaScanIntent = new Intent( Intent.ACTION_MEDIA_SCANNER_SCAN_FILE );
        //mediaScanIntent.setData( this.videoUri );
        //this.sendBroadcast( mediaScanIntent );
        return video;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode){
                case CAMERA_PERMISSION_ID:
                    //imageView.setImageURI(Uri.parse(currentPhotoPath));
                    imageView.setVideoURI(Uri.parse(currentPhotoPath));
                    imageView.requestFocus();
                    imageView.start();
/*
                    try {
                        // Start the MediaController
                        MediaController mediacontroller = new MediaController(
                                getContext());
                        mediacontroller.setAnchorView(imageView);
                        // Get the URL from String VideoURL
                        Uri video = Uri.parse(currentPhotoPath);
                        imageView.setMediaController(mediacontroller);
                        imageView.setVideoPath(currentPhotoPath);

                    } catch (Exception e) {
                        //Log.e("Error", e.getMessage());
                        e.printStackTrace();
                    }

                    imageView.requestFocus();
                    imageView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        // Close the progress bar and play the video
                        public void onPrepared(MediaPlayer mp) {
                            //pDialog.dismiss();
                            imageView.start();
                        }
                    });
*/
                    logger.info(" Capture successfully.");
                    putFiles();
                    break;
                case GALLERY_PERMISSION_ID:
                    Uri imageUri = data.getData();
                    //imageView.setImageURI(imageUri);
                    imageView.setVideoURI(imageUri);
                    logger.info(" Loaded successfully.");
                    putFiles();
                    break;
            }

        }
    }

    public void startGallery(){
        Intent pickGalleryImage = new Intent(Intent.ACTION_PICK);
        pickGalleryImage.setType("video/*");
        startActivityForResult(pickGalleryImage, GALLERY_PERMISSION_ID);
    }

    public void putFiles(){

        Uri file = Uri.fromFile(new File(currentPhotoPath));
        StorageReference riversRef = storageRef.child("users/" + ownerId + "/" + file.getLastPathSegment());

        // Register observers to listen for when the download is done or if it fails
        riversRef.putFile(file).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

                logger.warning("NO CARGO");
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                System.out.println(riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                System.out.println("Image URL: " + uri.toString());
                                imageUrl = uri.toString();
                            }
                        })
                );
                logger.warning("CARGA ARCHIVO");
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
            }
        });
    }
}
