package com.example.alton.resumebuilder;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Handler;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;

public  class TemplateOne extends Activity {

    EditText fname, fcontent;
    EditText fcontentphone, fcontentemail, fcontentaddress, fcontentobjective;
    EditText fcontenteducation, fcontentcoursework, fcontenttechskill, fcontentexperience, fcontentproject;
    Button write;
    ProgressDialog progressDialog;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_template_one, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.exit:
                this.finish();
                break;
            case R.id.clear:
                this.finish();
                startActivity(new Intent(this,TemplateOne.class));
                break;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_one);
        fname = (EditText) findViewById(R.id.fname);
        fcontent = (EditText) findViewById(R.id.ftext);
        write = (Button) findViewById(R.id.btnwrite);
        progressDialog = new ProgressDialog(this);
        // edited part after modification
        fcontentphone = (EditText) findViewById(R.id.fphone);
        fcontentemail = (EditText) findViewById(R.id.femail);
        fcontentaddress = (EditText) findViewById(R.id.faddress);
        fcontentobjective = (EditText) findViewById(R.id.fobjective);

        fcontenteducation = (EditText) findViewById(R.id.feducation);
        fcontentcoursework = (EditText) findViewById(R.id.fcoursework);
        fcontenttechskill = (EditText) findViewById(R.id.ftechskill);
        fcontentexperience = (EditText) findViewById(R.id.fexperience);
        fcontentproject = (EditText) findViewById(R.id.fproject);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        //Main part of coding begins
        //Writing content to pdf
        write.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                new async().execute();


            }
        });

        //Display the pdf
        Button viewButton = (Button) findViewById(R.id.viewsd);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename =  fname.getText().toString();
                if(fname.getText().toString().contentEquals(""))
                {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(TemplateOne.this);
                    builder.setMessage("Enter the name of the pdf to view");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                //    Toast.makeText(TemplateOne.this, "Enter PDF name to view pdf file", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        File file = new File("/sdcard/"+ filename +".pdf");
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile(file), "application/pdf");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        startActivity(intent);

                    }

                }
        });

        //Share pdf with others
        Button mButton = (Button) findViewById(R.id.mailbutton);
        mButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                try {

                    String emailAddress=fcontentemail.getText().toString();
                    String filepath=fname.getText().toString();

                    if(fname.getText().toString().contentEquals(""))
                    {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(TemplateOne.this);
                        builder.setMessage("Enter the name of the pdf to share");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        //Toast.makeText(TemplateOne.this, "Enter the pdf name to be shared.", Toast.LENGTH_SHORT).show();
                    }
                    else
                        {
                            final String path = "sdcard/" + filepath + ".pdf";

//                    // final String path = Environment.getExternalStorageDirectory().toString()+ "/sdcard/"+ getFileStreamPath() ".pdf";

                            File file = new File(path);
                            Uri uri = Uri.fromFile(file);

                            // Uri uri = Uri.fromFile(new File(externalStorage.getAbsolutePath() + "/sdcard/" + filepath +".pdf"));

                            Intent emailIntent = new Intent(Intent.ACTION_SEND);
                            emailIntent.putExtra(Intent.EXTRA_EMAIL, emailAddress);
                            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                            emailIntent.putExtra(Intent.EXTRA_TEXT, "Text");
                            emailIntent.setType("application/pdf");
                            emailIntent.putExtra(Intent.EXTRA_STREAM, uri);

                            startActivity(Intent.createChooser(emailIntent, "Share using"));

                        }

                                    }
                catch (Exception e){
                    Toast.makeText(getBaseContext(),"Failed to attach",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

private class async extends AsyncTask<String,Void,String>{
    Handler mHandler;
    @Override
    protected String doInBackground(String... strings) {
        final String filename = fname.getText().toString();

        final String filecontent = fcontent.getText().toString();
        // Modified part
        final String filephone = fcontentphone.getText().toString();
        final String fileemail = fcontentemail.getText().toString();
        final String fileaddress = fcontentaddress.getText().toString();
        final String fileobjective = fcontentobjective.getText().toString();
        final String fileeducation = fcontenteducation.getText().toString();
        final String filecourse = fcontentcoursework.getText().toString();
        final String filetechskill = fcontenttechskill.getText().toString();
        final String fileexperience = fcontentexperience.getText().toString();
        final String fileproject = fcontentproject.getText().toString();



       runOnUiThread(new Runnable() {
           @Override
           public void run() {

               if(validate(filename,filecontent, filephone, fileemail, fileaddress, fileobjective, fileeducation, filecourse, filetechskill, fileexperience
                       , fileproject)==false){
                   return;
               }

               if(isValidEmail(fcontentemail.getText().toString())==false)
               {
                   Toast.makeText(TemplateOne.this, "Enter valid email", Toast.LENGTH_SHORT).show();
                   progressDialog.dismiss();
                   return;
               }

                else {
                   FileOperationsOne fop = new FileOperationsOne();
                   fop.write(filename, filecontent, filephone, fileemail, fileaddress, fileobjective, fileeducation, filecourse, filetechskill, fileexperience, fileproject);
                   if (fop.write(filename, filecontent, filephone, fileemail, fileaddress, fileobjective, fileeducation, filecourse, filetechskill, fileexperience, fileproject)) {
                       progressDialog.dismiss();
                       Toast.makeText(getApplicationContext(),
                               filename + ".pdf created", Toast.LENGTH_SHORT)
                               .show();
                   } else {
                       Toast.makeText(getApplicationContext(), "I/O error",
                               Toast.LENGTH_SHORT).show();
                   }

               }
            }
       });


        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        progressDialog.setMessage("Creating Pdf...");
        progressDialog.show();
    }
}

public boolean validate(String filename, String content, String filephone, String fileemail, String fileaddress, String fileobjective, String fileeducation,
                        String filecourse, String filetechskill, String fileexperience, String fileproject)
{
    if(TextUtils.isEmpty(filename) || TextUtils.isEmpty(content) || TextUtils.isEmpty(filephone) || TextUtils.isEmpty(fileemail) || TextUtils.isEmpty(fileaddress)
            || TextUtils.isEmpty(fileobjective) || TextUtils.isEmpty(fileeducation) || TextUtils.isEmpty(filecourse)
            || TextUtils.isEmpty(filetechskill) || TextUtils.isEmpty(fileexperience) || TextUtils.isEmpty(fileproject))
    {
        progressDialog.dismiss();
        Toast.makeText(this, "Cannot leave the field blank!", Toast.LENGTH_SHORT).show();
        return false;
    }
    return true;
}

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
