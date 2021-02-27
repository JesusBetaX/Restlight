package com.jx;

import restlight.Call;
import restlight.Callback;
import restlight.FormBody;
import restlight.HttpRequest;
import restlight.ResponseBody;

/**
 *
 * @author jesus
 */
public class Async {
    
  public Call<ResponseBody> insert(
          String nombre, int edad, boolean soltera) {
      
    FormBody body = new FormBody()
        .add("nombre", nombre)
        .add("edad", edad)
        .add("soltera", soltera);
    
    HttpRequest request = new HttpRequest(
        "POST", "http://127.0.0.1/test.php", body);

    return request.newCall();
  }
    
  private void run() {
    Call<ResponseBody> insert = insert(
            "Elizabéth Magaña", 22, true);
    
    insert.execute(new Callback<ResponseBody>() {
        @Override
        public void onResponse(ResponseBody result) throws Exception {
            String str = result.string();
            System.out.println(str);
        }
        
        @Override
        public void onFailure(Exception e) {
            e.printStackTrace();
        }
    });
  }
    
  public static void main(String[] args) {
    Async async = new Async();
    async.run();
  }
}
