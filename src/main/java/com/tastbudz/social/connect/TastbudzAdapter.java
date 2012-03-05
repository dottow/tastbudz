package com.tastbudz.social.connect;

import org.springframework.social.ApiException;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class TastbudzAdapter implements ApiAdapter<Tastbudz> {

	@Override
	public UserProfile fetchUserProfile(Tastbudz tastbudz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConnectionValues(Tastbudz tastbudz, ConnectionValues values) {
//	    values.setProviderUserId(profile.getId());
//	    values.setDisplayName(profile.getUsername());
//	    values.setProfileUrl((new StringBuilder()).append("http://facebook.com/profile.php?id=").append(profile.getId()).toString());
//	    values.setImageUrl((new StringBuilder()).append("http://graph.facebook.com/").append(profile.getId()).append("/picture").toString());
	}

	@Override
	public boolean test(Tastbudz tastbudz) {
		try {
			UserProfile profile = fetchUserProfile(tastbudz);
			return true;
		}
		catch(ApiException e) {
			return false;
		}
	}

	public void updateStatus(Tastbudz tastbudz, String arg1) {
		// TODO Auto-generated method stub
	}
}

