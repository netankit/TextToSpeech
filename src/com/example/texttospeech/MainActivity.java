package com.example.texttospeech;

import java.util.Locale;
import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

   TextToSpeech ttsObj;
   private EditText write;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      write = (EditText)findViewById(R.id.editText1);
      ttsObj=new TextToSpeech(getApplicationContext(), 
      new TextToSpeech.OnInitListener() {
      @Override
      public void onInit(int status) {
         if(status != TextToSpeech.ERROR){
             ttsObj.setLanguage(Locale.US);
            }				
         }
      });
   }
   @Override
   public void onPause(){
      if(ttsObj !=null){
         ttsObj.stop();
         ttsObj.shutdown();
      }
      super.onPause();
   }
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }
   public void speakText(View view){
      String toSpeak = write.getText().toString();
      if (!toSpeak.equals("")){
    	  ttsObj.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
      }
      else
    	  ttsObj.speak("Please Enter Text", TextToSpeech.QUEUE_FLUSH, null);
   }
}