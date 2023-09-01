package response;


public class JwtAuthenticationResponse {
	
	public JwtAuthenticationResponse() {
		
	}
	
	
	public JwtAuthenticationResponse(String token) {
		this.token = token;
	}
	
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	
}

