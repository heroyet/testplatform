package com.ymt.testplatform.service.application;

import java.util.List;
import java.util.Map;

import com.ymt.testplatform.entity.Application;
import com.ymt.testplatform.entity.ApplicationEnv;
import com.ymt.testplatform.entity.ApplicationType;

public interface ApplicationService {
	
	// Application
	public Application findApplicationById(int id);
	
	public List<Application> findApplicationsByType(int typeid);
	
	public Application findApplicationByDomain(String domain);
	
	public List<Application> findApplicationsByDepartment(int departmentid);
	
	public void saveApplication(Application application);

	public void updateApplication(Application application);

	public void deleteApplication(Application application);

	public List<Application> findAllApplications(Integer pageIndex, Integer pageSize, Map<String, Object> map);
	
	public Long findApplicationPages(Integer pageSize, Map<String, Object> map);
	
	public List<Application> findAllApplications();
	
	// ApplicationType
	public ApplicationType findApplicationTypeById(int id);
	
	public ApplicationType findApplicationTypeByName(String name);
	
	public void saveApplicationType(ApplicationType applicationType);

	public void updateApplicationType(ApplicationType applicationType);

	public void deleteApplicationType(ApplicationType applicationType);

	public List<ApplicationType> findAllApplicationTypes();

	// ApplicationEnv
	public ApplicationEnv findApplicationEnvById(int id);
	
	public ApplicationEnv findApplicationEnvByAppAndEnv(int appid, int envid);
	
	public List<ApplicationEnv> findApplicationEnvsByApplicationId(int applicationid);
	
	public List<ApplicationEnv> findApplicationEnvsByVmInfoId(int vminfoid);
	
	public List<ApplicationEnv> findApplicationEnvByEnv(int envid);
	
	public void saveApplicationEnv(ApplicationEnv applicationEnv);

	public void updateApplicationEnv(ApplicationEnv applicationEnv);

	public void deleteApplicationEnv(ApplicationEnv applicationEnv);


}
