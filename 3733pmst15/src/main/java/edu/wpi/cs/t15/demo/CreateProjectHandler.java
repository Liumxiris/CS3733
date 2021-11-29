package edu.wpi.cs.t15.demo;

import java.io.ByteArrayInputStream;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import edu.wpi.cs.t15.demo.db.ProjectsDAO;
import edu.wpi.cs.t15.demo.http.CreateProjectRequest;
import edu.wpi.cs.t15.demo.http.CreateProjectResponse;
import edu.wpi.cs.t15.demo.model.Project;


public class CreateProjectHandler implements RequestHandler<CreateProjectRequest, CreateProjectResponse> {
	
	LambdaLogger logger; 
	private AmazonS3 s3 = null;
	public static final String REAL_BUCKET = "projects/";
	
	public CreateProjectHandler() {}
	
	public boolean createProject(String name) throws Exception {
		if(logger != null) {
			logger.log("in createProject");
		}
		
		ProjectsDAO dao = new ProjectsDAO(); 
		Project exist = dao.getProject(name);
		Project proj = new Project(name);
		
		if(exist == null) {
			return dao.addProject(proj);
		} else {
			return false;
		}
	}
	
	public boolean createSystemProject(String name) {
		if (logger != null) {
			logger.log("in CreateSystemProject");
		}
		
		if (s3 == null) {
			logger.log("attach to S3 request");
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
			logger.log("attach to S3 succeeed");
		}
		
		String bucket = REAL_BUCKET; 
		
		//TODO
		byte[] contents = ("" + name).getBytes();
		ByteArrayInputStream bais = new ByteArrayInputStream(contents);
		ObjectMetadata omd = new ObjectMetadata();
		omd.setContentLength(contents.length);
		
		PutObjectResult res = s3.putObject(new PutObjectRequest("3733pmst15", bucket + name, bais, omd)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		
		return true;
	}

	@Override
	public CreateProjectResponse handleRequest(CreateProjectRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());

		CreateProjectResponse response;
		try {
			if (req.system) {
				if (createSystemProject(req.getName())) {
					response = new CreateProjectResponse(req.getName());
				} else {
					response = new CreateProjectResponse(req.getName(), 422);
				}
			} else {
				if (createProject(req.getName())) {
					response = new CreateProjectResponse(req.getName());
				} else {
					response = new CreateProjectResponse(req.getName(), 422);
				}
			}
		} catch (Exception e) {
			response = new CreateProjectResponse("Unable to create project: " + req.getName() + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}

}
