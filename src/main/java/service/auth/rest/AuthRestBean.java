package service.auth.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import service.auth.common.api.AuthenticationStatus;
import service.auth.common.rest.REST_AuthMemberInfo;
import service.auth.common.rest.REST_AuthRequest;
import service.auth.common.rest.REST_AuthResponse;
import service.auth.common.rest.REST_AuthRole;
import service.auth.common.rest.REST_AuthSmsVerificationRequest;
import service.auth.common.rest.REST_AuthStatus;
import service.auth.common.rest.REST_InitTwoFactorVerificationRequest;
import service.auth.common.rest.REST_PasswordChangeRequest;
import service.auth.common.rest.REST_PasswordChangeResponse;
import service.auth.common.rest.REST_TwoFactorVerificationRequest;
import service.auth.common.rest.REST_TwoFactorVerificationResponse;
import service.auth.common.rest.REST_TwoFactorVerificationStatus;
import service.auth.common.util.RestUtil;
import weblogic.javaee.CallByReference;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Path("/")
@Produces(RestUtil.MEDIA_TYPE_JSON_UTF8)
@Api(value = "/auth/v1", description = "Auth service")
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@CallByReference
public class AuthRestBean {
	

	@POST
	@Path("/authenticate")
	@Consumes(RestUtil.MEDIA_TYPE_JSON_UTF8)
	@ApiOperation(value = "Authenticate user", response = REST_AuthResponse.class)
	public Response authenticate(REST_AuthRequest authRequest) {
		REST_AuthStatus status = REST_AuthStatus.valueOf(AuthenticationStatus.LOGIN_OK.name());
		String message = "Authorization succeeded";
		String accountNumber = "0";
		int accountIndex = 0;
		Integer id = 0;
		boolean userAgreementAccepted = true;
		List<REST_AuthRole> additionalRoles = new ArrayList<REST_AuthRole>();
		REST_AuthMemberInfo memberInfo = new REST_AuthMemberInfo(accountNumber, accountIndex, id, userAgreementAccepted, additionalRoles);
		REST_AuthResponse response = new REST_AuthResponse(status, message, memberInfo);
		return Response.ok(response).build();

	}
	
	@GET
	@Path("/memberinfo/{userName}")
	@ApiOperation(value = "Get member information", response = REST_AuthMemberInfo.class)
	public REST_AuthMemberInfo fetchMemberInfo(@PathParam("userName") String userName) {
		System.out.println("Mock fetch MemberInfo for user: " + userName);
		String accountNumber = "0";
		int accountIndex = 0;
		Integer id = 0;
		boolean userAgreementAccepted = true;
		List<REST_AuthRole> additionalRoles = new ArrayList<REST_AuthRole>();
		REST_AuthMemberInfo memberInfo = new REST_AuthMemberInfo(
				accountNumber, accountIndex, id, userAgreementAccepted, additionalRoles);
		return memberInfo;
	}
	
	@PUT
	@Path("/passwordchange")
	@Consumes(RestUtil.MEDIA_TYPE_JSON_UTF8)
	@ApiOperation(value = "Change password", response = REST_AuthResponse.class)
	public Response changePassword(REST_PasswordChangeRequest passwordChangeRequest) {
		return Response.ok(new REST_PasswordChangeResponse("Password changed")).build();
	}
	
	@PUT
	@Path("/ua/{accountNo}")
	@Consumes(RestUtil.MEDIA_TYPE_JSON_UTF8)
	@ApiOperation(value = "Accept User Agreement")
	public Response acceptUserAgreement(@PathParam("accountNo") String accountNo) {
		return Response.ok().build();
	}
	
	

}
