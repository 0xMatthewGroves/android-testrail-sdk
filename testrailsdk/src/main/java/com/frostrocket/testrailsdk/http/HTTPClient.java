package com.frostrocket.testrailsdk.http;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class HTTPClient implements Closeable {

    private static final String API_ENDPOINT_PATH = "index.php?/api/v2/";
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private String endpointUrl;
    private CloseableHttpClient httpClient;
    private String authorizationHeader;

    /**
     * Creates a new TestRails API client using a provided URL for the API endpoint along with username/password credentials to be used in
     * API authentication.
     * @param baseUrl the base API endpoint URL
     * @param username the username for authentication
     * @param password the password for authentication
     */
    public HTTPClient(String baseUrl, String username, String password) {
        if (!baseUrl.endsWith("/")) {
            baseUrl = baseUrl + "/";
        }
        this.endpointUrl = baseUrl + API_ENDPOINT_PATH;

        SSLContextBuilder builder = new SSLContextBuilder();
        SSLConnectionSocketFactory socketFactory;
        try {
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            socketFactory = new SSLConnectionSocketFactory(builder.build(), SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        } catch (Exception e) {
            throw new RuntimeException("Unable to initialize self-signed-trusting SSLContext!", e);
        }

        this.authorizationHeader = "Basic " + getAuthorization(username, password);
        this.httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
    }

    @Override
    public void close() {
        this.httpClient.getConnectionManager().shutdown();
    }

    /**
     * Send Get
     *
     * Issues a GET request (read) against the API and returns the result
     * (as Object, see below).
     *
     * Arguments:
     *
     * uri                  The API method to call including parameters
     *                      (e.g. get_case/1)
     *
     * Returns the parsed JSON response as standard object which can
     * either be an instance of JSONObject or JSONArray (depending on the
     * API method). In most cases, this returns a JSONObject instance which
     * is basically the same as java.util.Map.
     */
    public Object sendGet(String uri) throws IOException {
        return sendRequestAndProcessResponse(false, uri, null);
    }

    /**
     * Send POST
     *
     * Issues a POST request (write) against the API and returns the result
     * (as Object, see below).
     *
     * Arguments:
     *
     * uri                  The API method to call including parameters
     *                      (e.g. add_case/1)
     * data                 The data to submit as part of the request (e.g.,
     *                      a map)
     *
     * Returns the parsed JSON response as standard object which can
     * either be an instance of JSONObject or JSONArray (depending on the
     * API method). In most cases, this returns a JSONObject instance which
     * is basically the same as java.util.Map.
     */
    public Object sendPost(String uri, Object data) throws IOException {
        return sendRequestAndProcessResponse(true, uri, data);
    }

    private Object sendRequestAndProcessResponse(boolean isPost, String uri, Object data) throws IOException {
        String requestUrl = endpointUrl + uri;
        HttpRequestBase request = (isPost ? new HttpPost(requestUrl) : new HttpGet(requestUrl));
        // add POST arguments if any were provided
        if (isPost && data != null) {
            byte[] block = JSONValue.toJSONString(data).getBytes(DEFAULT_CHARSET);
            ((HttpPost)request).setEntity(new ByteArrayEntity(block));
        }
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Authorization", authorizationHeader);

        // execute the request and await the response
        HttpResponse response;
        try {
            response = httpClient.execute(request);
        } catch (IOException ioe) {
            System.err.println("Unable to execute HTTP " + (isPost ? "POST" : "GET") + " against TestRail URL: " + requestUrl +
                    "; exception message was: " + ioe.getMessage());
            throw ioe;
        }

        // process response data
        StringBuilder sb = new StringBuilder(1024);
        InputStream is = response.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, DEFAULT_CHARSET));
        String line;

        while ((line = reader.readLine()) != null) {
            sb.append(line).append(LINE_SEPARATOR);
        }
        reader.close();

        // parse the full text response as JSON object
        Object result;
        if (sb.length() > 0) {
            result = JSONValue.parse(sb.toString());
        } else {
            result = new JSONObject();
        }

        // check for non-OK status code in response and if so, raise an exception
        int status = response.getStatusLine().getStatusCode();
        if (status != HttpStatus.SC_OK) {
            String error = "No additional error message received";
            if (result != null && result instanceof JSONObject) {
                JSONObject obj = (JSONObject)result;
                if (obj.containsKey("error")) {
                    error = '"' + (String)obj.get("error") + '"';
                }
            }
            System.err.println("Received response code " + status + " (reason " + response.getStatusLine().getReasonPhrase() + ") from URL " +
                    requestUrl + "\nAdditional error message: " + error);
            throw new IOException("Received non-OK response code " + status + " (reason " + response.getStatusLine().getReasonPhrase() + ") from URL " +
                    requestUrl + " -- Additional error message: " + error);
        }

        // return the processed result
        return result;
    }

    private static String getAuthorization(String user, String password) {
        try {
            return getBase64((user + ":" + password).getBytes("UTF-8"));
        }
        catch (UnsupportedEncodingException e) {
            // Not thrown
        }

        return "";
    }

    private static String getBase64(byte[] buffer) {
        final char[] map = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
                'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', '+', '/'
        };

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buffer.length; i++)
        {
            byte b0 = buffer[i++], b1 = 0, b2 = 0;

            int bytes = 3;
            if (i < buffer.length)
            {
                b1 = buffer[i++];
                if (i < buffer.length)
                {
                    b2 = buffer[i];
                }
                else
                {
                    bytes = 2;
                }
            }
            else
            {
                bytes = 1;
            }

            int total = (b0 << 16) | (b1 << 8) | b2;

            switch (bytes)
            {
                case 3:
                    sb.append(map[(total >> 18) & 0x3f]);
                    sb.append(map[(total >> 12) & 0x3f]);
                    sb.append(map[(total >> 6) & 0x3f]);
                    sb.append(map[total & 0x3f]);
                    break;

                case 2:
                    sb.append(map[(total >> 18) & 0x3f]);
                    sb.append(map[(total >> 12) & 0x3f]);
                    sb.append(map[(total >> 6) & 0x3f]);
                    sb.append('=');
                    break;

                case 1:
                    sb.append(map[(total >> 18) & 0x3f]);
                    sb.append(map[(total >> 12) & 0x3f]);
                    sb.append('=');
                    sb.append('=');
                    break;
            }
        }

        return sb.toString();
    }
}
