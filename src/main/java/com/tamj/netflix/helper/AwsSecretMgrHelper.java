package com.tamj.netflix.helper;

import java.util.Base64;

import org.json.JSONObject;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.DecryptionFailureException;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.services.secretsmanager.model.InternalServiceErrorException;
import software.amazon.awssdk.services.secretsmanager.model.InvalidParameterException;
import software.amazon.awssdk.services.secretsmanager.model.InvalidRequestException;
import software.amazon.awssdk.services.secretsmanager.model.ResourceNotFoundException;


public class AwsSecretMgrHelper {
	// Use this code snippet in your app.
	// If you need more information about configurations or implementing the sample code, visit the AWS docs:
	// https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/java-dg-samples.html#prerequisites

	public static String getSecret(String secretName, String secretKey) {

//	    String secretName = "prod/RapidApi";
	    Region region = Region.of("eu-west-2");

	    // Create a Secrets Manager client
	    SecretsManagerClient client = SecretsManagerClient.builder()
	            .region(region)
	            .build();

	    // In this sample we only handle the specific exceptions for the 'GetSecretValue' API.
	    // See https://docs.aws.amazon.com/secretsmanager/latest/apireference/API_GetSecretValue.html
	    // We rethrow the exception by default.

	    String secret, decodedBinarySecret;
	    GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
	            .secretId(secretName)
	            .build();
	    GetSecretValueResponse getSecretValueResponse = null;

	    try {
	        getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
	    } catch (DecryptionFailureException e) {
	        // Secrets Manager can't decrypt the protected secret text using the provided KMS key.
	        // Deal with the exception here, and/or rethrow at your discretion.
	        throw e;
	    } catch (InternalServiceErrorException e) {
	        // An error occurred on the server side.
	        // Deal with the exception here, and/or rethrow at your discretion.
	        throw e;
	    } catch (InvalidParameterException e) {
	        // You provided an invalid value for a parameter.
	        // Deal with the exception here, and/or rethrow at your discretion.
	        throw e;
	    } catch (InvalidRequestException e) {
	        // You provided a parameter value that is not valid for the current state of the resource.
	        // Deal with the exception here, and/or rethrow at your discretion.
	        throw e;
	    } catch (ResourceNotFoundException e) {
	        // We can't find the resource that you asked for.
	        // Deal with the exception here, and/or rethrow at your discretion.
	        throw e;
	    }

	    // Decrypts secret using the associated KMS key.
	    // Depending on whether the secret is a string or binary, one of these fields will be populated.
	    if (getSecretValueResponse.secretString() != null) {
	        secret = getSecretValueResponse.secretString();
	        
	        JSONObject json = new JSONObject(secret);
	        return json.getString(secretKey);
	    }
	    else {
	    	decodedBinarySecret = new String(Base64.getDecoder().decode(getSecretValueResponse.secretBinary().asByteBuffer()).array());
	    	
	        JSONObject json = new JSONObject(decodedBinarySecret);
	        return json.getString(secretKey);
	    }

	    // Your code goes here.
	}
}
