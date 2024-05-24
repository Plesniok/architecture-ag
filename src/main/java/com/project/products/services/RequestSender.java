package com.project.products.services;

import com.project.products.models.api.SimpleResponse;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class RequestSender {

    private static SimpleResponse getSimpleResponse(HttpURLConnection connection) throws IOException {
        int responseCode = connection.getResponseCode();
        StringBuilder responseData = new StringBuilder();
        Scanner scanner = new Scanner(responseCode == HttpURLConnection.HTTP_OK ? connection.getInputStream(): connection.getErrorStream());
        while (scanner.hasNextLine()) {
            responseData.append(scanner.nextLine());
        }
        scanner.close();
        return new SimpleResponse(responseCode, responseData.toString());
    }

    public static SimpleResponse sendGetRequest(String url) throws IOException {
        URL requestUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
        connection.setRequestMethod("GET");

        return getSimpleResponse(connection);
    }

    public static SimpleResponse sendPutRequest(String url, String jsonPatch) throws IOException {
        URL requestUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8"); // Set content type for JSON

        byte[] postDataBytes = jsonPatch.getBytes("UTF-8");
        connection.setDoOutput(true);
        connection.getOutputStream().write(postDataBytes);

        return getSimpleResponse(connection);
    }

    public static SimpleResponse sendPostRequest(String url, String jsonPatch) throws IOException {
        URL requestUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8"); // Set content type for JSON

        byte[] postDataBytes = jsonPatch.getBytes("UTF-8");
        connection.setDoOutput(true);
        connection.getOutputStream().write(postDataBytes);

        return getSimpleResponse(connection);
    }

    public static SimpleResponse sendPutRequest(String url) throws IOException {
        return sendPutRequest(url, "{}");
    }

    // Add other methods for different request types (POST, PUT, etc.) if needed
}

