package VO;

public class Provide {
	public static final String TABLE_NAME="Provide"; 
	private String ProvideID;
	private String ProvideName;
	private String ProvideAddress;
	private String ProvidePhone;
	
	public Provide() {
		super();
	}
	
	public Provide(String provideName, String provideAddress,
			String providePhone) {
		super();
		ProvideName = provideName;
		ProvideAddress = provideAddress;
		ProvidePhone = providePhone;
	}

	public Provide(String provideID, String provideName, String provideAddress,
			String providePhone) {
		super();
		ProvideID = provideID;
		ProvideName = provideName;
		ProvideAddress = provideAddress;
		ProvidePhone = providePhone;
	}
	public String getProvideID() {
		return ProvideID;
	}
	public void setProvideID(String provideID) {
		ProvideID = provideID;
	}
	public String getProvideName() {
		return ProvideName;
	}
	public void setProvideName(String provideName) {
		ProvideName = provideName;
	}
	public String getProvideAddress() {
		return ProvideAddress;
	}
	public void setProvideAddress(String provideAddress) {
		ProvideAddress = provideAddress;
	}
	public String getProvidePhone() {
		return ProvidePhone;
	}
	public void setProvidePhone(String providePhone) {
		ProvidePhone = providePhone;
	}
	
}
