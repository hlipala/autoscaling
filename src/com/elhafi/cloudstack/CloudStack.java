package com.elhafi.cloudstack;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.util.EncodingUtil;

public class CloudStack {

	protected String secret;
	protected String apikey;
	protected String ip;

	public CloudStack(String ip, String new_apikey , String new_secret) {
		this.secret = new_secret;
		this.apikey = new_apikey;
		this.ip = ip;
	}

	// -----------------------------------------------------------------------------------------------------

	public String getMD5(String pass) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md.update(pass.getBytes());
		byte byteData[] = md.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
					.substring(1));
		}
		pass = sb.toString();

		return pass;
	}
	
	
	public LinkedList<NameValuePair> newQueryValues(String command,
			HashMap<String, String> optional) {
		LinkedList<NameValuePair> queryValues = new LinkedList<NameValuePair>();
		queryValues.add(new NameValuePair("command", command));
		queryValues.add(new NameValuePair("apiKey", apikey));
		queryValues.add(new NameValuePair("response", "json"));
		if (optional != null) {
			Iterator<Entry<String, String>> optional_it = optional.entrySet()
					.iterator();
			while (optional_it.hasNext()) {
				Entry<String, String> pairs = optional_it.next();
				queryValues.add(new NameValuePair((String) pairs.getKey(),
						(String) pairs.getValue()));
			}
		}
		return queryValues;
	}

	protected String sign_request(LinkedList<NameValuePair> queryValues)
			throws java.security.NoSuchAlgorithmException,
			java.security.InvalidKeyException {
		Collections.sort(queryValues, new Comparator<NameValuePair>() {
			public int compare(NameValuePair o1, NameValuePair o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

		String query_string = EncodingUtil.formUrlEncode(
				queryValues.toArray(new NameValuePair[0]), "UTF-8");

		Mac mac = Mac.getInstance("HmacSHA1");
		SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(),
				"HmacSHA1");
		mac.init(secret_key);
		byte[] digest = mac.doFinal(query_string.toLowerCase().getBytes());

		return DatatypeConverter.printBase64Binary(digest);
	}

	public String Request(LinkedList<NameValuePair> queryValues)
			throws Exception {
		HttpMethod method;
		method = makeHttpGet(queryValues);
		return executeGet(method);
	}

	protected HttpMethod makeHttpGet(LinkedList<NameValuePair> queryValues)
			throws Exception {
		String query_signature = sign_request(queryValues);
		queryValues.add(new NameValuePair("signature", query_signature));

		HttpMethod method = new GetMethod("http://" + ip + ":8080/client/api");
		// HttpMethod method = new
		// GetMethod("http://localhost:8080/client/api");
		method.setFollowRedirects(true);
		method.setQueryString(queryValues.toArray(new NameValuePair[0]));

		return method;
	}

	protected String executeGet(HttpMethod method) throws HttpException,
			IOException, Exception {
		HttpClient client = new HttpClient();

		// Document response = null;
		client.executeMethod(method);
		// System.out.println(method.getResponseBodyAsString());
		// response = handleResponse(method.getResponseBodyAsStream());

		String s = method.getResponseBodyAsString();
		// JsonElement jsonElement = parser.parse(new
		// InputStreamReader(method.getResponseBodyAsStream()));
		// JSONObject js=new JSONObject(method.getResponseBodyAsStream());
		// clean up the connection resources
		method.releaseConnection();

		return s;
	}

	public class CloudStackException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		CloudStackException(String errorcode, String errortext) {
			super(errorcode + ": " + errortext);
		}
	}

}
