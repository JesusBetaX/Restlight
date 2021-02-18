package com.jx;

import restlight.FormBody;
import restlight.Request;
import restlight.ResponseBody;

public class Delete {

  String run() throws Exception {
    FormBody body = new FormBody()
            .add("id", 101010);

    Request request = new Request();
    request.delete("http://127.0.0.1/test.php", body);

    try (ResponseBody response = request.execute()) {
      return response.string(request.getCharset());
    }
  }

  public static void main(String... args) throws Exception {
    Delete obj = new Delete();
    System.out.println(obj.run());
  }
}
