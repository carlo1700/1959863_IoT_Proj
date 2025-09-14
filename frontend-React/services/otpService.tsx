import axios from 'axios';

const API_URL = 'https://api.synapsy.it/2024/services/getotp/';

interface LoginResponse {
	[key: string]: any;
}

// otpCode, manca il tempo di scadenza
export const get_otp = async (accessToken: string, key: string): Promise<LoginResponse> => {
	const body = {
	  grant_type: 'password',
	  key: key,
	  access_token: accessToken,
	};
	try {
		const response = await axios.post<LoginResponse>(API_URL, body, {
		  headers: {
			'Content-Type': 'application/json;charset=UTF-8',
		  },
		});
		return response.data;

	} catch (error) {
		console.error('Login Error:', error);
		throw error;
	}
};
