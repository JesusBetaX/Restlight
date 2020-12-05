package com.jx;

import restlight.HttpUrlStack;
import restlight.Request;
import restlight.ResponseBody;

public class Get {

  HttpUrlStack stack = new HttpUrlStack();

  String run() throws Exception {
    String url = "http://weather.livedoor.com/forecast/webservice/json/v1?city=130010";
    Request request = new Request();
    request.request("GET", url);

    try (ResponseBody response = stack.execute(request)) {
      return response.string(request.getCharset());
    }
  }

  public static void main(String... args) throws Exception {
    Get obj = new Get();
    System.out.println(obj.run());
  }
}
