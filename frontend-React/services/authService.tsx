import axios from 'axios';
import { Platform } from 'react-native';
// import * as Random from 'expo-random';
// use crypto
import * as Crypto from 'expo-crypto';

const ALPHANUMERIC = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';

export const generateRandomString = async (length: number = 32): Promise<string> => {
  const date = new Date();
  const dateString = `${date.getFullYear()}${(date.getMonth() + 1).toString().padStart(2, '0')}${date.getDate().toString().padStart(2, '0')}${date.getHours().toString().padStart(2, '0')}${date.getMinutes().toString().padStart(2, '0')}${date.getSeconds().toString().padStart(2, '0')}`;

  let result = dateString;
  //const bytes = await Random.getRandomBytesAsync(length);
  const bytes = await Crypto.getRandomBytesAsync(length);
  for (let i = 0; i < 32; i++) {
      result += ALPHANUMERIC[bytes[i] % ALPHANUMERIC.length];
  }
  return result;
};

const API_URL = 'https://api.synapsy.it/2024/auth/apiLogin/';

interface LoginResponse {
  [key: string]: any;
}

export const login = async (email: string, password: string): Promise<LoginResponse> => {
  const key = await generateRandomString(32);
  const body = {
    grant_type: 'password',
    key: key,
    access_token: '',
    browser: 'Expo App',
    platform: Platform.OS,
    retaddr: '',
    idPortal: '41',
    idPlatform: '41',
    email: email,
    password: password,
  };

  try {
    const response = await axios.post<LoginResponse>(API_URL, body, {
      headers: {
        'Content-Type': 'application/json;charset=UTF-8',
      },
    });
    return [response.data, key];

  } catch (error) {
    console.error('Login Error:', error);
    throw error;
  }
};
