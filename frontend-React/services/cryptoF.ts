import CryptoJS from 'crypto-js';

export const encrypt = (data: string, key: string) => {
  return CryptoJS.AES.encrypt(data, key).toString();
};

export const decrypt = (data: string, key: string) => {
  const bytes = CryptoJS.AES.decrypt(data, key);
  return JSON.parse(bytes.toString(CryptoJS.enc.Utf8));
};
