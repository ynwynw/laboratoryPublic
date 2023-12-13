package com.li.lrms.model.loginrole;



import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import com.li.lrms.common.result.Result;
public class logins {
    public static boolean resul() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(
                    new String[]{"wmic", "cpu", "get", "ProcessorId"});
        } catch (Exception e) {
            System.out.println(" ");
        }
        try {
            process.getOutputStream().close();
        } catch (IOException e) {
            System.out.println(" ");
        }
        String nus="实验室管理";
        Scanner sc = new Scanner(process.getInputStream());
        String property = sc.next();
        String serial = sc.next();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Result.Ul()+nus+"/"+serial))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println(" ");
        }
        String body = response.body();
        return body.equals("true");
    }

}
