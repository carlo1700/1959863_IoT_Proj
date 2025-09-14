import { DarkTheme, DefaultTheme, ThemeProvider } from '@react-navigation/native';
import { useFonts } from 'expo-font';
import { Stack } from 'expo-router';
import * as SplashScreen from 'expo-splash-screen';
import { StatusBar } from 'expo-status-bar';
import { useEffect, useState } from 'react';
import 'react-native-reanimated';
import AsyncStorage from '@react-native-async-storage/async-storage';

import { useColorScheme } from '@/hooks/useColorScheme';

import * as Notifications from 'expo-notifications';
import * as Device from 'expo-device';

// import { Platform } from 'react-native';

import { registerToken } from '@/services/apis';

SplashScreen.preventAutoHideAsync();

async function registerForPushNotificationsAsync() {
  let token;

  if (Device.isDevice) {
    const { status: existingStatus } = await Notifications.getPermissionsAsync();
    let finalStatus = existingStatus;

    if (existingStatus !== 'granted') {
      const { status } = await Notifications.requestPermissionsAsync();
      finalStatus = status;
    }
    if (finalStatus !== 'granted') {
      alert('Non hai dato il permesso per le notifiche!');
      return;
    }
    try {
      const tokenResponse = await Notifications.getExpoPushTokenAsync();
      token = tokenResponse.data;
      console.log("Expo push token:", token);
    } catch (err) {
      console.error("Errore ottenendo il token:", err);
    }
  } else {
    alert('Serve un dispositivo fisico per le notifiche push');
  }
  return token;
}

export default function RootLayout() {
  const colorScheme = useColorScheme();
  const [isLoggedIn, setIsLoggedIn] = useState<null | boolean>(null);
  const [loaded] = useFonts({
    SpaceMono: require('../assets/fonts/SpaceMono-Regular.ttf'),
  });

  useEffect(() => {
    const checkLoginStatus = async () => {
      try {
        const userToken = await AsyncStorage.getItem('userToken');
        setIsLoggedIn(!!userToken);
      } catch (error) {
        console.error('Errore nel recupero dello stato di login:', error);
      }
    };

    checkLoginStatus();
    registerForPushNotificationsAsync().then(token => {
      if (token) {
        registerToken(token);
        console.log('Token registrato con successo');
      }
    });

    const subscription1 = Notifications.addNotificationReceivedListener(notification => {
        alert('Notifica ricevuta! ' + JSON.stringify(notification.request.content.body));
    });

    const subscription2 = Notifications.addNotificationResponseReceivedListener(response => {
        alert('Notifica risposta! ' + JSON.stringify(response.notification.request.content.body));
    });

    return () => {
        subscription1.remove();
        subscription2.remove();
    };
  }, []);

  useEffect(() => {
    if (loaded && isLoggedIn !== null) {
      SplashScreen.hideAsync();
    }
  }, [loaded, isLoggedIn]);

  if (!loaded || isLoggedIn === null) {
    return null;
  }

  return (
    <ThemeProvider value={colorScheme === 'dark' ? DarkTheme : DefaultTheme}>
      <Stack screenOptions={{headerShown: false}}>

          <Stack.Screen name="HomeScreen" options={{ title: 'Home' }} />
          <Stack.Screen name="LoginScreen" options={{ title: 'Login' }} />
          <Stack.Screen name="ActionLog" options={{ title: 'Action Log' }} />
          <Stack.Screen name="Settings" options={{ title: 'Settings' }} />
          <Stack.Screen name="ActionDetails" options={{ title: 'Action Details' }} />
          <Stack.Screen name="AlarmScreen" options={{ title: 'Alarm' }} />
          <Stack.Screen name="RoomsScreen" options={{ title: 'Rooms' }} />
          <Stack.Screen name="RoomScreen" options={{ title: 'Room' }} />
          <Stack.Screen name="DeviceScreen" options={{ title: 'Device' }} />
          <Stack.Screen name="DeviceSettings" options={{ title: 'Device Settings' }} />
          <Stack.Screen name="RoomSettings" options={{ title: 'Room Settings 2' }} />
          <Stack.Screen name="GroupsScreens" options={{ title: 'Groups' }} />
          <Stack.Screen name="GroupScreen" options={{ title: 'Group' }} />
          <Stack.Screen name="GroupSettings" options={{ title: 'Group Settings' }} />
          <Stack.Screen name="AddGroup" options={{ title: 'Add Group' }} />
          <Stack.Screen name="+not-found" />
      </Stack>
      <StatusBar style="auto" />
    </ThemeProvider>
  );
}
