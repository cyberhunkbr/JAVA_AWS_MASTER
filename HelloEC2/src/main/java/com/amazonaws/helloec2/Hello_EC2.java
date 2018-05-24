package com.amazonaws.helloec2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.client.apache.config.DefaultApacheHttpClientConfig;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.DescribeVolumesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.Volume;
/**
 * @author a572393
 *
 */
public class Hello_EC2 {
	
	 public enum Type{
	        ami_id("ami-id"),
	        ami_launch_index("ami-launch-index"),
	        ami_manifest_path("ami-manifest-path"),
	        block_device_mapping("block-device-mapping/"),
	        hostname("hostname"),	
	        instance_action("instance-action"),
	        instance_id("instance-id"),
	        instance_type("instance-type"),
	        kernel_id("kernel-id"),
	        local_hostname("local-hostname"),
	        local_ipv4("local-ipv4"),
	        mac("mac"),
	        network("network/"),
	        placement("placement/"),
	        public_hostname("public-hostname"),
	        public_ipv4("public-ipv4"),
	        public_keys("public-keys/"),
	        reservation_id("reservation-id"),
	        security_groups("security-groups"),
	        services("services/");
	 
	        private String name;
	 
	        private Type(String name){
	            this.name = name;
	        }
	    }
	 public static String retrieveMetadata(Type instanceId, int timeout,String defaultValue) {
		 
		 try{
		 URI uri = URI.create("http://54.245.29.19/latest/meta-data/instance-id");
		 System.out.println(uri.toString());
		 ClientConfig config = new DefaultApacheHttpClientConfig();


		 config.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT,timeout);
		 Client client = Client.create(config);
         WebResource webResource = client.resource(uri);
		 
         ClientResponse response = webResource.get(ClientResponse.class);
         
         String results = response.getEntity(String.class);
         return results;
		 }catch(Throwable t ){
			 t.printStackTrace();
			 return defaultValue;
		 }
		 
	 }
	 
	public static String retrieveInstanceId() throws Exception {
		String EC2Id = "";
		String inputLine;
		URL EC2MetaData = new URL("http://54.245.29.19/latest/meta-data/instance-id");
		URLConnection EC2MD = EC2MetaData.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				EC2MD.getInputStream()));
		while ((inputLine = in.readLine()) != null) {
			EC2Id = inputLine;
		}
		in.close();
		System.out.println("EC2Id="+EC2Id);
		return EC2Id;
	}

	 
	public static void main(String[] args) throws Exception {
		
		AWSCredentials credentials = new BasicAWSCredentials("<Access key ID>", "<Secret Key Id>");
	    @SuppressWarnings("deprecation")
		AmazonEC2 ec2 = new AmazonEC2Client(credentials);
	    ec2.setEndpoint("ec2.us-west-2.amazonaws.com");
	    AmazonEC2 ec3 = new AmazonEC2Client(credentials);
	    
	 
	    DescribeInstancesResult insResult = ec2.describeInstances();
	 
	    int count = 1;
	    for (Reservation reservation : insResult.getReservations())
	    {
	      for(Instance instance : reservation.getInstances())
	      {
	        System.out.println("Instance # " + count++
	                + "\n InstanceId: " + instance.getInstanceId()
	                + "\n InstanceType: " + instance.getInstanceType()
	                + "\n Public IP: " + instance.getPublicIpAddress());
	        StopInstancesRequest request = new StopInstancesRequest().withInstanceIds( instance.getInstanceId());
	        ec2.stopInstances(request);

	      }
	    }
	    
	    DescribeVolumesResult volReq = ec2.describeVolumes();
	    
	    int countt = 1;
	    for (Volume vol : volReq.getVolumes())
	    {
	      System.out.println("Volume " + count   + "\n Details: " + vol);
	      count++;
	    }
		// TODO Auto-generated method stub
		Hello_EC2 ec = new Hello_EC2();
		//ec.retrieveInstanceId();
		
		// String myEC2Id = retrieveMetadata(Type.instance_id,5000, null);
	    // System.out.println("The Instance Id is =" + myEC2Id + " .");
	}

}	
