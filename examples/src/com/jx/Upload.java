package com.jx;

import java.io.File;
import restlight.HttpUrlStack;
import restlight.MultipartBody;
import restlight.Request;
import restlight.ResponseBody;

public class Upload {

  HttpUrlStack stack = new HttpUrlStack();

  String run() throws Exception { 
    MultipartBody body = new MultipartBody()
            .addParam("nombre", "Elizabéth Magaña")
            .addFile("img", new File("C:\\Users\\jesus\\Pictures\\0_y9hPeM6fo2v1ZA31.png"));
    
    Request request = new Request();
    request.post("http://127.0.0.1/test.php", body);

    try (ResponseBody response = stack.execute(request)) {
      return response.string(request.getCharset());
    }
  }

  public static void main(String... args) throws Exception {
    Upload obj = new Upload();
    System.out.println(obj.run());
  }
}
