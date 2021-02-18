package com.jx;

import restlight.Request;
import restlight.ResponseBody;

public class Get {

  String run() throws Exception {
    Request request = new Request(
            "GET", "http://weather.livedoor.com/forecast/webservice/json/v1?city=130010");

    try (ResponseBody response = request.execute()) {
      return response.string(request.getCharset());
    }
  }

  public static void main(String... args) throws Exception {
    Get obj = new Get();
    System.out.println(obj.run());
  }
}
