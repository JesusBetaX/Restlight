package com.jx;

import restlight.Call;
import restlight.FormBody;
import restlight.HttpRequest;
import restlight.ResponseBody;

/**
 *
 * @author jesus
 */
public class Sync {
    
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
   * Envíe sincrónicamente la solicitud y devuelva su respuesta.
   */
  private void run() {
    Call<ResponseBody> insert = insert(
            "Elizabéth Magaña", 22, true);
    
    try (ResponseBody body = insert.execute()) {
        System.out.println(body.string());
        
    } catch(Exception e) {
        e.printStackTrace();
    }
  }
    
  public static void main(String[] args) {
    Sync sync = new Sync();
    sync.run();
  }
}
