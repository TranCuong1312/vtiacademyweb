package com.vti.academy.web.controller.admin;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.vti.academy.web.common.ResponseType;
import com.vti.academy.web.common.RestCode;
import com.vti.academy.web.model.Enterprise;
import com.vti.academy.web.response.CommonResponse;
import com.vti.academy.web.response.PaginationResponse;
import com.vti.academy.web.service.EnterpriseService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/admin/enterprise")
public class EnterpriseController {
	@Autowired
	private EnterpriseService enterpriseService;


	@GetMapping
	public CommonResponse getAll(Pageable pageable){PaginationResponse<Enterprise> data = new PaginationResponse<Enterprise>(enterpriseService.getAll(pageable), "admin/enterprise");
		return new CommonResponse(ResponseType.INFO.toString(), RestCode.SUCCESS.getCode(), "Get Success", data);
	}
	@GetMapping("/{id}")
	public CommonResponse getEnterpriseById(@PathVariable Long id) {
		return new CommonResponse(ResponseType.INFO.toString(),
				RestCode.SUCCESS.getCode(),
				"Get success",
				enterpriseService.getEnterpriseByID(id));
	}
	
	@GetMapping("/top3")
	public CommonResponse getTop3() {
		return new CommonResponse(ResponseType.INFO.toString(),
				RestCode.SUCCESS.getCode(),
				"Get success",
				enterpriseService.getTop3Enterprise());
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> createEnterprise(@RequestParam("icon") MultipartFile icon ,@RequestParam("image") MultipartFile image, @RequestParam("enterprise") String enterprise) throws IOException {
		String response = enterpriseService.createEnterprise(icon, image, enterprise);
		if(response == "file name exists")
			return new ResponseEntity<>(response,  HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(response,  HttpStatus.OK);
	}
	@PostMapping("/{id}/edit")
	public CommonResponse editAllEnterpriseById(@RequestParam("icon") MultipartFile icon, 
			@RequestParam("image") MultipartFile image,
			@RequestParam("enterprise") String enterprise,
			@PathVariable("id") String id) throws IOException {
		String response = enterpriseService.editEnterprise(icon, image, enterprise, id);
		if(response == "fail")
			return new CommonResponse(ResponseType.INFO.toString(),
					RestCode.BAD_REQUEST.getCode(),
					response);
		else
			return new CommonResponse(ResponseType.INFO.toString(),
					RestCode.SUCCESS.getCode(),
					response);	
	}
	@PostMapping("/{id}/icon/edit")
	public CommonResponse editIconEnterpriseById(@RequestParam("icon") MultipartFile icon, @RequestParam("enterprise") String enterprise, @PathVariable("id") String id) throws IOException {
		String response = enterpriseService.editIconEnterprise(icon, enterprise, id);
		if(response == "fail")
			return new CommonResponse(ResponseType.INFO.toString(),
					RestCode.BAD_REQUEST.getCode(),
					response);
		else
			return new CommonResponse(ResponseType.INFO.toString(),
					RestCode.SUCCESS.getCode(),
					response);	
	}
	@PostMapping("/{id}/image/edit")
	public CommonResponse editImageEnterpriseById(@RequestParam("image") MultipartFile image, @RequestParam("enterprise") String enterprise, @PathVariable("id") String id) throws IOException {
		String response = enterpriseService.editImageEnterprise(image, enterprise, id);
		if(response == "fail")
			return new CommonResponse(ResponseType.INFO.toString(),
					RestCode.BAD_REQUEST.getCode(),
					response);
		else
			return new CommonResponse(ResponseType.INFO.toString(),
					RestCode.SUCCESS.getCode(),
					response);	
	}
	@PostMapping("/{id}/content/edit")
	public CommonResponse editContentEnterpriseById(@RequestParam("enterprise") String enterprise, @PathVariable("id") String id) throws IOException {
		String response = enterpriseService.editContentEnterprise(enterprise, id);
		if(response == "fail")
			return new CommonResponse(ResponseType.INFO.toString(),
					RestCode.BAD_REQUEST.getCode(),
					response);
		else
			return new CommonResponse(ResponseType.INFO.toString(),
					RestCode.SUCCESS.getCode(),
					response);	
	}
	@DeleteMapping("/{id}")
	public CommonResponse deleteEnterpriseById(@PathVariable Long id) {
		String response = enterpriseService.deleteEnterprise(id);
		if(response == "fail")
			return new CommonResponse(ResponseType.INFO.toString(),
					RestCode.BAD_REQUEST.getCode(),
					response);
		else
			return new CommonResponse(ResponseType.INFO.toString(),
					RestCode.SUCCESS.getCode(),
					response);	
	}
}
