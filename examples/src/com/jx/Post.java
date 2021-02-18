package com.jx;

import restlight.FormBody;
import restlight.Request;
import restlight.ResponseBody;

public class Post {

  String run() throws Exception {
    FormBody body = new FormBody()
            .add("nombre", "Elizabéth Magaña")
            .add("edad", 22)
            .add("soltera", false);
    
    Request request = new Request();
    request.post("http://127.0.0.1/test.php", body);

    try (ResponseBody response = request.execute()) {
      return response.string(request.getCharset());
    }
  }

  public static void main(String... args) throws Exception {
    Post obj = new Post();
    System.out.println(obj.run());
  }
}
