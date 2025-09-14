import { useEffect, useState } from 'react';
import { Redirect } from 'expo-router';
import AsyncStorage from '@react-native-async-storage/async-storage';
import { View, ActivityIndicator } from 'react-native';
import { login } from '../services/authService';

export default function Index() {
  const [isLoggedIn, setIsLoggedIn] = useState<null | boolean>(null);

  useEffect(() => {
    const checkLoginStatus = async () => {
      try {
        // const accToken = await AsyncStorage.getItem('accToken');
        // const exipres = await AsyncStorage.getItem('exipres');
        // const email = await AsyncStorage.getItem('email');
        // const password = await AsyncStorage.getItem('password');
        // const rememberMe = await AsyncStorage.getItem('rememberMe');

        // if (accToken && exipres && rememberMe === 'true') {
        //   const currentDate = new Date();
        //   let currentTime = currentDate.getTime();
        //   const expirationDate = new Date(exipres);
        //   let expirationTime = expirationDate.getTime();
        //   expirationTime += 3480000; // Add one hour in milliseconds - 2 minutes = 120000
        //   currentTime = expirationTime + 1;
        //   if (currentTime < expirationTime) {
        //       alert('Token is still valid');
        //       setIsLoggedIn(true);
        //       return;
        //   }
        //   else {
        //     if (!email || !password) {
        //       setIsLoggedIn(false);
        //       return;
        //     }
        //     const data_comp = await login(email, password);
        //     const data = data_comp[0];
        //     const key = data_comp[1];
        //     const errorCode = data['errorCode'];
        //     const acc_token = data['access_token'];
        //     const userName = data['userName'];
        //     const userSurname = data['userSurname'];
        //     const exipres = data['.expires'];

        //     if (errorCode === 100) {
        //       await AsyncStorage.setItem('userName', userName);
        //       await AsyncStorage.setItem('userSurname', userSurname);
        //       await AsyncStorage.setItem('accToken', acc_token);
        //       await AsyncStorage.setItem('key', key);
        //       await AsyncStorage.setItem('exipres', exipres);
        //       await AsyncStorage.setItem('email', email);
        //       await AsyncStorage.setItem('password', password);
        //       setIsLoggedIn(true);
        //       return;
        //     } else {
        //       await AsyncStorage.removeItem('accToken');
        //       await AsyncStorage.removeItem('userSurname');
        //       await AsyncStorage.removeItem('userName');
        //       await AsyncStorage.removeItem('key');
        //       await AsyncStorage.removeItem('exipres');
        //       await AsyncStorage.removeItem('email');
        //       await AsyncStorage.removeItem('password');
        //       await AsyncStorage.removeItem('otpCode');
        //       await AsyncStorage.removeItem('rememberMe');
        //       await AsyncStorage.removeItem('otpScad');
        //       setIsLoggedIn(false);
        //     }
        //   }
        // }
        const accToken = await AsyncStorage.getItem('accToken');
        if (accToken) {
          setIsLoggedIn(true);
          return;
        }
        setIsLoggedIn(false);
      } catch (error) {
        console.error('Errore nel recupero dello stato di login:', error);
        setIsLoggedIn(false);
      }
    };

    checkLoginStatus();
  }, []);

  if (isLoggedIn === null) {
    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <ActivityIndicator size="large" color="#0000ff" />
      </View>
    );
  }

  return <Redirect href={isLoggedIn ? '/HomeScreen' : '/HomeScreen'} />;
}
