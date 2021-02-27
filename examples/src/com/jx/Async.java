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
  
  /*
   * Envía de manera asíncrona la petición y notifica a tu aplicación con un 
   * callback cuando una respuesta regresa. Ya que esta petición es asíncrona,
   * Restligth maneja la ejecución en el hilo de fondo para que el hilo de la 
   * UI principal no sea bloqueada o interfiera con esta.
   */
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
